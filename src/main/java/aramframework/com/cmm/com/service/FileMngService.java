package aramframework.com.cmm.com.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

import aramframework.com.cmm.com.dao.FileMngMapper;
import aramframework.com.cmm.com.domain.FileVO;

/**
 * 파일정보의 관리를 위한 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class FileMngService extends EgovAbstractServiceImpl {

	@Autowired 
	private FileMngMapper fileMngMapper;	

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 * 
	 * @param 	fileVO	FileVO
	 * @return			List
	 */
	public List<FileVO> selectFileListByFileNm(FileVO fileVO) {
		return fileMngMapper.selectFileListByFileNm(fileVO);
	}
	
	/**
	 * 파일명 검색에 대한 목록 총갯수을 조회한다.
	 * 
	 * @param 	fileVO	FileVO
	 * @return			총갯수
	 */
	public int selectFileListCntByFileNm(FileVO fileVO) {
		return fileMngMapper.selectFileListCntByFileNm(fileVO);
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 * 
	 * @param 	fileVO	FileVO
	 * @return			List
	 */
	public List<FileVO> selectFileList(FileVO fileVO) {
		return fileMngMapper.selectFileList(fileVO);
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 * 
	 * @param 	fileVO	FileVO
	 * @return			List
	 */
	public List<FileVO> selectImageFileList(FileVO fileVO) {
		return fileMngMapper.selectImageFileList(fileVO);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 * 
	 * @param 	fileVO	FileVO
	 * @return			FileVO
	 */
	public FileVO selectFileInf(FileVO fileVO) {
		return  fileMngMapper.selectFileInf(fileVO);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 * 
	 * @param 	fileVO	FileVO
	 * @return			최대값
	 */
	public int getMaxFileSN(FileVO fileVO) {
		return fileMngMapper.getMaxFileSN(fileVO);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 * 
	 * @param 	fvoList		List
	 * @return				atchFileId
	 */
	public String insertFileInfs(List<FileVO> fvoList) {
		String atchFileId = "";

		if (fvoList.size() != 0) {
			atchFileId = fileMngMapper.insertFileInfs(fvoList);
		}
		if (atchFileId == "") {
			atchFileId = null;
		}
		return atchFileId;
	}

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 * 
	 * @param 	fileVO	FileVO
	 * @return			atchFileId
	 */
	public String insertFileInf(FileVO fileVO) {
		fileMngMapper.insertFileInf(fileVO);
		return fileVO.getAtchFileId();
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 * 
	 * @param 	fvoList		List
	 */
	public void addFileInfs(List<FileVO> fvoList) {
		// Delete & Insert
		fileMngMapper.addFileInfs(fvoList);
	}

	/**
	 * 전체 파일을 삭제한다.
	 * 
	 * @param 	fileVO	FileVO
	 */
	public void deleteAllFileInf(FileVO fileVO) {
		deleteFileAll(fileVO);
		fileMngMapper.deleteAllFileInf(fileVO);
	}

	/**
	 * 여러 개의 파일을 삭제한다.
	 * 
	 * @param 	fvoList		List
	 */
	public void deleteFileInfs(List<FileVO> fvoList) {
		deleteFileList(fvoList);
		fileMngMapper.deleteFileInfs(fvoList);
	}

	/**
	 * 하나의 파일을 삭제한다.
	 * 
	 * @param 	fileVO	FileVO
	 */
	public void deleteFileInf(FileVO fileVO) {
		deleteFile(fileVO);
		fileMngMapper.deleteFileInf(fileVO);
	}

	/**
	 * 전체 파일을 삭제한다.
	 * 
	 * @param 	fileVO	FileVO
	 */
	public void deleteFileAll(FileVO fileVO) {
		List<FileVO> fileList = selectFileList(fileVO);
		deleteFileList(fileList);
	}	

	/**
	 * 전체 파일을 삭제한다.
	 * 
	 * @param 	fvoList	List
	 */
	public void deleteFileList(List<FileVO> fvoList) {
		for(FileVO fileVO: fvoList) {
			deleteFile(fileVO);
		}
	}	
	
	/**
	 * 하나의 파일을 삭제한다.
	 * 
	 * @param 	fileVO	FileVO
	 */
	public void deleteFile(FileVO fileVO) {
		if( fileVO.getStreFileNm() == null || !"".equals(fileVO.getStreFileNm())) {
			selectFileInf(fileVO);
		}	
		File uFile = new File(fileVO.getFileStreCours(), fileVO.getStreFileNm());
		uFile.delete();
	}	

}
