package aramframework.com.cmm.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

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

import aramframework.com.cmm.domain.ImageVO;
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
				if (file.getName().equals("cmmntyImageName")) {
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
        
        String jsonInputString = "{\"api_key\": \""+api_key+"\",\"image\": \""+base64Image+"\"}";
        
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        
        try(BufferedReader br = new BufferedReader(
          new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            
    		model.addAttribute("resultText", response.toString());
        }

		return "com/cmm/TestOcrHanja";
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

}
