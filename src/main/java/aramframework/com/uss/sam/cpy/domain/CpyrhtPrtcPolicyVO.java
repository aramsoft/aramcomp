package aramframework.com.uss.sam.cpy.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 저작권보호정책내용을 처리하는 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CpyrhtPrtcPolicyVO extends BaseVO {

	// domain
	/** 저작권 ID */
	private String cpyrhtId;

	/** 저작권보호정책내용 */
	private String cpyrhtPrtcPolicyCn;

}
