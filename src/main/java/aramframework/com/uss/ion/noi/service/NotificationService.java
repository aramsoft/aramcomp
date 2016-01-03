package aramframework.com.uss.ion.noi.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 정보알림이를 위한 서비스 인터페이스 클래스
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

public interface NotificationService {
	
	/**
	 * 정보알림이 목록을 조회 한다.
	 * 
	 * @param notificationVO
	 */
	public List<EgovMap> selectNotificationInfs(NotificationVO notificationVO);

	/**
	 * 정보알림이 총 갯수를 조회한다.
	 * 
	 * @param notificationVO
	 */
	public int selectNotificationInfsCnt(NotificationVO notificationVO);

	/**
	 * 정보알림이에 대한 상세정보를 조회한다.
	 * 
	 * @param notificationVO
	 */
	public NotificationVO selectNotificationInf(NotificationVO notificationVO);

	/**
	 * 정보알림이 정보를 등록한다.
	 * 
	 * @param notificationVO
	 */
	public void insertNotificationInf(NotificationVO notificationVO);

	/**
	 * 정보알림이 정보를 수정한다.
	 * 
	 * @param notificationVO
	 */
	public void updateNotifictionInf(NotificationVO notificationVO);

	/**
	 * 정보알림이 정보를 삭제한다.
	 * 
	 * @param notificationVO
	 */
	public void deleteNotifictionInf(NotificationVO notificationVO);

	/**
	 * 정보알림이 알림시간 등에 대한 점검을 수행한다.
	 * 
	 * @param notificationVO
	 */
	public boolean checkNotification(NotificationVO notificationVO);

	/**
	 * 정보알림이 정보 표시를 수행한다.
	 * 
	 */
	public List<EgovMap> selectNotificationData();
	
}
