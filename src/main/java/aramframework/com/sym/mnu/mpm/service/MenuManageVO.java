package aramframework.com.sym.mnu.mpm.service;

import aramframework.com.cmm.SearchVO;

/**
 * 메뉴목록관리 처리를 위한 VO 클래스르를 정의한다
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

public class MenuManageVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 메뉴정보 */
	/** 메뉴번호 */
	private int menuNo;

	/** 메뉴순서 */
	private int menuOrdr;

	/** 메뉴명 */
	private String menuNm;

	/** 상위메뉴번호 */
	private int upperMenuId;

	/** 메뉴설명 */
	private String menuDc;

	/** 관련이미지경로 */
	private String relateImagePath;

	/** 관련이미지명 */
	private String relateImageNm;

	/** 프로그램파일명 */
	private String progrmFileNm;

	/** 사이트맵 */
	/** 생성자ID **/
	private String creatPersonId;

	/** 권한정보설정 */
	/** 권한코드 */
	private String authorCode;

	/** Login 메뉴관련 VO변수 */
	/** tmp_UniqId */
	private String tmpUniqId;

	/** tmp_Cmd */
	private String tmpCmd;

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
	 * menuOrdr attribute를 리턴한다.
	 * 
	 * @return int
	 */
	public int getMenuOrdr() {
		return menuOrdr;
	}
	/**
	 * menuOrdr attribute 값을 설정한다.
	 * 
	 * @param menuOrdr
	 *            int
	 */
	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}

	/**
	 * menuNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * menuNm attribute 값을 설정한다.
	 * 
	 * @param menuNm
	 *            String
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	/**
	 * upperMenuId attribute를 리턴한다.
	 * 
	 * @return int
	 */
	public int getUpperMenuId() {
		return upperMenuId;
	}
	/**
	 * upperMenuId attribute 값을 설정한다.
	 * 
	 * @param upperMenuId
	 *            int
	 */
	public void setUpperMenuId(int upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	/**
	 * menuDc attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuDc() {
		return menuDc;
	}
	/**
	 * menuDc attribute 값을 설정한다.
	 * 
	 * @param menuDc
	 *            String
	 */
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}

	/**
	 * relateImagePath attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRelateImagePath() {
		return relateImagePath;
	}
	/**
	 * relateImagePath attribute 값을 설정한다.
	 * 
	 * @param relateImagePath
	 *            String
	 */
	public void setRelateImagePath(String relateImagePath) {
		this.relateImagePath = relateImagePath;
	}

	/**
	 * relateImageNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRelateImageNm() {
		return relateImageNm;
	}
	/**
	 * relateImageNm attribute 값을 설정한다.
	 * 
	 * @param relateImageNm
	 *            String
	 */
	public void setRelateImageNm(String relateImageNm) {
		this.relateImageNm = relateImageNm;
	}

	/**
	 * progrmFileNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	/**
	 * progrmFileNm attribute 값을 설정한다.
	 * 
	 * @param progrmFileNm
	 *            String
	 */
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
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
	 * tmp_UniqId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpUniqId() {
		return tmpUniqId;
	}
	/**
	 * tmp_UniqId attribute 값을 설정한다.
	 * 
	 * @param tmp_UniqId
	 *            String
	 */
	public void setTmpUniqId(String tmpUniqId) {
		this.tmpUniqId = tmpUniqId;
	}

	/**
	 * tmp_Cmd attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpCmd() {
		return tmpCmd;
	}
	/**
	 * tmp_Cmd attribute 값을 설정한다.
	 * 
	 * @param tmp_Cmd
	 *            String
	 */
	public void setTmpCmd(String tmp_Cmd) {
		this.tmpCmd = tmp_Cmd;
	}

}