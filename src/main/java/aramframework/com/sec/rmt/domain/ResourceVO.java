package aramframework.com.sec.rmt.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 자원관리에 대한 model 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceVO extends BaseVO {

	// domain
	/** 자원코드 */
	private String resourceCode;
	
	/** 자원명 */
	private String resourceNm;
	
	/** 자원패턴 */
	private String resourcePttrn;
	
	/** 자원설명 */
	private String resourceDc;
	
	/** 자원타입 */
	private String resourceTy;
	
	/** 자원Sort */
	private String resourceSort;
	
	/** 자원등록일시 */
	private String resourceCreatDe;
	
}