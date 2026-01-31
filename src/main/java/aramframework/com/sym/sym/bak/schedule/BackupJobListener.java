package aramframework.com.sym.sym.bak.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.sym.sym.bak.domain.BackupResultVO;
import aramframework.com.sym.sym.bak.service.BackupOpertService;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 백업작업의 실행시작, 완료를 저장하는 Quartz JobListener 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BackupJobListener implements JobListener {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * egovBackupOpertService
	 */
	private BackupOpertService backupOpertService;

	/** ID Generation */
	private EgovIdGnrService idgenService;

	/**
	 * 백업작업 서비스를 설정한다.
	 * 
	 * @param egovBackupOpertService
	 *            the egovBackupOpertService to set
	 */
	public void setBackupOpertService(BackupOpertService backupOpertService) {
		this.backupOpertService = backupOpertService;
	}

	/**
	 * 백업결과ID 생성서비스
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
	 * 백업 작업을 실행하기전에 백업결과 '수행중'상태로 저장한다.
	 * 
	 * @param jobContext
	 *            JobExecutionContext
	 * @see org.quartz.JobListener#jobToBeExecuted(JobExecutionContext
	 *      jobContext)
	 */
	public void jobToBeExecuted(JobExecutionContext jobContext) {
		logger.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "jobToBeExecuted ");
	
 		BackupResultVO backupResult = new BackupResultVO();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			backupResult.setBackupResultId(idgenService.getNextStringId());
			backupResult.setBackupOpertId(dataMap.getString("backupOpertId"));
			backupResult.setBackupFile(dataMap.getString("backupFile"));
			backupResult.setSttus("03"); // 상태는 수행중
			backupResult.setErrorInfo("");

			String executBeginTimeStr = null;
			Date executBeginTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executBeginTimeStr = formatter.format(executBeginTime);
			backupResult.setExecutBeginTime(executBeginTimeStr);
			backupResult.setFrstRegisterId("SYSTEM");

			backupOpertService.insertBackupResult(backupResult);

			// 저장이 이상없이 완료되면 datamap에 배치결과ID를 저장한다.
			dataMap.put("backupResultId", backupResult.getBackupResultId());
		} catch (Exception e) {
			logger.error("백업작업ID : " + backupResult.getBackupOpertId() 
			      + ", 백업결과저장(insert) 에러 : " + e.getMessage());
			logger.debug(e.getMessage(), e);
		}

	}

	/**
	 * 백업 작업을 완료한후 백업결과 '완료'상태로 저장한다.
	 * 
	 * @param jobContext
	 *            JobExecutionContext
	 * @see org.quartz.JobListener#jobWasExecuted(JobExecutionContext
	 *      jobContext)
	 */
	public void jobWasExecuted(JobExecutionContext jobContext, JobExecutionException jee) {
		logger.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "jobWasExecuted ");
		logger.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "수행시간 : " + jobContext.getFireTime() + "," + jobContext.getJobRunTime());

		boolean jobResult = false;
		BackupResultVO backupResult = new BackupResultVO();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			backupResult.setBackupResultId(dataMap.getString("backupResultId"));
			backupResult.setBackupOpertId(dataMap.getString("backupOpertId"));
			if (jobContext.getResult() != null) {
				jobResult = (Boolean) jobContext.getResult();
			}
			if (jobResult) {
				// 백업작업 성공.
				backupResult.setSttus("01");
				backupResult.setErrorInfo("");
				backupResult.setBackupFile(dataMap.getString("backupFile"));
			} else {
				// 백업작업이 true가 아닌값을 리턴하면 에러 상황임.
				backupResult.setSttus("02");
				backupResult.setErrorInfo("백업작업이 실패했습니다. \n" 
										+ "백업작업 [" + dataMap.getString("backupOpertId") + "]의 로그를 확인하세요");
			}
			// 수행중 exception이 발생한 경우
			if (jee != null) {
				logger.error("JobExecutionException 발생 : " + jee);
				backupResult.setSttus("02");
				String errorInfo = backupResult.getErrorInfo();
				backupResult.setErrorInfo(errorInfo + "\n" + "JobExecutionException 발생 : " + jee);
			}

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			backupResult.setExecutEndTime(executEndTimeStr);
			backupResult.setLastUpdusrId("SYSTEM");

			if (logger.isDebugEnabled()) {
				logger.debug("insert BackupResult Data : " + backupResult);
				logger.debug("backupFile : " + dataMap.getString("backupFile"));
			}
			backupOpertService.updateBackupResult(backupResult);

			// 저장이 이상없이 완료되면 datamap에 배치결과ID를 저장한다.
			dataMap.put("backupResultId", backupResult.getBackupResultId());
		} catch (Exception e) {
			logger.error("백업결과ID : " + backupResult.getBackupResultId() 
				  + ", 백업작업ID : " + backupResult.getBackupOpertId() 
				  + ", 백업결과저장(update) 에러 : " + e.getMessage());
			logger.debug(e.getMessage(), e);
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
		logger.debug("job[" + jobContext.getJobDetail().getKey().getName() + "] " + "jobExecutionVetoed ");

		BackupResultVO backupResult = new BackupResultVO();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			backupResult.setBackupResultId(dataMap.getString("backupResultId"));
			backupResult.setBackupOpertId(dataMap.getString("backupOpertId"));
			backupResult.setBackupFile(dataMap.getString("backupFile"));
			// 스케줄러가 배치작업을 실행하지 않음.
			backupResult.setSttus("02");
			backupResult.setErrorInfo("스케줄러가 배치작업을 실행하지 않았습니다(jobExecutionVetoed 이벤트). 스케줄러 로그를 확인하세요");

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			backupResult.setExecutEndTime(executEndTimeStr);
			backupResult.setLastUpdusrId("SYSTEM");

			backupOpertService.updateBackupResult(backupResult);

			// 저장이 이상없이 완료되면 datamap에 배치결과ID를 저장한다.
			dataMap.put("backupResultId", backupResult.getBackupResultId());
		} catch (Exception e) {
			logger.error("백업결과ID : " + backupResult.getBackupResultId() 
				  + ", 백업작업ID : " + backupResult.getBackupOpertId() 
				  + ", 백업결과저장(update) 에러 : " + e.getMessage());
			logger.debug(e.getMessage(), e);
		}
	}

}
