package aramframework.com.cmm.file.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import aramframework.cmm.util.WebUtil;
import aramframework.com.cmm.file.domain.FileVO;
import aramframework.com.cmm.file.service.FileMngService;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 파일 업로드/다운로드 유틸리티 컴포넌트 
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Component("fileMngUtil")
public class FileMngUtil {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${Globals.fileStorePath}")
	private String FILE_UPLOAD_DIR = "";
	
	public static final int BUFF_SIZE = 2048;

	@Autowired
	private EgovIdGnrService fileIdGnrService; 

	@Autowired
	private FileMngService fileMngService;

	/**
	 * 첨부파일을 새로 저장한다.
	 * 
	 * @param 	multiRequest	MultipartHttpServletRequest
	 * @param 	KeyStr			String
	 * @return					String
	 * @throws Exception
	 */
	public String insertMultiFile(MultipartHttpServletRequest multiRequest, String category) 
	throws Exception {
		String atchFileId = "";
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = parseFileInf(files, category, 0, "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		return atchFileId;
	}
		
	/**
	 * 첨부파일을 수정 저장한다.
	 * 
	 * @param 	multiRequest	MultipartHttpServletRequest
	 * @param 	KeyStr			String
	 * @param 	atchFileId		String
	 * @return					String
	 * @throws Exception
	 */
	public String updateMultiFile(MultipartHttpServletRequest multiRequest, String category, String atchFileId) 
	throws Exception {
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
				List<FileVO> result = parseFileInf(files, category, 0, atchFileId);
				atchFileId = fileMngService.insertFileInfs(result);
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int fileSn = fileMngService.getMaxFileSN(fvo);
				List<FileVO> result = parseFileInf(files, category, fileSn, atchFileId);
				fileMngService.addFileInfs(result);
			}
		}
		return atchFileId;
	}
		
	/**
	 * 첨부파일을 삭제한다.
	 * 
	 * @param 	atchFileId		String
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
	 * @param 	files			Map
	 * @param 	KeyStr			String
	 * @param 	fileKeyParam	int
	 * @param 	atchFileId		String
	 * @param 	storePath		String
	 * @return					List
	 * @throws Exception
	 */
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String category, 
			int fileSn, String atchFileId) 
	throws Exception {
		
		if (atchFileId == null || "".equals(atchFileId) ) {
			atchFileId = fileIdGnrService.getNextStringId();
		} 

		File saveFolder = new File(WebUtil.filePathBlackList(FILE_UPLOAD_DIR));

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
			logger.debug("origin file name => " + orginFileName);
			
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
			String newName = atchFileId + "_" + fileSn + "_" + category + "." + fileExt;
			long _size = file.getSize();

			if (!"".equals(orginFileName)) {
				filePath = FILE_UPLOAD_DIR + File.separator + newName;
				file.transferTo(new File(WebUtil.filePathBlackList(filePath)));
			}
			fvo = new FileVO();
			fvo.setFileExtsn(fileExt);
			fvo.setFileType(file.getContentType());
			fvo.setFileStreCours(FILE_UPLOAD_DIR);
			fvo.setFileSize(_size);
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileId);
			fvo.setCategory(category);
			fvo.setFileSn(fileSn);

			// writeFile(file, newName, storePathString);
			result.add(fvo);

			fileSn++;
		}

		return result;
	}

	/**
	 * 2011.08.09
	 * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	 * 
	 * @return Timestamp 값
	 */
/*
	private String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());

			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			logger.error("IGNORED: " + e.getMessage());
		}

		return rtnStr;
	}
*/	
}
