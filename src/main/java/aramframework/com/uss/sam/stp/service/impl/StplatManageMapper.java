package aramframework.com.uss.sam.stp.service.impl;

import java.util.List;

import aramframework.com.uss.sam.stp.service.StplatManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 약관내용을 처리하는 DAO 클래스
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
public interface StplatManageMapper {

	/**
	 * 약관정보 글 목록을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	public List<EgovMap> selectStplatList(StplatManageVO stplatManageVO) ;

	/**
	 * 약관정보 글 총 갯수를 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	public int selectStplatListCnt(StplatManageVO stplatManageVO);

	/**
	 * 약관정보 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	public StplatManageVO selectStplatDetail(StplatManageVO stplatManageVO) ;

	/**
	 * 약관정보 글을 등록한다.
	 * 
	 * @param stplatManageVO
	 */
	public void insertStplat(StplatManageVO stplatManageVO) ;

	/**
	 * 약관정보 글을 수정한다.
	 * 
	 * @param stplatManageVO
	 */
	public void updateStplat(StplatManageVO stplatManageVO) ;

	/**
	 * 약관정보 글을 삭제한다.
	 * 
	 * @param stplatManageVO
	 */
	public void deleteStplat(StplatManageVO stplatManageVO) ;

}
