package aramframework.com.uss.ion.bnt.service;

import java.io.InputStream;
import java.util.List;

import aramframework.com.uss.ion.bnt.domain.BndtCeckManageVO;
import aramframework.com.uss.ion.bnt.domain.BndtDiaryVO;
import aramframework.com.uss.ion.bnt.domain.BndtManageVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 당직관리에 대한 Service Interface를 정의한다.
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

public interface BndtManageService {

	/**
	 * 당직관리 정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO);

	/**
	 * 당직관리 목록 총 갯수를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public int selectBndtManageListCnt(BndtManageVO bndtManageVO);

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public BndtManageVO selectBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 당직관리 정보를 신규로 등록한다.
	 * 
	 * @param bndtManageVO
	 */
	public void insertBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 기 등록된 당직관리 정보를 수정한다.
	 * 
	 * @param bndtManageVO
	 */
	public void updtBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 기 등록된 당직관리 정보를 삭제한다.
	 * 
	 * @param bndtManageVO
	 */
	public void deleteBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 당직일지 갯수를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public int selectBndtDiaryTotCnt(BndtManageVO bndtManageVO);

	/***** 당직 체크관리 *****/
	/**
	 * 당직체크관리 정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public List<EgovMap> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 당직체크관리 목록 총 갯수를 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public int selectBndtCeckManageListCnt(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 당직체크관리 정보를 신규로 등록한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public void insertBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 기 등록된 당직체크관리 정보를 수정한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public void updtBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 기 등록된 당직체크관리 정보를 삭제한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public void deleteBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 당직체크 중복여부 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public int selectBndtCeckManageDplctAt(BndtCeckManageVO bndtCeckManageVO);

	/***** 당직 일지 *****/

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 * 
	 * @param bndtDiaryVO
	 */
	public List<BndtDiaryVO> selectBndtDiary(BndtDiaryVO bndtDiaryVO);

	/**
	 * 당직일지관리 정보를 신규로 등록한다.
	 * 
	 * @param bndtDiaryVO
	 * @param diaryForInsert
	 */
	public void insertBndtDiary(BndtDiaryVO bndtDiaryVO, String diaryForInsert);

	/**
	 * 기 등록된 당직일지관리 정보를 수정한다.
	 * 
	 * @param bndtDiaryVO
	 * @param diaryForUpdt
	 */
	public void updtBndtDiary(BndtDiaryVO bndtDiaryVO, String diaryForUpdt);

	/**
	 * 기 등록된 당직일지관리 정보를 삭제한다.
	 * 
	 * @param bndtDiaryVO
	 */
	public void deleteBndtDiary(BndtDiaryVO bndtDiaryVO);

	/**
	 * 당직자 excel생성
	 * 
	 * @param inputStream
	 */
	public List<BndtManageVO> selectBndtManageBnde(InputStream inputStream);

	/**
	 * 당직정보를 일괄등록처리한다.
	 * 
	 * @param bndtManageVO
	 * @param checkedBndtManageForInsert
	 */
	public void insertBndtManageBnde(BndtManageVO bndtManageVO, String checkedBndtManageForInsert);

	/**
	 * 당직관리 등록건수 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public int selectBndtManageMonthCnt(BndtManageVO bndtManageVO);
	
}