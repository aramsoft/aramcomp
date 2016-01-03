package aramframework.com.utl.sys.ssy.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 개요 - 동기화대상 서버에 대한 Service Interface를 정의한다.
 * 
 * 상세내용 - 동기화대상 서버에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 동기화대상 서버의 조회기능은 목록조회, 상세조회로 구분된다.
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

public interface SynchrnServerService {

	/**
	 * 동기화대상 서버를 관리하기 위해 등록된 서버목록을 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public List<SynchrnServerVO> selectSynchrnServerList(SynchrnServerVO synchrnServerVO);

	/**
	 * 동기화대상 서버 목록 총 갯수를 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public int selectSynchrnServerListCnt(SynchrnServerVO synchrnServerVO);

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public SynchrnServerVO selectSynchrnServer(SynchrnServerVO synchrnServerVO);

	/**
	 * 등록된 동기화대상 서버의 파일 목록을 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public List<String> selectSynchrnServerFiles(SynchrnServerVO synchrnServerVO);

	/**
	 * 등록된 동기화대상 서버의 파일을 삭제한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void deleteSynchrnServerFile(SynchrnServerVO synchrnServerVO);

	/**
	 * 등록된 동기화대상 서버의 파일을 다운로드 한다.
	 * 
	 * @param synchrnServerVO
	 * @param fileNm
	 */
	public void downloadFtpFile(SynchrnServerVO synchrnServerVO, String fileNm);

	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void insertSynchrnServer(SynchrnServerVO synchrnServerVO);

	/**
	 * 기 등록된 동기화대상 서버정보를 수정한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void updateSynchrnServer(SynchrnServerVO synchrnServerVO);

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void deleteSynchrnServer(SynchrnServerVO synchrnServerVO);

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * 
	 * @param synchrnServerVO
	 * @param uploadFile
	 */
	public boolean processSynchrn(SynchrnServerVO synchrnServerVO, File[] uploadFile);

	/**
	 * 업로드 파일의 목록을 조회한다.
	 * 
	 * @param filePath
	 */
	public List<String> getFileName(String filePath);

	/**
	 * 동기화 대상 파일을 업로드 한다.
	 * 
	 * @param multipartFile
	 * @param newName
	 * @param stordFilePath
	 * @param synchrnServerVO
	 */
	public void writeFile(MultipartFile multipartFile, String newName, String stordFilePath, SynchrnServerVO synchrnServerVO);

	/**
	 * 업로드 파일을 삭제한다.
	 * 
	 * @param deleteFiles
	 * @param stordFilePath
	 * @param synchrnServerVO
	 */
	public void deleteFile(String deleteFiles, String stordFilePath, SynchrnServerVO synchrnServerVO);
	
}