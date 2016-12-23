package aramframework.com.utl.sim.service;

//import aramframework.com.utl.sim.service.EgovFileTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * 메뉴관리 Business Interface class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class MenuGov {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	/**
	 * <pre>
	 * Comment : DAT 파일을 파싱하여 메뉴관리화면에 리턴.
	 * </pre>
	 * 
	 * @param String
	 *            parFile DAT파일명
	 * @param String
	 *            parChar 구분자
	 * @param Int
	 *            parField 필드수
	 * @return Vector list
	 * @version 1.0 (2009.02.04.)
	 * @see
	 */
	@SuppressWarnings("rawtypes")
	public static Vector parsFileByMenuChar(String parFile, String parChar, int parField) throws Exception {
		Vector list = null;
		String FileName = null;
		try {
			FileName = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
			File file = new File(FileName);

			// 파일이며, 존재하면 파싱 시작
			if (file.exists() && file.isFile()) {
				list = FileTool.parsFileByChar(parFile, parChar, parField);
			} else {
				list = new Vector();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e); // 2011.10.10 보안점검 후속조치
		}
		return list;
	}

	/**
	 * <pre>
	 * Comment : 메뉴관리 화면의 데이타를 DAT 파일로 생성.
	 * </pre>
	 * 
	 * @param String
	 *            [] menuIDArray ID Array
	 * @param String
	 *            [] menuNameArray Name Array
	 * @param String
	 *            [] menuLevelArray Lefel Array
	 * @param String
	 *            [] menuURLArray URL Array
	 * @return boolean true/false
	 * @version 1.0 (2009.02.04.)
	 * @see
	 */

	public static boolean setDataByDATFile(String parFile, String[] menuIDArray, String[] menuNameArray, String[] menuLevelArray, String[] menuURLArray)
			throws Exception {
		boolean success = false;
		String FileName = null;

		FileName = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(FileName);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		try {

			for (int i = 0; i < menuIDArray.length; i++) { // nodeId | parentNodeId | nodeName | nodeUrl
				out.write(menuIDArray[i] + "|" + menuLevelArray[i] + "|" + menuNameArray[i] + "|" + menuURLArray[i] + "|");
				out.newLine();
			}
			success = true;
			out.close();
		} catch (IOException e) {
			// e.printStackTrace();
			throw new RuntimeException(e); // 2011.10.10 보안점검 후속조치
		} finally {
			if (out != null)
				out.close();
		}
		return success;
	}
}