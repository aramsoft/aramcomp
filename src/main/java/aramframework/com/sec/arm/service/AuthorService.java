package aramframework.com.sec.arm.service;

import java.util.List;

import aramframework.com.sec.arm.domain.AuthorVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한관리에 관한 서비스 인터페이스 클래스를 정의한다.
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

public interface AuthorService {
	/**
	 * 모든 권한목록을 조회한다.
	 * 
	 * @param authorVO
	 */
	public List<EgovMap> selectAuthorAllList(AuthorVO authorVO);

	/**
	 * 개별사용자에게 할당된 권한리스트 조회
	 * 
	 * @param authorVO
	 */
	public List<EgovMap> selectAuthorList(AuthorVO authorVO);

	/**
	 * 목록조회 카운트를 반환한다
	 * 
	 * @param authorVO
	 */
	public int selectAuthorListCnt(AuthorVO authorVO);

	/**
	 * 시스템 사용자중 불필요한 시스템권한정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param authorVO
	 */
	public void deleteAuthor(AuthorVO authorVO);

	/**
	 * 사용자의 시스테접근권한를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param authorVO
	 */
	public void insertAuthor(AuthorVO authorVO);

	/**
	 * 개별사용자에게 할당된 권한 조회
	 * 
	 * @param authorVO
	 */
	public AuthorVO selectAuthor(AuthorVO authorVO);

	/**
	 * 화면에 조회된 사용자권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param authorVO
	 */
	public void updateAuthor(AuthorVO authorVO);

}
