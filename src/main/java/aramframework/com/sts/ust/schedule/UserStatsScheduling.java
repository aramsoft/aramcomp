package aramframework.com.sts.ust.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aramframework.com.sts.ust.service.UserStatsService;

/**
 * 사용자 통계 집계를 위한 스케줄링 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Component("userStatsScheduling")
public class UserStatsScheduling {

	/** UserStatsService */
	@Autowired
	private UserStatsService userStatsService;

	/**
	 * 사용자 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * 
	 * @exception Exception
	 */
	public void summaryUserStats() throws Exception {
		userStatsService.summaryUserStats();
	}
}
