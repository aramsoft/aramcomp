package aramframework.com.cmm;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 조회 base VO 클래스
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

public class SearchVO implements Serializable {

	private static final long serialVersionUID = -6062858939907510631L;

	// rest 관련
	// path id for rest
	protected String pathId = "";
	
	// search 관련
	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordPerPage */
	private int recordPerPage = 10;

	/** totalRecordCount */
	private int totalRecordCount = 0;

	/** 검색KeywordFrom */
	private String searchKeywordFrom = "";

	/** 검색KeywordTo */
	private String searchKeywordTo = "";

	// DB table 관련
	/** 최초등록자 아이디 */
	private String frstRegisterId = "";

	/** 최초 등록자명 */
	private String frstRegisterNm = "";

	/** 최초등록시점 */
	private Date frstRegisterPnttm = null;

	/** 최종수정자 아이디 */
	private String lastUpdusrId = "";

	/** 최종 수정자명 */
	private String lastUpdusrNm = "";

	/** 최종수정시점 */
	private Date lastUpdusrPnttm = null;

	// PaginationInfo 값을 채운다.
	public void fillPageInfo(PaginationInfo paginationInfo) {
		paginationInfo.setCurrentPageNo(this.pageIndex);
		paginationInfo.setRecordCountPerPage(this.recordPerPage);
		paginationInfo.setPageSize(this.pageSize);

		this.firstIndex = paginationInfo.getFirstRecordIndex();
		this.lastIndex = paginationInfo.getLastRecordIndex();
	}
	
	/**
	 * pathId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getPathId() {
		return pathId;
	}
	/**
	 * pathId attribute 값을 설정한다.
	 * 
	 * @param pathId
	 *            String
	 */
/*
	@XmlTransient 
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
*/
	/**
	 * searchCondition attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSearchCondition() {
		return searchCondition;
	}
	/**
	 * searchCondition attribute 값을 설정한다.
	 * 
	 * @param searchCondition
	 *            String
	 */
	@XmlTransient 
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * searchKeyword attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}
	/**
	 * searchKeyword attribute 값을 설정한다.
	 * 
	 * @param searchKeyword
	 *            String
	 */
	@XmlTransient 
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * searchUseYn attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * searchUseYn attribute 값을 설정한다.
	 * 
	 * @param searchUseYn
	 *            String
	 */
	@XmlTransient 
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * pageIndex attribute 값을 리턴한다.
	 * 
	 * @return int
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * pageIndex attribute 값을 설정한다.
	 * 
	 * @param pageIndex
	 *            int
	 */
	@XmlTransient 
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * pageSize attribute 값을 리턴한다.
	 * 
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * pageSize attribute 값을 설정한다.
	 * 
	 * @param pageSize
	 *            int
	 */
	@XmlTransient 
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * firstIndex attribute 값을 리턴한다.
	 * 
	 * @return int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}
	/**
	 * firstIndex attribute 값을 설정한다.
	 * 
	 * @param firstIndex
	 *            int
	 */
	@XmlTransient 
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * lastIndex attribute 값을 리턴한다.
	 * 
	 * @return int
	 */
	public int getLastIndex() {
		return lastIndex;
	}
	/**
	 * lastIndex attribute 값을 설정한다.
	 * 
	 * @param lastIndex
	 *            int
	 */
	@XmlTransient 
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordPerPage attribute 값을 리턴한다.
	 * 
	 * @return int
	 */
	public int getRecordPerPage() {
		return recordPerPage;
	}
	/**
	 * recordPerPage attribute 값을 설정한다.
	 * 
	 * @param recordPerPage
	 *            int
	 */
	@XmlTransient 
	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	
	/**
	 * totalRecordCount attribute 값을 리턴한다.
	 * 
	 * @return int
	 */
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	/**
	 * totalRecordCount attribute 값을 설정한다.
	 * 
	 * @param totalRecordCount
	 *            int
	 */
	@XmlTransient 
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	/**
	 * searchKeywordFrom attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}
	/**
	 * searchKeywordFrom attribute 값을 설정한다.
	 * 
	 * @param searchKeywordFrom
	 *            String
	 */
	@XmlTransient 
	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
	}

	/**
	 * searchKeywordTo attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}
	/**
	 * searchKeywordTo attribute 값을 설정한다.
	 * 
	 * @param searchKeywordTo
	 *            String
	 */
	@XmlTransient 
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}

	/**
	 * frstRegisterId attribute를 리턴한다.
	 * 
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * 
	 * @param frstRegisterId
	 *            the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegisterNm attribute를 리턴한다.
	 * 
	 * @return the frstRegisterNm
	 */
	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}
	/**
	 * frstRegisterNm attribute 값을 설정한다.
	 * 
	 * @param frstRegisterNm
	 *            the frstRegisterNm to set
	 */
	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	/**
	 * frstRegisterPnttm attribute를 리턴한다.
	 * 
	 * @return the frstRegisterPnttm
	 */
	public Date getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * 
	 * @param frstRegisterPnttm
	 *            the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(Date frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * lastUpdusrId attribute를 리턴한다.
	 * 
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * 
	 * @param AdbkNm
	 *            the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdusrNm attribute를 리턴한다.
	 * 
	 * @return the lastUpdusrNm
	 */
	public String getLastUpdusrNm() {
		return lastUpdusrNm;
	}
	/**
	 * lastUpdusrNm attribute 값을 설정한다.
	 * 
	 * @param lastUpdusrNm
	 *            the lastUpdusrNm to set
	 */
	public void setLastUpdusrNm(String lastUpdusrNm) {
		this.lastUpdusrNm = lastUpdusrNm;
	}

	/**
	 * lastUpdusrPnttm attribute를 리턴한다.
	 * 
	 * @return the lastUpdusrPnttm
	 */
	public Date getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * 
	 * @param lastUpdusrPnttm
	 *            the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(Date lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
