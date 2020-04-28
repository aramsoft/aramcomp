package aramframework.com.cop.adb.domain;

import java.util.ArrayList;
import java.util.List;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 주소록관리를 위한 VO 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class AdressBookVO extends BaseVO {

	// domain
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

	// helper
	/** 사용자 아이디(for list) */
	private String userIds = "";

	/** 주소록구성원 */
	private List<AdressBookUserVO> adbkUserList = new ArrayList<AdressBookUserVO>();

	// domain
	/**
	 * adbkId attribute를 리턴한다.
	 * 
	 * @return 	the adbkId
	 */
	public String getAdbkId() {
		return adbkId;
	}
	/**
	 * adbkId attribute 값을 설정한다.
	 * 
	 * @param 	adbkId	the adbkId to set
	 */
	public void setAdbkId(String adbkId) {
		this.adbkId = adbkId;
	}

	/**
	 * adbkNm attribute를 리턴한다.
	 * 
	 * @return 	the adbkNm
	 */
	public String getAdbkNm() {
		return adbkNm;
	}
	/**
	 * adbkNm attribute 값을 설정한다.
	 * 
	 * @param 	adbkNm	the adbkNm to set
	 */
	public void setAdbkNm(String adbkNm) {
		this.adbkNm = adbkNm;
	}

	/**
	 * othbcScope attribute를 리턴한다.
	 * 
	 * @return 	the othbcScope
	 */
	public String getOthbcScope() {
		return othbcScope;
	}
	/**
	 * othbcScope attribute 값을 설정한다.
	 * 
	 * @param 	othbcScope	the othbcScope to set
	 */
	public void setOthbcScope(String othbcScope) {
		this.othbcScope = othbcScope;
	}

	/**
	 * useAt attribute를 리턴한다.
	 * 
	 * @return 	the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param 	useAt	the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * wrterId attribute를 리턴한다.
	 * 
	 * @return 	the wrterId
	 */
	public String getWrterId() {
		return wrterId;
	}
	/**
	 * wrterId attribute 값을 설정한다.
	 * 
	 * @param 	wrterId	the wrterId to set
	 */
	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	/**
	 * trgetOrgnztId attribute를 리턴한다.
	 * 
	 * @return 	the trgetOrgnztId
	 */
	public String getTrgetOrgnztId() {
		return trgetOrgnztId;
	}
	/**
	 * trgetOrgnztId attribute 값을 설정한다.
	 * 
	 * @param 	trgetOrgnztId	the trgetOrgnztId to set
	 */
	public void setTrgetOrgnztId(String trgetOrgnztId) {
		this.trgetOrgnztId = trgetOrgnztId;
	}

	// helper
	/**
	 * userIds attribute를 리턴한다.
	 * 
	 * @return 	the userIds
	 */
	public String getUserIds() {
		return userIds;
	}
	/**
	 * userIds attribute 값을 설정한다.
	 * 
	 * @param 	userIds	the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	/**
	 * adbkUserList attribute를 리턴한다.
	 * 
	 * @return 	the adbkUserList
	 */
	public List<AdressBookUserVO> getAdbkUserList() {
		return adbkUserList;
	}
	/**
	 * adbkUserList attribute 값을 설정한다.
	 * 
	 * @param 	adbkUserList	the adbkUserList to set
	 */
	public void setAdbkUserList(List<AdressBookUserVO> adbkUserList) {
		this.adbkUserList = adbkUserList;
	}

}
