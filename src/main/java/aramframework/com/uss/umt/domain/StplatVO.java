package aramframework.com.uss.umt.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 가입약관VO클래스로서가입약관확인시 비지니스로직 처리용 항목을 구성한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StplatVO extends BaseVO {

	/** 약관아이디 */
	private String useStplatId;

	/** 사용약관안내 */
	private String useStplatCn;

	/** 정보동의안내 */
	private String infoProvdAgreCn;

}
