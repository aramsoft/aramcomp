package aramframework.com.cmm.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import aramframework.com.cmm.domain.ImageHanjaVO;
import aramframework.com.cmm.domain.ImageVO;
import aramframework.com.cmm.domain.ResultWrapVO;
import aramframework.com.cmm.service.OcrHanjaService;

@Controller
public class OcrHanjaController {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private OcrHanjaService ocrHanjaService;

	@RequestMapping(value="/testOcrHanja.do", method=RequestMethod.GET)
	public String testOcrHanja1() {

		return "com/cmm/TestOcrHanja";
	}
	
	@RequestMapping(value="/testOcrHanja.do", method=RequestMethod.POST)
	public String testOcrHanja2(
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

        String api_key = "07f30b2e-b542-44eb-99ec-6d9bc558c8ef";
        
        String imageId = null;
        String orgFileName = null;
        byte[] bytes = null;
        String encodedBase64 = null;
		for (MultipartFile file : multiRequest.getFileMap().values()) {
			// 파일 수정여부 확인
			orgFileName = file.getOriginalFilename(); 
			if (orgFileName != "") {
				if (file.getName().equals("uploadFile")) { 
					bytes = file.getBytes();
					encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
					
					imageId = orgFileName;
					ImageVO imageVO = new ImageVO();
					imageVO.setImageName(file.getOriginalFilename());
					imageVO.setImageType(file.getContentType());
					imageVO.setImage(bytes);
					ocrHanjaService.setImageInfo(imageId, imageVO);
					
					model.addAttribute("orgFileName", orgFileName);
					model.addAttribute("imageId", imageId);
				}
			}
		}	

        // System.out.println("data:image/jpeg;base64,"+encodedBase64);
        String base64Image = "data:image/jpeg;base64,"+encodedBase64;

        URL url = new URL ("http://133.186.211.14:8080/api/v3/page-ocr");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        
        String jsonInputString = "{\"api_key\": \""+api_key+"\",\"image\": \""+base64Image+"\", \"column_info\": \"True\"}";
        
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        
        String responseText = "";
        try(BufferedReader br = new BufferedReader(
          new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            responseText = response.toString();
    		model.addAttribute("responseText", responseText);
        }

        ResultWrapVO resultWrapVO = new ResultWrapVO();
    	ObjectMapper mapper = new ObjectMapper();
       	try {
       		resultWrapVO = mapper.readValue(responseText, ResultWrapVO.class);    
   		} catch (Exception e) {
   			LOG.error(e.getMessage());
   		} 
		model.addAttribute("resultWrapVO", resultWrapVO);

		ArrayList<Object> hanjaList = (ArrayList<Object>)resultWrapVO.getOcr_result();

		StringBuffer hanjaText = new StringBuffer();
		for (Object rowList : hanjaList) {
//			ArrayList<Object> sortedList = getSortedList((ArrayList<Object>)rowList); 
//			for (Object object : sortedList) {
			for (Object object : (ArrayList<Object>)rowList) {
				ImageHanjaVO hanjaVO = getImageHanja(object);
				hanjaText.append(hanjaVO.getHanja());
			}
			hanjaText.append("<br>");
		}
		ocrHanjaService.setHanjaText(imageId, hanjaText.toString());
		model.addAttribute("hanjaText", hanjaText.toString());
		return "com/cmm/TestOcrHanja";
	}
	
	private ArrayList<Object> getSortedList(ArrayList<Object> original) {
		Map<Double, Object> map = new HashMap<>();
		for (Object object : original) {
			Object[] pos = ((ArrayList<Object>)((ArrayList<Object>)object).toArray()[0]).toArray();
			double key = Double.parseDouble(pos[1].toString());	// y
//			int key = (int)pos[0] + ((int)pos[1]/row_height)*image_width;	// x+(y/height)*image_width
			map.put(key, object);
		}

		// sort by key
		List<Map.Entry<Double, Object>> entries =
	        map.entrySet().stream()
	                      .sorted(Map.Entry.comparingByKey())
	                      .collect(Collectors.toList());
		ArrayList<Object> newList = new ArrayList<Object>();
		for (Map.Entry<Double, Object> entry : entries) {
			newList.add(entry.getValue()); 
		}
		return newList;
	}
	
	private ImageHanjaVO getImageHanja(Object object) {
		ImageHanjaVO hanjaVO = new ImageHanjaVO();
//		Object[] pos = ((ArrayList<Object>)((ArrayList<Object>)object).toArray()[0]).toArray();
		String hanja = (String)((ArrayList<Object>)object).toArray()[1];
//		hanjaVO.setX(Double.parseDouble(pos[0].toString()));
//		hanjaVO.setY(Double.parseDouble(pos[1].toString())); 
//		hanjaVO.setW(Double.parseDouble(pos[2].toString()));
//		hanjaVO.setH(Double.parseDouble(pos[3].toString()));
		hanjaVO.setHanja(hanja);
		return hanjaVO;
	}
	
	/**
	 * 커뮤니티 이미지 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	@RequestMapping(value="/getHanjaImage.do", method=RequestMethod.GET)
	public void getHanjaImage(
			@RequestParam String imageId,			
			HttpServletResponse response) 
	throws Exception {
		
		ImageVO imageVO = ocrHanjaService.getImageInfo(imageId);

		byte[] img = imageVO.getImage();
		response.setHeader("Content-Type", imageVO.getImageType());
		response.setHeader("Content-Length", "" + img.length);
		response.setHeader("Cache-Control", "max-age=86400");
		response.getOutputStream().write(img);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	/**
	 * 커뮤니티 이미지 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	@RequestMapping(value="/getHanjaText.do", method=RequestMethod.GET)
	public void getHanjaText(
			@RequestParam String imageId,			
			HttpServletResponse response) 
	throws Exception {
		
		String hanjaText = ocrHanjaService.getHanjaText(imageId);

		// 1. 다운로드 페이지 설정
	    String docName = URLEncoder.encode("hanjaText.txt","UTF-8").replaceAll("\\+", "%20"); 
	    response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
	    response.setContentType("text/plain");

	    // 2. 소스코드 출력
		response.getOutputStream().write(hanjaText.replaceAll("<br>", "\n").getBytes("UTF-8"));
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}    

}
