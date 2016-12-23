package aramframework.com.uss.olp.qrm.dao;

import java.util.List;

import aramframework.com.uss.olp.qrm.domain.QustnrRespondManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문응답자관리 Dao Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface QustnrRespondManageMapper {

	/**
	 * 응답자정보 목록을 조회한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public List<EgovMap> selectQustnrRespondManageList(QustnrRespondManageVO qustnrRespondManageVO);

	/**
	 * 응답자정보를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public int selectQustnrRespondManageListCnt(QustnrRespondManageVO qustnrRespondManageVO);

	/**
	 * 응답자정보를(을) 상세조회 한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public QustnrRespondManageVO selectQustnrRespondManageDetail(QustnrRespondManageVO qustnrRespondManageVO);

	/**
	 * 응답자정보를(을) 등록한다.
	 * 
	 * @param qqustnrRespondManageVO
	 */
	public void insertQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO);

	/**
	 * 응답자정보를(을) 수정한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public void updateQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO);

	/**
	 * 응답자정보를(을) 삭제한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	public void deleteQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO);
	
}
