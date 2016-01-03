package aramframework.com.cmm.service;

import java.io.Serializable;

/**
 * 공통상세코드 모델 클래스
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

public class CacheVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 캐쉬그룹 */
	private String cacheGroup = "";

	/** 캐쉬키 */
	private String cacheKey = "";

	/** 캐쉬값 */
	private String cacheValue = "";

	public String getCacheGroup() {
		return cacheGroup;
	}
	public void setCacheGroup(String cacheGroup) {
		this.cacheGroup = cacheGroup;
	}

	public String getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public String getCacheValue() {
		return cacheValue;
	}
	public void setCacheValue(String cacheValue) {
		this.cacheValue = cacheValue;
	}


}
