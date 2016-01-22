package aramframework.com.uss.olp.qtm.service.impl;

import java.util.List;

import aramframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문템플릿 Dao Class 구현
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
public interface QustnrTmplatManageMapper {

	/**
	 * 설문템플릿 목록을 조회한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	public List<EgovMap> selectQustnrTmplatManageList(QustnrTmplatManageVO qustnrTmplatManageVO);

	/**
	 * 설문템플릿를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	public int selectQustnrTmplatManageListCnt(QustnrTmplatManageVO qustnrTmplatManageVO);

	/**
	 * 설문템플릿를(을) 상세조회 한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	public QustnrTmplatManageVO selectQustnrTmplatManageDetail(QustnrTmplatManageVO qustnrTmplatManageVO);

	/**
	 * 템플릿파일명을 조회한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	public QustnrTmplatManageVO selectQustnrTmplatManageTmplatImage(QustnrTmplatManageVO qustnrTmplatManageVO);

	/**
	 * 설문템플릿를(을) 등록한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	public void insertQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO);

	/**
	 * 설문템플릿를(을) 수정한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	public void updateQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO);

	/**
	 * 설문템플릿를(을) 삭제한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	public void deleteQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO);
	
}
