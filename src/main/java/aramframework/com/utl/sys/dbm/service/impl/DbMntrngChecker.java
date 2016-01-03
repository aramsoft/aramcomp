package aramframework.com.utl.sys.dbm.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.utl.sys.dbm.service.DbMntrngResult;

/**
 * DB서비스모니터링을 위한 Check 클래스
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

public class DbMntrngChecker {

	protected static final Logger LOG = LoggerFactory.getLogger(DbMntrngChecker.class);

	/**
	 * DB서비스모니터링을 수행한다.
	 * 
	 * @param dataSource
	 * @param dataSourcNm
	 * @param ceckSql
	 */
	public static DbMntrngResult check(DataSource dataSource, String dataSourcNm, String ceckSql) {

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(ceckSql);

			stmt.executeQuery();

			return new DbMntrngResult(true, null);
		} catch (SQLException e) {
			LOG.error("DB서비스모니터링 에러 : " + e.getMessage());
			LOG.debug(e.getMessage(), e);
			return new DbMntrngResult(false, e);
		} catch (Exception e) {
			LOG.error("DB서비스모니터링 에러 : " + e.getMessage());
			LOG.debug(e.getMessage(), e);
			return new DbMntrngResult(false, e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception ignore) {}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ignore) {}
		}

	}

}
