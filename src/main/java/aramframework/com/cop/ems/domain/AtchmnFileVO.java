package aramframework.com.cop.ems.domain;

/**
 * 발송메일에 첨부되는 파일 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class AtchmnFileVO {

	/** 첨부파일ID */
	private String atchFileId;

	/** 파일연번 */
	private String fileSn;

	/** 파일저장경로 */
	private String fileStreCours;

	/** 저장파일명 */
	private String streFileNm;

	/** 원파일명 */
	private String orignlFileNm;

	/** 파일확장자 */
	private String fileExtsn;

	/** 파일타입 */
	private String fileType;

	/** 파일크기 */
	private int fileSize;

	/**
	 * atchFileId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * 
	 * @param atchFileId
	 *            String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * fileSn attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getFileSn() {
		return fileSn;
	}
	/**
	 * fileSn attribute 값을 설정한다.
	 * 
	 * @param fileSn
	 *            String
	 */
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}

	/**
	 * fileStreCours attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getFileStreCours() {
		return fileStreCours;
	}
	/**
	 * fileStreCours attribute 값을 설정한다.
	 * 
	 * @param fileStreCours
	 *            String
	 */
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	/**
	 * streFileNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getStreFileNm() {
		return streFileNm;
	}
	/**
	 * streFileNm attribute 값을 설정한다.
	 * 
	 * @param streFileNm
	 *            String
	 */
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}

	/**
	 * orignlFileNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	/**
	 * orignlFileNm attribute 값을 설정한다.
	 * 
	 * @param orignlFileNm
	 *            String
	 */
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	/**
	 * fileExtsn attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getFileExtsn() {
		return fileExtsn;
	}
	/**
	 * fileExtsn attribute 값을 설정한다.
	 * 
	 * @param fileExtsn
	 *            String
	 */
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	/**
	 * fileType attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * fileType attribute 값을 설정한다.
	 * 
	 * @param fileType
	 *            String
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * fileSize attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * fileSize attribute 값을 설정한다.
	 * 
	 * @param fileSize
	 *            int
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
}
