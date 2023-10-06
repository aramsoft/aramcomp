package aramframework.com.cmm.com.domain;

/**
 * 커뮤니티 관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class HanjaDicVO {

	private int id = 0;
	private String hanja = "";
	private String hanjaDic = "";

	// domain
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHanja() {
		return hanja;
	}

	public void setHanja(String hanja) {
		this.hanja = hanja;
	}

	public String getHanjaDic() {
		return hanjaDic;
	}

	public void setHanjaDic(String hanjaDic) {
		this.hanjaDic = hanjaDic;
	}

}
