package aramframework.com.utl.sys.fsm.dao;

import java.util.List;

import aramframework.com.utl.sys.fsm.domain.FileSysMntrngLogVO;
import aramframework.com.utl.sys.fsm.domain.FileSysMntrngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 개요 - 파일시스템 모니터링대상에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용 - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
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
public interface FileSysMntrngMapper {

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 대상 목록을 불러온다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public List<FileSysMntrngVO> selectFileSysMntrngList(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링대상 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public int selectFileSysMntrngListCnt(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 대상을 불러온다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public FileSysMntrngVO selectFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링 대상 정보를 등록한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void insertFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링 대상 정보를 수정한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void updateFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링 대상 정보를 삭제한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void deleteFileSysMntrng(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 파일시스템 모니터링 결과 정보를 수정한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void updateFileSysMntrngSttus(FileSysMntrngVO fileSysMntrngVO);

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 로그 목록을 불러온다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public List<FileSysMntrngLogVO> selectFileSysMntrngLogList(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 파일시스템 모니터링로그 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public int selectFileSysMntrngLogListCnt(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 로그를 불러온다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public FileSysMntrngLogVO selectFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO);

	/**
	 * 파일시스템 모니터링 대상 정보를 등록한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public void insertFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO);

}