package aramframework.com.cop.bbs.domain;


import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.WebUtil;

/**
 * 게시판 속성 정보를 관리하기 위한 VO 클래스
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

public class BoardMasterVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 대상 아이디 */
	private String trgetId = "";

	/** 게시판 아이디 */
	private String bbsId = "";

	/** 게시판 명 */
	private String bbsNm = "";

	/** 게시판 소개 */
	private String bbsIntrcn = "";

	/** 게시판 속성코드 */
	private String bbsAttrbCode = "";

	/** 게시판속성 코드명 */
	private String bbsAttrbCodeNm = "";

	/** 게시판 유형코드 */
	private String bbsTyCode = "";

	/** 게시판유형 코드명 */
	private String bbsTyCodeNm = "";

	/** 파일첨부가능여부 */
	private String fileAtchPosblAt = "";

	/** 첨부가능파일숫자 */
	private int posblAtchFileNumber = 0;

	/** 첨부가능파일사이즈 */
	private String posblAtchFileSize = null;

	/** 템플릿 아이디 */
	private String tmplatId = "";

	/** 템플릿 명 */
	private String tmplatNm = "";

	/** 템플릿경로 */
	private String tmplatCours = "";

	/** 답장가능여부 */
	private String replyPosblAt = "";

	/** 사용여부 */
	private String useAt = "";

	/** 사용플래그 */
	private String bbsUseFlag = "";

	/** 등록구분코드 */
	private String registSeCode = "";

	// ---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	// ---------------------------------
	/** 추가 option (댓글-comment, 만족도조사-stsfdg) */
	private String option = "";

	/** 댓글 여부 */
	private String commentAt = "";

	/** 만족도조사 */
	private String stsfdgAt = "";

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
			this.pathId = WebUtil.getPathId(bbsId);
	}

	/**
	 * bbsNm attribute를 리턴한다.
	 * 
	 * @return the bbsNm
	 */
	public String getBbsNm() {
		return bbsNm;
	}
	/**
	 * bbsNm attribute 값을 설정한다.
	 * 
	 * @param bbsNm
	 *            the bbsNm to set
	 */
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	/**
	 * bbsIntrcn attribute를 리턴한다.
	 * 
	 * @return the bbsIntrcn
	 */
	public String getBbsIntrcn() {
		return bbsIntrcn;
	}
	/**
	 * bbsIntrcn attribute 값을 설정한다.
	 * 
	 * @param bbsIntrcn
	 *            the bbsIntrcn to set
	 */
	public void setBbsIntrcn(String bbsIntrcn) {
		this.bbsIntrcn = bbsIntrcn;
	}

	/**
	 * bbsAttrbCode attribute를 리턴한다.
	 * 
	 * @return the bbsAttrbCode
	 */
	public String getBbsAttrbCode() {
		return bbsAttrbCode;
	}
	/**
	 * bbsAttrbCode attribute 값을 설정한다.
	 * 
	 * @param bbsAttrbCode
	 *            the bbsAttrbCode to set
	 */
	public void setBbsAttrbCode(String bbsAttrbCode) {
		this.bbsAttrbCode = bbsAttrbCode;
	}

	/**
	 * bbsAttrbCodeNm attribute를 리턴한다.
	 * 
	 * @return the bbsAttrbCodeNm
	 */
	public String getBbsAttrbCodeNm() {
		return bbsAttrbCodeNm;
	}
	/**
	 * bbsAttrbCodeNm attribute 값을 설정한다.
	 * 
	 * @param bbsAttrbCodeNm
	 *            the bbsAttrbCodeNm to set
	 */
	public void setBbsAttrbCodeNm(String bbsAttrbCodeNm) {
		this.bbsAttrbCodeNm = bbsAttrbCodeNm;
	}

	/**
	 * bbsTyCode attribute를 리턴한다.
	 * 
	 * @return the bbsTyCode
	 */
	public String getBbsTyCode() {
		return bbsTyCode;
	}
	/**
	 * bbsTyCode attribute 값을 설정한다.
	 * 
	 * @param bbsTyCode
	 *            the bbsTyCode to set
	 */
	public void setBbsTyCode(String bbsTyCode) {
		this.bbsTyCode = bbsTyCode;
	}

	/**
	 * bbsTyCodeNm attribute를 리턴한다.
	 * 
	 * @return the bbsTyCodeNm
	 */
	public String getBbsTyCodeNm() {
		return bbsTyCodeNm;
	}
	/**
	 * bbsTyCodeNm attribute 값을 설정한다.
	 * 
	 * @param bbsTyCodeNm
	 *            the bbsTyCodeNm to set
	 */
	public void setBbsTyCodeNm(String bbsTyCodeNm) {
		this.bbsTyCodeNm = bbsTyCodeNm;
	}

	/**
	 * fileAtchPosblAt attribute를 리턴한다.
	 * 
	 * @return the fileAtchPosblAt
	 */
	public String getFileAtchPosblAt() {
		return fileAtchPosblAt;
	}
	/**
	 * fileAtchPosblAt attribute 값을 설정한다.
	 * 
	 * @param fileAtchPosblAt
	 *            the fileAtchPosblAt to set
	 */
	public void setFileAtchPosblAt(String fileAtchPosblAt) {
		this.fileAtchPosblAt = fileAtchPosblAt;
	}

	/**
	 * posblAtchFileNumber attribute를 리턴한다.
	 * 
	 * @return the posblAtchFileNumber
	 */
	public int getPosblAtchFileNumber() {
		return posblAtchFileNumber;
	}
	/**
	 * posblAtchFileNumber attribute 값을 설정한다.
	 * 
	 * @param posblAtchFileNumber
	 *            the posblAtchFileNumber to set
	 */
	public void setPosblAtchFileNumber(int posblAtchFileNumber) {
		this.posblAtchFileNumber = posblAtchFileNumber;
	}

	/**
	 * posblAtchFileSize attribute를 리턴한다.
	 * 
	 * @return the posblAtchFileSize
	 */
	public String getPosblAtchFileSize() {
		return posblAtchFileSize;
	}
	/**
	 * posblAtchFileSize attribute 값을 설정한다.
	 * 
	 * @param posblAtchFileSize
	 *            the posblAtchFileSize to set
	 */
	public void setPosblAtchFileSize(String posblAtchFileSize) {
		this.posblAtchFileSize = posblAtchFileSize;
	}

	/**
	 * replyPosblAt attribute를 리턴한다.
	 * 
	 * @return the replyPosblAt
	 */
	public String getReplyPosblAt() {
		return replyPosblAt;
	}
	/**
	 * replyPosblAt attribute 값을 설정한다.
	 * 
	 * @param replyPosblAt
	 *            the replyPosblAt to set
	 */
	public void setReplyPosblAt(String replyPosblAt) {
		this.replyPosblAt = replyPosblAt;
	}

	/**
	 * tmplatId attribute를 리턴한다.
	 * 
	 * @return the tmplatId
	 */
	public String getTmplatId() {
		return tmplatId;
	}
	/**
	 * tmplatId attribute 값을 설정한다.
	 * 
	 * @param tmplatId
	 *            the tmplatId to set
	 */
	public void setTmplatId(String tmplatId) {
		this.tmplatId = tmplatId;
	}

	/**
	 * tmplatNm attribute를 리턴한다.
	 * 
	 * @return the tmplatNm
	 */
	public String getTmplatNm() {
		return tmplatNm;
	}
	/**
	 * tmplatNm attribute 값을 설정한다.
	 * 
	 * @param tmplatNm
	 *            the tmplatNm to set
	 */
	public void setTmplatNm(String tmplatNm) {
		this.tmplatNm = tmplatNm;
	}

	/**
	 * tmplatCours attribute를 리턴한다.
	 * 
	 * @return the tmplatCours
	 */
	public String getTmplatCours() {
		return tmplatCours;
	}
	/**
	 * tmplatCours attribute 값을 설정한다.
	 * 
	 * @param tmplatCours
	 *            the tmplatCours to set
	 */
	public void setTmplatCours(String tmplatCours) {
		this.tmplatCours = tmplatCours;
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
	 * bbsUseFlag attribute를 리턴한다.
	 * 
	 * @return the bbsUseFlag
	 */
	public String getBbsUseFlag() {
		return bbsUseFlag;
	}
	/**
	 * bbsUseFlag attribute 값을 설정한다.
	 * 
	 * @param bbsUseFlag
	 *            the bbsUseFlag to set
	 */
	public void setBbsUseFlag(String bbsUseFlag) {
		this.bbsUseFlag = bbsUseFlag;
	}

	/**
	 * registSeCode attribute를 리턴한다.
	 * 
	 * @return the registSeCode
	 */
	public String getRegistSeCode() {
		return registSeCode;
	}
	/**
	 * registSeCode attribute 값을 설정한다.
	 * 
	 * @param registSeCode
	 *            the registSeCode to set
	 */
	public void setRegistSeCode(String registSeCode) {
		this.registSeCode = registSeCode;
	}

	/**
	 * option attribute를 리턴한다.
	 * 
	 * @return the option
	 */
	public String getOption() {
		return option;
	}
	/**
	 * option attribute 값을 설정한다.
	 * 
	 * @param option
	 *            the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * commentAt attribute를 리턴한다.
	 * 
	 * @return the commentAt
	 */
	public String getCommentAt() {
		return commentAt;
	}
	/**
	 * commentAt attribute 값을 설정한다.
	 * 
	 * @param commentAt
	 *            the commentAt to set
	 */
	public void setCommentAt(String commentAt) {
		this.commentAt = commentAt;
	}

	/**
	 * stsfdgAt attribute를 리턴한다.
	 * 
	 * @return the stsfdgAt
	 */
	public String getStsfdgAt() {
		return stsfdgAt;
	}
	/**
	 * stsfdg attribute 값을 설정한다.
	 * 
	 * @param stsfdgAt
	 *            the stsfdgAt to set
	 */
	public void setStsfdgAt(String stsfdgAt) {
		this.stsfdgAt = stsfdgAt;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
