package aramframework.com.cmm.config.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
 * 페이지 정보 네비게이션 표시 라이브러리
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware {

	private ServletContext servletContext;

	public ImagePaginationRenderer() { }

	public void initVariables() {
		firstPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath()
				+ "/images/aramframework/com/cmm/icon/icon_prevend.gif\" alt=\"처음\"   border=\"0\"/></a>&#160;";
		previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath()
				+ "/images/aramframework/com/cmm/icon/icon_prev.gif\"    alt=\"이전\"   border=\"0\"/></a>&#160;";
		currentPageLabel = "<strong>{0}</strong>&#160;";
		otherPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a>&#160;";
		nextPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath()
				+ "/images/aramframework/com/cmm/icon/icon_next.gif\"    alt=\"다음\"   border=\"0\"/></a>&#160;";
		lastPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath()
				+ "/images/aramframework/com/cmm/icon/icon_nextend.gif\" alt=\"마지막\" border=\"0\"/></a>&#160;";
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
