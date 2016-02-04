package aramframework.com.sym.ccm.adc.service;

import java.util.List;

import aramframework.com.sym.ccm.adc.domain.AdministCodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 행정코드에 대한 데이터 접근 클래스를 정의한다
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
public interface AdministCodeManageMapper {

	/**
	 * 행정코드 목록을 조회한다.
	 * 
	 * @param administCodeVO
	 */
	public List<EgovMap> selectAdministCodeList(AdministCodeVO administCodeVO);

	/**
	 * 행정코드 총 갯수를 조회한다.
	 * 
	 * @param administCodeVO
	 */
	public int selectAdministCodeListCnt(AdministCodeVO administCodeVO);

	/**
	 * 행정코드 상세항목을 조회한다.
	 * 
	 * @param administCodeVO
	 */
	public AdministCodeVO selectAdministCodeDetail(AdministCodeVO administCodeVO);

	/**
	 * 행정코드를 등록한다.
	 * 
	 * @param administCodeVO
	 */
	public void insertAdministCode(AdministCodeVO administCodeVO);

	/**
	 * 행정코드를 수정한다.
	 * 
	 * @param administCodeVO
	 */
	public void updateAdministCode(AdministCodeVO administCodeVO);

	/**
	 * 행정코드를 삭제한다.
	 * 
	 * @param administCodeVO
	 */
	public void deleteAdministCode(AdministCodeVO administCodeVO);

}
