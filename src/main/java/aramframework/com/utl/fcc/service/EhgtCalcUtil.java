package aramframework.com.utl.fcc.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * 대한민국, 미국,유럽연합, 일본, 중국연합 사이의 환율계산기능을
 * 제공하는  Business Interface class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class EhgtCalcUtil {

	// 환율....
	static final char EGHT_USD = 'U'; // 미국
	static final char EGHT_JPY = 'J'; // 일본
	static final char EGHT_EUR = 'E'; // 유럽연합
	static final char EGHT_CNY = 'C'; // 중국연합

	static final char EGHT_KWR = 'K'; // 대한민국

	static StringBuffer sb = new StringBuffer();

	/**
	 * 대한민국(KRW), 미국(USD), 유럽연합(EUR), 일본(JPY), 중국원화(CNY) 사이의 환율을 계산하는 기능이다 환율표 -
	 * 매매기준율 => 미국(USD) - 1485.00(USD), 일본-100(JPY) - 1596.26(JPY) 계산법:
	 * 대한민원(KRW) - 1,000원 -> 미국(USD)로 변환 시 => 1,000(원)/1485(매매기준율) = 0.67(URS)
	 * 계산법: 일본(JPY) - 100,000원 -> 대한민국(KRW) 변환 시 => (100,000(원) *
	 * 1596.26(매매기준율)) / 100(100엔당 기준표이므로) = 1,596,260.00 (KRW) 계산법: 일본(JPY) -
	 * 100,000원 -> 미국(USD) 변환 시 => ( (100,000(원) * 1596.26(매매기준율)) / 100(100엔당
	 * 기준표이므로) = 1,596,260.00 (KRW)) / 1,485.00 = 1,074.92 (USD)
	 * 
	 * @param srcType
	 *            - 환율기준
	 * @param srcAmount
	 *            - 금액
	 * @param cnvrType
	 *            - 변환환율
	 * @return 환율금액
	 * @exception MyException
	 * @see
	 */
	public void readHtmlParsing(String str) {
		// 2011.10.10 보안점검 후속조치
		HttpURLConnection con = null;
		InputStreamReader reader = null;
		try {
			// 입력받은 URL에 연결하여 InputStream을 통해 읽은 후 파싱 한다.
			URL url = new URL(str);

			con = (HttpURLConnection) url.openConnection();

			reader = new InputStreamReader(con.getInputStream(), "euc-kr");
			// InputStreamReader reader = new
			// InputStreamReader(con.getInputStream(), "utf-8");

			new ParserDelegator().parse(reader, new CallbackHandler(), true);

			con.disconnect();

		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (con != null) {
				con.disconnect();
			}

			if (reader != null) { // 2011.10.21 보안점검 후속조치
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

		}

	}

	// 파서는 콜백 형식으로 되어 있다. 각 태그가 들어 올때 적절한 메소드가 호출됨
	private class CallbackHandler extends HTMLEditorKit.ParserCallback {

		public void handleText(char[] data, int pos) {

			String srcStr = new String(data);

			srcStr = StringUtil.strip(srcStr, " ");

			sb.append(srcStr).append("/");

		}

	}

	public static String getEhgtCalc(String srcType, long srcAmount, String cnvrType) throws Exception {

		String rtnStr = null;

		String[] eghtStdrRt = null; // Html에서 파싱한 환율매매기준율을 저장하기 위한 문자열배열

		double srcStdrRt = 0.00; // 원래 매매기준율
		double cnvrStdrRt = 0.00; // 변환 매매기준율

		String sCnvrAmount = null; // 변환금액

		String srcStr = null;
		String cnvrStr = null;

		String srcTypeCnvr = srcType.toUpperCase();
		String cnvrTypeCnvr = cnvrType.toUpperCase();

		try {

			EhgtCalcUtil parser = new EhgtCalcUtil();

			parser.readHtmlParsing("http://community.fxkeb.com/fxportal/jsp/RS/DEPLOY_EXRATE/4176_0.html");

			// 2011.10.10 보안점검 후속조치
			if (sb == null) {
				throw new RuntimeException("StringBuffer is null!!");
			}

			eghtStdrRt = StringUtil.split(sb.toString(), "/");

		} catch (Exception e) {

			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치

		}

		if (eghtStdrRt == null || (eghtStdrRt.length == 0))
			throw new java.lang.Exception("String Split Error!");

		char srcChr = srcTypeCnvr.charAt(0);
		char cnvrChr = cnvrTypeCnvr.charAt(0);

		// 원래 환율기준 정의
		switch (srcChr) {

		case EGHT_USD: // 미국
			srcStr = "USD";
			break;

		case EGHT_JPY: // 일본
			srcStr = "JPY";
			break;

		case EGHT_EUR: // 유럽연합
			srcStr = "EUR";
			break;

		case EGHT_CNY: // 중국연합
			srcStr = "CNY";
			break;

		default:
			srcStr = "USD";
			break;
		}

		// 변환하고자 하는 환율기준 정의
		switch (cnvrChr) {

		case EGHT_USD: // 미국
			cnvrStr = "USD";
			break;

		case EGHT_JPY: // 일본
			cnvrStr = "JPY";
			break;

		case EGHT_EUR: // 유럽연합
			cnvrStr = "EUR";
			break;

		case EGHT_CNY: // 중국연합
			cnvrStr = "CNY";
			break;

		default:
			cnvrStr = "KRW";
			break;
		}

		try {

			// 변환하고자 하는 국가의 환율매매기준율 추출...
			for (int i = 0; i < eghtStdrRt.length; i++) {

				// 원래 매매기준율 추출
				if (eghtStdrRt[i].equals(srcStr)) {
					srcStdrRt = Double.parseDouble(eghtStdrRt[i + 1]);

					if (i == (eghtStdrRt.length - 1))
						break;
				}

				// 변환 매매기준율 추출
				if (eghtStdrRt[i].equals(cnvrStr)) {
					cnvrStdrRt = Double.parseDouble(eghtStdrRt[i + 1]);

					if (i == (eghtStdrRt.length - 1))
						break;
				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치

		}

		// 정확한 계산을 위한 BigDecimal 형태로 구현.
		BigDecimal bSrcAmount = new BigDecimal(String.valueOf(srcAmount)); 
		// 변환하고자 하는 금액
		BigDecimal bSrcStdrRt = new BigDecimal(String.valueOf(srcStdrRt)); 
		// 원래 매매 비율
		BigDecimal bCnvrStdrRt = new BigDecimal(String.valueOf(cnvrStdrRt)); 
		// 변환 매매 비율
		BigDecimal bStdr = new BigDecimal("100"); // 변환 매매 비율

		try {

			// 원래 매매기준율 및 변환매매기준율 기준으로 환율금액 계산
			switch (srcChr) {

			case EGHT_KWR: // 대한민국
				if (cnvrChr == 'K')
					// 변환금액 = 변환대상금액;
					sCnvrAmount = bSrcAmount.toString();
				else if (cnvrChr == 'J')
					// 변환금액 = (변환대상금액 / 변환매매비율) * 100;
					sCnvrAmount = (bSrcAmount.divide(bCnvrStdrRt, 4, 4)).multiply(bStdr).setScale(2, 4).toString();
				else
					// 변환금액 = (변환대상금액 / 변환매매비율);
					sCnvrAmount = bSrcAmount.divide(bCnvrStdrRt, 2, 4).toString();
				break;

			case EGHT_USD: // 미국
				if (cnvrChr == 'U')
					// 변환금액 = 변환대상금액;
					sCnvrAmount = bSrcAmount.toString();
				else if (cnvrChr == 'K')
					// 변환금액 = 변환대상금액 * 원래 매매 비율;
					sCnvrAmount = bSrcAmount.multiply(bSrcStdrRt).setScale(2, 4).toString();
				else if (cnvrChr == 'J')
					// cnvrAmount = ((변환대상금액 * 원래 매매 비율) / 변환 매매 비율) * 100;
					sCnvrAmount = ((bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bCnvrStdrRt, 2, 4)).multiply(bStdr).setScale(2, 4).toString();
				else
					// cnvrAmount = (변환대상금액 * 원래 매매 비율) / 변환 매매 비율;
					sCnvrAmount = (bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bCnvrStdrRt, 2, 4).toString();
				break;

			case EGHT_EUR: // 유럽연합
				if (cnvrChr == 'E')
					// 변환금액 = 변환대상금액;
					sCnvrAmount = bSrcAmount.toString();
				else if (cnvrChr == 'K')
					// cnvrAmount = 변환대상금액 * 원래 매매 비율;
					sCnvrAmount = bSrcAmount.multiply(bSrcStdrRt).setScale(2, 4).toString();
				else if (cnvrChr == 'J')
					// cnvrAmount = ((변환대상금액 * 원래 매매 비율) / 변환 매매 비율) * 100;
					sCnvrAmount = ((bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bCnvrStdrRt, 2, 4)).multiply(bStdr).setScale(2, 4).toString();
				else
					// cnvrAmount = (변환대상금액 * 원래 매매 비율) / 변환 매매 비율;
					sCnvrAmount = (bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bCnvrStdrRt, 2, 4).toString();
				break;

			case EGHT_JPY: // 일본
				if (cnvrChr == 'J')
					// 변환금액 = 변환대상금액;
					sCnvrAmount = bSrcAmount.toString();
				else if (cnvrChr == 'K')
					// cnvrAmount = (변환대상금액 * 원래 매매 비율) / 100;
					sCnvrAmount = (bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bStdr, 2, 4).toString();
				else
					// cnvrAmount = ((변환대상금액 * 원래 매매 비율) / 100) / 변환 매매 비율;
					sCnvrAmount = ((bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bStdr, 2, 4)).divide(bCnvrStdrRt, 2, 4).toString();
				break;

			case EGHT_CNY: // 중국연합
				if (cnvrChr == 'C')
					// 변환금액 = 변환대상금액;
					sCnvrAmount = bSrcAmount.toString();
				else if (cnvrChr == 'K')
					// cnvrAmount = 변환대상금액 * 원래 매매 비율;
					sCnvrAmount = bSrcAmount.multiply(bSrcStdrRt).setScale(2, 4).toString();
				else if (cnvrChr == 'J')
					// cnvrAmount = ((변환대상금액 * 원래 매매 비율) / 변환 매매 비율) * 100;
					sCnvrAmount = ((bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bCnvrStdrRt, 2, 4)).multiply(bStdr).setScale(2, 4).toString();
				else
					// cnvrAmount = (변환대상금액 * 원래 매매 비율) / 변환 매매 비율;
					sCnvrAmount = (bSrcAmount.multiply(bSrcStdrRt).setScale(4, 4)).divide(bCnvrStdrRt, 2, 4).toString();
				break;

			default:
				// 변환금액 = (변환대상금액 / 변환매매비율);
				sCnvrAmount = bSrcAmount.divide(bCnvrStdrRt, 2, 4).toString();
				break;
			}

		} catch (Exception e) {

			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치
		}

		rtnStr = sCnvrAmount + "  " + cnvrStr;

		return rtnStr;
	}

}