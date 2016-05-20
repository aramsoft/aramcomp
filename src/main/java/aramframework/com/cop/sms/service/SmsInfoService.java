package aramframework.com.cop.sms.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cop.sms.dao.SmsMapper;
import aramframework.com.cop.sms.domain.SmsMessageVO;
import aramframework.com.cop.sms.domain.SmsRecptnVO;
import aramframework.com.cop.sms.domain.SmsVO;
import aramframework.com.cop.sms.smeapi.SmsInfoSender;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 문자메시지를 위한 서비스 구현 클래스
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

@Service
public class SmsInfoService extends EgovAbstractServiceImpl {

	@Autowired 
	private SmsMapper smsMapper;	

	@Autowired 
	private EgovIdGnrService smsIdGnrService; 

	private String smeConfigPath = null;

	@PostConstruct
	public void init() {
		// --------------------------------
		// 속성 정보 얻기
		// --------------------------------
		smeConfigPath = AramProperties.getPathProperty("Globals.SMEConfigPath");
	}

	private String getPhoneNumber(String number) {
		String result = number;

		if (number == null || number.trim().equals("")) {
			return "";
		}

		result = result.replace("-", "");
		result = result.replace("(", "");
		result = result.replace(")", "");
		result = result.replace(" ", "");

		return result;
	}

	private String formatPhoneNumber(String number) throws ParseException {
		if (number == null || number.trim().equals("")) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();

		if (number.length() == 9) { // 02-500-1234 형식
			buffer.append(number.substring(0, 2));
			buffer.append("-");
			buffer.append(number.substring(2, 2 + 3));
			buffer.append("-");
			buffer.append(number.substring(2 + 3, 2 + 3 + 4));

		} else if (number.length() == 10) {
			if (number.startsWith("02")) { // 02-5000-1234 형식
				buffer.append(number.substring(0, 2));
				buffer.append("-");
				buffer.append(number.substring(2, 2 + 4));
				buffer.append("-");
				buffer.append(number.substring(2 + 4, 2 + 4 + 4));

			} else { // 031-500-1234 형식
				buffer.append(number.substring(0, 3));
				buffer.append("-");
				buffer.append(number.substring(3, 3 + 3));
				buffer.append("-");
				buffer.append(number.substring(3 + 3, 3 + 3 + 4));
			}

		} else if (number.length() == 11) { // 031-5000-1234 형식
			buffer.append(number.substring(0, 3));
			buffer.append("-");
			buffer.append(number.substring(3, 3 + 4));
			buffer.append("-");
			buffer.append(number.substring(3 + 4, 3 + 4 + 4));

		} else if (number.length() == 12) { // 0505-5000-1234 형식
			buffer.append(number.substring(0, 4));
			buffer.append("-");
			buffer.append(number.substring(4, 4 + 4));
			buffer.append("-");
			buffer.append(number.substring(4 + 4, 4 + 4 + 4));

		} else {
			return number;
		}

		return buffer.toString();
	}

	/**
	 * 문자메시지 목록을 조회 한다.
	 * 
	 * @param smsVO
	 */
	public List<EgovMap> selectSmsInfs(SmsVO smsVO) throws Exception {

		List<EgovMap> result = smsMapper.selectSmsInfs(smsVO);

		// 전화번호 포맷 처리
		for (int i = 0; i < result.size(); i++) {
			String phone = result.get(i).get("trnsmitTelno").toString();
			result.get(i).put("trnsmitTelno", (formatPhoneNumber(phone)));
		}

		return result;
	}

	/**
	 * 문자메시지 총갯수을 조회 한다.
	 * 
	 * @param smsVO
	 */
	public int selectSmsInfsCnt(SmsVO smsVO) throws Exception {
		return smsMapper.selectSmsInfsCnt(smsVO);
	}

	/**
	 * 문자메시지를 전송(등록)한다.
	 * 
	 * @param smsVO
	 */
	public void insertSmsInf(SmsVO smsVO) throws Exception {

		HashMap<String, SmsRecptnVO> check = new HashMap<String, SmsRecptnVO>();

		String smsId = smsIdGnrService.getNextStringId();

		smsVO.setSmsId(smsId);
		smsVO.setTrnsmitTelno(getPhoneNumber(smsVO.getTrnsmitTelno()));

		// ---------------------------------------
		// 마스터 정보 등록
		// ---------------------------------------
		smsMapper.insertSmsInf(smsVO);

		// ---------------------------------------
		// 전송 요청 및 상세(수신자)정보 등록
		// ---------------------------------------
		SmsRecptnVO smsRecptn = null;
		for (int i = 0; i < smsVO.getRecptnTelno().length; i++) {
			if (getPhoneNumber(smsVO.getRecptnTelno()[i]).equals("")) {
				continue;
			}
			smsRecptn = new SmsRecptnVO();

			smsRecptn.setSmsId(smsId);
			smsRecptn.setRecptnTelno(getPhoneNumber(smsVO.getRecptnTelno()[i]));

			// 동일 전화번호면 SKIP
			if (check.containsKey(smsRecptn.getRecptnTelno())) {
				continue;
			} else {
				check.put(smsRecptn.getRecptnTelno(), smsRecptn);
			}

			// ---------------------------------------
			// 실 전송 요청 저장
			// ---------------------------------------
			SmsMessageVO smsMsg = new SmsMessageVO();

			smsMsg.setCallFrom(smsVO.getTrnsmitTelno());
			smsMsg.setCallTo(smsRecptn.getRecptnTelno());
			smsMsg.setCallBack(smsRecptn.getRecptnTelno());
			smsMsg.setCallBackUrl("");
			smsMsg.setText(smsVO.getTrnsmitCn());

			smsMsg.setMessageId(smsId + "-" + smsRecptn.getRecptnTelno());

			// SMS 전송 요청
			SmsInfoSender sender = null;
			SmsMessageVO result = null;
			try {
				sender = new SmsInfoSender(smeConfigPath);
				sender.open();
				result = sender.send(smsMsg);
			} finally {
				if (sender != null) {
					sender.close();
				}
			}
			// //-------------------------------------

			// Sender의 전송 결과는 SMS G/W 처리 상의 결과만 리턴함
			// 이동통신사의 오류는 별도의 Receiver에서 수신 처리함
			// 수신 처리시 MessageId의 구성 형식(SMS_ID + "-" + 수신전화번호)를 통해 DB에 결과를 반영

			// 2011.10.21 보안점검 후속조치
			if (result != null) {
				smsRecptn.setResultCode(Integer.toString(result.getResult()));
				smsRecptn.setResultMssage(result.getResultMessage());
			}
			smsMapper.insertSmsRecptnInf(smsRecptn);
		}
	}

	/**
	 * 문자메시지에 대한 상세정보를 조회한다.
	 * 
	 * @param smsVO
	 */
	public SmsVO selectSmsInf(SmsVO smsVO) throws Exception {
		SmsVO resultVo = smsMapper.selectSmsInf(smsVO);

		// 전화번호 포맷 처리
		resultVo.setTrnsmitTelno(formatPhoneNumber(resultVo.getTrnsmitTelno()));

		SmsRecptnVO recptn = new SmsRecptnVO();

		recptn.setSmsId(smsVO.getSmsId());

		List<EgovMap> list = smsMapper.selectSmsRecptnInfs(recptn);

		// 전화번호 포맷 처리
		for (int i = 0; i < list.size(); i++) {
			String phone = list.get(i).get("recptnTelno").toString();
			list.get(i).put("recptnTelno", (formatPhoneNumber(phone)));
		}
		resultVo.setRecptn(list);

		// searchVO 이전 
		resultVo.setSearchVO(smsVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 문자메시지 실 전송을 요청한다.
	 * 
	 * @param smsMessageVO
	 */
	public SmsMessageVO sendRequsest(SmsMessageVO smsMessageVO) throws Exception {
		String callTo = smsMessageVO.getCallTo();
		String callFrom = smsMessageVO.getCallFrom();
		String callBack = smsMessageVO.getCallBack();
		String callBackUrl = smsMessageVO.getCallBackUrl();
		String text = smsMessageVO.getText();
		String messageId = smsMessageVO.getMessageId(); // messageId 지정 필요

		egovLogger.info("------------------------");
		egovLogger.info("callTo = " + callTo);
		egovLogger.info("callFrom = " + callFrom);
		egovLogger.info("callBack = " + callBack);
		egovLogger.info("callBackUrl = " + callBackUrl);
		egovLogger.info("text = " + text);
		egovLogger.info("messageId = " + messageId);

		// SMS 전송 요청
		SmsInfoSender sender = null;
		SmsMessageVO result = null;
		try {
			sender = new SmsInfoSender(smeConfigPath);

			sender.open();
			result = sender.send(smsMessageVO);
		} finally {
			if (sender != null) {
				sender.close();
			}
		}

		// Sender의 전송 결과는 SMS G/W 처리 상의 결과만 리턴함
		// 이동통신사의 오류는 별도의 Receiver에서 수신 처리함 (로그 기록)

		if (result != null) { // 2011.10.21 보안점검 후속조치
			smsMessageVO.setResult(result.getResult());
			smsMessageVO.setResultMessage(result.getResultMessage());
		}
		return smsMessageVO;
	}

	/**
	 * 여러 건의 문자메시지 실 전송을 요청한다.
	 * 
	 * @param smsConn
	 * @throws Exception
	 */
	public SmsMessageVO[] sendRequsest(SmsMessageVO[] smsConn) throws Exception {
		SmsInfoSender sender = null;

		try {
			sender = new SmsInfoSender(smeConfigPath);

			sender.open();

			// SMS 전송 요청
			SmsMessageVO result = null;
			for (int i = 0; i < smsConn.length; i++) {
				String callTo = smsConn[i].getCallTo();
				String callFrom = smsConn[i].getCallFrom();
				String callBack = smsConn[i].getCallBack();
				String callBackUrl = smsConn[i].getCallBackUrl();
				String text = smsConn[i].getText();
				String messageId = smsConn[i].getMessageId(); // messageId 지정 필요

				egovLogger.info("------------------------");
				egovLogger.info("callTo[" + i + "] = " + callTo);
				egovLogger.info("callFrom[" + i + "] = " + callFrom);
				egovLogger.info("callBack[" + i + "] = " + callBack);
				egovLogger.info("callBackUrl[" + i + "] = " + callBackUrl);
				egovLogger.info("text =[" + i + "] = " + text);
				egovLogger.info("messageId[" + i + "] = " + messageId);

				// smsConn[i] = sendRequsest(smsConn[i]);
				result = sender.send(smsConn[i]);

				// Sender의 전송 결과는 SMS G/W 처리 상의 결과만 리턴함
				// 이동통신사의 오류는 별도의 Receiver에서 수신 처리함 (로그 기록)

				smsConn[i].setResult(result.getResult());
				smsConn[i].setResultMessage(result.getResultMessage());
			}

		} finally {
			if (sender != null) {
				sender.close();
			}
		}

		return smsConn;
	}
}
