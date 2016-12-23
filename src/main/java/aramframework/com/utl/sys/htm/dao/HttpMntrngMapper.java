package aramframework.com.utl.sys.htm.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.utl.sys.htm.domain.HttpMntrngLogVO;
import aramframework.com.utl.sys.htm.domain.HttpMntrngVO;

/**
 * 개요 - HTTP서비스모니터링에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용 - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - HTTP서비스모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface HttpMntrngMapper {

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
	public int selectHttpMntrngListCnt(HttpMntrngVO httpMntrngVO);

	/**
	 * 등록된 HTTP서비스모니터링의 상세정보를 조회한다.
	 * 
	 * @param httpMntrngVO
	 */
	public HttpMntrngVO selectHttpMntrngDetail(HttpMntrngVO httpMntrngVO);

	/**
	 * HTTP서비스모니터링 정보를 신규로 등록한다.
	 * 
	 * @param httpMntrngVO
	 */
	public void insertHttpMntrng(HttpMntrngVO httpMntrngVO);

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 수정한다.
	 * 
	 * @param httpMntrngVO
	 */
	public void updateHttpMntrng(HttpMntrngVO httpMntrngVO);

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * 
	 * @param httpMntrngVO
	 */
	public void deleteHttpMntrng(HttpMntrngVO httpMntrngVO);

	/**
	 * HTTP서비스모니터링 결과 정보를 수정한다.
	 * 
	 * @param httpMntrngVO
	 */
	public void updateHttpMntrngSttus(HttpMntrngVO httpMntrngVO);

	/**
	 * 등록된 HTTP서비스모니터링로그 목록을 조회한다.
	 * 
	 * @param httpMntrngVO
	 */
	public List<HttpMntrngLogVO> selectHttpMntrngLogList(HttpMntrngLogVO httpMntrngLogVO);

	/**
	 * HTTP서비스모니터링로그 목록 총 갯수를 조회한다.
	 * 
	 * @param httpMntrngLogVO
	 */
	public int selectHttpMntrngLogListCnt(HttpMntrngLogVO httpMntrngLogVO);

	/**
	 * 등록된 HTTP서비스모니터링로그의 상세정보를 조회한다.
	 * 
	 * @param httpMntrngLogVO
	 */
	public HttpMntrngLogVO selectHttpMntrngDetailLog(HttpMntrngLogVO httpMntrngLogVO);

	/**
	 * HTTP서비스모니터링로그 정보를 신규로 등록한다.
	 * 
	 * @param httpMntrngLogVO
	 */
	public void insertHttpMntrngLog(HttpMntrngLogVO httpMntrngLogVO);

}
