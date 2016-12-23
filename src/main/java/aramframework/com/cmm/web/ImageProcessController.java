package aramframework.com.cmm.web;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.service.FileMngService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.utl.sim.service.FileCmprs;

/**
 * 이미지 파일 처리를 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ImageProcessController {

	@Autowired
	private FileMngService fileMngService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
	 * 
	 * @param atchFileId
	 * @param fileSn
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/content/imagefiles/{filePathId}/file/{fileSn}")
	public void getImage(
			@PathVariable String filePathId, 
			@PathVariable String fileSn, 
			@RequestParam(value="width", required=false) String strWidth,
			HttpServletResponse response) 
	throws Exception {

		String fileId = WebUtil.getOriginalId(filePathId, "FILE_");
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(fileId);
		fileVO.setFileSn(fileSn);
		
		fileVO = fileMngService.selectFileInf(fileVO);

		// 2011.10.10 보안점검 후속조치
		String source = fileVO.getFileStreCours() +  fileVO.getStreFileNm();
		source = source.replace('\\', File.separatorChar).replace('/', File.separatorChar);
		File file = new File(source);

		FileInputStream fis = null;
		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();

		try {

			if( strWidth!=null && !strWidth.equals("") ) {
				int width = Integer.parseInt(strWidth);
				BufferedImage bi = ImageIO.read(file);
				int itype = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();
		 
				int height = (int)(((float)bi.getHeight()/(float)bi.getWidth())*width);
				BufferedImage resizedImage = new BufferedImage(width, height, itype);
				Graphics2D g = resizedImage.createGraphics();
				g.drawImage(bi, 0, 0, width, height, null);
				g.dispose();
	
				ImageIO.write(resizedImage, fileVO.getFileExtsn().toLowerCase(), bStream); 
			} else {
				fis = new FileInputStream(file);
				in = new BufferedInputStream(fis);

				int imgByte;
				while ((imgByte = in.read()) != -1) {
					bStream.write(imgByte);
				}
			}
			
			String type = "";

			if (fileVO.getFileExtsn() != null && !"".equals(fileVO.getFileExtsn())) {
/*
				if ("jpg".equals(fileVO.getFileExtsn().toLowerCase())) {
					type = "image/jpeg";
				} else {
					type = "image/" + fileVO.getFileExtsn().toLowerCase();
				}
*/
				type = "image/" + fileVO.getFileExtsn().toLowerCase();

			} else {
				LOG.debug("Image fileType is null.");
			}

			response.setHeader("Content-Type", type);
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

			// 2011.10.10 보안점검 후속조치 끝
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception ignore) {
					LOG.error("IGNORE: " + ignore.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					LOG.error("IGNORE: " + ignore.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ignore) {
					LOG.error("IGNORE: " + ignore.getMessage());
				}
			}
		}
	}
	
	/**
	 * 이미지의 크기를 줄인다.
	 * 
	 * @param atchFileId
	 * @param fileSn
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/reduceImage.do")
	public void reduceImage(
			FileVO fileVO) 
	throws Exception {

		fileMngService.selectFileInf(fileVO);
		String source = fileVO.getFileStreCours() +  fileVO.getStreFileNm();
		String target = fileVO.getFileStreCours() +  "test.jpg";
		FileCmprs.cmprsJpeg(source, target);
	}

	/**
	 * 이미지의 폭을 수정한다.
	 * 
	 * @param atchFileId
	 * @param fileSn
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/resizeImage.do")
	public void resizeImage(
			@RequestParam(value="width", required=false) int width,
			FileVO fileVO) 
	throws Exception {

		fileMngService.selectFileInf(fileVO);
		String source = fileVO.getFileStreCours() +  fileVO.getStreFileNm();
		String target = fileVO.getFileStreCours() +  "test.jpg";
		FileCmprs.resizeJpeg(source, target, width);
	}
}
