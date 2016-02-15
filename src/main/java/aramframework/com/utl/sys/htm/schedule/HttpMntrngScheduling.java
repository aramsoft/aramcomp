package aramframework.com.utl.sys.htm.schedule;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sys.htm.domain.HttpMntrngVO;
import aramframework.com.utl.sys.htm.service.HttpMntrngChecker;
import aramframework.com.utl.sys.htm.service.HttpMntrngService;

/**
 * 개요 - HTTP서비스모니터링을 위한 스케쥴링 클래스
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

@Component("httpMntrngScheduling")
public class HttpMntrngScheduling {

	@Autowired
	private HttpMntrngService httpMntrngService;

	@Autowired
	private MailSender mntrngMailSender;

	@Autowired
	private SimpleMailMessage mntrngMessage;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * HTTP서비스 모니터링를 수행한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int monitorHttp() throws Exception {
		int batchResult = 0;

		// 모니터링 대상 정보 읽어들이기
		List<HttpMntrngVO> targetList = null;
		HttpMntrngVO httpMonVO = new HttpMntrngVO();

		// 모니터링 대상 검색 조건 초기화
		httpMonVO.getSearchVO().setPageIndex(1);
		httpMonVO.getSearchVO().setFirstIndex(0);
		httpMonVO.getSearchVO().setRecordPerPage(RECORD_COUNT_PER_PAGE);
		targetList = httpMntrngService.selectHttpMntrngList(httpMonVO);

		if (LOG.isDebugEnabled()) {
			LOG.debug("조회조건 " + httpMonVO);
			LOG.debug("Result 건수 : " + targetList.size());
		}

		// 서비스체크 함수 호출.
		Iterator<HttpMntrngVO> iter = targetList.iterator();
		HttpMntrngVO target = null;

//		String webKind = null;
		String httpSttusCd = null;
//		String sysId = null;
		String SiteUrl = null;

		boolean nrmltAt = true;

		while (iter.hasNext()) {

			nrmltAt = true;
			target = (HttpMntrngVO) iter.next();
			SiteUrl = target.getSiteUrl();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Data : " + target);
			}

			// 서비스 체크 수행.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
			target.setCreatDt(formatter.format(new java.util.Date()));

//			sysId = target.getSysId();
//			webKind = target.getWebKind();

			try {
				httpSttusCd = HttpMntrngChecker.getPrductStatus(SiteUrl);
				target.setHttpSttusCd(httpSttusCd);
			} catch (Exception e1) {
				target.setLogInfo(e1.getMessage());
				nrmltAt = false;
			}

			if (httpSttusCd == "02") {
				nrmltAt = false;
			}

			// email 전송.
			if (!nrmltAt) {
				batchResult = -1;
				sendEmail(target);
			}

			// DB에 결과값 저장
			target.setHttpSttusCd(httpSttusCd);
			if (httpSttusCd == "02") {
				target.setLogInfo("Connection timed out: connect");
			}

			target.setLastUpdusrId("SYSTEM");
			httpMntrngService.updateHttpMntrngSttus(target);
		}
		return batchResult;
	}

	/**
	 * 이메일을 전송한다.
	 * 
	 * @return
	 * 
	 * @param target
	 */
	private void sendEmail(HttpMntrngVO target) {

		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(target.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = StringUtil.replace(subject, "{모니터링종류}", "HTTP서비스 모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = StringUtil.replace(text, "{모니터링종류}", "HTTP서비스 모니터링");
		errorContents = "웹서비스종류 : ";
		errorContents += target.getWebKind();
		errorContents += "\n";
		errorContents += "시스템URL : ";
		errorContents += target.getSiteUrl();
		errorContents += "\n";
		errorContents += "상태 : ";
		errorContents += target.getHttpSttusCd();
		errorContents += "\n";
		errorContents += "모티터링 시각 : ";
		errorContents += DateUtil.convertDate(target.getCreatDt(), "", "", "");
		errorContents += "\n";
		if (target.getLogInfo() != null && !target.getLogInfo().equals("")) {
			errorContents += target.getWebKind() + " 의 프로세스 상태가 비정상입니다.  \n로그를 확인해주세요.";
		}
		text = StringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

		this.mntrngMailSender.send(msg);
	}

}