package aramframework.com.sym.prm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 프로그램목록 처리를 위한 VO 클래스르를 정의한다
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ProgrmManageVO extends BaseVO {

	// domain
	/** 프로그램파일명 */
	private String progrmFileNm;
	
	/** 프로그램저장경로 */
	private String progrmStrePath;
	
	/** 프로그램한글명 */
	private String progrmKoreanNm;
	
	/** 프로그램설명 */
	private String progrmDc;
	
	/** URL */
	private String url;
	
	/** 커뮤니티 사용 여부 */
	private String cmmntyUseAt;

	// helper
	/** 커뮤니티 사용 여부 검색 */
	private String searchUseAt;

}