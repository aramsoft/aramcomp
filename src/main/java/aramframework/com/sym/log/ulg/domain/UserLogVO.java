package aramframework.com.sym.log.ulg.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사용자 로그관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserLogVO extends BaseVO {

	// domain
	/** 발생일자 */
	private String occrrncDe = "";
	
	/** 요청자아이디 */
	private String rqesterId = "";
	
	/** 서비스명 */
	private String srvcNm = "";
	
	/** 메서드명 */
	private String methodNm = "";
	
	/** 생성횟수 */
	private String creatCo = "";
	
	/** 수정횟수 */
	private String updtCo = "";
	
	/** 조회횟수 */
	private String rdCnt = "";
	
	/** 삭제횟수 */
	private String deleteCo = "";
	
	/** 출력횟수 */
	private String outptCo = "";
	
	/** 에러횟수 */
	private String errorCo = "";

	// helper	
	/** 요청자 이름 */
	private String rqesterNm = "";
	
	/** 검색시작일 */
	private String searchBgnDe = "";
	
	/** 검색종료일 */
	private String searchEndDe = "";

}
