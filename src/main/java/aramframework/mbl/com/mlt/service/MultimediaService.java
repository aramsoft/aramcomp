package aramframework.mbl.com.mlt.service;

import java.util.List;

import aramframework.com.cmm.service.FileVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 멀티미디어 제어에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 멀티미디어에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 멀티미디어에 대한 조회기능은 목록, 상세조회로 구분된다.
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

public interface MultimediaService {

    /**
     * 멀티미디어 목록을 조회하는 Service interface 메서드
     * 
     * @param multimediaVO
     */
    public List<EgovMap> selectMultimediaList(MultimediaVO multimediaVO);

    /**
     * 멀티미디어 정보의 총 갯수를 조회한다.
     * 
     * @param multimediaVO
     */
    public int selectMultimediaListCnt(MultimediaVO multimediaVO);

    /**
     * 멀티미디어 상세정보를 조회하는 Service interface 메서드
     * @param multimedia
     * @return multimedia 멀티미디어 정보
     */
    public MultimediaVO selectMultimedia(MultimediaVO multimediaVO);

    /**
     * 멀티미디어 정보를 등록하는 Service interface 메서드
     * 
     * @param multimediaVO
     */
    public void insertMultimedia(MultimediaVO multimediaVO);

   /**
     * 멀티미디어 정보를 수정하는 Service interface 메서드
     * 
     * @param multimediaVO
     */
    public void updateMultimedia(MultimediaVO multimediaVO);

    /**
     * 멀티미디어 정보를 삭제하는 Service interface 메서드 *
     * 
     * @param multimediaVO
     */
    public void deleteMultimedia(MultimediaVO multimediaVO);

    /**
     * 멀티미디어 파일을 상대 경로에 저장한다.
     * 
     * @param fileList
     */
    public void copyFileToRelativePath(List<FileVO> fileList);

    /**
     * 파일별 지원 브라우저 정보를 조회한다.
     * @return List<MultimediaFileInfo>
     */
    public List<MultimediaFileInfoVO> getMultimediaFileInfoFromXML();

    /**
     * 지원 브라우저 정보를 조회한다.
     * 
     * @param mltmdNm
     * @param extList
     */
    public String getBrowserInfoFromXML(String mltmdNm, List<String> extList);
}
