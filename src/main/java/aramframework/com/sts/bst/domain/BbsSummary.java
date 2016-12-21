package aramframework.com.sts.bst.domain;

/**
 * 게시물집계정보에 대한 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class BbsSummary {

	// domain
	/** 발생일자 */
	private String occrrncDe;

	/** 통계구분 */
	private String statsSe;

	/** 세부통계구분 */
	private String statsDetailSe;

	/** 생성글수 */
	private int creatCo;

	/** 총조회수 */
	private int totInqireCo;

	/** 평균조회수 */
	private int avrgInqireCo;

	/** 최고조회게시글ID */
	private String mxmmInqireBbsId;

	/** 최소조회게시글ID */
	private String mummInqireBbsId;

	/** 최대게시자ID */
	private String topNtcepersonId;

	// domain
	/**
	 * occrrncDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getOccrrncDe() {
		return occrrncDe;
	}
	/**
	 * occrrncDe attribute 값을 설정한다.
	 * 
	 * @param occrrncDe
	 *            String
	 */
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}

	/**
	 * statsSe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getStatsSe() {
		return statsSe;
	}
	/**
	 * statsSe attribute 값을 설정한다.
	 * 
	 * @param statsSe
	 *            String
	 */
	public void setStatsSe(String statsSe) {
		this.statsSe = statsSe;
	}

	/**
	 * statsDetailSe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getStatsDetailSe() {
		return statsDetailSe;
	}
	/**
	 * statsDetailSe attribute 값을 설정한다.
	 * 
	 * @param statsDetailSe
	 *            String
	 */
	public void setStatsDetailSe(String statsDetailSe) {
		this.statsDetailSe = statsDetailSe;
	}

	/**
	 * creatCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getCreatCo() {
		return creatCo;
	}
	/**
	 * creatCo attribute 값을 설정한다.
	 * 
	 * @param creatCo
	 *            int
	 */
	public void setCreatCo(int creatCo) {
		this.creatCo = creatCo;
	}

	/**
	 * totInqireCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getTotInqireCo() {
		return totInqireCo;
	}
	/**
	 * totInqireCo attribute 값을 설정한다.
	 * 
	 * @param totInqireCo
	 *            int
	 */
	public void setTotInqireCo(int totInqireCo) {
		this.totInqireCo = totInqireCo;
	}
	
	/**
	 * avrgInqireCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getAvrgInqireCo() {
		return avrgInqireCo;
	}
	/**
	 * avrgInqireCo attribute 값을 설정한다.
	 * 
	 * @param avrgInqireCo
	 *            int
	 */
	public void setAvrgInqireCo(int avrgInqireCo) {
		this.avrgInqireCo = avrgInqireCo;
	}

	/**
	 * mxmmInqireBbsId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMxmmInqireBbsId() {
		return mxmmInqireBbsId;
	}
	/**
	 * mxmmInqireBbsId attribute 값을 설정한다.
	 * 
	 * @param mxmmInqireBbsId
	 *            String
	 */
	public void setMxmmInqireBbsId(String mxmmInqireBbsId) {
		this.mxmmInqireBbsId = mxmmInqireBbsId;
	}

	/**
	 * mummInqireBbsId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMummInqireBbsId() {
		return mummInqireBbsId;
	}
	/**
	 * mummInqireBbsId attribute 값을 설정한다.
	 * 
	 * @param mummInqireBbsId
	 *            String
	 */
	public void setMummInqireBbsId(String mummInqireBbsId) {
		this.mummInqireBbsId = mummInqireBbsId;
	}

	/**
	 * topNtcepersonId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTopNtcepersonId() {
		return topNtcepersonId;
	}
	/**
	 * topNtcepersonId attribute 값을 설정한다.
	 * 
	 * @param topNtcepersonId
	 *            String
	 */
	public void setTopNtcepersonId(String topNtcepersonId) {
		this.topNtcepersonId = topNtcepersonId;
	}

}
