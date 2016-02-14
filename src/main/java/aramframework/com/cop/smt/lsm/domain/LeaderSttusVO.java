package aramframework.com.cop.smt.lsm.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 간부상태에 대한 Vo 클래스를 정의한다.
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

public class LeaderSttusVO extends SearchVO {

	/** 간부ID */
	private String leaderId;

	/** 간부명 */
	private String leaderNm;

	/** 소속	 */
	private String orgnztNm;

	/** 간부상태	 */
	private String leaderSttus;

	/** 간부상태	 */
	private String leaderSttusNm;

	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderNm() {
		return leaderNm;
	}
	public void setLeaderNm(String leaderNm) {
		this.leaderNm = leaderNm;
	}

	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	public String getLeaderSttus() {
		return leaderSttus;
	}
	public void setLeaderSttus(String leaderSttus) {
		this.leaderSttus = leaderSttus;
	}

	public String getLeaderSttusNm() {
		return leaderSttusNm;
	}
	public void setLeaderSttusNm(String leaderSttusNm) {
		this.leaderSttusNm = leaderSttusNm;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}