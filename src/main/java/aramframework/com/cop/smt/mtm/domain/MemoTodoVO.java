package aramframework.com.cop.smt.mtm.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 메모할일에 대한 Vo 클래스를 정의한다.
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

public class MemoTodoVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 사용자ID조회조건 */
	private String searchId = "";

	/** 일자 조회조건 */
	private String searchDe = "";

	/** 시작일자 조회조건 */
	private String searchBgnDe = "";

	/** 종료일자 조회조건 */
	private String searchEndDe = "";

	/** 할일ID */
	private String todoId;

	/** 할일제목 */
	private String todoNm;

	/** 할일시작시간 */
	private String todoBeginTime;

	/** 할일종료시간 */
	private String todoEndTime;

	/** 할일일자 */
	private String todoDe;

	/** 할일시작시 */
	private String todoBeginHour;

	/** 할일시작분 */
	private String todoBeginMin;

	/** 할일종료시 */
	private String todoEndHour;

	/** 할일종료분 */
	private String todoEndMin;

	/** 작성자ID	 */
	private String wrterId;

	/** 작성자명 */
	private String wrterNm;

	/** 할일내용 */
	private String todoCn;

	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String getSearchDe() {
		return searchDe;
	}
	public void setSearchDe(String searchDe) {
		this.searchDe = searchDe;
	}

	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getTodoId() {
		return todoId;
	}
	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public String getTodoNm() {
		return todoNm;
	}
	public void setTodoNm(String todoNm) {
		this.todoNm = todoNm;
	}

	public String getTodoBeginTime() {
		return todoBeginTime;
	}
	public void setTodoBeginTime(String todoBeginTime) {
		this.todoBeginTime = todoBeginTime;
	}

	public String getTodoEndTime() {
		return todoEndTime;
	}
	public void setTodoEndTime(String todoEndTime) {
		this.todoEndTime = todoEndTime;
	}

	public String getTodoDe() {
		return todoDe;
	}
	public void setTodoDe(String todoDe) {
		this.todoDe = todoDe;
	}

	public String getTodoBeginHour() {
		return todoBeginHour;
	}
	public void setTodoBeginHour(String todoBeginHour) {
		this.todoBeginHour = todoBeginHour;
	}

	public String getTodoBeginMin() {
		return todoBeginMin;
	}
	public void setTodoBeginMin(String todoBeginMin) {
		this.todoBeginMin = todoBeginMin;
	}

	public String getTodoEndHour() {
		return todoEndHour;
	}
	public void setTodoEndHour(String todoEndHour) {
		this.todoEndHour = todoEndHour;
	}

	public String getTodoEndMin() {
		return todoEndMin;
	}
	public void setTodoEndMin(String todoEndMin) {
		this.todoEndMin = todoEndMin;
	}

	public String getWrterId() {
		return wrterId;
	}
	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	public String getWrterNm() {
		return wrterNm;
	}
	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	public String getTodoCn() {
		return todoCn;
	}
	public void setTodoCn(String todoCn) {
		this.todoCn = todoCn;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}