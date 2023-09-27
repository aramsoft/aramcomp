package aramframework.com.sym.ccm.zip.dao;

import java.util.List;

import aramframework.com.sym.ccm.zip.domain.ZipAramVO;
import aramframework.com.sym.ccm.zip.domain.ZipVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 우편번호에 대한 데이터 접근 클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
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
	 * 우편번호 상세항목을 조회한다.
	 * 
	 * @param zipAramVO
	 */
	public ZipAramVO selectZipDetailAram(ZipAramVO zipAramVO);

	/**
	 * 우편번호를 등록한다.
	 * 
	 * @param zipAramVO
	 */
	public void insertZipAram(ZipAramVO zipAramVO);

	/**
	 * 우편번호를 수정한다.
	 * 
	 * @param zipAramVO
	 */
	public void updateZipAram(ZipAramVO zipAramVO);

}
