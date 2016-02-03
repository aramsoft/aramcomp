package aramframework.com.uss.umt.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.uss.umt.domain.MberManageVO;
import aramframework.com.uss.umt.domain.StplatVO;

/**
 * 일반회원관리에 관한 데이터 접근 클래스를 정의한다.
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
public interface MberManageMapper {

	/**
	 * 기 등록된 특정 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param mberManageVO
	 */
	public List<MberManageVO> selectMberList(MberManageVO mberManageVO);

	/**
	 * 일반회원 총 갯수를 조회한다.
	 * 
	 * @param mberManageVO
	 */
	public int selectMberListCnt(MberManageVO mberManageVO);

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param mberManageVO
	 */
	public MberManageVO selectMber(MberManageVO mberManageVO);

	/**
	 * 일반회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param mberManageVO
	 */
	public void insertMber(MberManageVO mberManageVO);

	/**
	 * 화면에 조회된일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param mberManageVO
	 */
	public void updateMber(MberManageVO mberManageVO);

	/**
	 * 화면에 조회된 일반회원의 정보를 데이터베이스에서 삭제
	 * 
	 * @param delId
	 */
	public void deleteMber(String delId);

	/**
	 * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param mberId
	 */
	public MberManageVO selectPassword(String mberId);

	/**
	 * 일반회원 암호수정
	 * 
	 * @param passVO
	 */
	public void updatePassword(MberManageVO passVO);

	/**
	 * 일반회원 약관확인
	 * 
	 * @param stplatId
	 */
	public StplatVO selectStplat(String stplatId);

}