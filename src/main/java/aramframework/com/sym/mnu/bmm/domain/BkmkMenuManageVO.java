package aramframework.com.sym.mnu.bmm.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 바로가기메뉴관리를 위한 VO 모델 클래스
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

public class BkmkMenuManageVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 메뉴 아이디 */
	private String menuId = "";

	/** 메뉴명 */
	private String menuNm = "";

	/** 메뉴 URL */
	private String progrmStrePath = "";

	/** 등록자 아이디 */
	private String userId = "";

	/** 메뉴 설명 */
	private String menuDc = "";

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
