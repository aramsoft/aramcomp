package aramframework.com.sts.sst.domain;

/**
 * 웹로그집계정보에 대한 모델 클래스
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

public class WebLogSummary {

	// domain
	/** 발생일자 */
	private String occrrncDe;
	
	/** URL */
	private String url;

	/** 횟수	 */
	private int rdCnt;
	
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
	 * url attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * url attribute 값을 설정한다.
	 * 
	 * @param url
	 *            String
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * rdCnt attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getRdCnt() {
		return rdCnt;
	}
	/**
	 * rdCnt attribute 값을 설정한다.
	 * 
	 * @param rdCnt
	 *            int
	 */
	public void setRdCnt(int rdCnt) {
		this.rdCnt = rdCnt;
	}
	
}
