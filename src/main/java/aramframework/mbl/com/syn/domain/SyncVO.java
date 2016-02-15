package aramframework.mbl.com.syn.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 동기화 서비스에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 동기화 서비스 정보를 조회하기 위해 필요한 정보를 관리한다.
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

public class SyncVO extends BaseVO {

	// domain
	/** 동기화 서비스 일련번호	 */
	private int sn = 0;
	
	/** 동기화 서비스 회원ID	 */
	private String mberId = "";
	
	/** 동기화 서비스 제목	 */
	private String syncSj = "";
	
	/** 동기화 서비스 내용	 */
	private String syncCn = "";
	
	/** 동기화 서비스 동기일시	 */
	private String syncDt = "";
	
	/** 동기화 서비스 생성일시	 */	
	private String creatDt = "";
	
	/** 동기화 서비스 수정일시	 */
	private String updtDt = "";	
	
	// helper
	/** 조회건수	 */
	private int fetchRow = 0;
	
	/** 접근 기기에 따른 조회 페이징 처리 정보	 */
	private String deviceType = "";
	
	// domain
	/**
	 * 동기화 서비스 일련번호를 획득한다.
	 * 
	 * @return sn
	 */
	public int getSn() {
		return sn;
	}
	/**
	 * 동기화 서비스 일련번호를  할당한다.
	 * 
	 * @param sn
	 */
	public void setSn(int sn) {
		this.sn = sn;
	}
	
	/**
	 * 동기화 서비스 회원ID를 획득한다. 
	 * 
	 * @return
	 */
	public String getMberId() {
		return mberId;
	}
	/**
	 * 동기화 서비스 회원ID를 할당한다.  
	 * 
	 * @param mberId
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	
	/**
	 * 동기화 서비스 제목을 획득한다.
	 * 
	 * @return
	 */
	public String getSyncSj() {
		return syncSj;
	}
	/**
	 * 동기화 서비스 제목을 할당한다. 
	 * 
	 * @param syncSj
	 */
	public void setSyncSj(String syncSj) {
		this.syncSj = syncSj;
	}
	
	/**
	 * 동기화 서비스 내용을 획득한다.
	 * 
	 * @return
	 */
	public String getSyncCn() {
		return syncCn;
	}
	/**
	 * 동기화 서비스 내용을 할당한다. 
	 * 
	 * @param syncCn
	 */
	public void setSyncCn(String syncCn) {
		this.syncCn = syncCn;
	}
	
	/**
	 * 동기화 서비스 동기화일시를 획득한다.
	 * 
	 * @return
	 */
	public String getSyncDt() {
		return syncDt;
	}
	/**
	 * 동기화 서비스 동기화일시를 할당한다. 
	 * 
	 * @param syncDt
	 */
	public void setSyncDt(String syncDt) {
		this.syncDt = syncDt;
	}	
	
	/**
	 * 동기화 서비스 등록일시를 획득한다.
	 * 
	 * @return
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * 동기화 서비스 등록일시를 할당한다. 
	 * 
	 * @param creatDt
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}	
	
	/**
	 * 동기화 서비스 수정일시를 획득한다.
	 * 	
	 * @return
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * 동기화 서비스 수정일시를 할당한다. 
	 * 
	 * @param updtDt
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}	

	// helper
	/**
	 * 조회 건수를 가져온다.
	 * @return int 조회 건수
	 */	
	public int getFetchRow() {
		return fetchRow;
	}
	/**
	 * 조회 건수를 저장한다.
	 * 
	 * @param fetchRow 조회건수
	 */
	public void setFetchRow(int fetchRow) {
		this.fetchRow = fetchRow;
	}
	
	/**
	 * 접속 장비타입을 가져온다.
	 * @return String 장비타입
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * 조회 건수를 저장한다.
	 * 
	 * @param deviceType 접속 장비타입
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
