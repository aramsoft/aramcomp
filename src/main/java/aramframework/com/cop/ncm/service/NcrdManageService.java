package aramframework.com.cop.ncm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 명함정보를 관리하기 위한 서비스 인터페이스 클래스
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

public interface NcrdManageService {

	/**
	 * 내가 소유한 명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public List<EgovMap> selectNcrdListMine(NameCardVO nameCardVO);

	/**
	 * 내가 소유한 명함 정보에 대한 목록의 총갯수를 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public int selectNcrdListMineCnt(NameCardVO nameCardVO);

	/**
	 * 명함 정보에 대한 상세정보를 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public NameCardVO selectNcrdItem(NameCardVO nameCardVO);

	/**
	 * 명함 정보 및 명함사용자 정보를 등록한다.
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
	 * 공개 명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param nameCardVO
	 */
	public List<EgovMap> selectNcrdListPublic(NameCardVO nameCardVO);

	/**
	 * 공개 명함 정보에 대한 목록의 총갯수를 조회한다.
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
