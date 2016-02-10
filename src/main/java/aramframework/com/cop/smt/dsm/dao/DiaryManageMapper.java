package aramframework.com.cop.smt.dsm.dao;

import java.util.List;

import aramframework.com.cop.smt.dsm.domain.DiaryManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 일지관리를 처리하는 Dao Class 구현
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
public interface DiaryManageMapper {

	/**
	 * 일지관리 목록을 조회한다.
	 * 
	 * @param diaryManageVO
	 */
	public List<EgovMap> selectDiaryManageList(DiaryManageVO diaryManageVO);

	/**
	 * 일지관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param diaryManageVO
	 */
	public int selectDiaryManageListCnt(DiaryManageVO diaryManageVO);

	/**
	 * 일지관리를(을) 상세조회 한다.
	 * 
	 * @param diaryManageVO
	 */
	public DiaryManageVO selectDiaryManageDetail(DiaryManageVO diaryManageVO);

	/**
	 * 일지관리를(을) 등록한다.
	 * 
	 * @param qdiaryManageVO
	 */
	public void insertDiaryManage(DiaryManageVO diaryManageVO);

	/**
	 * 일지관리를(을) 수정한다.
	 * 
	 * @param diaryManageVO
	 */
	public void updateDiaryManage(DiaryManageVO diaryManageVO);

	/**
	 * 일지관리를(을) 삭제한다.
	 * 
	 * @param diaryManageVO
	 */
	public void deleteDiaryManage(DiaryManageVO diaryManageVO);

}
