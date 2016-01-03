package aramframework.com.uss.ion.tir.service.impl;

import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 트위터수신을 처리하는  Dao Class 구현
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

@Mapper("twitterMapper")
public interface TwitterMapper {

	/**
	 * 트위터 계정을 조회 한다.
	 * 
	 * @param usid
	 *            -조회할 정보가 담긴 객체
	 * @return Map - 조회 정보가 담긴 Map
	 */
	public EgovMap selectTwitterAccount(String usid) ;

	/**
	 * 트위터 계정을 건수를 조회 한다.
	 * 
	 * @param usid
	 *            -조회할 정보가 담긴 객체
	 * @return int - 조회 정보가 담긴 Integer
	 */
	public int selectTwitterAccountCheck(String usid) ;

	/**
	 * 트위터 계정을 신규로 등록한다.
	 * 
	 * @param param
	 *            - 조회할 정보가 담긴 Map
	 */
	public void insertTwitterAccount(Map<String, String> param) ;

	/**
	 * 트위터 계정을 수정한다.
	 * 
	 * @param param
	 *            - 조회할 정보가 담긴 Map
	 */
	public void updateTwitterAccount(Map<String, String> param) ;

	/**
	 * 트위터 계정을 삭제한다.
	 * 
	 * @param param
	 *            - 조회할 정보가 담긴 Map
	 */
	public void deleteTwitterAccount(Map<String, String> param) ;
	
}
