package aramframework.com.cmm.domain;

/**
 * 커뮤니티 관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ImageHanjaVO {

	private int x;
	private int y;
	private int w;
	private int h;
	
	private String hanja = "";

	// domain
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public String getHanja() {
		return hanja;
	}

	public void setHanja(String hanja) {
		this.hanja = hanja;
	}

}
