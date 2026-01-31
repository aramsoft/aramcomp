package aramframework.com.sec.rmt.dao;

import java.util.List;

import aramframework.com.sec.rmt.domain.ResourceVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 자원관리에 대한 DAO 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface ResourceMapper {

	/**
	 * 등록된 모든 자원 정보 목록 조회
	 * 
	 * @param resourceVO
	 */
	public List<EgovMap> selectResourceAllList(ResourceVO resourceVO);

	/**
	 * 등록된 자원 정보 목록 조회
	 * 
	 * @param resourceVO
	 */
	public List<EgovMap> selectResourceList(ResourceVO resourceVO);

	/**
	 * 자원목록 총 갯수를 조회한다.
	 * 
	 * @param resourceVO
	 */
	public int selectResourceListCnt(ResourceVO resourceVO);

	/**
	 * 등록된 자원 정보 조회
	 * 
	 * @param resourceVO
	 */
	public ResourceVO selectResource(ResourceVO resourceVO);

	/**
	 * 자원을 등록
	 * 
	 * @param resourceVO
	 */
	public void insertResource(ResourceVO resourceVO);

	/**
	 * 자원을 수정
	 * 
	 * @param resourceVO
	 */
	public void updateResource(ResourceVO resourceVO);

	/**
	 * 불필요한 자원정보 삭제
	 * 
	 * @param resourceVO
	 */
	public void deleteResource(ResourceVO resourceVO);

}