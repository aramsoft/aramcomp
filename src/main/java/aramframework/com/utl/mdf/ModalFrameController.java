package aramframework.com.utl.mdf;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 모달프레임 생성 콘트롤러
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
public class ModalFrameController {

	protected static final Logger LOG = LoggerFactory.getLogger(ModalFrameController.class);

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
		LOG.debug("requestUrl = " + requestUrl);
		
		return "aramframework/com/utl/mdf/ModalPopupFrame";
	}

}
