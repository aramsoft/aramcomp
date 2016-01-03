package aramframework.com.sym.ccm.icr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 기관코드에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface InsttCodeRecptnService {

	/**
	 * 기관코드수신 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	List<EgovMap> selectInsttCodeRecptnList(InsttCodeRecptnVO insttCodeRecptnVO);

	/**
	 * 기관코드수신 총 갯수를 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	int selectInsttCodeRecptnListCnt(InsttCodeRecptnVO insttCodeRecptnVO);

	/**
	 * 기관코드 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	List<EgovMap> selectInsttCodeList(InsttCodeRecptnVO insttCodeRecptnVO);

	/**
	 * 기관코드 총 갯수를 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	int selectInsttCodeListCnt(InsttCodeRecptnVO insttCodeRecptnVO);

	/**
	 * 기관코드 상세내역을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	InsttCodeRecptnVO selectInsttCodeDetail(InsttCodeRecptnVO insttCodeRecptnVO);

	/**
	 * 기관코드수신을 처리한다.
	 * 
	 */
	void insertInsttCodeRecptn();

}
