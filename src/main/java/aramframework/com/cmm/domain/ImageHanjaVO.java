package aramframework.com.cmm.domain;

/**
 * 커뮤니티 관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ImageHanjaVO {

	private double x;
	private double y;
	private double w;
	private double h;
	
	private String hanja = "";

	// domain
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public String getHanja() {
		return hanja;
	}

	public void setHanja(String hanja) {
		this.hanja = hanja;
	}

}
