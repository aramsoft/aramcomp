package aramframework.com.uss.olp.qri.service;

import java.util.List;

import aramframework.com.uss.olp.qri.domain.QustnrRespondInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 설문조사 Dao Class 구현
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
public interface QustnrRespondInfoMapper {

	/**
	 * 설문조사(설문등록)를(을) 목록을 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public List<EgovMap> selectQustnrRespondInfoManageList(QustnrRespondInfoVO qustnrRespondInfoVO);

	/**
	 * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public int selectQustnrRespondInfoManageListCnt(QustnrRespondInfoVO qustnrRespondInfoVO);

	/**
	 * 회원정보를 조회한다.
	 * 
	 * @param uniqId
	 */
	public EgovMap selectQustnrRespondEmplyrinfo(String uniqId);

	/**
	 * 설문정보를 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondQestnrInfo(String qestnrId);

	/**
	 * 문항정보를 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondQustnrQesitm(String qestnrId);

	/**
	 * 항목정보를 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondQustnrIem(String qestnrId);

	/**
	 * 객관식 통계를 조회 한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondStatistics1(String qestnrId);

	/**
	 * 주관식 통계를 조회한다.
	 * 
	 * @param qestnrId
	 */
	public List<EgovMap> selectQustnrRespondStatistics2(String qestnrId);

	/* 설문조사(관리자모드) */
	/**
	 * 응답자결과(설문조사) 목록을 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public List<EgovMap> selectQustnrRespondInfoList(QustnrRespondInfoVO qustnrRespondInfoVO);

	/**
	 * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public int selectQustnrRespondInfoListCnt(QustnrRespondInfoVO qustnrRespondInfoVO);

	/**
	 * 응답자결과(설문조사)를(을) 상세조회 한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public QustnrRespondInfoVO selectQustnrRespondInfoDetail(QustnrRespondInfoVO qustnrRespondInfoVO);

	/**
	 * 응답자결과(설문조사)를(을) 등록한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public void insertQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO);

	/**
	 * 응답자결과(설문조사)를(을) 수정한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public void updateQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO);

	/**
	 * 응답자결과(설문조사)를(을) 삭제한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	public void deleteQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO);
	
}
