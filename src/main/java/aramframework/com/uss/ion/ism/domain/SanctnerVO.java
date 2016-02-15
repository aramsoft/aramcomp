package aramframework.com.uss.ion.ism.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 결재자에 대한 Vo 클래스를 정의한다.
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

public class SanctnerVO extends BaseVO {

	/** 조직명	 */
	private String orgnztNm;
	
	/** 직위명	 */
	private String ofcpsNm;
	
	/** 사용자명	 */
	private String emplyrNm;
	
	/** 사용자ID	 */
	private String uniqId;
	
	/** 사원번호	 */
	private String emplNo;

	public String getOrgnztNm() {
		return orgnztNm;
	}

	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	public String getOfcpsNm() {
		return ofcpsNm;
	}

	public void setOfcpsNm(String ofcpsNm) {
		this.ofcpsNm = ofcpsNm;
	}

	public String getEmplyrNm() {
		return emplyrNm;
	}

	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}

	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	public String getEmplNo() {
		return emplNo;
	}

	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}

}