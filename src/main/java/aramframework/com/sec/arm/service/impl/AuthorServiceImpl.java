package aramframework.com.sec.arm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sec.arm.domain.AuthorVO;
import aramframework.com.sec.arm.service.AuthorService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한관리에 관한 ServiceImpl 클래스를 정의한다.
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

@Service("authorService")
public class AuthorServiceImpl extends EgovAbstractServiceImpl implements AuthorService {

	@Autowired
	private AuthorMapper authorMapper;
	
	/**
	 * 모든 권한목록을 조회한다.
	 * 
	 * @param authorVO
	 */
	public List<EgovMap> selectAuthorAllList(AuthorVO authorVO) {
		return authorMapper.selectAuthorAllList(authorVO);
	}
	
	/**
	 * 권한 목록을 조회한다.
	 * 
	 * @param authorVO
	 */
	public List<EgovMap> selectAuthorList(AuthorVO authorVO) {
		return authorMapper.selectAuthorList(authorVO);
	}

	/**
	 * 권한 목록 카운트를 조회한다.
	 * 
	 * @param authorVO
	 */
	public int selectAuthorListCnt(AuthorVO authorVO) {
		return authorMapper.selectAuthorListCnt(authorVO);
	}

	/**
	 * 권한을 조회한다.
	 * 
	 * @param authorVO
	 */
	public AuthorVO selectAuthor(AuthorVO authorVO) {
		AuthorVO resultVo = authorMapper.selectAuthor(authorVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, authorVO); 
		return resultVo;
	}

	/**
	 * 권한을 등록한다.
	 * 
	 * @param authorVO
	 */
	public void insertAuthor(AuthorVO authorVO) {
		authorMapper.insertAuthor(authorVO);
	}

	/**
	 * 권한을 수정한다.
	 * 
	 * @param authorVO
	 */
	public void updateAuthor(AuthorVO authorVO) {
		authorMapper.updateAuthor(authorVO);
	}

	/**
	 * 권한을 삭제한다.
	 * 
	 * @param authorVO
	 */
	public void deleteAuthor(AuthorVO authorVO) {
		authorMapper.deleteAuthor(authorVO);
	}

}
