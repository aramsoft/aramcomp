package aramframework.mbl.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.service.FileMngService;
import aramframework.com.cmm.util.WebUtil;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class MblFileDownloadController {

	@Autowired
	private FileMngService fileService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 브라우저 구분 얻기.
	 * 
	 * @param 	request	HttpServletRequest
	 * @return			브라우저 이름
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) {
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 * 
	 * @param 	filename	파일이름
	 * @param 	request		HttpServletRequest
	 * @param 	response	HttpServletResponse
	 * @throws 	Exception
	 */
	private void setDisposition(
			String filename, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE") || browser.equals("Trident")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			// throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 * 
	 * @param 	filePathId	파일ID
	 * @param 	fileSn		파일번호
	 * @param 	request		HttpServletRequest
	 * @param 	response	HttpServletResponse
	 * @throws 				Exception
	 */
	@RequestMapping(value = "/content/mbl/files/{filePathId}/file/{fileSn}")
	public void cvplFileDownload(
			@PathVariable String filePathId, 
			@PathVariable String fileSn, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception {

		String fileId = WebUtil.getOriginalId(filePathId, "FILE_");
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(fileId);
		fileVO.setFileSn(fileSn);
		
		fileVO = fileService.selectFileInf(fileVO);

		File uFile = new File(fileVO.getFileStreCours(), fileVO.getStreFileNm());
		int fSize = (int) uFile.length();

		if (fSize > 0) {
//			String mimetype = "application/x-msdownload";
//			String mimetype = "application/octet-stream";
			String mimetype = fileVO.getFileType();

			response.setContentType(mimetype);
			setDisposition(fileVO.getOrignlFileNm(), request, response);
			response.setContentLength(fSize);

			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				LOG.error("IGNORED: " + ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						LOG.error("IGNORED: " + ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						LOG.error("IGNORED: " + ignore.getMessage());
					}
				}
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fileVO.getOrignlFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}
	
}
