package aramframework.com.cop.adb.dao;

import java.util.List;

import aramframework.com.cop.adb.domain.AdressBookUserVO;
import aramframework.com.cop.adb.domain.AdressBookVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 주소록을 관리하는 서비스를 정의하기위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface AdressBookMapper {

	/**
	 * 주어진 조건에 따른 주소록목록을 불러온다.
	 * 
	 * @param adressBookVO
	 */
	public List<EgovMap> selectAdressBookList(AdressBookVO adressBookVO);

	/**
	 * 주소록 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param adressBookVO
	 */
	public int selectAdressBookListCnt(AdressBookVO adressBookVO);

	/**
	 * 주어진 조건에 맞는 주소록을 불러온다.
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
	 * 주어진 조건에 따라 주소록에 기등록된 구성원의 목록을 불러온다.
	 * 
	 * @param adressBookVO
	 */
	public List<AdressBookUserVO> selectUserList(AdressBookVO adressBookVO);

	/**
	 * 주소록을 구성하는 구성원을 등록한다.
	 * 
	 * @param adressBookUserVO
	 */
	public void insertAdressBookUser(AdressBookUserVO adressBookUserVO);

	/**
	 * 주소록 구성원을 삭제한다.
	 * 
	 * @param adressBookUserVO
	 */
	public void deleteAdressBookUser(AdressBookUserVO adressBookUserVO);

	/**
	 * 주어진 조건에 따라 주소록에 추가할 사용자목록을 불러온다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectManList(AdressBookUserVO adressBookUserVO);

	/**
	 * 사용자 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectManListCnt(AdressBookUserVO adressBookUserVO);

	/**
	 * 주소록을 구성할 사용자의 정보를 조회한다.
	 * 
	 * @param id
	 */
	public AdressBookUserVO selectManUser(String id);

	/**
	 * 주어진 조건에 따라 주소록에 추가할 명함목록을 불러온다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectCardList(AdressBookUserVO adressBookUserVO);

	/**
	 * 명함 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectCardListCnt(AdressBookUserVO adressBookUserVO);

	/**
	 * 주소록을 구성할 명함의 정보를 조회한다.
	 * 
	 * @param id
	 */
	public AdressBookUserVO selectCardUser(String id);

}
