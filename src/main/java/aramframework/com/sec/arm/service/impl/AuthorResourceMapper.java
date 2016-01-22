package aramframework.com.sec.arm.service.impl;

import java.util.List;

import aramframework.com.sec.arm.service.AuthorResourceVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한별 롤관리에 대한 DAO 클래스를 정의한다.
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
public interface AuthorResourceMapper {

	/**
	 * 권한 롤 관계정보 목록 조회
	 * 
	 * @param authorResourceVO
	 */
	public List<EgovMap> selectAuthorResourceList(AuthorResourceVO authorResourceVO);

	/**
	 * 목록조회 카운트를 반환한다
	 * 
	 * @param authorResourceVO
	 */
	public int selectAuthorResourceListCnt(AuthorResourceVO authorResourceVO);

	/**
	 * 권한 롤 관계정보를 조회
	 * 
	 * @param authorResourceVO
	 */
	public AuthorResourceVO selectAuthorResource(AuthorResourceVO authorResourceVO);

	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param authorResourceVO
	 */
	public void insertAuthorResource(AuthorResourceVO authorResourceVO);

	/**
	 * 수정된 권한 롤 관계정보를 데이터베이스에 반영
	 * 
	 * @param authorResourceVO
	 */
	public void updateAuthorResource(AuthorResourceVO authorResourceVO);

	/**
	 * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param authorResourceVO
	 */
	public void deleteAuthorResource(AuthorResourceVO authorResourceVO);

}