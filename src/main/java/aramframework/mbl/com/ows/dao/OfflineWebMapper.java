package aramframework.mbl.com.ows.dao;

import java.util.List;

import aramframework.mbl.com.ows.domain.OfflineWebVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 오프라인웹 서비스에 대한 DB상의 접근, 변경을 처리한다.
 * 
 * 상세내용
 * - 오프라인웹 서비스에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 오프라인웹 서비스에 대한 조회기능은 목록, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface OfflineWebMapper {
	
	/**
	 * 오프라인웹 서비스 신규 일련번호 조회
	 * 
	 */
	public int selectOfflineWebNewSn();
	
	/**
	 * 오프라인웹 서비스 목록 조회
	 * 
	 * @param offlineWebVO
	 */
	public List<EgovMap> selectOfflineWebList(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스 글 총 갯수 조회
	 * 
	 * @param offlineWebVO
	 */
	public int selectOfflineWebListCnt(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스 상세 조회
	 * 
	 * @param offlineWebVO
	 */
	public OfflineWebVO selectOfflineWeb(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스 등록
	 * 
	 * @param offlineWebVO
	 */
	public int insertOfflineWeb(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스 수정
	 * 
	 * @param offlineWebVO
	 */
	public int updateOfflineWeb(OfflineWebVO offlineWebVO);
	
	/**
	 * 오프라인웹 서비스 수정
	 * 
	 * @param offlineWebVO
	 */
	public int deleteOfflineWeb(OfflineWebVO offlineWebVO);
	
}
