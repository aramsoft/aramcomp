package aramframework.mbl.com.mlt.dao;

import java.util.List;

import aramframework.mbl.com.mlt.domain.MultimediaVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 멀티미디어에 대한 DB상의 접근, 변경을 처리한다.
 * 
 * 상세내용
 * - 멀티미디어에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 멀티미디어에 대한 조회기능은 목록, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface MultimediaMapper {

    /**
     * 멀티미디어 목록 DB 조회 메서드
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
     * 멀티미디어 정보 상세 DB 조회 메서드
     * 
     * @param multimediaVO
     */
    public MultimediaVO selectMultimedia(MultimediaVO multimediaVO); 
  
    /**
     * 멀티미디어 정보 DB 등록 메서드
     * 
     * @param multimediaVO
     */
    public void insertMultimedia(MultimediaVO multimediaVO);
 
    /**
     * 멀티미디어 정보 DB 수정 메서드
     * 
     * @param multimediaVO
     */
    public void updateMultimedia(MultimediaVO multimediaVO);

    /**
     * 멀티미디어 정보 DB 삭제 메서드
     * 
     * @param multimediaVO
     */
    public void deleteMultimedia(MultimediaVO multimediaVO);

}
