package aramframework.com.sym.log.lgm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 시스템 로그를 위한 VO 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysLogVO extends BaseVO {

	// domain
	/** 요청아이디 */
	private String requstId = "";
	
	/** 업무구분코드 */
	private String jobSeCode = "";

	/** 기관코드 */
	private String insttCode = "";
	
	/** 발생일자 */
	private String occrrncDe = "";
	
	/** 요청아이피 */
	private String rqesterIp = "";
	
	/** 요청자아이디 */
	private String rqesterId = "";
	
	/** 대상메뉴명 */
	private String trgetMenuNm = "";
	
	/** 서비스명 */
	private String srvcNm = "";
	
	/** 메서드명 */
	private String methodNm = "";
	
	/** 처리구분코드 */
	private String processSeCode = "";
	
	/** 처리횟수 */
	private int processCo = 0;
	
	/** 처리시간 */
	private String processTime = "";
	
	/** 응답코드 */
	private String rspnsCode = "";
	
	/** 에러구분 */
	private String errorSe = "";
	
	/** 에러횟수 */
	private int errorCo = 0;
	
	/** 에러코드 */
	private String errorCode = "";
	
	// helper
	/** 에러코드 명 */
	private String errorCodeNm = "";
	
	/** 기관코드 명 */
	private String insttCodeNm = "";
	
	/** 업무구분코드명 */
	private String jobSeCodeNm = "";
	
	/** 처리구분코드명 */
	private String processSeCodeNm = "";
	
	/** 요청자 이름 */
	private String rqesterNm = "";
	
	/** 응답코드 명 */
	private String rspnsCodeNm = "";
	
	/** 검색시작일 */
	private String searchBgnDe = "";
	
	/** 검색종료일 */
	private String searchEndDe = "";

}
