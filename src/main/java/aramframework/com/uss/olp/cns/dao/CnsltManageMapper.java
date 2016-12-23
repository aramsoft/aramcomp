package aramframework.com.uss.olp.cns.dao;

import java.util.List;

import aramframework.com.uss.olp.cns.domain.CnsltManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 상담내용을 처리하는 DAO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface CnsltManageMapper {

	/**
	 * 상담내용 글 목록을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public List<EgovMap> selectCnsltList(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글 총 갯수를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public int selectCnsltListCnt(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public CnsltManageVO selectCnsltListDetail(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 등록한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void insertCnsltDtls(CnsltManageVO cnsltManageVO);

	/**
	 * 작성비밀번호를 확인한다.
	 * 
	 * @param cnsltManageVO
	 */
	public int selectCnsltPasswordConfirmCnt(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 수정한다.(조회수를 수정)
	 * 
	 * @param cnsltManageVO
	 */
	public void updateCnsltInqireCo(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 수정한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void updateCnsltDtls(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 삭제한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void deleteCnsltDtls(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글 목록을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public List<EgovMap> selectCnsltAnswerList(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글 총 갯수를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public int selectCnsltAnswerListCnt(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	public CnsltManageVO selectCnsltAnswerDetail(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글을 수정한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void updateCnsltDtlsAnswer(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글을 삭제한다.
	 * 
	 * @param cnsltManageVO
	 */
	public void deleteCnsltDtlsAnswer(CnsltManageVO cnsltManageVO);

}
