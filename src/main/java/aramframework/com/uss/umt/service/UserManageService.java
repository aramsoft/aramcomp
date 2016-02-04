package aramframework.com.uss.umt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.umt.domain.UserManageVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 사용자관리에 관한 비지니스 클래스를 정의한다.
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

@Service
public class UserManageService extends EgovAbstractServiceImpl {

	@Autowired
	private UserManageMapper userManageMapper;	

	@Autowired
	private MberManageMapper mberManageMapper;	

	@Autowired
	private EntrprsManageMapper entrprsManageMapper;	

	/** egovUsrCnfrmIdGnrService */
	@Autowired
	private EgovIdGnrService usrCnfrmIdGnrService;

	/**
	 * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param userManageVO
	 */
	public List<UserManageVO> selectUserList(UserManageVO userManageVO) {
		return  userManageMapper.selectUserList(userManageVO);
	}

	/**
	 * 기 등록된 특정 사용자목록의 전체수를 확인
	 * 
	 * @param userManageVO
	 */
	public int selectUserListCnt(UserManageVO userManageVO) {
		return userManageMapper.selectUserListCnt(userManageVO);
	}

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param userManageVO
	 */
	public UserManageVO selectUser(UserManageVO userManageVO) {
		UserManageVO resultVo = userManageMapper.selectUser(userManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, userManageVO); 
		return resultVo;
	}

	/**
	 * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param userManageVO
	 */
	public void insertUser(UserManageVO userManageVO) {
		// 고유아이디 셋팅
		try {
			userManageVO.setUniqId(usrCnfrmIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(userManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		userManageVO.setPassword(pass);
		userManageMapper.insertUser(userManageVO);
	}

	/**
	 * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param userManageVO
	 */
	public void updateUser(UserManageVO userManageVO) {
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(userManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		userManageVO.setPassword(pass);
		userManageMapper.updateUser(userManageVO);
	}

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedIdForDel
	 */
	public void deleteUser(String checkedIdForDel) {
		String[] delId = checkedIdForDel.split(",");
		for (int i = 0; i < delId.length; i++) {
			String[] id = delId[i].split(":");
			if (id[0].equals("USR03")) {
				// 업무사용자(직원)삭제
				userManageMapper.deleteUserHistory(id[1]);
				userManageMapper.deleteUser(id[1]);
			} else if (id[0].equals("USR01")) {
				// 일반회원삭제
				mberManageMapper.deleteMber(id[1]);
			} else if (id[0].equals("USR02")) {
				// 기업회원삭제
				entrprsManageMapper.deleteEntrprsMber(id[1]);
			}
		}
	}

	/**
	 * 사용자정보 수정시 히스토리 정보를 추가
	 * 
	 * @param userManageVO
	 */
	public void insertUserHistory(UserManageVO userManageVO) {
		userManageMapper.insertUserHistory(userManageVO);
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * 
	 * @param checkId
	 */
	public int checkIdDplct(String checkId) {
		return userManageMapper.checkIdDplct(checkId);
	}

	/**
	 * 사용자가 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param uniqId
	 */
	public UserManageVO selectPassword(String uniqId) {
		return userManageMapper.selectPassword(uniqId);
	}

	/**
	 * 업무사용자 암호 수정
	 * 
	 * @param userManageVO
	 */
	public void updatePassword(UserManageVO userManageVO) {
		userManageMapper.updatePassword(userManageVO);
	}

}