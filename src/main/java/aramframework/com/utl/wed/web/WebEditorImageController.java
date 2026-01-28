package aramframework.com.utl.wed.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aramframework.cmm.constant.Globals;
import aramframework.com.utl.fcc.service.FileUploadUtil;
import aramframework.com.utl.fcc.service.FormBasedFileUtil;
import aramframework.com.utl.fcc.service.FormBasedFileVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 웹에디터 이미지 upload 처리 Controller
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class WebEditorImageController {

	/** 첨부파일 위치 지정 */
	private final String uploadDir = Globals.FILE_UPLOAD_DIR;

	/** 첨부 최대 파일 크기 지정 */
	private final long maxFileSize = 1024 * 1024 * 100; // 업로드 최대 사이즈 설정 (100M)

	/**
	 * 이미지 Upload 화면으로 이동한다.
	 * 
	 */
	@RequestMapping(value = "/utl/wed/insertImage.do", method = RequestMethod.GET)
	public String goInsertImage() {
		return "com/utl/wed/InsertImage";
	}

	/**
	 * 이미지 Upload를 처리한다.
	 * 
	 */
	@RequestMapping(value = "/utl/wed/insertImage.do", method = RequestMethod.POST)
	public String imageUpload(HttpServletRequest request, ModelMap model) 
	throws Exception {
		// Spring multipartResolver 미사용 시 (commons-fileupload 활용)
		// List<EgovFormBasedFileVo> list = EgovFormBasedFileUtil.uploadFiles(request, uploadDir, maxFileSize);

		// Spring multipartResolver 사용시
		List<FormBasedFileVo> list = FileUploadUtil.uploadFiles(request, uploadDir, maxFileSize);

		if (list.size() > 0) {
			FormBasedFileVo vo = list.get(0); // 첫번째 이미지

			String url = request.getContextPath() + "/utl/wed/imageSrc.do?" + "path=" + vo.getServerSubPath() + "&physical=" + vo.getPhysicalName()
					+ "&contentType=" + vo.getContentType();

			model.addAttribute("url", url);
		}

		return "com/utl/wed/InsertImage";
	}

	/**
	 * 이미지 view를 제공한다.
	 * 
	 */
	@RequestMapping(value = "/utl/wed/imageSrc.do", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		String subPath = request.getParameter("path");
		String physical = request.getParameter("physical");
		String mimeType = request.getParameter("contentType");

		FormBasedFileUtil.viewFile(response, uploadDir, subPath, physical, mimeType);
	}
}
