package aramframework.com.uss.olp.qqm.dao;

import java.util.List;

import aramframework.com.uss.olp.qqm.domain.QustnrQestnManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문문항을 처리하는 Dao Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface QustnrQestnManageMapper {

	/**
	 * 설문조사 응답자답변내용결과/기타답변내용결과 통계를 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public List<EgovMap> selectQustnrManageStatistics2(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문조사 통계를 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public List<EgovMap> selectQustnrManageStatistics(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문지정보 설문제목을 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public String selectQustnrManageQestnrSj(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문문항 목록을 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public List<EgovMap> selectQustnrQestnManageList(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문문항를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public int selectQustnrQestnManageListCnt(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문문항를(을) 상세조회 한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public QustnrQestnManageVO selectQustnrQestnManageDetail(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문문항를(을) 등록한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public void insertQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문문항를(을) 수정한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	public void updateQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO);

	/**
	 * 설문문항를(을) 삭제한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	// 설문조사(설문결과) 삭제
	public void deleteQustnrRespondInfo(QustnrQestnManageVO qustnrQestnManageVO);
	// 설문항목 삭제
	public void deleteQustnrItemManage(QustnrQestnManageVO qustnrQestnManageVO);
	// 설문문항
	public void deleteQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO);
	
}
