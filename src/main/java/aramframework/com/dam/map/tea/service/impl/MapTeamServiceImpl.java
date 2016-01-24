package aramframework.com.dam.map.tea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.dam.map.tea.service.MapTeamService;
import aramframework.com.dam.map.tea.service.MapTeamVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식맵(조직별)에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("mapTeamService")
public class MapTeamServiceImpl extends EgovAbstractServiceImpl implements MapTeamService {

	@Autowired
	private MapTeamMapper mapTeamMapper;

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	public List<EgovMap> selectMapTeamList(MapTeamVO mapTeamVO) {
		return mapTeamMapper.selectMapTeamList(mapTeamVO);
	}

	/**
	 * 지식맵(조직별) 목록 총 갯수를 조회한다.
	 * 
	 * @param mapTeamVO
	 */
	public int selectMapTeamListCnt(MapTeamVO mapTeamVO) {
		return mapTeamMapper.selectMapTeamListCnt(mapTeamVO);
	}

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	public void selectMapTeamDetail(MapTeamVO mapTeamVO) {
		MapTeamVO resultVo = mapTeamMapper.selectMapTeamDetail(mapTeamVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, mapTeamVO); 
	}

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * 
	 * @param mapTeamVO
	 */
	public void insertMapTeam(MapTeamVO mapTeamVO) {
		mapTeamMapper.insertMapTeam(mapTeamVO);
	}

	/**
	 * 기 등록 된 지식맵(조직별) 정보를 수정 한다.
	 * 
	 * @param mapTeamVO
	 */
	public void updateMapTeam(MapTeamVO mapTeamVO) {
		mapTeamMapper.updateMapTeam(mapTeamVO);
	}

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * 
	 * @param mapTeamVO
	 */
	public void deleteMapTeam(MapTeamVO mapTeamVO) {
		mapTeamMapper.deleteMapTeam(mapTeamVO);
	}

}