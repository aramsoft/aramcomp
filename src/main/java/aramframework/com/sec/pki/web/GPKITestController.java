package aramframework.com.sec.pki.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.sec.pki.service.GPKIService;

/**
 * GPKI(Goverment Public Key Infrastructure) 테스트를 위한 서비스 컨트롤러 클래스
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
public class GPKITestController {

	/** EgovGPKIService */
	@Resource(name = "GPKIService")
	private GPKIService gpkiService;

	// @IncludedInfo(name="암호화/복호화", order = 110)
	@RequestMapping("/sec/pki/gpkiTestInit.do")
	public String initRequest(HttpServletRequest request, ModelMap model) throws Exception {

		String cmd = "init";
		String target = gpkiService.getServerId();

		model.addAttribute("cmd", cmd);
		model.addAttribute("target", target);

		return "aramframework/com/sec/pki/GpkiTest";
	}

	@RequestMapping("/sec/pki/gpkiTest.do")
	public String handleRequest(
			@RequestParam("cmd") String cmd, 
			@RequestParam("target") String target, 
			@RequestParam("source") String source,
			@RequestParam("result") String result, 
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {
		String cmdInfo = cmd;
		String targetInfo = target;

		if (cmdInfo == null || cmdInfo.equals("")) {
			cmdInfo = "init";
		}

		if (targetInfo == null || targetInfo.equals("")) {
			targetInfo = gpkiService.getServerId();
		}

		model.addAttribute("cmd", cmdInfo);
		model.addAttribute("target", targetInfo);

		if ("encrypt".equals(cmdInfo)) {
			byte[] data = gpkiService.encrypt(source.getBytes(), targetInfo);

			model.addAttribute("source", "");
			model.addAttribute("result", gpkiService.getBASE64String(data));
		} else if ("decrypt".equals(cmdInfo)) {
			byte[] data = gpkiService.getDataFromBASE64(result);

			model.addAttribute("source", new String(gpkiService.decrypt(data)));
			model.addAttribute("result", "");
		} else if ("sign".equals(cmdInfo)) {
			byte[] data = gpkiService.sign(source.getBytes());

			model.addAttribute("source", "");
			model.addAttribute("result", gpkiService.getBASE64String(data));
		} else if ("verify".equals(cmdInfo)) {
			byte[] data = gpkiService.getDataFromBASE64(result);

			model.addAttribute("source", new String(gpkiService.verifySign(data)));
			model.addAttribute("result", "");
		}

		return "aramframework/com/sec/pki/GpkiTest";
	}
}
