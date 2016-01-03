package aramframework.com.uss.ion.ism.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 약식결재관리에 대한 Service Interface를 정의한다.
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

public interface InfrmlSanctnService {

	/**
	 * 결재자 목록을 조회한다.
	 * 
	 * @param sanctnerVO
	 */
	public List<EgovMap> selectSanctnerList(SanctnerVO sanctnerVO);

	/**
	 * 결재자 총 갯수를 조회한다.
	 * 
	 * @param sanctnerVO
	 */
	public int selectSanctnerListCnt(SanctnerVO sanctnerVO);

	/**
	 * 약식결재 정보를 조회한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO selectInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 수정한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO updateInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 승인한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO updateInfrmlSanctnConfm(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 반려한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO updateInfrmlSanctnReturn(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 등록한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO insertInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 삭제한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public void deleteInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

}