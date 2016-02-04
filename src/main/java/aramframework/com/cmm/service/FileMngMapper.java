package aramframework.com.cmm.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import aramframework.com.cmm.domain.FileVO;

/**
 * 파일정보 관리를 위한 데이터 처리 클래스
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

@Repository
public class FileMngMapper extends EgovAbstractMapper {

	final static String NAMESPACE = "aramframework.com.cmm.service.FileMngMapper";
	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 */
	public List<FileVO> selectFileListByFileNm(FileVO fileVO) {
		return selectList(NAMESPACE+".selectFileListByFileNm", fileVO);
	}

	/**
	 * 파일명 검색에 대한 목록 전체 건수를 조회한다.
	 * 
	 * @param fileVO
	 */
	public int selectFileListCntByFileNm(FileVO fileVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".selectFileListCntByFileNm", fileVO);
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 */
	public List<FileVO> selectFileList(FileVO fileVO) {
		return selectList(NAMESPACE+".selectFileList", fileVO);
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 */
	public List<FileVO> selectImageFileList(FileVO fileVO) {
		return selectList(NAMESPACE+".selectImageFileList", fileVO);
	}
	
	/**
	 * 파일에 대한 상세정보를 조회한다.
	 * 
	 * @param fileVO
	 */
	public FileVO selectFileInf(FileVO fileVO) {
		return (FileVO) selectOne(NAMESPACE+".selectFileInf", fileVO);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 * 
	 * @param fileVO
	 */
	public int getMaxFileSN(FileVO fileVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".getMaxFileSN", fileVO);
	}

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 * 
	 * @param fileVO
	 */
	public void insertFileInf(FileVO fileVO) {
		insert(NAMESPACE+".insertFileMaster", fileVO);
		insert(NAMESPACE+".insertFileDetail", fileVO);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 * 
	 * @param fileList
	 */
	public String insertFileInfs(List<FileVO> fileList) {
		FileVO fileVO = fileList.get(0);
		String atchFileId = fileVO.getAtchFileId();		// FileUtil에서 구해짐
		insert(NAMESPACE+".insertFileMaster", fileVO);

		for(FileVO fVO : fileList) {
			insert(NAMESPACE+".insertFileDetail", fVO);
		}

		return atchFileId;
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 추가한다.
	 * 
	 * @param fileList
	 */
	public void addFileInfs(List<FileVO> fileList) {
		for(FileVO fileVO : fileList) {
			insert(NAMESPACE+".insertFileDetail", fileVO);
		}
	}

	/**
	 * 전체 파일을 삭제한다.
	 * 
	 * @param fileVO
	 */
	public void deleteAllFileInf(FileVO fileVO) {
		delete(NAMESPACE+".deleteFileDetails", fileVO);
		delete(NAMESPACE+".deleteFile", fileVO);
//		update(NAMESPACE+".deleteUpdateFile", fileVO);
	}

	/**
	 * 하나의 파일을 삭제한다.
	 * 
	 * @param fileVO
	 */
	public void deleteFileInf(FileVO fileVO) {
		delete(NAMESPACE+".deleteFileDetail", fileVO);
	}

	/**
	 * 여러 파일을 삭제한다.
	 * 
	 * @param fileVO
	 */
	public void deleteFileList(FileVO fileVO) {
		delete(NAMESPACE+".deleteFileDetails", fileVO);
	}
	
	/**
	 * 여러 개의 파일을 삭제한다.
	 * 
	 * @param fileList
	 */
	public void deleteFileInfs(List<FileVO> fileList) {
		for(FileVO fileVO : fileList) {
			delete(NAMESPACE+".deleteFileDetail", fileVO);
		}
	}

}
