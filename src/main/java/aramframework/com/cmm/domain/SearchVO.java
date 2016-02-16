package aramframework.com.cmm.domain;

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

public class SearchVO {

	// search 관련
	/** 검색조건 */
	private String searchCondition = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색KeywordFrom */
	private String searchKeywordFrom = "";

	/** 검색KeywordTo */
	private String searchKeywordTo = "";

	// page 관련
	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 0;

	/** lastIndex */
	private int lastIndex = 0;

	/** recordPerPage */
	private int recordPerPage = 10;

	/** totalRecordCount */
	private int totalRecordCount = 0;

	// PaginationInfo 값을 채운다.
	public void fillPageInfo(PaginationInfo paginationInfo) {
		paginationInfo.setCurrentPageNo(this.pageIndex);
		paginationInfo.setRecordCountPerPage(this.recordPerPage);
		paginationInfo.setPageSize(this.pageSize);

		this.firstIndex = paginationInfo.getFirstRecordIndex();
		this.lastIndex = paginationInfo.getLastRecordIndex();
	}
	
	// 일정크기를 가져온다.
	public void setSizeAndOffset(int size, int offset) {
		this.recordPerPage = size;
		this.firstIndex = offset;
	}
	
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
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
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
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
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
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
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
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
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
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

}
