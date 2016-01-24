package aramframework.com.cop.smt.wmr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cop.smt.wmr.service.WikMnthngReprtService;
import aramframework.com.cop.smt.wmr.service.WikMnthngReprtVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 주간월간보고에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("wikMnthngReprtService")
public class WikMnthngReprtServiceImpl extends EgovAbstractServiceImpl implements WikMnthngReprtService {

	@Autowired 
	private WikMnthngReprtMapper wikMnthngReprtMapper;	

	@Autowired 
	private EgovIdGnrService wikMnthngReprtIdGnrService; 

	@Autowired 
	private FileMngUtil fileUtil;

	/**
	 * 보고자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectReportrList(SearchVO searchVO) {
		return wikMnthngReprtMapper.selectReportrList(searchVO);
	}

	/**
	 * 보고자 총횟수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectReportrListCnt(SearchVO searchVO) {
		return wikMnthngReprtMapper.selectReportrListCnt(searchVO);
	}

	/**
	 * 사용자 직위명 정보를 조회한다.
	 * 
	 * @param wrterId
	 */
	public String selectWrterClsfNm(String wrterId) {
		return wikMnthngReprtMapper.selectWrterClsfNm(wrterId);
	}

	/**
	 * 주간월간보고 목록을 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public List<EgovMap> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO) {
		return wikMnthngReprtMapper.selectWikMnthngReprtList(wikMnthngReprtVO);
	}

	/**
	 * 주간월간보고 총횟수를 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public int selectWikMnthngReprtListCnt(WikMnthngReprtVO wikMnthngReprtVO) {
		return wikMnthngReprtMapper.selectWikMnthngReprtListCnt(wikMnthngReprtVO);
	}

	/**
	 * 주간월간보고 정보를 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public WikMnthngReprtVO selectWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) {

		WikMnthngReprtVO resultVo =wikMnthngReprtMapper.selectWikMnthngReprt(wikMnthngReprtVO);
		
		if (resultVo.getConfmDt() == null || resultVo.getConfmDt().equals("")) {
/*
			String year = resultVo.getFrstRegisterPnttm().substring(0, 4);
			String month = resultVo.getFrstRegisterPnttm().substring(5, 7);
			String day = resultVo.getFrstRegisterPnttm().substring(8, 10);
			String hour = resultVo.getFrstRegisterPnttm().substring(11, 13);
			String min = resultVo.getFrstRegisterPnttm().substring(14, 16);

			String yymmddhhmm = year + "/" + month + "/" + day + "  " + hour + "시 " + min + "분";
			resultVo.setReprtSttus("등록 (" + yymmddhhmm + ") ");
*/
		} else {
			String year = resultVo.getConfmDt().substring(0, 4);
			String month = resultVo.getConfmDt().substring(4, 6);
			String day = resultVo.getConfmDt().substring(6, 8);
			String hour = resultVo.getConfmDt().substring(8, 10);
			String min = resultVo.getConfmDt().substring(10, 12);

			String yymmddhhmm = year + "/" + month + "/" + day + "  " + hour + "시 " + min + "분";
			resultVo.setReprtSttus("승인 (" + yymmddhhmm + ") ");
		}
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, wikMnthngReprtVO); 
		return resultVo;
	}

	/**
	 * 주간월간보고 정보를 등록한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void insertWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) {
		try {
			wikMnthngReprtVO.setReprtId(wikMnthngReprtIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		wikMnthngReprtMapper.insertWikMnthngReprt(wikMnthngReprtVO);
	}

	/**
	 * 주간월간보고 정보를 수정한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void updateWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) {
		wikMnthngReprtMapper.updateWikMnthngReprt(wikMnthngReprtVO);
	}

	/**
	 * 주간월간보고 정보를 승인한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void confirmWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
		wikMnthngReprtVO.setConfmDt(formatter.format(new java.util.Date()));
		wikMnthngReprtMapper.confirmWikMnthngReprt(wikMnthngReprtVO);
	}

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void deleteWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(wikMnthngReprtVO.getAtchFileId());

		wikMnthngReprtMapper.deleteWikMnthngReprt(wikMnthngReprtVO);
	}
	
}