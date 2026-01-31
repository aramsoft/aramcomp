package aramframework.com.utl.mdf.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 모달프레임 생성 콘트롤러
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ModalFrameController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 팝업 페이지를 호출한다.
	 * 
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/utl/mdf/openPopup.do")
	public String openPopupWindow(
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {

		String width = request.getParameter("width");
		String height = request.getParameter("height");

		model.addAttribute("width", width);
		model.addAttribute("height", height);

		String requestUrl = request.getParameter("requestUrl");
		String arguments = request.getParameter("arguments");

		requestUrl += "?PopFlag=Y";
		if (arguments != null && arguments != "") {
			String[] parameters = arguments.split(";");
			
			for (int i=0; i < parameters.length; i++ ) {
				String[] values = parameters[i].split("\\^");
				requestUrl += "&" + values[0] + "=" + values[1];
			}
		}
		
		model.addAttribute("requestUrl", requestUrl);
		logger.debug("requestUrl = " + requestUrl);
		
		return "com/utl/mdf/ModalPopupFrame";
	}

}
