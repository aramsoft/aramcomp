package aramframework.com.ssi.syi.ist.dao;

import java.util.List;

import aramframework.com.ssi.syi.ist.domain.CntcSttusVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 연계현황에 대한 데이터 접근 클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface CntcSttusMapper {

	/**
	 * 연계현황 목록을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	public List<EgovMap> selectCntcSttusList(CntcSttusVO cntcSttusVO);

	/**
	 * 연계현황 총 갯수를 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	public int selectCntcSttusListCnt(CntcSttusVO cntcSttusVO);

	/**
	 * 연계현황 상세항목을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	public CntcSttusVO selectCntcSttusDetail(CntcSttusVO cntcSttusVO);

}
