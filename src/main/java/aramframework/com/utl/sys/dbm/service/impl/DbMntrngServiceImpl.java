package aramframework.com.utl.sys.dbm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.utl.sys.dbm.service.DbMntrngVO;
import aramframework.com.utl.sys.dbm.service.DbMntrngLogVO;
import aramframework.com.utl.sys.dbm.service.DbMntrngService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * DB서비스모니터링관리에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("dbMntrngService")
public class DbMntrngServiceImpl extends EgovAbstractServiceImpl implements DbMntrngService {

	/**
	 * DB서비스모니터링DAO
	 */
	@Resource(name = "dbMntrngMapper")
	private DbMntrngMapper dbMntrngMapper;	

	/**
	 * DB서비스모니터링의 목록을 조회 한다.
	 * 
	 * @param dbMntrngVO
	 */
	public List<EgovMap> selectDbMntrngList(DbMntrngVO dbMntrngVO) {
		return dbMntrngMapper.selectDbMntrngList(dbMntrngVO);
	}

	/**
	 * DB서비스모니터링 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param dbMntrngVO
	 */
	public int selectDbMntrngListCnt(DbMntrngVO dbMntrngVO) {
		return dbMntrngMapper.selectDbMntrngListCnt(dbMntrngVO);
	}

	/**
	 * DB서비스모니터링을 상세조회 한다.
	 * 
	 * @param dbMntrngVO
	 */
	public DbMntrngVO selectDbMntrng(DbMntrngVO dbMntrngVO) {
		DbMntrngVO resultVo = dbMntrngMapper.selectDbMntrng(dbMntrngVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, dbMntrngVO); 
		return resultVo;
	}

	/**
	 * DB서비스모니터링을 등록한다.
	 * 
	 * @param dbMntrngVO
	 */
	public void insertDbMntrng(DbMntrngVO dbMntrngVO) {
		// 상태값을 초기치로 설정한다.
		dbMntrngVO.setMntrngSttus("01");
		dbMntrngMapper.insertDbMntrng(dbMntrngVO);
	}

	/**
	 * DB서비스모니터링정보를 수정한다.
	 * 
	 * @param dbMntrngVO
	 */
	public void updateDbMntrng(DbMntrngVO dbMntrngVO) {
		dbMntrngMapper.updateDbMntrng(dbMntrngVO);
	}

	/**
	 * DB서비스모니터링을 삭제한다.
	 * 
	 * @param dbMntrngVO
	 */
	public void deleteDbMntrng(DbMntrngVO dbMntrngVO) {
		dbMntrngMapper.deleteDbMntrng(dbMntrngVO);
	}

	/**
	 * DB서비스모니터링로그의 목록을 조회 한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public List<EgovMap> selectDbMntrngLogList(DbMntrngLogVO dbMntrngLogVO) {
		return dbMntrngMapper.selectDbMntrngLogList(dbMntrngLogVO);
	}

	/**
	 * DB서비스모니터링로그 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public int selectDbMntrngLogListCnt(DbMntrngLogVO dbMntrngLogVO) {
		return dbMntrngMapper.selectDbMntrngLogListCnt(dbMntrngLogVO);
	}

	/**
	 * DB서비스모니터링로그을 상세조회 한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public DbMntrngLogVO selectDbMntrngLog(DbMntrngLogVO dbMntrngLogVO) {
		DbMntrngLogVO resultVo = dbMntrngMapper.selectDbMntrngLog(dbMntrngLogVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, dbMntrngLogVO); 
		return resultVo;
	}

	/**
	 * DB서비스모니터링로그를 등록한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	public void insertDbMntrngLog(DbMntrngLogVO dbMntrngLogVO) {
		dbMntrngMapper.insertDbMntrngLog(dbMntrngLogVO);
	}

}