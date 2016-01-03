package aramframework.com.uss.olp.opm.service;

import aramframework.com.cmm.SearchVO;

/**
 * 온라인POLL항목 VO Class 구현
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

public class OnlinePollItemVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 온라인POLL 아이디 */
	private String pollId;

	/** 온라인POLL 이름 */
	private String pollNm;

	/** 온라인POLL항목 아이디 */
	private String pollIemId;

	/** 온라인POLL항목 이름 */
	private String pollIemNm;

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
	 * pollIemId 리턴
	 * 
	 * @return the pollIemId
	 */
	public String getPollIemId() {
		return pollIemId;
	}
	/**
	 * pollIemId 설정
	 * 
	 * @param pollIemId
	 *            the pollIemId to set
	 */
	public void setPollIemId(String pollIemId) {
		this.pollIemId = pollIemId;
	}

	/**
	 * pollIemNm 리턴
	 * 
	 * @return the pollIemNm
	 */
	public String getPollIemNm() {
		return pollIemNm;
	}
	/**
	 * pollIemNm 설정
	 * 
	 * @param pollIemNm
	 *            the pollIemNm to set
	 */
	public void setPollIemNm(String pollIemNm) {
		this.pollIemNm = pollIemNm;
	}

}
