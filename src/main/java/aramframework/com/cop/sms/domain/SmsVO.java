package aramframework.com.cop.sms.domain;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import aramframework.com.cmm.com.domain.BaseVO;

/**
 * 문자메시지 서비스를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class SmsVO extends BaseVO {

	// domain
	/** 문자메시지 ID */
	private String smsId = "";

	/** 전송 전화번호 */
	private String trnsmitTelno = "";

	/** 전송 내용 */
	private String trnsmitCn = "";

	// helper
	/** 유일 아이디 */
	private String userId = "";

	/** 수신 전화번호 개수 */
	private int recptnCnt = 0;

	/** 수신 정보 List */
	private List<EgovMap> recptn = null;

	/** 수전 전화번호 배열 */
	private String[] recptnTelno = null;

	// domain
	/**
	 * smsId attribute를 리턴한다.
	 * 
	 * @return the smsId
	 */
	public String getSmsId() {
		return smsId;
	}
	/**
	 * smsId attribute 값을 설정한다.
	 * 
	 * @param smsId
	 *            the smsId to set
	 */
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	/**
	 * trnsmitTelno attribute를 리턴한다.
	 * 
	 * @return the trnsmitTelno
	 */
	public String getTrnsmitTelno() {
		return trnsmitTelno;
	}
	/**
	 * trnsmitTelno attribute 값을 설정한다.
	 * 
	 * @param trnsmitTelno
	 *            the trnsmitTelno to set
	 */
	public void setTrnsmitTelno(String trnsmitTelno) {
		this.trnsmitTelno = trnsmitTelno;
	}

	/**
	 * trnsmitCn attribute를 리턴한다.
	 * 
	 * @return the trnsmitCn
	 */
	public String getTrnsmitCn() {
		return trnsmitCn;
	}
	/**
	 * trnsmitCn attribute 값을 설정한다.
	 * 
	 * @param trnsmitCn
	 *            the trnsmitCn to set
	 */
	public void setTrnsmitCn(String trnsmitCn) {
		this.trnsmitCn = trnsmitCn;
	}

	// helper
	/**
	 * userId attribute를 리턴한다.
	 * 
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * recptnCnt attribute를 리턴한다.
	 * 
	 * @return the recptnCnt
	 */
	public int getRecptnCnt() {
		return recptnCnt;
	}
	/**
	 * recptnCnt attribute 값을 설정한다.
	 * 
	 * @param recptnCnt
	 *            the recptnCnt to set
	 */
	public void setRecptnCnt(int recptnCnt) {
		this.recptnCnt = recptnCnt;
	}

	/**
	 * recptn attribute를 리턴한다.
	 * 
	 * @return the recptn
	 */
	public List<EgovMap> getRecptn() {
		return recptn;
	}
	/**
	 * recptn attribute 값을 설정한다.
	 * 
	 * @param recptn
	 *            the recptn to set
	 */
	public void setRecptn(List<EgovMap> recptn) {
		this.recptn = recptn;
	}

	/**
	 * recptnTelno attribute를 리턴한다.
	 * 
	 * @return the recptnTelno
	 */
	// public String[] getRecptnTelno() {
	// 		return recptnTelno;
	// }
	// 2011.10.07 private 배열을 public 함수가 반환되지 않도록 함
	public String[] getRecptnTelno() {
		// 메소드를 private으로 하거나, 복제본을 반환하거나,
		// 수정을 제어하는 public메소드를 별도로 만든다.
		String[] ret = null;
		if (this.recptnTelno != null) {
			ret = new String[recptnTelno.length];
			for (int i = 0; i < recptnTelno.length; i++) {
				ret[i] = this.recptnTelno[i];
			}
		}
		return ret;
	}

	/**
	 * recptnTelno attribute 값을 설정한다.
	 * 
	 * @param recptnTelno
	 *            the recptnTelno to set
	 */
	// public void setRecptnTelno(String[] recptnTelno) {
	// 		this.recptnTelno = recptnTelno;
	// }
	// 2011.10.07 private 배열-유형 필드에 공용 데이터 할당되지 않도록 함
	public void setRecptnTelno(String[] recptnTelno) {
		this.recptnTelno = new String[recptnTelno.length];
		for (int i = 0; i < recptnTelno.length; ++i)
			this.recptnTelno[i] = recptnTelno[i];
	}

}
