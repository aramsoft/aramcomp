package aramframework.com.sym.prm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.prm.dao.ProgrmManageMapper;
import aramframework.com.sym.prm.domain.ProgrmManageVO;
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
public class ProgrmManageService extends EgovAbstractServiceImpl {

	@Autowired
	private ProgrmManageMapper progrmManageMapper;

	/**
	 * 프로그램 목록을 조회
	 * 
	 * @param progrmManageVO
	 */
	public List<EgovMap> selectProgrmList(ProgrmManageVO progrmManageVO) {
		return progrmManageMapper.selectProgrmList(progrmManageVO);
	}

	/**
	 * 프로그램목록 총건수를 조회한다.
	 * 
	 * @param progrmManageVO
	 */
	public int selectProgrmListCnt(ProgrmManageVO progrmManageVO) {
		return progrmManageMapper.selectProgrmListCnt(progrmManageVO);
	}

	/**
	 * 프로그램 파일 존재여부를 조회
	 * 
	 * @param progrmFileNm
	 */
	public int selectProgrmNMTotCnt(String progrmFileNm) {
		return progrmManageMapper.selectProgrmNMTotCnt(progrmFileNm);
	}

	/**
	 * 프로그램 상세정보를 조회
	 * 
	 * @param progrmManageVO
	 */
	public ProgrmManageVO selectProgrm(ProgrmManageVO progrmManageVO) {
		return progrmManageMapper.selectProgrm(progrmManageVO);
	}

	/**
	 * 프로그램 정보를 등록
	 * 
	 * @param progrmManageVO
	 */
	public void insertProgrm(ProgrmManageVO progrmManageVO) {
		progrmManageMapper.insertProgrm(progrmManageVO);
	}

	/**
	 * 프로그램 정보를 수정
	 * 
	 * @param progrmManageVO
	 */
	public void updateProgrm(ProgrmManageVO progrmManageVO) {
		progrmManageMapper.updateProgrm(progrmManageVO);
	}

	/**
	 * 프로그램 정보를 삭제
	 * 
	 * @param progrmManageVO
	 */
	public void deleteProgrm(ProgrmManageVO progrmManageVO) {
		progrmManageMapper.deleteProgrm(progrmManageVO);
	}

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedProgrmFileNmForDel
	 */
	public void deleteProgrmManageList(String[] progrmFileNms) {
		ProgrmManageVO vo = new ProgrmManageVO();
		for (int i = 0; i < progrmFileNms.length; i++) {
			vo.setProgrmFileNm(progrmFileNms[i]);
			progrmManageMapper.deleteProgrm(vo);
		}
	}

}