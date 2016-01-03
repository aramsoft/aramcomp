package aramframework.com.cop.smt.djm.service;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

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

	private static final long serialVersionUID = 1L;

	/** 부서ID조회조건 */
	private String searchDeptId = "";

	/** 부서업무함ID조회조건 */
	private String searchDeptJobBxId = "";

	// Domain
	
	/** 부서업무함 ID	 */
	private String deptJobBxId;

	/** 부서업무함명	 */
	private String deptJobBxNm;

	/** 부서 ID	 */
	private String deptId;

	/** 부서명	 */
	private String deptNm;

	/** 부서업무 ID	 */
	private String deptJobId;

	/** 부서업무명	 */
	private String deptJobNm;

	/** 부서업무내용	 */
	private String deptJobCn;

	/** 업무담당자 ID	 */
	private String chargerId;

	/** 업무담당자명	 */
	private String chargerNm;

	/** 우선순위	 */
	private String priort;

	/** 첨부파일 ID */
	private String atchFileId;

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

	public String getDeptJobBxId() {
		return deptJobBxId;
	}
	public void setDeptJobBxId(String deptJobBxId) {
		this.deptJobBxId = deptJobBxId;
	}

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

	public String getDeptJobId() {
		return deptJobId;
	}
	public void setDeptJobId(String deptJobId) {
		this.deptJobId = deptJobId;
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

	public String getChargerId() {
		return chargerId;
	}
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}

	public String getChargerNm() {
		return chargerNm;
	}
	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}

	public String getPriort() {
		return priort;
	}
	public void setPriort(String priort) {
		this.priort = priort;
	}

	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}