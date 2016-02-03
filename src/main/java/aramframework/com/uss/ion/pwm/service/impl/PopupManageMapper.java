package aramframework.com.uss.ion.pwm.service.impl;

import java.util.List;

import aramframework.com.uss.ion.pwm.domain.PopupManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 팝업창에 대한 DAO를 정의한다.
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
public interface PopupManageMapper {

	/**
	 * 팝업창를 사용하기 위해 등록된 팝업창목록을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public List<EgovMap> selectPopupMainList(PopupManageVO popupManageVO);
	
	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창목록을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public List<EgovMap> selectPopupList(PopupManageVO popupManageVO);

	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창목록 총갯수를 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public int selectPopupListCnt(PopupManageVO popupManageVO);

	/**
	 * 팝업창을 사용자 화면에서 볼수 있는 정보들을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	public PopupManageVO selectPopupDetail(PopupManageVO popupManageVO);
	
	/**
	 * 팝업창정보를 신규로 등록한다.
	 * 
	 * @param popupManageVO
	 */
	public void insertPopup(PopupManageVO popupManageVO);

	/**
	 * 기 등록된 팝업창정보를 수정한다.
	 * 
	 * @param popupManageVO
	 */
	public void updatePopup(PopupManageVO popupManageVO);

	/**
	 * 기 등록된 팝업창정보를 삭제한다.
	 * 
	 * @param popupManageVO
	 */
	public void deletePopup(PopupManageVO popupManageVO);

}