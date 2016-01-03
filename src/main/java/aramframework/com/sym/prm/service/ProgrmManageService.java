package aramframework.com.sym.prm.service;

import java.util.List;

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

public interface ProgrmManageService {

	/**
	 * 프로그램 목록을 조회
	 * 
	 * @param progrmManageVO
	 */
	List<EgovMap> selectProgrmList(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램목록 총건수를 조회한다.
	 * 
	 * @param progrmManageVO
	 */
	int selectProgrmListCnt(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램 파일 존재여부를 조회
	 * 
	 * @param progrmFileNm
	 */
	int selectProgrmNMTotCnt(String progrmFileNm);

	/**
	 * 프로그램 상세정보를 조회
	 * 
	 * @param progrmManageVO
	 */
	ProgrmManageVO selectProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램 정보를 등록
	 * 
	 * @param progrmManageVO
	 */
	void insertProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램 정보를 수정
	 * 
	 * @param progrmManageVO
	 */
	void updateProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램 정보를 삭제
	 * 
	 * @param progrmManageVO
	 */
	void deleteProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedProgrmFileNmForDel
	 */
	void deleteProgrmManageList(String checkedProgrmFileNmForDel);

}