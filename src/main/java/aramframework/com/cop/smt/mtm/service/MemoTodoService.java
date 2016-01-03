package aramframework.com.cop.smt.mtm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 메모할일에 대한 Service Interface를 정의한다.
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

public interface MemoTodoService {

	/**
	 * 메모할일 목록 중 오늘의 할일을 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public List<EgovMap> selectMemoTodoListToday(MemoTodoVO memoTodoVO);

	/**
	 * 메모할일 목록을 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public List<EgovMap> selectMemoTodoList(MemoTodoVO memoTodoVO);

	/**
	 * 메모할일 총횟수를 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public int selectMemoTodoListCnt(MemoTodoVO memoTodoVO);

	/**
	 * 메모할일 정보를 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	public MemoTodoVO selectMemoTodo(MemoTodoVO memoTodoVO);

	/**
	 * 메모할일 정보를 등록한다.
	 * 
	 * @param memoTodoVO
	 */
	public void insertMemoTodo(MemoTodoVO memoTodoVO);

	/**
	 * 메모할일 정보를 수정한다.
	 * 
	 * @param memoTodoVO
	 */
	public void updateMemoTodo(MemoTodoVO memoTodoVO);

	/**
	 * 메모할일 정보를 삭제한다.
	 * 
	 * @param memoTodoVO
	 */
	public void deleteMemoTodo(MemoTodoVO memoTodoVO);

}