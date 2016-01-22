package aramframework.com.sym.prm.service.impl;

import java.util.List;

import aramframework.com.sym.prm.service.ProgrmManageDtlVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 프로그램 목록관리및 프로그램변경관리에 대한 DAO 클래스를 정의한다.
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
public interface ProgrmManageDtlMapper {

	/**
	 * 프로그램변경요청 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public List<EgovMap> selectProgrmChangeRequstList(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	public int selectProgrmChangeRequstListCnt(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청 요청번호MAX 정보를 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public int selectProgrmChangeRequstNo(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청 정보를 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public ProgrmManageDtlVO selectProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청을 등록
	 * 
	 * @param progrmManageDtlVO
	 */
	public void insertProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청을 수정
	 * 
	 * @param progrmManageDtlVO
	 */
	public void updateProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청을 삭제
	 * 
	 * @param progrmManageDtlVO
	 */
	public void deleteProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경내역 전체삭제 초기화
	 * 
	 */
	public void deleteAllProgrmDtls();

	/**
	 * 프로그램변경요청처리목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public List<EgovMap> selectChangeRequstProcessList(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	public int selectChangeRequstProcessListCnt(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청 처리 수정
	 * 
	 * @param progrmManageDtlVO
	 */
	public void updateProgrmChangeRequstProcess(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청자 Email 정보를 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public String selectRqesterEmail(ProgrmManageDtlVO progrmManageDtlVO);
	
	/**
	 * 프로그램변경이력 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public List<EgovMap> selectProgrmChangeHistoryList(ProgrmManageDtlVO progrmManageDtlVO);

	/**
	 * 프로그램변경요청 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	public int selectProgrmChangeHistoryListCnt(ProgrmManageDtlVO progrmManageDtlVO);
	
}