package aramframework.com.uss.ion.lsi.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 로그인화면이미지에 대한 Vo 클래스를 정의한다.
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

public class LoginScrinImageVO extends BaseVO {

	// domain
	/** 이미지 ID */
	private String imageId;
	
	/** 이미지명 */
	private String imageNm;
	
	/** 반영여부 */
	private String reflctAt;
	
	/** 로그인 이미지 */
	private String image;
	
	/** 이미지 설명 */
	private String imageDc;
	
	/** 로그인 이미지 파일 */
	private String imageFile;
	
	// helper
	/** 사용자 ID */
	private String userId;
	
	/** 등록일자 */
	private String regDate;
	
	/** 파일첨부여부 */
	private boolean isAtchFile;

	// domain
	/**
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId;
	}
	/**
	 * @param imageId
	 *            the imageId to set
	 */
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the imageNm
	 */
	public String getImageNm() {
		return imageNm;
	}
	/**
	 * @param imageNm
	 *            the imageNm to set
	 */
	public void setImageNm(String imageNm) {
		this.imageNm = imageNm;
	}

	/**
	 * @return the reflctAt
	 */
	public String getReflctAt() {
		return reflctAt;
	}
	/**
	 * @param reflctAt
	 *            the reflctAt to set
	 */
	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the imageDc
	 */
	public String getImageDc() {
		return imageDc;
	}
	/**
	 * @param imageDc
	 *            the imageDc to set
	 */
	public void setImageDc(String imageDc) {
		this.imageDc = imageDc;
	}

	/**
	 * @return the imageFile
	 */
	public String getImageFile() {
		return imageFile;
	}
	/**
	 * @param imageFile
	 *            the imageFile to set
	 */
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	// helper	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the isAtchFile
	 */
	public boolean isAtchFile() {
		return isAtchFile;
	}
	/**
	 * @param isAtchFile
	 *            the isAtchFile to set
	 */
	public void setAtchFile(boolean isAtchFile) {
		this.isAtchFile = isAtchFile;
	}

}
