package aramframework.com.cop.bbs.dao;

import java.util.List;

import aramframework.com.cop.bbs.domain.SatisfactionVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 만족도조사를 위한 데이터 접근 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface BBSSatisfactionMapper {

	/**
	 * 만족도조사에 대한 목록을 조회 한다.
	 * 
	 * @param satisfactionVO
	 */
	public List<EgovMap> selectSatisfactionList(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 목록 건수를 조회 한다.
	 * 
	 * @param satisfactionVO
	 */
	public int selectSatisfactionListCnt(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 내용을 조회한다.
	 * 
	 * @param satisfactionVO
	 */
	public SatisfactionVO selectSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사를 등록한다.
	 * 
	 * @param satisfactionVO
	 */
	public void insertSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 내용을 수정한다.
	 * 
	 * @param satisfactionVO
	 */
	public void updateSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사를 삭제한다.
	 * 
	 * @param satisfactionVO
	 */
	public void deleteSatisfaction(SatisfactionVO satisfactionVO);

	/**
	 * 만족도조사에 대한 패스워드를 조회 한다.
	 * 
	 * @param satisfactionVO
	 */
	public String getSatisfactionPassword(SatisfactionVO satisfactionVO);

	/**
	 * 만족도 전체 점수를 제공한다.
	 * 
	 * @param satisfactionVO
	 */
	public Float getSummary(SatisfactionVO satisfactionVO);
	
}
