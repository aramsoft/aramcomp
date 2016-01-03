package aramframework.com.cop.adb.service;

import java.util.ArrayList;
import java.util.List;

import aramframework.com.cmm.SearchVO;

/**
 * 주소록관리를 위한 VO 모델 클래스
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

public class AdressBookVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 주소록구성원 */
	private List<AdressBookUserVO> adbkMan = new ArrayList<AdressBookUserVO>();

	/** 사용자 아이디 */
	private String userIds = "";

	/** 주소록 아이디 */
	private String adbkId = "";

	/** 주소록 명 */
	private String adbkNm = "";

	/** 주소록 공개범위 */
	private String othbcScope = "";

	/** 최초등록자 부서 */
	private String trgetOrgnztId = "";

	/** 주소록 사용여부 */
	private String useAt = "";

	/** 주소록 등록자 아이디 */
	private String wrterId = "";

	/**
	 * adbkMan attribute를 리턴한다.
	 * 
	 * @return the adbkMan
	 */
	public List<AdressBookUserVO> getAdbkMan() {
		return adbkMan;
	}
	/**
	 * adbkMan attribute 값을 설정한다.
	 * 
	 * @param adbkMan
	 *            the adbkMan to set
	 */
	public void setAdbkMan(List<AdressBookUserVO> adbkMan) {
		this.adbkMan = adbkMan;
	}

	/**
	 * userIds attribute를 리턴한다.
	 * 
	 * @return the userIds
	 */
	public String getUserIds() {
		return userIds;
	}
	/**
	 * userIds attribute 값을 설정한다.
	 * 
	 * @param userIds
	 *            the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	/**
	 * AdbkId attribute를 리턴한다.
	 * 
	 * @return the AdbkId
	 */
	public String getAdbkId() {
		return adbkId;
	}
	/**
	 * AdbkId attribute 값을 설정한다.
	 * 
	 * @param AdbkId
	 *            the AdbkId to set
	 */
	public void setAdbkId(String adbkId) {
		this.adbkId = adbkId;
	}

	/**
	 * adbkNm attribute를 리턴한다.
	 * 
	 * @return the adbkNm
	 */
	public String getAdbkNm() {
		return adbkNm;
	}
	/**
	 * AdbkNm attribute 값을 설정한다.
	 * 
	 * @param AdbkNm
	 *            the AdbkNm to set
	 */
	public void setAdbkNm(String adbkNm) {
		this.adbkNm = adbkNm;
	}

	/**
	 * othbcScope attribute를 리턴한다.
	 * 
	 * @return the othbcScope
	 */
	public String getOthbcScope() {
		return othbcScope;
	}
	/**
	 * othbcScope attribute 값을 설정한다.
	 * 
	 * @param othbcScope
	 *            the othbcScope to set
	 */
	public void setOthbcScope(String othbcScope) {
		this.othbcScope = othbcScope;
	}

	/**
	 * trgetOrgnztId attribute를 리턴한다.
	 * 
	 * @return the trgetOrgnztId
	 */
	public String getTrgetOrgnztId() {
		return trgetOrgnztId;
	}
	/**
	 * trgetOrgnztId attribute 값을 설정한다.
	 * 
	 * @param trgetOrgnztId
	 *            the trgetOrgnztId to set
	 */
	public void setTrgetOrgnztId(String trgetOrgnztId) {
		this.trgetOrgnztId = trgetOrgnztId;
	}

	/**
	 * useAt attribute를 리턴한다.
	 * 
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * wrterId attribute를 리턴한다.
	 * 
	 * @return the wrterId
	 */
	public String getWrterId() {
		return wrterId;
	}
	/**
	 * wrterId attribute 값을 설정한다.
	 * 
	 * @param wrterId
	 *            the wrterId to set
	 */
	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

}
