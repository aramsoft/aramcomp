package aramframework.com.utl.sim.service;

//import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//import com.artofsolving.jodconverter.DocumentConverter;
//import com.artofsolving.jodconverter.DocumentFamily;
//import com.artofsolving.jodconverter.DocumentFormat;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.util.WebUtil;
//import aramframework.com.utl.fcc.service.StringUtil;

/**
 * xls,doc,ppt를 Pdf로 변환하는 화면 Business Interface class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class PdfCnvr {
	
	public static String addrIP = "";
	static final char FILE_SEPARATOR = File.separatorChar;
	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	public static final int BUFF_SIZE = 2048;
	public static String stordFilePath = AramProperties.getProperty("Globals.fileStorePath");

	/**
	 * @param oHTMLText
	 * @param oOutputStream
	 */
	public static void convertFromHTMLToPDF(String targetPdf, String oHTMLText) {
		/*
		try {
			// connect to an OpenOffice.org instance running on port 8100
			SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
			connection.connect();

			// 원본 디렉토리에 targetPdf 명칭지정
			String pdfFileValue = stordFilePath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
			OutputStream oOutputStream = new FileOutputStream(pdfFileValue + "/" + targetPdf + ".pdf");

			DocumentFormat inputDocumentFormat = new DocumentFormat("HTML",
					DocumentFamily.TEXT, "text/html", "html");
			inputDocumentFormat.setExportFilter(DocumentFamily.TEXT,
					"HTML 	(StarWriter)");
			DocumentFormat outputDocumentFormat = new DocumentFormat(
					"Portable	Document Format", DocumentFamily.TEXT,
					"application/pdf", "pdf");
			outputDocumentFormat.setExportFilter(DocumentFamily.TEXT,
					"writer_pdf_Export");
			DocumentConverter oDocumentConverter = new OpenOfficeDocumentConverter(connection);
			oDocumentConverter.convert(
					new ByteArrayInputStream(oHTMLText.getBytes()),
					inputDocumentFormat, oOutputStream, outputDocumentFormat);
		} catch (Exception e) {
			LOG.error(e);
		}
*/
	}

	/**
	 * <pre>
	 * Comment : doc, xls 파일등을 PDF변환 변환한다. .
	 * </pre>
	 * 
	 * @param String
	 *            pdfFileSrc doc, xls 파일 전체경로
	 * @param String
	 *            targetPdf 변환파일명(확장자 제외)
	 * @return boolean status true/false 를 리턴한다.
	 * @version 1.0 (2009.02.10)
	 * @see
	 */
	public static boolean getPDF(String targetPdf, MultipartHttpServletRequest mptRequest) throws Exception {
		boolean status = false;
/*
		try {
			for (MultipartFile mFile : mptRequest.getFileMap().values()) {

				if (mFile.getSize() > 0) {

					// Write File 이후 Move File????
					String newName = "";
					String orginFileName = mFile.getOriginalFilename();

					int _index = orginFileName.lastIndexOf(".");
					String fileExt = orginFileName.substring(_index + 1);

					// newName 은 Naming Convention에 의해서 생성
					newName = StringUtil.getTimeStamp() + "." + fileExt;
					writeFile(mFile, newName, stordFilePath);

					String pdfFileSrcValue = stordFilePath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
					File inputFile = new File(WebUtil.filePathBlackList(pdfFileSrcValue + newName));

					if (inputFile.exists()) {
						// connect to an OpenOffice.org instance running on port 8100
						SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
						connection.connect();
						// 원본 디렉토리에 targetPdf 명칭지정
						String valueFile = null;
						valueFile = inputFile.getParent().replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
						File outputFile = new File(valueFile + "/" + targetPdf + ".pdf");
						// convert
						DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
						converter.convert(inputFile, outputFile);
						// close the connection
						connection.disconnect();

						if (inputFile.exists()) {
							// 3. 삭제해줍니다.
							status = inputFile.delete();
						}
						status = true;
					} else {
						status = false;
					}
				}
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println(ex); // 보안점검 후속조치
			status = false;
		}
		// 메소드 종료 Log
*/
		return status;
	}

	/**
	 * 파일을 실제 물리적인 경로에 생성한다.
	 * 
	 * @param file
	 * @param newName
	 * @param stordFilePath
	 * @throws Exception
	 */
	// 2011.10.10 보안점검 후속조치
	protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = file.getInputStream();
			File cFile = new File(WebUtil.filePathBlackList(stordFilePath));

			if (!cFile.isDirectory())
				cFile.mkdir();

			bos = new FileOutputStream(WebUtil.filePathBlackList(stordFilePath + File.separator + newName));

			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];

			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception ignore) {
					System.out.println("IGNORE: " + ignore);
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception ignore) {
					System.out.println("IGNORE: " + ignore);
				}
			}
		}
	}
	
}