package aramframework.com.utl.sys.prm.schedule;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sys.prm.domain.ProcessMonVO;
import aramframework.com.utl.sys.prm.service.ProcessMonService;

/**
 * 개요 - 프로세스 모니터링을 위한 스케쥴링 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Component("processMonScheduling")
public class ProcessMonScheduling {

	@Autowired
	private ProcessMonService processMonService;

	@Autowired
	private SimpleMailMessage mntrngMessage;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * 프로세스 모니터링를 수행한다.
	 * 
	 * @param
	 * @return
	 */
	public int monitorProcess() throws Exception {
		int batchResult = 0;

		// 모니터링 대상 정보 읽어들이기
		List<ProcessMonVO> targetList = null;
		ProcessMonVO processMonVO = new ProcessMonVO();

		// 모니터링 대상 검색 조건 초기화
		processMonVO.getSearchVO().setPageIndex(1);
		processMonVO.getSearchVO().setFirstIndex(0);
		processMonVO.getSearchVO().setRecordPerPage(RECORD_COUNT_PER_PAGE);
		targetList = processMonService.selectProcessMonList(processMonVO);

		if (LOG.isDebugEnabled()) {
			LOG.debug("조회조건 " + processMonVO);
			LOG.debug("Result 건수 : " + targetList.size());
		}

		// 서비스체크 함수 호출.
		Iterator<ProcessMonVO> iter = targetList.iterator();
		ProcessMonVO target = null;
		String procsSttus = null;
		String processNm = "";

		boolean nrmltAt = true;

		while (iter.hasNext()) {

			nrmltAt = true;
			target = iter.next();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Data : " + target);
			}

			// 서비스 체크 수행.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
			target.setCreatDt(formatter.format(new java.util.Date()));

			processNm = target.getProcessNm();

			try {

				procsSttus = ProcessMonChecker.getProcessId(processNm);
				target.setProcsSttus(procsSttus);

			} catch (Exception e1) {
				target.setLogInfo(e1.getMessage());
				nrmltAt = false;
			}

			if (procsSttus == "02") {
				nrmltAt = false;
			}

			// email 전송.
			if (!nrmltAt) {
				batchResult = -1;
				sendEmail(target);
			}

			// DB에 결과값 저장
			target.setProcsSttus(procsSttus);
			if (procsSttus == "02") {
				target.setLogInfo("실행 중인 작업 중 지정된 조건에 일치하는 작업이 없습니다.");
			}

			target.setLastUpdusrId("SYSTEM");
			processMonService.updateProcessMonSttus(target);
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
	private void sendEmail(ProcessMonVO target) {

		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(target.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = StringUtil.replace(subject, "{모니터링종류}", "프로세스모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = StringUtil.replace(text, "{모니터링종류}", "프로세스모니터링");
		errorContents = "프로세스명 : ";
		errorContents += target.getProcessNm();
		errorContents += "\n";
		errorContents += "상태 : ";
		errorContents += target.getProcsSttus();
		errorContents += "\n";
		errorContents += "모티터링 시각 : ";
		errorContents += DateUtil.convertDate(target.getCreatDt(), "", "", "");
		errorContents += "\n";
		if (target.getLogInfo() != null && !target.getLogInfo().equals("")) {
			errorContents += target.getProcessNm() + " 의 프로세스 상태가 비정상입니다.  \n로그를 확인해주세요.";
		}
		text = StringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

//		this.mntrngMailSender.send(msg);
	}

}