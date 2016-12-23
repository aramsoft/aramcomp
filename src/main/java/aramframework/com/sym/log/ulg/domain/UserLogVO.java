package aramframework.com.sym.log.ulg.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 사용자 로그관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class UserLogVO extends BaseVO {

	// domain
	/** 발생일자 */
	private String occrrncDe = "";
	
	/** 요청자아이디 */
	private String rqesterId = "";
	
	/** 서비스명 */
	private String srvcNm = "";
	
	/** 메서드명 */
	private String methodNm = "";
	
	/** 생성횟수 */
	private String creatCo = "";
	
	/** 수정횟수 */
	private String updtCo = "";
	
	/** 조회횟수 */
	private String rdCnt = "";
	
	/** 삭제횟수 */
	private String deleteCo = "";
	
	/** 출력횟수 */
	private String outptCo = "";
	
	/** 에러횟수 */
	private String errorCo = "";

	// helper	
	/** 요청자 이름 */
	private String rqesterNm = "";
	
	/** 검색시작일 */
	private String searchBgnDe = "";
	
	/** 검색종료일 */
	private String searchEndDe = "";

	// domain
	public String getOccrrncDe() {
		return occrrncDe;
	}
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}

	public String getRqesterId() {
		return rqesterId;
	}
	public void setRqesterId(String rqesterId) {
		this.rqesterId = rqesterId;
	}

	public String getSrvcNm() {
		return srvcNm;
	}
	public void setSrvcNm(String srvcNm) {
		this.srvcNm = srvcNm;
	}

	public String getMethodNm() {
		return methodNm;
	}
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}
	
	public String getCreatCo() {
		return creatCo;
	}
	public void setCreatCo(String creatCo) {
		this.creatCo = creatCo;
	}

	public String getUpdtCo() {
		return updtCo;
	}
	public void setUpdtCo(String updtCo) {
		this.updtCo = updtCo;
	}

	public String getRdCnt() {
		return rdCnt;
	}
	public void setRdCnt(String rdCnt) {
		this.rdCnt = rdCnt;
	}

	public String getDeleteCo() {
		return deleteCo;
	}
	public void setDeleteCo(String deleteCo) {
		this.deleteCo = deleteCo;
	}

	public String getOutptCo() {
		return outptCo;
	}
	public void setOutptCo(String outptCo) {
		this.outptCo = outptCo;
	}

	public String getErrorCo() {
		return errorCo;
	}
	public void setErrorCo(String errorCo) {
		this.errorCo = errorCo;
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
