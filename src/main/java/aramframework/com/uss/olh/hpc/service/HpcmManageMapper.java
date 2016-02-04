package aramframework.com.uss.olh.hpc.service;

import java.util.List;

import aramframework.com.uss.olh.hpc.domain.HpcmManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 도움말을 처리하는 DAO 클래스
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
public interface HpcmManageMapper {

	/**
	 * 도움말 글 목록을 조회한다.
	 * 
	 * @param hpcmManageVO
	 */
	public List<EgovMap> selectHpcmList(HpcmManageVO hpcmManageVO);

	/**
	 * 도움말 글 총 갯수를 조회한다.
	 * 
	 * @param hpcmManageVO
	 */
	public int selectHpcmListCnt(HpcmManageVO hpcmManageVO);

	/**
	 * 도움말 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param hpcmManageVO
	 */
	public HpcmManageVO selectHpcmDetail(HpcmManageVO hpcmManageVO);

	/**
	 * 도움말 글을 등록한다.
	 * 
	 * @param hpcmManageVO
	 */
	public void insertHpcmCn(HpcmManageVO hpcmManageVO);

	/**
	 * 도움말 글을 수정한다.
	 * 
	 * @param hpcmManageVO
	 */
	public void updateHpcmCn(HpcmManageVO hpcmManageVO);

	/**
	 * 도움말 글을 삭제한다.
	 * 
	 * @param hpcmManageVO
	 */
	public void deleteHpcmCn(HpcmManageVO hpcmManageVO);

}
