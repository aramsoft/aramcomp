package aramframework.com.sts.bst.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aramframework.com.sts.bst.service.BbsStatsService;

/**
 * 게시물 통계 집계를 위한 스케줄링 클래스
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

@Component("bbsStatsScheduling")
public class BbsStatsScheduling {

	@Autowired
	private BbsStatsService bbsStatsService;

	/**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * 
	 * @exception Exception
	 */
	public void summaryBbsStats() throws Exception {
		bbsStatsService.summaryBbsStats();
	}
}
