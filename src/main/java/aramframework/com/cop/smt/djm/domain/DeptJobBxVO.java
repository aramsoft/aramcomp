package aramframework.com.cop.smt.djm.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 부서업무함에 대한 Vo 클래스를 정의한다.
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

public class DeptJobBxVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 표시순서 변경 조건 */
	private String ordrCnd = "";

	/** 팝업 조건 */
	private String popupCnd = "";

	//  Domain
	
	/** 부서업무함 ID	 */
	private String deptJobBxId;

	/** 부서업무함명 */
	private String deptJobBxNm;

	/** 부서 ID	 */
	private String deptId;

	/** 부서명	 */
	private String deptNm;

	/** 표시순서	 */
	private int indictOrdr;

	public String getOrdrCnd() {
		return ordrCnd;
	}
	public void setOrdrCnd(String ordrCnd) {
		this.ordrCnd = ordrCnd;
	}

	public String getPopupCnd() {
		return popupCnd;
	}
	public void setPopupCnd(String popupCnd) {
		this.popupCnd = popupCnd;
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

	public int getIndictOrdr() {
		return indictOrdr;
	}
	public void setIndictOrdr(int indictOrdr) {
		this.indictOrdr = indictOrdr;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}