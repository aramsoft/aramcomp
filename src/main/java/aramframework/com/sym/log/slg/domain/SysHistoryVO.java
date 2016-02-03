package aramframework.com.sym.log.slg.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 시스템 처리 이력관리를 위한 데이터 객체
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

public class SysHistoryVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 등록 구분코드 명 */
	private String histSeCodeNm = "";

	/** 생성일시 */
	private String histId = "";
	
	/** 이력내용 */
	private String histCn = "";
	
	/** 이력구분코드 */
	private String histSeCode = "";
	
	/** 시스템명 */
	private String sysNm = "";
	
	/** 첨부파일ID */
	private String atchFileId = "";

	/**
	 * @return the histSeCodeNm
	 */
	public String getHistSeCodeNm() {
		return histSeCodeNm;
	}
	/**
	 * @param histSeCodeNm
	 *            the histSeCodeNm to set
	 */
	public void setHistSeCodeNm(String histSeCodeNm) {
		this.histSeCodeNm = histSeCodeNm;
	}

	/**
	 * @return the creatDt
	 */
	public String getHistId() {
		return histId;
	}
	/**
	 * @param creatDt
	 *            the creatDt to set
	 */
	public void setHistId(String histId) {
		this.histId = histId;
	}

	/**
	 * @return the histCn
	 */
	public String getHistCn() {
		return histCn;
	}
	/**
	 * @param histCn
	 *            the histCn to set
	 */
	public void setHistCn(String histCn) {
		this.histCn = histCn;
	}

	/**
	 * @return the histSeCode
	 */
	public String getHistSeCode() {
		return histSeCode;
	}
	/**
	 * @param histSeCode
	 *            the histSeCode to set
	 */
	public void setHistSeCode(String histSeCode) {
		this.histSeCode = histSeCode;
	}

	/**
	 * @return the sysNm
	 */
	public String getSysNm() {
		return sysNm;
	}
	/**
	 * @param sysNm
	 *            the sysNm to set
	 */
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * 
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
