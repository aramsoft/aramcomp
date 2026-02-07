package aramframework.com.cop.adb.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 주소록구성원 관리를 위한 VO 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AdressBookUserVO extends BaseVO {

	// domain
	/** 주소록구성원 아이디 */
	private String adbkUserId = "";

	/** 주소록 아이디 */
	private String adbkId = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 명함 아이디 */
	private String ncrdId = "";

	/** 주소록구성원 이름 */
	private String nm = "";

	/** 이메일 주소 */
	private String emailAdres = "";

	/** 폰 번호 */
	private String moblphonNo = "";

	/** 팩스 번호 */
	private String fxnum = "";

	/** 회사 번호 */
	private String offmTelno = "";

	/** 집 전화번호 */
	private String homeTelno = "";

	// helper
	/** 사용자 아이디 */
	private String userId = "";

	/** 사용자 명 */
	private String userNm = "";

	/** 사용자 이메일 */
	private String userEmail = "";

	/** 사용자 접전화 처음 */
	private String areaNo = "";

	/** 사용자 집전화 가운데 */
	private String homemiddleTelno = "";

	/** 사용자 집전화 마지막 */
	private String homeendTelno = "";

}
