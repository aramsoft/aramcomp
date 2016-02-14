package aramframework.com.uss.olp.mgt.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 회의관리 Vo Class 구현
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

public class MeetingManageVO extends SearchVO  {

	// domain
	/** 회의ID */
	private String mtgId = "";

	/** 회의명 */
	private String mtgNm = "";

	/** 회의안건내용 */
	private String mtgMtrCn = "";

	/** 회의순번 */
	private String mtgSn = "";

	/** 회의수 */
	private String mtgCo = "";

	/** 회의일자 */
	private String mtgDe = "";

	/** 회의장소 */
	private String mtgPlace = "";

	/** 회의시작시간 */
	private String mtgBeginTime = "";

	/** 회의종료시간 */
	private String mtgEndTime = "";

	/** 비공개회의여부 */
	private String clsdrMtgAt = "";

	/** 열람개시일자 */
	private String readngBeginDe = "";

	/** 열람여부 */
	private String readngAt = "";

	/** 회의결과내용 */
	private String mtgResultCn = "";

	/** 회의결과유무 */
	private String mtgResultEnnc = "";

	/** 기타사항 */
	private String etcMatter = "";

	/** 주관부서ID */
	private String mngtDeptId = "";

	/** 주관자ID */
	private String mnaerId = "";

	/** 주관자부서ID */
	private String mnaerDeptId = "";

	/** 회의여부 */
	private String mtnAt = "";

	/** 불참석자수 */
	private String nonatdrnCo = "";

	/** 참석자수 */
	private String atdrnCo = "";

	/** 대상 아이디 */
	private String trgetId = "";

	// helper
	/** 주관부서명 */
	private String mngtDeptNm = "";

	/** 주관자ID */
	private String mnaerIds = "";

	/** 주관자명 */
	private String mnaerNm = "";

	/** 주관자부서명 */
	private String mnaerDeptNm = "";
	
	/** 주관자직위직급코드 */
	private String mnaerOfcpsClsfCode = "";

	// 추가검색조건
	/** 회의시작 시간 */
	private String mtgBeginHH = "";

	/** 회의시작 분 */
	private String mtgBeginMM = "";

	/** 회의종료 시간 */
	private String mtgEndHH = "";

	/** 회의종료 분 */
	private String mtgEndMM = "";

	// domain
	/**
	 * mtgId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgId() {
		return mtgId;
	}
	/**
	 * mtgId attribute 값을 설정한다.
	 * 
	 * @return mtgId String
	 */
	public void setMtgId(String mtgId) {
		this.mtgId = mtgId;
	}

	/**
	 * mtgNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgNm() {
		return mtgNm;
	}
	/**
	 * mtgNm attribute 값을 설정한다.
	 * 
	 * @return mtgNm String
	 */
	public void setMtgNm(String mtgNm) {
		this.mtgNm = mtgNm;
	}

	/**
	 * mtgMtrCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgMtrCn() {
		return mtgMtrCn;
	}
	/**
	 * mtgMtrCn attribute 값을 설정한다.
	 * 
	 * @return mtgMtrCn String
	 */
	public void setMtgMtrCn(String mtgMtrCn) {
		this.mtgMtrCn = mtgMtrCn;
	}

	/**
	 * mtgSn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgSn() {
		return mtgSn;
	}
	/**
	 * mtgSn attribute 값을 설정한다.
	 * 
	 * @return mtgSn String
	 */
	public void setMtgSn(String mtgSn) {
		this.mtgSn = mtgSn;
	}

	/**
	 * mtgCo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgCo() {
		return mtgCo;
	}
	/**
	 * mtgCo attribute 값을 설정한다.
	 * 
	 * @return mtgCo String
	 */
	public void setMtgCo(String mtgCo) {
		this.mtgCo = mtgCo;
	}

	/**
	 * mtgDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgDe() {
		return mtgDe;
	}
	/**
	 * mtgDe attribute 값을 설정한다.
	 * 
	 * @return mtgDe String
	 */
	public void setMtgDe(String mtgDe) {
		this.mtgDe = mtgDe;
	}

	/**
	 * mtgPlace attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgPlace() {
		return mtgPlace;
	}
	/**
	 * mtgPlace attribute 값을 설정한다.
	 * 
	 * @return mtgPlace String
	 */
	public void setMtgPlace(String mtgPlace) {
		this.mtgPlace = mtgPlace;
	}

	/**
	 * mtgBeginTime attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgBeginTime() {
		return mtgBeginTime;
	}
	/**
	 * mtgBeginTime attribute 값을 설정한다.
	 * 
	 * @return mtgBeginTime String
	 */
	public void setMtgBeginTime(String mtgBeginTime) {
		this.mtgBeginTime = mtgBeginTime;
	}

	/**
	 * mtgEndTime attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgEndTime() {
		return mtgEndTime;
	}
	/**
	 * mtgEndTime attribute 값을 설정한다.
	 * 
	 * @return mtgEndTime String
	 */
	public void setMtgEndTime(String mtgEndTime) {
		this.mtgEndTime = mtgEndTime;
	}

	/**
	 * clsdrMtgAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getClsdrMtgAt() {
		return clsdrMtgAt;
	}
	/**
	 * clsdrMtgAt attribute 값을 설정한다.
	 * 
	 * @return clsdrMtgAt String
	 */
	public void setClsdrMtgAt(String clsdrMtgAt) {
		this.clsdrMtgAt = clsdrMtgAt;
	}

	/**
	 * readngBeginDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getReadngBeginDe() {
		return readngBeginDe;
	}
	/**
	 * readngBeginDe attribute 값을 설정한다.
	 * 
	 * @return readngBeginDe String
	 */
	public void setReadngBeginDe(String readngBeginDe) {
		this.readngBeginDe = readngBeginDe;
	}

	/**
	 * readngAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getReadngAt() {
		return readngAt;
	}
	/**
	 * readngAt attribute 값을 설정한다.
	 * 
	 * @return readngAt String
	 */
	public void setReadngAt(String readngAt) {
		this.readngAt = readngAt;
	}

	/**
	 * mtgResultCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgResultCn() {
		return mtgResultCn;
	}
	/**
	 * mtgResultCn attribute 값을 설정한다.
	 * 
	 * @return mtgResultCn String
	 */
	public void setMtgResultCn(String mtgResultCn) {
		this.mtgResultCn = mtgResultCn;
	}

	/**
	 * mtgResultEnnc attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgResultEnnc() {
		return mtgResultEnnc;
	}
	/**
	 * mtgResultEnnc attribute 값을 설정한다.
	 * 
	 * @return mtgResultEnnc String
	 */
	public void setMtgResultEnnc(String mtgResultEnnc) {
		this.mtgResultEnnc = mtgResultEnnc;
	}

	/**
	 * etcMatter attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEtcMatter() {
		return etcMatter;
	}
	/**
	 * etcMatter attribute 값을 설정한다.
	 * 
	 * @return etcMatter String
	 */
	public void setEtcMatter(String etcMatter) {
		this.etcMatter = etcMatter;
	}

	/**
	 * mngtDeptId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMngtDeptId() {
		return mngtDeptId;
	}
	/**
	 * mngtDeptId attribute 값을 설정한다.
	 * 
	 * @return mngtDeptId String
	 */
	public void setMngtDeptId(String mngtDeptId) {
		this.mngtDeptId = mngtDeptId;
	}

	/**
	 * mnaerId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMnaerId() {
		return mnaerId;
	}
	/**
	 * mnaerId attribute 값을 설정한다.
	 * 
	 * @return mnaerId String
	 */
	public void setMnaerId(String mnaerId) {
		this.mnaerId = mnaerId;
	}

	/**
	 * mnaerDeptId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMnaerDeptId() {
		return mnaerDeptId;
	}
	/**
	 * mnaerDeptId attribute 값을 설정한다.
	 * 
	 * @return mnaerDeptId String
	 */
	public void setMnaerDeptId(String mnaerDeptId) {
		this.mnaerDeptId = mnaerDeptId;
	}

	/**
	 * mtnAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtnAt() {
		return mtnAt;
	}
	/**
	 * mtnAt attribute 값을 설정한다.
	 * 
	 * @return mtnAt String
	 */
	public void setMtnAt(String mtnAt) {
		this.mtnAt = mtnAt;
	}

	/**
	 * nonatdrnCo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getNonatdrnCo() {
		return nonatdrnCo;
	}
	/**
	 * nonatdrnCo attribute 값을 설정한다.
	 * 
	 * @return nonatdrnCo String
	 */
	public void setNonatdrnCo(String nonatdrnCo) {
		this.nonatdrnCo = nonatdrnCo;
	}

	/**
	 * atdrnCo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAtdrnCo() {
		return atdrnCo;
	}
	/**
	 * atdrnCo attribute 값을 설정한다.
	 * 
	 * @return atdrnCo String
	 */
	public void setAtdrnCo(String atdrnCo) {
		this.atdrnCo = atdrnCo;
	}

	/**
	 * trgetId attribute를 리턴한다.
	 * 
	 * @return the trgetId
	 */
	public String getTrgetId() {
		return trgetId;
	}
	/**
	 * trgetId attribute 값을 설정한다.
	 * 
	 * @param trgetId
	 *            the trgetId to set
	 */
	public void setTrgetId(String trgetId) {
		this.trgetId = trgetId;
	}

	// helper
	/**
	 * mngtDeptNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMngtDeptNm() {
		return mngtDeptNm;
	}
	/**
	 * mngtDeptNm attribute 값을 설정한다.
	 * 
	 * @return mngtDeptNm String
	 */
	public void setMngtDeptNm(String mngtDeptNm) {
		this.mngtDeptNm = mngtDeptNm;
	}

	/**
	 * mnaerIds attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMnaerIds() {
		return mnaerIds;
	}
	/**
	 * mnaerIds attribute 값을 설정한다.
	 * 
	 * @return mnaerId String
	 */
	public void setMnaerIds(String mnaerIds) {
		this.mnaerIds = mnaerIds;
	}

	/**
	 * mnaerNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMnaerNm() {
		return mnaerNm;
	}
	/**
	 * mnaerNm attribute 값을 설정한다.
	 * 
	 * @return mnaerNm String
	 */
	public void setMnaerNm(String mnaerNm) {
		this.mnaerNm = mnaerNm;
	}

	/**
	 * mnaerOfcpsClsfCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMnaerOfcpsClsfCode() {
		return mnaerOfcpsClsfCode;
	}
	/**
	 * mnaerOfcpsClsfCode attribute 값을 설정한다.
	 * 
	 * @return mnaerOfcpsClsfCode String
	 */
	public void setMnaerOfcpsClsfCode(String mnaerOfcpsClsfCode) {
		this.mnaerOfcpsClsfCode = mnaerOfcpsClsfCode;
	}

	/**
	 * mnaerDeptNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMnaerDeptNm() {
		return mnaerDeptNm;
	}
	/**
	 * mnaerDeptNm attribute 값을 설정한다.
	 * 
	 * @return mnaerDeptNm String
	 */
	public void setMnaerDeptNm(String mnaerDeptNm) {
		this.mnaerDeptNm = mnaerDeptNm;
	}

	/**
	 * mtgBeginHH attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgBeginHH() {
		return mtgBeginHH;
	}
	/**
	 * mtgBeginHH attribute 값을 설정한다.
	 * 
	 * @return mtgBeginHH String
	 */
	public void setMtgBeginHH(String mtgBeginHH) {
		this.mtgBeginHH = mtgBeginHH;
	}

	/**
	 * mtgBeginMM attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgBeginMM() {
		return mtgBeginMM;
	}
	/**
	 * mtgBeginMM attribute 값을 설정한다.
	 * 
	 * @return mtgBeginMM String
	 */
	public void setMtgBeginMM(String mtgBeginMM) {
		this.mtgBeginMM = mtgBeginMM;
	}

	/**
	 * mtgEndHH attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgEndHH() {
		return mtgEndHH;
	}
	/**
	 * mtgEndHH attribute 값을 설정한다.
	 * 
	 * @return mtgEndHH String
	 */
	public void setMtgEndHH(String mtgEndHH) {
		this.mtgEndHH = mtgEndHH;
	}

	/**
	 * mtgEndMM attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMtgEndMM() {
		return mtgEndMM;
	}
	/**
	 * mtgEndMM attribute 값을 설정한다.
	 * 
	 * @return mtgEndMM String
	 */
	public void setMtgEndMM(String mtgEndMM) {
		this.mtgEndMM = mtgEndMM;
	}

}
