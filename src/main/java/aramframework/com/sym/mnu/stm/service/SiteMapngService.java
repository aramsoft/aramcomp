package aramframework.com.sym.mnu.stm.service;

import aramframework.com.cmm.SearchVO;

/**
 * 메뉴사이트맵에 관한 서비스 인터페이스 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

public interface SiteMapngService {

	/**
	 * 사이트맵 조회
	 * 
	 * @param searchVO
	 */
	SiteMapngVO selectSiteMapng(SearchVO searchVO);
	
}