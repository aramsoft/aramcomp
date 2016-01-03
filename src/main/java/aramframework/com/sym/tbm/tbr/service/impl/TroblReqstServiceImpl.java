package aramframework.com.sym.tbm.tbr.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.tbm.tbr.service.TroblReqstService;
import aramframework.com.sym.tbm.tbr.service.TroblReqstVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 장애신청 정보에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("troblReqstService")
public class TroblReqstServiceImpl extends EgovAbstractServiceImpl implements TroblReqstService {

	@Resource(name = "troblReqstMapper")
	private TroblReqstMapper troblReqstMapper;	

	/** ID Generation */
	@Resource(name = "troblIdGnrService")
	private EgovIdGnrService idegenService;

	/**
	 * 장애요청을 관리하기 위해 등록된 장애요청목록을 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	public List<EgovMap> selectTroblReqstList(TroblReqstVO troblReqstVO) {
		return troblReqstMapper.selectTroblReqstList(troblReqstVO);
	}

	/**
	 * 장애요청목록 총 갯수를 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	public int selectTroblReqstListCnt(TroblReqstVO troblReqstVO) {
		return troblReqstMapper.selectTroblReqstListCnt(troblReqstVO);
	}

	/**
	 * 등록된 장애요청의 상세정보를 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	public TroblReqstVO selectTroblReqst(TroblReqstVO troblReqstVO) {
		TroblReqstVO resultVo = troblReqstMapper.selectTroblReqst(troblReqstVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, troblReqstVO,
				Arrays.asList(new String[] {"strTroblNm", "strTroblKnd", "strProcessSttus"}) ); 
		return resultVo;
	}

	/**
	 * 장애요청정보를 신규로 등록한다.
	 * 
	 * @param troblReqstVO
	 */
	public void insertTroblReqst(TroblReqstVO troblReqstVO) {
		try {
			troblReqstVO.setTroblId(idegenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		troblReqstMapper.insertTroblReqst(troblReqstVO);
	}

	/**
	 * 기 등록된 장애요청정보를 수정한다.
	 * 
	 * @param troblReqstVO
	 */
	public void updateTroblReqst(TroblReqstVO troblReqstVO) {
		troblReqstMapper.updateTroblReqst(troblReqstVO);
	}

	/**
	 * 기 등록된 장애요청정보를 삭제한다.
	 * 
	 * @param troblReqstVO
	 */
	public void deleteTroblReqst(TroblReqstVO troblReqstVO) {
		troblReqstMapper.deleteTroblReqst(troblReqstVO);
	}

	/**
	 * 장애처리를 요청한다.
	 * 
	 * @param troblReqstVO
	 */
	public void requstTroblReqst(TroblReqstVO troblReqstVO) {
		troblReqstMapper.requstTroblReqst(troblReqstVO);
	}
	
}