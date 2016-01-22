package aramframework.com.sec.arm.service.impl;

import java.util.List;

import aramframework.com.sec.arm.service.AuthorVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한관리에 대한 DAO 클래스를 정의한다.
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

@Mapper
public interface AuthorMapper {

	/**
	 * 모든 권한목록을 조회한다.
	 * 
	 * @param authorVO
	 */
	public List<EgovMap> selectAuthorAllList(AuthorVO authorVO);

	/**
	 * 권한목록을 조회한다.
	 * 
	 * @param authorVO
	 */
	public List<EgovMap> selectAuthorList(AuthorVO authorVO);

	/**
	 * 권한목록 총 갯수를 조회한다.
	 * 
	 * @param authorVO
	 */
	public int selectAuthorListCnt(AuthorVO authorVO);

	/**
	 * 권한을 조회한다.
	 * 
	 * @param authorVO
	 */
	public AuthorVO selectAuthor(AuthorVO authorVO);

	/**
	 * 권한을 등록한다.
	 * 
	 * @param authorVO
	 */
	public void insertAuthor(AuthorVO authorVO);

	/**
	 * 권한을 수정한다.
	 * 
	 * @param authorVO
	 */
	public void updateAuthor(AuthorVO authorVO);

	/**
	 * 권한을 삭제한다.
	 * 
	 * @param authorVO
	 */
	public void deleteAuthor(AuthorVO authorVO);

}
