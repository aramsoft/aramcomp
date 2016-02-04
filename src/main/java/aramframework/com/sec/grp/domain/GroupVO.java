package aramframework.com.sec.grp.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 그룹관리에 대한 model 클래스를 정의한다.
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

public class GroupVO extends SearchVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** 그룹 ID */
	private String groupId;
	
	/** 그룹명 */
	private String groupNm;
	
	/** 그룹등록일시 */
	private String groupCreatDe;
	
	/** 그룹설명  */
	private String groupDc;

	/**
	 * groupId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * groupId attribute 값을 설정한다.
	 * 
	 * @param groupId
	 *            String
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * groupNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getGroupNm() {
		return groupNm;
	}
	/**
	 * groupNm attribute 값을 설정한다.
	 * 
	 * @param groupNm
	 *            String
	 */
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}

	/**
	 * groupCreatDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getGroupCreatDe() {
		return groupCreatDe;
	}
	/**
	 * groupCreatDe attribute 값을 설정한다.
	 * 
	 * @param groupCreatDe
	 *            String
	 */
	public void setGroupCreatDe(String groupCreatDe) {
		this.groupCreatDe = groupCreatDe;
	}

	/**
	 * groupDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getGroupDc() {
		return groupDc;
	}
	/**
	 * groupDc attribute 값을 설정한다.
	 * 
	 * @param groupDc
	 *            String
	 */
	public void setGroupDc(String groupDc) {
		this.groupDc = groupDc;
	}

}