package aramframework.com.sym.bat.schedule;

import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import aramframework.com.sym.bat.domain.BatchSchdulVO;
import aramframework.com.sym.bat.service.BatchSchdulService;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

/**
 * Quartz Scheduler를 실행하는 스케줄러 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BatchScheduler implements BeanFactoryAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MethodInvokingJobDetailFactoryBean factory;

	private BeanFactory beanFactory;

	/** egovBatchSchdulService	 */
	private BatchSchdulService batchSchdulService;

	/** ID Generation */
	private EgovIdGnrService idgenService;

	/** Quartz 스케줄러 */
	private Scheduler sched;

	// 실행 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	/**
	 * 배치스케줄 서비스 리턴
	 * 
	 * @return the egovBatchSchdulService
	 */
	public BatchSchdulService getBatchSchdulService() {
		return batchSchdulService;
	}
	/**
	 * 배치스케줄 서비스 저장.
	 * 
	 * @param egovBatchSchdulService
	 *            the egovBatchSchdulService to set
	 */
	public void setBatchSchdulService(BatchSchdulService egovBatchSchdulService) {
		this.batchSchdulService = egovBatchSchdulService;
	}

	/**
	 * 배치결과ID 생성서비스 리턴
	 * 
	 * @return the idgenService
	 */
	public EgovIdGnrService getIdgenService() {
		return idgenService;
	}
	/**
	 * 배치결과ID 생성서비스 저장.
	 * 
	 * @param idgenService
	 *            the idgenService to set
	 */
	public void setIdgenService(EgovIdGnrService idgenService) {
		this.idgenService = idgenService;
	}
	
	/**
	 * 클래스 초기화메소드. 배치스케줄테이블을 읽어서 Quartz 스케줄러를 초기화한다.
	 * 
	 */
	public void init() throws Exception {

		List<BatchSchdulVO> targetList = null;
		BatchSchdulVO batchSchdulVO = new BatchSchdulVO();
		// 모니터링 대상 검색 조건 초기화
		batchSchdulVO.setPageIndex(1);
		batchSchdulVO.setFirstIndex(0);
		batchSchdulVO.setRecordPerPage(RECORD_COUNT_PER_PAGE);
		targetList = batchSchdulService.selectBatchSchdulList(batchSchdulVO);

		// 스케줄러 생성하기
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		sched = schedFact.getScheduler();

		factory = new MethodInvokingJobDetailFactoryBean();
		factory.setBeanFactory(beanFactory);
	
		// 스케줄러에 Job, Trigger 등록하기
		BatchSchdulVO target = null;
		for (int i = 0; i < targetList.size(); i++) {
			target = (BatchSchdulVO) targetList.get(i);
			if("Y".equals(target.getUseAt())) {
				insertBatchSchdul(target, false);
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
	 * 배치스케줄러에 batchSchdul 파라미터를 이용하여 Job , Trigger를 Add 한다.
	 * 
	 * @param batchSchdul
	 *            배치스케줄러에 등록할 스케줄정보
	 * @exception Exception
	 *                Exception
	 */
	public void insertBatchSchdul(BatchSchdulVO batchSchdulVO, boolean updateMode) throws Exception {
		if (updateMode) {
			logger.debug("배치스케줄을 갱신합니다. 배치스케줄ID : " + batchSchdulVO.getBatchSchdulId());
		} else {
			logger.debug("배치스케줄을 등록합니다. 배치스케줄ID : " + batchSchdulVO.getBatchSchdulId());
		}

		// Job 만들기
		JobDetail jobDetail = null;
		JobKey jobKey = new JobKey(batchSchdulVO.getBatchSchdulId());
		if(batchSchdulVO.getBatchProgrm() != null
			&& !"".equals(batchSchdulVO.getBatchProgrm())) {
			logger.debug("배치대상. getBatchProgrm " + batchSchdulVO.getBatchProgrm());
			jobDetail = JobBuilder.newJob(BatchShellScriptJob.class)
					.withIdentity(jobKey)
					.build();
	
			jobDetail.getJobDataMap().put("batchProgrm", batchSchdulVO.getBatchProgrm());
			jobDetail.getJobDataMap().put("paramtr", batchSchdulVO.getParamtr());

		} else if(batchSchdulVO.getBatchObject() != null
				&& !"".equals(batchSchdulVO.getBatchObject())) {
			logger.debug("배치대상. getBatchObject " + batchSchdulVO.getBatchObject());

			factory.setName(jobKey.getName());
			factory.setTargetBeanName(batchSchdulVO.getBatchObject());
			factory.setTargetMethod(batchSchdulVO.getBatchMethod());
			factory.setConcurrent(false);
			factory.afterPropertiesSet();
			
			jobDetail = (JobDetail) factory.getObject();

		} else {
			logger.debug("배치대상. None ");
			return;
		}
		
		// 데이터 전달
		jobDetail.getJobDataMap().put("batchOpertId", batchSchdulVO.getBatchOpertId());
		jobDetail.getJobDataMap().put("batchSchdulId", batchSchdulVO.getBatchSchdulId());

		// Trigger 만들기
		Trigger trigger = null;
		if( batchSchdulVO.getRepeatInterval() != 0L ) {
			logger.debug("배치스케줄. SimpleScheduleBuilder");
			trigger = TriggerBuilder.newTrigger()
				.withIdentity(jobKey.getName())
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInMilliseconds(batchSchdulVO.getRepeatInterval())
			            .repeatForever())
			            .startAt(new Date(System.currentTimeMillis() + batchSchdulVO.getStartDelay()))
			    .forJob(jobKey.getName())
				.build();
		} else {
			logger.debug("배치스케줄. CronScheduleBuilder");
			trigger = TriggerBuilder.newTrigger()
				.withIdentity(jobKey.getName())
				.withSchedule(CronScheduleBuilder.cronSchedule(batchSchdulVO.toCronExpression()))
				.forJob(jobKey.getName())
				.build();
		}	

		BatchJobListener listener = new BatchJobListener();
        listener.setBatchSchdulService(batchSchdulService);
        listener.setIdgenService(idgenService);

		sched.getListenerManager().addJobListener(listener, KeyMatcher.keyEquals(jobKey));
		
		try {
			if (updateMode) {
				sched.deleteJob(jobKey);
			}
			// 스케줄러에 추가하기
			sched.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// SchedulerException 이 발생하면 로그를 출력하고 다음 배치작업으로 넘어간다.
			// 트리거의 실행시각이 현재 시각보다 이전이면 SchedulerException이 발생한다.
			if (updateMode) {
				logger.error("스케줄러에 배치작업갱신할때 에러가 발생했습니다. 배치스케줄ID : " + batchSchdulVO.getBatchSchdulId() + ", 배치작업ID : " + batchSchdulVO.getBatchOpertId());
			} else {
				logger.error("스케줄러에 배치작업추가할때 에러가 발생했습니다. 배치스케줄ID : " + batchSchdulVO.getBatchSchdulId() + ", 배치작업ID : " + batchSchdulVO.getBatchOpertId());
			}
			logger.error("에러내용 : " + e.getMessage());
		}
	}

	/**
	 * 배치스케줄러에 batchSchdul 파라미터를 이용하여 Job , Trigger를 삭제한다.
	 * 
	 * @param batchSchdul
	 *            배치스케줄러에 삭제할 스케줄정보
	 * @exception Exception
	 *                Exception
	 */
	public void deleteBatchSchdul(BatchSchdulVO batchSchdulVO) throws Exception {
		JobKey jobKey = new JobKey(batchSchdulVO.getBatchSchdulId());
		try {
			// 스케줄러에서 기존Job, Trigger 삭제하기
			logger.debug("배치스케줄을 삭제합니다. 배치스케줄ID : " + batchSchdulVO.getBatchSchdulId());
			sched.deleteJob(jobKey);
		} catch (SchedulerException e) {
			// SchedulerException 이 발생하면 로그를 출력하고 다음 배치작업으로 넘어간다.
			// 트리거의 실행시각이 현재 시각보다 이전이면 SchedulerException이 발생한다.
			logger.error("스케줄러에 배치작업을 삭제할때 에러가 발생했습니다. 배치스케줄ID : " + batchSchdulVO.getBatchSchdulId() + ", 배치작업ID : " + batchSchdulVO.getBatchOpertId());
			logger.error("에러내용 : " + e.getMessage());
		}
	}

}
