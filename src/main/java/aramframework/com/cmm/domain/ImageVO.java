package aramframework.com.cmm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 커뮤니티 관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ImageVO extends BaseVO {

	/** image file name */
	private String imageName = "";

	/** image type */
	private String imageType = "";

	/** image */
	private byte[] image;

	// domain
	/** image file name 	 */ 
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/** image type 	 */ 
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	/** image 	 */ 
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

}
