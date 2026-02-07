package aramframework.com.uss.sam.stp.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 약관내용을 처리하는 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StplatManageVO extends BaseVO {

	// domain
	/** 이용약관 ID */
	private String useStplatId;

	/** 이용약관명 */
	private String useStplatNm;

	/** 이용약관내용 */
	private String useStplatCn;

	/** 정보제공동의내용 */
	private String infoProvdAgreCn;

}
