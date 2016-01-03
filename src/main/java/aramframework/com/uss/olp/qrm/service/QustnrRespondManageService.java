package aramframework.com.uss.olp.qrm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문응답자관리 Service Class 구현
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

public interface QustnrRespondManageService {

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
	 * @param qustnrRespondManageVO
	 */
	void insertQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO);

	/**
	 * 응답자정보를(을) 수정한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	void updateQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO);

	/**
	 * 응답자정보를(을) 삭제한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	void deleteQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO);

}
