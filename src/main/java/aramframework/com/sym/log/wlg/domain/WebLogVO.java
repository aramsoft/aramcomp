package aramframework.com.sym.log.wlg.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 웹 로그관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
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

	// domain
	public String getRequstId() {
		return requstId;
	}
	public void setRequstId(String requstId) {
		this.requstId = requstId;
	}

	public String getOccrrncDe() {
		return occrrncDe;
	}
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getRqesterId() {
		return rqesterId;
	}
	public void setRqesterId(String rqesterId) {
		this.rqesterId = rqesterId;
	}

	public String getRqesterIp() {
		return rqesterIp;
	}
	public void setRqesterIp(String rqesterIp) {
		this.rqesterIp = rqesterIp;
	}

	// helper
	public String getRqesterNm() {
		return rqesterNm;
	}
	public void setRqesterNm(String rqesterNm) {
		this.rqesterNm = rqesterNm;
	}

	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}
	
	public String getSearchEndDe() {
		return searchEndDe;
	}
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

}
