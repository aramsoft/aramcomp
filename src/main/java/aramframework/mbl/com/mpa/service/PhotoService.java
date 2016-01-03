package aramframework.mbl.com.mpa.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 사진 앨범에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 사진에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 사진에 대한 조회기능은 목록, 상세조회로 구분된다.
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

public interface PhotoService {

    /**
     * 사진 목록을 조회하는 Service interface 메서드
     * 
     * @param photoVO
     */
    public List<EgovMap> selectPhotoList(PhotoVO photoVO);

    /**
     * 사진 정보의 총 갯수를 조회한다.
     * 
     * @param photoVO
     */
    public int selectPhotoListCnt(PhotoVO photoVO);

    /**
     * 사진 상세정보를 조회하는 Service interface 메서드
     * 
     * @param photoVO
     */
    public PhotoVO selectPhoto(PhotoVO photoVO);

    /**
     * 사진 정보를 등록하는 Service interface 메서드
     * 
     * @param photoVO
     */
    public void insertPhoto(PhotoVO photoVO);

    /**
     * 사진 정보를 수정하는 Service interface 메서드
     * 
     * @param photoVO
     */
    public void updatePhoto(PhotoVO photoVO);

    /**
     * 사진 정보를 삭제하는 Service interface 메서드 *
     * 
     * @param photoVO
     */
    public void deletePhoto(PhotoVO photoVO);

}
