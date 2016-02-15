package aramframework.com.cop.smt.wmr.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 주간월간보고에 대한 Vo 클래스를 정의한다.
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

public class WikMnthngReprtVO extends BaseVO {

	// domain
	/** 보고서ID */
	private String reprtId;

	/** 보고서구분 */
	private String reprtSe;

	/** 보고서제목 */
	private String reprtSj;

	/** 보고일자 */
	private String reprtDe = "";

	/** 작성자ID */
	private String wrterId;

	/** 보고자ID */
	private String reportrId;

	/** 보고시작일자 */
	private String reprtBgnDe = "";

	/** 보고종료일자 */
	private String reprtEndDe = "";

	/** 금주보고내용 */
	private String reprtThswikCn;

	/** 차주보고내용 */
	private String reprtLesseeCn;

	/** 특이사항 */
	private String partclrMatter;

	/** 첨부파일ID */
	private String atchFileId;

	/** 승인일시 */
	private String confmDt;

	// helper
	/** 작성자명 */
	private String wrterNm;
	
	/*** 작성자직급명  */
	private String wrterClsfNm;

	/** 보고자명 */
	private String reportrNm;

	/** 보고자직급명 */
	private String reportrClsfNm;

	/*** 보고서상태 */
	private String reprtSttus;

	// 검색조건
	/** 사용자ID조회조건 */
	private String searchId = "";

	/** 일자 조회조건 */
	private String searchDe = "";

	/** 시작일자 조회조건 */
	private String searchBgnDe = "";

	/** 종료일자 조회조건 */
	private String searchEndDe = "";

	/** 주간/월간보고서 상태 조회조건 */
	private String searchSttus = "";

	/** 보고유형 조회조건 */
	private String searchSe = "";

	// domain
	public String getReprtId() {
		return reprtId;
	}
	public void setReprtId(String reprtId) {
		this.reprtId = reprtId;
	}

	public String getReprtSe() {
		return reprtSe;
	}
	public void setReprtSe(String reprtSe) {
		this.reprtSe = reprtSe;
	}

	public String getReprtSj() {
		return reprtSj;
	}
	public void setReprtSj(String reprtSj) {
		this.reprtSj = reprtSj;
	}

	public String getReprtDe() {
		return reprtDe;
	}
	public void setReprtDe(String reprtDe) {
		this.reprtDe = reprtDe;
	}

	public String getWrterId() {
		return wrterId;
	}
	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	public String getReportrId() {
		return reportrId;
	}
	public void setReportrId(String reportrId) {
		this.reportrId = reportrId;
	}

	public String getReprtBgnDe() {
		return reprtBgnDe;
	}
	public void setReprtBgnDe(String reprtBgnDe) {
		this.reprtBgnDe = reprtBgnDe;
	}

	public String getReprtEndDe() {
		return reprtEndDe;
	}
	public void setReprtEndDe(String reprtEndDe) {
		this.reprtEndDe = reprtEndDe;
	}

	public String getReprtThswikCn() {
		return reprtThswikCn;
	}
	public void setReprtThswikCn(String reprtThswikCn) {
		this.reprtThswikCn = reprtThswikCn;
	}

	public String getReprtLesseeCn() {
		return reprtLesseeCn;
	}
	public void setReprtLesseeCn(String reprtLesseeCn) {
		this.reprtLesseeCn = reprtLesseeCn;
	}

	public String getPartclrMatter() {
		return partclrMatter;
	}
	public void setPartclrMatter(String partclrMatter) {
		this.partclrMatter = partclrMatter;
	}

	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getConfmDt() {
		return confmDt;
	}
	public void setConfmDt(String confmDt) {
		this.confmDt = confmDt;
	}

	// helper
	public String getWrterNm() {
		return wrterNm;
	}
	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	public String getWrterClsfNm() {
		return wrterClsfNm;
	}
	public void setWrterClsfNm(String wrterClsfNm) {
		this.wrterClsfNm = wrterClsfNm;
	}

	public String getReportrNm() {
		return reportrNm;
	}
	public void setReportrNm(String reportrNm) {
		this.reportrNm = reportrNm;
	}

	public String getReportrClsfNm() {
		return reportrClsfNm;
	}
	public void setReportrClsfNm(String reportrClsfNm) {
		this.reportrClsfNm = reportrClsfNm;
	}

	public String getReprtSttus() {
		return reprtSttus;
	}
	public void setReprtSttus(String reprtSttus) {
		this.reprtSttus = reprtSttus;
	}

	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String getSearchDe() {
		return searchDe;
	}
	public void setSearchDe(String searchDe) {
		this.searchDe = searchDe;
	}

	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getSearchSttus() {
		return searchSttus;
	}
	public void setSearchSttus(String searchSttus) {
		this.searchSttus = searchSttus;
	}

	public String getSearchSe() {
		return searchSe;
	}
	public void setSearchSe(String searchSe) {
		this.searchSe = searchSe;
	}

}