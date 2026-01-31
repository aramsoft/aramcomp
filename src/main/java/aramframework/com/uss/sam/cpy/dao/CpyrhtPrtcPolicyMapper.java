package aramframework.com.uss.sam.cpy.dao;

import java.util.List;

import aramframework.com.uss.sam.cpy.domain.CpyrhtPrtcPolicyVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 저작권보호정책내용을 처리하는 비즈니스 구현 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface CpyrhtPrtcPolicyMapper {

	/**
	 * 저작권보호정책 글 목록을 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public List<EgovMap> selectCpyrhtPrtcPolicyList(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책 글 총 갯수를 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public int selectCpyrhtPrtcPolicyListCnt(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public CpyrhtPrtcPolicyVO selectCpyrhtPrtcPolicyDetail(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책 글을 등록한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public void insertCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책 글을 수정한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public void updateCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책 글을 삭제한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public void deleteCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

}
