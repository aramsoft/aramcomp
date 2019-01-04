package aramframework.com.utl.sys.fsm.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.utl.sys.fsm.dao.FileSysMntrngMapper;
import aramframework.com.utl.sys.fsm.domain.FileSysMntrngLogVO;
import aramframework.com.utl.sys.fsm.domain.FileSysMntrngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요 파일시스템 모니터링대상에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용 - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 *         - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class FileSysMntrngService extends EgovAbstractServiceImpl {

	@Autowired
	private FileSysMntrngMapper fileSysMntrngMapper;
	
	@Autowired
	private EgovIdGnrService fileSysMntrngIdGnrService; 

	@Autowired
	private EgovIdGnrService fileSysMntrngLogIdGnrService; 

	/**
	 * 파일시스템 모니터링대상 목록을 조회 한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public List<FileSysMntrngVO> selectFileSysMntrngList(FileSysMntrngVO fileSysMntrngVO) {
		return fileSysMntrngMapper.selectFileSysMntrngList(fileSysMntrngVO);
	}

	/**
	 * 파일시스템 모니터링대상 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public int selectFileSysMntrngListCnt(FileSysMntrngVO fileSysMntrngLogVO) {
		return fileSysMntrngMapper.selectFileSysMntrngListCnt(fileSysMntrngLogVO);
	}

	/**
	 * 파일시스템 모니터링대상을 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public FileSysMntrngVO selectFileSysMntrng(FileSysMntrngVO fileSysMntrngVO) {
		return fileSysMntrngMapper.selectFileSysMntrng(fileSysMntrngVO);
	}

	/**
	 * 파일시스템 모니터링대상을 등록한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void insertFileSysMntrng(FileSysMntrngVO fileSysMntrngVO) {
		try {
			fileSysMntrngVO.setFileSysId(fileSysMntrngIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		fileSysMntrngMapper.insertFileSysMntrng(fileSysMntrngVO);
	}

	/**
	 * 파일시스템 모니터링대상을 수정한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void updateFileSysMntrng(FileSysMntrngVO fileSysMntrngVO) {
		fileSysMntrngMapper.updateFileSysMntrng(fileSysMntrngVO);
	}

	/**
	 * 파일시스템 모니터링대상을 삭제한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void deleteFileSysMntrng(FileSysMntrngVO fileSysMntrngVO) {
		fileSysMntrngMapper.deleteFileSysMntrng(fileSysMntrngVO);
	}

	/**
	 * 파일시스템의 크기를 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public int selectFileSysMg(FileSysMntrngVO fileSysMntrngVO) {
		try {
			FileSystemUtils.freeSpaceKb("");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}

	/**
	 * 파일시스템 모니터링 결과를 수정한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	public void updateFileSysMntrngSttus(FileSysMntrngVO fileSysMntrngVO) {
		fileSysMntrngMapper.updateFileSysMntrngSttus(fileSysMntrngVO);

		FileSysMntrngLogVO fileSysMntrngLogVO = new FileSysMntrngLogVO();
		try {
			fileSysMntrngLogVO.setLogId(fileSysMntrngLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		fileSysMntrngLogVO.setFileSysId(fileSysMntrngVO.getFileSysId());
		fileSysMntrngLogVO.setFileSysNm(fileSysMntrngVO.getFileSysNm());
		fileSysMntrngLogVO.setFileSysManageNm(fileSysMntrngVO.getFileSysManageNm());
		fileSysMntrngLogVO.setFileSysMg(fileSysMntrngVO.getFileSysMg());
		fileSysMntrngLogVO.setFileSysThrhld(fileSysMntrngVO.getFileSysThrhld());
		fileSysMntrngLogVO.setFileSysUsgQty(fileSysMntrngVO.getFileSysUsgQty());
		fileSysMntrngLogVO.setLogInfo(fileSysMntrngVO.getLogInfo());
		fileSysMntrngLogVO.setMntrngSttus(fileSysMntrngVO.getMntrngSttus());
		fileSysMntrngLogVO.setCreatDt(fileSysMntrngVO.getCreatDt());
		fileSysMntrngLogVO.setFrstRegisterId(fileSysMntrngVO.getLastUpdusrId());
		insertFileSysMntrngLog(fileSysMntrngLogVO);
	}

	/**
	 * 파일시스템 모니터링로그 목록을 조회 한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public List<FileSysMntrngLogVO> selectFileSysMntrngLogList(FileSysMntrngLogVO fileSysMntrngLogVO) {
		return fileSysMntrngMapper.selectFileSysMntrngLogList(fileSysMntrngLogVO);
	}

	/**
	 * 파일시스템 모니터링로그 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public int selectFileSysMntrngLogListCnt(FileSysMntrngLogVO fileSysMntrngLogVO) {
		return fileSysMntrngMapper.selectFileSysMntrngLogListCnt(fileSysMntrngLogVO);
	}

	/**
	 * 파일시스템 모니터링로그를 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public FileSysMntrngLogVO selectFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO) {
		return fileSysMntrngMapper.selectFileSysMntrngLog(fileSysMntrngLogVO);
	}

	/**
	 * 파일시스템 모니터링로그를 등록한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public void insertFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO) {
		fileSysMntrngMapper.insertFileSysMntrngLog(fileSysMntrngLogVO);
	}

}