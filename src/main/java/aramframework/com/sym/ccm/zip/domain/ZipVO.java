package aramframework.com.sym.ccm.zip.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 우편번호 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ZipVO extends BaseVO {

	// domain
	/** 우편번호 */
	private String zip = "";

	/** 일련번호 */
	private int sn = 0;

	/** 시도명 */
	private String ctprvnNm = "";

	/** 시군구명 */
	private String signguNm = "";

	/** 읍면동명 */
	private String emdNm = "";

	/** 리건물명 */
	private String liBuldNm = "";

    /** 번지동호 */
	private String lnbrDongHo = "";

}
