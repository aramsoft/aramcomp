package aramframework.com.cop.tpl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.tpl.dao.TemplateMapper;
import aramframework.com.cop.tpl.domain.TemplateInfVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 템플릿 정보관리를 위한 서비스 구현 클래스
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
public class TemplateService extends EgovAbstractServiceImpl {

	@Autowired 
	private TemplateMapper templateMapper;
	
	@Autowired 
	private EgovIdGnrService tmplatIdGnrService; 

	/**
	 * 템플릿에 대한 목록를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public List<EgovMap> selectTemplateInfs(TemplateInfVO templateInfVO) {
		return templateMapper.selectTemplateInfs(templateInfVO);
	}

	/**
	 * 템플릿에 대한 총횟수를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public int selectTemplateInfsCnt(TemplateInfVO templateInfVO) {
		return templateMapper.selectTemplateInfsCnt(templateInfVO);
	}

	/**
	 * 템플릿에 대한 상세정보를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public TemplateInfVO selectTemplateInf(TemplateInfVO templateInfVO) {
		TemplateInfVO resultVo = templateMapper.selectTemplateInf(templateInfVO);
		// searchVO 이전 
		resultVo.setSearchVO(templateInfVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 템플릿에 대한 미리보기 정보를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public TemplateInfVO selectTemplatePreview(TemplateInfVO templateInfVO) {
		return templateMapper.selectTemplatePreview(templateInfVO);
	}

	/**
	 * 템플릿 정보를 등록한다.
	 * 
	 * @param templateInfVO
	 */
	public void insertTemplateInf(TemplateInfVO templateInfVO) {
		try {
			templateInfVO.setTmplatId(tmplatIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		templateMapper.insertTemplateInf(templateInfVO);
	}

	/**
	 * 템플릿 정보를 수정한다.
	 * 
	 * @param templateInfVO
	 */
	public void updateTemplateInf(TemplateInfVO templateInfVO) {
		templateMapper.updateTemplateInf(templateInfVO);
	}

	/**
	 * 템플릿 정보를 삭제한다.
	 * 
	 * @param templateInfVO
	 */
	public void deleteTemplateInf(TemplateInfVO templateInfVO) {
		templateMapper.deleteTemplateInf(templateInfVO);
	}

}
