package aramframework.com.sec.pki.service.impl;

import java.util.Enumeration;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPSearchConstraints;
import netscape.ldap.LDAPSearchResults;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gpki.gpkiapi_jni;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.sec.pki.service.GPKIService;

/**
 * GPKI(Goverment Public Key Infrastructure)를 위한 서비스 구현 클래스
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

@Service("GPKIService")
public class GPKIServiceImpl extends EgovAbstractServiceImpl implements GPKIService {
	/** GPKI API JNI */
	private gpkiapi_jni gpkiAPI = null;
	/** 속성 파일 정보 */
	private String config = null;

	final private Logger LOG = LoggerFactory.getLogger(this.getClass());

	/*
	 * // PostConstruct 사용 시 startup loading으로 인하여 gpkiapi_jni.dll이 없는 경우 servlet loading되지 못함
	 * // setup(synchronized) 메소드 사용 방식으로 변경
	 * 
	 * @PostConstruct public void init() { 
	 * 		//-------------------------------- 
	 * 		// 속성 정보 얻기 
	 * 		//-------------------------------- 
	 * 		config = AramProperties.getProperty("Globals.GPKIConfPath");
	 * 
	 * 		//-------------------------------- 
	 * 		// GPKI JNI 취득
	 * 		//-------------------------------- 
	 * 		// gpkiapki_jni.jar의 경우는 System Classpath에 추가되어야 함.. 
	 * 		// 그렇지 않은 경우는 다음과 같은 오류가 reload시 발생됨 
	 * 		// Native Library C:\WINDOWS\system32\gpkiapi_jni.dll already 
	 * 		// loaded in another classloader 
	 * 		//-------------------------------- gpkiAPI = new gpkiapi_jni(); 
	 * }
	 */

	public void setup() {
		synchronized (this) {
			if (config == null || gpkiAPI == null) {
				// --------------------------------
				// 속성 정보 얻기
				// --------------------------------
				config = AramProperties.getPathProperty("Globals.GPKIConfPath");

				// --------------------------------
				// GPKI JNI 취득
				// --------------------------------
				// gpkiapki_jni.jar의 경우는 System Classpath에 추가되어야 함..
				// 그렇지 않은 경우는 다음과 같은 오류가 reload시 발생됨
				// Native Library C:\WINDOWS\system32\gpkiapi_jni.dll already
				// loaded in another classloader
				// --------------------------------
				gpkiAPI = new gpkiapi_jni();
			}
		}
	}

	/**
	 * 현 서버의 ID를 얻는다.
	 */
	public String getServerId() throws Exception {
		// -----------------------------------------
		// @PostConstruct 미사용 방식
		// -----------------------------------------
		if (config == null || gpkiAPI == null) {
			setup();
		}
		// //---------------------------------------

		return AramProperties.getProperty(config, "gpki.certificate.server");
	}

	/**
	 * LDAP에서 인증서 얻기.
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	protected byte[] getCertFromLDAP(String code) throws Exception {
		// -----------------------------------------
		// @PostConstruct 미사용 방식
		// -----------------------------------------
		if (config == null || gpkiAPI == null) {
			setup();
		}
		// //---------------------------------------

		// --------------------------------
		// LDAP 관련 정보 얻기
		// --------------------------------
		String serverIp = AramProperties.getProperty(config, "gpki.ldap.ip");
		String serverPort = AramProperties.getProperty(config, "gpki.ldap.port");
		String basedn = AramProperties.getProperty(config, "gpki.ldap.basedn");
		String readEntry = "cn=SVR" + code;
		String attribute = AramProperties.getProperty(config, "gpki.ldap.attribute");

		String pwd = null;

		// --------------------------------
		// LDAP 연결
		// --------------------------------
		byte[] cert = null;
		LDAPEntry entry = null;
		Enumeration<?> enumerator = null;
		LDAPSearchConstraints cons = null;
		LDAPSearchResults res = null;
		LDAPConnection ld = null;
		LDAPSearchConstraints constraints = null;

		try {
			ld = new LDAPConnection();
			constraints = new LDAPSearchConstraints();
			constraints.setTimeLimit(5000);
			ld.setConnectTimeout(3);
			ld.setConstraints(constraints);

			ld.connect(serverIp, Integer.parseInt(serverPort), basedn, pwd);

			cons = ld.getSearchConstraints();
			cons.setBatchSize(1);
			res = ld.search(basedn, 2, readEntry, null, false, cons);
			entry = (LDAPEntry) res.nextElement();
			enumerator = entry.getAttribute(attribute).getByteValues();
			cert = (byte[]) enumerator.nextElement();
		} catch (Exception ex) {
			LOG.error("Exception:  " + ex.getClass().getName());
			LOG.error("Exception  Message:  " + ex.getMessage());
			throw ex;
		} finally {
			if (ld != null) {
				try {
					ld.disconnect();
				} catch (LDAPException ex) {
					LOG.debug("Ignored Exception : " + ex.getMessage());
				}
			}
		}

		return cert;
	}

	/**
	 * 데이터 암호화 처리.
	 * 
	 * @see aramframework.com.sec.pki.service.GPKIService#encrypt(byte[],
	 *      java.lang.String)
	 */
	public byte[] encrypt(byte[] message, String target) throws Exception {
		// -----------------------------------------
		// @PostConstruct 미사용 방식
		// -----------------------------------------
		if (config == null || gpkiAPI == null) {
			setup();
		}
		// //---------------------------------------

		byte[] cert = getCertFromLDAP(target);

		byte[] encryptedData = null;

		try {
			gpkiAPI.API_Init(".");
			int returnCode = 0;

			returnCode = gpkiAPI.API_SetOption(gpkiapi_jni.API_OPT_RSA_ENC_V20);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}

			returnCode = gpkiAPI.CMS_MakeEnvelopedData(cert, message, gpkiapi_jni.SYM_ALG_SEED_CBC);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			encryptedData = gpkiAPI.baReturnArray;

		} catch (Exception ex) {
			// System.err.println(ex);
			LOG.error(ex.getMessage());
			throw ex;
		} finally {
			if (gpkiAPI != null) {
				gpkiAPI.API_Finish();
			}
		}

		return encryptedData;
	}

	/**
	 * 복호화 처리.
	 * 
	 * @see aramframework.com.sec.pki.service.GPKIService#decrypt(byte[])
	 */
	public byte[] decrypt(byte[] data) throws Exception {
		// -----------------------------------------
		// @PostConstruct 미사용 방식
		// -----------------------------------------
		if (config == null || gpkiAPI == null) {
			setup();
		}
		// //---------------------------------------

		// ----------------------------------
		// 설정 정보 (암호화용 인증서 정보 필요)
		// ----------------------------------
		String path = AramProperties.getProperty(config, "gpki.certificate.path");

		String certForEnvFile = path + "/SVR" + AramProperties.getProperty(config, "gpki.certificate.server") + "_env.cer";
		String keyForEnvFile = path + "/SVR" + AramProperties.getProperty(config, "gpki.certificate.server") + "_env.key";
		String pinForEnv = AramProperties.getProperty(config, "gpki.privatekey.password");

		// ----------------------------------
		// 복호화 처리
		// ----------------------------------
		byte[] plainData = null;

		try {
			gpkiAPI.API_Init(".");
			int returnCode = 0;

			byte[] baPriKey = null;
			byte[] certificate = null;

			returnCode = gpkiAPI.STORAGE_ReadPriKey(gpkiapi_jni.MEDIA_TYPE_FILE_PATH, keyForEnvFile, pinForEnv, gpkiapi_jni.DATA_TYPE_OTHER);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			baPriKey = gpkiAPI.baReturnArray;

			returnCode = gpkiAPI.STORAGE_ReadCert(gpkiapi_jni.MEDIA_TYPE_FILE_PATH, certForEnvFile, gpkiapi_jni.DATA_TYPE_OTHER);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			certificate = gpkiAPI.baReturnArray;

			returnCode = gpkiAPI.CMS_ProcessEnvelopedData(certificate, baPriKey, data);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			plainData = gpkiAPI.baReturnArray;

		} catch (Exception ex) {
			// System.err.println(ex);
			LOG.error(ex.getMessage());
			throw ex;
		} finally {
			if (gpkiAPI != null) {
				gpkiAPI.API_Finish();
			}
		}

		return plainData;
	}

	/**
	 * 전자서명 처리.
	 * 
	 * @see aramframework.com.sec.pki.service.GPKIService#sign(byte[])
	 */
	public byte[] sign(byte[] message) throws Exception {
		// -----------------------------------------
		// @PostConstruct 미사용 방식
		// -----------------------------------------
		if (config == null || gpkiAPI == null) {
			setup();
		}
		// 
		//---------------------------------------

		// ----------------------------------
		// 설정 정보 (전자서명용 인증서 정보 필요)
		// ----------------------------------
		String path = AramProperties.getProperty(config, "gpki.certificate.path");

		String certForSignFile = path + "/SVR" + AramProperties.getProperty(config, "gpki.certificate.server") + "_sig.cer";
		String keyForSignFile = path + "/SVR" + AramProperties.getProperty(config, "gpki.certificate.server") + "_sig.key";
		String pinForSign = AramProperties.getProperty(config, "gpki.privatekey.password");

		// ----------------------------------
		// 전자서명 처리
		// ----------------------------------
		byte[] signedData = null;
		byte[] certificate = null;
		byte[] key = null;

		try {
			gpkiAPI.API_Init(".");
			int returnCode = 0;

			returnCode = gpkiAPI.STORAGE_ReadCert(gpkiapi_jni.MEDIA_TYPE_FILE_PATH, certForSignFile, gpkiapi_jni.DATA_TYPE_OTHER);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			certificate = gpkiAPI.baReturnArray;

			returnCode = gpkiAPI.STORAGE_ReadPriKey(gpkiapi_jni.MEDIA_TYPE_FILE_PATH, keyForSignFile, pinForSign, gpkiapi_jni.DATA_TYPE_OTHER);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			key = gpkiAPI.baReturnArray;

			returnCode = gpkiAPI.CMS_MakeSignedData(certificate, key, message, "");
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			signedData = gpkiAPI.baReturnArray;

		} catch (Exception ex) {
			// System.err.println(ex);
			LOG.error(ex.getMessage());
			throw ex;
		} finally {
			if (gpkiAPI != null) {
				gpkiAPI.API_Finish();
			}
		}

		return signedData;
	}

	/**
	 * 전자서명 검증.
	 * 
	 * @see aramframework.com.sec.pki.service.GPKIService#verifySign(byte[])
	 */
	public byte[] verifySign(byte[] signedData) throws Exception {
		// -----------------------------------------
		// @PostConstruct 미사용 방식
		// -----------------------------------------
		if (config == null || gpkiAPI == null) {
			setup();
		}
		// 
		//---------------------------------------

		// ----------------------------------
		// 전자서명 확인
		// ----------------------------------
		byte[] plainData = null;

		try {
			gpkiAPI.API_Init(".");
			int returnCode = 0;

			returnCode = gpkiAPI.CMS_ProcessSignedData(signedData);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			plainData = gpkiAPI.baData;

		} catch (Exception ex) {
			// System.err.println(ex);
			LOG.error(ex.getMessage());
			throw ex;
		} finally {
			if (gpkiAPI != null) {
				gpkiAPI.API_Finish();
			}
		}

		return plainData;
	}

	/**
	 * BASE64 encoding 처리.
	 * 
	 * @see aramframework.com.sec.pki.service.GPKIService#getBASE64String(byte[])
	 */
	public String getBASE64String(byte[] data) throws Exception {
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * BASE64 decoding 처리.
	 * 
	 * @see aramframework.com.sec.pki.service.GPKIService#getDataFromBASE64(java.lang.String)
	 */
	public byte[] getDataFromBASE64(String base64) throws Exception {
		return Base64.decodeBase64(base64.getBytes());
	}
}
