package aramframework.com.utl.pao.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 전자관인 출력 화면 Controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class PrntngOutptController {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	/**
	 * 전자관인 출력 화면 컨트롤
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/utl/pao/EgovPrntngOutpt.do")
	public ModelAndView handleRequest(
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException {

		LOG.info("EgovPrntngOutptController start....");
		Map<String, Object> cmdModel = new HashMap<String, Object>();

		// 이동할 JSP
		String jspStr = "";
		// 실행명령어
		String cmdStr = request.getParameter("cmdStr");
		if (cmdStr == null || cmdStr.equals("")) {
			cmdStr = "";
		}

		// 실행명령어에 따른 JSP 할당
		if (cmdStr.equals("ComUtlPaoErncslOutpt")) { // test 샘플용 경로
			jspStr = "aramframework/com/utl/pao/EgovErncslOutpt";

			try {
				cmdModel.put("resultStr", "UTILITY 직접 호출");
			} catch (Exception e) {
				jspStr = "/egovDevIndex";
				// e.printStackTrace();
				LOG.error(e.getMessage()); // 2011.10.10 보안점검 후속조치
			}
		} else {
			jspStr = "/egovDevIndex";
		}
		LOG.info("EgovPrntngOutptController end....");
		return new ModelAndView(jspStr, "cmdModel", cmdModel);
	}
}