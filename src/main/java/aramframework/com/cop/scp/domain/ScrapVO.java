package aramframework.com.cop.scp.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 스크랩 서비스를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class ScrapVO extends BaseVO {

	// domain
	/** 스크랩 ID */
	private String scrapId = "";

	/** 게시판 ID */
	private String bbsId = "";

	/** 게시물 번호 */
	private long nttId = 0L;

	/** 스크랩명 */
	private String scrapNm = "";

	/** 사용 여부 */
	private String useAt = "";

	// helper
	/** 유일 아이디 */
	private String uniqId = "";

	// domain
	/**
	 * scrapId attribute를 리턴한다.
	 * 
	 * @return the scrapId
	 */
	public String getScrapId() {
		return scrapId;
	}
	/**
	 * scrapId attribute 값을 설정한다.
	 * 
	 * @param scrapId
	 *            the scrapId to set
	 */
	public void setScrapId(String scrapId) {
		this.scrapId = scrapId;
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
	 * scrapNm attribute를 리턴한다.
	 * 
	 * @return the scrapNm
	 */
	public String getScrapNm() {
		return scrapNm;
	}
	/**
	 * scrapNm attribute 값을 설정한다.
	 * 
	 * @param scrapNm
	 *            the scrapNm to set
	 */
	public void setScrapNm(String scrapNm) {
		this.scrapNm = scrapNm;
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

	// helper
	/**
	 * uniqId attribute를 리턴한다.
	 * 
	 * @return the uniqId
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * 
	 * @param uniqId
	 *            the uniqId to set
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

}
