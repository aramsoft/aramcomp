package aramframework.com.sec.grp.dao;

import java.util.List;

import aramframework.com.sec.grp.domain.GroupVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 그룹관리에 대한 DAO 클래스를 정의한다.
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
public interface GroupMapper {

	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * 
	 * @param groupVO
	 */
	public List<EgovMap> selectGroupList(GroupVO groupVO);

	/**
	 * 롤목록 총 갯수를 조회한다.
	 * 
	 * @param groupVO
	 */
	public int selectGroupListCnt(GroupVO groupVO);

	/**
	 * 검색조건에 따른 그룹정보를 조회
	 * 
	 * @param groupVO
	 */
	public GroupVO selectGroup(String groupId);

	/**
	 * 검색조건에 따른 그룹정보를 조회
	 * 
	 * @param groupVO
	 */
	public GroupVO selectGroup(GroupVO groupVO);

	/**
	 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param groupVO
	 */
	public void insertGroup(GroupVO groupVO);

	/**
	 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param groupVO
	 */
	public void updateGroup(GroupVO groupVO);

	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param groupVO
	 */
	public void deleteGroup(GroupVO groupVO);

}