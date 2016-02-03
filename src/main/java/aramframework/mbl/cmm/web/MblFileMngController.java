package aramframework.mbl.cmm.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.service.FileMngService;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
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

@Controller
public class MblFileMngController {
	
	@Autowired
    private FileMngService fileService;

	/**
	 * 첨부파일에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 * @param atchFileId
]	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfs.mdo")
	public String selectFileInfs(
			@RequestParam(value="param_atchFileId", required=true) String atchFileId,
			ModelMap model) 
	throws Exception {

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileList(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "aramframework/mbl/cmm/fms/FileList";
	}

	/**
	 * 첨부파일 변경을 위한 수정페이지로 이동한다.
	 * 
	 * @param fileVO
	 * @param atchFileId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/editFileInfs.mdo")
	public String selectFileInfsForUpdate(
			@RequestParam(value="param_atchFileId", required=true) String atchFileId,
			ModelMap model) 
	throws Exception {

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileList(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "aramframework/mbl/cmm/fms/FileList";
	}

	/**
	 * 이미지 첨부파일에 대한 목록을 조회한다.
	 * 
	 * @param fileVO
	 * @param atchFileId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectImageFileInfs.mdo")
	public String selectImageFileInfs(
			@RequestParam(value="param_atchFileId", required=true) String atchFileId,
			ModelMap model) 
	throws Exception {

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectImageFileList(fileVO);

		model.addAttribute("fileList", result);

		return "aramframework/mbl/cmm/fms/ImgFileList";
	}

	/**
	 * 첨부파일에 대한 삭제를 처리한다.
	 * 
	 * @param fileVO
	 * @param returnUrl
]	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/deleteFileInfs.mdo")
	@Secured("ROLE_USER")
	public String deleteFileInf(
			@RequestParam("returnUrl") String returnUrl,
			FileVO fileVO,
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {

		fileService.selectFileInf(fileVO);

		File uFile = new File(fileVO.getFileStreCours(), fileVO.getStreFileNm());
		uFile.delete();
		
		fileService.deleteFileInf(fileVO);

		// --------------------------------------------
		// contextRoot가 있는 경우 제외 시켜야 함
		// --------------------------------------------
		// return "forward:/cmm/fms/selectFileInfs.do";
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
