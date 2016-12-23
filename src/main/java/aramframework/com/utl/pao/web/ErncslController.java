package aramframework.com.utl.pao.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.utl.pao.domain.PrntngOutptVO;
import aramframework.com.utl.pao.service.PrntngOutptService;

/**
 * 전자관인에 관한 Util 테스트를 위한 화면 Controller
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ErncslController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PrntngOutptService prntngOutptService; 

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	/**
	 * 서블릿 초기화
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 관인이미지를 조회하여 출력
	 * 
	 * @param
	 * @param
	 * @return
	 * @exception MyException
	 * @see
	 */
	@RequestMapping(value = "/utl/pao/EgovErncsl.do")
	public void doGet(
			@RequestParam("sOrgCode") String orgCode, 
			@RequestParam("sErncslSe") String erncslSe, 
			HttpServletRequest request,
			HttpServletResponse response) 
	throws ServletException, IOException {

		LOG.info("EgovErncsl start....");

		PrntngOutptVO prntngOutptVO = new PrntngOutptVO();

		prntngOutptVO.setOrgCode(orgCode);
		prntngOutptVO.setErncslSe(erncslSe);

		try {
			prntngOutptService.selectErncsl(prntngOutptVO);
		} catch (Exception e) {
			// e.printStackTrace();
			LOG.error(e.getMessage()); // 2011.10.10 보안점검 후속조치
		}

		// 2011.10.10 보안점검 후속조치
		if (prntngOutptVO.getImgInfo() == null) {
			throw new RuntimeException("image not found!!!");
		}
		// 2011.10.10 보안점검 후속조치 끝

		byte[] img = prntngOutptVO.getImgInfo();
		String imgtype = prntngOutptVO.getImgType();
		String type = "";

		if (imgtype != null && !"".equals(imgtype)) {
			type = "image/" + imgtype;
		} else {
			LOG.debug("Image fileType is null.");
		}
		if (img == null) {
			LOG.debug("Image fileInfo is null.");
			return;
		}

		response.setHeader("Content-Type", type.replaceAll("\r", "").replaceAll("\n", ""));
		response.setHeader("Content-Length", "" + img.length);
		response.getOutputStream().write(img);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		LOG.info("EgovErncsl end....");
	}
}