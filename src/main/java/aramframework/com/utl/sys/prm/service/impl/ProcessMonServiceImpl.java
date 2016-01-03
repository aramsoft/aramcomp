package aramframework.com.utl.sys.prm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.utl.sys.prm.service.ProcessMonService;
import aramframework.com.utl.sys.prm.service.ProcessMonLogVO;
import aramframework.com.utl.sys.prm.service.ProcessMonVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요 - PROCESS모니터링에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용 - PROCESS모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - PROCESS모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
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

@Service("processMonService")
public class ProcessMonServiceImpl extends EgovAbstractServiceImpl implements ProcessMonService {

	@Resource(name = "processMonMapper")
	private ProcessMonMapper processMonMapper;	

	@Resource(name = "processMonIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name = "processMonLogIdGnrService")
	private EgovIdGnrService idgenServiceLog;

	/**
	 * 등록된 PROCESS모니터링 목록을 조회한다.
	 * 
	 * @param processMonVO
	 *            - PROCESS모니터링 Vo
	 * @return List - PROCESS모니터링 목록
	 */
	public List<ProcessMonVO> selectProcessMonList(ProcessMonVO processMonVO) {
		return processMonMapper.selectProcessMonList(processMonVO);
	}

	/**
	 * PROCESS모니터링 목록 총 갯수를 조회한다.
	 * 
	 * @param processMonVO
	 *            - PROCESS모니터링 Vo
	 * @return int - PROCESS모니터링 토탈 카운트 수
	 */
	public int selectProcessMonListCnt(ProcessMonVO processMonVO) {
		return processMonMapper.selectProcessMonListCnt(processMonVO);
	}

	/**
	 * 등록된 PROCESS모니터링의 상세정보를 조회한다.
	 * 
	 * @param processMonVO
	 *            - PROCESS모니터링 Vo
	 * @return processMonVO - PROCESS모니터링 Vo
	 */
	public ProcessMonVO selectProcessMon(ProcessMonVO processMonVO) {
		ProcessMonVO resultVo = processMonMapper.selectProcessMon(processMonVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, processMonVO); 
		return resultVo;
	}

	/**
	 * PROCESS모니터링 정보를 신규로 등록한다.
	 * 
	 * @param processNm
	 *            - PROCESS모니터링 model
	 */
	public void insertProcessMon(ProcessMonVO processMonVO) {
		try {
			processMonVO.setProcessId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		processMonMapper.insertProcessMon(processMonVO);
	}

	/**
	 * 기 등록된 PROCESS모니터링 정보를 수정한다.
	 * 
	 * @param processNm
	 *            - PROCESS모니터링 model
	 */
	public void updateProcessMon(ProcessMonVO processMonVO) {
		processMonMapper.updateProcessMon(processMonVO);
	}

	/**
	 * 기 등록된 PROCESS모니터링 정보를 삭제한다.
	 * 
	 * @param processNm
	 *            - PROCESS모니터링 model
	 */
	public void deleteProcessMon(ProcessMonVO processMonVO) {
		processMonMapper.deleteProcessMon(processMonVO);
	}

	/**
	 * 프로세스 모니터링 결과를 수정한다.
	 * 
	 * @param processMonLog
	 *            - 프로세스 모니터링대상 model
	 */
	public void updateProcessMonSttus(ProcessMonVO processMonVO) {
		processMonMapper.updateProcessMonSttus(processMonVO);

		ProcessMonLogVO processMonLogVO = new ProcessMonLogVO();
		processMonLogVO.setProcessId(processMonVO.getProcessId());
		try {
			processMonLogVO.setLogId(idgenServiceLog.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		processMonLogVO.setProcessNm(processMonVO.getProcessNm());
		processMonLogVO.setProcsSttus(processMonVO.getProcsSttus());
		processMonLogVO.setCreatDt(processMonVO.getCreatDt());
		processMonLogVO.setLogInfo(processMonVO.getLogInfo());
		processMonLogVO.setMngrNm(processMonVO.getMngrNm());
		processMonLogVO.setMngrEmailAddr(processMonVO.getMngrEmailAddr());
		processMonLogVO.setFrstRegisterId(processMonVO.getLastUpdusrId());
		insertProcessMonLog(processMonLogVO);
	}

	/**
	 * 등록된 프로세스 모니터링로그 목록을 조회한다.
	 * 
	 * @param processMonVO
	 *            - PROCESS모니터링 Vo
	 * @return List - PROCESS모니터링 목록
	 */
	public List<ProcessMonLogVO> selectProcessMonLogList(ProcessMonLogVO processMonLogVO) {
		return processMonMapper.selectProcessMonLogList(processMonLogVO);
	}

	/**
	 * 등록된 프로세스 모니터링로그 목록 총 갯수를 조회한다.
	 * 
	 * @param processMonVO
	 *            - PROCESS모니터링 Vo
	 * @return int - PROCESS모니터링 토탈 카운트 수
	 */
	public int selectProcessMonLogListCnt(ProcessMonLogVO processMonLogVO) {
		return processMonMapper.selectProcessMonLogListCnt(processMonLogVO);
	}


	/**
	 * 프로세스 모니터링로그의 상세정보를 조회한다.
	 * 
	 * @param ProcessMonVO
	 *            - 프로세스모니터링로그 model
	 * @return ProcessMonVO - 프로세스모니터링로그 model
	 */
	public ProcessMonLogVO selectProcessMonLog(ProcessMonLogVO processMonLogVO) {
		ProcessMonLogVO resultVo = processMonMapper.selectProcessMonLog(processMonLogVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, processMonLogVO); 
		return resultVo;
	}

	/**
	 * 프로세스 모니터링로그를 등록한다.
	 * 
	 * @param processMonLog
	 *            - 프로세스 모니터링로그 model
	 */
	public void insertProcessMonLog(ProcessMonLogVO processMonLogVO) {
		processMonMapper.insertProcessMonLog(processMonLogVO);
	}

}