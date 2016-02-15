package aramframework.com.sym.bat.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 배치작업관리에 대한 model 클래스
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

public class BatchOpertVO extends BaseVO {

	// domain
	/** 배치작업ID */
	private String batchOpertId;
	
	/** 배치작업명 */
	private String batchOpertNm;
	
	/** 배치프로그램 */
	private String batchProgrm;
	
	/** 파라미터 */
	private String paramtr;
	
	/** 배치클래스 */
	private String batchObject;
	
	/** 배치메소드 */
	private String batchMethod;
	
	/** 배치빈 */
	private String batchBean;
	
	/** 사용여부 */
	private String useAt;
	
	// domain
	/**
	 * 배치작업ID를 리턴한다.
	 * 
	 * @return the batchOpertId
	 */
	public String getBatchOpertId() {
		return batchOpertId;
	}
	/**
	 * 배치작업ID를 설정한다.
	 * 
	 * @param batchOpertId
	 *            설정할 배치작업ID
	 */
	public void setBatchOpertId(String batchOpertId) {
		this.batchOpertId = batchOpertId;
	}

	/**
	 * 배치작업명을 리턴한다.
	 * 
	 * @return the batchOpertNm
	 */
	public String getBatchOpertNm() {
		return batchOpertNm;
	}
	/**
	 * 배치작업명을 설정한다.
	 * 
	 * @param batchOpertNm
	 *            설정할 배치작업명
	 */
	public void setBatchOpertNm(String batchOpertNm) {
		this.batchOpertNm = batchOpertNm;
	}

	/**
	 * 배치프로그램을 리턴한다.
	 * 
	 * @return the batchProgrm
	 */
	public String getBatchProgrm() {
		return batchProgrm;
	}
	/**
	 * 배치프로그램을 설정한다.
	 * 
	 * @param batchProgrm
	 *            설정할 배치프로그램
	 */
	public void setBatchProgrm(String batchProgrm) {
		this.batchProgrm = batchProgrm;
	}

	/**
	 * 파라미터를 리턴한다.
	 * 
	 * @return the paramtr
	 */
	public String getParamtr() {
		return paramtr;
	}
	/**
	 * 파라미터를 설정한다.
	 * 
	 * @param paramtr
	 *            설정할 파라미터
	 */
	public void setParamtr(String paramtr) {
		this.paramtr = paramtr;
	}

	/**
	 * 배치클래스을 리턴한다.
	 * 
	 * @return the batchObject
	 */
	public String getBatchObject() {
		return batchObject;
	}
	/**
	 * 배치클래스을 설정한다.
	 * 
	 * @param batchObject
	 *            설정할 배치클래스
	 */
	public void setBatchObject(String batchObject) {
		this.batchObject = batchObject;
	}

	/**
	 * 배치메소드을 리턴한다.
	 * 
	 * @return the batchMethod
	 */
	public String getBatchMethod() {
		return batchMethod;
	}
	/**
	 * 배치메소드을 설정한다.
	 * 
	 * @param batchMethod
	 *            설정할 배치메소드
	 */            
	public void setBatchMethod(String batchMethod) {
		this.batchMethod = batchMethod;
	}

	/**
	 * 배치빈을 리턴한다.
	 * 
	 * @return the batchBean
	 */
	public String getBatchBean() {
		return batchBean;
	}
	/**
	 * 배치빈을 설정한다.
	 * 
	 * @param batchBean
	 *            설정할 배치빈
	 */            
	public void setBatchBean(String batchBean) {
		this.batchBean = batchBean;
	}

	/**
	 * 사용여부를 리턴한다.
	 * 
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * 사용여부를 설정한다.
	 * 
	 * @param useAt
	 *            설정할 사용여부
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

}