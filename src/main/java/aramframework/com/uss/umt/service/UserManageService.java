package aramframework.com.uss.umt.service;

import java.util.List;

/**
 * 사용자관리에 관한 인터페이스클래스를 정의한다.
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

public interface UserManageService {

	/**
	 * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param userManageVO
	 */
	public List<UserManageVO> selectUserList(UserManageVO userManageVO);

	/**
	 * 기 등록된 특정 사용자목록의 전체수를 확인
	 * 
	 * @param userManageVO
	 */
	public int selectUserListCnt(UserManageVO userManageVO);

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param userManageVO
	 */
	public UserManageVO selectUser(UserManageVO userManageVO);

	/**
	 * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param userManageVO
	 */
	public void insertUser(UserManageVO userManageVO);

	/**
	 * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param userManageVO
	 */
	public void updateUser(UserManageVO userManageVO);

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedIdForDel
	 */
	public void deleteUser(String checkedIdForDel);

	/**
	 * 사용자정보 수정시 히스토리 정보를 추가
	 * 
	 * @param userManageVO
	 */
	public void insertUserHistory(UserManageVO userManageVO);

	/**
	 * 사용자가 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param uniqId
	 */
	public UserManageVO selectPassword(String uniqId);

	/**
	 * 업무사용자 암호 수정
	 * 
	 * @param userManageVO
	 */
	public void updatePassword(UserManageVO userManageVO);

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * 
	 * @param checkId
	 */
	public int checkIdDplct(String checkId);

}