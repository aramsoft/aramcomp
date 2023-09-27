package aramframework.com.uss.umt.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.uss.umt.domain.EmplyrManageVO;

/**
 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface EmplyrManageMapper {

	/**
	 * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param emplyrManageVO
	 */
	public List<EmplyrManageVO> selectEmplyrList(EmplyrManageVO emplyrManageVO);

	/**
	 * 사용자총 갯수를 조회한다.
	 * 
	 * @param emplyrManageVO
	 */
	public int selectEmplyrListCnt(EmplyrManageVO emplyrManageVO);

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 사용자들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param emplyrManageVO
	 */
	public EmplyrManageVO selectEmplyr(EmplyrManageVO emplyrManageVO);

	/**
	 * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param emplyrManageVO
	 */
	public void insertEmplyr(EmplyrManageVO emplyrManageVO);

	/**
	 * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param emplyrManageVO
	 */
	public void updateEmplyr(EmplyrManageVO emplyrManageVO);

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * 
	 * @param emplyrId
	 */
	public void deleteEmplyr(String emplyrId);

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * 
	 * @param emplyrId
	 */
	public void deleteEmplyrHistory(String emplyrId);

	/**
	 * 사용자정보 수정시 히스토리 정보를 추가
	 * 
	 * @param emplyrManageVO
	 */
	public void insertEmplyrHistory(EmplyrManageVO emplyrManageVO);

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * 
	 * @param emplyrId
	 */
	public int checkIdDplct(String emplyrId);

	/**
	 * 업무사용자가 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param emplyrId
	 */
	public EmplyrManageVO selectPassword(String emplyrId);

	/**
	 * 업무사용자 암호수정
	 * 
	 * @param emplyrManageVO
	 */
	public void updatePassword(EmplyrManageVO emplyrManageVO);

}