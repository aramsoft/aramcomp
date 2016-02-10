package aramframework.com.utl.sys.pxy.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.utl.sys.pxy.domain.ProxySvcLogVO;
import aramframework.com.utl.sys.pxy.domain.ProxySvcVO;

/**
 * 개요 - 프록시서비스정보 및 프록시로그정보에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용 - 프록시서비스정보 및 프록시로그정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
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

@Mapper
public interface ProxySvcMapper  {

	/**
	 * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	public List<ProxySvcVO> selectProxySvcList(ProxySvcVO proxySvcVO);

	/**
	 * 프록시서비스 목록 총 갯수를 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	public int selectProxySvcListCnt(ProxySvcVO proxySvcVO);

	/**
	 * 등록된 프록시서비스의 상세정보를 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	public ProxySvcVO selectProxySvc(ProxySvcVO proxySvcVO);

	/**
	 * 프록시서비스를 신규로 등록한다.
	 * 
	 * @param proxySvcVO
	 */
	public void insertProxySvc(ProxySvcVO proxySvcVO);

	/**
	 * 기 등록된 프록시서비스를 수정한다.
	 * 
	 * @param proxySvcVO
	 */
	public void updateProxySvc(ProxySvcVO proxySvcVO);

	/**
	 * 기 등록된 프록시서비스를 삭제한다.
	 * 
	 * @param proxySvcVO
	 */
	public void deleteProxySvc(ProxySvcVO proxySvcVO);

	/**
	 * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
	 * 
	 * @param proxySvcLogVO
	 */
	public List<ProxySvcLogVO> selectProxySvcLogList(ProxySvcLogVO proxySvcLogVO);

	/**
	 * 프록시로그 목록 총 갯수를 조회한다.
	 * 
	 * @param proxySvcLogVO
	 */
	public int selectProxySvcLogListCnt(ProxySvcLogVO proxySvcLogVO);

	/**
	 * 프록시로그를 생성한다.
	 * 
	 * @param proxySvcLogVO
	 */
	public void insertProxySvcLog(ProxySvcLogVO proxySvcLogVO);

}