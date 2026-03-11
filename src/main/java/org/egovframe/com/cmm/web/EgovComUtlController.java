package org.egovframe.com.cmm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 공통유틸리티성 작업을 위한 Controller 클래스
 * @author 공통 서비스 개발팀 JJY
 * @since 2009.03.02
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.02  JJY            최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Controller
public class EgovComUtlController {

	/**
	 * validation rule dynamic java script
	 */
	@RequestMapping("/validator.do")
	public String validate() {
		return "cmm/validator";
	}

}