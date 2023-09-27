package aramframework.com.cop.adb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.adb.dao.AdressBookMapper;
import aramframework.com.cop.adb.domain.AdressBookUserVO;
import aramframework.com.cop.adb.domain.AdressBookVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 주소록정보를 관리하기 위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class AdressBookService extends EgovAbstractServiceImpl {

	@Autowired
	private AdressBookMapper adressBookMapper;
		
	@Autowired
	private EgovIdGnrService adbkIdGnrService; 

	@Autowired
	private EgovIdGnrService adbkUserIdGnrService; 

	/**
	 * 주소록 목록을 조회한다.
	 * 
	 * @param adressBookVO	AdressBookVO
	 * @return				List
	 */
	public List<EgovMap> selectAdressBookList(AdressBookVO adressBookVO) {
		return adressBookMapper.selectAdressBookList(adressBookVO);
	}

	/**
	 * 주소록 목록 총갯수을 조회한다.
	 * 
	 * @param adressBookVO	AdressBookVO
	 * @return				전체 건수
	 */
	public int selectAdressBookListCnt(AdressBookVO adressBookVO) {
		return adressBookMapper.selectAdressBookListCnt(adressBookVO);
	}

	/**
	 * 주소록 정보를 조회한다.
	 * 
	 * @param adressBookVO	AdressBookVO
	 * @return				AdressBookVO
	 */
	public AdressBookVO selectAdressBook(AdressBookVO adressBookVO) {
		AdressBookVO resultVo = adressBookMapper.selectAdressBook(adressBookVO);
		resultVo.setAdbkUserList(adressBookMapper.selectUserList(adressBookVO));
		return resultVo;
	}

	/**
	 * 주소록 정보를 등록한다.
	 * 
	 * @param adressBookVO	AdressBookVO
	 */
	public void insertAdressBook(AdressBookVO adressBookVO){

		try {
			adressBookVO.setAdbkId(adbkIdGnrService.getNextStringId());
			adressBookVO.setUseAt("Y");
	
			adressBookMapper.insertAdressBook(adressBookVO);
	
			AdressBookUserVO newAdbkUserVO = new AdressBookUserVO();
			for (int i = 0; i < adressBookVO.getAdbkUserList().size(); i++) {
				newAdbkUserVO = adressBookVO.getAdbkUserList().get(i);
				newAdbkUserVO.setAdbkUserId(adbkUserIdGnrService.getNextStringId());
				newAdbkUserVO.setAdbkId(adressBookVO.getAdbkId());
				adressBookMapper.insertAdressBookUser(newAdbkUserVO);
			}
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 주소록 정보를 수정한다.
	 * 
	 * @param adressBookVO	AdressBookVO
	 */
	public void updateAdressBook(AdressBookVO adressBookVO) {

		adressBookMapper.updateAdressBook(adressBookVO);

		List<AdressBookUserVO> oldUserList = adressBookMapper.selectUserList(adressBookVO);

		AdressBookUserVO newAdbkUserVO = null;
		AdressBookUserVO oldAdbkUserVO = null;
		try {
			for (int i = 0; i < adressBookVO.getAdbkUserList().size(); i++) {
				newAdbkUserVO = adressBookVO.getAdbkUserList().get(i);
	
				boolean exist = false;
				for (int j = 0; j < oldUserList.size(); j++) {
					oldAdbkUserVO = oldUserList.get(j);
					if (newAdbkUserVO.getEmplyrId().equals(oldAdbkUserVO.getEmplyrId())
							&& newAdbkUserVO.getNcrdId().equals(oldAdbkUserVO.getNcrdId())) {
						exist = true;
						break;
					}
				}
				
				// old에는 없고 new에는 있는 경우 새로 추가
				if (!exist) {
					newAdbkUserVO.setAdbkUserId(adbkUserIdGnrService.getNextStringId());
					newAdbkUserVO.setAdbkId(adressBookVO.getAdbkId());
					adressBookMapper.insertAdressBookUser(newAdbkUserVO);
				}
			}
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}

		for (int i = 0; i < oldUserList.size(); i++) {
			oldAdbkUserVO = oldUserList.get(i);

			boolean exist = false;
			for (int j = 0; j < adressBookVO.getAdbkUserList().size(); j++) {
				newAdbkUserVO = adressBookVO.getAdbkUserList().get(j);
				if (oldAdbkUserVO.getEmplyrId().equals(newAdbkUserVO.getEmplyrId())
						&& oldAdbkUserVO.getNcrdId().equals(newAdbkUserVO.getNcrdId())) {
					exist = true;
					break;
				}
			}
			
			// old에는 있고 new에는 없는 경우 삭제
			if (!exist) {
				adressBookMapper.deleteAdressBookUser(oldAdbkUserVO);
			}
		}
	}

	/**
	 * 주소록 정보를 삭제한다.
	 * 
	 * @param adressBookVO	AdressBookVO
	 */
	public void deleteAdressBook(AdressBookVO adressBookVO) {
		adressBookMapper.deleteAdressBook(adressBookVO);
	}

	/**
	 * 사용자 목록을 조회한다.
	 * 
	 * @param adressBookUserVO	AdressBookUserVO
	 * @return					List
	 */
	public List<EgovMap> selectManList(AdressBookUserVO adressBookUserVO) {
		return adressBookMapper.selectManList(adressBookUserVO);
	}

	/**
	 * 사용자 목록 총갯수을 조회한다.
	 * 
	 * @param adressBookUserVO	AdressBookUserVO
	 * @return					전체 건수
	 */
	public int selectManListCnt(AdressBookUserVO adressBookUserVO) {
		return adressBookMapper.selectManListCnt(adressBookUserVO);
	}
	
	/**
	 * 명함 목록을 조회한다.
	 * 
	 * @param adressBookUserVO	AdressBookUserVO
	 * @return					List
	 */
	public List<EgovMap> selectCardList(AdressBookUserVO adressBookUserVO) {
		return adressBookMapper.selectCardList(adressBookUserVO);
	}

	/**
	 * 명함 목록 총갯수을 조회한다.
	 * 
	 * @param adressBookUserVO	AdressBookUserVO
	 * @return					전체 건수
	 */
	public int selectCardListCnt(AdressBookUserVO adressBookUserVO) {
		return adressBookMapper.selectCardListCnt(adressBookUserVO);
	}
	
	/**
	 * 주소록 구성원 정보를 불러온다.
	 * 
	 * @param id	String
	 * @return		AdressBookUserVO
	 */
	public AdressBookUserVO selectAdbkUser(String id) {

		AdressBookUserVO adbkUser = null;

		if (id.length() > 4 && id.substring(0, 4).equals("NCRD")) {
			adbkUser = adressBookMapper.selectCardUser(id);
		} else {
			adbkUser = adressBookMapper.selectManUser(id);
		}

		return adbkUser;
	}

}
