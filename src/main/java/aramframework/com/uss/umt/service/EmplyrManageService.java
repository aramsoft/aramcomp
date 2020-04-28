package aramframework.com.uss.umt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.umt.dao.EntrprsManageMapper;
import aramframework.com.uss.umt.dao.MberManageMapper;
import aramframework.com.uss.umt.dao.EmplyrManageMapper;
import aramframework.com.uss.umt.domain.EmplyrManageVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자관리에 관한 비지니스 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class EmplyrManageService extends EgovAbstractServiceImpl {

	@Autowired
	private EmplyrManageMapper emplyrManageMapper;	

	@Autowired
	private MberManageMapper mberManageMapper;	

	@Autowired
	private EntrprsManageMapper entrprsManageMapper;	

	/**
	 * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param emplyrManageVO
	 */
	public List<EmplyrManageVO> selectEmplyrList(EmplyrManageVO emplyrManageVO) {
		return  emplyrManageMapper.selectEmplyrList(emplyrManageVO);
	}

	/**
	 * 기 등록된 특정 사용자목록의 전체수를 확인
	 * 
	 * @param emplyrManageVO
	 */
	public int selectEmplyrListCnt(EmplyrManageVO emplyrManageVO) {
		return emplyrManageMapper.selectEmplyrListCnt(emplyrManageVO);
	}

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param emplyrManageVO
	 */
	public EmplyrManageVO selectEmplyr(EmplyrManageVO emplyrManageVO) {
		return emplyrManageMapper.selectEmplyr(emplyrManageVO);
	}

	/**
	 * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param emplyrManageVO
	 */
	public void insertEmplyr(EmplyrManageVO emplyrManageVO) {
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(emplyrManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		emplyrManageVO.setPassword(pass);
		emplyrManageMapper.insertEmplyr(emplyrManageVO);
	}

	/**
	 * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param emplyrManageVO
	 */
	public void updateEmplyr(EmplyrManageVO emplyrManageVO) {
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(emplyrManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		emplyrManageVO.setPassword(pass);
		emplyrManageMapper.updateEmplyr(emplyrManageVO);
	}

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedIdForDel
	 */
	public void deleteEmplyr(EmplyrManageVO emplyrManageVO) {
		emplyrManageMapper.deleteEmplyrHistory(emplyrManageVO.getEmplyrId());
		emplyrManageMapper.deleteEmplyr(emplyrManageVO.getEmplyrId());
	}

	/**
	 * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedIdForDel
	 */
	public void deleteIdsAll(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			String[] id = ids[i].split("-");
			if (id[0].equals("USR03")) {
				// 업무사용자(직원)삭제
				emplyrManageMapper.deleteEmplyrHistory(id[1]);
				emplyrManageMapper.deleteEmplyr(id[1]);
			} else if (id[0].equals("USR01")) {
				// 일반회원삭제
				mberManageMapper.deleteMber(id[1]);
			} else if (id[0].equals("USR02")) {
				// 기업회원삭제
				entrprsManageMapper.deleteEntrprsMber(id[1]);
			}
		}
	}
	
	/**
	 * 사용자정보 수정시 히스토리 정보를 추가
	 * 
	 * @param emplyrManageVO
	 */
	public void insertEmplyrHistory(EmplyrManageVO emplyrManageVO) {
		emplyrManageMapper.insertEmplyrHistory(emplyrManageVO);
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * 
	 * @param checkId
	 */
	public int checkIdDplct(String checkId) {
		return emplyrManageMapper.checkIdDplct(checkId);
	}

	/**
	 * 사용자가 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param emplyrId
	 */
	public EmplyrManageVO selectPassword(String emplyrId) {
		return emplyrManageMapper.selectPassword(emplyrId);
	}

	/**
	 * 업무사용자 암호 수정
	 * 
	 * @param emplyrManageVO
	 */
	public void updatePassword(EmplyrManageVO emplyrManageVO) {
		emplyrManageMapper.updatePassword(emplyrManageVO);
	}

}