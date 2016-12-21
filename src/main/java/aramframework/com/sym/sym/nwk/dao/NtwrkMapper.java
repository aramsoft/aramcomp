package aramframework.com.sym.sym.nwk.dao;

import java.util.List;

import aramframework.com.sym.sym.nwk.domain.NtwrkVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 네트워크에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface NtwrkMapper {

	/**
	 * 네트워크를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	public List<EgovMap> selectNtwrkList(NtwrkVO ntwrkVO);

	/**
	 * 네트워크목록 총 갯수를 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	public int selectNtwrkListCnt(NtwrkVO ntwrkVO);

	/**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	public NtwrkVO selectNtwrk(NtwrkVO ntwrkVO);

	/**
	 * 네트워크정보를 신규로 등록한다.
	 * 
	 * @param ntwrkVO
	 */
	public void insertNtwrk(NtwrkVO ntwrkVO);

	/**
	 * 기 등록된 네트워크정보를 수정한다.
	 * 
	 * @param ntwrkVO
	 */
	public void updateNtwrk(NtwrkVO ntwrkVO);

	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 * 
	 * @param ntwrkVO
	 */
	public void deleteNtwrk(NtwrkVO ntwrkVO);

}
