package aramframework.com.cop.smt.dsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.smt.dsm.dao.DiaryManageMapper;
import aramframework.com.cop.smt.dsm.domain.DiaryManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 일지관리를 처리하는 ServiceImpl Class 구현
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

@Service
public class DiaryManageService extends EgovAbstractServiceImpl {

	@Autowired 
	private DiaryManageMapper diaryManageMapper;	

	@Autowired 
	private EgovIdGnrService diaryManageIdGnrService; 

	@Autowired 
	private FileMngUtil fileUtil;

	/**
	 * 일지관리 목록를(을) 조회한다.
	 * 
	 * @param diaryManageVO
	 */
	public List<EgovMap> selectDiaryManageList(DiaryManageVO diaryManageVO) {
		return  diaryManageMapper.selectDiaryManageList(diaryManageVO);
	}

	/**
	 * 일지관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param diaryManageVO
	 */
	public int selectDiaryManageListCnt(DiaryManageVO diaryManageVO) {
		return (Integer) diaryManageMapper.selectDiaryManageListCnt(diaryManageVO);
	}

	/**
	 * 일지관리를(을) 상세조회 한다.
	 * 
	 * @param diaryManageVO
	 */
	public DiaryManageVO selectDiaryManageDetail(DiaryManageVO diaryManageVO) {
		DiaryManageVO resultVo = diaryManageMapper.selectDiaryManageDetail(diaryManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, diaryManageVO); 
		return resultVo;
	}

	/**
	 * 일지관리를(을) 등록한다.
	 * 
	 * @param diaryManageVO
	 */
	public void insertDiaryManage(DiaryManageVO diaryManageVO) {
		try {
			diaryManageVO.setDiaryId(diaryManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		diaryManageMapper.insertDiaryManage(diaryManageVO);
	}

	/**
	 * 일지관리를(을) 수정한다.
	 * 
	 * @param diaryManageVO
	 */
	public void updateDiaryManage(DiaryManageVO diaryManageVO) {
		diaryManageMapper.updateDiaryManage(diaryManageVO);
	}

	/**
	 * 일지관리를(을) 삭제한다.
	 * 
	 * @param diaryManageVO
	 */
	public void deleteDiaryManage(DiaryManageVO diaryManageVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(diaryManageVO.getAtchFileId());

		diaryManageMapper.deleteDiaryManage(diaryManageVO);
	}
	
}
