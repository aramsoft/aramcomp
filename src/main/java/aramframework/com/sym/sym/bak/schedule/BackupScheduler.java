package aramframework.com.sym.sym.bak.schedule;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.sym.sym.bak.domain.BackupOpertVO;
import aramframework.com.sym.sym.bak.service.BackupOpertService;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

/**
 * Quartz Scheduler를 실행하는 스케줄러 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BackupScheduler {

	/** BackupOpertService	 */
	private BackupOpertService backupOpertService;

	/** ID Generation */
	private EgovIdGnrService idgenService;

	/** Quartz 스케줄러 */
	private Scheduler sched;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 실행 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * 백업작업서비스 리턴
	 * 
	 */
	public BackupOpertService getBackupOpertService() {
		return backupOpertService;
	}
	/**
	 * 백업작업서비스 저장.
	 * 
	 * @param backupOpertService
	 */
	public void setBackupOpertService(BackupOpertService backupOpertService) {
		this.backupOpertService = backupOpertService;
	}

	/**
	 * 백업결과ID 생성서비스 리턴
	 * 
	 * @return the idgenService
	 */
	public EgovIdGnrService getIdgenService() {
		return idgenService;
	}
	/**
	 * 백업결과ID 생성서비스 저장.
	 * 
	 * @param idgenService
	 */
	public void setIdgenService(EgovIdGnrService idgenService) {
		this.idgenService = idgenService;
	}

	/**
	 * 클래스 초기화메소드. 배치스케줄테이블을 읽어서 Quartz 스케줄러를 초기화한다.
	 * 
	 */
	public void init() throws Exception {

		List<BackupOpertVO> targetList = null;
		BackupOpertVO backupOpertVO = new BackupOpertVO();
		
		// 모니터링 대상 검색 조건 초기화
		backupOpertVO.setPageIndex(1);
		backupOpertVO.setFirstIndex(0);
		backupOpertVO.setRecordPerPage(RECORD_COUNT_PER_PAGE);
		targetList = backupOpertService.selectBackupOpertList(backupOpertVO);

		// 스케줄러 생성하기
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		sched = schedFact.getScheduler();

		// 스케줄러에 Job, Trigger 등록하기
		BackupOpertVO target = null;
		for (int i = 0; i < targetList.size(); i++) {
			target = (BackupOpertVO) targetList.get(i);
			if("Y".equals(target.getUseAt())) {
				insertBackupOpert(target, false);
			}	
		}
		sched.start();
	}

	/**
	 * 클래스 destroy메소드. Quartz 스케줄러를 shutdown한다.
	 * 
	 */
	public void destroy() throws Exception {
		sched.shutdown();
	}

	/**
	 * 백업스케줄러에 backupOpert 파라미터를 이용하여 Job , Trigger를 Add 한다.
	 * 
	 * @param backupOpertVO
	 */
	public void insertBackupOpert(BackupOpertVO backupOpertVO, boolean updateMode) throws Exception  {
		if (updateMode) {
			LOG.debug("백업스케줄을 갱신합니다. 백업작업ID : " + backupOpertVO.getBackupOpertId());
		} else {
			LOG.debug("백업스케줄을 등록합니다. 백업작업ID : " + backupOpertVO.getBackupOpertId());
		}
		// Job 만들기
		JobKey jobKey = new JobKey(backupOpertVO.getBackupOpertId());
		JobDetail jobDetail = JobBuilder.newJob(BackupJob.class)
				.withIdentity(jobKey)
				.build();

		// Trigger 만들기
		Trigger trigger = TriggerBuilder.newTrigger()
			    .withIdentity(jobKey.getName())
			    .withSchedule(CronScheduleBuilder.cronSchedule(backupOpertVO.toCronExpression()))
			    .forJob(jobKey.getName())
			    .build();
		
		// 데이터 전달
		jobDetail.getJobDataMap().put("backupOpertId", backupOpertVO.getBackupOpertId());
		jobDetail.getJobDataMap().put("backupOrginlDrctry", backupOpertVO.getBackupOrginlDrctry());
		jobDetail.getJobDataMap().put("backupStreDrctry", backupOpertVO.getBackupStreDrctry());
		jobDetail.getJobDataMap().put("cmprsSe", backupOpertVO.getCmprsSe());

		BackupJobListener listener = new BackupJobListener();
        listener.setBackupOpertService(backupOpertService);
        listener.setIdgenService(idgenService);

		sched.getListenerManager().addJobListener(listener, KeyMatcher.keyEquals(jobKey));
		
		try {
			if (updateMode) {
				// 스케줄러에서 기존Job, Trigger 삭제하기
				sched.deleteJob(jobKey);
			}	
			// 스케줄러에 추가하기
			sched.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// SchedulerException 이 발생하면 로그를 출력하고 다음 백업작업으로 넘어간다.
			// 트리거의 실행시각이 현재 시각보다 이전이면 SchedulerException이 발생한다.
			if (updateMode) {
				LOG.error("스케줄러에 백업작업갱신할때 에러가 발생했습니다. 백업작업ID : " + backupOpertVO.getBackupOpertId());
			} else {
				LOG.error("스케줄러에 백업작업추가할때 에러가 발생했습니다. 백업작업ID : " + backupOpertVO.getBackupOpertId());
			}
			LOG.error("에러내용 : " + e.getMessage());
		}
	}

	/**
	 * 백업스케줄러에 backupOpert 파라미터를 이용하여 Job , Trigger를 삭제한다.
	 * 
	 * @param backupOpertVO
	 */
	public void deleteBackupOpert(BackupOpertVO backupOpertVO)  {
		JobKey jobKey = new JobKey(backupOpertVO.getBackupOpertId());
		try {
			// 스케줄러에서 기존Job, Trigger 삭제하기
			LOG.debug("백업작업을 삭제합니다. 백업작업ID : " + backupOpertVO.getBackupOpertId());
			sched.deleteJob(jobKey);
		} catch (SchedulerException e) {
			// SchedulerException 이 발생하면 로그를 출력하고 다음 배치작업으로 넘어간다.
			LOG.error("스케줄러에 백업작업을 삭제할때 에러가 발생했습니다. 배치스케줄ID : " + backupOpertVO.getBackupOpertId());
			LOG.error("에러내용 : " + e.getMessage());
		}
	}

}
