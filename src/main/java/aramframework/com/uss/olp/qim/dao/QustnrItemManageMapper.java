package aramframework.com.uss.olp.qim.dao;

import java.util.List;

import aramframework.com.uss.olp.qim.domain.QustnrItemManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문항목관리를 처리하는 Dao Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface QustnrItemManageMapper {

	/**
	 * 설문항목 목록을 조회한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public List<EgovMap> selectQustnrItemManageList(QustnrItemManageVO qustnrItemManageVO);

	/**
	 * 설문항목를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public int selectQustnrItemManageListCnt(QustnrItemManageVO qustnrItemManageVO);

	/**
	 * 설문항목를(을) 상세조회 한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public QustnrItemManageVO selectQustnrItemManageDetail(QustnrItemManageVO qustnrItemManageVO);

	/**
	 * 설문항목를(을) 등록한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public void insertQustnrItemManage(QustnrItemManageVO qustnrItemManageVO);

	/**
	 * 설문항목를(을) 수정한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	public void updateQustnrItemManage(QustnrItemManageVO qustnrItemManageVO);

	/**
	 * 설문항목를(을) 삭제한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	// 설문조사(설문결과) 삭제
	public void deleteQustnrRespondInfo(QustnrItemManageVO qustnrItemManageVO); 
	// 설문항목 삭제
	public void deleteQustnrItemManage(QustnrItemManageVO qustnrItemManageVO);
	
}
