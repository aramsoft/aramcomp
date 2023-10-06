package aramframework.com.cmm.com.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.BaseVO;
import aramframework.com.cmm.util.WebUtil;

/**
 * 파일정보 처리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class FileVO extends BaseVO {

	// domain
	/** 첨부파일 아이디 */
	private String atchFileId = "";

	/** 참조 아이디 */
	private String category = "";
	
	/** 생성일자	 */
	private String creatDt = "";

	/** 파일연번  */
	private int fileSn = 0;

	/** 파일저장경로 */
	private String fileStreCours = "";

	/** 저장파일명 */
	private String streFileNm = "";

	/** 원파일명	 */
	private String orignlFileNm = "";

	/** 파일확장자 */
	private String fileExtsn = "";

	/** 파일콘텐트타입 */
	private String fileType = "";

	/** 파일내용	 */
	private String fileCn = "";

	/** 파일크기	 */
	private long fileSize = 0;

	// domain
	/**
	 * atchFileId attribute를 리턴한다.
	 * 
	 * @return 	the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * 
	 * @param 	atchFileId	the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
		if(atchFileId != null && atchFileId.length() != 0)
			this.setPathId(WebUtil.getPathId(atchFileId));
	}

	/**
	 * category attribute를 리턴한다.
	 * 
	 * @return 	the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * category attribute 값을 설정한다.
	 * 
	 * @param 	category	the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * creatDt attribute를 리턴한다.
	 * 
	 * @return 	the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * creatDt attribute 값을 설정한다.
	 * 
	 * @param 	creatDt	the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	/**
	 * fileSn attribute를 리턴한다.
	 * 
	 * @return 	the fileSn
	 */
	public int getFileSn() {
		return fileSn;
	}
	/**
	 * fileSn attribute 값을 설정한다.
	 * 
	 * @param 	fileSn	the fileSn to set
	 */
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}

	/**
	 * fileStreCours attribute를 리턴한다.
	 * 
	 * @return 	the fileStreCours
	 */
	public String getFileStreCours() {
		return fileStreCours;
	}
	/**
	 * fileStreCours attribute 값을 설정한다.
	 * 
	 * @param 	fileStreCours	the fileStreCours to set
	 */
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	/**
	 * streFileNm attribute를 리턴한다.
	 * 
	 * @return 	the streFileNm
	 */
	public String getStreFileNm() {
		return streFileNm;
	}
	/**
	 * streFileNm attribute 값을 설정한다.
	 * 
	 * @param 	streFileNm	the streFileNm to set
	 */
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}

	/**
	 * orignlFileNm attribute를 리턴한다.
	 * 
	 * @return 	the orignlFileNm
	 */
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	/**
	 * orignlFileNm attribute 값을 설정한다.
	 * 
	 * @param 	orignlFileNm	the orignlFileNm to set
	 */
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	/**
	 * fileExtsn attribute를 리턴한다.
	 * 
	 * @return 	the fileExtsn
	 */
	public String getFileExtsn() {
		return fileExtsn;
	}
	/**
	 * fileExtsn attribute 값을 설정한다.
	 * 
	 * @param 	fileExtsn	the fileExtsn to set
	 */
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	/**
	 * fileType attribute를 리턴한다.
	 * 
	 * @return 	the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * fileType attribute 값을 설정한다.
	 * 
	 * @param 	fileType	the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * fileCn attribute를 리턴한다.
	 * 
	 * @return 	the fileCn
	 */
	public String getFileCn() {
		return fileCn;
	}
	/**
	 * fileCn attribute 값을 설정한다.
	 * 
	 * @param 	fileCn	the fileCn to set
	 */
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}

	/**
	 * fileSize attribute를 리턴한다.
	 * 
	 * @return 	the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * fileSize attribute 값을 설정한다.
	 * 
	 * @param 	fileSize	the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * toString 메소드를 대치한다.
	 * 
	 * @return 	String 
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
