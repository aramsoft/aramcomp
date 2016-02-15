package aramframework.com.uss.ion.ntm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 쪽지 관리(보내기) Model and VO Class 구현
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

public class NoteManageVO extends BaseVO {

	// domain
	/** 쪽지 ID */
	private String noteId;

	/** 쪽지 제목 */
	private String noteSj;

	/** 쪽지 내용 */
	private String noteCn;

	/** 쪽지 첨부파일 아이디 */
	private String atchFileId;

	// helper
	/** 쪽지 송신 ID */
	private String noteTrnsmitId;

	/** 쪽지 수신 ID */
	private String noteRecptnId;

	/** 수신자 ID */
	private String rcverId;

	/** 개봉여부 */
	private String openYn;

	/** 수신구분 */
	private String recptnSe;

	/** 쪽지 발신자 */
	private String trnsmiterId;

	/** 쪽지 수신자 목록 */
	private String recptnEmpList;

	/** 쪽지 첨부파일 */
	private byte[] atchFile;

	// domain
	/**
	 * @return the noteId
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId
	 *            the noteId to set
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return the noteSj
	 */
	public String getNoteSj() {
		return noteSj;
	}
	/**
	 * @param noteSj
	 *            the noteSj to set
	 */
	public void setNoteSj(String noteSj) {
		this.noteSj = noteSj;
	}

	/**
	 * @return the noteCn
	 */
	public String getNoteCn() {
		return noteCn;
	}
	/**
	 * @param noteCn
	 *            the noteCn to set
	 */
	public void setNoteCn(String noteCn) {
		this.noteCn = noteCn;
	}

	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param atchFileId
	 *            the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	// helper
	/**
	 * @return the noteTrnsmitId
	 */
	public String getNoteTrnsmitId() {
		return noteTrnsmitId;
	}
	/**
	 * @param noteTrnsmitId
	 *            the noteTrnsmitId to set
	 */
	public void setNoteTrnsmitId(String noteTrnsmitId) {
		this.noteTrnsmitId = noteTrnsmitId;
	}

	/**
	 * @return the noteRecptnId
	 */
	public String getNoteRecptnId() {
		return noteRecptnId;
	}
	/**
	 * @param noteRecptnId
	 *            the noteRecptnId to set
	 */
	public void setNoteRecptnId(String noteRecptnId) {
		this.noteRecptnId = noteRecptnId;
	}

	/**
	 * @return the rcverId
	 */
	public String getRcverId() {
		return rcverId;
	}
	/**
	 * @param rcverId
	 *            the rcverId to set
	 */
	public void setRcverId(String rcverId) {
		this.rcverId = rcverId;
	}

	/**
	 * @return the openYn
	 */
	public String getOpenYn() {
		return openYn;
	}
	/**
	 * @param openYn
	 *            the openYn to set
	 */
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	/**
	 * @return the recptnSe
	 */
	public String getRecptnSe() {
		return recptnSe;
	}
	/**
	 * @param recptnSe
	 *            the recptnSe to set
	 */
	public void setRecptnSe(String recptnSe) {
		this.recptnSe = recptnSe;
	}

	/**
	 * @return the trnsmiterId
	 */
	public String getTrnsmiterId() {
		return trnsmiterId;
	}
	/**
	 * @param trnsmiterId
	 *            the trnsmiterId to set
	 */
	public void setTrnsmiterId(String trnsmiterId) {
		this.trnsmiterId = trnsmiterId;
	}

	/**
	 * @return the recptnEmpList
	 */
	public String getRecptnEmpList() {
		return recptnEmpList;
	}
	/**
	 * @param recptnEmpList
	 *            the recptnEmpList to set
	 */
	public void setRecptnEmpList(String recptnEmpList) {
		this.recptnEmpList = recptnEmpList;
	}

	/**
	 * @return the atchFile
	 */
	public byte[] getAtchFile() {
		return atchFile;
	}
	/**
	 * @param atchFile
	 *            the atchFile to set
	 */
	public void setAtchFile(byte[] atchFile) {
		this.atchFile = atchFile;
	}

}
