package aramframework.com.sym.ccm.acr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 법정동코드에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface AdministCodeRecptnService {

	/**
	 * 법정동코드수신 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	List<EgovMap> selectAdministCodeRecptnList(AdministCodeRecptnVO administCodeRecptnVO);

	/**
	 * 법정동코드수신 총 갯수를 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	int selectAdministCodeRecptnListCnt(AdministCodeRecptnVO administCodeRecptnVO);

	/**
	 * 법정동코드 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	List<EgovMap> selectAdministCodeList(AdministCodeRecptnVO administCodeRecptnVO);

	/**
	 * 법정동코드 총 갯수를 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	int selectAdministCodeListCnt(AdministCodeRecptnVO administCodeRecptnVO);

	/**
	 * 법정동코드 상세내역을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	AdministCodeRecptnVO selectAdministCodeDetail(AdministCodeRecptnVO administCodeRecptnVO);

	/**
	 * 법정동코드수신을 처리한다.
	 * 
	 */
	void insertAdministCodeRecptn();

}
