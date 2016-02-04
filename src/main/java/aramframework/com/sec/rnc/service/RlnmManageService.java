package aramframework.com.sec.rnc.service;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 실명인증관리에 관한비지니스클래스를 정의한다.
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
public class RlnmManageService extends EgovAbstractServiceImpl {

	/**
	 * 실명인증확인화면 호출(주민번호)
	 * 
	 * @param ihidnum
	 *            주민등록번호
	 * @param realname
	 *            실명
	 * @param sbscrbTy
	 *            사용자 유형
	 * @return result 결과메시지코드
	 * @throws Exception
	 */
	public String rlnmCnfirm(String ihidnum, String realname, String sbscrbTy) {
		String result = "ERR";

		EgovSocketClient lm_oSocketClient = new EgovSocketClient();
		try {
			/*
			 * 서비스구분코드|인증서CN|이름|주민번호
			 * 
			 * 인증서 파일 에서 숫자에 해당하는 값:예) SVR1310000001_***.*** 1310000001이 인증서CN 값 이다
			 */
			String str = "99|1311000011|" + realname + "|" + ihidnum;
			// String str = "99|1311000011|홍길동|6303201063614";
			byte[] lm_bResult1 = lm_oSocketClient.execute(str);
			result = new String(lm_bResult1);
		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		}

		return result;
	}

	/**
	 * 실명인증확인화면 호출(GPIN)
	 * 
	 * @param pinId
	 *            공공아이핀 아이디
	 * @param pinPassword
	 *            공공아이핀 패스워드
	 * @param sbscrbTy
	 *            사용자 유형
	 * @return result 결과메시지코드
	 * @throws Exception
	 */
	public String rlnmPinCnfirm(String pinId, String pinPassword, String sbscrbTy) {
		String result = "ERR";

		try {
			/*
			 * 공공아이핀 센터를 통해 pinId, pinPassword를 검증한다.
			 */
			result = "result message";
		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		}

		return result;
	}

}