package aramframework.com.uat.uia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.ems.domain.SndngMailVO;
import aramframework.com.cop.ems.service.SndngMailService;
import aramframework.com.uat.uia.dao.LoginMapper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.NumberUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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

	/** EgovSndngMailService */
	@Autowired
	private SndngMailService sndngMailService;

	/**
	 * 2011.08.26 EsntlId를 이용한 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	public LoginVO actionLoginByEsntlId(LoginVO vo) {

		LoginVO loginVO = loginMapper.actionLoginByEsntlId(vo);

		// 3. 결과를 리턴한다.
		if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
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
		if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
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
		if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
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
		if (loginVO != null && !loginVO.getId().equals("")) {
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
	public boolean searchPassword(LoginVO vo) {

		boolean result = true;

		// 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
		LoginVO loginVO = loginMapper.searchPassword(vo);
		if (loginVO == null || loginVO.getPassword() == null || loginVO.getPassword().equals("")) {
			return false;
		}

		// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫+영+영+숫=9자리)
		String newpassword = "";
		for (int i = 1; i <= 9; i++) {
			// 영자
			if (i % 3 != 0) {
				newpassword += StringUtil.getRandomStr('a', 'z');
				// 숫자
			} else {
				newpassword += NumberUtil.getRandomNum(0, 9);
			}
		}

		// 3. 임시 비밀번호를 암호화하여 DB에 저장한다.
		LoginVO pwVO = new LoginVO();
		String enpassword;
		try {
			enpassword = FileScrty.encryptPassword(newpassword);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pwVO.setId(vo.getId());
		pwVO.setPassword(enpassword);
		pwVO.setUserSe(vo.getUserSe());
		loginMapper.updatePassword(pwVO);

		// 4. 임시 비밀번호를 이메일 발송한다.(메일연동솔루션 활용)
		SndngMailVO sndngMailVO = new SndngMailVO();
		sndngMailVO.setDsptchPerson("admin");
		sndngMailVO.setRecptnPerson(vo.getEmail());
		sndngMailVO.setSj("[ARAM] 임시 비밀번호를 발송했습니다.");
		sndngMailVO.setEmailCn("고객님의 임시 비밀번호는 " + newpassword + " 입니다.");
		sndngMailVO.setAtchFileId("");

//		result = sndngMailService.insertSndngMail(sndngMailVO);
		result = sndngMailService.sndngMail(sndngMailVO);

		return result;
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
