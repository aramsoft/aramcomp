package aramframework.com.utl.sys.ssy.domain;

import java.io.File;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 동기화대상 서버에 대한 model 클래스를 정의한다.
 * 
 * 상세내용 - 동기화대상 서버의 ID, 서버 명, 서버 IP, FTP ID, FTP 비밀번호, 동기화위치 등의 항목을 관리한다.
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

public class SynchrnServerVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 동기화대상 서버명 조회조건 */
	private String strSynchrnServerNm;
	
	/** 동기화 대상 파일 */
	private File synchrnFile;
	
	/** 삭제 대상 파일 */
	private String deleteFileNm;
	
	/** 업로드 위치 */
	private String filePath;
	
	/** 반영여부 */
	private String strReflctAt;
	
	/** 서버 ID */
	private String serverId;
	
	/** 서버 명 */
	private String serverNm;
	
	/** 서버 IP */
	private String serverIp;
	
	/** 서버 Port */
	private String serverPort;
	
	/** FTP ID */
	private String ftpId;
	
	/** FTP 비밀번호 */
	private String ftpPassword;
	
	/** 동기화 위치 */
	private String synchrnLc;
	
	/** 반영 여부 */
	private String reflctAt;
	
	/**
	 * @return the strSynchrnServerNm
	 */
	public String getStrSynchrnServerNm() {
		return strSynchrnServerNm;
	}
	/**
	 * @param strSynchrnServerNm
	 *            the strSynchrnServerNm to set
	 */
	public void setStrSynchrnServerNm(String strSynchrnServerNm) {
		this.strSynchrnServerNm = strSynchrnServerNm;
	}

	/**
	 * @return the synchrnFile
	 */
	public File getSynchrnFile() {
		return synchrnFile;
	}
	/**
	 * @param synchrnFile
	 *            the synchrnFile to set
	 */
	public void setSynchrnFile(File synchrnFile) {
		this.synchrnFile = synchrnFile;
	}

	/**
	 * @return the deleteFileNm
	 */
	public String getDeleteFileNm() {
		return deleteFileNm;
	}
	/**
	 * @param deleteFileNm
	 *            the deleteFileNm to set
	 */
	public void setDeleteFileNm(String deleteFileNm) {
		this.deleteFileNm = deleteFileNm;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the strReflctAt
	 */
	public String getStrReflctAt() {
		return strReflctAt;
	}
	/**
	 * @param strReflctAt
	 *            the strReflctAt to set
	 */
	public void setStrReflctAt(String strReflctAt) {
		this.strReflctAt = strReflctAt;
	}

	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}
	/**
	 * @param serverId
	 *            the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
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
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}
	/**
	 * @param serverIp
	 *            the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}
	/**
	 * @param serverPort
	 *            the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the ftpId
	 */
	public String getFtpId() {
		return ftpId;
	}
	/**
	 * @param ftpId
	 *            the ftpId to set
	 */
	public void setFtpId(String ftpId) {
		this.ftpId = ftpId;
	}

	/**
	 * @return the ftpPassword
	 */
	public String getFtpPassword() {
		return ftpPassword;
	}
	/**
	 * @param ftpPassword
	 *            the ftpPassword to set
	 */
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	/**
	 * @return the synchrnLc
	 */
	public String getSynchrnLc() {
		return synchrnLc;
	}
	/**
	 * @param synchrnLc
	 *            the synchrnLc to set
	 */
	public void setSynchrnLc(String synchrnLc) {
		this.synchrnLc = synchrnLc;
	}

	/**
	 * @return the reflctAt
	 */
	public String getReflctAt() {
		return reflctAt;
	}
	/**
	 * @param reflctAt
	 *            the reflctAt to set
	 */
	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
	}

}