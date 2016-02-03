package aramframework.com.utl.sys.srm.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import aramframework.com.cop.sms.domain.SmsVO;
import aramframework.com.cop.sms.service.SmsInfoService;
import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sys.srm.domain.ServerResrceMntrngVO;

/**
 * 개요 - 서버자원모니터링 Service Interface를 invoke 할 수 있는 클래스를 정의한다.
 * 
 * 상세내용 - 서버자원모니터링 정보 결과를 확인할 수 있는 함수를 호출할 수 있는 기능을 제공한다.
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

@Component("serverResrceMntrngScheduling")
public class ServerResrceMntrngScheduling {

	@Autowired
	private ServerResrceMntrngService serverResrceMntrngService;

	@Autowired
	private SmsInfoService smsInfoService; 
	 
	@Autowired
	private SimpleMailMessage mntrngMessage;

	@Autowired
	private MailSender mntrngMailSender;

	Process process;
	JMXServiceURL address = null;
	JMXConnector connector = null;
	MBeanServerConnection mbs = null;
	ObjectName name = null;
	MBeanInfo mBeanInfo = null;
	MBeanAttributeInfo[] attrInfos = null;
	ServerResrceMntrngVO serverResrceMntrngVO = null;

	/**
	 * 서버자원 모니터링를 수행한다.
	 * 
	 * @param
	 * @return
	 */

	public void init(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {

		String serverEqpmnIp = serverResrceMntrngVO.getServerEqpmnIp();

		try {
			address = new JMXServiceURL("service:jmx:rmi://" + serverEqpmnIp + ":9999/jndi/rmi://" + serverEqpmnIp + ":9999/server");
			connector = JMXConnectorFactory.connect(address);

			mbs = connector.getMBeanServerConnection();

			name = new ObjectName("aramframework.com.utl.sys.srm.service:type=EgovServerResrceMntrng");

			mBeanInfo = mbs.getMBeanInfo(name);
			attrInfos = mBeanInfo.getAttributes();

			for (MBeanAttributeInfo attrInfo : attrInfos) {
				if (attrInfo.getName().equals("CpuUsage"))
					serverResrceMntrngVO.setCpuUseRt(mbs.getAttribute(name, attrInfo.getName()).toString());
				else if (attrInfo.getName().equals("MemoryUsage"))
					serverResrceMntrngVO.setMoryUseRt(mbs.getAttribute(name, attrInfo.getName()).toString());
				System.out.println(attrInfo.getName() + " = " + mbs.getAttribute(name, attrInfo.getName()));
			}
			serverResrceMntrngVO.setSvcSttus("01");
			serverResrceMntrngVO.setFrstRegisterId(InetAddress.getLocalHost().getHostAddress());
			serverResrceMntrngVO.setLastUpdusrId("SYSTEM");

			if (Double.parseDouble(serverResrceMntrngVO.getCpuUseRt()) > 90 || Double.parseDouble(serverResrceMntrngVO.getMoryUseRt()) > 90) {
				serverResrceMntrngVO.setSvcSttus("02");
				serverResrceMntrngVO.setLogInfo("적정수치를 초과하였습니다.");
				sendEmail(serverResrceMntrngVO);
				// sendSMS(egovServerResrceMntrngService.selectServerResrceMntrng(serverResrceMntrngVO));
			}
			serverResrceMntrngService.insertServerResrceMntrng(serverResrceMntrngVO);

		} catch (Exception e) {

			serverResrceMntrngVO.setSvcSttus("02");

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// e.printStackTrace(printStream);
			System.out.println(e); // 2011.10.10 보안점검 후속조치

			String logInfo = out.toString();
			byte[] btLogInfo = logInfo.getBytes();

			if (btLogInfo.length > 2000)
				logInfo = new String(btLogInfo, 0, 2000);

			serverResrceMntrngVO.setLogInfo(logInfo);
			serverResrceMntrngVO.setFrstRegisterId(InetAddress.getLocalHost().getHostAddress());
			serverResrceMntrngVO.setLastUpdusrId("SYSTEM");

			try {
				serverResrceMntrngService.insertServerResrceMntrng(serverResrceMntrngVO);
			} catch (Exception ex) {
				new Exception(ex);
			}

		} finally {
			if (connector != null)
				try {
					connector.close();
				} catch (IOException ie) {
					System.out.println(ie); // 2011.10.10 보안점검 후속조치
				}
		}
	}

	/**
	 * 서버자원 모니터링를 수행한다.
	 * 
	 * @param
	 * @return
	 */
	public void monitorServerResrce() {

		try {
			List<ServerResrceMntrngVO> result = serverResrceMntrngService.selectMntrngServerList(serverResrceMntrngVO);
			Iterator<ServerResrceMntrngVO> iter = result.iterator();

			while (iter.hasNext()) {
				ServerResrceMntrngVO serverResrceMntrngVO = (ServerResrceMntrngVO) iter.next();
				init(serverResrceMntrngVO);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치
		}
	}

	/**
	 * 이메일을 전송한다.
	 * 
	 * @param serverResrceMntrngVO
	 *            - 서버자원모니터링 Vo
	 * @return
	 * 
	 * @param serverResrceMntrngVO
	 */
	public void sendEmail(ServerResrceMntrngVO serverResrceMntrngVO) {
		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(serverResrceMntrngVO.getMngrEamilAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = StringUtil.replace(subject, "{모니터링종류}", "서버자원서비스모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = StringUtil.replace(text, "{모니터링종류}", "서버자원서비스모니터링");
		errorContents = "서버명 : ";
		errorContents += serverResrceMntrngVO.getServerNm();
		errorContents += "\n";
		errorContents += "서버IP : ";
		errorContents += serverResrceMntrngVO.getServerEqpmnIp();
		errorContents += "\n";
		errorContents += "CPU사용률 : ";
		errorContents += serverResrceMntrngVO.getCpuUseRt();
		errorContents += "\n";
		errorContents += "메모리사용률 : ";
		errorContents += serverResrceMntrngVO.getMoryUseRt();
		errorContents += "\n";
		errorContents += "서비스상태 : 비정상";
		// errorContents += serverResrceMntrngVO.getSvcSttusNm();
		errorContents += "\n";
		errorContents += "내용 : ";
		errorContents += serverResrceMntrngVO.getLogInfo();
		errorContents += "\n";
		errorContents += "생성일시 : ";
		errorContents += DateUtil.convertDate(serverResrceMntrngVO.getCreatDt(), "", "", "");
		errorContents += "\n";
		errorContents += serverResrceMntrngVO.getServerNm() + " 의 서버자원 서비스 상태가 비정상입니다. \n로그를 확인해주세요.";
		text = StringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

		this.mntrngMailSender.send(msg);
	}

	public void sendSMS(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		String[] receiveTelno = { "010-6802-0886" };
		SmsVO smsVO = new SmsVO();
		smsVO.setTrnsmitTelno("000-000-0000"); // 발신자
		smsVO.setRecptnTelno(receiveTelno); // 수신자
		smsVO.setTrnsmitCn("테스트 입니다");

		smsInfoService.insertSmsInf(smsVO);
	}

}