package aramframework.com.cop.smt.sim.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 일정관리 VO Class 구현
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SchdulManageVO extends BaseVO  {

	// domain
	/** 일정ID */
	private String schdulId;

	/** 일정구분(회의/교육/세미나/강의 기타) */
	private String schdulSe;

	/** 공개범위 */
	private String othbcScope;

	/** 일정시작일자 */
	private String schdulBgnde;

	/** 일정종료일자 */
	private String schdulEndde;

	/** 일정명 */
	private String schdulNm;

	/** 일정내용 */
	private String schdulCn;

	/** 일정장소 */
	private String schdulPlace;

	/** 일정중요도코드 */
	private String schdulIpcrCode;

	/** 일정담담자ID */
	private String schdulChargerId;

	/** 첨부파일ID */
	private String atchFileId;

	/** 반복구분(반복, 연속, 요일반복) */
	private String reptitSeCode;

	/** 대상 아이디 */
	private String trgetId = "";

	// helper
	/** 유저ID */
	private String userId;
	
	/** 담당자명 */
	private String schdulChargerName = "";

	/** 일정시작일자(시간) */
	private String schdulBgndeHH = "";

	/** 일정시작일자(분) */
	private String schdulBgndeMM = "";

	/** 일정종료일자(시간) */
	private String schdulEnddeHH = "";

	/** 일정종료일자(분) */
	private String schdulEnddeMM = "";

	/** 일정시작일자(Year/Month/Day) */
	private String schdulBgndeYYYMMDD = "";

	/** 일정종료일자(Year/Month/Day) */
	private String schdulEnddeYYYMMDD = "";

	// 페이지간 검색유지
	/** 검색일정구분 */
	private String searchSchdulSe = "";

	/** 년 */
	private String  year = "";

	/** 월 */
	private String  month = "";

	/** 주 */ 
	private String  week = "";
	
	/** 일 */
	private String  day = "";

	// IBatis 검색용
	/** 검색모드(월/주/일) */
	private String searchMode = "";
	
	/** 검색월 */
	private String searchMonth = "";

	/** 검색일 */
	private String searchDay = "";
	
	/** 검색시작일 */
	private String searchBgnde = "";
	
	/** 검색종료일 */
	private String searchEndde = "";
	
}
