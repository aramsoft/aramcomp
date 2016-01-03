package aramframework.com.uss.ion.msi.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 메인화면이미지에 대한 Service Interface를 정의한다.
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

public interface MainImageService {

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public List<EgovMap> selectMainImageList(MainImageVO mainImageVO);

	/**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public int selectLoginScrinImageListCnt(MainImageVO mainImageVO);

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public MainImageVO selectMainImage(MainImageVO mainImageVO);

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * 
	 * @param mainImageVO
	 */
	public void insertMainImage(MainImageVO mainImageVO);

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * 
	 * @param mainImageVO
	 */
	public void updateMainImage(MainImageVO mainImageVO);

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * 
	 * @param mainImageVO
	 */
	public void deleteMainImage(MainImageVO mainImageVO);

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제한다.
	 * 
	 * @param mainImageVO
	 */
	public void deleteMainImageFile(MainImageVO mainImageVO);

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public List<EgovMap> selectMainImageResult(MainImageVO mainImageVO);

}