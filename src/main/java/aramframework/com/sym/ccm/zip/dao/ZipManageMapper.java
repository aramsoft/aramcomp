package aramframework.com.sym.ccm.zip.dao;

import java.util.List;

import aramframework.com.sym.ccm.zip.domain.ZipVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 우편번호에 대한 데이터 접근 클래스를 정의한다
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
public interface ZipManageMapper {

	/**
	 * 우편번호 목록을 조회한다.
	 * 
	 * @param zipVO
	 */
	public List<EgovMap> selectZipList(ZipVO zipVO);

	/**
	 * 우편번호 총 갯수를 조회한다.
	 * 
	 * @param zipVO
	 */
	public int selectZipListCnt(ZipVO zipVO);

	/**
	 * 우편번호 상세항목을 조회한다.
	 * 
	 * @param zipVO
	 */
	public ZipVO selectZipDetail(ZipVO zipVO);

	/**
	 * 우편번호를 등록한다.
	 * 
	 * @param zipVO
	 */
	public void insertZip(ZipVO zipVO);

	/**
	 * 우편번호를 등록한다.
	 * 
	 * @param zipVO
	 */
	public void insertExcelZip(ZipVO zipVO);

	/**
	 * 우편번호를 등록한다.
	 * 
	 * @param zipVO
	 */
	public void insertExcelZipAram(ZipVO zipVO);

	/**
	 * 우편번호를 수정한다.
	 * 
	 * @param zipVO
	 */
	public void updateZip(ZipVO zipVO);

	/**
	 * 우편번호를 삭제한다.
	 * 
	 * @param zipVO
	 */
	public void deleteZip(ZipVO zipVO);

	/**
	 * 우편번호 전체를 삭제한다.
	 * 
	 */
	public void deleteAllZip();

	/**
	 * 우편번호 전체를 삭제한다.(아람)
	 * 
	 */
	public void deleteAllZipAram();

}
