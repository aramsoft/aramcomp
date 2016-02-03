package aramframework.com.utl.sys.htm.service;

import java.util.List;

import aramframework.com.utl.sys.htm.domain.HttpMntrngLogVO;
import aramframework.com.utl.sys.htm.domain.HttpMntrngVO;

/**
 * 개요 - HTTP서비스 모니터링에 대한 Service Interface를 정의한다.
 * 
 * 상세내용 - HTTP서비스 모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - HTTP서비스 모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
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

public interface HttpMntrngService {

	/**
	 * 등록된 HTTP서비스모니터링 목록을 조회한다.
	 * 
	 * @param httpMntrngVO
	 */
	public List<HttpMntrngVO> selectHttpMntrngList(HttpMntrngVO httpMntrngVO);

	/**
	 * HTTP서비스모니터링 목록 총 갯수를 조회한다.
	 * 
	 * @param httpMntrngVO
	 */
	int selectHttpMntrngListCnt(HttpMntrngVO httpMntrngVO);

	/**
	 * 등록된 HTTP서비스모니터링의 상세정보를 조회한다.
	 * 
	 * @param httpMntrngVO
	 */
	HttpMntrngVO selectHttpMntrngDetail(HttpMntrngVO httpMntrngVO);

	/**
	 * HTTP서비스모니터링 정보를 신규로 등록한다.
	 * 
	 * @param httpMntrngVO
	 */
	void insertHttpMntrng(HttpMntrngVO httpMntrngVO);

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 수정한다.
	 * 
	 * @param httpMntrngVO
	 */
	void updateHttpMntrng(HttpMntrngVO httpMntrngVO);

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * 
	 * @param httpMntrngVO
	 */
	void deleteHttpMntrng(HttpMntrngVO httpMntrngVO);

	/**
	 * HTTP서비스 모니터링 결과를 수정한다.
	 * 
	 * @param httpMntrngVO
	 */
	public void updateHttpMntrngSttus(HttpMntrngVO httpMntrngVO);

	/**
	 * 등록된 HTTP서비스모니터링로그 목록을 조회한다.
	 * 
	 * @param httpMntrngLogVO
	 */
	public List<HttpMntrngLogVO> selectHttpMntrngLogList(HttpMntrngLogVO httpMntrngLogVO);

	/**
	 * HTTP서비스모니터링로그 목록 총 갯수를 조회한다.
	 * 
	 * @param httpMntrngLogVO
	 */
	 int selectHttpMntrngLogListCnt(HttpMntrngLogVO httpMntrngLogVO);

	/**
	 * 등록된 HTTP서비스모니터링로그의 상세정보를 조회한다.
	 * 
	 * @param httpMntrngLogVO
	 */
	HttpMntrngLogVO selectHttpMntrngDetailLog(HttpMntrngLogVO httpMntrngLogVO);

	/**
	 * HTTP서비스모니터링로그 정보를 등록한다.
	 * 
	 * @param httpMntrngLogVO
	 */
	void insertHttpMntrngLog(HttpMntrngLogVO httpMntrngLogVO);

}
