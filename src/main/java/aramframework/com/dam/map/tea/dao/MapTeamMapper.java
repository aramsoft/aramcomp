package aramframework.com.dam.map.tea.dao;

import java.util.List;

import aramframework.com.dam.map.tea.domain.MapTeamVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식맵(조직별)에 대한 DAO 클래스를 정의한다.
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
public interface MapTeamMapper {

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	public List<EgovMap> selectMapTeamList(MapTeamVO mapTeamVO) ;

	/**
	 * 지식맵(조직별) 목록 총 갯수를 조회한다.
	 * 
	 * @param mapTeamVO
	 */
	public int selectMapTeamListCnt(MapTeamVO mapTeamVO) ;

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	public MapTeamVO selectMapTeamDetail(MapTeamVO mapTeamVO) ;

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * 
	 * @param mapTeamVO
	 */
	public void insertMapTeam(MapTeamVO mapTeamVO) ;

	/**
	 * 기 등록 된 지식맵(조직별) 정보를 수정 한다.
	 * 
	 * @param mapTeamVO
	 */
	public void updateMapTeam(MapTeamVO mapTeamVO) ;

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * 
	 * @param mapTeamVO
	 */
	public void deleteMapTeam(MapTeamVO mapTeamVO) ;

}