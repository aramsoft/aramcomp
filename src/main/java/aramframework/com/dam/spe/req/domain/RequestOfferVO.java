package aramframework.com.dam.spe.req.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 지식정보제공/지식정보요청 Model and VO Class 구현
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

public class RequestOfferVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 지식ID */
	private String knoId;

	/** 조직ID */
	private String orgnztId;

	/** 소속조직명 */
	private String orgnztNm;

	/** 전문가ID */
	private String speId;

	/** 지식유형코드 */
	private String knoTypeCd;
	
	/** 지식유형명 */
	private String knoTypeNm;

	/** 사용자ID */
	private String emplyrId;

	/** 지식명 */
	private String knoNm;

	/** 지식내용 */
	private String knoCn;

	/** 첨부파일ID */
	private String atchFileId;

	/** 부모지식ID */
	private String parentsKnoId = "";

	/** 답변깊이 */
	private int threadDepth = 0;

	/** 답변순서 */
	private int threadNo = 0;

	/** 답변그룹번호 */
	private int threadGroupNo = 0;

	/**
	 * @return the knoId
	 */
	public String getKnoId() {
		return knoId;
	}
	/**
	 * @param knoId
	 *            the knoId to set
	 */
	public void setKnoId(String knoId) {
		this.knoId = knoId;
	}

	/**
	 * @return the orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * @param orgnztId
	 *            the orgnztId to set
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	
	/**
	 * @return the speId
	 */
	public String getSpeId() {
		return speId;
	}
	/**
	 * @param speId
	 *            the speId to set
	 */
	public void setSpeId(String speId) {
		this.speId = speId;
	}

	/**
	 * @return the knoTypeCd
	 */
	public String getKnoTypeCd() {
		return knoTypeCd;
	}
	/**
	 * @param knoTypeCd
	 *            the knoTypeCd to set
	 */
	public void setKnoTypeCd(String knoTypeCd) {
		this.knoTypeCd = knoTypeCd;
	}

	/**
	 * @return the knoTypeNm
	 */
	public String getKnoTypeNm() {
		return knoTypeNm;
	}
	/**
	 * @param knoTypeNm
	 *            the knoTypeNm to set
	 */
	public void setKnoTypeNm(String knoTypeNm) {
		this.knoTypeNm = knoTypeNm;
	}

	/**
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * @param emplyrId
	 *            the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	/**
	 * @return the knoNm
	 */
	public String getKnoNm() {
		return knoNm;
	}
	/**
	 * @param knoNm
	 *            the knoNm to set
	 */
	public void setKnoNm(String knoNm) {
		this.knoNm = knoNm;
	}

	/**
	 * @return the knoCn
	 */
	public String getKnoCn() {
		return knoCn;
	}
	/**
	 * @param knoCn
	 *            the knoCn to set
	 */
	public void setKnoCn(String knoCn) {
		this.knoCn = knoCn;
	}

	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param atchFileId
	 *            the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * @return the parentsKnoId
	 */
	public String getParentsKnoId() {
		return parentsKnoId;
	}
	/**
	 * @param parentsKnoId
	 *            the parentsKnoId to set
	 */
	public void setParentsKnoId(String parentsKnoId) {
		this.parentsKnoId = parentsKnoId;
	}

	/**
	 * @return the threadDepth
	 */
	public int getThreadDepth() {
		return threadDepth;
	}
	/**
	 * @param threadDepth
	 *            the threadDepth to set
	 */
	public void setThreadDepth(int threadDepth) {
		this.threadDepth = threadDepth;
	}

	/**
	 * @return the threadNo
	 */
	public int getThreadNo() {
		return threadNo;
	}
	/**
	 * @param threadNo
	 *            the threadNo to set
	 */
	public void setThreadNo(int threadNo) {
		this.threadNo = threadNo;
	}

	/**
	 * @return the threadGroupNo
	 */
	public int getThreadGroupNo() {
		return threadGroupNo;
	}
	/**
	 * @param threadGroupNo
	 *            the threadGroupNo to set
	 */
	public void setThreadGroupNo(int threadGroupNo) {
		this.threadGroupNo = threadGroupNo;
	}

}
