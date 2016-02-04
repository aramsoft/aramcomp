package aramframework.com.sym.log.tlg.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 송수신 로그관리를 위한 VO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

public class TrsmrcvLogVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 검색시작일 */
	private String searchBgnDe = "";
	
	/** 검색종료일 */
	private String searchEndDe = "";


	/** 요청아이디 */
	private String requstId = "";

	/** 발생일자 */
	private String occrrncDe = "";

	/** 송수신구분코드 */
	private String trsmrcvSeCode = "";

	/** 송수신구분코드 명 */
	private String trsmrcvSeCodeNm = "";

	/** 연계ID */
	private String cntcId;

	/** 제공기관ID */
	private String provdInsttId;

	/** 제공시스템ID */
	private String provdSysId;

	/** 제공서비스ID */
	private String provdSvcId;

	/** 요청기관ID */
	private String requstInsttId;

	/** 요청시스템ID */
	private String requstSysId;

	/** 요청송신시각 */
	private String requstTrnsmitTm;

	/** 요청수신시각 */
	private String requstRecptnTm;

	/** 응답송신시각 */
	private String rspnsTrnsmitTm;

	/** 응답수신시각 */
	private String rspnsRecptnTm;

	/** 결과코드 */
	private String resultCode;

	/** 결과메시지 */
	private String resultMessage;

	/** 요청자아이디 */
	private String rqesterId = "";

	/** 요청자 이름 */
	private String rqesterNm = "";

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

	public String getTrsmrcvSeCode() {
		return trsmrcvSeCode;
	}
	public void setTrsmrcvSeCode(String trsmrcvSeCode) {
		this.trsmrcvSeCode = trsmrcvSeCode;
	}

	public String getTrsmrcvSeCodeNm() {
		return trsmrcvSeCodeNm;
	}
	public void setTrsmrcvSeCodeNm(String trsmrcvSeCodeNm) {
		this.trsmrcvSeCodeNm = trsmrcvSeCodeNm;
	}

	public String getcntcId() {
		return cntcId;
	}
	public void setcntcId(String cntcId) {
		this.cntcId = cntcId;
	}

	public String getProvdInsttId() {
		return provdInsttId;
	}
	public void setProvdInsttId(String provdInsttId) {
		this.provdInsttId = provdInsttId;
	}
	
	public String getProvdSysId() {
		return provdSysId;
	}
	public void setProvdSysId(String provdSysId) {
		this.provdSysId = provdSysId;
	}
	
	public String getProvdSvcId() {
		return provdSvcId;
	}
	public void setProvdSvcId(String provdSvcId) {
		this.provdSvcId = provdSvcId;
	}

	public String getRequstInsttId() {
		return requstInsttId;
	}
	public void setRequstInsttId(String requstInsttId) {
		this.requstInsttId = requstInsttId;
	}

	public String getRequstSysId() {
		return requstSysId;
	}
	public void setRequstSysId(String requstSysId) {
		this.requstSysId = requstSysId;
	}
	
	public String getRequstTrnsmitTm() {
		return requstTrnsmitTm;
	}
	public void setRequstTrnsmitTm(String requstTrnsmitTm) {
		this.requstTrnsmitTm = requstTrnsmitTm;
	}
	
	public String getRequstRecptnTm() {
		return requstRecptnTm;
	}
	public void setRequstRecptnTm(String requstRecptnTm) {
		this.requstRecptnTm = requstRecptnTm;
	}

	public String getRspnsTrnsmitTm() {
		return rspnsTrnsmitTm;
	}
	public void setRspnsTrnsmitTm(String rspnsTrnsmitTm) {
		this.rspnsTrnsmitTm = rspnsTrnsmitTm;
	}

	public String getRspnsRecptnTm() {
		return rspnsRecptnTm;
	}
	public void setRspnsRecptnTm(String rspnsRecptnTm) {
		this.rspnsRecptnTm = rspnsRecptnTm;
	}

	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getRqesterId() {
		return rqesterId;
	}
	public void setRqesterId(String rqesterId) {
		this.rqesterId = rqesterId;
	}

	public String getRqesterNm() {
		return rqesterNm;
	}
	public void setRqesterNm(String rqesterNm) {
		this.rqesterNm = rqesterNm;
	}

}
