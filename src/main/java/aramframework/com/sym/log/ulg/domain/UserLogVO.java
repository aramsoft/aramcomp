package aramframework.com.sym.log.ulg.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 사용자 로그관리를 위한 VO 클래스
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

public class UserLogVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 검색시작일 */
	private String searchBgnDe = "";
	
	/** 검색종료일 */
	private String searchEndDe = "";


	/** 발생일자 */
	private String occrrncDe = "";
	
	/** 요청자아이디 */
	private String rqesterId = "";
	
	/** 요청자 이름 */
	private String rqesterNm = "";
	
	/** 메서드명 */
	private String methodNm = "";
	
	/** 서비스명 */
	private String srvcNm = "";
	
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

	public String getRqesterNm() {
		return rqesterNm;
	}
	public void setRqesterNm(String rqesterNm) {
		this.rqesterNm = rqesterNm;
	}

	public String getMethodNm() {
		return methodNm;
	}
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}
	
	public String getSrvcNm() {
		return srvcNm;
	}
	public void setSrvcNm(String srvcNm) {
		this.srvcNm = srvcNm;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
