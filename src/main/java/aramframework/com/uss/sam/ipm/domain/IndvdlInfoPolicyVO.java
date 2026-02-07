package aramframework.com.uss.sam.ipm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 개인정보보호정책 VO Class 구현
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class IndvdlInfoPolicyVO extends BaseVO {

	// domain
	/** 개인정보보호정책 아이디 */
	private String indvdlInfoId;

	/** 개인정보보호정책 명 */
	private String indvdlInfoNm;

	/** 개인정보보호정책 내용 */
	private String indvdlInfoDc;

	/** 개인정보보호정책 동의여부 */
	private String indvdlInfoYn;

}
