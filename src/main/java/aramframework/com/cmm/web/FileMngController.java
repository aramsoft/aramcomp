package aramframework.com.cmm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.service.FileMngService;

/**
 * 파일 조회, 삭제처리를 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class FileMngController {

	@Autowired
	private FileMngService fileMngService;

	/**
	 * 첨부파일에 대한 목록을 조회한다.
	 * 
	 * @param fileId	String
	 * @param fileVO	FileVO
	 * @param model		ModelMap
	 * @return			String
	 * @throws 			Exception
	 */
	@RequestMapping("/content/files/{fileId}")
	public String selectFileList(
			@PathVariable String fileId, 
			@ModelAttribute FileVO fileVO, 
			ModelMap model) 
	throws Exception {

		fileVO.setAtchFileId(fileId);
		List<FileVO> result = fileMngService.selectFileList(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());

		return "cmm/fms/FileList";
	}

	/**
	 * 첨부파일 변경을 위한 수정페이지로 이동한다.
	 * 
	 * @param fileId	String
	 * @param fileVO	FileVO
	 * @param model		ModelMap
	 * @return			String
	 * @throws 			Exception
	 */
	@RequestMapping("/content/files/{fileId}/editform")
	public String editFileInfs(
			@PathVariable String fileId, 
			@ModelAttribute FileVO fileVO, 
			ModelMap model) 
	throws Exception {

		fileVO.setAtchFileId(fileId);
		List<FileVO> result = fileMngService.selectFileList(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCnt", result.size());

		return "cmm/fms/FileList";
	}

	/**
	 * 이미지 첨부파일에 대한 목록을 조회한다.
	 * 
	 * @param fileId	String
	 * @param fileVO	FileVO
	 * @param model		ModelMap
	 * @return			String
	 * @throws 			Exception
	 */
	@RequestMapping("/content/imagefiles/{fileId}")
	public String selectImageFileList(
			@PathVariable String fileId, 
			@ModelAttribute FileVO fileVO, 
			ModelMap model) 
	throws Exception {

		fileVO.setAtchFileId(fileId);
		List<FileVO> result = fileMngService.selectImageFileList(fileVO);

		model.addAttribute("fileList", result);

		return "cmm/fms/ImgFileList";
	}

	/**
	 * 첨부파일에 대한 삭제를 처리한다.
	 * 
	 * @param returnUrl	String
	 * @param fileVO	FileVO
	 * @param request	HttpServletRequest
	 * @return			String
	 * @throws 			Exception
	 */
	@RequestMapping("/cmm/fms/deleteFileInfs.do")
	@Secured("ROLE_USER")
	public String deleteFileInf(
			@RequestParam String returnUrl,
			@ModelAttribute FileVO fileVO, 
			HttpServletRequest request) 
	throws Exception {

		fileMngService.deleteFileInf(fileVO);

		// --------------------------------------------
		// contextRoot가 있는 경우 제외 시켜야 함
		// --------------------------------------------
		// return "forward:/content/files/"+fileVO.getFileId();
		// return "forward:" + returnUrl;

		if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
			return "forward:" + returnUrl;
		}

		if (returnUrl.startsWith(request.getContextPath())) {
			return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
		} else {
			return "forward:" + returnUrl;
		}
		// //------------------------------------------
	}

}
