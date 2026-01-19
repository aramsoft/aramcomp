package aramframework.com.cop.bbs.domain;

import aramframework.com.cmm.com.domain.BaseVO;
import aramframework.com.cmm.util.WebUtil;

/**
 * 게시물 관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BoardVO extends BaseVO {

	// domain
	/** 게시물 아이디   */
	private long nttId = 0L;

	/** 게시판 아이디	 */
	private String bbsId = "";

	/** 게시물 쓰레드 번호  */
	private int threadNo = 0;

	/** 게시물 제목  */
	private String nttSj = "";

	/** 게시물 내용  */
	private String nttCn = "";

	/** 답장여부  */
	private String answerAt = "";

	/** 부모글번호  */
	private long parntsNttId = 0L;

	/** 답장위치 */
	private int threadDepth = 0;

	/** 정렬순서   */
	private long threadGroupNo = 0L;

	/** 조회수  */
	private int rdcnt = 0;

	/** 사용여부 	 */
	private String useAt = "";

	/** 게시시작일  */
	private String ntceBgnde = "";

	/** 게시종료일  */
	private String ntceEndde = "";

	/** 게시자 아이디	 */
	private String ntcrId = "";

	/** 게시자명   */
	private String ntcrNm = "";

	/** 패스워드	*/
	private String password = "";

	/** 게시물 첨부파일 아이디  */
	private String atchFileId = "";

	// helper
	// Board Master 정보
	private BoardMasterVO boardMasterVO = null;
	
	/** 유효여부   */
	private String isExpired = "N";
	/** 수정 처리 여부 */
	private boolean isGuestModified = false;

	// domain
	/**
	 * nttId attribute를 리턴한다.
	 * 
	 * @return the nttId
	 */
	public long getNttId() {
		return nttId;
	}
	/**
	 * nttId attribute 값을 설정한다.
	 * 
	 * @param nttId
	 *            the nttId to set
	 */
	public void setNttId(long nttId) {
		this.nttId = nttId;
	}

	/**
	 * bbsId attribute를 리턴한다.
	 * 
	 * @return the bbsId
	 */
	public String getBbsId() {
		return bbsId;
	}
	/**
	 * bbsId attribute 값을 설정한다.
	 * 
	 * @param bbsId
	 *            the bbsId to set
	 */
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
		if(bbsId.length() != 0)
			this.setPathId(WebUtil.getPathId(bbsId));
	}

	/**
	 * threadNo attribute를 리턴한다.
	 * 
	 * @return the threadNo
	 */
	public int getThreadNo() {
		return threadNo;
	}
	/**
	 * threadNo attribute 값을 설정한다.
	 * 
	 * @param threadNo
	 *            the threadNo to set
	 */
	public void setThreadNo(int threadNo) {
		this.threadNo = threadNo;
	}

	/**
	 * nttSj attribute를 리턴한다.
	 * 
	 * @return the nttSj
	 */
	public String getNttSj() {
		return nttSj;
	}
	/**
	 * nttSj attribute 값을 설정한다.
	 * 
	 * @param nttSj
	 *            the nttSj to set
	 */
	public void setNttSj(String nttSj) {
		this.nttSj = nttSj;
	}

	/**
	 * nttCn attribute를 리턴한다.
	 * 
	 * @return the nttCn
	 */
	public String getNttCn() {
		return nttCn;
	}
	/**
	 * nttCn attribute 값을 설정한다.
	 * 
	 * @param nttCn
	 *            the nttCn to set
	 */
	public void setNttCn(String nttCn) {
		this.nttCn = nttCn;
	}

	/**
	 * answerAt attribute를 리턴한다.
	 * 
	 * @return the answerAt
	 */
	public String getAnswerAt() {
		return answerAt;
	}
	/**
	 * answerAt attribute 값을 설정한다.
	 * 
	 * @param answerAt
	 *            the answerAt to set
	 */
	public void setAnswerAt(String answerAt) {
		this.answerAt = answerAt;
	}

	/**
	 * parntsNttId attribute를 리턴한다.
	 * 
	 * @return the parntsNttId
	 */
	public long getParntsNttId() {
		return parntsNttId;
	}
	/**
	 * parntsNttId attribute 값을 설정한다.
	 * 
	 * @param parnts
	 *            the parntsNttId to set
	 */
	public void setParntsNttId(long parntsNttId) {
		this.parntsNttId = parntsNttId;
	}

	/**
	 * threadDepth attribute를 리턴한다.
	 * 
	 * @return the threadDepth
	 */
	public int getThreadDepth() {
		return threadDepth;
	}
	/**
	 * threadDepth attribute 값을 설정한다.
	 * 
	 * @param threadDepth
	 *            the threadDepth to set
	 */
	public void setThreadDepth(int threadDepth) {
		this.threadDepth = threadDepth;
	}

	/**
	 * threadGroupNo attribute를 리턴한다.
	 * 
	 * @return the threadGroupNo
	 */
	public long getThreadGroupNo() {
		return threadGroupNo;
	}
	/**
	 * threadGroupNo attribute 값을 설정한다.
	 * 
	 * @param threadGroupNo
	 *            the threadGroupNo to set
	 */
	public void setThreadGroupNo(long threadGroupNo) {
		this.threadGroupNo = threadGroupNo;
	}

	/**
	 * rdcnt attribute를 리턴한다.
	 * 
	 * @return the rdcnt
	 */
	public int getRdcnt() {
		return rdcnt;
	}
	/**
	 * rdcnt attribute 값을 설정한다.
	 * 
	 * @param rdcnt
	 *            the rdcnt to set
	 */
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
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
	 * ntceBgnde attribute를 리턴한다.
	 * 
	 * @return the ntceBgnde
	 */
	public String getNtceBgnde() {
		return ntceBgnde;
	}
	/**
	 * ntceBgnde attribute 값을 설정한다.
	 * 
	 * @param ntceBgnde
	 *            the ntceBgnde to set
	 */
	public void setNtceBgnde(String ntceBgnde) {
		this.ntceBgnde = ntceBgnde;
	}

	/**
	 * ntceEndde attribute를 리턴한다.
	 * 
	 * @return the ntceEndde
	 */
	public String getNtceEndde() {
		return ntceEndde;
	}
	/**
	 * ntceEndde attribute 값을 설정한다.
	 * 
	 * @param ntceEndde
	 *            the ntceEndde to set
	 */
	public void setNtceEndde(String ntceEndde) {
		this.ntceEndde = ntceEndde;
	}

	/**
	 * ntcrId attribute를 리턴한다.
	 * 
	 * @return the ntcrId
	 */
	public String getNtcrId() {
		return ntcrId;
	}
	/**
	 * ntcrId attribute 값을 설정한다.
	 * 
	 * @param ntcrId
	 *            the ntcrId to set
	 */
	public void setNtcrId(String ntcrId) {
		this.ntcrId = ntcrId;
	}

	/**
	 * ntcrNm attribute를 리턴한다.
	 * 
	 * @return the ntcrNm
	 */
	public String getNtcrNm() {
		return ntcrNm;
	}
	/**
	 * ntcrNm attribute 값을 설정한다.
	 * 
	 * @param ntcrNm
	 *            the ntcrNm to set
	 */
	public void setNtcrNm(String ntcrNm) {
		this.ntcrNm = ntcrNm;
	}

	/**
	 * password attribute를 리턴한다.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * password attribute 값을 설정한다.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * atchFileId attribute를 리턴한다.
	 * 
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * 
	 * @param atchFileId
	 *            the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	// helper
	/**
	 * boardMasterVO attribute를 리턴한다.
	 * 
	 * @return the boardMasterVO
	 */
	public BoardMasterVO getBoardMasterVO() {
		return boardMasterVO;
	}
	/**
	 * boardMasterVO attribute 값을 설정한다.
	 * 
	 * @param boardMasterVO
	 *            the boardMasterVO to set
	 */
	public void setBoardMasterVO(BoardMasterVO boardMasterVO) {
		this.boardMasterVO = boardMasterVO;
	}

	/**
	 * isExpired attribute를 리턴한다.
	 * 
	 * @return the isExpired
	 */
	public String getIsExpired() {
		return isExpired;
	}
	/**
	 * isExpired attribute 값을 설정한다.
	 * 
	 * @param isExpired
	 *            the isExpired to set
	 */
	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}

	/**
	 * isModified attribute를 리턴한다.
	 * 
	 * @return the isModified
	 */
	public boolean isGuestModified() {
		return isGuestModified;
	}
	/**
	 * isModified attribute 값을 설정한다.
	 * 
	 * @param isModified
	 *            the isModified to set
	 */
	public void setGuestModified(boolean isGuestModified) {
		this.isGuestModified = isGuestModified;
	}

}
