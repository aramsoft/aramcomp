package aramframework.com.sym.cal.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 휴일 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RestdeVO extends BaseVO {

	// domain
	/** 휴일번호 */
	private int restdeNo = 0;

	/** 휴일일자 */
	private String restdeDe = "";

	/** 휴일명 */
	private String restdeNm = "";

	/** 휴일설명 */
	private String restdeDc = "";

	/** 휴일구분코드 */
	private String restdeSeCode = "";

	// helper
	/** 휴일구분 */
	private String restdeSe = "";

	/** 년 */
	private String year = "";

	/** 월 */
	private String month = "";

	/** 일 */
	private String day = "";

	/** 휴일여부 */
	private String restdeAt = "";

	/** 달력셀 */
	private int cellNum = 0;

	/** 월별 주순위 */
	private int weeks = 0;

	/** 월 주수 */
	private int maxWeeks = 0;

	/** 요일 */
	private int week = 0;

	/** 시작요일 */
	private int startWeekMonth = 0;

	/** 마지막 일자 */
	private int lastDayMonth = 0;

}
