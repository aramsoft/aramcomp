package aramframework.mbl.com.geo.service;

import java.util.List;

import aramframework.mbl.com.geo.domain.GeoLocationVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 위치정보연계에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 건물의 위치정보에 대한 등록, 수정, 삭제, 상세조회 기능을 제공한다.
 * - 건물의 위치정보의 조회기능은 목록, 상세조회로 구분된다.
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

public interface GeoLocationService {

    /**
     * 건물의 위치정보 목록을 조회하는 Service interface 메서드
     * 
     * @param geoLocationVO
     */
    public List<EgovMap> selectBuildingLocationInfoList(GeoLocationVO geoLocationVO);

    /**
     * 건물의 위치정보 총 갯수를 조회한다.
     * 
     * @param geoLocationVO
     */
    public int selectBuildingLocationInfoListCnt(GeoLocationVO geoLocationVO);

    /**
     * 건물의 위치정보를 상세조회하는 Service interface 메서드
     * 
     * @param geoLocationVO
     */
    public GeoLocationVO selectBuildingLocationInfo(GeoLocationVO geoLocationVO);

    /**
     * 건물의 위치정보를 등록하는 Service interface 메서드
     * 
     * @param geoLocationVO
     */
    public void insertBuildingLocationInfo(GeoLocationVO geoLocationVO);

    /**
     * 건물의 위치정보를 수정하는 Service interface 메서드
     * 
     * @param geoLocationVO
     */
    public void updateBuildingLocationInfo(GeoLocationVO geoLocationVO);

    /**
     * 건물의 위치정보를 삭제하는 Service interface 메서드
     * 
     * @param geoLocationVO
     */
    public void deleteBuildingLocationInfo(GeoLocationVO geoLocationVO) ;

}