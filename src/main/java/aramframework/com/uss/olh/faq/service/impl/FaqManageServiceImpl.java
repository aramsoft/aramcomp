package aramframework.com.uss.olh.faq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olh.faq.domain.FaqManageVO;
import aramframework.com.uss.olh.faq.service.FaqManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * FAQ를 처리하는 비즈니스 구현 클래스
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

@Service("faqManageService")
public class FaqManageServiceImpl extends EgovAbstractServiceImpl implements FaqManageService {

	@Autowired
	private FaqManageMapper faqManageMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService faqManageIdGnrService; 

	@Autowired
	private FileMngUtil fileUtil;

	/**
	 * FAQ 글 목록을 조회한다.
	 * 
	 * @param faqManageVO
	 */
	public List<EgovMap> selectFaqList(FaqManageVO faqManageVO) {
		return faqManageMapper.selectFaqList(faqManageVO);
	}

	/**
	 * FAQ 글 총 갯수를 조회한다.
	 * 
	 * @param faqManageVO
	 */
	public int selectFaqListCnt(FaqManageVO faqManageVO) {
		return faqManageMapper.selectFaqListCnt(faqManageVO);
	}

	/**
	 * FAQ 글을 조회한다.
	 * 
	 * @param faqManageVO
	 */
	public FaqManageVO selectFaqListDetail(FaqManageVO faqManageVO) {
		FaqManageVO resultVo = faqManageMapper.selectFaqListDetail(faqManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, faqManageVO); 
		return resultVo;
	}

	/**
	 * FAQ 글을 등록한다.
	 * 
	 * @param faqManageVO
	 */
	public void insertFaqCn(FaqManageVO faqManageVO) {
		try {
			faqManageVO.setFaqId(faqManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		faqManageMapper.insertFaqCn(faqManageVO);
	}

	/**
	 * FAQ 글을 수정한다.
	 * 
	 * @param faqManageVO
	 */
	public void updateFaqCn(FaqManageVO faqManageVO) {
		faqManageMapper.updateFaqCn(faqManageVO);
	}

	/**
	 * FAQ 조회수를 수정한다.
	 * 
	 * @param faqManageVO
	 */
	public void updateFaqInqireCo(FaqManageVO faqManageVO) {
		faqManageMapper.updateFaqInqireCo(faqManageVO);
	}

	/**
	 * FAQ 글을 삭제한다.
	 * 
	 * @param faqManageVO
	 */
	public void deleteFaqCn(FaqManageVO faqManageVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(faqManageVO.getAtchFileId());

		faqManageMapper.deleteFaqCn(faqManageVO);
	}

}
