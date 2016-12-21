package aramframework.com.sts.ust.domain;

/**
 * 사용자집계정보에 대한 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class UserSummary {

	// domain
	/** 발생일자	*/
	private String occrrncDe;
	
	/** 통계구분 */
	private String statsSe;
	
	/** 세부통계구분 */
	private String statsDetailSe;
	
	/** 사용자수 */
	private int userCo;

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
	 * userCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getUserCo() {
		return userCo;
	}
	/**
	 * userCo attribute 값을 설정한다.
	 * 
	 * @param userCo
	 *            int
	 */
	public void setUserCo(int userCo) {
		this.userCo = userCo;
	}
	
}
