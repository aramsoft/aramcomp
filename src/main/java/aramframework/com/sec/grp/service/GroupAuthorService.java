package aramframework.com.sec.grp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sec.grp.dao.GroupAuthorMapper;
import aramframework.com.sec.grp.domain.GroupAuthorVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한그룹에 관한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class GroupAuthorService extends EgovAbstractServiceImpl {

	@Autowired
	private GroupAuthorMapper groupAuthorMapper;

	/**
	 * 그룹별 할당된 권한 목록 조회
	 * 
	 * @param groupAuthorVO
	 */
	public List<EgovMap> selectGroupAuthorList(GroupAuthorVO groupAuthorVO) {
		return groupAuthorMapper.selectGroupAuthorList(groupAuthorVO);
	}

	/**
	 * 목록조회 카운트를 반환한다
	 * 
	 * @param groupAuthorVO
	 */
	public int selectGroupAuthorListCnt(GroupAuthorVO groupAuthorVO) {
		return groupAuthorMapper.selectGroupAuthorListCnt(groupAuthorVO);
	}

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * 
	 * @param groupAuthorVO
	 */
	public void insertGroupAuthor(GroupAuthorVO groupAuthorVO) {
		groupAuthorMapper.insertGroupAuthor(groupAuthorVO);
	}

	/**
	 * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param groupAuthorVO
	 */
	public void updateGroupAuthor(GroupAuthorVO groupAuthorVO) {
		groupAuthorMapper.updateGroupAuthor(groupAuthorVO);
	}

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * 
	 * @param groupAuthorVO
	 */
	public void deleteGroupAuthor(GroupAuthorVO groupAuthorVO) {
		groupAuthorMapper.deleteGroupAuthor(groupAuthorVO);
	}

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * 
	 * @param groupAuthorVO
	 */
	public void insertGroupAuthors(
			String userIds, 
			String authorCodes,
			String regYns, 
			String mberTyCodes) {
		
		String[] strUserIds = userIds.split(";");
		String[] strAuthorCodes = authorCodes.split(";");
		String[] strRegYns = regYns.split(";");
		String[] strMberTyCodes = mberTyCodes.split(";");// 2011.08.04 수정 부분

		GroupAuthorVO gaVO = new GroupAuthorVO();
		for (int i = 0; i < strUserIds.length; i++) {
			gaVO.setUniqId(strUserIds[i]);
			gaVO.setAuthorCode(strAuthorCodes[i]);
			gaVO.setMberTyCode(strMberTyCodes[i]);// 2011.08.04 수정 부분
			if (strRegYns[i].equals("N"))
				groupAuthorMapper.insertGroupAuthor(gaVO);
			else
				groupAuthorMapper.updateGroupAuthor(gaVO);
		}
	}

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * 
	 * @param groupAuthorVO
	 */
	public void deleteGroupAuthors(String userIds) {
		String[] strUserIds = userIds.split(";");
		GroupAuthorVO agVO = new GroupAuthorVO();
		for (int i = 0; i < strUserIds.length; i++) {
			agVO.setUniqId(strUserIds[i]);
			groupAuthorMapper.deleteGroupAuthor(agVO);
		}
	}

}