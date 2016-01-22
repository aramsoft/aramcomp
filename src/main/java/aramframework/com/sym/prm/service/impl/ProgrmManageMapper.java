package aramframework.com.sym.prm.service.impl;

import java.util.List;

import aramframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 프로그램 목록관리및 프로그램변경관리에 대한 DAO 클래스를 정의한다.
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
public interface ProgrmManageMapper {

	/**
	 * 프로그램 목록을 조회
	 * 
	 * @param progrmManageVO
	 */
	public List<EgovMap> selectProgrmList(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램목록 조회 총건수를 조회한다.
	 * 
	 * @param progrmManageVO
	 */
	public int selectProgrmListCnt(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램목록 전체총수를 조회한다.
	 * 
	 */
	public int selectProgrmListCntAll();

	/**
	 * 프로그램 파일 존재여부를 조회
	 * 
	 * @param progrmFileNm
	 */
	public int selectProgrmNMTotCnt(String progrmFileNm);

	/**
	 * 프로그램 기본정보를 조회
	 * 
	 * @param progrmManageVO
	 */
	public ProgrmManageVO selectProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램 기본정보 및 URL을 등록
	 * 
	 * @param progrmManageVO
	 */
	public void insertProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램 기본정보 및 URL을 수정
	 * 
	 * @param progrmManageVO
	 */
	public void updateProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램 기본정보 및 URL을 삭제
	 * 
	 * @param progrmManageVO
	 */
	public void deleteProgrm(ProgrmManageVO progrmManageVO);

	/**
	 * 프로그램목록 전체삭제 초기화
	 * 
	 */
	public void deleteAllProgrm();

}