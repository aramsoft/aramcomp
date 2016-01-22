package aramframework.com.uss.olp.cns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.uss.olp.cns.service.CnsltManageVO;
import aramframework.com.uss.olp.cns.service.CnsltManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 상담내용을 처리하는 구현 클래스
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
public class CnsltManageServiceImpl extends EgovAbstractServiceImpl implements CnsltManageService {

	@Autowired
	private CnsltManageMapper cnsltManageMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService cnsltManageIdGnrService; 

	@Autowired
	private FileMngUtil fileUtil;

	/**
	 * 상담내용 글 목록을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public List<EgovMap> selectCnsltList(CnsltManageVO cnsltManageVO) {
		return cnsltManageMapper.selectCnsltList(cnsltManageVO);
	}

	/**
	 * 상담내용 글 총 갯수를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public int selectCnsltListCnt(CnsltManageVO cnsltManageVO) {
		return cnsltManageMapper.selectCnsltListCnt(cnsltManageVO);
	}

	/**
	 * 상담내용 글을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public CnsltManageVO selectCnsltListDetail(CnsltManageVO cnsltManageVO) {
		CnsltManageVO resultVo = cnsltManageMapper.selectCnsltListDetail(cnsltManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cnsltManageVO); 
		return resultVo;
	}

	/**
	 * 상담내용 글을 수정한다.(조회수를 수정)
	 * 
	 * @param cnsltManageVO
	 */
	public void updateCnsltInqireCo(CnsltManageVO cnsltManageVO) {
		cnsltManageMapper.updateCnsltInqireCo(cnsltManageVO);
	}

	/**
	 * 상담내용 글을 등록한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void insertCnsltDtls(CnsltManageVO cnsltManageVO) {
		try {
			cnsltManageVO.setCnsltId(cnsltManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		cnsltManageMapper.insertCnsltDtls(cnsltManageVO);
	}

	/**
	 * 작성비밀번호를 확인한다.
	 * 
	 * @param cnsltManageVO
	 */
	public int selectCnsltPasswordConfirmCnt(CnsltManageVO cnsltManageVO) {
		return cnsltManageMapper.selectCnsltPasswordConfirmCnt(cnsltManageVO);
	}

	/**
	 * 상담내용 글을 수정한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void updateCnsltDtls(CnsltManageVO cnsltManageVO) {
		cnsltManageMapper.updateCnsltDtls(cnsltManageVO);
	}

	/**
	 * 상담내용 글을 삭제한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void deleteCnsltDtls(CnsltManageVO cnsltManageVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(cnsltManageVO.getAtchFileId());

		cnsltManageMapper.deleteCnsltDtls(cnsltManageVO);
	}

	/**
	 * 상담답변 글 목록을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public List<EgovMap> selectCnsltAnswerList(CnsltManageVO cnsltManageVO) {
		return cnsltManageMapper.selectCnsltAnswerList(cnsltManageVO);
	}

	/**
	 * 상담답변 글 총 갯수를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public int selectCnsltAnswerListCnt(CnsltManageVO cnsltManageVO) {
		return cnsltManageMapper.selectCnsltListCnt(cnsltManageVO);
	}

	/**
	 * 상담답변 글을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public CnsltManageVO selectCnsltAnswerDetail(CnsltManageVO cnsltManageVO) {
		CnsltManageVO resultVo = cnsltManageMapper.selectCnsltAnswerDetail(cnsltManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cnsltManageVO); 
		return resultVo;
	}

	/**
	 * 상담답변 글을 수정한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void updateCnsltDtlsAnswer(CnsltManageVO cnsltManageVO) {
		cnsltManageMapper.updateCnsltDtlsAnswer(cnsltManageVO);
	}

}
