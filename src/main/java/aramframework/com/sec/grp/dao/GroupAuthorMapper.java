package aramframework.com.sec.grp.dao;

import java.util.List;

import aramframework.com.sec.grp.domain.GroupAuthorVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한그룹에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface GroupAuthorMapper {

	/**
	 * 그룹별 할당된 권한 목록 조회
	 * 
	 * @param groupAuthorVO
	 */
	public List<EgovMap> selectGroupAuthorList(GroupAuthorVO groupAuthorVO);

	/**
	 * 그룹권한목록 총 갯수를 조회한다.
	 * 
	 * @param groupAuthorVO
	 */
	public int selectGroupAuthorListCnt(GroupAuthorVO groupAuthorVO);

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * 
	 * @param groupAuthorVO
	 */
	public void insertGroupAuthor(GroupAuthorVO groupAuthorVO);

	/**
	 * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param groupAuthorVO
	 */
	public void updateGroupAuthor(GroupAuthorVO groupAuthorVO);

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * 
	 * @param groupAuthorVO
	 */
	public void deleteGroupAuthor(GroupAuthorVO groupAuthorVO);

}