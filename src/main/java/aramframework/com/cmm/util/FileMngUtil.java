package aramframework.com.cmm.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.service.FileMngService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 파일 업로드/다운로드 유틸리티 컴포넌트 
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

@Component("fileMngUtil")
public class FileMngUtil {

	public static final int BUFF_SIZE = 2048;

	@Autowired
	private EgovIdGnrService fileIdGnrService; 

	@Autowired
	private FileMngService fileMngService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 첨부파일을 새로 저장한다.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public String insertMultiFile(MultipartHttpServletRequest multiRequest, String KeyStr) 
	throws Exception {
		String atchFileId = "";
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = parseFileInf(files, KeyStr, 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		return atchFileId;
	}
		
	/**
	 * 첨부파일을 수정 저장한다.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public String updateMultiFile(MultipartHttpServletRequest multiRequest, String KeyStr, String atchFileId) 
	throws Exception {
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
				List<FileVO> result = parseFileInf(files, KeyStr, 0, atchFileId, "");
				atchFileId = fileMngService.insertFileInfs(result);
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> result = parseFileInf(files, KeyStr, cnt, atchFileId, "");
				fileMngService.addFileInfs(result);
			}
		}
		return atchFileId;
	}
		
	/**
	 * 첨부파일을 삭제한다.
	 * 
	 * @param files
	 * @return
	 */
	public void deleteMultiFile(String atchFileId) {
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(atchFileId);
		if (fvo.getAtchFileId() != null && !"".equals(fvo.getAtchFileId()) ) {
			fileMngService.deleteAllFileInf(fvo);
		}
	}
		
	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, 
			int fileKeyParam, String atchFileId, String storePath) 
	throws Exception {
		
		int fileKey = fileKeyParam;

		String storePathString = "";
		String atchFileIdString = "";

		if (storePath == null || "".equals(storePath) ) {
			storePathString = AramProperties.getProperty("Globals.fileStorePath");
		} else {
			storePathString = AramProperties.getProperty(storePath);
		}

		if (atchFileId == null || "".equals(atchFileId) ) {
			atchFileIdString = fileIdGnrService.getNextStringId();
		} else {
			atchFileIdString = atchFileId;
		}

		File saveFolder = new File(WebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();
			LOG.debug("origin file name => " + orginFileName);
			
			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			}
			// //------------------------------------

			int index = orginFileName.lastIndexOf(".");
			// String fileName = orginFileName.substring(0, index);
			String fileExt = orginFileName.substring(index + 1);
			String newName = KeyStr + getTimeStamp() + fileKey;
			long _size = file.getSize();

			if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(WebUtil.filePathBlackList(filePath)));
			}
			fvo = new FileVO();
			fvo.setFileExtsn(fileExt);
			fvo.setFileType(file.getContentType());
			fvo.setFileStreCours(storePathString);
			fvo.setFileSize(Long.toString(_size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));

			// writeFile(file, newName, storePathString);
			result.add(fvo);

			fileKey++;
		}

		return result;
	}

	/**
	 * 2011.08.09
	 * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	 * 
	 * @param
	 * @return Timestamp 값
	 * @exception MyException
	 * @see
	 */
	private String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());

			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			LOG.error("IGNORED: " + e.getMessage());
		}

		return rtnStr;
	}
}
