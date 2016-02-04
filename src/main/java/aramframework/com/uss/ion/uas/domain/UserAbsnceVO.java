package aramframework.com.uss.ion.uas.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요
 * - 사용자부재에 대한 Vo 클래스를 정의한다.
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

public class UserAbsnceVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 부재여부 조회조건 */
	private String selAbsnceAt;

	/** 사용자ID */
	private String userId;
	
	/** 사용자명 */
	private String userNm;
	
	/** 사용자부재여부 */
	private String userAbsnceAt;
	
	/** 등록여부 */
	private String regYn;

	/**
	 * @return the selAbsnceAt
	 */
	public String getSelAbsnceAt() {
		return selAbsnceAt;
	}
	/**
	 * @param selAbsnceAt
	 *            the selAbsnceAt to set
	 */
	public void setSelAbsnceAt(String selAbsnceAt) {
		this.selAbsnceAt = selAbsnceAt;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm
	 *            the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * @return the userAbsnceAt
	 */
	public String getUserAbsnceAt() {
		return userAbsnceAt;
	}
	/**
	 * @param userAbsnceAt
	 *            the userAbsnceAt to set
	 */
	public void setUserAbsnceAt(String userAbsnceAt) {
		this.userAbsnceAt = userAbsnceAt;
	}

	/**
	 * @return the regYn
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * @param regYn
	 *            the regYn to set
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	
}
