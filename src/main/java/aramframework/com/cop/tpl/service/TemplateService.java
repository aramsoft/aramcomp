package aramframework.com.cop.tpl.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 템플릿 관리를 위한 서비스 인터페이스 클래스
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

public interface TemplateService {

	/**
	 * 템플릿에 대한 목록를 조회한다.
	 * 
	 * @param tmplatInfVO
	 */
	public List<EgovMap> selectTemplateInfs(TemplateInfVO templatInfVO);

	/**
	 * 템플릿에 대한 총횟수를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public int selectTemplateInfsCnt(TemplateInfVO templateInfVO);

	/**
	 * 템플릿에 대한 상세정보를 조회한다.
	 * 
	 * @param tmplatInfVO
	 */
	public TemplateInfVO selectTemplateInf(TemplateInfVO templatInfVO);

	/**
	 * 템플릿에 대한 미리보기 정보를 조회한다.
	 * 
	 * @param tmplatInfVO
	 */
	public TemplateInfVO selectTemplatePreview(TemplateInfVO templatInfVO);

	/**
	 * 템플릿 정보를 등록한다.
	 * 
	 * @param templatInfVO
	 */
	public void insertTemplateInf(TemplateInfVO templatInfVO);

	/**
	 * 템플릿 정보를 수정한다.
	 * 
	 * @param templatInfVO
	 */
	public void updateTemplateInf(TemplateInfVO templatInfVO);

	/**
	 * 템플릿 정보를 삭제한다.
	 * 
	 * @param templatInfVO
	 */
	public void deleteTemplateInf(TemplateInfVO templatInfVO);

}
