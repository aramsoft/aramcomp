package aramframework.com.uss.olp.qtm.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 설문템플릿 VO Class 구현
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

public class QustnrTmplatManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 설문템플릿 아이디 */
	private String qestnrTmplatId = "";

	/** 설문템플릿 유형 */
	private String qestnrTmplatTy = "";

	/** 설문템플 이미지내용 */
	private byte[] qestnrTmplatImageInfo;

	/** 설문템플릿 설명 */
	private String qestnrTmplatCn = "";

	/** 설문템플릿경로명 */
	private String qestnrTmplatCours;

	/**
	 * qestnrTmplatId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrTmplatId() {
		return qestnrTmplatId;
	}
	/**
	 * qestnrTmplatId attribute 값을 설정한다.
	 * 
	 * @return qestnrTmplatId String
	 */
	public void setQestnrTmplatId(String qestnrTmplatId) {
		this.qestnrTmplatId = qestnrTmplatId;
	}

	/**
	 * qestnrTmplatTy attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrTmplatTy() {
		return qestnrTmplatTy;
	}
	/**
	 * qestnrTmplatTy attribute 값을 설정한다.
	 * 
	 * @return qestnrTmplatTy String
	 */
	public void setQestnrTmplatTy(String qestnrTmplatTy) {
		this.qestnrTmplatTy = qestnrTmplatTy;
	}

	/**
	 * qestnrTmplatImageInfo attribute 를 리턴한다.
	 * 
	 * @return the byte[]
	 */
	public byte[] getQestnrTmplatImageInfo() {
		return qestnrTmplatImageInfo;
	}
	/**
	 * qestnrTmplatImagepathnm attribute 값을 설정한다.
	 * 
	 * @return qestnrTmplatImageInfo byte[]
	 */
	public void setQestnrTmplatImageInfo(byte[] qestnrTmplatImageInfo) {
		this.qestnrTmplatImageInfo = qestnrTmplatImageInfo;
	}

	/**
	 * qestnrTmplatCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrTmplatCn() {
		return qestnrTmplatCn;
	}
	/**
	 * qestnrTmplatCn attribute 값을 설정한다.
	 * 
	 * @return qestnrTmplatCn String
	 */
	public void setQestnrTmplatCn(String qestnrTmplatCn) {
		this.qestnrTmplatCn = qestnrTmplatCn;
	}

	/**
	 * qestnrTmplatCours attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrTmplatCours() {
		return qestnrTmplatCours;
	}
	/**
	 * qestnrTmplatCours attribute 값을 설정한다.
	 * 
	 * @return qestnrTmplatCours String
	 */
	public void setQestnrTmplatCours(String qestnrTmplatCours) {
		this.qestnrTmplatCours = qestnrTmplatCours;
	}

}
