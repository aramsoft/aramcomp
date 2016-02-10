package aramframework.com.cop.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.domain.CommunityVO;
import aramframework.com.cop.cmy.service.CommunityManageService;
import aramframework.com.cop.com.dao.ConfirmMapper;
import aramframework.com.cop.com.domain.ConfirmHistoryVO;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 승인정보를 관리하기 위한 서비스 구현 클래스
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

@Service
public class ConfirmService extends EgovAbstractServiceImpl {

	@Autowired 
	private ConfirmMapper confirmMapper;
	
	@Autowired 
	private CommunityManageService cmmntyService;

	/**
	 * 승인(탈퇴)요청에 대한 목록을 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public List<EgovMap> selectConfirmRequestList(ConfirmHistoryVO confirmHistoryVO) {
		return confirmMapper.selectConfirmRequestList(confirmHistoryVO);
	}

	/**
	 * 승인(탈퇴)요청에 대한 목록 총갯수를 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public int selectConfirmRequestListCnt(ConfirmHistoryVO confirmHistoryVO) {
		return confirmMapper.selectConfirmRequestListCnt(confirmHistoryVO);
	}

	/**
	 * 승인(탈퇴)요청에 대한 상세내용을 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public ConfirmHistoryVO selectSingleConfirmRequest(ConfirmHistoryVO confirmHistoryVO) {
		ConfirmHistoryVO resultVo = confirmMapper.selectSingleConfirmRequest(confirmHistoryVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, confirmHistoryVO); 
		return resultVo;
	}

	/**
	 * 승인(탈퇴)요청에 대한 등록을 처리한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public void insertConfirmRequest(ConfirmHistoryVO confirmHistoryVO) {
		confirmMapper.insertConfirmRequest(confirmHistoryVO);
	}

	/**
	 * 승인(탈퇴)요청에 대한 확인을 처리한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public void updateConfirmRequest(ConfirmHistoryVO confirmHistoryVO) {
		String sttus = confirmHistoryVO.getConfmSttusCode();

		// 승인요청이면 아무것도 처리하지 않음
		if ("AP01".equals(sttus)) {
			return;
		}

		// sttus 가 승인완료 AP02일때만 로직처리/ 승인반려시에는 승인정보만 변경처리

		if ("AP02".equals(sttus)) {
			if ("CF12".equals(confirmHistoryVO.getConfmTyCode())) {
				// 커뮤니티 사용자 탈퇴처리
				CommunityUserVO communityUserVO = new CommunityUserVO();

				communityUserVO.setLastUpdusrId(confirmHistoryVO.getConfmerId());
				communityUserVO.setCmmntyId(confirmHistoryVO.getTrgetJobId());
				communityUserVO.setEmplyrId(confirmHistoryVO.getConfmRqesterId());
				communityUserVO.setSecsnDe(DateUtil.getToday());
				communityUserVO.setMngrAt("N"); // 커뮤니티 운영자가 아닌 것으로 강제 설정(2011.9.7 추가 사항)

				cmmntyService.deleteCommunityUserInf(communityUserVO);

			} else if ("CF02".equals(confirmHistoryVO.getConfmTyCode())) {
				// 커뮤니티 삭제
				CommunityVO communityVO = new CommunityVO();

				communityVO.setLastUpdusrId(confirmHistoryVO.getConfmerId());
				communityVO.setCmmntyId(confirmHistoryVO.getTrgetJobId());

				cmmntyService.deleteCommunityInf(communityVO);

			} else if ("CF11".equals(confirmHistoryVO.getConfmTyCode())) {
				// 커뮤니티 가입
				CommunityUserVO communityUserVO = new CommunityUserVO();

				communityUserVO.setCmmntyId(confirmHistoryVO.getTrgetJobId());
				communityUserVO.setEmplyrId(confirmHistoryVO.getConfmRqesterId());
				communityUserVO.setMngrAt("N");
				communityUserVO.setUseAt("Y");
				communityUserVO.setFrstRegisterId(confirmHistoryVO.getConfmRqesterId());

				cmmntyService.insertCommunityUserInf(communityUserVO);

			}
		}

		confirmHistoryVO.setConfmDe(DateUtil.getToday());

		confirmMapper.updateConfirmRequest(confirmHistoryVO);
	}

	/**
	 * 현재 승인 요청된 건수를 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public int countConfirmRequest(ConfirmHistoryVO confirmHistoryVO) {
		return confirmMapper.countConfirmRequest(confirmHistoryVO);
	}
	
}
