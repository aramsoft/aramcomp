package aramframework.com.sym.prm.service;

import java.util.List;

import aramframework.com.sym.prm.domain.ProgrmManageDtlVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 프로그램관리에 관한 서비스 인터페이스 클래스를 정의한다.
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

public interface ProgrmManageDtlService {

	/**
	 * 프로그램변경요청 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	List<EgovMap> selectProgrmChangeRequstList(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청목록 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	int selectProgrmChangeRequstListCnt(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청 정보를 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	ProgrmManageDtlVO selectProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청을 등록
	 * 
	 * @param progrmManageDtlVO
	 */
	void insertProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청을 수정
	 * 
	 * @param progrmManageDtlVO
	 */
	void updateProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청을 삭제
	 * 
	 * @param progrmManageDtlVO
	 */
	void deleteProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청처리 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	List<EgovMap> selectChangeRequstProcessList(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청처리목록 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	int selectChangeRequstProcessListCnt(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청처리를 수정
	 * 
	 * @param progrmManageDtlVO
	 */
	void updateProgrmChangeRequstProcess(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청자 Email 정보를 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	String selectRqesterEmail(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경이력 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	List<EgovMap> selectProgrmChangeHistoryList(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청목록 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	int selectProgrmChangeHistoryListCnt(ProgrmManageDtlVO progrmManageDtlVO);

}