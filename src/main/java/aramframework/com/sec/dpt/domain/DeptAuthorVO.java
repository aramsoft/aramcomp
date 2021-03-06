package aramframework.com.sec.dpt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 부서권한에 대한 model 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class DeptAuthorVO extends BaseVO {

	/** 부서코드 */
	private String deptCode;
	
	/** 부서 명 */
	private String deptNm;
	
	/** 설정대상 사용자 ID */
	private String userId;
	
	/** 설정대상 사용자 명 */
	private String userNm;
	
	/** 설정대상 사용자 유형 코드 */
//	private String mberTyCode;

	/** 설정대상 사용자 유형 명 */
//	private String mberTyNm;

	/** 권한코드 */
	private String authorCode;
	
	/** 등록 여부	 */
	private String regYn;
	
	/**
	 * deptCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * deptCode attribute 값을 설정한다.
	 * 
	 * @param deptCode
	 *            String
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * deptNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getDeptNm() {
		return deptNm;
	}
	/**
	 * deptNm attribute 값을 설정한다.
	 * 
	 * @param deptNm
	 *            String
	 */
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	/**
	 * userId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * 
	 * @param userId
	 *            String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * 
	 * @param userNm
	 *            String
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * mberTyCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
//	public String getMberTyCode() {
//		return mberTyCode;
//	}
	/**
	 * mberTyCode attribute 값을 설정한다.
	 * 
	 * @param mberTyCode
	 *            String
	 */
//	public void setMberTyCode(String mberTyCode) {
//		this.mberTyCode = mberTyCode;
//	}

	/**
	 * mberTyNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
//	public String getMberTyNm() {
//		return mberTyNm;
//	}
	/**
	 * mberTyNm attribute 값을 설정한다.
	 * 
	 * @param mberTyNm
	 *            String
	 */
//	public void setMberTyNm(String mberTyNm) {
//		this.mberTyNm = mberTyNm;
//	}

	/**
	 * authorCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorCode() {
		return authorCode;
	}
	/**
	 * authorCode attribute 값을 설정한다.
	 * 
	 * @param authorCode
	 *            String
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	/**
	 * regYn attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * regYn attribute 값을 설정한다.
	 * 
	 * @param regYn
	 *            String
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}

}