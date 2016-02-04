package aramframework.com.uss.ion.ism.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 약식결재관리에 대한 model 클래스를 정의한다.
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

public class InfrmlSanctnVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 약식결재ID */
	private String infrmlSanctnId;
	
	/** 업무구분코드 */
	private String jobSeCode;
	
	/** 업무구분명 */
	private String jobSeNm;
	
	/** 신청자ID */
	private String applcntId;
	
	/** 신청자명 */
	private String applcntNm;
	
	/** 신청일자 */
	private String reqstDe = "";
	
	/** 결재자ID */
	private String sanctnerId;
	
	/** 결재자명 */
	private String sanctnerNm;
	
	/** 결재자소속 */
	private String sanctnerOrgnztNm;
	
	/** 승인여부 */
	private String confmAt = "";
	
	/** 결재일시 */
	private String sanctnDt = "";
	
	/** 반려사유 */
	private String returnResn = "";
	
	public String getInfrmlSanctnId() {
		return infrmlSanctnId;
	}
	public void setInfrmlSanctnId(String infrmlSanctnId) {
		this.infrmlSanctnId = infrmlSanctnId;
	}
	
	public String getJobSeCode() {
		return jobSeCode;
	}
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	public String getJobSeNm() {
		return jobSeNm;
	}
	public void setJobSeNm(String jobSeNm) {
		this.jobSeNm = jobSeNm;
	}

	public String getApplcntId() {
		return applcntId;
	}
	public void setApplcntId(String applcntId) {
		this.applcntId = applcntId;
	}

	public String getApplcntNm() {
		return applcntNm;
	}
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}

	public String getReqstDe() {
		return reqstDe;
	}
	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}

	public String getSanctnerId() {
		return sanctnerId;
	}
	public void setSanctnerId(String sanctnerId) {
		this.sanctnerId = sanctnerId;
	}

	public String getSanctnerNm() {
		return sanctnerNm;
	}
	public void setSanctnerNm(String sanctnerNm) {
		this.sanctnerNm = sanctnerNm;
	}

	public String getSanctnerOrgnztNm() {
		return sanctnerOrgnztNm;
	}
	public void setSanctnerOrgnztNm(String sanctnerOrgnztNm) {
		this.sanctnerOrgnztNm = sanctnerOrgnztNm;
	}

	public String getConfmAt() {
		return confmAt;
	}
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	public String getSanctnDt() {
		return sanctnDt;
	}
	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}

	public String getReturnResn() {
		return returnResn;
	}
	public void setReturnResn(String returnResn) {
		this.returnResn = returnResn;
	}

}