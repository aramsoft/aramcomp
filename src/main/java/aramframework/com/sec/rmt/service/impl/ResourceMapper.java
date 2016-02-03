package aramframework.com.sec.rmt.service.impl;

import java.util.List;

import aramframework.com.sec.rmt.domain.ResourceVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 롤관리에 대한 DAO 클래스를 정의한다.
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
public interface ResourceMapper {

	/**
	 * 등록된 모든 롤 정보 목록 조회
	 * 
	 * @param resourceVO
	 */
	public List<EgovMap> selectResourceAllList(ResourceVO resourceVO);

	/**
	 * 등록된 롤 정보 목록 조회
	 * 
	 * @param resourceVO
	 */
	public List<EgovMap> selectResourceList(ResourceVO resourceVO);

	/**
	 * 롤목록 총 갯수를 조회한다.
	 * 
	 * @param resourceVO
	 */
	public int selectResourceListCnt(ResourceVO resourceVO);

	/**
	 * 등록된 롤 정보 조회
	 * 
	 * @param resourceVO
	 */
	public ResourceVO selectResource(ResourceVO resourceVO);

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
	 * 
	 * @param resourceVO
	 */
	public void insertResource(ResourceVO resourceVO);

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
	 * 
	 * @param resourceVO
	 */
	public void updateResource(ResourceVO resourceVO);

	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param resourceVO
	 */
	public void deleteResource(ResourceVO resourceVO);

}