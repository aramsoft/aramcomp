package aramframework.com.sym.mnu.stm.domain;

/**
 * 사이트맵 조회를 위한 VO 클래스르를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class SiteMapngVO {

	// domain
	/** 맵생성ID */
	private String mapCreatId;
	
	/** 생성자ID **/
	private String creatPersonId;
	
	/** 맵파일명 */
	private String bndeFileNm;
	
	/** 맵파일경로 */
	private String bndeFilePath;

	// helper
	/** 보안설정대상ID */
	private String scrtyEstbstrgetId;

	// domain
	/**
	 * mapCreatId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMapCreatId() {
		return mapCreatId;
	}
	/**
	 * mapCreatId attribute 값을 설정한다.
	 * 
	 * @param mapCreatId
	 *            String
	 */
	public void setMapCreatId(String mapCreatId) {
		this.mapCreatId = mapCreatId;
	}

	/**
	 * creatPersonId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCreatPersonId() {
		return creatPersonId;
	}
	/**
	 * creatPersonId attribute 값을 설정한다.
	 * 
	 * @param creatPersonId
	 *            String
	 */
	public void setCreatPersonId(String creatPersonId) {
		this.creatPersonId = creatPersonId;
	}

	/**
	 * bndeFileNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getBndeFileNm() {
		return bndeFileNm;
	}
	/**
	 * bndeFileNm attribute 값을 설정한다.
	 * 
	 * @param bndeFileNm
	 *            String
	 */
	public void setBndeFileNm(String bndeFileNm) {
		this.bndeFileNm = bndeFileNm;
	}

	/**
	 * bndeFilePath attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getBndeFilePath() {
		return bndeFilePath;
	}
	/**
	 * bndeFilePath attribute 값을 설정한다.
	 * 
	 * @param bndeFilePath
	 *            String
	 */
	public void setBndeFilePath(String bndeFilePath) {
		this.bndeFilePath = bndeFilePath;
	}

	// helper
	/**
	 * scrtyEstbstrgetId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getScrtyEstbstrgetId() {
		return scrtyEstbstrgetId;
	}
	/**
	 * scrtyEstbstrgetId attribute 값을 설정한다.
	 * 
	 * @param scrtyEstbstrgetId
	 *            String
	 */
	public void setScrtyEstbstrgetId(String scrtyEstbstrgetId) {
		this.scrtyEstbstrgetId = scrtyEstbstrgetId;
	}

}