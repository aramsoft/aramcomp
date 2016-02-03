package aramframework.com.sym.sym.srv.service.impl;

import java.util.List;

import aramframework.com.sym.sym.srv.domain.ServerEqpmnVO;
import aramframework.com.sym.sym.srv.domain.ServerVO;
import aramframework.com.sym.sym.srv.service.ServerEqpmnRelateVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 서버정보에 대한 DAO 클래스를 정의한다.
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
public interface ServerMapper {

	/**
	 * 서버장비를 관리하기 위해 등록된 서버장비목록을 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public List<EgovMap> selectServerEqpmnList(ServerEqpmnVO serverEqpmnVO);

	/**
	 * 서버장비목록 총 갯수를 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public int selectServerEqpmnListCnt(ServerEqpmnVO serverEqpmnVO);

	/**
	 * 등록된 서버장비의 상세정보를 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public ServerEqpmnVO selectServerEqpmn(ServerEqpmnVO serverEqpmnVO);

	/**
	 * 서버장비정보를 신규로 등록한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public void insertServerEqpmn(ServerEqpmnVO serverEqpmnVO);

	/**
	 * 기 등록된 서버장비정보를 수정한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public void updateServerEqpmn(ServerEqpmnVO serverEqpmnVO);

	/**
	 * 기 등록된 서버장비정보를 삭제한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public void deleteServerEqpmn(ServerEqpmnVO serverEqpmnVO); 

	/**
	 * 서버정보를 관리하기 위해 등록된 서버목록을 조회한다.
	 * 
	 * @param serverVO
	 */
	public List<EgovMap> selectServerList(ServerVO serverVO);

	/**
	 * @param serverVO
	 */
	public int selectServerListCnt(ServerVO serverVO);

	/**
	 * 등록된 서버의 상세정보를 조회한다.
	 * 
	 * @param serverVO
	 */
	public ServerVO selectServer(ServerVO serverVO);

	/**
	 * 서버에 등록된 서버장비목록을 조회한다.
	 * 
	 * @param serverVO
	 */
	public List<EgovMap> selectServerEqpmnRelateDetail(ServerVO serverVO);

	/**
	 * 서버에 등록된 서버장비목록의 카운트를 조회한다.
	 * 
	 * @param serverVO
	 */
	public int selectServerEqpmnRelateDetailTotCnt(ServerVO serverVO);

	/**
	 * 서버정보를 신규로 등록한다.
	 * 
	 * @param serverVO
	 */
	public void insertServer(ServerVO serverVO);

	/**
	 * 기 등록된 서버정보를 수정한다.
	 * 
	 * @param serverVO
	 */
	public void updateServer(ServerVO serverVO);

	/**
	 * 기 등록된 서버정보를 삭제한다.
	 * 
	 * @param serverVO
	 */
	public void deleteServer(ServerVO serverVO);

	/**
	 * 서버장비관계정보를 관리하기 위해 대상 서버장비목록을 조회한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public List<EgovMap> selectServerEqpmnRelateList(ServerEqpmnRelateVO serverEqpmnRelateVO);

	/**
	 * 서버장비관계 대상 목록 총 갯수를 조회한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public int selectServerEqpmnRelateListCnt(ServerEqpmnRelateVO serverEqpmnRelateVO);

	/**
	 * 서버장비관계정보를 신규로 등록한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public void insertServerEqpmnRelate(ServerEqpmnRelateVO serverEqpmnRelateVO);

	/**
	 * 기 등록된 서버장비관계정보를 삭제한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public void deleteServerEqpmnRelate(ServerEqpmnRelateVO serverEqpmnRelateVO);

}