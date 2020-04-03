package aramframework.com.cmm.userdetails.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import egovframework.rte.fdl.security.userdetails.EgovUserDetails;
import egovframework.rte.fdl.security.userdetails.jdbc.EgovUsersByUsernameMapping;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * mapRow 결과를 사용자 EgovUserDetails Object 에 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class SessionMapping extends EgovUsersByUsernameMapping {

	/**
	 * 사용자정보를 테이블에서 조회하여 EgovUsersByUsernameMapping 에 매핑한다.
	 * 
	 * @param 	ds						DataSource
	 * @param 	usersByUsernameQuery	String
	 */
	public SessionMapping(DataSource ds, String usersByUsernameQuery) {
		super(ds, usersByUsernameQuery);
	}

	/**
	 * mapRow Override
	 * 
	 * @param 	rs		ResultSet 결과
	 * @param 	rownum  row num
	 * @return 			EgovUserDetails
	 * @exception SQLException
	 */
	@Override
	protected EgovUserDetails mapRow(ResultSet rs, int rownum) throws SQLException {
		logger.debug("## EgovUsersByUsernameMapping mapRow ##");

		String strUserId = rs.getString("user_id");
		String strPassWord = rs.getString("password");
		boolean strEnabled = rs.getBoolean("enabled");

		String strUserNm = rs.getString("user_nm");
		String strUserSe = rs.getString("user_se");
		String strUserEmail = rs.getString("user_email");
		String strOrgnztId = rs.getString("orgnzt_id");
		/** 2010.06.30 *이용 *조직명 추가 */
		String strOrgnztNm = rs.getString("orgnzt_nm");
		String strMblTelNo = rs.getString("mbtlnum");

		// 세션 항목 설정
		LoginVO loginVO = new LoginVO();
		loginVO.setUserId(strUserId);
		loginVO.setPassword(strPassWord);
		loginVO.setName(strUserNm);
		loginVO.setUserSe(strUserSe);
		loginVO.setEmail(strUserEmail);
		loginVO.setOrgnztId(strOrgnztId);
		/** 2010.06.30 *이용 *조직명 추가 */
		loginVO.setOrgnztNm(strOrgnztNm);
		loginVO.setMblTelNo(strMblTelNo);

		return new EgovUserDetails(strUserId, strPassWord, strEnabled, loginVO);
	}
	
}
