package aramframework.com.sec.grp.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 그룹관리에 대한 model 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class GroupVO extends BaseVO {
	
	// domain
	/** 그룹 ID */
	private String groupId;
	
	/** 그룹명 */
	private String groupNm;
	
	/** 그룹등록일시 */
	private String groupCreatDe;
	
	/** 그룹설명  */
	private String groupDc;

}