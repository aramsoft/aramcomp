package aramframework.com.cop.ems.dao;

import java.util.List;

import aramframework.com.cop.ems.domain.AtchmnFileVO;
import aramframework.com.cop.ems.domain.SndngMailVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 발송메일 내역을 조회하는 DAO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface SndngMailMapper {

	/**
	 * 발송메일 목록을 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	public List<EgovMap> selectSndngMailList(SndngMailVO sndngMailVO);

	/**
	 * 발송메일 총건수를 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	public int selectSndngMailListCnt(SndngMailVO sndngMailVO);

	/**
	 * 발송메일을 상세 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	public SndngMailVO selectSndngMail(SndngMailVO sndngMailVO);

	/**
	 * 발송할 메일을 등록한다
	 * 
	 * @param sndngMailVO
	 */
	public void insertSndngMail(SndngMailVO sndngMailVO);

	/**
	 * 발송결과를 수정한다.
	 * 
	 * @param sndngMailVO
	 */
	public void updateSndngMail(SndngMailVO sndngMailVO); 

	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	public void deleteSndngMail(SndngMailVO sndngMailVO);

	/**
	 * 발송할 메일에 있는 첨부파일 목록을 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	public List<AtchmnFileVO> selectAtchmnFileList(SndngMailVO sndngMailVO);

}
