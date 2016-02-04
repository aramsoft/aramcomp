package aramframework.com.uss.ion.noi.service;

import java.util.List;

import aramframework.com.uss.ion.noi.domain.NotificationVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 정보알림이를 위한 데이터 접근 클래스
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

@Mapper
public interface NotificationMapper {
	/**
	 * 정보알림이 목록을 조회한다.
	 * 
	 * @param notificationVO
	 */
	public List<EgovMap> selectNotificationInfs(NotificationVO notificationVO);

	/**
	 * 정보알림이 목록 숫자를 조회한다
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
	public void updateNotificationInf(NotificationVO notificationVO);

	/**
	 * 정보알림이 정보를 삭제한다.
	 * 
	 * @param notificationVO
	 */
	public void deleteNotificationInf(NotificationVO notificationVO); 

	/**
	 * 정보알림이 표시를 위한 대상 알림 정보를 얻는다.
	 * 
	 * @param notificationVO
	 */
	public List<EgovMap> getNotificationData(NotificationVO notificationVO);
	
}
