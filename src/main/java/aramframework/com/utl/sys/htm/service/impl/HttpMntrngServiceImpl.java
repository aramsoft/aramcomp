package aramframework.com.utl.sys.htm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.utl.sys.htm.domain.HttpMntrngLogVO;
import aramframework.com.utl.sys.htm.domain.HttpMntrngVO;
import aramframework.com.utl.sys.htm.service.HttpMntrngService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요 - HTTP서비스모니터링에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용 - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - HTTP서비스모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
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

@Service("httpMntrngService")
public class HttpMntrngServiceImpl extends EgovAbstractServiceImpl implements HttpMntrngService {

	@Autowired
	private HttpMntrngMapper httpMntrngMapper;		
	
	/** ID Generation */
	@Autowired
	private EgovIdGnrService httpMntrngIdGnrService; 

	/** ID Generation */
	@Autowired
	private EgovIdGnrService httpMntrngLogIdGnrService; 

	/**
	 * 등록된 HTTP서비스모니터링 목록을 조회한다.
	 * 
	 * @param HttpMntrngVO
	 *            - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 */
	public List<HttpMntrngVO> selectHttpMntrngList(HttpMntrngVO httpMntrngVO) {
		return httpMntrngMapper.selectHttpMntrngList(httpMntrngVO);
	}

	/**
	 * HTTP서비스모니터링 목록 총 갯수를 조회한다.
	 * 
	 * @param HttpMntrngVO
	 *            - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 */
	public int selectHttpMntrngListCnt(HttpMntrngVO httpMntrngVO) {
		return httpMntrngMapper.selectHttpMntrngListCnt(httpMntrngVO);
	}

	/**
	 * 등록된 HTTP서비스모니터링의 상세정보를 조회한다.
	 * 
	 * @param httpMonVO
	 *            - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 */
	public HttpMntrngVO selectHttpMntrngDetail(HttpMntrngVO httpMntrngVO) {
		HttpMntrngVO resultVo = httpMntrngMapper.selectHttpMntrngDetail(httpMntrngVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, httpMntrngVO); 
		return resultVo;
	}

	/**
	 * HTTP서비스모니터링 정보를 신규로 등록한다.
	 * 
	 * @param siteUrl
	 *            - HTTP서비스모니터링 model
	 */
	public void insertHttpMntrng(HttpMntrngVO httpMntrngVO) {
		try {
			httpMntrngVO.setSysId(httpMntrngIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		httpMntrngMapper.insertHttpMntrng(httpMntrngVO);
	}

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 수정한다.
	 * 
	 * @param siteUrl
	 *            - HTTP서비스모니터링 model
	 */
	public void updateHttpMntrng(HttpMntrngVO httpMntrngVO) {
		httpMntrngMapper.updateHttpMntrng(httpMntrngVO);
	}

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * 
	 * @param siteUrl
	 *            - HTTP서비스모니터링 model
	 */
	public void deleteHttpMntrng(HttpMntrngVO httpMntrngVO) {
		httpMntrngMapper.deleteHttpMntrng(httpMntrngVO);
	}

	/**
	 * HTTP서비스 모니터링 결과를 수정한다.
	 * 
	 * @param httpMonLog
	 *            - HTTP서비스 모니터링대상 model
	 */
	public void updateHttpMntrngSttus(HttpMntrngVO httpMntrngVO) {
		httpMntrngMapper.updateHttpMntrngSttus(httpMntrngVO);

		HttpMntrngLogVO httpMntrngLogVO = new HttpMntrngLogVO();
		httpMntrngLogVO.setSysId(httpMntrngVO.getSysId());
		try {
			httpMntrngLogVO.setLogId(httpMntrngLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		httpMntrngLogVO.setWebKind(httpMntrngVO.getWebKind());
		httpMntrngLogVO.setSiteUrl(httpMntrngVO.getSiteUrl());
		httpMntrngLogVO.setHttpSttusCd(httpMntrngVO.getHttpSttusCd());
		httpMntrngLogVO.setCreatDt(httpMntrngVO.getCreatDt());
		httpMntrngLogVO.setLogInfo(httpMntrngVO.getLogInfo());
		httpMntrngLogVO.setMngrNm(httpMntrngVO.getMngrNm());
		httpMntrngLogVO.setMngrEmailAddr(httpMntrngVO.getMngrEmailAddr());
		httpMntrngLogVO.setFrstRegisterId(httpMntrngVO.getLastUpdusrId());
		insertHttpMntrngLog(httpMntrngLogVO);
	}

	/**
	 * 등록된 HTTP서비스모니터링로그 목록을 조회한다.
	 * 
	 * @param HttpMntrngVO
	 *            - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 */
	public List<HttpMntrngLogVO> selectHttpMntrngLogList(HttpMntrngLogVO httpMntrngLogVO) {
		return httpMntrngMapper.selectHttpMntrngLogList(httpMntrngLogVO);
	}

	/**
	 * 등록된 HTTP서비스모니터링로그 총 갯수를 조회한다.
	 * 
	 * @param HttpMntrngVO
	 *            - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 */
	public int selectHttpMntrngLogListCnt(HttpMntrngLogVO httpMntrngLogVO) {
		return httpMntrngMapper.selectHttpMntrngLogListCnt(httpMntrngLogVO);
	}

	/**
	 * 등록된 HTTP서비스모니터링로그의 상세정보를 조회한다.
	 * 
	 * @param httpMonVO
	 *            - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 */
	public HttpMntrngLogVO selectHttpMntrngDetailLog(HttpMntrngLogVO httpMntrngLogVO) {
		HttpMntrngLogVO resultVo = httpMntrngMapper.selectHttpMntrngDetailLog(httpMntrngLogVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, httpMntrngLogVO); 
		return resultVo;
	}

	/**
	 * HTTP서비스모니터링로그 정보를 등록한다.
	 * 
	 * @param siteUrl
	 *            - HTTP서비스모니터링 model
	 */
	public void insertHttpMntrngLog(HttpMntrngLogVO httpMntrngLogVO) {
		httpMntrngMapper.insertHttpMntrngLog(httpMntrngLogVO);
	}

}
