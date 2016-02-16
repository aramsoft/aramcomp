package aramframework.com.uss.ion.noi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.noi.dao.NotificationMapper;
import aramframework.com.uss.ion.noi.domain.NotificationVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 정보알림이를 위한 서비스 구현 클래스
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
public class NotificationService extends EgovAbstractServiceImpl {

	@Autowired
	private NotificationMapper notificationMapper;	

	/**
	 * 정보알림이 목록을 조회 한다.
	 * 
	 * @param notificationVO
	 */
	public List<EgovMap> selectNotificationInfs(NotificationVO notificationVO) {
		return notificationMapper.selectNotificationInfs(notificationVO);
	}

	/**
	 * 정보알림이 총 갯수를 조회한다.
	 * 
	 * @param notificationVO
	 */
	public int selectNotificationInfsCnt(NotificationVO notificationVO) {
		return notificationMapper.selectNotificationInfsCnt(notificationVO);
	}

	/**
	 * 알림메시지에 대한 상세정보를 조회한다.
	 * 
	 * @param notificationVO
	 */
	public NotificationVO selectNotificationInf(NotificationVO notificationVO) {
		NotificationVO resultVo = notificationMapper.selectNotificationInf(notificationVO);
		// searchVO 이전 
		resultVo.setSearchVO(notificationVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 정보알림이 정보를 등록한다.
	 * 
	 * @param notificationVO
	 */
	public void insertNotificationInf(NotificationVO notificationVO) {
		// ---------------------------------------
		// 알림일자 및 시작 지정
		// ---------------------------------------
		StringBuffer time = new StringBuffer();

		time.append(notificationVO.getNtfcDate().replaceAll("-", ""));
		time.append(notificationVO.getNtfcHH().length() == 1 ? "0" + notificationVO.getNtfcHH() : notificationVO.getNtfcHH());
		time.append(notificationVO.getNtfcMM().length() == 1 ? "0" + notificationVO.getNtfcMM() : notificationVO.getNtfcMM());
		time.append("00");

		notificationVO.setNtfcTime(time.toString());

		// ---------------------------------------
		// 사전 알림간격 지정
		// ---------------------------------------
		StringBuffer interval = new StringBuffer();

		String[] array = notificationVO.getBhNtfcIntrvl();

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				interval.append(",");
			}

			interval.append(array[i]);
		}

		notificationVO.setBhNtfcIntrvlString(interval.toString());

		// ---------------------------------------
		// 등록 처리
		// ---------------------------------------
		notificationMapper.insertNotificationInf(notificationVO);
	}

	/**
	 * 정보알림이 정보를 수정한다.
	 * 
	 * @param notificationVO
	 */
	public void updateNotifictionInf(NotificationVO notificationVO) {
		// ---------------------------------------
		// 알림일자 및 시작 지정
		// ---------------------------------------
		StringBuffer time = new StringBuffer();

		time.append(notificationVO.getNtfcDate().replaceAll("-", ""));
		time.append(notificationVO.getNtfcHH().length() == 1 ? "0" + notificationVO.getNtfcHH() : notificationVO.getNtfcHH());
		time.append(notificationVO.getNtfcMM().length() == 1 ? "0" + notificationVO.getNtfcMM() : notificationVO.getNtfcMM());
		time.append("00");

		notificationVO.setNtfcTime(time.toString());

		// ---------------------------------------
		// 사전 알림간격 지정
		// ---------------------------------------
		StringBuffer interval = new StringBuffer();

		String[] array = notificationVO.getBhNtfcIntrvl();

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				interval.append(",");
			}

			interval.append(array[i]);
		}

		notificationVO.setBhNtfcIntrvlString(interval.toString());

		// ---------------------------------------
		// 수정 처리
		// ---------------------------------------
		notificationMapper.updateNotificationInf(notificationVO);
	}

	/**
	 * 정보알림이 정보를 삭제한다.
	 * 
	 * @param notificationVO
	 */
	public void deleteNotifictionInf(NotificationVO notificationVO) {
		notificationMapper.deleteNotificationInf(notificationVO);
	}

	/**
	 * 정보알림이 알림시간 등에 대한 점검을 수행한다.
	 * 
	 * @param notificationVO
	 */
	public boolean checkNotification(NotificationVO notificationVO) {
		// ---------------------------------------
		// 알림일자 및 시작 지정
		// ---------------------------------------
		StringBuffer time = new StringBuffer();

		time.append(notificationVO.getNtfcDate().replaceAll("-", ""));
		time.append(notificationVO.getNtfcHH().length() == 1 ? "0" + notificationVO.getNtfcHH() : notificationVO.getNtfcHH());
		time.append(notificationVO.getNtfcMM().length() == 1 ? "0" + notificationVO.getNtfcMM() : notificationVO.getNtfcMM());
		time.append("00");

		// ---------------------------------------
		// 시간 지정
		// ---------------------------------------
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		Calendar alarm = Calendar.getInstance();
		try {
			alarm.setTime(formatter.parse(time.toString()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		Calendar current = Calendar.getInstance();
		current.add(Calendar.MINUTE, -1);

		if (current.after(alarm)) {
			return false;
		}

		return true;
	}

	private String getDateTimeWithoutSec(Calendar cal) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		return formatter.format(cal.getTime()).substring(0, 12);
	}

	/**
	 * 정보알림이 정보 표시를 수행한다.
	 * 
	 */
	public List<EgovMap> selectNotificationData() {

		List<EgovMap> result = new ArrayList<EgovMap>();

		// ------------------------------------------
		// 검색 조건 지정
		// ------------------------------------------
		NotificationVO vo = new NotificationVO();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		SimpleDateFormat other = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

		// 전후 1시간 조건 지정..
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.add(Calendar.HOUR, -1);
		end.add(Calendar.HOUR, 1);

		vo.setStartDateTime(formatter.format(start.getTime()));
		vo.setEndDateTime(formatter.format(end.getTime()));
		// //----------------------------------------

		List<EgovMap> target = notificationMapper.getNotificationData(vo);

		EgovMap egovMap = new EgovMap();
		Calendar current = Calendar.getInstance();
		for (int i = 0; i < target.size(); i++) {
			egovMap = target.get(i);

			String[] interval = ("0," + egovMap.get("BhNtfcIntrvlString")).split(",");

			for (int j = 0; j < interval.length; j++) {
				Calendar alarm = Calendar.getInstance();
				try {
					alarm.setTime(other.parse(vo.getNtfcTime()));
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

				alarm.add(Calendar.MINUTE, -1 * Integer.parseInt(interval[j]));

				if (getDateTimeWithoutSec(current).equals(getDateTimeWithoutSec(alarm))) {

					result.add(egovMap);
					break;
				}
			}
		}

		return result;
	}
	
}
