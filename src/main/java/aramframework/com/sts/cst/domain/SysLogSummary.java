package aramframework.com.sts.cst.domain;

/**
 * 시스템로그집계정보에 대한 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class SysLogSummary {

	// domain
	/** 발생일자 */
	private String occrrncDe;
	
	/** 서비스명 */
	private String srvcNm;
	
	/** 메소드명 */
	private String methodNm;
	
	/** 생성횟수 */
	private int creatCo;
	
	/** 수정횟수 */
	private int updtCo;

	/** 조회횟수 */
	private int rdCnt;
	
	/** 삭제횟수 */
	private int deleteCo;
	
	/** 출력횟수 */
	private int outptCo;
	
	/** 에러횟수 */
	private int errorCo;
	
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
	 * srvcNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSrvcNm() {
		return srvcNm;
	}
	/**
	 * srvcNm attribute 값을 설정한다.
	 * 
	 * @param srvcNm
	 *            String
	 */
	public void setSrvcNm(String srvcNm) {
		this.srvcNm = srvcNm;
	}

	/**
	 * methodNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMethodNm() {
		return methodNm;
	}
	/**
	 * methodNm attribute 값을 설정한다.
	 * 
	 * @param methodNm
	 *            String
	 */
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
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
	 * updtCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getUpdtCo() {
		return updtCo;
	}
	/**
	 * updtCo attribute 값을 설정한다.
	 * 
	 * @param updtCo
	 *            int
	 */
	public void setUpdtCo(int updtCo) {
		this.updtCo = updtCo;
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

	/**
	 * deleteCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getDeleteCo() {
		return deleteCo;
	}
	/**
	 * deleteCo attribute 값을 설정한다.
	 * 
	 * @param deleteCo
	 *            int
	 */
	public void setDeleteCo(int deleteCo) {
		this.deleteCo = deleteCo;
	}

	/**
	 * outptCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getOutptCo() {
		return outptCo;
	}
	/**
	 * outptCo attribute 값을 설정한다.
	 * 
	 * @param outptCo
	 *            int
	 */
	public void setOutptCo(int outptCo) {
		this.outptCo = outptCo;
	}

	/**
	 * errorCo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getErrorCo() {
		return errorCo;
	}
	/**
	 * errorCo attribute 값을 설정한다.
	 * 
	 * @param errorCo
	 *            int
	 */
	public void setErrorCo(int errorCo) {
		this.errorCo = errorCo;
	}

}
