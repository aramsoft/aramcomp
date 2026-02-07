package aramframework.com.uss.umt.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 업무사용자VO클래스로서 업무사용자관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EmplyrManageVO extends BaseVO {

	// domain
	/** 사용자 ID */
	private String emplyrId;
	
	/** 조직 ID */
	private String orgnztId;
	
	/** 사용자 명 */
	private String emplyrNm;
	
	/** 비밀번호 */
	private String password;
	
	/** 사원번호 */
	private String emplNo;
	
	/** 주민등록번호 */
	private String ihidnum;
	
	/** 성별코드	*/
	private String sexdstnCode;
	
	/** 생일 */
	private String brth;
	
	/** 팩스번호 */
	private String fxnum;
	
	/** 비밀번호 힌트 */
	private String passwordHint;
	
	/** 비밀번호 정답 */
	private String passwordCnsr;
	
	/** 지역번호 */
	private String areaNo;
	
	/** 집중간전화번호 */
	private String homemiddleTelno;
	
	/** 집끝전화번호 */
	private String homeendTelno;
	
	/** 우편번호 */
	private String zip;
	
	/** 집 주소 */
	private String homeadres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 사무실전화번호 */
	private String offmTelno;
	
	/** 핸드폰번호 */
	private String moblphonNo;
	
	/** 이메일주소 */
	private String emailAdres;
	
	/** 직위명 */
	private String ofcpsNm;
	
	/** 소속기관코드 */
	private String insttCode;
	
	/** 사용자 상태 */
	private String emplyrSttusCode;
	
	/** DN 값 */
	private String subDn;

	/** 가입일 */
	private String sbscrbDe;
	
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

	/** 검색조건 회원타입  */
	private String mberTy;

	/** 검색조건 가입일자 시작일  */
	private String sbscrbDeBegin;

	/** 검색조건 가입일자 종료일  */
	private String sbscrbDeEnd;

}