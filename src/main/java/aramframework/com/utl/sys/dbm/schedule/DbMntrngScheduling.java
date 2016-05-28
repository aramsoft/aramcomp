package aramframework.com.utl.sys.dbm.schedule;

import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sys.dbm.domain.DbMntrngLogVO;
import aramframework.com.utl.sys.dbm.domain.DbMntrngResult;
import aramframework.com.utl.sys.dbm.domain.DbMntrngVO;
import aramframework.com.utl.sys.dbm.service.DbMntrngService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * DB서비스모니터링을 위한 스케쥴링 클래스
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

@Component("dbMntrngScheduling")
public class DbMntrngScheduling {

	@Autowired
	private DbMntrngService dbMntrngService;

	@Autowired
	private MailSender mntrngMailSender;

	@Autowired
	private SimpleMailMessage mntrngMessage;

	@Autowired
	DataSource dataSource;
	
	/** ID Generation */
	@Autowired
	private EgovIdGnrService dbMntrngLogIdGnrService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * DB서비스 모니터링를 수행한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int monitorDb() throws Exception {
		int batchResult = 0;
		
		// 모니터링 대상 정보 읽어들이기~~~
		List<EgovMap> targetList = null;
		DbMntrngVO dbMntrngVO = new DbMntrngVO();
		// 모니터링 대상 검색 조건 초기화
		dbMntrngVO.getSearchVO().setPageIndex(1);
		dbMntrngVO.getSearchVO().setFirstIndex(0);
		dbMntrngVO.getSearchVO().setRecordPerPage(RECORD_COUNT_PER_PAGE);
		targetList = dbMntrngService.selectDbMntrngList(dbMntrngVO);
		if (LOG.isDebugEnabled()) {
			LOG.debug("조회조건 " + dbMntrngVO);
			LOG.debug("Result 건수 : " + targetList.size());
		}
		// 서비스체크 함수 호출.
		Iterator<EgovMap> iter = targetList.iterator();
		EgovMap target = null;
		DbMntrngResult result = null;
		DbMntrngVO dbMntrng = null;
		DbMntrngLogVO dbMntrngLog = null;
		while (iter.hasNext()) {
			target = iter.next();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Data : " + target);
			}
			dbMntrng = new DbMntrngVO();
			dbMntrng.setDataSourcNm((String)target.get("dataSourcNm"));
			dbMntrng.setServerNm((String)target.get("serverNm"));
			dbMntrng.setDbmsKind((String)target.get("dbmsKind"));
			dbMntrng.setCeckSql((String)target.get("ceckSql"));
			dbMntrng.setMngrNm((String)target.get("mngrNm"));
			dbMntrng.setMngrEmailAddr((String)target.get("mngrEmailAddr"));
			dbMntrng.setMntrngSttus((String)target.get("mntrngSttus"));
			
			// 서비스 체크 수행.
			result = DbMntrngChecker.check(dataSource, dbMntrng.getDataSourcNm(), dbMntrng.getCeckSql());

			// 대상테이블에 DB에 결과값 저장
			if (result.isNrmltAt()) {
				dbMntrng.setMntrngSttus("01");
			} else {
				dbMntrng.setMntrngSttus("02");
			}
			dbMntrng.setLastUpdusrId("SYSTEM");
			dbMntrngService.updateDbMntrng(dbMntrng);

			// 로그테이블 추가저장.
			dbMntrngLog = new DbMntrngLogVO();
			dbMntrngLog.setLogId(dbMntrngLogIdGnrService.getNextStringId());
			dbMntrngLog.setDataSourcNm(dbMntrng.getDataSourcNm());
			dbMntrngLog.setServerNm(dbMntrng.getServerNm());
			dbMntrngLog.setDbmsKind(dbMntrng.getDbmsKind());
			dbMntrngLog.setCeckSql(dbMntrng.getCeckSql());
			dbMntrngLog.setMngrNm(dbMntrng.getMngrNm());
			dbMntrngLog.setMngrEmailAddr(dbMntrng.getMngrEmailAddr());
			dbMntrngLog.setMntrngSttus(dbMntrng.getMntrngSttus());
			dbMntrngLog.setFrstRegisterId(dbMntrng.getLastUpdusrId());
			if (result.getCause() != null) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("에러메시지: " + result.getCause().getMessage());
				}

				if (result.getCause().getMessage() != null) {
					dbMntrngLog.setLogInfo(result.getCause().getMessage());
				} else {
					dbMntrngLog.setLogInfo("");
				}

			} else {
				dbMntrngLog.setLogInfo("");
			}
			dbMntrngService.insertDbMntrngLog(dbMntrngLog);

			// 모니터링시각을 가져오기위해 로그정보를 가져온다.
			dbMntrngLog = dbMntrngService.selectDbMntrngLog(dbMntrngLog);
			if (LOG.isDebugEnabled()) {
				LOG.debug("DB서비스로그 Data : " + dbMntrngLog);
			}
			// email 전송.
			if (!result.isNrmltAt()) {
				batchResult = -1;
				sendEmail(dbMntrngLog);
			}
		}
		return batchResult;
	}

	/**
	 * 이메일을 전송한다.
	 * 
	 * @param mntrngLog
	 *            모니터링 대상정보
	 * @return
	 * 
	 */
	private void sendEmail(DbMntrngLogVO mntrngLog) {
		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(mntrngLog.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = StringUtil.replace(subject, "{모니터링종류}", "DB서비스모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = StringUtil.replace(text, "{모니터링종류}", "DB서비스모니터링");
		errorContents = "데이타소스명 : " + mntrngLog.getDataSourcNm() + "\n";
		errorContents = errorContents + "서버명  : " + mntrngLog.getServerNm() + "\n";
		errorContents = errorContents + "DBMS종류 : " + mntrngLog.getDbmsKindNm() + "\n";
		errorContents = errorContents + "체크SQL : " + mntrngLog.getCeckSql() + "\n";
		errorContents = errorContents + "상태 : " + mntrngLog.getMntrngSttusNm() + "\n";
		errorContents = errorContents + "모니터링시각 : " + mntrngLog.getCreatDt() + "\n";
		errorContents = errorContents + "에러메시지 : " + mntrngLog.getLogInfo() + "\n";
		text = StringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

//		this.mntrngMailSender.send(msg);
	}

}
