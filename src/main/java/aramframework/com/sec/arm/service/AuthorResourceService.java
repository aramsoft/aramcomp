package aramframework.com.sec.arm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sec.arm.dao.AuthorResourceMapper;
import aramframework.com.sec.arm.domain.AuthorResourceVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한별 롤관리에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class AuthorResourceService extends EgovAbstractServiceImpl {

	@Autowired
	private AuthorResourceMapper authorResourceMapper;

	/**
	 * 권한 롤 관계정보 목록 조회
	 * 
	 * @param authorResourceVO
	 */
	public List<EgovMap> selectAuthorResourceList(AuthorResourceVO authorResourceVO) {
		return authorResourceMapper.selectAuthorResourceList(authorResourceVO);
	}

	/**
	 * 목록조회 카운트를 반환한다
	 * 
	 * @param authorResourceVO
	 */
	public int selectAuthorResourceListCnt(AuthorResourceVO authorResourceVO) {
		return authorResourceMapper.selectAuthorResourceListCnt(authorResourceVO);
	}

	/**
	 * 권한 롤 관계정보를 조회
	 * 
	 * @param authorResourceVO
	 */
	public AuthorResourceVO selectAuthorResource(AuthorResourceVO authorResourceVO) {
		return authorResourceMapper.selectAuthorResource(authorResourceVO);
	}

	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param authorResourceVO
	 */
	public void insertAuthorResource(AuthorResourceVO authorResourceVO) {
		authorResourceMapper.insertAuthorResource(authorResourceVO);
	}

	/**
	 * 수정된 권한 롤 관계정보를 데이터베이스에 반영
	 * 
	 * @param authorResourceVO
	 */
	public void updateAuthorResource(AuthorResourceVO authorResourceVO) {
		authorResourceMapper.updateAuthorResource(authorResourceVO);
	}

	/**
	 * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param authorResourceVO
	 */
	public void deleteAuthorResource(AuthorResourceVO authorResourceVO) {
		authorResourceMapper.deleteAuthorResource(authorResourceVO);
	}

	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param authorResourceVO
	 */
	public void insertAuthorResources(String authorCode, String[] resourceCodes, String[] regYns) {
		AuthorResourceVO suthorResourceVO = new AuthorResourceVO();
		suthorResourceVO.setAuthorCode(authorCode);
		for (int i = 0; i < resourceCodes.length; i++) {
			suthorResourceVO.setResourceCode(resourceCodes[i]);
			suthorResourceVO.setRegYn(regYns[i]);

			deleteAuthorResource(suthorResourceVO);// 2011.09.07
			if (regYns[i].equals("Y")) {
				insertAuthorResource(suthorResourceVO);
			} 
		}
	}

}