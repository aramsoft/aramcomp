package aramframework.com.uss.ion.uas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.uas.dao.UserAbsnceMapper;
import aramframework.com.uss.ion.uas.domain.UserAbsnceVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 사용자부재에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class UserAbsnceService extends EgovAbstractServiceImpl {

	@Autowired
	private UserAbsnceMapper userAbsnceMapper;	

	/**
	 * 사용자부재정보를 관리하기 위해 등록된 사용자부재 목록을 조회한다.
	 * 
	 * @param userAbsnceVO
	 */
	public List<EgovMap> selectUserAbsnceList(UserAbsnceVO userAbsnceVO) {
		return userAbsnceMapper.selectUserAbsnceList(userAbsnceVO);
	}

	/**
	 * 사용자부재정보목록 총 갯수를 조회한다.
	 * 
	 * @param userAbsnceVO
	 */
	public int selectUserAbsnceListCnt(UserAbsnceVO userAbsnceVO) {
		return userAbsnceMapper.selectUserAbsnceListCnt(userAbsnceVO);
	}

	/**
	 * 등록된 사용자부재 상세정보를 조회한다.
	 * 
	 * @param userAbsnceVO
	 */
	public UserAbsnceVO selectUserAbsnce(UserAbsnceVO userAbsnceVO) {
		UserAbsnceVO resultVo = userAbsnceMapper.selectUserAbsnce(userAbsnceVO);
		// searchVO 이전 
		resultVo.setSearchVO(userAbsnceVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * 
	 * @param userAbsnceVO
	 */
	public void insertUserAbsnce(UserAbsnceVO userAbsnceVO) {
		userAbsnceMapper.insertUserAbsnce(userAbsnceVO);
	}

	/**
	 * 기 등록된 사용자부재정보를 수정한다.
	 * 
	 * @param userAbsnceVO
	 */
	public void updateUserAbsnce(UserAbsnceVO userAbsnceVO) {
		userAbsnceMapper.updateUserAbsnce(userAbsnceVO);
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * 
	 * @param userAbsnceVO
	 */
	public void deleteUserAbsnce(UserAbsnceVO userAbsnceVO) {
		userAbsnceMapper.deleteUserAbsnce(userAbsnceVO);
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * 
	 * @param userAbsnceVO
	 */
	public void deleteUserAbsnces(String userIds) {
		String[] strUserIds = userIds.split(";");

		UserAbsnceVO uaVO = new UserAbsnceVO();
		for (int i = 0; i < strUserIds.length; i++) {
			uaVO.setUserId(strUserIds[i]);
			userAbsnceMapper.deleteUserAbsnce(uaVO);
		}
	}

	/**
	 * 사용자부재정보가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param userAbsnceVO
	 */
	public UserAbsnceVO selectUserAbsnceResult(UserAbsnceVO userAbsnceVO) {
		return null;
	}
	
}
