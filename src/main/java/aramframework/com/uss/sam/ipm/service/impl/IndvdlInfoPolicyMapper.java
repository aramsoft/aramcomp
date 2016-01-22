package aramframework.com.uss.sam.ipm.service.impl;

import java.util.List;

import aramframework.com.uss.sam.ipm.service.IndvdlInfoPolicyVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개인정보보호정책를 처리하는 Dao Class 구현
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
public interface IndvdlInfoPolicyMapper {

	/**
	 * 개인정보보호정책를(을) 목록을 한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public List<EgovMap> selectIndvdlInfoPolicyList(IndvdlInfoPolicyVO indvdlInfoPolicyVO);

	/**
	 * 개인정보보호정책를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public int selectIndvdlInfoPolicyListCnt(IndvdlInfoPolicyVO indvdlInfoPolicyVO);

	/**
	 * 개인정보보호정책를(을) 상세조회 한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public IndvdlInfoPolicyVO selectIndvdlInfoPolicyDetail(IndvdlInfoPolicyVO indvdlInfoPolicyVO);

	/**
	 * 개인정보보호정책를(을) 등록한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public void insertIndvdlInfoPolicy(IndvdlInfoPolicyVO indvdlInfoPolicyVO);

	/**
	 * 개인정보보호정책를(을) 수정한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public void updateIndvdlInfoPolicy(IndvdlInfoPolicyVO indvdlInfoPolicyVO);

	/**
	 * 개인정보보호정책를(을) 삭제한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public void deleteIndvdlInfoPolicy(IndvdlInfoPolicyVO indvdlInfoPolicyVO);

}
