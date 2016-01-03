package aramframework.com.utl.sys.fsm.service;

import java.util.List;

/**
 * 개요 - 파일시스템 모니터링대상에 대한 Service Interface를 정의한다.
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

public interface FileSysMntrngService {

	/**
	 * 파일시스템 모니터링대상 목록을 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public List<FileSysMntrngVO> selectFileSysMntrngList(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링대상 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public int selectFileSysMntrngListCnt(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링대상을 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public FileSysMntrngVO selectFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링대상을 등록한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void insertFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링대상을 수정한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void updateFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링대상을 삭제한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void deleteFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템의 크기를 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public int selectFileSysMg(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링 결과를 수정한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void updateFileSysMntrngSttus(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링로그 목록을 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public List<FileSysMntrngLogVO> selectFileSysMntrngLogList(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 파일시스템 모니터링로그 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public int selectFileSysMntrngLogListCnt(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 파일시스템 모니터링로그를 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public FileSysMntrngLogVO selectFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 파일시스템 모니터링로그를 등록한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public void insertFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO);
	
}