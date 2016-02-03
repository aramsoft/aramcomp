package aramframework.com.utl.sys.nsm.service;

import java.util.List;

import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngLogVO;
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngVO;

/**
 * 개요 - 네트워크서비스 모니터링대상에 대한 Service Interface를 정의한다.
 * 
 * 상세내용 - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 네트워크서비스 모니터링대상의 조회기능은  목록조회, 상세조회로 구분된다.
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

public interface NtwrkSvcMntrngService {

	/**
	 * 네트워크서비스 모니터링대상 목록을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public List<NtwrkSvcMntrngVO> selectNtwrkSvcMntrngList(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링대상 총 갯수를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public int selectNtwrkSvcMntrngListCnt(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링대상을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public NtwrkSvcMntrngVO selectNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링대상을 수정한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void updateNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링대상을 등록한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void insertNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링대상을 등록하기 위한 중복 조회를 수행한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public int selectNtwrkSvcMntrngCheck(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링대상을 삭제한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void deleteNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링 결과를 수정한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void updateNtwrkSvcMntrngSttus(NtwrkSvcMntrngVO ntwrkSvcMntrngVO);

	/**
	 * 네트워크서비스 모니터링 로그 목록을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public List<NtwrkSvcMntrngLogVO> selectNtwrkSvcMntrngLogList(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO);

	/**
	 * 네트워크서비스 모니터링대상 로그 총 갯수를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public int selectNtwrkSvcMntrngLogListCnt(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO);

	/**
	 * 네트워크서비스 모니터링 로그를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public NtwrkSvcMntrngLogVO selectNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO);

	/**
	 * 네트워크서비스 모니터링 로그를 등록한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public void insertNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO);
	
}