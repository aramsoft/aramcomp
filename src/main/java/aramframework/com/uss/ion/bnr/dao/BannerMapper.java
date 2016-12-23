package aramframework.com.uss.ion.bnr.dao;

import java.util.List;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.uss.ion.bnr.domain.BannerVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 배너에 대한 DAO 클래스를 정의한다.
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface BannerMapper {

	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 * 
	 * @param bannerVO
	 */
	public List<EgovMap> selectBannerList(BannerVO bannerVO);

	/**
	 * 배너목록 총 갯수를 조회한다.
	 * 
	 * @param bannerVO
	 */
	public int selectBannerListCnt(BannerVO bannerVO);

	/**
	 * 등록된 배너의 상세정보를 조회한다.
	 * 
	 * @param bannerVO
	 */
	public BannerVO selectBanner(BannerVO bannerVO);

	/**
	 * 배너정보를 신규로 등록한다.
	 * 
	 * @param bannerVO
	 */
	public void insertBanner(BannerVO bannerVO);

	/**
	 * 기 등록된 배너정보를 수정한다.
	 * 
	 * @param bannerVO
	 */
	public void updateBanner(BannerVO bannerVO);

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 * 
	 * @param bannerVO
	 */
	public void deleteBanner(BannerVO bannerVO);

	/**
	 * 기 등록된 배너정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 * 
	 * @param bannerVO
	 */
	public FileVO selectBannerFile(BannerVO bannerVO);

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param bannerVO
	 */
	public List<EgovMap> selectBannerResult(BannerVO bannerVO);

}
