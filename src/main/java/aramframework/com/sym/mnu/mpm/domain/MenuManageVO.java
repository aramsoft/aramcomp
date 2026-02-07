package aramframework.com.sym.mnu.mpm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 메뉴목록관리 처리를 위한 VO 클래스르를 정의한다
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MenuManageVO extends BaseVO {

	// domain
	/** 메뉴번호 */
	private int menuNo;

	/** 상위메뉴번호 */
	private int upperMenuId;

	/** 메뉴명 */
	private String menuNm;

	/** 프로그램파일명 */
	private String progrmFileNm;

	/** 메뉴순서 */
	private int menuOrdr;

	/** 메뉴설명 */
	private String menuDc;

	/** 관련이미지경로 */
	private String relateImagePath;

	/** 관련이미지명 */
	private String relateImageNm;

	// helper
	/** 사이트맵 */
	/** 생성자ID **/
	private String creatPersonId;

	/** 권한정보설정 */
	/** 권한코드 */
	private String authorCode;

	/** Login 메뉴관련 VO변수 */
	/** tmpUserId */
	private String tmpUserId;

	/** tmp_Cmd */
	private String tmpCmd;

}