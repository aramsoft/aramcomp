package aramframework.com.uss.umt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.umt.dao.MberManageMapper;
import aramframework.com.uss.umt.domain.MberManageVO;
import aramframework.com.uss.umt.domain.StplatVO;
import aramframework.com.utl.sim.service.FileScrty;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 일반회원관리에 관한비지니스클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class MberManageService extends EgovAbstractServiceImpl {

	@Autowired
	private MberManageMapper mberManageMapper;	

	/**
	 * 기 등록된 회원 중 검색조건에 맞는 회원들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param mberManageVO
	 */
	public List<MberManageVO> selectMberList(MberManageVO mberManageVO) {
		return mberManageMapper.selectMberList(mberManageVO);
	}

	/**
	 * 일반회원 총 갯수를 조회한다.
	 * 
	 * @param mberManageVO
	 */
	public int selectMberListCnt(MberManageVO mberManageVO) {
		return mberManageMapper.selectMberListCnt(mberManageVO);
	}

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param mberManageVO
	 */
	public MberManageVO selectMber(MberManageVO mberManageVO) {
		return mberManageMapper.selectMber(mberManageVO);
	}

	/**
	 * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param mberManageVO
	 */
	public void insertMber(MberManageVO mberManageVO) {
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(mberManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mberManageVO.setPassword(pass);

		mberManageMapper.insertMber(mberManageVO);
	}

	/**
	 * 화면에 조회된 일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param mberManageVO
	 */
	public void updateMber(MberManageVO mberManageVO) {
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(mberManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mberManageVO.setPassword(pass);

		mberManageMapper.updateMber(mberManageVO);
	}

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedIdForDel
	 */
	public void deleteMber(MberManageVO mberManageVO) {
		// 일반회원삭제
		mberManageMapper.deleteMber(mberManageVO.getMberId());
	}

	/**
	 * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param mberId
	 */
	public MberManageVO selectPassword(String mberId) {
		return  mberManageMapper.selectPassword(mberId);
	}

	/**
	 * 일반회원암호수정
	 * 
	 * @param mberManageVO
	 */
	public void updatePassword(MberManageVO mberManageVO) {
		mberManageMapper.updatePassword(mberManageVO);
	}

	/**
	 * 일반회원 약관확인
	 * 
	 * @param stplatId
	 */
	public StplatVO selectStplat(String stplatId) {
		return mberManageMapper.selectStplat(stplatId);
	}

}