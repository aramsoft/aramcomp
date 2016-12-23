package aramframework.com.cop.smt.mtm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.smt.mtm.dao.MemoTodoMapper;
import aramframework.com.cop.smt.mtm.domain.MemoTodoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 : 메모할일에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class MemoTodoService extends EgovAbstractServiceImpl {

	@Autowired 
	private MemoTodoMapper memoTodoMapper;	

	@Autowired 
	private EgovIdGnrService memoTodoIdGnrService; 

	/**
	 * 메모할일 목록 중 오늘의 할일을 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public List<EgovMap> selectMemoTodoListToday(MemoTodoVO memoTodoVO) {
		return memoTodoMapper.selectMemoTodoListToday(memoTodoVO);
	}

	/**
	 * 메모할일 목록을 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public List<EgovMap> selectMemoTodoList(MemoTodoVO memoTodoVO) {
		return memoTodoMapper.selectMemoTodoList(memoTodoVO);
	}

	/**
	 * 메모할일 총횟수를 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public int selectMemoTodoListCnt(MemoTodoVO memoTodoVO) {
		return memoTodoMapper.selectMemoTodoListCnt(memoTodoVO);
	}

	/**
	 * 메모할일 정보를 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public MemoTodoVO selectMemoTodo(MemoTodoVO memoTodoVO) {
		MemoTodoVO resultVo = memoTodoMapper.selectMemoTodo(memoTodoVO);
		// searchVO 이전 
		resultVo.setSearchVO(memoTodoVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 메모할일 정보를 등록한다.
	 * 
	 * @param memoTodoVO
	 */
	public void insertMemoTodo(MemoTodoVO memoTodoVO) {
		try {
			memoTodoVO.setTodoId(memoTodoIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		memoTodoMapper.insertMemoTodo(memoTodoVO);
	}

	/**
	 * 메모할일 정보를 수정한다.
	 * 
	 * @param memoTodoVO
	 */
	public void updateMemoTodo(MemoTodoVO memoTodoVO) {
		memoTodoMapper.updateMemoTodo(memoTodoVO);
	}

	/**
	 * 메모할일 정보를 삭제한다.
	 * 
	 * @param memoTodoVO
	 */
	public void deleteMemoTodo(MemoTodoVO memoTodoVO) {
		memoTodoMapper.deleteMemoTodo(memoTodoVO);
	}

}