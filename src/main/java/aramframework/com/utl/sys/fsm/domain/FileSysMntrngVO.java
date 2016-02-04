package aramframework.com.utl.sys.fsm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 파일시스템 모니터링대상에 대한 Vo 클래스를 정의한다.
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

public class FileSysMntrngVO extends SearchVO {

	private static final long serialVersionUID = 1L;

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
	
	/** 관리자명 */
	private String mngrNm;
	
	/** 관리자이메일주소 */
	private String mngrEmailAddr;
	
	/** 로그정보 */
	private String logInfo;
	
	/** 모니터링상태 */
	private String mntrngSttus;
	
	/** 생성일시 */
	private String creatDt;
	
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

	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}

	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}

	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getMntrngSttus() {
		return mntrngSttus;
	}
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

}