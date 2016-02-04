package aramframework.com.cmm.config.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.sym.log.lgm.domain.SysLogVO;
import aramframework.com.sym.log.lgm.service.SysLogService;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 시스템 로그생성을 위한 Advice 클래스
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

public class SysLogAdvice {

	@Autowired 
	private SysLogService sysLogService;

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 insert로 시작되는 Method
	 * 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logInsert(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLogVO sysLogVO = new SysLogVO();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "C";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

			/* Authenticated */
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			if( loginVO != null ) {
				uniqId = loginVO.getUniqId();
				ip = loginVO.getIp();
			}
			
			if( !"127.0.0.1".equals(ip)) {
				sysLogVO.setSrvcNm(className);
				sysLogVO.setMethodNm(methodName);
				sysLogVO.setProcessSeCode(processSeCode);
				sysLogVO.setProcessTime(processTime);
				sysLogVO.setRqesterId(uniqId);
				sysLogVO.setRqesterIp(ip);
	
				sysLogService.logInsertSysLog(sysLogVO);
			}	
		}

	}

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 update로 시작되는 Method
	 * 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLogVO sysLog = new SysLogVO();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "U";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

			/* Authenticated */
			Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
			if (isAuthenticated.booleanValue()) {
				LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
				uniqId = loginVO.getUniqId();
				ip = loginVO.getIp();
			}
			if( !"127.0.0.1".equals(ip)) {
				sysLog.setSrvcNm(className);
				sysLog.setMethodNm(methodName);
				sysLog.setProcessSeCode(processSeCode);
				sysLog.setProcessTime(processTime);
				sysLog.setRqesterId(uniqId);
				sysLog.setRqesterIp(ip);
	
				sysLogService.logInsertSysLog(sysLog);
			}
		}

	}

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 delete로 시작되는 Method
	 * 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logDelete(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLogVO sysLog = new SysLogVO();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "D";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

			/* Authenticated */
			Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
			if (isAuthenticated.booleanValue()) {
				LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
				uniqId = loginVO.getUniqId();
				ip = loginVO.getIp();
			}
			if( !"127.0.0.1".equals(ip)) {
				sysLog.setSrvcNm(className);
				sysLog.setMethodNm(methodName);
				sysLog.setProcessSeCode(processSeCode);
				sysLog.setProcessTime(processTime);
				sysLog.setRqesterId(uniqId);
				sysLog.setRqesterIp(ip);
	
				sysLogService.logInsertSysLog(sysLog);
			}	
		}

	}

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 select로 시작되는 Method
	 * 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logSelect(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLogVO sysLog = new SysLogVO();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "R";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

			/* Authenticated */
			Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
			if (isAuthenticated.booleanValue()) {
				LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
				uniqId = loginVO.getUniqId();
				ip = loginVO.getIp();
			}
			if( !"127.0.0.1".equals(ip)) {
				sysLog.setSrvcNm(className);
				sysLog.setMethodNm(methodName);
				sysLog.setProcessSeCode(processSeCode);
				sysLog.setProcessTime(processTime);
				sysLog.setRqesterId(uniqId);
				sysLog.setRqesterIp(ip);
	
				sysLogService.logInsertSysLog(sysLog);
			}	
		}

	}

}
