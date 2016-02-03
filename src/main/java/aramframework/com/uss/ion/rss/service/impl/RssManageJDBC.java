package aramframework.com.uss.ion.rss.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aramframework.com.cmm.domain.ComCodeVO;

/**
 * RSS태그관리를 처리하는 Dao Class 구현
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

@Repository("rssManageJDBC")
public class RssManageJDBC {

	@Autowired
	DataSource dataSource;
	
	/**
	 * JDBC 테이블 목록을조회한다.
	 * 
	 * @return List -조회한목록이담긴List
	 * @throws Exception
	 */
	public List<ComCodeVO> selectRssManageTableList() {

		String TABLE_NAME = "TABLE_NAME";
		String TABLE_SCHEMA = "TABLE_SCHEM";
		String[] TABLE_AND_VIEW_TYPES = { "TABLE", "VIEW" };
		ArrayList<ComCodeVO> arrListResult = new ArrayList<ComCodeVO>();

		Connection conn = null;
		DatabaseMetaData dbmd = null;
		ResultSet tables = null;

		try {

			conn = dataSource.getConnection();
			dbmd = conn.getMetaData();

			tables = dbmd.getTables(null, "CMM2", null, TABLE_AND_VIEW_TYPES);
			while (tables.next()) {

				ComCodeVO codeVO = new ComCodeVO();
				codeVO.setCode(tables.getString(TABLE_NAME));
				codeVO.setCodeNm(tables.getString(TABLE_SCHEMA));
				arrListResult.add(codeVO);
			}

		} catch (Exception ex) {
			System.out.println(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (tables != null) {
				try {
					tables.close();
				} catch (Exception e) {
					System.out.println(e); // 2011.10.10 보안점검 후속조치
				}
			}
			if (dbmd != null) {
				try {
					dbmd = null;
				} catch (Exception e) {
					System.out.println("IGNORE: " + e); // 2011.10.10 보안점검 후속조치
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println("IGNORE: " + e); // 2011.10.10 보안점검 후속조치
				}
			}
		}

		return arrListResult;

	}

	/**
	 * JDBC 테이블 컬럼 목록을 조회한다.
	 * 
	 * @param map
	 *            - 컬럼조회정보
	 * @return List -조회한목록이담긴List
	 * @throws Exception
	 */
	public List<ComCodeVO> selectRssManageTableColumnList(Map<String, String> map) {

		String sTableName = (String) map.get("tableName");
		String sDbType = (String) map.get("dbType");
		String sSQL = "";
		ArrayList<ComCodeVO> arrListResult = new ArrayList<ComCodeVO>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			if (!sDbType.equals("altibase")) {// 2011.10.18
				st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} else {
				st = conn.createStatement();
			}
			
			if (sDbType.equals("mysql")) {
				sSQL = "SELECT * FROM " + sTableName + " LIMIT 1 ";
			} else {
				sSQL = "SELECT * FROM " + sTableName + " WHERE ROWNUM <= 1 ";
			}

			rs = st.executeQuery(sSQL);

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();

			for (int i = 1; i < numberOfColumns + 1; i++) {
				ComCodeVO codeVO = new ComCodeVO();
				codeVO.setCode( rsMetaData.getTableName(i));
				codeVO.setCodeNm( rsMetaData.getColumnName(i));
				arrListResult.add(codeVO);
			}

		} catch (Exception ex) {
			System.out.println(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					System.out.println("IGNORE: " + e); // 2011.10.10 보안점검 후속조치
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					System.out.println("IGNORE: " + e); // 2011.10.10 보안점검 후속조치
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println("IGNORE: " + e); // 2011.10.10 보안점검 후속조치
				}
			}
		}

		return arrListResult;
	}

}
