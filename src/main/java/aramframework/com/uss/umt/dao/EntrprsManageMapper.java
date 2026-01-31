package aramframework.com.uss.umt.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.uss.umt.domain.EntrprsManageVO;
import aramframework.com.uss.umt.domain.StplatVO;

/**
 * 기업회원관리에 관한 데이터 접근 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface EntrprsManageMapper  {

	/**
	 * 기 등록된 특정 기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param entrprsManageVO
	 */
	public List<EntrprsManageVO> selectEntrprsMberList(EntrprsManageVO entrprsManageVO);

	/**
	 * 기업회원 총 갯수를 조회한다.
	 * 
	 * @param entrprsManageVO
	 */
	public int selectEntrprsMberListCnt(EntrprsManageVO entrprsManageVO);

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param entrprsManageVO
	 */
	public EntrprsManageVO selectEntrprsMber(EntrprsManageVO entrprsManageVO);
	
	/**
	 * 기업회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param entrprsManageVO
	 */
	public void insertEntrprsMber(EntrprsManageVO entrprsManageVO);

	/**
	 * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param entrprsManageVO
	 */
	public void updateEntrprsMber(EntrprsManageVO entrprsManageVO);

	/**
	 * 화면에 조회된 기업회원의 정보를 데이터베이스에서 삭제
	 * 
	 * @param delId
	 */
	public void deleteEntrprsMber(String delId);

	/**
	 * 기업회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param entrprsmberId
	 */
	public EntrprsManageVO selectPassword(String entrprsmberId);

	/**
	 * 기업회원 암호수정
	 * 
	 * @param passVO
	 */
	public void updatePassword(EntrprsManageVO passVO);

	/**
	 * 약관정보를 조회
	 * 
	 * @param stplatId
	 */
	public StplatVO selectStplat(String stplatId);

}