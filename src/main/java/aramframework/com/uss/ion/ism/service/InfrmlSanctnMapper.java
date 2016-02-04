package aramframework.com.uss.ion.ism.service;

import java.util.List;

import aramframework.com.uss.ion.ism.domain.InfrmlSanctnVO;
import aramframework.com.uss.ion.ism.domain.SanctnerVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 약식결재관리에 대한 DAO 클래스를 정의한다.
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

@Mapper
public interface InfrmlSanctnMapper {

	/**
	 * 주어진 조건에 맞는 결재자를 불러온다.
	 * 
	 * @param sanctnerVO
	 */
	public List<EgovMap> selectSanctnerList(SanctnerVO sanctnerVO);

	/**
	 * 결재자 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param sanctnerVO
	 */
	public int selectSanctnerListCnt(SanctnerVO sanctnerVO);

	/**
	 * 주어진 조건에 맞는 약식결재정보를 불러온다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctnVO selectInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 수정한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public void updateInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 승인 또는 반려한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public void updateInfrmlSanctnConfm(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 등록한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public void insertInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

	/**
	 * 약식결재관리 정보를 삭제한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	public void deleteInfrmlSanctn(InfrmlSanctnVO infrmlSanctnVO);

}