package aramframework.com.cop.smt.mrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cop.smt.mrm.domain.MemoReprtVO;
import aramframework.com.cop.smt.mrm.service.MemoReprtService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 메모보고에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("memoReprtService")
public class MemoReprtServiceImpl extends EgovAbstractServiceImpl implements MemoReprtService {

	@Autowired 
	private MemoReprtMapper memoReprtMapper;	

	@Autowired 
	private EgovIdGnrService memoReprtIdGnrService; 

	@Autowired 
	private FileMngUtil fileUtil;

	/**
	 * 보고자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectReportrList(SearchVO searchVO) {
		return memoReprtMapper.selectReportrList(searchVO);
	}

	/**
	 * 보고자 총횟수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectReportrListCnt(SearchVO searchVO) {
		return memoReprtMapper.selectReportrListCnt(searchVO);
	}

	/**
	 * 사용자 직위명을 정보를 조회한다.
	 * 
	 * @param wrterId
	 */
	public String selectWrterClsfNm(String wrterId) {
		return memoReprtMapper.selectWrterClsfNm(wrterId);
	}

	/**
	 * 메모보고 목록을 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	public List<EgovMap> selectMemoReprtList(MemoReprtVO memoReprtVO) {
		return memoReprtMapper.selectMemoReprtList(memoReprtVO);
	}

	/**
	 * 메모보고 총횟수를 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	public int selectMemoReprtListCnt(MemoReprtVO memoReprtVO) {
		return memoReprtMapper.selectMemoReprtListCnt(memoReprtVO);
	}

	/**
	 * 메모보고 정보를 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	public MemoReprtVO selectMemoReprt(MemoReprtVO memoReprtVO) {

		MemoReprtVO resultVo = memoReprtMapper.selectMemoReprt(memoReprtVO);

		if (resultVo.getReportrInqireDt() == null || resultVo.getReportrInqireDt().equals("")) {
			resultVo.setReprtSttus("미확인");
		} else {
			String year = resultVo.getReportrInqireDt().substring(0, 4);
			String month = resultVo.getReportrInqireDt().substring(4, 6);
			String day = resultVo.getReportrInqireDt().substring(6, 8);
			String hour = resultVo.getReportrInqireDt().substring(8, 10);
			String min = resultVo.getReportrInqireDt().substring(10, 12);

			String yymmddhhmm = year + "/" + month + "/" + day + "  " + hour + "시 " + min + "분";
			resultVo.setReprtSttus("확인 (" + yymmddhhmm + ") ");
		}
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, memoReprtVO); 
		return resultVo;
	}

	/**
	 * 메모보고 정보를 등록한다.
	 * 
	 * @param memoReprtVO
	 */
	public void insertMemoReprt(MemoReprtVO memoReprtVO) {
		try {
			memoReprtVO.setReprtId(memoReprtIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		memoReprtMapper.insertMemoReprt(memoReprtVO);
	}

	/**
	 * 메모보고 정보의 지시사항을 등록한다.
	 * 
	 * @param memoReprtVO
	 */
	public void updateMemoReprtDrctMatter(MemoReprtVO memoReprtVO) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
		memoReprtVO.setDrctMatterRegistDt(formatter.format(new java.util.Date()));
		memoReprtMapper.updateMemoReprtDrctMatter(memoReprtVO);
	}

	/**
	 * 메모보고 정보를 수정한다.
	 * 
	 * @param memoReprtVO
	 */
	public void updateMemoReprt(MemoReprtVO memoReprtVO) {
		memoReprtMapper.updateMemoReprt(memoReprtVO);
	}

	/**
	 * 메모보고 정보의 보고자 조회일시를 수정한다.
	 * 
	 * @param memoReprtVO
	 */
	public void readMemoReprt(MemoReprtVO memoReprtVO) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
		memoReprtVO.setReportrInqireDt(formatter.format(new java.util.Date()));
		memoReprtMapper.readMemoReprt(memoReprtVO);
	}

	/**
	 * 메모보고 정보를 삭제한다.
	 * 
	 * @param memoReprtVO
	 */
	public void deleteMemoReprt(MemoReprtVO memoReprtVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(memoReprtVO.getAtchFileId());

		memoReprtMapper.deleteMemoReprt(memoReprtVO);
	}

}