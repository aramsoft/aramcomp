package aramframework.com.dam.mgm.dao;

import java.util.List;

import aramframework.com.dam.mgm.domain.KnoManagementVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식정보에 대한 DAO 클래스를 정의한다.
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface KnoManagementMapper {

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * 
	 * @param knoManagementVO
	 */
	public List<EgovMap> selectKnoManagementList(KnoManagementVO knoManagementVO);

	/**
	 * 지식정보 목록 총 갯수를 조회한다.
	 * 
	 * @param knoManagementVO
	 */
	public int selectKnoManagementListCnt(KnoManagementVO knoManagementVO);

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * 
	 * @param knoManagementVO
	 */
	public KnoManagementVO selectKnoManagement(KnoManagementVO knoManagementVO);
	
	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * 
	 * @param knoManagementVO
	 */
	public void insertKnoManagement(KnoManagementVO knoManagementVO);

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * 
	 * @param knoManagementVO
	 */
	public void updateKnoManagement(KnoManagementVO knoManagementVO);

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * 
	 * @param knoManagementVO
	 */
	public void deleteKnoManagement(KnoManagementVO knoManagementVO);

}