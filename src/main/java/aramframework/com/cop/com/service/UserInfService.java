package aramframework.com.cop.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.service.CommunityManageService;
import aramframework.com.cop.com.dao.UserInfMapper;
import aramframework.com.cop.com.domain.UserInfVO;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 협업에서 사용할 사용자 조회 서비스 기능 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class UserInfService extends EgovAbstractServiceImpl {

	@Autowired 
	private UserInfMapper userInfMapper;	

	@Autowired 
	private CommunityManageService cmmntyService; // 커뮤니티 관리자 권한 확인

	/**
	 * 커뮤니티  및 동호회 사용자 권한을 확인한다.
	 * 
	 */
	public String checkCommunityUser(String defaultYN) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null) {
			return "N";
		}

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			return "Y";
		}
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		String trgetId = (String) requestAttributes.getAttribute("curTrgetId", RequestAttributes.SCOPE_REQUEST);
		
		if (trgetId.startsWith("CMMNTY_")) {
			CommunityUserVO communityUserVO = new CommunityUserVO();

			communityUserVO.setCmmntyId(trgetId);
			communityUserVO.setEmplyrId(loginVO.getUniqId());

			if (cmmntyService.checkCommunityUserInf(communityUserVO).equals("EXIST")) {
				return "Y";
			} else {
				return "N";
			}
		}
		return defaultYN;
	}

	/**
	 * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
	 * 
	 */
	public String checkCommunityManager() {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null) {
			return "N";
		}

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			return "Y";
		}
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		String trgetId = (String) requestAttributes.getAttribute("curTrgetId", RequestAttributes.SCOPE_REQUEST);
		
		if (trgetId.startsWith("CMMNTY_")) {
			CommunityUserVO communityUserVO = new CommunityUserVO();

			communityUserVO.setCmmntyId(trgetId);
			communityUserVO.setEmplyrId(loginVO.getUniqId());

			if (cmmntyService.isManager(communityUserVO)) {
				return "Y";
			}
		} 			

		return "N";
	}

	/**
	 * 사용자 정보에 대한 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectUserList(UserInfVO userInfVO) {
		return userInfMapper.selectUserList(userInfVO);
	}

	/**
	 * 사용자 정보에 대한 목록 총갯수를 조회한다.
	 * 
	 * @param userInfVO
	 */
	public int selectUserListCnt(UserInfVO userInfVO) {
		return userInfMapper.selectUserListCnt(userInfVO);
	}

	/**
	 * 커뮤니티에 대한 모든 사용자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectAllCmmntyUser(UserInfVO userInfVO) {
		return userInfMapper.selectAllCmmntyUser(userInfVO);
	}
	
	/**
	 * 커뮤니티 사용자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectCmmntyUserList(UserInfVO userInfVO) {
		return userInfMapper.selectCmmntyUserList(userInfVO);
	}

	/**
	 * 커뮤니티 사용자 목록 총갯수을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public int selectCmmntyUserListCnt(UserInfVO userInfVO) {
		return userInfMapper.selectCmmntyUserListCnt(userInfVO);
	}
	
	/**
	 * 커뮤니티 관리자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectCmmntyMngrList(UserInfVO userInfVO) {
		return userInfMapper.selectCmmntyMngrList(userInfVO);
	}

	/**
	 * 커뮤니티 관리자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public int selectCmmntyMngrListCnt(UserInfVO userInfVO) {
		return userInfMapper.selectCmmntyMngrListCnt(userInfVO);
	}

}
