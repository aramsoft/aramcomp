package aramframework.com.cop.smt.dsm.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 일지관리 VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class DiaryManageVO extends BaseVO  {

	// domain
	/** 일지ID */
	private String diaryId;

	/** 일정ID */
	private String schdulId;

	/** 진척율 */
	private String diaryProcsPte;

	/** 일정명 */
	private String diaryNm;

	/** 지지사항 */
	private String drctMatter;

	/** 특이사항 */
	private String partclrMatter;

	/** 첨부파일 */
	private String atchFileId;

	// helper
	/** 일정명 */
	private String schdulNm;

	// domain
	/**
	 * diaryId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getDiaryId() {
		return diaryId;
	}
	/**
	 * diaryId attribute 값을 설정한다.
	 * 
	 * @return diaryId String
	 */
	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	/**
	 * schdulId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulId() {
		return schdulId;
	}
	/**
	 * schdulId attribute 값을 설정한다.
	 * 
	 * @return schdulId String
	 */
	public void setSchdulId(String schdulId) {
		this.schdulId = schdulId;
	}

	/**
	 * diaryProcsPte attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getDiaryProcsPte() {
		return diaryProcsPte;
	}
	/**
	 * diaryProcsPte attribute 값을 설정한다.
	 * 
	 * @return diaryProcsPte String
	 */
	public void setDiaryProcsPte(String diaryProcsPte) {
		this.diaryProcsPte = diaryProcsPte;
	}

	/**
	 * diaryNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getDiaryNm() {
		return diaryNm;
	}
	/**
	 * diaryNm attribute 값을 설정한다.
	 * 
	 * @return diaryNm String
	 */
	public void setDiaryNm(String diaryNm) {
		this.diaryNm = diaryNm;
	}

	/**
	 * drctMatter attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getDrctMatter() {
		return drctMatter;
	}
	/**
	 * drctMatter attribute 값을 설정한다.
	 * 
	 * @return drctMatter String
	 */
	public void setDrctMatter(String drctMatter) {
		this.drctMatter = drctMatter;
	}

	/**
	 * partclrMatter attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getPartclrMatter() {
		return partclrMatter;
	}
	/**
	 * partclrMatter attribute 값을 설정한다.
	 * 
	 * @return partclrMatter String
	 */
	public void setPartclrMatter(String partclrMatter) {
		this.partclrMatter = partclrMatter;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * 
	 * @return atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	// helper
	/**
	 * schdulNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulNm() {
		return schdulNm;
	}
	/**
	 * schdulNm attribute 값을 설정한다.
	 * 
	 * @return schdulCn String
	 */
	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

}
