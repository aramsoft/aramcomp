package aramframework.com.sym.prm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 프로그램변경관리 처리를 위한 VO 클래스르를 정의한다
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

public class ProgrmManageDtlVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 프로그램파일명 */
	private String progrmFileNm;
	
	/** 요청번호 */
	private int rqestNo;
	
	/** 요청제목 */
	private String rqestSj;
	
	/** 요청자ID */
	private String rqestPersonId;
	
	/** 요청일자 */
	private String rqestDe;
	
	/** 변경요청내용 */
	private String changeRqestCn;
	
	/** 처리자ID */
	private String opetrId;
	
	/** 처리상태코드 */
	private String processSttus;
	
	/** 처리일자 */
	private String processDe;
	
	/** 요청처리내용 */
	private String rqestProcessCn;

	/** 요청시작일자 */
	private String rqestDeBegin;
	
	/** 요청종료일자 */
	private String rqestDeEnd;

	/**
	 * progrmFileNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	/**
	 * progrmFileNm attribute 값을 설정한다.
	 * 
	 * @param progrmFileNm
	 *            String
	 */
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	/**
	 * rqestNo attribute를 리턴한다.
	 * 
	 * @return int
	 */
	public int getRqestNo() {
		return rqestNo;
	}
	/**
	 * rqestNo attribute 값을 설정한다.
	 * 
	 * @param rqestNo
	 *            int
	 */
	public void setRqestNo(int rqestNo) {
		this.rqestNo = rqestNo;
	}

	/**
	 * rqestSj attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRqestSj() {
		return rqestSj;
	}
	/**
	 * rqesteSj attribute 값을 설정한다.
	 * 
	 * @param rqestSj
	 *            String
	 */
	public void setRqestSj(String rqestSj) {
		this.rqestSj = rqestSj;
	}

	/**
	 * rqestPersonId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRqestPersonId() {
		return rqestPersonId;
	}
	/**
	 * rqestPersonId attribute 값을 설정한다.
	 * 
	 * @param rqestPersonId
	 *            String
	 */
	public void setRqestPersonId(String rqestPersonId) {
		this.rqestPersonId = rqestPersonId;
	}

	/**
	 * rqestDe attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRqestDe() {
		return rqestDe;
	}
	/**
	 * rqestDe attribute 값을 설정한다.
	 * 
	 * @param rqestDe
	 *            String
	 */
	public void setRqestDe(String rqestDe) {
		this.rqestDe = rqestDe;
	}

	/**
	 * changeRqestCn attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getChangeRqestCn() {
		return changeRqestCn;
	}
	/**
	 * changRqestCn attribute 값을 설정한다.
	 * 
	 * @param changRqestCn
	 *            String
	 */
	public void setChangeRqestCn(String changeRqestCn) {
		this.changeRqestCn = changeRqestCn;
	}

	/**
	 * opetrId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getOpetrId() {
		return opetrId;
	}
	/**
	 * opetrId attribute 값을 설정한다.
	 * 
	 * @param opetrId
	 *            String
	 */
	public void setOpetrId(String opetrId) {
		this.opetrId = opetrId;
	}

	/**
	 * processSttus attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProcessSttus() {
		return processSttus;
	}
	/**
	 * processSttus attribute 값을 설정한다.
	 * 
	 * @param processSttus
	 *            String
	 */
	public void setProcessSttus(String processSttus) {
		this.processSttus = processSttus;
	}

	/**
	 * processDe attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProcessDe() {
		return processDe;
	}
	/**
	 * processDe attribute 값을 설정한다.
	 * 
	 * @param processDe
	 *            String
	 */
	public void setProcessDe(String processDe) {
		this.processDe = processDe;
	}

	/**
	 * rqestProcessCn attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRqestProcessCn() {
		return rqestProcessCn;
	}
	/**
	 * rqestProcessCn attribute 값을 설정한다.
	 * 
	 * @param rqesterProcessCn
	 *            String
	 */
	public void setRqestProcessCn(String rqestProcessCn) {
		this.rqestProcessCn = rqestProcessCn;
	}

	/**
	 * rqestDeBegin attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRqestDeBegin() {
		return rqestDeBegin;
	}
	/**
	 * rqestDeBegin attribute 값을 설정한다.
	 * 
	 * @param rqestDeBegin
	 *            String
	 */
	public void setRqestDeBegin(String rqestDeBegin) {
		this.rqestDeBegin = rqestDeBegin;
	}

	/**
	 * rqestDeEnd attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRqestDeEnd() {
		return rqestDeEnd;
	}
	/**
	 * rqesterDeEnd attribute 값을 설정한다.
	 * 
	 * @param rqesterDeEnd
	 *            String
	 */
	public void setRqestDeEnd(String rqestDeEnd) {
		this.rqestDeEnd = rqestDeEnd;
	}

}