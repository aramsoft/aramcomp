package aramframework.com.utl.pao.service;

import aramframework.com.utl.pao.domain.PrntngOutptVO;

/**
 * 관인 처리 Util 클래스
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

public interface PrntngOutpt {

	/**
	 * 전자관인 정보를 제공하는 기능
	 */
	PrntngOutptVO selectErncsl(PrntngOutptVO prntngOutptVO);

}
