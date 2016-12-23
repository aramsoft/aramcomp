package aramframework.com.cop.ncm.dao;

import java.util.List;

import aramframework.com.cop.ncm.domain.NameCardUseVO;
import aramframework.com.cop.ncm.domain.NameCardVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 명함정보를 관리하기 위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface NcrdManageMapper {

	/**
	 * 내가 소유한 명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public List<EgovMap> selectNcrdListMine(NameCardVO nameCardVO);

	/**
	 * 내가 소유한 명함 정보에 대한 목록 전체 건수를 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public int selectNcrdListMineCnt(NameCardVO nameCardVO);

	/**
	 * 명함 정보에 대한 상세정보를 조회한다.
	 * 
	 * @param ncrdId
	 */
	public NameCardVO selectNcrdItem(String ncrdId);

	/**
	 * 명함 정보에 대한 상세정보를 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public NameCardVO selectNcrdItem(NameCardVO nameCardVO);

	/**
	 * 명함 정보를 등록한다.
	 * 
	 * @param nameCardVO
	 */
	public void insertNcrdItem(NameCardVO nameCardVO);

	/**
	 * 명함 정보를 수정한다.
	 * 
	 * @param nameCardVO
	 */
	public void updateNcrdItem(NameCardVO nameCardVO);

	/**
	 * 명함 정보를 삭제한다.
	 * 
	 * @param nameCardVO
	 */
	public void deleteNcrdItem(NameCardVO nameCardVO);

	/**
	 * 공개명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public List<EgovMap> selectNcrdListPublic(NameCardVO nameCardVO);

	/**
	 * 공개명함 정보에 대한  목록 전체 건수를 조회한다..
	 * 
	 * @param nameCardVO
	 */
	public int selectNcrdListPublicCnt(NameCardVO nameCardVO);

	/**
	 * 명함사용자 정보를 등록한다.
	 * 
	 * @param nameCardUseVO
	 */
	public void insertNcrdUseInf(NameCardUseVO nameCardUseVO);

	/**
	 * 명함사용자 정보를 수정한다.
	 * 
	 * @param nameCardUseVO
	 */
	public void updateNcrdUseInf(NameCardUseVO nameCardUseVO);

	/**
	 * 명함사용자 정보를 삭제한다.
	 * 
	 * @param nameCardUseVO
	 */
	public void deleteNcrdUseInf(NameCardUseVO nameCardUseVO);

}
