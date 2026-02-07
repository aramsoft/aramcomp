package aramframework.com.cmm.com.domain;

import lombok.Data;

/**
 * IncludedInfo annotation을 바탕으로 화면에 표시할 정보를 구성하기 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class IncludedInfoVO {

	private String name;
	private String listUrl;
	private int order;
	private int gid;

}
