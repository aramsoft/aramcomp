package aramframework.com.dam.map.tea.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식맵(조직별)에 대한 Service Interface를 정의한다.
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

public interface MapTeamService {

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	List<EgovMap> selectMapTeamList(MapTeamVO mapTeamVO);

	/**
	 * 지식맵(조직별) 목록 총 갯수를 조회한다.
	 * 
	 * @param mapTeamVO
	 */
	int selectMapTeamListCnt(MapTeamVO mapTeamVO);

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	void selectMapTeamDetail(MapTeamVO mapTeamVO);

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * 
	 * @param mapTeamVO
	 */
	void insertMapTeam(MapTeamVO mapTeamVO);

	/**
	 * 기 등록 된 지식맵(조직별) 정보를 수정 한다.
	 * 
	 * @param mapTeamVO
	 */
	void updateMapTeam(MapTeamVO mapTeamVO);

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * 
	 * @param mapTeamVO
	 */
	void deleteMapTeam(MapTeamVO mapTeamVO);

}