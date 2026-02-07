package aramframework.com.uss.umt.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 기업회원VO클래스로서 기업회원관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EntrprsManageVO extends BaseVO {

	// domain
	/** 기업 회원 ID */
	private String entrprsmberId;
	
	/** 기업구분코드 */
	private String entrprsSeCode;
	
	/** 기업 회원 상태 */
	private String entrprsMberSttus;
	
	/** 기업 회원 비밀번호 */
	private String password;
	
	/** 기업 회원 비밀번호 힌트 */
	private String passwordHint;
	
	/** 기업 회원 비밀번호 정답 */
	private String passwordCnsr;
	
	/** 사업자번호 */
	private String bizrno;
	
	/** 법인등록번호 */
	private String jurirno;
	
	/** 회사명 */
	private String cmpnyNm;
	
	/** 대표이사 */
	private String cxfc;
	
	/** 우편번호 */
	private String zip;
	
	/** 주소 */
	private String adres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 지역번호 */
	private String areaNo;
	
	/** 회사중간전화번호 */
	private String entrprsMiddleTelno;
	
	/** 회사끝전화번호 */
	private String entrprsEndTelno;
	
	/** 팩스번호 */
	private String fxnum;
	
	/** 업종코드 */
	private String indutyCode;
	
	/** 신청자 명 */
	private String applcntNm;
	
	/** 신청자 주민등록번호*/
	private String applcntIhidnum;
	
	/** 가입 일자 */
	private String sbscrbDe;
	
	/** 신청자 이메일주소 */
	private String applcntEmailAdres;

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