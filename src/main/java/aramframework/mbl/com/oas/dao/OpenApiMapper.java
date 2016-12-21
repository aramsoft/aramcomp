package aramframework.mbl.com.oas.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.mbl.com.oas.domain.OpenApiVO;

/**
 * 개요
 * - OpenAPI연계에 대한 DB상의 접근, 변경을 처리한다.
 * 
 * 상세내용
 * - OpenAPI연계 정보에 대한 등록, 조회, OpenAPI 서비스 내용 조회 기능을 제공한다.
 * - OpenAPI연계 정보의 조회기능은 목록, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface OpenApiMapper {

    /**
     * OpenAPI 연계정보 목록을 조회한다.
     * @param openApiVO
     * @return List<OpenApiVO> OpenAPI 연계정보 리스트
     * @throws Exception
    */
    public List<OpenApiVO> selectOpenApiInquiryHistoryList(OpenApiVO openApiVO);

    /**
     * OpenAPI 연계정보에 대한 목록 건수를 조회 한다.
     *
     * @param openApiVO
     * @return int OpenAPI 연계정보 목록 건수
     * @throws Exception
    */
    public int selectOpenApiInquiryHistoryListCnt(OpenApiVO openApiVO);
 
    /**
     * OpenAPI 연계정보를 조회한다.
     * @param openApiVO
     * @return OpenApi OpenAPI 연계정보
     * @throws Exception
    */
    public OpenApiVO selectOpenApiInquiryHistory(OpenApiVO openApiVO);
    
    /**
     * OpenAPI 연계정보를 DB에 등록한다.
     *
     * @param openApi
     * @throws Exception
    */
    public void insertOpenApiInquiryHistory(OpenApiVO openApiVO);
    
}