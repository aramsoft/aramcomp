package aramframework.com.cop.adb.domain;

import java.util.ArrayList;
import java.util.List;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 주소록관리를 위한 VO 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AdressBookVO extends BaseVO {

	// domain
	/** 주소록 아이디 */
	private String adbkId = "";

	/** 주소록 명 */
	private String adbkNm = "";

	/** 주소록 공개범위 */
	private String othbcScope = "";

	/** 최초등록자 부서 */
	private String trgetOrgnztId = "";

	/** 주소록 사용여부 */
	private String useAt = "";

	/** 주소록 등록자 아이디 */
	private String wrterId = "";

	// helper
	/** 사용자 아이디(for list) */
	private String userIds = "";

	/** 주소록구성원 */
	private List<AdressBookUserVO> adbkUserList = new ArrayList<AdressBookUserVO>();

}
