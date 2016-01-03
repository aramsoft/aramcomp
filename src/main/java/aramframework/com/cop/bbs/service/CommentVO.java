package aramframework.com.cop.bbs.service;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 게시물 관리를 위한 VO 클래스
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

public class CommentVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 수정 처리 여부 */
	private boolean isModified = false;

	// 도메인 정보
	
	/** 댓글번호 */
	private String commentNo = "";

	/** 게시판 ID */
	private String bbsId = "";

	/** 게시물 번호 */
	private long nttId = 0L;

	/** 작성자 ID */
	private String wrterId = "";

	/** 작성자명 */
	private String wrterNm = "";

	/** 패스워드 */
	private String commentPassword = "";

	/** 확인 패스워드 */
	private String confirmPassword = "";

	/** 댓글 내용 */
	private String commentCn = "";

	/** 사용 여부 */
	private String useAt = "";

	/**
	 * isModified attribute를 리턴한다.
	 * 
	 * @return the isModified
	 */
	public boolean isModified() {
		return isModified;
	}
	/**
	 * isModified attribute 값을 설정한다.
	 * 
	 * @param isModified
	 *            the isModified to set
	 */
	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	/**
	 * commentNo attribute를 리턴한다.
	 * 
	 * @return the commentNo
	 */
	public String getCommentNo() {
		return commentNo;
	}
	/**
	 * commentNo attribute 값을 설정한다.
	 * 
	 * @param commentNo
	 *            the commentNo to set
	 */
	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
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
	}

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
	 * wrterId attribute를 리턴한다.
	 * 
	 * @return the wrterId
	 */
	public String getWrterId() {
		return wrterId;
	}
	/**
	 * wrterId attribute 값을 설정한다.
	 * 
	 * @param wrterId
	 *            the wrterId to set
	 */
	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	/**
	 * wrterNm attribute를 리턴한다.
	 * 
	 * @return the wrterNm
	 */
	public String getWrterNm() {
		return wrterNm;
	}
	/**
	 * wrterNm attribute 값을 설정한다.
	 * 
	 * @param wrterNm
	 *            the wrterNm to set
	 */
	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	/**
	 * commentPassword attribute를 리턴한다.
	 * 
	 * @return the commentPassword
	 */
	public String getCommentPassword() {
		return commentPassword;
	}
	/**
	 * commentPassword attribute 값을 설정한다.
	 * 
	 * @param commentPassword
	 *            the commentPassword to set
	 */
	public void setCommentPassword(String commentPassword) {
		this.commentPassword = commentPassword;
	}

	/**
	 * confirmPassword attribute를 리턴한다.
	 * 
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * confirmPassword attribute 값을 설정한다.
	 * 
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * commentCn attribute를 리턴한다.
	 * 
	 * @return the commentCn
	 */
	public String getCommentCn() {
		return commentCn;
	}
	/**
	 * commentCn attribute 값을 설정한다.
	 * 
	 * @param commentCn
	 *            the commentCn to set
	 */
	public void setCommentCn(String commentCn) {
		this.commentCn = commentCn;
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
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
