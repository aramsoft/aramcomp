package aramframework.com.ssi.syi.ims.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.ssi.syi.ims.service.CntcMessageItemVO;
import aramframework.com.ssi.syi.ims.service.CntcMessageVO;
import aramframework.com.ssi.syi.ims.service.CntcMessageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 연계메시지에 대한 서비스 구현클래스를 정의한다.
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

@Service("cntcMessageService")
public class CntcMessageServiceImpl extends EgovAbstractServiceImpl implements CntcMessageService {

	@Resource(name = "cntcMessageMapper")
	private CntcMessageMapper cntcMessageMapper;
	
	/** EgovIdGnrService */
	@Resource(name = "cntcMessageIdGnrService")
	private EgovIdGnrService idgenService;

	/** EgovIdGnrService */
	@Resource(name = "cntcMessageItemIdGnrService")
	private EgovIdGnrService idgenServiceItem;

	/**
	 * 연계메시지 목록을 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	public List<CntcMessageVO> selectCntcMessageList(CntcMessageVO cntcMessageVO) {
		return cntcMessageMapper.selectCntcMessageList(cntcMessageVO);
	}

	/**
	 * 연계메시지 총 갯수를 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	public int selectCntcMessageListCnt(CntcMessageVO cntcMessageVO) {
		return cntcMessageMapper.selectCntcMessageListCnt(cntcMessageVO);
	}

	/**
	 * 연계메시지 상세항목을 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	public CntcMessageVO selectCntcMessageDetail(CntcMessageVO cntcMessageVO) {
		CntcMessageVO resultVo = cntcMessageMapper.selectCntcMessageDetail(cntcMessageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cntcMessageVO); 
		return resultVo;
	}

	/**
	 * 연계메시지를 등록한다.
	 * 
	 * @param cntcMessageVO
	 */
	public void insertCntcMessage(CntcMessageVO cntcMessageVO) {
		try {
			cntcMessageVO.setCntcMessageId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		cntcMessageMapper.insertCntcMessage(cntcMessageVO);
	}

	/**
	 * 연계메시지를 수정한다.
	 * 
	 * @param cntcMessageVO
	 */
	public void updateCntcMessage(CntcMessageVO cntcMessageVO) {
		cntcMessageMapper.updateCntcMessage(cntcMessageVO);
	}

	/**
	 * 연계메시지를 삭제한다.
	 * 
	 * @param cntcMessageVO
	 */
	public void deleteCntcMessage(CntcMessageVO cntcMessageVO) {
		cntcMessageMapper.deleteCntcMessage(cntcMessageVO);
	}

	/**
	 * 연계메시지항목 목록을 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public List<EgovMap> selectCntcMessageItemList(CntcMessageItemVO cntcMessageItemVO) {
		return cntcMessageMapper.selectCntcMessageItemList(cntcMessageItemVO);
	}

	/**
	 * 연계메시지항목 총 갯수를 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public int selectCntcMessageItemListCnt(CntcMessageItemVO cntcMessageItemVO) {
		return cntcMessageMapper.selectCntcMessageItemListCnt(cntcMessageItemVO);
	}

	/**
	 * 연계메시지항목 상세항목을 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public CntcMessageItemVO selectCntcMessageItemDetail(CntcMessageItemVO cntcMessageItemVO) {
		CntcMessageItemVO resultVo = cntcMessageMapper.selectCntcMessageItemDetail(cntcMessageItemVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cntcMessageItemVO); 
		return resultVo;
	}

	/**
	 * 연계메시지 항목을 등록한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public void insertCntcMessageItem(CntcMessageItemVO cntcMessageItemVO) {
		try {
			cntcMessageItemVO.setItemId(idgenServiceItem.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		cntcMessageMapper.insertCntcMessageItem(cntcMessageItemVO);
	}

	/**
	 * 연계메시지 항목을 수정한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public void updateCntcMessageItem(CntcMessageItemVO cntcMessageItemVO) {
		cntcMessageMapper.updateCntcMessageItem(cntcMessageItemVO);
	}

	/**
	 * 연계메시지 항목을 삭제한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public void deleteCntcMessageItem(CntcMessageItemVO cntcMessageItemVO) {
		cntcMessageMapper.deleteCntcMessageItem(cntcMessageItemVO);
	}

}
