package aramframework.com.uss.umt.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 일반회원VO클래스로서 일반회원관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MberManageVO extends BaseVO {

	// domain
	/** 회원 ID */
	private String mberId;
	
	/** 비밀번호 */
	private String password;
	
	/** 비밀번호 힌트 */
	private String passwordHint;
	
	/** 비밀번호 정답 */
	private String passwordCnsr;
	
	/** 주민등록번호 */
	private String ihidnum;
	
	/** 회원명 */
	private String mberNm;
	
	/** 우편번호 */
	private String zip;
	
	/** 주소 */
	private String adres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 지역번호 */
	private String areaNo;
	
	/** 중간전화번호 */
	private String middleTelno;
	
	/** 끝전화번호 */
	private String endTelno;
	
	/** 핸드폰번호 */
	private String moblphonNo;
	
	/** 회원상태 */
	private String mberSttus;
	
	/** 팩스번호 */
	private String mberFxnum;
	
	/** 이메일주소 */
	private String mberEmailAdres;

	/** 가입 일자 */
	private String sbscrbDe;
	
	/** 성별코드 */
	private String sexdstnCode;
	
	/** 구글 계정 */
	private String googleAccount;

	// helper
	/** 사용자 유형 */
	private String userTy;
	
	// 추가 검색 조건
	/** 이전비밀번호 - 비밀번호 변경시 사용 */
	private String oldPassword = "";

	/** 검색조건-회원상태 (0, A, D, P) */
	private String sbscrbSttus = "0";

}