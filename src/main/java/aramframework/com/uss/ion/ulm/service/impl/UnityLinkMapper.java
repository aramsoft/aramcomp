package aramframework.com.uss.ion.ulm.service.impl;

import java.util.List;

import aramframework.com.uss.ion.ulm.domain.UnityLinkVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 통합링크관리를 처리하는 Dao Class 구현
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
public interface UnityLinkMapper {

	/**
	 * 통합링크관리 메인 셈플 목록을 조회한다.
	 * 
	 * @param unityLinkVO
	 */
	public List<EgovMap> selectUnityLinkSample(UnityLinkVO unityLinkVO) ;

	/**
	 * 통합링크관리를(을) 목록을 한다.
	 * 
	 * @param unityLinkVO
	 */
	public List<EgovMap> selectUnityLinkList(UnityLinkVO unityLinkVO) ;

	/**
	 * 통합링크관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param unityLinkVO
	 */
	public int selectUnityLinkListCnt(UnityLinkVO unityLinkVO) ;

	/**
	 * 통합링크관리를(을) 상세조회 한다.
	 * 
	 * @param unityLinkVO
	 */
	public UnityLinkVO selectUnityLinkDetail(UnityLinkVO unityLinkVO) ;

	/**
	 * 통합링크관리를(을) 등록한다.
	 * 
	 * @param unityLinkVO
	 */
	public void insertUnityLink(UnityLinkVO  unityLinkVO) ;

	/**
	 * 통합링크관리를(을) 수정한다.
	 * 
	 * @param unityLinkVO
	 */
	public void updateUnityLink(UnityLinkVO  unityLinkVO) ;

	/**
	 * 통합링크관리를(을) 삭제한다.
	 * 
	 * @param unityLinkVO
	 */
	public void deleteUnityLink(UnityLinkVO unityLinkVO) ;

}
