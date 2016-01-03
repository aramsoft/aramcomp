package aramframework.com.dam.app.service.impl;

import java.util.List;

import aramframework.com.dam.app.service.KnoAppraisalVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식정보평가에 대한 DAO 클래스를 정의한다.
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

@Mapper("knoAppraisalMapper")
public interface KnoAppraisalMapper {

	/**
	 * 등록된 지식정보평가 정보를 조회 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public List<EgovMap> selectKnoAppraisalList(KnoAppraisalVO knoAppraisalVO);

	/**
	 * 지식정보평가 목록 총 갯수를 조회한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public int selectKnoAppraisalListCnt(KnoAppraisalVO knoAppraisalVO);

	/**
	 * 지식정보평가 상세 정보를 조회 한다.
	 * 
	 * @param knoAppraisalVO
	 * 
	 */
	public KnoAppraisalVO selectKnoAppraisal(KnoAppraisalVO knoAppraisalVO);

	/**
	 * 지식정보평가 정보를 신규로 등록한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public void insertKnoAppraisal(KnoAppraisalVO knoAppraisalVO);

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public void updateKnoAppraisal(KnoAppraisalVO knoAppraisalVO);

	/**
	 * 기 등록된 지식정보평가 정보를 삭제한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public void deleteKnoAppraisal(KnoAppraisalVO knoAppraisalVO);

}