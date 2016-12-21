package aramframework.mbl.com.rns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.mbl.com.rns.dao.RealtimeNoticeMapper;
import aramframework.mbl.com.rns.domain.RealtimeNoticeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 실시간 공지 서비스에 대한 Service Interface를 구현한다.
 * 
 * 상세내용
 * - 실시간 공지 서비스에 대한 등록, 삭제, 조회 기능을 제공한다.
 * - 실시간 공지 서비스에 대한 조회기능은 목록, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class RealtimeNoticeService extends EgovAbstractServiceImpl {
	
	@Autowired
    private RealtimeNoticeMapper realtimeNoticeMapper;	

	/**
	 * [관리자] 실시간공지서비스 목록 조회
	 * 
	 * @param realtimeNoticeVO
	 */
	public List<EgovMap> selectRealtimeNoticeList(RealtimeNoticeVO realtimeNoticeVO) {
		realtimeNoticeVO.setFetchRow(realtimeNoticeVO.getFetchRow() * 5);
		return realtimeNoticeMapper.selectRealtimeNoticeList(realtimeNoticeVO);
	}
	
	/**
	 * [관리자/사용자] 실시간공지서비스 상세조회 / 실시간공지 서비스 내용 
	 * 
	 * @param realtimeNoticeVO
	 */
	public RealtimeNoticeVO selectRealtimeNotice(RealtimeNoticeVO realtimeNoticeVO) {
		return realtimeNoticeMapper.selectRealtimeNotice(realtimeNoticeVO);
	}
	
	/**
	 * [관리자] 실시간공지서비스 등록
	 * 
	 * @param realtimeNoticeVO
	 */
	public int insertRealtimeNotice(RealtimeNoticeVO realtimeNoticeVO) {
		int rtn = 0;
		int newSn = realtimeNoticeMapper.selectRealtimeNoticeNewSn();
		
		// 1. SN 을 생성한다. 
		// int sn =  idgenService.getNextIntegerId();
		realtimeNoticeVO.setSn(newSn);
		
		// 2. 등록을 한다.
		rtn = realtimeNoticeMapper.insertRealtimeNotice(realtimeNoticeVO);

		// 3. 정상등록 후 등록 행이 첫번째 행이 아니라면 기존의 행의 최신구분을 변경한다.
/*
		if(rtn == 1 && newSn > 1) {
			realtimeNotice.setSn(newSn);
			realtimeNotice.setRecentCode("WRT02");
			realtimeNoticeDAO.updateRealtimeNoticeRecent(realtimeNotice);
		}
*/		
		return rtn;
	}
	
	/**
	 * [관리자] 실시간공지서비스 삭제
	 * 
	 * @param realtimeNoticeVO
	 */
	public int deleteRealtimeNotice(RealtimeNoticeVO realtimeNoticeVO) {
		return realtimeNoticeMapper.deleteRealtimeNotice(realtimeNoticeVO);
	}

	/**
	 * 실시간 공지서비스 메시지 조회
	 * 
	 */
	public RealtimeNoticeVO selectRealtimeNoticeMsg() {
		return realtimeNoticeMapper.selectRealtimeNoticeMsg();
	}
	
}
