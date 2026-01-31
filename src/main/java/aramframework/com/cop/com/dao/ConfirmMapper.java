package aramframework.com.cop.com.dao;

import java.util.List;

import aramframework.com.cop.com.domain.ConfirmHistoryVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 승인정보 관리를 위한 데이터 접근 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface ConfirmMapper {

	/**
	 * 승인(탈퇴)요청에 대한 목록을 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public List<EgovMap> selectConfirmRequestList(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 목록 전체 건수를 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public int selectConfirmRequestListCnt(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한  전체 건수를 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public int countConfirmRequest(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 상세내용을 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public ConfirmHistoryVO selectSingleConfirmRequest(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 등록을 처리한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public void insertConfirmRequest(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 정보를 수정한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public void updateConfirmRequest(ConfirmHistoryVO confirmHistoryVO);

}
