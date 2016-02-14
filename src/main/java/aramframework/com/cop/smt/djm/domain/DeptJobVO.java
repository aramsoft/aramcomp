package aramframework.com.cop.smt.djm.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 부서업무에 대한 Vo 클래스를 정의한다.
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

public class DeptJobVO extends SearchVO {

	// domain
	/** 부서업무 ID	 */
	private String deptJobId;

	/** 부서업무함 ID	 */
	private String deptJobBxId;

	/** 부서업무명	 */
	private String deptJobNm;

	/** 부서업무내용	 */
	private String deptJobCn;

	/** 첨부파일 ID */
	private String atchFileId;

	/** 업무담당자 ID	 */
	private String chargerId;

	/** 우선순위	 */
	private String priort;

	// helper
	/** 부서업무함명	 */
	private String deptJobBxNm;

	/** 부서 ID	 */
	private String deptId;

	/** 부서명	 */
	private String deptNm;

	/** 업무담당자명	 */
	private String chargerNm;

	/** 부서ID조회조건 */
	private String searchDeptId = "";

	/** 부서업무함ID조회조건 */
	private String searchDeptJobBxId = "";

	// domain
	public String getDeptJobId() {
		return deptJobId;
	}
	public void setDeptJobId(String deptJobId) {
		this.deptJobId = deptJobId;
	}

	public String getDeptJobBxId() {
		return deptJobBxId;
	}
	public void setDeptJobBxId(String deptJobBxId) {
		this.deptJobBxId = deptJobBxId;
	}

	public String getDeptJobNm() {
		return deptJobNm;
	}
	public void setDeptJobNm(String deptJobNm) {
		this.deptJobNm = deptJobNm;
	}

	public String getDeptJobCn() {
		return deptJobCn;
	}
	public void setDeptJobCn(String deptJobCn) {
		this.deptJobCn = deptJobCn;
	}

	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getChargerId() {
		return chargerId;
	}
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}

	public String getPriort() {
		return priort;
	}
	public void setPriort(String priort) {
		this.priort = priort;
	}

	// helper
	public String getDeptJobBxNm() {
		return deptJobBxNm;
	}
	public void setDeptJobBxNm(String deptJobBxNm) {
		this.deptJobBxNm = deptJobBxNm;
	}

	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getChargerNm() {
		return chargerNm;
	}
	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}

	public String getSearchDeptId() {
		return searchDeptId;
	}
	public void setSearchDeptId(String searchDeptId) {
		this.searchDeptId = searchDeptId;
	}

	public String getSearchDeptJobBxId() {
		return searchDeptJobBxId;
	}
	public void setSearchDeptJobBxId(String searchDeptJobBxId) {
		this.searchDeptJobBxId = searchDeptJobBxId;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}