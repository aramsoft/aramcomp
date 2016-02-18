package aramframework.com.cop.ems.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.ems.domain.SndngMailVO;
import aramframework.com.cop.ems.service.SndngMailService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 발송메일 내역을 조회하는 컨트롤러 클래스
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
public class SndngMailController {

	@Autowired 
	private SndngMailService sndngMailService;

	@Autowired 
	private FileMngUtil fileUtil;

    /** 파일구분자 */
    static final char FILE_SEPARATOR = File.separatorChar;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 발송메일 내역을 조회한다
	 * 
	 * @param sndngMailVO
	 */
	@IncludedInfo(name = "발송메일관리", order = 4500, gid = 45)
	@RequestMapping(value = "/cop/ems/listSndngMail.do")
	@Secured("ROLE_USER")
	public String listSndngMail(
			@ModelAttribute SndngMailVO sndngMailVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		sndngMailVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", sndngMailService.selectSndngMailList(sndngMailVO));
		int totCnt = sndngMailService.selectSndngMailListCnt(sndngMailVO);

		sndngMailVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/ems/MailList");
	}

	/**
	 * 발송메일목록을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/deleteSndngMailList.do")
	@Secured("ROLE_USER")
	public String deleteSndngMailList(
			@RequestParam String messageIds, 
			ModelMap model) {

		sndngMailService.deleteSndngMails(messageIds);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/ems/listSndngMail.do");
	}
	
	/**
	 * 발송메일을 상세 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/detailSndngMail.do")
	@Secured("ROLE_USER")
	public String detailSndngMail(
			@ModelAttribute SndngMailVO sndngMailVO) {

		// 1. 발송메일을 상세 조회한다.
		sndngMailService.selectSndngMail(sndngMailVO);

		// 발송메일 상세조회 화면 이동
		return WebUtil.adjustViewName("/cop/ems/MailDetail");
	}

	/**
	 * XML형태의 발송요청메일을 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/detailSndngMailXml.do")
	@Secured("ROLE_USER")
	public void detailSndngMailXml(
			@ModelAttribute SndngMailVO sndngMailVO, 
			HttpServletResponse response) 
	throws Exception {
//		String xmlFile = Globals.MAIL_REQUEST_PATH + sndngMailVO.getMssageId() + ".xml";
		String xmlFile = AramProperties.getProperty("Globals.MailRequestPath") + sndngMailVO.getMssageId() + ".xml";
		File uFile = new File(xmlFile);
		int fSize = (int) uFile.length();

		// 2011.10.10 보안점검 후속 조치
		if (fSize > 0) {
			String mimetype = "application/x-msdownload;charset=UTF-8";

			// response.setBufferSize(fSize);
			response.setContentType(mimetype);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + uFile.getName() + "\"");
			response.setContentLength(fSize);

			BufferedInputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				FileCopyUtils.copy(in, response.getOutputStream());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						LOG.debug("IGNORED: " + ignore.getMessage());
					}
				}
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} else {
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + WebUtil.clearXSSMinimum(xmlFile) + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

    /**
	 * 발송메일 등록화면으로 들어간다
	 * 
	 * @param sndngMailVO 
	 */
    @RequestMapping(value="/cop/ems/registSndngMail.do")
	@Secured("ROLE_USER")
    public String registSndngMail(
			@ModelAttribute SndngMailVO sndngMailVO) {
    	
		return WebUtil.adjustViewName("/cop/ems/MailRegist");
    }
    
    /**
	 * 발송메일을 등록한다
	 * 
	 * @param multiRequest 
	 * @param sndngMailVO 
	 */
    @RequestMapping(value="/cop/ems/insertSndngMail.do")
	@Secured("ROLE_USER")
	public String insertSndngMail(
			MultipartHttpServletRequest multiRequest,
			@ModelAttribute SndngMailVO sndngMailVO,
			ModelMap model) 
    throws Exception {
    	
    	String link = "N";
    	if (sndngMailVO != null 
    			&& sndngMailVO.getLink() != null 
    			&& !sndngMailVO.getLink().equals("")) {
    		link = sndngMailVO.getLink();
    	}
    	
		// 첨부파일 관련 첨부파일ID 생성
    	sndngMailVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "MSG_"));
    	
    	LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	sndngMailVO.setDsptchPerson(loginVO.getId());
    	
    	// 발송메일을 등록한다.
    	boolean result = sndngMailService.insertSndngMail(sndngMailVO);
    	if (!result) {
			return WebUtil.adjustViewName("/cmm/error/egovError");
    	}
    	
		if (link.equals("N")) {
			model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		    return WebUtil.redirectJsp(model, "/cop/ems/listSndngMail.do");
		} else {
			model.addAttribute("closeYn", "Y");
			return WebUtil.adjustViewName("/cop/ems/MailRegist");
		}
	}
    
	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/deleteSndngMail.do")
	@Secured("ROLE_USER")
	public String deleteSndngMail(
			@ModelAttribute SndngMailVO sndngMailVO, 
			ModelMap model) {

		sndngMailService.deleteSndngMail(sndngMailVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/ems/listSndngMail.do");
	}

}