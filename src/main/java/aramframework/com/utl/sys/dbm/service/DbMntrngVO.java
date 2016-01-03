package aramframework.com.utl.sys.dbm.service;

import aramframework.com.cmm.SearchVO;

/**
 * DB서비스모니터링에 대한 model 클래스
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

public class DbMntrngVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 데이터소스명 */
	private String dataSourcNm;
	
	/** 서버명 */
	private String serverNm;
	
	/** DBMS종류 */
	private String dbmsKind;
	
	/** 체크SQL */
	private String ceckSql;
	
	/** 관리자명 */
	private String mngrNm;
	
	/** 관리자이메일주소 */
	private String mngrEmailAddr;
	
	/** 모니터링상태 */
	private String mntrngSttus;

	/** 생성일시 */
	private String creatDt;

	/** 모니터링상태명 */
	private String mntrngSttusNm;
	
	/** DBMS종류명 */
	private String dbmsKindNm;

	/**
	 * @return the dataSourcNm
	 */
	public String getDataSourcNm() {
		return dataSourcNm;
	}
	/**
	 * @param dataSourcNm
	 *            the dataSourcNm to set
	 */
	public void setDataSourcNm(String dataSourcNm) {
		this.dataSourcNm = dataSourcNm;
	}

	/**
	 * @return the serverNm
	 */
	public String getServerNm() {
		return serverNm;
	}
	/**
	 * @param serverNm
	 *            the serverNm to set
	 */
	public void setServerNm(String serverNm) {
		this.serverNm = serverNm;
	}

	/**
	 * @return the dbmsKind
	 */
	public String getDbmsKind() {
		return dbmsKind;
	}
	/**
	 * @param dbmsKind
	 *            the dbmsKind to set
	 */
	public void setDbmsKind(String dbmsKind) {
		this.dbmsKind = dbmsKind;
	}

	/**
	 * @return the ceckSql
	 */
	public String getCeckSql() {
		return ceckSql;
	}
	/**
	 * @param ceckSql
	 *            the ceckSql to set
	 */
	public void setCeckSql(String ceckSql) {
		this.ceckSql = ceckSql;
	}

	/**
	 * @return the mngrNm
	 */
	public String getMngrNm() {
		return mngrNm;
	}
	/**
	 * @param mngrNm
	 *            the mngrNm to set
	 */
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}

	/**
	 * @return the mngrEmailAddr
	 */
	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	/**
	 * @param mngrEmailAddr
	 *            the mngrEmailAddr to set
	 */
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}

	/**
	 * @return the mntrngSttus
	 */
	public String getMntrngSttus() {
		return mntrngSttus;
	}
	/**
	 * @param mntrngSttus
	 *            the mntrngSttus to set
	 */
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

	/**
	 * @return the mntrngSttusNm
	 */
	public String getMntrngSttusNm() {
		return mntrngSttusNm;
	}
	/**
	 * @param mntrngSttusNm
	 *            the mntrngSttusNm to set
	 */
	public void setMntrngSttusNm(String mntrngSttusNm) {
		this.mntrngSttusNm = mntrngSttusNm;
	}

	/**
	 * @return the dbmsKindNm
	 */
	public String getDbmsKindNm() {
		return dbmsKindNm;
	}
	/**
	 * @param dbmsKindNm
	 *            the dbmsKindNm to set
	 */
	public void setDbmsKindNm(String dbmsKindNm) {
		this.dbmsKindNm = dbmsKindNm;
	}

	/**
	 * @return the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * @param creatDt
	 *            the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

}