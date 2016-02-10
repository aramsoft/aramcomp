package aramframework.com.utl.sys.dbm.dao;

import java.util.List;

import aramframework.com.utl.sys.dbm.domain.DbMntrngLogVO;
import aramframework.com.utl.sys.dbm.domain.DbMntrngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * DB서비스모니터링관리에 대한 DAO 클래스를 정의한다.
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

@Mapper
public interface DbMntrngMapper {

	/**
	 * DB서비스모니터링정보목록을 조회한다.
	 * 
	 * @param dbMntrngVO
	 */
	public List<EgovMap> selectDbMntrngList(DbMntrngVO dbMntrngVO);

	/**
	 * DB서비스모니터링 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param dbMntrngVO
	 */
	public int selectDbMntrngListCnt(DbMntrngVO dbMntrngVO);

	/**
	 * DB서비스모니터링정보를 상세조회 한다.
	 * 
	 * @param dbMntrngVO
	 */
	public DbMntrngVO selectDbMntrng(DbMntrngVO dbMntrngVO);

	/**
	 * DB서비스모니터링을 등록한다.
	 * 
	 * @param dbMntrngVO
	 */
	public void insertDbMntrng(DbMntrngVO dbMntrngVO);

	/**
	 * DB서비스모니터링정보를 수정한다.
	 * 
	 * @param dbMntrngVO
	 */
	public void updateDbMntrng(DbMntrngVO dbMntrngVO);

	/**
	 * DB서비스모니터링을 삭제한다.
	 * 
	 * @param dbMntrngVO
	 */
	public void deleteDbMntrng(DbMntrngVO dbMntrngVO);

	/**
	 * DB서비스모니터링로그정보목록을 조회한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public List<EgovMap> selectDbMntrngLogList(DbMntrngLogVO dbMntrngLogVO);

	/**
	 * DB서비스모니터링로그 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public int selectDbMntrngLogListCnt(DbMntrngLogVO dbMntrngLogVO);

	/**
	 * DB서비스모니터링로그정보를 상세조회 한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public DbMntrngLogVO selectDbMntrngLog(DbMntrngLogVO dbMntrngLogVO);

	/**
	 * DB서비스모니터링로그를 등록한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public void insertDbMntrngLog(DbMntrngLogVO dbMntrngLogVO);

}