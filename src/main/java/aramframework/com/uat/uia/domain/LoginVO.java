package aramframework.com.uat.uia.domain;

import java.io.Serializable;

import lombok.Data;

 
/**
 * 공통 Login 객체 모델 VO
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class LoginVO implements Serializable{

	private static final long serialVersionUID = 8845407526781637386L;

	/** 아이디 */
	private String userId;

	/** 이름 */
	private String name;

	/** 주민등록번호 */
	private String ihidNum;

	/** 이메일주소 */
	private String email;

	/** 비밀번호 */
	private String password;

	/** 비밀번호 힌트 */
	private String passwordHint;

	/** 비밀번호 정답 */
	private String passwordCnsr;

	/** 사용자구분 */
	private String userSe;

	/** 조직(부서)ID */
	private String orgnztId;

	/** 조직(부서)명 */
	private String orgnztNm;

	/** 로그인 후 이동할 페이지 */
	private String url;

	/** 사용자 IP정보 */
	private String ip;

	/** GPKI인증 DN */
	private String dn;

	/** mbl tel no */
	private String mblTelNo;

}
