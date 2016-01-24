package aramframework.com.sym.tbm.tbp.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.tbm.tbp.service.TroblProcessService;
import aramframework.com.sym.tbm.tbp.service.TroblProcessVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 장애처리결과 관리정보에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("troblProcessService")
public class TroblProcessServiceImpl extends EgovAbstractServiceImpl implements TroblProcessService {

	@Autowired
	private TroblProcessMapper troblProcessMapper;	

	/**
	 * 장애처리정보를 관리하기 위해 대상 장애처리목록을 조회한다.
	 * 
	 * @param troblProcessVO 
	 */
	public List<EgovMap> selectTroblProcessList(TroblProcessVO troblProcessVO) {
		return troblProcessMapper.selectTroblProcessList(troblProcessVO);
	}

	/**
	 * 장애처리목록 총 갯수를 조회한다.
	 * 
	 * @param troblProcessVO
	 */
	public int selectTroblProcessListCnt(TroblProcessVO troblProcessVO) {
		return troblProcessMapper.selectTroblProcessListCnt(troblProcessVO);
	}

	/**
	 * 등록된 장애처리의 상세정보를 조회한다.
	 * 
	 * @param troblProcessVO
	 */
	public TroblProcessVO selectTroblProcess(TroblProcessVO troblProcessVO) {
		TroblProcessVO resultVo = troblProcessMapper.selectTroblProcess(troblProcessVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, troblProcessVO,
				Arrays.asList(new String[] {"strTroblNm", "strTroblKnd", "strProcessSttus"}) ); 
		return resultVo;
	}

	/**
	 * 장애처리정보를 신규로 등록한다.
	 * 
	 * @param troblProcessVO
	 */
	public void insertTroblProcess(TroblProcessVO troblProcessVO) {
		troblProcessMapper.insertTroblProcess(troblProcessVO);
	}

	/**
	 * 기 등록된 장애처리정보를 삭제한다.
	 * 
	 * @param troblProcessVO
	 */
	public void deleteTroblProcess(TroblProcessVO troblProcessVO) {
		troblProcessMapper.deleteTroblProcess(troblProcessVO);
	}

}