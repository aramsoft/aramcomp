package aramframework.com.dam.per.service.impl;

import java.util.List;

import aramframework.com.dam.per.domain.KnoPersonalVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 개인지식정보에 대한 DAO 클래스를 정의한다.
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
public interface KnoPersonalMapper {

	/**
	 * 등록된 개인지식 정보를 조회 한다.
	 * 
	 * @param knoPersonalVO
	 */
	public List<EgovMap> selectKnoPersonalList(KnoPersonalVO knoPersonalVO);

	/**
	 * 개인지식 목록 총 갯수를 조회한다.
	 * 
	 * @param knoPersonalVO
	 */
	public int selectKnoPersonalListCnt(KnoPersonalVO knoPersonalVO);

	/**
	 * 개인지식정보 상세 정보를 조회 한다.
	 * 
	 * @param knoPersonalVO
	 */
	public KnoPersonalVO selectKnoPersonal(KnoPersonalVO knoPersonalVO);

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * 
	 * @param knoPersonalVO
	 */
	public void insertKnoPersonal(KnoPersonalVO knoPersonalVO);

	/**
	 * 기 등록 된 개인지식 정보를 수정 한다.
	 * 
	 * @param knoPersonalVO
	 */
	public void updateKnoPersonal(KnoPersonalVO knoPersonalVO);

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * 
	 * @param knoPersonalVO
	 */
	public void deleteKnoPersonal(KnoPersonalVO knoPersonalVO);

}