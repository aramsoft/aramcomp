package aramframework.mbl.com.geo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.mbl.com.geo.dao.GeoLocationMapper;
import aramframework.mbl.com.geo.domain.GeoLocationVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 위치정보연계에 대한 Service Interface를 구현한다.
 * 
 * 상세내용
 * - 건물의 위치정보에 대한 등록, 수정, 삭제, 상세조회 기능을 제공한다.
 * - 건물의 위치정보의 조회기능은 목록, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class GeoLocationService extends EgovAbstractServiceImpl {

	@Autowired
    private GeoLocationMapper geoLocationMapper;    
    
    /** ID Generation */    
	@Autowired
    private EgovIdGnrService geoLocationIdGnrService; 

    /**
     * 건물의 위치정보 목록을 조회한다.
     * 
     * @param geoLocationVO
     */
    public List<EgovMap> selectBuildingLocationInfoList(GeoLocationVO geoLocationVO) {
    	return geoLocationMapper.selectBuildingLocationInfoList(geoLocationVO);
    }
 
    /**
     * 건물의 위치정보 총 갯수를 조회한다.
     * 
     * @param geoLocationVO
     */
    public int selectBuildingLocationInfoListCnt(GeoLocationVO geoLocationVO) {
    	return geoLocationMapper.selectBuildingLocationInfoListCnt(geoLocationVO);
    }
    
    /**
     * 건물의 위치정보를 조회한다.
     * 
     * @param geoLocationVO
     */
    public GeoLocationVO selectBuildingLocationInfo(GeoLocationVO geoLocationVO) {
    	GeoLocationVO resultVo = geoLocationMapper.selectBuildingLocationInfo(geoLocationVO);
		// searchVO 이전 
		resultVo.setSearchVO(geoLocationVO.getSearchVO()); 
		return resultVo;
   }
 
    /**
     * 건물의 위치정보를 DB에 등록한다.
     * 
     * @param geoLocationVO
     */
    public void insertBuildingLocationInfo(GeoLocationVO geoLocationVO) {
        try {
			geoLocationVO.setSn(geoLocationIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
        geoLocationMapper.insertBuildingLocationInfo(geoLocationVO);
    }

    /**
     * 건물의 위치정보를 수정한다.
     * 
     * @param geoLocationVO
     */
    public void updateBuildingLocationInfo(GeoLocationVO geoLocationVO) {
        geoLocationMapper.updateBuildingLocationInfo(geoLocationVO);
    }
    
    /**
     * 건물의 위치정보를 삭제한다.
     * 
     * @param geoLocationVO
     */
    public void deleteBuildingLocationInfo(GeoLocationVO geoLocationVO) {
        geoLocationMapper.deleteBuildingLocationInfo(geoLocationVO);
    }
    
}
