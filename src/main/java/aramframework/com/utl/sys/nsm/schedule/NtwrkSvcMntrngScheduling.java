package aramframework.com.utl.sys.nsm.schedule;

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
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngVO;
import aramframework.com.utl.sys.nsm.service.NtwrkSvcMntrngChecker;
import aramframework.com.utl.sys.nsm.service.NtwrkSvcMntrngResult;
import aramframework.com.utl.sys.nsm.service.NtwrkSvcMntrngService;

/**
 * 개요 - 네트워크서비스 모니터링을 위한 스케쥴링클래스를 정의한다.
 * 
 * 상세내용 - 네트워크서비스 모니터링 기능을 제공한다. 
 *         - 네트워크서비스 모니터링 결과를 관리자에게 이메일로 전송한다.
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

@Component("ntwrkSvcMntrngScheduling")
public class NtwrkSvcMntrngScheduling {

	@Autowired
	private NtwrkSvcMntrngService ntwrkSvcMntrngService;

	@Autowired
	private MailSender mntrngMailSender;

	@Autowired
	private SimpleMailMessage mntrngMessage;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * 네트워크 서비스 모니터링를 수행한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int monitorNtwrkSvc() throws Exception {
		int batchResult = 0;

		// 모니터링 대상 정보 읽어들이기
		List<NtwrkSvcMntrngVO> targetList = null;
		NtwrkSvcMntrngVO ntwrkSvcMntrngVO = new NtwrkSvcMntrngVO();

		// 모니터링 대상 검색 조건 초기화
		ntwrkSvcMntrngVO.setPageIndex(1);
		ntwrkSvcMntrngVO.setFirstIndex(0);
		ntwrkSvcMntrngVO.setRecordPerPage(RECORD_COUNT_PER_PAGE);
		targetList = ntwrkSvcMntrngService.selectNtwrkSvcMntrngList(ntwrkSvcMntrngVO);
		if (LOG.isDebugEnabled()) {
			LOG.debug("조회조건 " + ntwrkSvcMntrngVO);
			LOG.debug("Result 건수 : " + targetList.size());
		}
		// 서비스체크 함수 호출.
		Iterator<NtwrkSvcMntrngVO> iter = targetList.iterator();
		NtwrkSvcMntrngVO target = null;
		NtwrkSvcMntrngResult result = null;
		while (iter.hasNext()) {
			target = iter.next();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Data : " + target);
			}
			// 서비스 체크 수행.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
			target.setCreatDt(formatter.format(new java.util.Date()));
			result = NtwrkSvcMntrngChecker.check(target.getSysIp(), Integer.valueOf(target.getSysPort()));

			// email 전송.
			if (!result.isNrmltAt()) {
				batchResult = -1;
				sendEmail(target);
			}

			// DB에 결과값 저장
			if (result.isNrmltAt()) {
				target.setMntrngSttus("01");
			} else {
				target.setMntrngSttus("02");
			}

			// DB에 로그정보 저장
			if (result.getCause() != null) {
				target.setLogInfo(result.getCause().getMessage());
			} else {
				target.setLogInfo("");
			}
			target.setLastUpdusrId("SYSTEM");
			ntwrkSvcMntrngService.updateNtwrkSvcMntrngSttus(target);
		}
		return batchResult;
	}

	/**
	 * 이메일을 전송한다.
	 * 
	 * @param target
	 *            모니터링 대상정보
	 * @return
	 * 
	 */
	private void sendEmail(NtwrkSvcMntrngVO target) {
		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(target.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = StringUtil.replace(subject, "{모니터링종류}", "네트워크서비스모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = StringUtil.replace(text, "{모니터링종류}", "네트워크서비스모니터링");
		errorContents = "서버명 : ";
		errorContents += target.getSysNm();
		errorContents += "\n";
		errorContents += "서버IP : ";
		errorContents += target.getSysIp();
		errorContents += "\n";
		errorContents += "서버포트 : ";
		errorContents += target.getSysPort();
		errorContents += "\n";
		errorContents += "상태 : ";
		errorContents += target.getMntrngSttus();
		errorContents += "\n";
		errorContents += "모니터링 시각 : ";
		errorContents += DateUtil.convertDate(target.getCreatDt(), "", "", "");
		errorContents += "\n";
		errorContents += target.getSysNm() + " 의 네트워크 서비스 상태가 비정상입니다. \n로그를 확인해주세요.";
		text = StringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

//		this.mntrngMailSender.send(msg);
	}

}
