package aramframework.com.sec.grp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sec.grp.dao.GroupMapper;
import aramframework.com.sec.grp.domain.GroupVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 그룹관리에 관한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class GroupService extends EgovAbstractServiceImpl {

	@Autowired
	private GroupMapper groupMapper;
	
	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService groupIdGnrService; 
	
	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * 
	 * @param groupVO
	 */
	public List<EgovMap> selectGroupList(GroupVO groupVO) {
		return groupMapper.selectGroupList(groupVO);
	}

	/**
	 * 목록조회 카운트를 반환한다
	 * 
	 * @param groupVO
	 */
	public int selectGroupListCnt(GroupVO groupVO) {
		return groupMapper.selectGroupListCnt(groupVO);
	}

	/**
	 * 검색조건에 따른 그룹정보를 조회
	 * 
	 * @param groupVO
	 */
	public GroupVO selectGroup(GroupVO groupVO) {
		return groupMapper.selectGroup(groupVO);
	}

	/**
	 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param groupVO
	 */
	public void insertGroup(GroupVO groupVO) {
		try {
			groupVO.setGroupId(groupIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		groupMapper.insertGroup(groupVO);
	}

	/**
	 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param groupVO
	 */
	public void updateGroup(GroupVO groupVO) {
		groupMapper.updateGroup(groupVO);
	}

	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param groupVO
	 */
	public void deleteGroup(GroupVO groupVO) {
		groupMapper.deleteGroup(groupVO);
	}

	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param groupVO
	 */
	public void deleteGroups(String groupIds) {
		String[] strGroupIds = groupIds.split(";");
		GroupVO groupVO = new GroupVO(); 
		for (int i = 0; i < strGroupIds.length; i++) {
			groupVO.setGroupId(strGroupIds[i]);
			groupMapper.deleteGroup(groupVO);
		}
	}

}