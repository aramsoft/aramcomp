package aramframework.com.sec.arm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 권한별 롤 관리에 대한 model 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AuthorResourceVO extends BaseVO {

	// domain
	/** 권한코드 */
	private String authorCode;
	
	/** 롤코드 */
	private String resourceCode;
	
	/** 등록일자 */
	private String creatDt;

	// helper
	/** 롤명 */
	private String resourceNm;
	
	/** 롤 패턴 */
	private String resourcePttrn;
	
	/** 롤 설명 */
	private String resourceDc;
	
	/** 롤 타입 */
	private String resourceTy;
	
	/** 롤 순서정렬 */
	private String resourceSort;
	
	/** 롤 등록여부 */
	private String regYn;
	
	// 추가 검색 설정

	/** 검색Keyword */
	private String saveSearchKeyword;

	/** 현재페이지 */
	private int savePageIndex;
	
	/** 검색조건 */
	private int saveRecordPerPage;
	
}
