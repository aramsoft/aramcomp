package aramframework.com.utl.sys.fsm.service;

import java.io.IOException;
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
import aramframework.com.utl.sys.fsm.domain.FileSysMntrngVO;

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

@Component("fileSystemMntrngScheduling")
public class FileSystemMntrngScheduling {

	@Autowired
	private FileSysMntrngService fileSysMntrngService;

	@Autowired
	private MailSender mntrngMailSender;

	@Autowired
	private SimpleMailMessage mntrngMessage;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * DB서비스 모니터링를 수행한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int monitorFileSys() throws Exception {
		int batchResult = 0;
		
		// 모니터링 대상 정보 읽어들이기
		List<FileSysMntrngVO> targetList = null;
		FileSysMntrngVO fileSysMntrngVO = new FileSysMntrngVO();
		// 모니터링 대상 검색 조건 초기화
		fileSysMntrngVO.setPageIndex(1);
		fileSysMntrngVO.setFirstIndex(0);
		fileSysMntrngVO.setRecordPerPage(RECORD_COUNT_PER_PAGE);

		targetList = fileSysMntrngService.selectFileSysMntrngList(fileSysMntrngVO);
		LOG.debug("조회조건 " + fileSysMntrngVO);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Result 건수 : " + targetList.size());
		}
		// 서비스체크 함수 호출.
		Iterator<FileSysMntrngVO> iter = targetList.iterator();
		FileSysMntrngVO target = null;

		FileSysMntrngVO fileSysMntrng = new FileSysMntrngVO();
		String fileSysNm = "";
		int fileSysMg = 0;
		int fileSysThrhld = 0;
		int fileSysUsgQty = 0;
		boolean nrmltAt = true;
		while (iter.hasNext()) {
			nrmltAt = true;
			target = (FileSysMntrngVO) iter.next();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Data : " + target);
			}
			fileSysMntrng = new FileSysMntrngVO();
			fileSysMntrng.setFileSysId(target.getFileSysId());
			fileSysMntrng.setFileSysNm(target.getFileSysNm());
			fileSysMntrng.setFileSysManageNm(target.getFileSysManageNm());
			fileSysMntrng.setFileSysThrhld(target.getFileSysThrhld());
			fileSysMntrng.setMntrngSttus(target.getMntrngSttus());
			fileSysMntrng.setMngrNm(target.getMngrNm());
			fileSysMntrng.setMngrEmailAddr(target.getMngrEmailAddr());

			// 서비스 체크 수행.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
			fileSysMntrng.setCreatDt(formatter.format(new java.util.Date()));

			fileSysNm = fileSysMntrng.getFileSysNm();
			fileSysThrhld = fileSysMntrng.getFileSysThrhld();
			try {
				fileSysMg = (int) FileSystemChecker.totalSpaceGb(fileSysNm);
				fileSysUsgQty = (int) (fileSysMg - FileSystemChecker.freeSpaceGb(fileSysNm));

				fileSysMntrng.setFileSysMg(fileSysMg);
				fileSysMntrng.setFileSysUsgQty(fileSysUsgQty);
			} catch (IOException e1) {
				fileSysMntrng.setLogInfo(e1.getMessage());
				nrmltAt = false;
			} catch (Exception e2) {
				fileSysMntrng.setLogInfo(e2.getMessage());
				nrmltAt = false;
			}

			if (fileSysUsgQty > fileSysThrhld) {
				nrmltAt = false;
			}

			// DB에 결과값 저장
			if (nrmltAt) {
				fileSysMntrng.setMntrngSttus("01");
			} else {
				fileSysMntrng.setMntrngSttus("02");
			}

			fileSysMntrng.setLastUpdusrId("SYSTEM");
			fileSysMntrngService.updateFileSysMntrngSttus(fileSysMntrng);

			// email 전송.
			if (!nrmltAt) {
				batchResult = -1;
				sendEmail(fileSysMntrng);
			}

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
	private void sendEmail(FileSysMntrngVO fileSysMntrng) {
		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(fileSysMntrng.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = StringUtil.replace(subject, "{모니터링종류}", "파일시스템모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = StringUtil.replace(text, "{모니터링종류}", "파일시스템모니터링");
		errorContents = "파일시스템명 : ";
		errorContents += fileSysMntrng.getFileSysNm();
		errorContents += "\n";
		errorContents += "파일시스템관리명 : ";
		errorContents += fileSysMntrng.getFileSysManageNm();
		errorContents += "\n";

		if (fileSysMntrng.getLogInfo() != null && !fileSysMntrng.getLogInfo().equals("")) {
			errorContents += "해당파일의 파일시스템 정보를 가져오는중 에러가 발생하였습니다.";
		} else {
			errorContents += "크기 : ";
			errorContents += fileSysMntrng.getFileSysMg();
			errorContents += "GB\n";
			errorContents += "임계치 : ";
			errorContents += fileSysMntrng.getFileSysThrhld();
			errorContents += "GB\n";
			errorContents += "사용량 : ";
			errorContents += fileSysMntrng.getFileSysUsgQty();
			errorContents += "GB\n";
		}

		errorContents += "상태 : ";
		errorContents += fileSysMntrng.getMntrngSttus();
		errorContents += "\n";
		errorContents += "모니터링 시각 : ";
		errorContents += DateUtil.convertDate(fileSysMntrng.getCreatDt(), "", "", "");
		errorContents += "\n";
		if (fileSysMntrng.getLogInfo() != null && !fileSysMntrng.getLogInfo().equals("")) {
			errorContents += fileSysMntrng.getFileSysManageNm() + " 의 파일시스템 상태가 비정상입니다.  \n로그를 확인해주세요.";
		} else {
			errorContents += fileSysMntrng.getFileSysManageNm() + " 의 파일시스템이 임계치를 넘었습니다.";
		}
		text = StringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

//		this.mntrngMailSender.send(msg);
	}

}
