package aramframework.com.dam.spe.req.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.dam.spe.req.dao.RequestOfferMapper;
import aramframework.com.dam.spe.req.domain.RequestOfferVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 지식정보제공/지식정보요청를 처리하는 ServiceImpl Class 구현
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
public class RequestOfferService extends EgovAbstractServiceImpl {

	@Autowired
	private RequestOfferMapper requestOfferMapper;

	/* RSS ID Generator Service */
	@Autowired
	private EgovIdGnrService requestOfferIdGnrService; 

	@Autowired
	private FileMngUtil fileUtil;

	/**
	 * 삭제시 하위 답변 건수를 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public int selectRequestOfferDelCnt(String ansParents) {
		return (Integer) requestOfferMapper.selectRequestOfferDelCnt(ansParents);
	}

	/**
	 * 등록된 지식전문가 건수를 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public boolean selectRequestOfferSpeCheck(String speId) {
		int nSpeCnt = (Integer) requestOfferMapper.selectRequestOfferSpeCnt(speId);
		return (nSpeCnt > 0) ? true: false;
	}

	/**
	 * 지식정보제공/지식정보요청를(을) 목록을 조회 한다.
	 * 
	 * @param requestOfferVO
	 */
	public List<EgovMap> selectRequestOfferList(RequestOfferVO requestOfferVO) {
		return requestOfferMapper.selectRequestOfferList(requestOfferVO);
	}

	/**
	 * 지식정보제공/지식정보요청를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public int selectRequestOfferListCnt(RequestOfferVO requestOfferVO) {
		return requestOfferMapper.selectRequestOfferListCnt(requestOfferVO);
	}

	/**
	 * 지식정보제공/지식정보요청를(을) 상세조회 한다.
	 * 
	 * @param requestOfferVO
	 */
	public RequestOfferVO selectRequestOffer(RequestOfferVO requestOfferVO) {
		RequestOfferVO resultVo = requestOfferMapper.selectRequestOffer(requestOfferVO);
		// searchVO 이전 
		resultVo.setSearchVO(requestOfferVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 지식정보제공/지식정보요청를(을) 등록한다.
	 * 
	 * @param requestOfferVO
	 */
	public void insertRequestOffer(RequestOfferVO requestOfferVO) {
		try {
			requestOfferVO.setKnoId(requestOfferIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}

		// 답글이 아닌경우 Parnts = 0, replyLc는 = 0, sortOrdr = nttNo(Query에서 처리)
		requestOfferMapper.insertRequestOffer(requestOfferVO);
	}
	/**
	 * 지식정보제공/지식정보요청를(을) 등록한다.
	 * 
	 * @param requestOfferVO
	 */
	public void replyRequestOffer(RequestOfferVO requestOfferVO) {
		try {
			requestOfferVO.setKnoId(requestOfferIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}

		requestOfferMapper.replyRequestOffer(requestOfferVO);
		// ----------------------------------------------------------
		// 현재 글 이후 게시물에 대한 NTT_NO를 증가 (정렬을 추가하기 위해)
		// ----------------------------------------------------------
		int threadNo = requestOfferMapper.getParentThreadNo(requestOfferVO);

		requestOfferVO.setThreadNo(threadNo);
		requestOfferMapper.updateOtherThreadNo(requestOfferVO);

		requestOfferVO.setThreadNo(threadNo + 1);
		requestOfferMapper.updateThreadNo(requestOfferVO);
	}

	/**
	 * 지식정보제공/지식정보요청를(을) 수정한다.
	 * 
	 * @param requestOfferVO
	 */
	public void updateRequestOffer(RequestOfferVO requestOfferVO) {
		requestOfferMapper.updateRequestOffer(requestOfferVO);
	}

	/**
	 * 지식정보제공/지식정보요청를(을) 삭제한다.
	 * 
	 * @param requestOfferVO
	 */
	public void deleteRequestOffer(RequestOfferVO requestOfferVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(requestOfferVO.getAtchFileId());

		requestOfferMapper.deleteRequestOffer(requestOfferVO);
	}

}
