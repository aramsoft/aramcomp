package aramframework.com.cop.ems.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 발송메일 VO 클래스
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

public class SndngMailVO extends BaseVO {
	
	// domain
	/** 메세지ID */
	private String mssageId;

	/** 메일내용 */
	private String emailCn;

	/** 발신자 */
	private String dsptchPerson;

	/** 수신자 */
	private String recptnPerson;

	/** 제목 */
	private String sj;

	/** 발송결과코드 */
	private String sndngResultCode;

	/** 발신일자 */
	private String sndngDe;

	/** 첨부파일ID */
	private String atchFileId;

	// helper
	/** 메세지ID 리스트 */
	private String messageIdList;

	/** 첨부파일ID 리스트 */
	private String atchFileIdList;

	/** 발송요청XML내용 */
	private String xmlContent;

	/** 팝업링크여부(Y/N) */
	private String link;

	// domain
	/**
	 * mssageId attribute 를 리턴한다.
	 * @return String
	 */
	public String getMssageId() {
		return mssageId;
	}
	/**
	 * mssageId attribute 값을 설정한다.
	 * @param mssageId String
	 */
	public void setMssageId(String mssageId) {
		this.mssageId = mssageId;
	}

	/**
	 * emailCn attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmailCn() {
		return emailCn;
	}
	/**
	 * emailCn attribute 값을 설정한다.
	 * @param emailCn String
	 */
	public void setEmailCn(String emailCn) {
		this.emailCn = emailCn;
	}

	/**
	 * dsptchPerson attribute 를 리턴한다.
	 * @return String
	 */
	public String getDsptchPerson() {
		return dsptchPerson;
	}
	/**
	 * dsptchPerson attribute 값을 설정한다.
	 * @param dsptchPerson String
	 */
	public void setDsptchPerson(String dsptchPerson) {
		this.dsptchPerson = dsptchPerson;
	}

	/**
	 * recptnPerson attribute 를 리턴한다.
	 * @return String
	 */
	public String getRecptnPerson() {
		return recptnPerson;
	}
	/**
	 * recptnPerson attribute 값을 설정한다.
	 * @param recptnPerson String
	 */
	public void setRecptnPerson(String recptnPerson) {
		this.recptnPerson = recptnPerson;
	}

	/**
	 * sj attribute 를 리턴한다.
	 * @return String
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * sj attribute 값을 설정한다.
	 * @param sj String
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}

	/**
	 * sndngResultCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getSndngResultCode() {
		return sndngResultCode;
	}
	/**
	 * sndngResultCode attribute 값을 설정한다.
	 * @param sndngResultCode String
	 */
	public void setSndngResultCode(String sndngResultCode) {
		this.sndngResultCode = sndngResultCode;
	}

	/**
	 * sndngDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getSndngDe() {
		return sndngDe;
	}
	/**
	 * sndngDe attribute 값을 설정한다.
	 * @param sndngDe String
	 */
	public void setSndngDe(String sndngDe) {
		this.sndngDe = sndngDe;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * @param atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	// helper
	/**
	 * messageIdList attribute 를 리턴한다.
	 * @return String
	 */
	public String getMessageIdList() {
		return messageIdList;
	}
	/**
	 * messageIdList attribute 값을 설정한다.
	 * @param messageIdList String
	 */
	public void setMessageIdList(String messageIdList) {
		this.messageIdList = messageIdList;
	}

	/**
	 * atchFileIdList attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchFileIdList() {
		return atchFileIdList;
	}
	/**
	 * atchFileIdList attribute 값을 설정한다.
	 * @param atchFileIdList String
	 */
	public void setAtchFileIdList(String atchFileIdList) {
		this.atchFileIdList = atchFileIdList;
	}

	/**
	 * xmlContent attribute 를 리턴한다.
	 * @return String
	 */
	public String getXmlContent() {
		return xmlContent;
	}
	/**
	 * xmlContent attribute 값을 설정한다.
	 * @param xmlContent String
	 */
	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}

	/**
	 * link attribute 를 리턴한다.
	 * @return String
	 */
	public String getLink() {
		return link;
	}
	/**
	 * link attribute 값을 설정한다.
	 * @param link String
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
}
