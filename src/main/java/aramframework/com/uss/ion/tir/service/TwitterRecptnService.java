package aramframework.com.uss.ion.tir.service;

import java.util.List;
import java.util.Map;

/**
 * 트위터수신을 처리하는 Service Class 구현
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

public interface TwitterRecptnService {

	/**
	 * 트위터 목록을 조회한다.
	 * 
	 * @param sTwitterId
	 *            -트위터 아이디
	 * @param sTwitterPw
	 *            -트위터 비밀번호
	 * @param nPageSize
	 *            -페이징 갯수
	 * @return List -조회 결과
	 * @throws Exception
	 *             -Exception Throws
	 */
	public List<TwitterInfoVO> twitterRecptnList(Map<String, String> map, int nPageSize) throws Exception;

}
