package aramframework.com.cop.tpl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.tpl.dao.TemplateMapper;
import aramframework.com.cop.tpl.domain.TemplateInfVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 템플릿 정보관리를 위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
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
		return templateMapper.selectTemplateInf(templateInfVO);
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
