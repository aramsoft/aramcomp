package aramframework.com.sym.prm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 프로그램변경관리 처리를 위한 VO 클래스르를 정의한다
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ProgrmManageDtlVO extends BaseVO {

	// domain
	/** 프로그램파일명 */
	private String progrmFileNm;
	
	/** 요청번호 */
	private int rqestNo;
	
	/** 요청자ID */
	private String rqestPersonId;
	
	/** 변경요청내용 */
	private String changeRqestCn;
	
	/** 요청처리내용 */
	private String rqestProcessCn;

	/** 처리자ID */
	private String opetrId;
	
	/** 처리상태코드 */
	private String processSttus;
	
	/** 처리일자 */
	private String processDe;
	
	/** 요청일자 */
	private String rqestDe;
	
	/** 요청제목 */
	private String rqestSj;
	
	// helper
	/** 요청시작일자 */
	private String rqestDeBegin;
	
	/** 요청종료일자 */
	private String rqestDeEnd;

}