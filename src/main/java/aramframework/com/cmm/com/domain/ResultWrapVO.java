package aramframework.com.cmm.com.domain;

/**
 * Result Wrap VO클래스.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2017.03.13
 * @version 1.0
 *
 */
public class ResultWrapVO {

	// domain
	private String	image_id;
	private String	image;
	private String	owner;
	private String	meta;
	private String	created_date;
	private String	status;
	private Object  ocr_result;
	
	// domain
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Object getOcr_result() {
		return ocr_result;
	}
	public void setOcr_result(Object ocr_result) {
		this.ocr_result = ocr_result;
	}

}
