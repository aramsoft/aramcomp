package aramframework.com.sym.bat.schedule;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 배치쉘스크립트를 실행하는 Quartz Job 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BatchShellScriptJob implements Job {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {

		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();

		if (LOG.isDebugEnabled()) {
			LOG.debug("job[" + jobContext.getJobDetail().getName() + "] " + "Trigger이름 : " + jobContext.getTrigger().getName());
			LOG.debug("job[" + jobContext.getJobDetail().getName() + "] " + "BatchOpert이름 : " + dataMap.getString("batchOpertId"));
			LOG.debug("job[" + jobContext.getJobDetail().getName() + "] " + "BatchProgram이름 : " + dataMap.getString("batchProgrm"));
			LOG.debug("job[" + jobContext.getJobDetail().getName() + "] " + "Parameter이름 : " + dataMap.getString("paramtr"));
		}

		int result = executeProgram(dataMap.getString("batchProgrm"), dataMap.getString("paramtr"));

		// jobContext에 결과값을 저장한다.
		jobContext.setResult(result);
	}

	/**
	 * 시스템에서 특정 쉘프로그램을 실행한다.
	 * 
	 * @param batchProgrm
	 *            배치실행화일
	 * @param paramtr
	 *            배치실행화일에 전달될 파라미터
	 * @return 배치실행화일리턴값(integer)
	 * @exception Exception
	 */
	private int executeProgram(String batchProgrm, String paramtr) {

		int result = 0;
		try {
			Process p = null;
			String cmdStr = batchProgrm + " " + paramtr;
			p = Runtime.getRuntime().exec(cmdStr);
			p.waitFor();
			result = p.exitValue();
			LOG.debug("배치실행화일 - " + cmdStr + "실행완료, 결과값:" + result);
			// //프로세스 에러시 종료
			// if (p.exitValue() != 0) {
			//
			// }
			// //프로세스 실행 성공시 결과 확인
			// else {
			// }
		} catch (Exception e) {
			LOG.error("배치스크립트 실행 에러 : " + e.getMessage());
			LOG.debug(e.getMessage(), e);
		}

		return result;
	}

}
