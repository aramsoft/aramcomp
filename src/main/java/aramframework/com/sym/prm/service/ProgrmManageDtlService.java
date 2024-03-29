package aramframework.com.sym.prm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.prm.dao.ProgrmManageDtlMapper;
import aramframework.com.sym.prm.domain.ProgrmManageDtlVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 프로그램목록관리 및 프로그램변경관리에 관한 비즈니스 구현 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class ProgrmManageDtlService extends EgovAbstractServiceImpl {

	@Autowired
	private ProgrmManageDtlMapper progrmManageDtlMapper;
	
	/**
	 * 프로그램변경요청 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public List<EgovMap> selectProgrmChangeRequstList(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectProgrmChangeRequstList(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청목록 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	public int selectProgrmChangeRequstListCnt(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectProgrmChangeRequstListCnt(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청 정보를 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public ProgrmManageDtlVO selectProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectProgrmChangeRequst(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청을 등록
	 * 
	 * @param progrmManageDtlVO
	 */
	public void insertProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO) {
		progrmManageDtlVO.setRqestNo(progrmManageDtlMapper.selectProgrmChangeRequstNo(progrmManageDtlVO)); 
		progrmManageDtlMapper.insertProgrmChangeRequst(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청을 수정
	 * 
	 * @param progrmManageDtlVO
	 */
	public void updateProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO) {
		progrmManageDtlMapper.updateProgrmChangeRequst(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청을 삭제
	 * 
	 * @param progrmManageDtlVO
	 */
	public void deleteProgrmChangeRequst(ProgrmManageDtlVO progrmManageDtlVO) {
		progrmManageDtlMapper.deleteProgrmChangeRequst(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청처리 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public List<EgovMap> selectChangeRequstProcessList(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectChangeRequstProcessList(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청처리목록 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	public int selectChangeRequstProcessListCnt(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectChangeRequstProcessListCnt(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청처리를 수정
	 * 
	 * @param progrmManageDtlVO
	 */
	public void updateProgrmChangeRequstProcess(ProgrmManageDtlVO progrmManageDtlVO) {
		progrmManageDtlMapper.updateProgrmChangeRequstProcess(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청자 Email 정보를 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public String selectRqesterEmail(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectRqesterEmail(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경이력 목록을 조회
	 * 
	 * @param progrmManageDtlVO
	 */
	public List<EgovMap> selectProgrmChangeHistoryList(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectProgrmChangeHistoryList(progrmManageDtlVO);
	}

	/**
	 * 프로그램변경요청목록 총건수를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	public int selectProgrmChangeHistoryListCnt(ProgrmManageDtlVO progrmManageDtlVO) {
		return progrmManageDtlMapper.selectProgrmChangeHistoryListCnt(progrmManageDtlVO);
	}

}