package aramframework.com.uss.umt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.umt.dao.EntrprsManageMapper;
import aramframework.com.uss.umt.dao.MberManageMapper;
import aramframework.com.uss.umt.dao.UserManageMapper;
import aramframework.com.uss.umt.domain.EntrprsManageVO;
import aramframework.com.uss.umt.domain.StplatVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 기업회원관리에 관한 비지니스클래스를 정의한다.
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

@Service
public class EntrprsManageService extends EgovAbstractServiceImpl {

	@Autowired
	private UserManageMapper userManageMapper;
	
	@Autowired
	private MberManageMapper mberManageMapper;
	
	@Autowired
	private EntrprsManageMapper entrprsManageMapper;
	
	/** egovUsrCnfrmIdGnrService */
	@Autowired
	private EgovIdGnrService usrCnfrmIdGnrService; 

	/**
	 * 기 등록된기업 회원 중 검색조건에 맞는 회원들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param entrprsManageVO
	 */
	public List<EntrprsManageVO> selectEntrprsMberList(EntrprsManageVO entrprsManageVO) {
		return entrprsManageMapper.selectEntrprsMberList(entrprsManageVO);
	}

	/**
	 * 기업회원 총 갯수를 조회한다.
	 * 
	 * @param entrprsManageVO
	 */
	public int selectEntrprsMberListCnt(EntrprsManageVO entrprsManageVO) {
		return entrprsManageMapper.selectEntrprsMberListCnt(entrprsManageVO);
	}

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param entrprsManageVO
	 */
	public EntrprsManageVO selectEntrprsMber(EntrprsManageVO entrprsManageVO) {
		EntrprsManageVO resultVo = entrprsManageMapper.selectEntrprsMber(entrprsManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(entrprsManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 기업회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param entrprsManageVO
	 */
	public void insertEntrprsMber(EntrprsManageVO entrprsManageVO) {
		// 고유아이디 셋팅
		try {
			entrprsManageVO.setUniqId(usrCnfrmIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(entrprsManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		entrprsManageVO.setPassword(pass);

		entrprsManageMapper.insertEntrprsMber(entrprsManageVO);
	}

	/**
	 * 화면에 조회된 기업회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param entrprsManageVO
	 */
	public void updateEntrprsMber(EntrprsManageVO entrprsManageVO) {
		// 패스워드 암호화
		String pass;
		try {
			pass = FileScrty.encryptPassword(entrprsManageVO.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		entrprsManageVO.setPassword(pass);
		entrprsManageMapper.updateEntrprsMber(entrprsManageVO);
	}

	/**
	 * 화면에 조회된 기업회원의 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedIdForDel
	 */
	public void deleteEntrprsMber(EntrprsManageVO entrprsManageVO) {
		entrprsManageMapper.deleteEntrprsMber(entrprsManageVO.getUniqId());
	}

	/**
	 * 기업회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
	 * 
	 * @param entrprsmberId
	 */
	public EntrprsManageVO selectPassword(String entrprsmberId) {
		return  entrprsManageMapper.selectPassword(entrprsmberId);
	}

	/**
	 * 기업회원 암호 수정
	 * 
	 * @param entrprsManageVO
	 */
	public void updatePassword(EntrprsManageVO entrprsManageVO) {
		entrprsManageMapper.updatePassword(entrprsManageVO);
	}

	/**
	 * 기업회원용 약관정보 조회
	 * 
	 * @param stplatId
	 */
	public StplatVO selectStplat(String stplatId) {
		return  entrprsManageMapper.selectStplat(stplatId);
	}

}