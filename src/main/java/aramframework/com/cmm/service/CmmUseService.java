package aramframework.com.cmm.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.dao.CmmUseMapper;
import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.cmm.domain.SearchCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class CmmUseService extends EgovAbstractServiceImpl {

	@Autowired 
	ServletContext servletContext;

	@Autowired 
	private CmmUseMapper cmmUseMapper;	

	/**
	 * 공통코드를 ServletContext에 등록한다.
	 * 
	 * @param codeId
	 * @param codeName
	 */
	public void populateCmmCodeList(String codeId, String codeName) {
		@SuppressWarnings("unchecked")
		List<ComCodeVO> saveCode = (List<ComCodeVO>)servletContext.getAttribute(codeName);
		if( saveCode == null ) {
			SearchCodeVO vo = new SearchCodeVO();
			vo.setCodeId(codeId);
			servletContext.setAttribute(codeName, cmmUseMapper.selectCmmCodeList(vo));
		}
	}

	/**
	 * 공통코드를 조회한다.
	 * 
	 * @param searchCodeVO
	 */
	public List<ComCodeVO> selectCmmCodeList(SearchCodeVO searchCodeVO) {
		return cmmUseMapper.selectCmmCodeList(searchCodeVO);
	}

	/**
	 * 공통코드를 조회한다.
	 * 
	 * @param codeId
	 */
	public List<ComCodeVO> selectCmmCodeList(String codeId) {
		SearchCodeVO vo = new SearchCodeVO();
		vo.setCodeId(codeId);
		return cmmUseMapper.selectCmmCodeList(vo);
	}

	/**
	 * 공통코드를 조회한다.
	 * 
	 * @param codeId
	 * @param codeDetailId
	 */
	public ComCodeVO selectCmmCode(String codeId, String codeDetailId) {
		SearchCodeVO vo = new SearchCodeVO();
		vo.setCodeId(codeId);
		vo.setCode(codeDetailId);
		return cmmUseMapper.selectCmmCode(vo);
	}
	
	/**
	 * searchCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
	 * 
	 * @param voList
	 */
	public Map<String, List<ComCodeVO>> selectCmmCodeMap(List<SearchCodeVO> voList) {
		Map<String, List<ComCodeVO>> map = new HashMap<String, List<ComCodeVO>>();

		Iterator<SearchCodeVO> iter = voList.iterator();
		SearchCodeVO vo;
		while (iter.hasNext()) {
			vo = iter.next();
			map.put(vo.getCodeId(), cmmUseMapper.selectCmmCodeList(vo));
		}

		return map;
	}

	/**
	 * 조직정보를 코드형태로 리턴한다.
	 * 
	 * @param searchCodeVO
	 */
	public List<ComCodeVO> selectOgrnztIdList(SearchCodeVO searchCodeVO) {
		return cmmUseMapper.selectOgrnztIdList(searchCodeVO);
	}

}
