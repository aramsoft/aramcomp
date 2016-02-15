package aramframework.com.sym.mnu.mcm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 메뉴생성 처리를 위한 VO 클래스르를 정의한다
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

public class MenuCreateVO extends BaseVO {

	// domain
	/** 메뉴번호 */
	private int menuNo;

	/** 권한코드 */
	private String authorCode;

	/** 맵생성ID */
	private String mapCreatId;

	// helper
	/** 권한명 */
	private String authorNm;

	/** 권한설명 */
	private String authorDc;

	/** 권한생성일자 */
	private String authorCreatDe;

	/** 생성자ID **/
	private String creatPersonId;

	// domain
	/**
	 * menuNo attribute를 리턴한다.
	 * 
	 * @return int
	 */
	public int getMenuNo() {
		return menuNo;
	}
	/**
	 * menuNo attribute 값을 설정한다.
	 * 
	 * @param menuNo
	 *            int
	 */
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	/**
	 * authorCode attribute를 리턴한다.
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
	 * mapCreatId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMapCreatId() {
		return mapCreatId;
	}
	/**
	 * mapCreatId attribute 값을 설정한다.
	 * 
	 * @param mapCreatId
	 *            String
	 */
	public void setMapCreatId(String mapCreatId) {
		this.mapCreatId = mapCreatId;
	}

	// helper
	/**
	 * authorNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorNm() {
		return authorNm;
	}
	/**
	 * authorNm attribute 값을 설정한다.
	 * 
	 * @param authorNm
	 *            String
	 */
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	/**
	 * authorDc attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorDc() {
		return authorDc;
	}
	/**
	 * authorDc attribute 값을 설정한다.
	 * 
	 * @param authorDc
	 *            String
	 */
	public void setAuthorDc(String authorDc) {
		this.authorDc = authorDc;
	}

	/**
	 * authorCreatDe attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorCreatDe() {
		return authorCreatDe;
	}
	/**
	 * authorCreatDe attribute 값을 설정한다.
	 * 
	 * @param authorCreatDe
	 *            String
	 */
	public void setAuthorCreatDe(String authorCreatDe) {
		this.authorCreatDe = authorCreatDe;
	}

	/**
	 * creatPersonId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCreatPersonId() {
		return creatPersonId;
	}
	/**
	 * creatPersonId attribute 값을 설정한다.
	 * 
	 * @param creatPersonId
	 *            String
	 */
	public void setCreatPersonId(String creatPersonId) {
		this.creatPersonId = creatPersonId;
	}

}