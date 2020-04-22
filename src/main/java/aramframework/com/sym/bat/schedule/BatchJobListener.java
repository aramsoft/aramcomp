package aramframework.com.sym.bat.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.sym.bat.domain.BatchResultVO;
import aramframework.com.sym.bat.service.BatchSchdulService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 배치작업의 실행시작, 완료를 저장하는 Quartz JobListener 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BatchJobListener implements JobListener {

	/**
	 * batchSchdulService
	 */
	private BatchSchdulService batchSchdulService;

	/** ID Generation */
	private EgovIdGnrService idgenService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 배치스케줄 서비스를 설정한다.
	 * 
	 * @param batchSchdulService
	 *            the batchSchdulService to set
	 */
	public void setBatchSchdulService(BatchSchdulService batchSchdulService) {
		this.batchSchdulService = batchSchdulService;
	}

	/**
	 * 배치결과ID 생성서비스
	 * 
	 * @param idgenService
	 *            the idgenService to set
	 */
	public void setIdgenService(EgovIdGnrService idgenService) {
		this.idgenService = idgenService;
	}

	/**
	 * Job Listener 이름을 리턴한다.
	 * 
	 * @see org.quartz.JobListener#getName()
	 */
	public String getName() {
		return this.getClass().getName();
	}

	/**
	 * Batch 작업을 실행하기전에 Batch결과 '수행중'상태로 저장한다.
	 * 
	 * @param jobContext
	 *            JobExecutionContext
	 * @see org.quartz.JobListener#jobToBeExecuted(JobExecutionContext
	 *      jobContext)
	 */
	public void jobToBeExecuted(JobExecutionContext jobContext) {
		LOG.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "jobToBeExecuted ");
		BatchResultVO batchResult = new BatchResultVO();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			batchResult.setBatchResultId(idgenService.getNextStringId());
			batchResult.setBatchSchdulId(dataMap.getString("batchSchdulId"));
			batchResult.setBatchOpertId(dataMap.getString("batchOpertId"));
			batchResult.setSttus("03"); // 상태는 수행중
			batchResult.setErrorInfo("");

			String executBeginTimeStr = null;
			Date executBeginTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executBeginTimeStr = formatter.format(executBeginTime);
			batchResult.setExecutBeginTime(executBeginTimeStr);
			batchResult.setFrstRegisterId("SYSTEM");

			batchSchdulService.insertBatchResult(batchResult);

			// 저장이 이상없이 완료되면 datamap에 배치결과ID를 저장한다.
			dataMap.put("batchResultId", batchResult.getBatchResultId());
		} catch (Exception e) {
			LOG.error("배치스케줄ID : " + batchResult.getBatchSchdulId() 
			      + ", 배치작업ID : " + batchResult.getBatchOpertId() 
			      + ", 배치결과저장(insert) 에러 : " + e.getMessage());
			LOG.debug(e.getMessage(), e);
		}

	}

	/**
	 * Batch 작업을 완료한후 Batch결과 '완료'상태로 저장한다.
	 * 
	 * @param jobContext
	 *            JobExecutionContext
	 * @see org.quartz.JobListener#jobWasExecuted(JobExecutionContext
	 *      jobContext)
	 */
	public void jobWasExecuted(JobExecutionContext jobContext, JobExecutionException jee) {
		LOG.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "jobWasExecuted ");
		LOG.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "수행시간 : " + jobContext.getFireTime() + ", " + jobContext.getJobRunTime());

		int jobResult = 99;
		BatchResultVO batchResult = new BatchResultVO();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			batchResult.setBatchResultId(dataMap.getString("batchResultId"));
			batchResult.setBatchSchdulId(dataMap.getString("batchSchdulId"));
			batchResult.setBatchOpertId(dataMap.getString("batchOpertId"));
			if (jobContext.getResult() != null) {
				jobResult = (Integer) jobContext.getResult();
			}
			if (jobResult == 0) {
				// 배치작업 성공.
				batchResult.setSttus("01");
				batchResult.setErrorInfo("");
			} else {
				// 배치작업이 0이 아닌값을 리턴하면 에러 상황임.
				batchResult.setSttus("02");
				batchResult.setErrorInfo("배치작업이 결과값 [" + jobResult + "]를 리턴했습니다. \n" + "배치프로그램 [" + dataMap.getString("batchOpertNm") + "]의 로그를 확인하세요");
			}
			// 수행중 exception이 발생한 경우
			if (jee != null) {
				LOG.error("JobExecutionException 발생 : " + jee);
				batchResult.setSttus("02");
				String errorInfo = batchResult.getErrorInfo();
				batchResult.setErrorInfo(errorInfo + "\n" + "JobExecutionException 발생 : " + jee);
			}

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			batchResult.setExecutEndTime(executEndTimeStr);
			batchResult.setLastUpdusrId("SYSTEM");

			batchSchdulService.updateBatchResult(batchResult);

			// 저장이 이상없이 완료되면 datamap에 배치결과ID를 저장한다.
			dataMap.put("batchResultId", batchResult.getBatchResultId());
		} catch (Exception e) {
			LOG.error("배치결과ID : " + batchResult.getBatchResultId() 
			      + ", 배치스케줄ID : " + batchResult.getBatchSchdulId() 
			      + ", 배치작업ID : " + batchResult.getBatchOpertId() 
			      + ", 배치결과저장(update) 에러 : " + e.getMessage());
			LOG.debug(e.getMessage(), e);
		}
	}

	/**
	 * Batch 작업을 실행한 후에 Batch결과 '에러'상태로 저장한다.
	 * 
	 * @param jobContext
	 *            JobExecutionContext
	 * 
	 * @see org.quartz.JobListener#jobExecutionVetoed(JobExecutionContext
	 *      jobContext)
	 */
	public void jobExecutionVetoed(JobExecutionContext jobContext) {
		LOG.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "jobExecutionVetoed ");

		BatchResultVO batchResult = new BatchResultVO();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			batchResult.setBatchResultId(dataMap.getString("batchResultId"));
			batchResult.setBatchSchdulId(dataMap.getString("batchSchdulId"));
			batchResult.setBatchOpertId(dataMap.getString("batchOpertId"));
			// 스케줄러가 배치작업을 실행하지 않음.
			batchResult.setSttus("02");
			batchResult.setErrorInfo("스케줄러가 배치작업을 실행하지 않았습니다(jobExecutionVetoed 이벤트). 스케줄러 로그를 확인하세요");

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			batchResult.setExecutEndTime(executEndTimeStr);
			batchResult.setLastUpdusrId("SYSTEM");

			batchSchdulService.updateBatchResult(batchResult);

			// 저장이 이상없이 완료되면 datamap에 배치결과ID를 저장한다.
			dataMap.put("batchResultId", batchResult.getBatchResultId());
		} catch (Exception e) {
			LOG.error("배치결과ID : " + batchResult.getBatchResultId() 
				  + ", 배치스케줄ID : " + batchResult.getBatchSchdulId() 
				  + ", 배치작업ID : "	+ batchResult.getBatchOpertId() 
				  + ", 배치결과저장(update) 에러 : " + e.getMessage());
			LOG.debug(e.getMessage(), e);
		}

	}

}
