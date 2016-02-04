package aramframework.com.cmm.service;

import java.util.List;
import java.util.Map;

import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.cmm.domain.SearchCodeVO;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기 위한 서비스 인터페이스
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

public interface CmmUseService {

	/**
	 * 공통코드를 ServletContext에 등록한다.
	 * 
	 * @param codeId
	 * @param codeName
	 */
	public void populateCmmCodeList(String codeId, String codeName);

	/**
	 * 공통코드를 조회한다.
	 * 
	 * @param searchCodeVO
	 */
	public List<ComCodeVO> selectCmmCodeList(SearchCodeVO searchCodeVO);

	/**
	 * 공통코드를 조회한다.
	 * 
	 * @param codeId
	 */
	public List<ComCodeVO> selectCmmCodeList(String codeId);

	/**
	 * 공통코드를 조회한다.
	 * 
	 * @param codeId
	 * @param code
	 */
	public ComCodeVO selectCmmCode(String codeId, String code);
	
	/**
	 * searchCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
	 * 
	 * @param voList
	 */
	public Map<String, List<ComCodeVO>> selectCmmCodeMap(List<SearchCodeVO> voList);

	/**
	 * 조직정보를 코드형태로 리턴한다.
	 * 
	 * @param searchCodeVO
	 */
	public List<ComCodeVO> selectOgrnztIdList(SearchCodeVO searchCodeVO);

}
