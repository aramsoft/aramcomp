package aramframework.com.utl.fcc.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import aramframework.cmm.util.WebUtil;

/**
 * Spring 기반 File Upload 유틸리티
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class FileUploadUtil  {

	/** Buffer size */
	public static final int BUFFER_SIZE = 8192;

	public static final String SEPERATOR = File.separator;

	/**
	 * 파일을 Upload 처리한다.
	 * 
	 * @param request
	 * @param where
	 * @param maxFileSize
	 * @return
	 * @throws Exception
	 */
	public static List<FileUploadFileVo> uploadFiles(
			HttpServletRequest request, 
			String where, 
			long maxFileSize) throws Exception {
		
		List<FileUploadFileVo> list = new ArrayList<FileUploadFileVo>();

		MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest) request;

		for(MultipartFile mFile: mptRequest.getFileMap().values()) {

			FileUploadFileVo vo = new FileUploadFileVo();

			String tmp = mFile.getOriginalFilename();
			if (tmp.lastIndexOf("\\") >= 0) {
				tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
			}

			vo.setFileName(tmp);
			vo.setContentType(mFile.getContentType());
			vo.setServerSubPath(getTodayString());
			vo.setPhysicalName(getPhysicalFileName());
			vo.setSize(mFile.getSize());

			if (tmp.lastIndexOf(".") >= 0) {
				vo.setPhysicalName(vo.getPhysicalName() + tmp.substring(tmp.lastIndexOf(".")));
			}

			if (mFile.getSize() > 0) {
				saveFile(mFile.getInputStream(),
						new File(WebUtil.filePathBlackList(where + SEPERATOR + vo.getServerSubPath() 
																 + SEPERATOR + vo.getPhysicalName())));
				list.add(vo);
			}
		}

		return list;
	}
	
	/**
	 * 오늘 날짜 문자열 취득. ex) 20090101
	 * 
	 * @return
	 */
	private static String getTodayString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

		return format.format(new Date());
	}

	/**
	 * 물리적 파일명 생성.
	 * 
	 * @return
	 */
	private static String getPhysicalFileName() {
		return FileUploadUUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	/**
	 * 파일명 변환.
	 * 
	 * @param filename
	 *            String
	 * @return
	 * @throws Exception
	 */
	private static String convert(String filename) throws Exception {
		// return java.net.URLEncoder.encode(filename, "utf-8");
		return filename;
	}

	/**
	 * Stream으로부터 파일을 저장함.
	 * 
	 * @param is
	 *            InputStream
	 * @param file
	 *            File
	 * @throws IOException
	 */
	private static long saveFile(InputStream is, File file) throws IOException {
		// 디렉토리 생성
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		OutputStream os = null;
		long size = 0L;

		try {
			os = new FileOutputStream(file);

			int bytesRead = 0;
			byte[] buffer = new byte[BUFFER_SIZE];

			while ((bytesRead = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
				size += bytesRead;
				os.write(buffer, 0, bytesRead);
			}
		} finally {
			if (os != null) {
				os.close();
			}
		}

		return size;
	}

	/**
	 * 파일을 Download 처리한다.
	 * 
	 * @param response
	 * @param where
	 * @param serverSubPath
	 * @param physicalName
	 * @param original
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletResponse response, String where, String serverSubPath, String physicalName, String original) throws Exception {
		String downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName;

		File file = new File(WebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] b = new byte[BUFFER_SIZE];

		original = original.replaceAll("\r", "").replaceAll("\n", "");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + convert(original) + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());

			int read = 0;

			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
		} finally {
			if (outs != null) {
				outs.close();
			}

			if (fin != null) {
				fin.close();
			}
		}
	}

	/**
	 * 이미지에 대한 미리보기 기능을 제공한다.
	 * 
	 * mimeType의 경우는 JSP 상에서 다음과 같이 얻을 수 있다.
	 * getServletConfig().getServletContext().getMimeType(name);
	 * 
	 * @param response
	 * @param where
	 * @param serverSubPath
	 * @param physicalName
	 * @param mimeType
	 * @throws Exception
	 */
	public static void viewFile(HttpServletResponse response, String where, String serverSubPath, String physicalName, String mimeTypeParam) throws Exception {
		String mimeType = mimeTypeParam;
		String downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName;

		File file = new File(WebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] b = new byte[BUFFER_SIZE];

		if (mimeType == null) {
			mimeType = "application/octet-stream;";
		}

		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "filename=image;");

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());

			int read = 0;

			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
			// 2011.10.10 보안점검 후속조치
		} finally {
			if (outs != null) {
				try {
					outs.close();
				} catch (Exception ignore) {}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (Exception ignore) {}
			}
		}
	}
	
}
