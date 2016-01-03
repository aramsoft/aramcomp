package aramframework.mbl.com.ows.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 오프라인웹 서비스에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 오프라인웹 서비스에 대한 등록, 삭제, 조회 기능을 제공한다.
 * - 오프라인웹 서비스 조회기능은 목록, 상세조회로 구분된다.
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

public interface OfflineWebService {
	
	/**
	 * 오프라인웹 서비스 글 목록 조회
	 * 
	 * @param offlineWebVO
	 */
	public List<EgovMap> selectOfflineWebList(OfflineWebVO offlineWebVO);

	/**
	 * 오프라인웹 서비스 글 목록 총갯수
	 * 
	 * @param offlineWebVO
	 */
	public int selectOfflineWebListCnt(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스글 상세 조회 
	 * 
	 * @param offlineWebVO
	 */
	public OfflineWebVO selectOfflineWeb(OfflineWebVO offlineWebVO);
	
	/**
	 * 동기호 서비스 글 등록 
	 * 
	 * @param offlineWebVO
	 */
	public int insertOfflineWeb(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스 글 수정
	 * 
	 * @param offlineWebVO
	 */
	public int updateOfflineWeb(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스 글 삭제
	 * 
	 * @param offlineWebVO
	 */
	public int deleteOfflineWeb(OfflineWebVO offlineWebVO);

}
