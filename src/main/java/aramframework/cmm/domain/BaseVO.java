package aramframework.cmm.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 조회 base VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseVO extends SearchVO {

	// 공통 변수 
	// DB 공통 field
	/** 최초등록자 아이디 */
	private String frstRegisterId = "";

	/** 최초 등록자명 */
	private String frstRegisterNm = "";

	/** 최초등록시점 */
	private Date frstRegisterPnttm = null;

	/** 최종수정자 아이디 */
	private String lastUpdusrId = "";

	/** 최종 수정자명 */
	private String lastUpdusrNm = "";

	/** 최종수정시점 */
	private Date lastUpdusrPnttm = null;

}
