package aramframework.com.uss.olp.opm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 온라인POLL관리 VO Class 구현
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

public class OnlinePollManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 대상 아이디 */
	private String trgetId = "";

	/** 온라인POLL 아이디 */
	private String pollId;

	/** 온라인POLL 이름 */
	private String pollNm;

	/** 온라인POLL 시작일자 */
	private String pollBeginDe;

	/** 온라인POLL 종료일자 */
	private String pollEndDe;

	/** 온라인POLL 종류 */
	private String pollKindCode;

	/** 온라인POLL 페기유무 */
	private String pollDsuseYn;

	/** 온라인POLL 자동페기 */
	private String pollAutoDsuseYn;

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
	 * pollId 리턴
	 * 
	 * @return the pollId
	 */
	public String getPollId() {
		return pollId;
	}
	/**
	 * pollId 설정
	 * 
	 * @param pollId
	 *            the pollId to set
	 */
	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	/**
	 * pollNm 리턴
	 * 
	 * @return the pollNm
	 */
	public String getPollNm() {
		return pollNm;
	}
	/**
	 * pollNm 설정
	 * 
	 * @param pollNm
	 *            the pollNm to set
	 */
	public void setPollNm(String pollNm) {
		this.pollNm = pollNm;
	}

	/**
	 * pollBeginDe 리턴
	 * 
	 * @return the pollBeginDe
	 */
	public String getPollBeginDe() {
		return pollBeginDe;
	}
	/**
	 * pollBeginDe 설정
	 * 
	 * @param pollBeginDe
	 *            the pollBeginDe to set
	 */
	public void setPollBeginDe(String pollBeginDe) {
		this.pollBeginDe = pollBeginDe;
	}

	/**
	 * pollEndDe 리턴
	 * 
	 * @return the pollEndDe
	 */
	public String getPollEndDe() {
		return pollEndDe;
	}
	/**
	 * pollEndDe 설정
	 * 
	 * @param pollEndDe
	 *            the pollEndDe to set
	 */
	public void setPollEndDe(String pollEndDe) {
		this.pollEndDe = pollEndDe;
	}

	/**
	 * pollKindCode 리턴
	 * 
	 * @return the pollKindCode
	 */
	public String getPollKindCode() {
		return pollKindCode;
	}
	/**
	 * pollKindCode 설정
	 * 
	 * @param pollKindCode
	 *            the pollKindCode to set
	 */
	public void setPollKindCode(String pollKindCode) {
		this.pollKindCode = pollKindCode;
	}

	/**
	 * pollDsuseYn 리턴
	 * 
	 * @return the pollDsuseYn
	 */
	public String getPollDsuseYn() {
		return pollDsuseYn;
	}
	/**
	 * pollDsuseYn 설정
	 * 
	 * @param pollDsuseYn
	 *            the pollDsuseYn to set
	 */
	public void setPollDsuseYn(String pollDsuseYn) {
		this.pollDsuseYn = pollDsuseYn;
	}

	/**
	 * pollAutoDsuseYn 리턴
	 * 
	 * @return the pollAutoDsuseYn
	 */
	public String getPollAutoDsuseYn() {
		return pollAutoDsuseYn;
	}
	/**
	 * pollAutoDsuseYn 설정
	 * 
	 * @param pollAutoDsuseYn
	 *            the pollAutoDsuseYn to set
	 */
	public void setPollAutoDsuseYn(String pollAutoDsuseYn) {
		this.pollAutoDsuseYn = pollAutoDsuseYn;
	}

}
