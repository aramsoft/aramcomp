package aramframework.com.uss.sam.cpy.service;

import java.util.List;

import aramframework.com.uss.sam.cpy.domain.CpyrhtPrtcPolicyVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 저작권보호정책내용을 처리하는 서비스 클래스
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

public interface CpyrhtPrtcPolicyService {

	/**
	 * 저작권보호정책내용 글 목록을 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	List<EgovMap> selectCpyrhtPrtcPolicyList(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책내용 글 총 갯수를 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	int selectCpyrhtPrtcPolicyListCnt(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책내용 글을 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	CpyrhtPrtcPolicyVO selectCpyrhtPrtcPolicyDetail(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책내용 글을 등록한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	void insertCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책내용 글을 수정한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	void updateCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

	/**
	 * 저작권보호정책내용 글을 삭제한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	void deleteCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO);

}
