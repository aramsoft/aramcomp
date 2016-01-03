package aramframework.com.utl.sys.ssy.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.utl.sys.ssy.service.SynchrnServerVO;

/**
 * 개요 - 동기화대상 서버에 대한 DAO 클래스를 정의한다.
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

@Mapper("synchrnServerMapper")
public interface SynchrnServerMapper {

	/**
	 * 동기화대상 서버를 관리하기 위해 등록된 동기화대상 서버목록을 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public List<SynchrnServerVO> selectSynchrnServerList(SynchrnServerVO synchrnServerVO);

	/**
	 * 동기화대상 서버목록 총 갯수를 조회한다.
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
	 */
	public void processSynchrn(SynchrnServerVO synchrnServerVO);

	/**
	 * 동기화 처리를 하기 위해 동기화대상 서버목록을 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public List<SynchrnServerVO> processSynchrnServerList(SynchrnServerVO synchrnServerVO);
	
}