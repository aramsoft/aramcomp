package aramframework.com.utl.fcc.service;

import java.io.Serializable;

import lombok.Data;

/**
 * Form-based File Upload VO
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@SuppressWarnings("serial")
@Data
public class FileUploadFileVo implements Serializable {

	/** 파일명 */
	private String fileName = "";
	/** ContextType */
	private String contentType = "";
	/** 하위 디렉토리 지정 */
	private String serverSubPath = "";
	/** 물리적 파일명 */
	private String physicalName = "";
	/** 파일 사이즈 */
	private long size = 0L;

}
