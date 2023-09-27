package aramframework.com.uat.uia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uat.uia.dao.LoginMapper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.NumberUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sim.service.FileScrty;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class LoginService extends EgovAbstractServiceImpl {

	@Autowired
	private LoginMapper loginMapper;	

	/**
	 * 2011.08.26 EsntlId를 이용한 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	public LoginVO actionLoginByEsntlId(LoginVO vo) {

		LoginVO loginVO = loginMapper.actionLoginByEsntlId(vo);

		// 3. 결과를 리턴한다.
		if (loginVO != null && !loginVO.getUserId().equals("") && !loginVO.getPassword().equals("")) {
			return loginVO;
		} else {
			loginVO = new LoginVO();
		}

		return loginVO;
	}

	/**
	 * 일반 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	public LoginVO actionLogin(LoginVO vo) {

		// 1. 입력한 비밀번호를 암호화한다.
		String enpassword;
		try {
			enpassword = FileScrty.encryptPassword(vo.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		vo.setPassword(enpassword);

		// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
		LoginVO loginVO = loginMapper.actionLogin(vo);

		// 3. 결과를 리턴한다.
		if (loginVO != null && !loginVO.getUserId().equals("") && !loginVO.getPassword().equals("")) {
			return loginVO;
		} else {
			loginVO = new LoginVO();
		}

		return loginVO;
	}

	/**
	 * 인증서 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	public LoginVO actionCrtfctLogin(LoginVO vo) {

		// 1. DN값으로 ID, PW를 조회한다.
		LoginVO loginVO = loginMapper.actionCrtfctLogin(vo);

		// 3. 결과를 리턴한다.
		if (loginVO != null && !loginVO.getUserId().equals("") && !loginVO.getPassword().equals("")) {
			return loginVO;
		} else {
			loginVO = new LoginVO();
		}

		return loginVO;
	}

	/**
	 * 아이디를 찾는다.
	 * 
	 * @param loginVO
	 */
	public LoginVO searchId(LoginVO vo) {

		// 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
		LoginVO loginVO = loginMapper.searchId(vo);

		// 2. 결과를 리턴한다.
		if (loginVO != null && !loginVO.getUserId().equals("")) {
			return loginVO;
		} else {
			loginVO = new LoginVO();
		}

		return loginVO;
	}

	/**
	 * 비밀번호를 찾는다.
	 * 
	 * @param loginVO
	 */
	public String searchPassword(LoginVO vo) {

		String newpasswd = "";

		// 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
		LoginVO loginVO = loginMapper.searchPassword(vo);
		if (loginVO == null || loginVO.getPassword() == null || loginVO.getPassword().equals("")) {
			return newpasswd;
		}

		// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫+영+영+숫=9자리)
		for (int i = 1; i <= 9; i++) {
			// 영자
			if (i % 3 != 0) {
				newpasswd += StringUtil.getRandomStr('a', 'z');
				// 숫자
			} else {
				newpasswd += NumberUtil.getRandomNum(0, 9);
			}
		}

		// 3. 임시 비밀번호를 암호화하여 DB에 저장한다.
		LoginVO pwVO = new LoginVO();
		String enpassword;
		try {
			enpassword = FileScrty.encryptPassword(newpasswd);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pwVO.setUserId(vo.getUserId());
		pwVO.setPassword(enpassword);
		pwVO.setUserSe(vo.getUserSe());
		loginMapper.updatePassword(pwVO);

		return newpasswd;
	}
	
	/**
	 * 핸드폰주인을  찾는다.
	 * 
	 * @param mblTelNo
	 */
	public int searchMblTelNo(String mblTelNo) {
		return loginMapper.searchMblTelNo(mblTelNo);
	}
	
}
