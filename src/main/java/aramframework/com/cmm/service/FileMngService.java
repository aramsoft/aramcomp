package aramframework.com.cmm.service;

import java.util.List;

/**
 * 파일정보의 관리를 위한 서비스 인터페이스
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

public interface FileMngService {

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 */
	public List<FileVO> selectFileListByFileNm(FileVO fileVO);

	/**
	 * 파일명 검색에 대한 목록 총갯수을 조회한다.
	 * 
	 * @param fileVO
	 */
	public int selectFileListCntByFileNm(FileVO fileVO);

	/**
	 * 파일에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 */
	public List<FileVO> selectFileList(FileVO fileVO);

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 */
	public List<FileVO> selectImageFileList(FileVO fileVO);

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 * 
	 * @param fileVO
	 */
	public FileVO selectFileInf(FileVO fileVO);

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 * 
	 * @param fileVO
	 */
	public int getMaxFileSN(FileVO fileVO);

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 * 
	 * @param fvoList
	 */
	public String insertFileInfs(List<FileVO> fvoList);

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 * 
	 * @param fileVO
	 */
	public String insertFileInf(FileVO fileVO);

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 * 
	 * @param fvoList
	 */
	public void addFileInfs(List<FileVO> fvoList);

	/**
	 * 전체 파일을 삭제한다.
	 * 
	 * @param fileVO
	 */
	public void deleteAllFileInf(FileVO fileVO);

	/**
	 * 여러 개의 파일을 삭제한다.
	 * 
	 * @param fvoList
	 */
	public void deleteFileInfs(List<FileVO> fvoList);

	/**
	 * 하나의 파일을 삭제한다.
	 * 
	 * @param fileVO
	 */
	public void deleteFileInf(FileVO fileVO);

}
