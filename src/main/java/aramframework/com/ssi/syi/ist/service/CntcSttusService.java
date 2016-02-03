package aramframework.com.ssi.syi.ist.service;

import java.util.List;

import aramframework.com.ssi.syi.ist.domain.CntcSttusVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 연계현황에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface CntcSttusService {

	/**
	 * 연계현황 목록을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	List<EgovMap> selectCntcSttusList(CntcSttusVO cntcSttusVO);

	/**
	 * 연계현황 총 갯수를 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	int selectCntcSttusListCnt(CntcSttusVO cntcSttusVO);

	/**
	 * 연계현황 상세항목을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	CntcSttusVO selectCntcSttusDetail(CntcSttusVO cntcSttusVO);

}
