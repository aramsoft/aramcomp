package aramframework.com.uss.ion.vct.service.impl;

import java.util.List;

import aramframework.com.uss.ion.vct.service.IndvdlYrycManageVO;
import aramframework.com.uss.ion.vct.service.VcatnManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 휴가관리에 대한 DAO 클래스를 정의한다.
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
public interface VcatnManageMapper {

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public List<EgovMap> selectVcatnManageList(VcatnManageVO vcatnManageVO);

	/**
	 * 휴가관리목록 총 갯수를 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public int selectVcatnManageListCnt(VcatnManageVO vcatnManageVO);

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO);

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 * 
	 * @param vcatnManageVO
	 */
	public void insertVcatnManage(VcatnManageVO vcatnManageVO);

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 * 
	 * @param vcatnManageVO
	 */
	public void updateVcatnManage(VcatnManageVO vcatnManageVO);

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 * 
	 * @param vcatnManageVO
	 */
	public void deleteVcatnManage(VcatnManageVO vcatnManageVO);

	/**
	 * 휴가일자 중복여부 체크
	 * 
	 * @param vcatnManageVO
	 */
	public int selectVcatnManageDplctAt(VcatnManageVO vcatnManageVO);

	/*** 승인관련 ***/
	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public List<EgovMap> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO);

	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록 총 갯수를 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public int selectVcatnManageConfmListCnt(VcatnManageVO vcatnManageVO);

	/**
	 * 신청된 휴가를 승인처리한다.
	 * 
	 * @param vcatnManageVO
	 */
	public void updateVcatnManageConfm(VcatnManageVO vcatnManageVO);

	/*** 연차관련 ***/
	/**
	 * 개인별 연차관리의 상세정보를 조회한다.
	 * 
	 * @param indvdlYrycManageVO
	 */
	public IndvdlYrycManageVO selectIndvdlYrycManage(IndvdlYrycManageVO indvdlYrycManageVO);

	/**
	 * 연차정보를 수정처리한다.
	 * 
	 * @param indvdlYrycManageVO
	 */
	public void updateIndvdlYrycManage(IndvdlYrycManageVO indvdlYrycManageVO);

}
