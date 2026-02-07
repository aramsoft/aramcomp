package aramframework.cmm.domain;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import lombok.Data;

/**
 * 조회 base VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class SearchVO {

	// search 관련
	/** 검색조건 */
	private String searchCondition = "";

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
	
}
