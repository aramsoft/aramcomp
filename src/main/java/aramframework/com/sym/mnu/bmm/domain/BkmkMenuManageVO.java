package aramframework.com.sym.mnu.bmm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 바로가기메뉴관리를 위한 VO 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class BkmkMenuManageVO extends BaseVO {

	// domain
	/** 메뉴 아이디 */
	private String menuId = "";

	/** 등록자 아이디 */
	private String userId = "";

	/** 메뉴명 */
	private String menuNm = "";

	/** 메뉴 URL */
	private String progrmStrePath = "";

	// helper
	/** 메뉴 설명 */
	private String menuDc = "";

	// domain
	/**
	 * menuId attribute를 리턴한다.
	 * 
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * menuId attribute 값을 설정한다.
	 * 
	 * @param menuId
	 *            the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * userId attribute를 리턴한다.
	 * 
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * menuNm attribute를 리턴한다.
	 * 
	 * @return the menuNm
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * menuNm attribute 값을 설정한다.
	 * 
	 * @param menuNm
	 *            the menuNm to set
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	/**
	 * progrmStrePath attribute를 리턴한다.
	 * 
	 * @return the progrmStrePath
	 */
	public String getProgrmStrePath() {
		return progrmStrePath;
	}
	/**
	 * progrmStrePath attribute 값을 설정한다.
	 * 
	 * @param progrmStrePath
	 *            the progrmStrePath to set
	 */
	public void setProgrmStrePath(String progrmStrePath) {
		this.progrmStrePath = progrmStrePath;
	}

	// helper
	/**
	 * menuDc attribute를 리턴한다.
	 * 
	 * @return the menuDc
	 */
	public String getMenuDc() {
		return menuDc;
	}
	/**
	 * menuDc attribute 값을 설정한다.
	 * 
	 * @param menuDc
	 *            the menuDc to set
	 */
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}
	
}
