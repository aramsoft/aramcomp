package aramframework.com.cop.adb.service;

import java.util.List;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cop.adb.domain.AdressBookUserVO;
import aramframework.com.cop.adb.domain.AdressBookVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 주소록정보를 관리하기 위한 서비스 인터페이스 클래스
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

public interface AdressBookService {

	/**
	 * 주소록 목록을 조회한다.
	 * 
	 * @param adressBookVO
	 */
	public List<EgovMap> selectAdressBookList(AdressBookVO adressBookVO);

	/**
	 * 주소록 목록 총갯수을 조회한다.
	 * 
	 * @param adressBookVO
	 */
	public int selectAdressBookListCnt(AdressBookVO adressBookVO);

	/**
	 * 주소록 정보를 조회한다.
	 * 
	 * @param adressBookVO
	 */
	public AdressBookVO selectAdressBook(AdressBookVO adressBookVO);

	/**
	 * 주소록 정보를 등록한다.
	 * 
	 * @param adressBookVO
	 */
	public void insertAdressBook(AdressBookVO adressBookVO);

	/**
	 * 주소록 정보를 수정한다.
	 * 
	 * @param adressBookVO
	 */
	public void updateAdressBook(AdressBookVO adressBookVO);

	/**
	 * 주소록 정보를 삭제한다.
	 * 
	 * @param adressBookVO
	 */
	public void deleteAdressBook(AdressBookVO adressBookVO);

	/**
	 * 사용자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectManList(SearchVO searchVO);

	/**
	 * 사용자 목록 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectManListCnt(SearchVO searchVO);

	/**
	 * 명함 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectCardList(SearchVO searchVO);

	/**
	 * 명함 목록 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectCardListCnt(SearchVO searchVO);

	/**
	 * 주소록 구성원 정보를 불러온다.
	 * 
	 * @param id
	 */
	public AdressBookUserVO selectAdbkUser(String id);

}