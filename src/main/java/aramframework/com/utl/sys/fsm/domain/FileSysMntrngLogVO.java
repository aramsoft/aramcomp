package aramframework.com.utl.sys.fsm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 파일시스템 모니터링 로그에 대한 Vo 클래스를 정의한다.
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

public class FileSysMntrngLogVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 시작일자 조회조건 */
	private String searchBgnDe = "";

	/** 시작시간 조회조건 */
	private String searchBgnHour = "";

	/** 시작일시 조회조건 */
	private String searchBgnDt = "";

	/** 종료일자 조회조건 */
	private String searchEndDe = "";

	/** 종료시간 조회조건 */
	private String searchEndHour = "";

	/** 종료일시 조회조건 */
	private String searchEndDt = "";

	/** 로그ID */
	private String logId;
	
	/** 파일시스템ID */
	private String fileSysId;
	
	/** 파일시스템명 */
	private String fileSysNm;
	
	/** 파일시스템관리명 */
	private String fileSysManageNm;
	
	/** 파일시스템크기 */
	private int fileSysMg;
	
	/** 파일시스템임계치 */
	private int fileSysThrhld;
	
	/** 파일시스템임계치율 */
	private int fileSysThrhldRt;
	
	/** 파일시스템사용량 */
	private int fileSysUsgQty;
	
	/** 파일시스템사용률 */
	private double fileSysUsgRt;
	
	/** 모니터링상태 */
	private String mntrngSttus;
	
	/** 로그정보 */
	private String logInfo;
	
	/** 생성일시 */
	private String creatDt;
	
	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchBgnHour() {
		return searchBgnHour;
	}
	public void setSearchBgnHour(String searchBgnHour) {
		this.searchBgnHour = searchBgnHour;
	}

	public String getSearchBgnDt() {
		return searchBgnDt;
	}
	public void setSearchBgnDt(String searchBgnDt) {
		this.searchBgnDt = searchBgnDt;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getSearchEndHour() {
		return searchEndHour;
	}
	public void setSearchEndHour(String searchEndHour) {
		this.searchEndHour = searchEndHour;
	}

	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}

	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getFileSysId() {
		return fileSysId;
	}
	public void setFileSysId(String fileSysId) {
		this.fileSysId = fileSysId;
	}

	public String getFileSysNm() {
		return fileSysNm;
	}
	public void setFileSysNm(String fileSysNm) {
		this.fileSysNm = fileSysNm;
	}

	public String getFileSysManageNm() {
		return fileSysManageNm;
	}
	public void setFileSysManageNm(String fileSysManageNm) {
		this.fileSysManageNm = fileSysManageNm;
	}

	public int getFileSysMg() {
		return fileSysMg;
	}
	public void setFileSysMg(int fileSysMg) {
		this.fileSysMg = fileSysMg;
	}

	public int getFileSysThrhld() {
		return fileSysThrhld;
	}
	public void setFileSysThrhld(int fileSysThrhld) {
		this.fileSysThrhld = fileSysThrhld;
	}

	public int getFileSysThrhldRt() {
		return fileSysThrhldRt;
	}
	public void setFileSysThrhldRt(int fileSysThrhldRt) {
		this.fileSysThrhldRt = fileSysThrhldRt;
	}

	public int getFileSysUsgQty() {
		return fileSysUsgQty;
	}
	public void setFileSysUsgQty(int fileSysUsgQty) {
		this.fileSysUsgQty = fileSysUsgQty;
	}

	public double getFileSysUsgRt() {
		return fileSysUsgRt;
	}
	public void setFileSysUsgRt(double fileSysUsgRt) {
		this.fileSysUsgRt = fileSysUsgRt;
	}

	public String getMntrngSttus() {
		return mntrngSttus;
	}
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

}