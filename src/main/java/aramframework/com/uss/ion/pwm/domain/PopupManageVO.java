package aramframework.com.uss.ion.pwm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 팝업창에 대한 model 클래스를 정의한다.
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

public class PopupManageVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 팝업창아이디 */
	private String popupId;
	
	/** 팝업창 타이틀명 */
	private String popupTitleNm;
	
	/** 실파일 URL */
	private String fileUrl;
	
	/** 팝업창이 화면에 보여지는 가로 위치정보 */
	private String popupWlc;

	/** 팝업창이 화면에 보여지는 세로 위치정보 */
	private String popupHlc;

	/** 팝업창의 높이 */
	private String popupHSize;

	/** 팝업창의 넚이 */
	private String popupWSize;

	/** 게시시작일 */
	private String ntceBgnde;
	
	/** 게시종료일 */
	private String ntceEndde;

	/** 게시시작일(시간) */
	private String ntceBgndeHH;

	/** 게시시작일(분) */
	private String ntceBgndeMM;

	/** 게시종료일(시간) */
	private String ntceEnddeHH;

	/** 게시종료일(분) */
	private String ntceEnddeMM;

	/** 그만보기 설정 여부 */
	private String stopVewAt;
	
	/** 게시여부 */
	private String ntceAt;

	/**
	 * popupId 리턴
	 * 
	 * @return the popupId
	 */
	public String getPopupId() {
		return popupId;
	}
	/**
	 * popupId 설정
	 * 
	 * @param popupId
	 *            the popupId to set
	 */
	public void setPopupId(String popupId) {
		this.popupId = popupId;
	}

	/**
	 * popupTitleNm 리턴
	 * 
	 * @return the popupTitleNm
	 */
	public String getPopupTitleNm() {
		return popupTitleNm;
	}
	/**
	 * popupTitleNm 설정
	 * 
	 * @param popupTitleNm
	 *            the popupTitleNm to set
	 */
	public void setPopupTitleNm(String popupTitleNm) {
		this.popupTitleNm = popupTitleNm;
	}

	/**
	 * fileUrl 리턴
	 * 
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}
	/**
	 * fileUrl 설정
	 * 
	 * @param fileUrl
	 *            the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * popupWlc 리턴
	 * 
	 * @return the popupWlc
	 */
	public String getPopupWlc() {
		return popupWlc;
	}
	/**
	 * popupWlc 설정
	 * 
	 * @param popupWlc
	 *            the popupWlc to set
	 */
	public void setPopupWlc(String popupWlc) {
		this.popupWlc = popupWlc;
	}

	/**
	 * popupHlc 리턴
	 * 
	 * @return the popupHlc
	 */
	public String getPopupHlc() {
		return popupHlc;
	}
	/**
	 * popupHlc 설정
	 * 
	 * @param popupHlc
	 *            the popupHlc to set
	 */
	public void setPopupHlc(String popupHlc) {
		this.popupHlc = popupHlc;
	}

	/**
	 * popupHSize 리턴
	 * 
	 * @return the popupHSize
	 */
	public String getPopupHSize() {
		return popupHSize;
	}
	/**
	 * popupHSize 설정
	 * 
	 * @param popupHSize
	 *            the popupHSize to set
	 */
	public void setPopupHSize(String popupHSize) {
		this.popupHSize = popupHSize;
	}

	/**
	 * popupWSize 리턴
	 * 
	 * @return the popupWSize
	 */
	public String getPopupWSize() {
		return popupWSize;
	}
	/**
	 * popupWSize 설정
	 * 
	 * @param popupWSize
	 *            the popupWSize to set
	 */
	public void setPopupWSize(String popupWSize) {
		this.popupWSize = popupWSize;
	}

	/**
	 * ntceBgnde 리턴
	 * 
	 * @return the ntceBgnde
	 */
	public String getNtceBgnde() {
		return ntceBgnde;
	}
	/**
	 * ntceBgnde 설정
	 * 
	 * @param ntceBgnde
	 *            the ntceBgnde to set
	 */
	public void setNtceBgnde(String ntceBgnde) {
		this.ntceBgnde = ntceBgnde;
	}

	/**
	 * ntceEndde 리턴
	 * 
	 * @return the ntceEndde
	 */
	public String getNtceEndde() {
		return ntceEndde;
	}
	/**
	 * ntceEndde 설정
	 * 
	 * @param ntceEndde
	 *            the ntceEndde to set
	 */
	public void setNtceEndde(String ntceEndde) {
		this.ntceEndde = ntceEndde;
	}

	/**
	 * ntceBgndeHH 리턴
	 * 
	 * @return the ntceBgndeHH
	 */
	public String getNtceBgndeHH() {
		return ntceBgndeHH;
	}
	/**
	 * ntceBgndeHH 설정
	 * 
	 * @param ntceBgndeHH
	 *            the ntceBgndeHH to set
	 */
	public void setNtceBgndeHH(String ntceBgndeHH) {
		this.ntceBgndeHH = ntceBgndeHH;
	}

	/**
	 * ntceBgndeMM 리턴
	 * 
	 * @return the ntceBgndeMM
	 */
	public String getNtceBgndeMM() {
		return ntceBgndeMM;
	}
	/**
	 * ntceBgndeMM 설정
	 * 
	 * @param ntceBgndeMM
	 *            the ntceBgndeMM to set
	 */
	public void setNtceBgndeMM(String ntceBgndeMM) {
		this.ntceBgndeMM = ntceBgndeMM;
	}

	/**
	 * ntceEnddeHH 리턴
	 * 
	 * @return the ntceEnddeHH
	 */
	public String getNtceEnddeHH() {
		return ntceEnddeHH;
	}
	/**
	 * ntceEnddeHH 설정
	 * 
	 * @param ntceEnddeHH
	 *            the ntceEnddeHH to set
	 */
	public void setNtceEnddeHH(String ntceEnddeHH) {
		this.ntceEnddeHH = ntceEnddeHH;
	}

	/**
	 * ntceEnddeMM 리턴
	 * 
	 * @return the ntceEnddeMM
	 */
	public String getNtceEnddeMM() {
		return ntceEnddeMM;
	}
	/**
	 * ntceEnddeMM 설정
	 * 
	 * @param ntceEnddeMM
	 *            the ntceEnddeMM to set
	 */
	public void setNtceEnddeMM(String ntceEnddeMM) {
		this.ntceEnddeMM = ntceEnddeMM;
	}

	/**
	 * stopVewAt 리턴
	 * 
	 * @return the stopVewAt
	 */
	public String getStopVewAt() {
		return stopVewAt;
	}
	/**
	 * stopVewAt 설정
	 * 
	 * @param stopVewAt
	 *            the stopVewAt to set
	 */
	public void setStopVewAt(String stopVewAt) {
		this.stopVewAt = stopVewAt;
	}

	/**
	 * ntceAt 리턴
	 * 
	 * @return the ntceAt
	 */
	public String getNtceAt() {
		return ntceAt;
	}
	/**
	 * ntceAt 설정
	 * 
	 * @param ntceAt
	 *            the ntceAt to set
	 */
	public void setNtceAt(String ntceAt) {
		this.ntceAt = ntceAt;
	}

}