package aramframework.com.cop.ncm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 명함정보 관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class NameCardVO extends BaseVO {

	// domain
	/** 명함아이디 */
	private String ncrdId = "";

	/** 대상 아이디 */
	private String trgetId = "";

	/** 이름 */
	private String ncrdNm = "";

	/** 전화번호 */
	private String telNo = "";

	/** 국가번호 */
	private String nationNo = "";

	/** 지역번호 */
	private String areaNo = "";

	/** 중간전화번호 */
	private String middleTelNo = "";

	/** 끝전화번호 */
	private String endTelNo = "";

	/** 휴대폰번호 */
	private String mbtlNum = "";

	/** 식별번호 */
	private String idntfcNo = "";

	/** 중간휴대폰번호 */
	private String middleMbtlNum = "";

	/** 끝휴대폰번호 */
	private String endMbtlNum = "";

	/** 이메일주소 */
	private String emailAdres = "";

	/** 회사명 */
	private String cmpnyNm = "";

	/** 부서명 */
	private String deptNm = "";

	/** 주소 */
	private String adres = "";

	/** 상세주소 */
	private String detailAdres = "";

	/** 직위명 */
	private String ofcpsNm = "";

	/** 직급명 */
	private String clsfNm = "";

	/** 공개여부 */
	private String othbcScope = "";

	/** 비고 */
	private String remark = "";

	// helper
	/** 템플릿 구분 코드명 */
	private String tmplatSeCodeNm = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

}
