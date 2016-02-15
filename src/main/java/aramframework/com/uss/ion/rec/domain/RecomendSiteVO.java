package aramframework.com.uss.ion.rec.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 추천사이트정보를 처리하는 VO 클래스
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

public class RecomendSiteVO extends BaseVO  {

	// domain
	/** 추천사이트 ID */
	private String recomendSiteId;

	/** 추천사이트명 */
	private String recomendSiteNm;

	/** 추천사이트 URL */
	private String recomendSiteUrl;

	/** 추천사이트설명 */
	private String recomendSiteDc;

	/** 추천사유내용 */
	private String recomendResnCn;

	/** 추천승인여부 */
	private String recomendConfmAt;

	/** 승인일자 */
	private String confmDe;

	/** 대상 아이디 */
	private String trgetId = "";

	// domain
	/**
	 * recomendSiteId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getRecomendSiteId() {
		return recomendSiteId;
	}
	/**
	 * recomendSiteId attribute 값을 설정한다.
	 * 
	 * @return recomendSiteId String
	 */
	public void setRecomendSiteId(String recomendSiteId) {
		this.recomendSiteId = recomendSiteId;
	}

	/**
	 * recomendSiteNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getRecomendSiteNm() {
		return recomendSiteNm;
	}
	/**
	 * recomendSiteNm attribute 값을 설정한다.
	 * 
	 * @return recomendSiteNm String
	 */
	public void setRecomendSiteNm(String recomendSiteNm) {
		this.recomendSiteNm = recomendSiteNm;
	}

	/**
	 * recomendSiteUrl attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getRecomendSiteUrl() {
		return recomendSiteUrl;
	}
	/**
	 * recomendSiteUrl attribute 값을 설정한다.
	 * 
	 * @return recomendSiteUrl String
	 */
	public void setRecomendSiteUrl(String recomendSiteUrl) {
		this.recomendSiteUrl = recomendSiteUrl;
	}

	/**
	 * recomendSiteDc attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getRecomendSiteDc() {
		return recomendSiteDc;
	}
	/**
	 * recomendSiteDc attribute 값을 설정한다.
	 * 
	 * @return recomendSiteDc String
	 */
	public void setRecomendSiteDc(String recomendSiteDc) {
		this.recomendSiteDc = recomendSiteDc;
	}

	/**
	 * recomendResnCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getRecomendResnCn() {
		return recomendResnCn;
	}
	/**
	 * recomendResnCn attribute 값을 설정한다.
	 * 
	 * @return recomendResnCn String
	 */
	public void setRecomendResnCn(String recomendResnCn) {
		this.recomendResnCn = recomendResnCn;
	}

	/**
	 * recomendConfmAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getRecomendConfmAt() {
		return recomendConfmAt;
	}
	/**
	 * recomendConfmAt attribute 값을 설정한다.
	 * 
	 * @return recomendConfmAt String
	 */
	public void setRecomendConfmAt(String recomendConfmAt) {
		this.recomendConfmAt = recomendConfmAt;
	}

	/**
	 * confmDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getConfmDe() {
		return confmDe;
	}
	/**
	 * confmDe attribute 값을 설정한다.
	 * 
	 * @return confmDe String
	 */
	public void setConfmDe(String confmDe) {
		this.confmDe = confmDe;
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

}
