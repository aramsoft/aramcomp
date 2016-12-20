package aramframework.com.cmm.annotation;

/**
 * IncludedInfo annotation을 바탕으로 화면에 표시할 정보를 구성하기 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 * 
 */
public class IncludedInfoVO {

	private String name;
	private String listUrl;
	private int order;
	private int gid;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getListUrl() {
		return listUrl;
	}
	public void setListUrl(String listUrl) {
		this.listUrl = listUrl;
	}

	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
}
