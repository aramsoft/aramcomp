package aramframework.com.cop.bbs.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.bbs.service.BoardUseInfVO;
import aramframework.com.cop.bbs.service.BBSUseInfoService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시판 이용정보를 관리하기 위한 서비스 구현 클래스
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

@Service("bbsUseInfoService")
public class BBSUseInfoServiceImpl extends EgovAbstractServiceImpl implements BBSUseInfoService {

	@Autowired 
	private BBSUseInfoMapper bbsUseInfoMapper;	

	/**
	 * 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public List<EgovMap> selectBBSUseInfs(BoardUseInfVO boardUseInfVO) {
		return bbsUseInfoMapper.selectBBSUseInfs(boardUseInfVO);
	}

	/**
	 * 게시판 사용정보 목록 총갯수을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public int selectBBSUseInfsCnt(BoardUseInfVO boardUseInfVO) {
		return bbsUseInfoMapper.selectBBSUseInfsCnt(boardUseInfVO);
	}

	/**
	 * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public List<EgovMap> selectBBSUseInfsByTrget(BoardUseInfVO boardUseInfVO) {
		return bbsUseInfoMapper.selectBBSUseInfsByTrget(boardUseInfVO);
	}

	/**
	 * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록 총갯수을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public int selectBBSUseInfsCntByTrget(BoardUseInfVO boardUseInfVO) {
		return bbsUseInfoMapper.selectBBSUseInfsCntByTrget(boardUseInfVO);
	}

	/**
	 * 게시판 사용정보에 대한 상세정보를 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public BoardUseInfVO selectBBSUseInf(BoardUseInfVO boardUseInfVO) {
		BoardUseInfVO resultVo = bbsUseInfoMapper.selectBBSUseInf(boardUseInfVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, boardUseInfVO); 
		return resultVo;
	}

	/**
	 * 게시판 사용정보에 대한 존재를 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public int existBBSUseInf(BoardUseInfVO boardUseInfVO) {
		return bbsUseInfoMapper.existBBSUseInf(boardUseInfVO);
	}

	/**
	 * 게시판 사용정보를 등록한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void insertBBSUseInf(BoardUseInfVO boardUseInfVO) {
		bbsUseInfoMapper.insertBBSUseInf(boardUseInfVO);
	}

	/**
	 * 게시판 사용정보를 수정한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void updateBBSUseInf(BoardUseInfVO boardUseInfVO) {
		bbsUseInfoMapper.updateBBSUseInf(boardUseInfVO);
	}

	/**
	 * 게시판 사용 정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteBBSUseInf(BoardUseInfVO boardUseInfVO) {
		bbsUseInfoMapper.deleteBBSUseInf(boardUseInfVO);
	}

	/**
	 * 커뮤니티에 사용되는 게시판 사용정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteBBSUseInfByCmmnty(BoardUseInfVO boardUseInfVO)  {

		List<EgovMap> result = bbsUseInfoMapper.selectBBSUseInfByCmmnty(boardUseInfVO);

		BoardUseInfVO bdUseInf = new BoardUseInfVO();
		Iterator<EgovMap> iter = result.iterator();
		EgovMap vo = null;
		while (iter.hasNext()) {
			vo = (EgovMap) iter.next();
			
			bdUseInf.setBbsId(vo.get("bbsId").toString());
			bdUseInf.setLastUpdusrId(boardUseInfVO.getLastUpdusrId());
			// bdUseInf.setTrgetId(bdUseVO.getCmmntyId()); // 사용자 ID를 넘겨야 함..
			bdUseInf.setTrgetId(boardUseInfVO.getTrgetId());

			bbsUseInfoMapper.deleteBBSUseInf(bdUseInf);
		}
	}

	/**
	 * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteAllBBSUseInfByCmmnty(BoardUseInfVO boardUseInfVO) {
		bbsUseInfoMapper.deleteAllBBSUseInfByCmmnty(boardUseInfVO);
	}

}
