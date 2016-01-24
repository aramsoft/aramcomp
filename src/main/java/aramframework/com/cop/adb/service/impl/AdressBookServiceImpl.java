package aramframework.com.cop.adb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.adb.service.AdressBookUserVO;
import aramframework.com.cop.adb.service.AdressBookVO;
import aramframework.com.cop.adb.service.AdressBookService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 주소록정보를 관리하기 위한 서비스 구현 클래스
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

@Service("adressBookService")
public class AdressBookServiceImpl extends EgovAbstractServiceImpl implements AdressBookService {

	@Autowired
	private AdressBookMapper adressBookMapper;
		
	@Autowired
	private EgovIdGnrService adbkIdGnrService; 

	@Autowired
	private EgovIdGnrService adbkUserIdGnrService; 

	/**
	 * 주소록 목록을 조회한다.
	 * 
	 * @param adressBookVO
	 */
	public List<EgovMap> selectAdressBookList(AdressBookVO adressBookVO) {
		return adressBookMapper.selectAdressBookList(adressBookVO);
	}

	/**
	 * 주소록 목록 총갯수을 조회한다.
	 * 
	 * @param adressBookVO
	 */
	public int selectAdressBookListCnt(AdressBookVO adressBookVO) {
		return adressBookMapper.selectAdressBookListCnt(adressBookVO);
	}

	/**
	 * 주소록 정보를 조회한다.
	 * 
	 * @param adressBookVO
	 */
	public AdressBookVO selectAdressBook(AdressBookVO adressBookVO) {
		AdressBookVO resultVo = adressBookMapper.selectAdressBook(adressBookVO);
		resultVo.setAdbkMan(adressBookMapper.selectUserList(adressBookVO));
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, adressBookVO); 
		return resultVo;
	}

	/**
	 * 주소록 정보를 등록한다.
	 * 
	 * @param adressBookVO
	 */
	public void insertAdressBook(AdressBookVO adressBookVO){

		try {
			adressBookVO.setAdbkId(adbkIdGnrService.getNextStringId());
			adressBookVO.setUseAt("Y");
	
			adressBookMapper.insertAdressBook(adressBookVO);
	
			AdressBookUserVO adbkUserVO = new AdressBookUserVO();
			for (int i = 0; i < adressBookVO.getAdbkMan().size(); i++) {
				adbkUserVO = adressBookVO.getAdbkMan().get(i);
				adbkUserVO.setAdbkUserId(adbkUserIdGnrService.getNextStringId());
				adbkUserVO.setAdbkId(adressBookVO.getAdbkId());
				adressBookMapper.insertAdressBookUser(adbkUserVO);
			}
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 주소록 정보를 수정한다.
	 * 
	 * @param adressBookVO
	 */
	public void updateAdressBook(AdressBookVO adressBookVO) {

		adressBookMapper.updateAdressBook(adressBookVO);

		List<AdressBookUserVO> temp = adressBookMapper.selectUserList(adressBookVO);

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getEmplyrId() == null)
				temp.get(i).setEmplyrId("");

			if (temp.get(i).getNcrdId() == null)
				temp.get(i).setNcrdId("");
		}

		AdressBookUserVO adbkUserVO = null;
		for (int i = 0; i < adressBookVO.getAdbkMan().size(); i++) {
			adbkUserVO = adressBookVO.getAdbkMan().get(i);
			if (adbkUserVO.getEmplyrId() == null)
				adbkUserVO.setEmplyrId("");

			if (adbkUserVO.getNcrdId() == null)
				adbkUserVO.setNcrdId("");
		}

		AdressBookUserVO tempUserVO = null;
		try {
			for (int i = 0; i < adressBookVO.getAdbkMan().size(); i++) {
				adbkUserVO = adressBookVO.getAdbkMan().get(i);
	
				boolean check = false;
				for (int j = 0; j < temp.size(); j++) {
					tempUserVO = temp.get(j);
					if (adbkUserVO.getEmplyrId().equals(tempUserVO.getEmplyrId())
							&& adbkUserVO.getNcrdId().equals(tempUserVO.getNcrdId())) {
						check = true;
						break;
					}
				}
				
				if (!check) {
					adbkUserVO.setAdbkUserId(adbkUserIdGnrService.getNextStringId());
					adbkUserVO.setAdbkId(adressBookVO.getAdbkId());
					adressBookMapper.insertAdressBookUser(adbkUserVO);
				}
			}
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}

		for (int i = 0; i < temp.size(); i++) {
			tempUserVO = temp.get(i);

			boolean check = false;
			for (int j = 0; j < adressBookVO.getAdbkMan().size(); j++) {
				adbkUserVO = adressBookVO.getAdbkMan().get(j);
				if (tempUserVO.getEmplyrId().equals(adbkUserVO.getEmplyrId())
						&& tempUserVO.getNcrdId().equals(adbkUserVO.getNcrdId())) {
					check = true;
					break;
				}
			}
			
			if (!check) {
				adressBookMapper.deleteAdressBookUser(tempUserVO);
			}
		}
	}

	/**
	 * 주소록 정보를 삭제한다.
	 * 
	 * @param adressBookVO
	 */
	public void deleteAdressBook(AdressBookVO adressBookVO) {
		adressBookMapper.updateAdressBook(adressBookVO);
	}

	/**
	 * 사용자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectManList(SearchVO searchVO) {
		return adressBookMapper.selectManList(searchVO);
	}

	/**
	 * 사용자 목록 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectManListCnt(SearchVO searchVO) {
		return adressBookMapper.selectManListCnt(searchVO);
	}
	
	/**
	 * 명함 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectCardList(SearchVO searchVO) {
		return adressBookMapper.selectCardList(searchVO);
	}

	/**
	 * 명함 목록 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectCardListCnt(SearchVO searchVO) {
		return adressBookMapper.selectCardListCnt(searchVO);
	}
	
	/**
	 * 주소록 구성원 정보를 불러온다.
	 * 
	 * @param id
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
