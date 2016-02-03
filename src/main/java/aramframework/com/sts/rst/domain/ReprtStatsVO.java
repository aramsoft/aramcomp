package aramframework.com.sts.rst.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 개요
 * - 보고서통계에 대한 Vo 클래스를 정의한다.
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

public class ReprtStatsVO extends SearchVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 보고서유형 */
	private String pmReprtTy;
	
	/** 기간구분 */
	private String pmDateTy;
	
	/** 보고서통계 시작일자 */
	private String pmFromDate;
	
	/** 보고서통계 종료일자 */
	private String pmToDate;
	
	/** 보고서통계 그래프 사이즈 단위 */
	float maxUnit = 50.0f;

	/** 보고서ID */
	private String reprtId;
	
	/** 보고서명 */
	private String reprtNm;
	
	/** 보고서유형 */
	private String reprtTy;
	
	/** 보고서유형명 */
	private String reprtTyNm;
	
	/** 보고서현황 */
	private String reprtSttus;
	
	/** 보고서현황명 */
	private String reprtSttusNm;
	
	/** 보고서 카운트 */
	private String cnt;
	
	/** 사용자 ID */
	private String userId;
	
	/** 등록일자 */
	private String regDate;
	
	/** 그래프용 등록일자 */
	private String grpRegDate;
	
	/** 그래프용 등록일자 카운트 */
	private String grpCnt;
	
	/** 그래프용 보고서유형 */
	private String grpReprtTy;
	
	/** 그래프용 보고서유형명 */
	private String grpReprtTyNm;
	
	/** 그래프용 보고서유형 카운트 */
	private String grpReprtTyCnt;
	
	/** 그래프용 진행상태 */
	private String grpReprtSttus;
	
	/** 그래프용 진행상태명 */
	private String grpReprtSttusNm;
	
	/** 그래프용 진행상태 카운트 */
	private String grpReprtSttusCnt;

	/**
	 * @return the pmReprtTy
	 */
	public String getPmReprtTy() {
		return pmReprtTy;
	}
	/**
	 * @param pmReprtTy
	 *            the pmReprtTy to set
	 */
	public void setPmReprtTy(String pmReprtTy) {
		this.pmReprtTy = pmReprtTy;
	}

	/**
	 * @return the pmDateTy
	 */
	public String getPmDateTy() {
		return pmDateTy;
	}
	/**
	 * @param pmDateTy
	 *            the pmDateTy to set
	 */
	public void setPmDateTy(String pmDateTy) {
		this.pmDateTy = pmDateTy;
	}

	/**
	 * @return the pmFromDate
	 */
	public String getPmFromDate() {
		return pmFromDate;
	}
	/**
	 * @param pmFromDate
	 *            the pmFromDate to set
	 */
	public void setPmFromDate(String pmFromDate) {
		this.pmFromDate = pmFromDate;
	}

	/**
	 * @return the pmToDate
	 */
	public String getPmToDate() {
		return pmToDate;
	}
	/**
	 * @param pmToDate
	 *            the pmToDate to set
	 */
	public void setPmToDate(String pmToDate) {
		this.pmToDate = pmToDate;
	}

	/**
	 * @return the maxUnit
	 */
	public float getMaxUnit() {
		return maxUnit;
	}
	/**
	 * @param maxUnit
	 *            the maxUnit to set
	 */
	public void setMaxUnit(float maxUnit) {
		this.maxUnit = maxUnit;
	}

	/**
	 * @return the reprtId
	 */
	public String getReprtId() {
		return reprtId;
	}
	/**
	 * @param reprtId
	 *            the reprtId to set
	 */
	public void setReprtId(String reprtId) {
		this.reprtId = reprtId;
	}

	/**
	 * @return the reprtNm
	 */
	public String getReprtNm() {
		return reprtNm;
	}
	/**
	 * @param reprtNm
	 *            the reprtNm to set
	 */
	public void setReprtNm(String reprtNm) {
		this.reprtNm = reprtNm;
	}

	/**
	 * @return the reprtTy
	 */
	public String getReprtTy() {
		return reprtTy;
	}
	/**
	 * @param reprtTy
	 *            the reprtTy to set
	 */
	public void setReprtTy(String reprtTy) {
		this.reprtTy = reprtTy;
	}

	/**
	 * @return the reprtTyNm
	 */
	public String getReprtTyNm() {
		return reprtTyNm;
	}
	/**
	 * @param reprtTyNm
	 *            the reprtTyNm to set
	 */
	public void setReprtTyNm(String reprtTyNm) {
		this.reprtTyNm = reprtTyNm;
	}

	/**
	 * @return the reprtSttus
	 */
	public String getReprtSttus() {
		return reprtSttus;
	}
	/**
	 * @param reprtSttus
	 *            the reprtSttus to set
	 */
	public void setReprtSttus(String reprtSttus) {
		this.reprtSttus = reprtSttus;
	}

	/**
	 * @return the reprtSttusNm
	 */
	public String getReprtSttusNm() {
		return reprtSttusNm;
	}
	/**
	 * @param reprtSttusNm
	 *            the reprtSttusNm to set
	 */
	public void setReprtSttusNm(String reprtSttusNm) {
		this.reprtSttusNm = reprtSttusNm;
	}

	/**
	 * @return the cnt
	 */
	public String getCnt() {
		return cnt;
	}
	/**
	 * @param cnt
	 *            the cnt to set
	 */
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the grpRegDate
	 */
	public String getGrpRegDate() {
		return grpRegDate;
	}
	/**
	 * @param grpRegDate
	 *            the grpRegDate to set
	 */
	public void setGrpRegDate(String grpRegDate) {
		this.grpRegDate = grpRegDate;
	}

	/**
	 * @return the grpCnt
	 */
	public String getGrpCnt() {
		return grpCnt;
	}
	/**
	 * @param grpCnt
	 *            the grpCnt to set
	 */
	public void setGrpCnt(String grpCnt) {
		this.grpCnt = grpCnt;
	}

	/**
	 * @return the grpReprtTy
	 */
	public String getGrpReprtTy() {
		return grpReprtTy;
	}
	/**
	 * @param grpReprtTy
	 *            the grpReprtTy to set
	 */
	public void setGrpReprtTy(String grpReprtTy) {
		this.grpReprtTy = grpReprtTy;
	}

	/**
	 * @return the grpReprtTyNm
	 */
	public String getGrpReprtTyNm() {
		return grpReprtTyNm;
	}
	/**
	 * @param grpReprtTyNm
	 *            the grpReprtTyNm to set
	 */
	public void setGrpReprtTyNm(String grpReprtTyNm) {
		this.grpReprtTyNm = grpReprtTyNm;
	}

	/**
	 * @return the grpReprtTyCnt
	 */
	public String getGrpReprtTyCnt() {
		return grpReprtTyCnt;
	}
	/**
	 * @param grpReprtTyCnt
	 *            the grpReprtTyCnt to set
	 */
	public void setGrpReprtTyCnt(String grpReprtTyCnt) {
		this.grpReprtTyCnt = grpReprtTyCnt;
	}

	/**
	 * @return the grpReprtSttus
	 */
	public String getGrpReprtSttus() {
		return grpReprtSttus;
	}
	/**
	 * @param grpReprtSttus
	 *            the grpReprtSttus to set
	 */
	public void setGrpReprtSttus(String grpReprtSttus) {
		this.grpReprtSttus = grpReprtSttus;
	}

	/**
	 * @return the grpReprtSttusNm
	 */
	public String getGrpReprtSttusNm() {
		return grpReprtSttusNm;
	}
	/**
	 * @param grpReprtSttusNm
	 *            the grpReprtSttusNm to set
	 */
	public void setGrpReprtSttusNm(String grpReprtSttusNm) {
		this.grpReprtSttusNm = grpReprtSttusNm;
	}

	/**
	 * @return the grpReprtSttusCnt
	 */
	public String getGrpReprtSttusCnt() {
		return grpReprtSttusCnt;
	}
	/**
	 * @param grpReprtSttusCnt
	 *            the grpReprtSttusCnt to set
	 */
	public void setGrpReprtSttusCnt(String grpReprtSttusCnt) {
		this.grpReprtSttusCnt = grpReprtSttusCnt;
	}
	
}
