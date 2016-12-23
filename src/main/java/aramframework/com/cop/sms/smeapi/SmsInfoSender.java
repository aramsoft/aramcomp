package aramframework.com.cop.sms.smeapi;

import x3.client.smeapi.SMEConnection;
import x3.client.smeapi.SMEConnectionFactory;
import x3.client.smeapi.SMEException;
import x3.client.smeapi.SMERequest;
import x3.client.smeapi.SMEResponse;
import x3.client.smeapi.SMESender;
import x3.client.smeapi.SMESession;
import x3.client.smeapi.impl.SMEConfig;
import x3.client.smeapi.impl.SMEConnectionFactoryImpl;
import x3.client.smeapi.impl.SMELogger;
import aramframework.com.cop.sms.domain.SmsMessageVO;

/**
 * 문자메시지 연동 처리를 위한 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class SmsInfoSender {

	/** SMS 서버 URL */
	private final String connString; // ex) sme://000.000.000.000:20000
	/** SMS 연계 ID */
	private final String smsId;
	/** SMS 연계 password */
	private final String smsPwd;

	/** SMS G/W Connection Factory */
	private SMEConnectionFactory factSender = null;
	/** SMS G/W Connection */
	private SMEConnection connSender = null;
	/** SMS G/W Session */
	private SMESession sessSender = null;
	/** SMS G/W Sender */
	private SMESender sender = null;

	/**
	 * SMS 연계를 위한 생성자. SMEConfig 설정파일로부터 필요한 연결 정보 및 로그 관련 정보를 얻는다.
	 * 
	 * @param configFile
	 * @throws Exception
	 */
	public SmsInfoSender(String configFile) throws Exception {
		SMEConfig.configSet(configFile);

		connString = SMEConfig.getSmsUrl();
		smsId = SMEConfig.getSmsId();
		smsPwd = SMEConfig.getSmsPwd();

		String tmp = null;

		tmp = SMEConfig.getLogLevel();
		if (tmp != null && !tmp.equals("")) {
			SMELogger.setLogLevel(tmp);
			// System.out.println(tmp);
		}

		tmp = SMEConfig.getLogLayout();
		if (tmp != null && !tmp.equals("")) {
			SMELogger.setLogLayout(tmp);
			// System.out.println(tmp);
		}

		tmp = SMEConfig.getLogPath();
		if (tmp != null && !tmp.equals("")) {
			SMELogger.setLogPath(tmp);
			// System.out.println(tmp);
		}

		SMELogger.setUseConsoleLogger(SMEConfig.getUseConsoleLogger());
		SMELogger.setUseFileLogger(SMEConfig.getUseFileLogger());
	}

	/**
	 * SMS 연결을 위한 Connection 및 Session 생성한다. 발송건이 있을 경우만 open()을 호출하고 close()를
	 * 호출하여 종료한다. 만약 DB 와 연동시 select로 데이타 검출시 데이타가 없으면 open()을 호출하지 않는다. (중요!!!
	 * 꼭 데이타가 있을 경우만 open() 을 하여 접속)
	 * 
	 * @throws SMEException
	 */
	public void open() throws SMEException {
		this.factSender = new SMEConnectionFactoryImpl(connString);
		this.connSender = factSender.createConnection(smsId, smsPwd); // 아이디와  패스워드입니다.
		this.sessSender = connSender.createSession();
		this.sender = sessSender.createSender();

		// 현재 발송한 호에 대해서 리포트 수신을 위해서는 true 로 설정해야 리포트 수신을 할 수 있다.
		// 만약 false 로 세팅하고 발송을 하면 현재 발송한 호에 대해서는 결과수신을 할 수 없다.
		// 리포트가 필요없는 기관에서는 아래 디폴트값인 false를 유지한다.
		// false로 설정을 하면 보내는 메시지에 대해서 결과를 수신할 수 없습니다.
		// [2008-08-25] 리포트 수신 필수조건으로 변경
		// 리포트는 필수 수신입니다.
		this.sessSender.setReceiverCreated(true);
		this.connSender.start();
	}

	/**
	 * SMS를 전송한다.
	 * 
	 * @param smsConn
	 * @return
	 */
	public SmsMessageVO send(SmsMessageVO smsMsg) throws SMEException {
		SMERequest request = null;

		try {
			request = sessSender.createRequest();
			// destination
			request.setTo(smsMsg.getCallTo()); // 수신번호
			// origination
			request.setFrom(smsMsg.getCallFrom()); // 발신번호
			// callback
			request.setCallback(smsMsg.getCallBack()); // 회신번호(콜백번호)

			// callbackurl
			// 무선인터넷 주소 휴대전화 인터넷 (WAP) 페이지 접속용 URL
			// 단문자메세지 외의 별도 과금이 되므로 WAP 페이지가 있는 기관에서만 사용
			// 해당 URL 접속시 수신자에게 과금이 되므로 주의.
			request.setCallbackURL(smsMsg.getCallBackUrl()); // CallbackURL은 선택사항 입니다.

			// message (메세지내용)
			request.setText(smsMsg.getText());

			// serial *MUST* be unique number in single SME.
			// 반드시 메시지 발송시 연속되는 일련번호 형식을 띈 고유값이어야 함
			// SMS G/W로 전송누적 일련번호
			// 예) 'TestMessage-000000' 숫자 또는 문자 + 숫자로 조합 가능 ( 40 byte )
			// 예) '200808251259590001'
			request.setMessageId(smsMsg.getMessageId()); // 일련번호 고유값

			sender = sessSender.createSender();
			SMEResponse res = sender.send(request);
			int nRes = res.getResult();

			smsMsg.setResult(nRes);
			// smsConn.setResultMessage("");

			switch (nRes) {
			case 0:
				smsMsg.setResultMessage("");
				break;
			case 3000:
				smsMsg.setResultMessage("착발신 번호 포맷 오류 또는 부재");
				break;
			case 3001:
				smsMsg.setResultMessage("콜백번호 포맷 오류");
				break;
			case 3002:
				smsMsg.setResultMessage("MessageID 포맷 오류 또는 부재");
				break;
			case 3003:
				smsMsg.setResultMessage("Text 및 Callback URL 포맷 오류");
				break;
			case 4005:
				smsMsg.setResultMessage("SMG Server 스팸 메시지로 처리 거부됨");
				break;
			case 5000:
				smsMsg.setResultMessage("SMG Server 내부 에러");
				break;
			default:
				smsMsg.setResultMessage("알 수 없는 오류 발생");
			}

		} catch (SMEException ex) {
			throw ex;
		}

		return smsMsg;
	}

	/**
	 * SMS 연결을 위한 Connection 및 Session 해제한다. 메시지 처리 전송후 반드시 종료해야 한다.
	 */
	public void close() {
		try {
			if (sender != null)
				sender.close();
		} catch (SMEException ignore) {
			ignore.printStackTrace();
		}

		try {
			if (sessSender != null)
				sessSender.close();
		} catch (SMEException ignore) {
			ignore.printStackTrace();
		}

		try {
			if (connSender != null)
				connSender.close();
		} catch (SMEException ignore) {
			ignore.printStackTrace();
		}
	}
}
