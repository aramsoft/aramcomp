package aramframework.mbl.com.ows.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 오프라인웹 서비스에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 오프라인웹 서비스 정보를 조회하기 위해 필요한 정보를 관리한다.
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

public class OfflineWebVO extends BaseVO {

	// domain
	/** 오프라인웹 서비스 일련번호	 */
	private int sn = 0;
	
	/** 오프라인웹 서비스 회원ID	 */
	private String mberId = "";
	
	/** 오프라인웹 서비스 제목	 */
	private String offlineWebSj = "";
	
	/** 오프라인웹 서비스 내용	 */
	private String offlineWebCn = "";
		
	/** 오프라인웹 서비스 생성일시 	 */	
	private String creatDt = "";
	
	/** 오프라인웹 서비스 수정일시 	 */
	private String updtDt = "";	
	
	// helper
	/** 조회건수	 */
	private int fetchRow = 0;
	
	/** 접근 기기에 따른 조회 페이징 처리 정보	 */
	private String deviceType = "";
	
	// domain
	/**
	 * 오프라인웹 서비스 일련번호를 획득한다.
	 * 
	 * @return int 일련번호 
	 */
	public int getSn() {
		return sn;
	}
	/**
	 * 오프라인웹 서비스 일련번호를 할당한다.
	 * 
	 * @param sn 일련번호
	 */
	public void setSn(int sn) {
		this.sn = sn;
	}
	
	/**
	 * 오프라인웹 서비스 회원ID를 획득한다.
	 * 
	 * @return int 회원ID 
	 */
	public String getMberId() {
		return mberId;
	}
	/**
	 * 오프라인웹 서비스 회원ID를 할당한다.
	 * 
	 * @param mberId 회원ID 
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}

	/**
	 * 오프라인웹 서비스 글 제목을 획득한다.
	 * 
	 * @return String 글 제목 
	 */
	public String getOfflineWebSj() {
		return offlineWebSj;
	}
	/**
	 * 오프라인웹 서비스 글 제목을 할당한다.
	 * 
	 * @param offlineWebSj 글 제목 
	 */
	public void setOfflineWebSj(String offlineWebSj) {
		this.offlineWebSj = offlineWebSj;
	}
	
	/**
	 * 오프라인웹 서비스 글 내용을 획득한다.
	 * 
	 * @return String 글 내용 
	 */
	public String getOfflineWebCn() {
		return offlineWebCn;
	}
	/**
	 * 오프라인웹 서비스 글 내용을 할당한다.
	 * 
	 * @param offlineWebCn 글 내용 
	 */
	public void setOfflineWebCn(String offlineWebCn) {
		this.offlineWebCn = offlineWebCn;
	}
	
	/**
	 * 오프라인웹 서비스 등록일자를 획득한다.
	 * 
	 * @return String 등록일자 
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * 오프라인웹 서비스 등록일자를 할당한다.
	 * 
	 * @param creatDt 등록일자 
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	
	/**
	 * 오프라인웹 서비스 수정일자를 획득한다.
	 * 
	 * @return String 수정일자 
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * 오프라인웹 서비스 수정일자를 할당한다.
	 * 
	 * @param updtDt 수정일자
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
