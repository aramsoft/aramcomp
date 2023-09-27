package org.egovframe.rte.ptl.mvc.tags.ui;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

public class PaginationTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private PaginationInfo paginationInfo;
	private String type;
	private String jsFunction;

	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(
					(HttpServletRequest)this.pageContext.getRequest(), this.pageContext.getServletContext());
			Object paginationManager;
			if (ctx.containsBean("paginationManager")) {
				paginationManager = (PaginationManager) ctx.getBean("paginationManager");
			} else {
				paginationManager = new DefaultPaginationManager();
			}

			PaginationRenderer paginationRenderer = ((PaginationManager) paginationManager).getRendererType(this.type);
			String contents = paginationRenderer.renderPagination(this.paginationInfo, this.jsFunction);
			out.println(contents);
			return 6;
		} catch (IOException var6) {
			throw new JspException();
		}
	}

	public void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}

	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public void setType(String type) {
		this.type = type;
	}
}