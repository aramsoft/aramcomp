package aramframework.com.sym.log.wlg.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 웹 로그관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WebLogVO extends BaseVO {

	// domain
	/** 요청아이디 */
	private String requstId = "";

	/** 발생일자 */
	private String occrrncDe = "";

	/** URL */
	private String url = "";

	/** 요청자아이디 */
	private String rqesterId = "";

	/** 요청아이피 */
	private String rqesterIp = "";

	// helper
	/** 요청자 이름 */
	private String rqesterNm = "";

	/** 검색시작일 */
	private String searchBgnDe = "";

	/** 검색종료일 */
	private String searchEndDe = "";

}
