package aramframework.com.sec.dpt.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 부서관리VO클래스.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DeptVO extends BaseVO {

	// domain
	private String orgnztId;
	private String orgnztNm;
	private String orgnztDc;

}
