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
public class ZipAramVO extends BaseVO {

	private String zip;
	private String sn;
	private String ctprvnNm;
	private String signguNm;
	private String emdNm;
	private String liNm;
	private String doseoNm;
	private String sanYn;
	private String stBunjiM;
	private String stBunjiS;
	private String edBunjiM;
	private String edBunjiS;
	private String buldNm;
	private String stDong;
	private String edDong;
	private String fixDate;
	private String address;

}
