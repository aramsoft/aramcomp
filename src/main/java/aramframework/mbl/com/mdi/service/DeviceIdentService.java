package aramframework.mbl.com.mdi.service;

import java.util.List;

import aramframework.mbl.com.mdi.domain.DeviceIdentVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 모바일기기식별에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 모바일기기 식별정보에 대한 등록, 수정, 삭제, 조회 기능과 User-Agent값 조회, 모바일기기 정보 추출 기능을 제공한다.
 * - 모바일기기 식별정보에 대한 조회기능은 목록, 상세조회로 구분된다.
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

public interface DeviceIdentService {

    /**
     * 모바일 기기 정보 목록을 조회하는 Service interface 메서드
     * 
     * @param deviceIdentVO
     */
    public List<EgovMap> selectDeviceIdentList(DeviceIdentVO deviceIdentVO);

    /**
     * 모바일 기기 정보의 총 갯수를 조회한다.
     * 
     * @param deviceIdentVO
     */
    public int selectDeviceIdentListCnt(DeviceIdentVO deviceIdentVO);

    /**
     * 모바일 기기 상세정보를 조회하는 Service interface 메서드
     * 
     * @param deviceIdentVO
     */
    public DeviceIdentVO selectDeviceIdent(DeviceIdentVO deviceIdentVO);

    /**
     * 모바일 기기 정보를 등록하는 Service interface 메서드
     * 
     * @param deviceIdentVO
     */
    public void insertDeviceIdent(DeviceIdentVO deviceIdentVO);

    /**
     * 모바일 기기 정보를 수정하는 Service interface 메서드
     * 
     * @param deviceIdentVO
     */
    public void updateDeviceIdent(DeviceIdentVO deviceIdentVO);

    /**
     * 모바일 기기 정보를 삭제하는 Service interface 메서드
     * 
     * @param deviceIdentVO
     */
    public void deleteDeviceIdent(DeviceIdentVO deviceIdentVO);

    /**
     * 모바일 기기 정보를 XML 파일로 생성한다.
     */
    public void createDeviceIndentListToXML();

    /**
     * User-Agent 값에서 모바일기기 정보를 추출하는 Service interface
     * 메서드
     * @param userAgent
     */
    public DeviceIdentVO getDeviceIdentFromXML(String userAgent);
    
}
