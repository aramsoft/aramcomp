package aramframework.com.uss.olp.qim.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문항목관리를 처리하는 Service Class 구현
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

public interface QustnrItemManageService {

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
	void insertQustnrItemManage(QustnrItemManageVO qustnrItemManageVO);

	/**
	 * 설문항목를(을) 수정한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	void updateQustnrItemManage(QustnrItemManageVO qustnrItemManageVO);

	/**
	 * 설문항목를(을) 삭제한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	void deleteQustnrItemManage(QustnrItemManageVO qustnrItemManageVO);

}
