package aramframework.com.uss.ion.pwm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.pwm.dao.PopupManageMapper;
import aramframework.com.uss.ion.pwm.domain.PopupManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 팝업창에 대한 ServiceImpl을 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class PopupManageService extends EgovAbstractServiceImpl {

	@Autowired
	public PopupManageMapper popupManageMapper;	

	@Autowired
	private EgovIdGnrService popupManageIdGnrService; 

	/**
	 * 팝업창를 사용하기위해 위해 등록된 팝업창목록을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public List<EgovMap> selectPopupMainList(PopupManageVO popupManageVO) {
		return popupManageMapper.selectPopupMainList(popupManageVO);
	}

	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창목록을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public List<EgovMap> selectPopupList(PopupManageVO popupManageVO) {
		return popupManageMapper.selectPopupList(popupManageVO);
	}

	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창목록 갯수를 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public int selectPopupListCnt(PopupManageVO popupManageVO) {
		return (Integer) popupManageMapper.selectPopupListCnt(popupManageVO);
	}

	/**
	 * 팝업창을 사용자 화면에서 볼수 있는 정보들을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public PopupManageVO selectPopupDetail(PopupManageVO popupManageVO) {
		PopupManageVO resultVo = popupManageMapper.selectPopupDetail(popupManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(popupManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 팝업창정보를 신규로 등록한다.
	 * 
	 * @param popupManageVO
	 */
	public void insertPopup(PopupManageVO popupManageVO) {
		try {
			popupManageVO.setPopupId(popupManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		popupManageMapper.insertPopup(popupManageVO);
	}

	/**
	 * 기 등록된 팝업창정보를 수정한다.
	 * 
	 * @param popupManageVO
	 */
	public void updatePopup(PopupManageVO popupManageVO) {
		popupManageMapper.updatePopup(popupManageVO);
	}

	/**
	 * 기 등록된 팝업창정보를 삭제한다.
	 * 
	 * @param popupManageVO
	 */
	public void deletePopup(PopupManageVO popupManageVO) {
		popupManageMapper.deletePopup(popupManageVO);
	}

}