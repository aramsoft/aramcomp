package aramframework.com.cop.tpl.service.impl;

import java.util.List;

import aramframework.com.cop.tpl.domain.TemplateInfVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 템플릿 정보관리를 위한 데이터 접근 클래스
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
public interface TemplateMapper {

	/**
	 * 템플릿에 대한 목록를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public List<EgovMap> selectTemplateInfs(TemplateInfVO templateInfVO);

	/**
	 * 템플릿에 대한 목록 전체 건수를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public int selectTemplateInfsCnt(TemplateInfVO templateInfVO);

	/**
	 * 템플릿에 대한 상세정보를 조회한다.
	 * 
	 * @param templateInfVO
	 */
	public TemplateInfVO selectTemplateInf(TemplateInfVO templateInfVO);

	/**
	 * 템플릿 정보를 등록한다.
	 * 
	 * @param templateInfVO
	 */
	public void insertTemplateInf(TemplateInfVO templateInfVO);

	/**
	 * 템플릿 정보를 수정한다.
	 * 
	 * @param templateInfVO
	 */
	public void updateTemplateInf(TemplateInfVO templateInfVO);

	/**
	 * 템플릿 정보를 삭제한다.
	 * 
	 * @param templateInfVO
	 */
	public void deleteTemplateInf(TemplateInfVO templateInfVO);

	/**
	 * 템플릿에 대한 미리보기 정보를 조회한다.
	 * 
	 * @param templateInfVO
	 * @return
	 */
	public TemplateInfVO selectTemplatePreview(TemplateInfVO templateInfVO);

}
