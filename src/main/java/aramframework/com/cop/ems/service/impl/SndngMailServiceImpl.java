package aramframework.com.cop.ems.service.impl;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeUtility;

import noNamespace.SndngMailDocument;

import org.apache.commons.mail.EmailAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.constant.Globals;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cop.ems.service.AtchmnFileVO;
import aramframework.com.cop.ems.service.EgovMultiPartEmail;
import aramframework.com.cop.ems.service.SndngMailService;
import aramframework.com.cop.ems.service.SndngMailVO;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sim.service.FileTool;
import aramframework.com.utl.sim.service.XMLDoc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메일 솔루션과 연동해서 이용해서 메일을 보내는 서비스 구현 클래스
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

@Service("sndngMailService")
public class SndngMailServiceImpl extends EgovAbstractServiceImpl implements SndngMailService {
	
	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

//	첨부파일 미사용시
//	@Autowired 
//    private MailSender emsMailSender;
	
	@Autowired 
    private EgovMultiPartEmail email;
	
	@Autowired 
	private SndngMailMapper sndngMailMapper;
		
   /** Message ID Generation */
	@Autowired 
    private EgovIdGnrService mailMsgIdGnrService; 
    
	@Autowired 
	private FileMngUtil fileMngUtil; 

	protected static final Logger LOG = LoggerFactory.getLogger(SndngMailServiceImpl.class);

	/**
	 * 발송메일 목록을 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	public List<EgovMap> selectSndngMailList(SndngMailVO sndngMailVO) {
		return sndngMailMapper.selectSndngMailList(sndngMailVO);
	}

	/**
	 * 발송메일 총건수를 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	public int selectSndngMailListCnt(SndngMailVO sndngMailVO) {
		return sndngMailMapper.selectSndngMailListCnt(sndngMailVO);
	}

	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	public void deleteSndngMailList(SndngMailVO sndngMailVO) {

		// 1. 발송메일을 삭제한다.
		String[] sbuf = StringUtil.split(sndngMailVO.getMessageIdList(), ",");
		for (int i = 0; i < sbuf.length; i++) {
			SndngMailVO vo = new SndngMailVO();
			vo.setMssageId(sbuf[i]);
			deleteSndngMail(vo);
		}
	}
	
	/**
	 * 발송메일을 상세 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	public SndngMailVO selectSndngMail(SndngMailVO sndngMailVO) {
		SndngMailVO resultVo = sndngMailMapper.selectSndngMail(sndngMailVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, sndngMailVO); 
		return resultVo;
	}

	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	public void deleteSndngMail(SndngMailVO sndngMailVO) {

		// 1. 발송메일을 삭제한다.
		sndngMailMapper.deleteSndngMail(sndngMailVO);

		// 2. 발송요청XML파일을 삭제한다.
		String xmlFile = Globals.MAIL_REQUEST_PATH + sndngMailVO.getMssageId() + ".xml";
		FileTool.deleteFile(xmlFile);

		// 3. 첨부파일 삭제 ....
		fileMngUtil.deleteMultiFile(sndngMailVO.getAtchFileId());
	}

    /**
	 * 발송할 메일을 등록한다
	 * 
	 * @param sndngMailVO
	 */
    public boolean insertSndngMail(SndngMailVO sndngMailVO) {
    	
    	String recptnPersons = sndngMailVO.getRecptnPerson().replaceAll(" ", "");
    	String [] recptnPersonList = recptnPersons.split(";");

    	for (int j = 0; j < recptnPersonList.length; j++) {
    		
    		// 1-0.메세지ID를 생성한다.
        	String mssageId = null;
			try {
				mssageId = mailMsgIdGnrService.getNextStringId();
			} catch (FdlException e) {
				throw new RuntimeException(e);
			}

        	// 1-1.발송메일  데이터를 만든다.
        	SndngMailVO mailVO = new SndngMailVO();
        	mailVO.setMssageId(mssageId);
        	mailVO.setDsptchPerson(sndngMailVO.getDsptchPerson());
        	mailVO.setRecptnPerson(recptnPersonList[j]);
        	mailVO.setSj(sndngMailVO.getSj());
        	//mailVO.setEmailCn(EgovStringUtil.checkHtmlView(vo.getEmailCn()));
        	mailVO.setEmailCn(sndngMailVO.getEmailCn());
        	mailVO.setSndngResultCode("R");	// 발송결과 요청
        	
        	if(sndngMailVO.getAtchFileId() == null || sndngMailVO.getAtchFileId().equals("")){
        		mailVO.setAtchFileId(null);
        	}else{
        		mailVO.setAtchFileId(sndngMailVO.getAtchFileId());
        	}

        	// 1-3.발송메일을 등록한다.
        	sndngMailMapper.insertSndngMail(mailVO);
        	
        	// 1-4.메일을 발송한다.
        	boolean sendingMailResult = sndngMail(mailVO);
        	
        	if(!sendingMailResult) {
        		mailVO.setSndngResultCode("F");	// 발송결과 실패
            	sndngMailMapper.updateSndngMail(mailVO);	// 발송상태를 DB에 업데이트 한다.
        		return false;
        	}
        	
        	// 1-5.발송메일 요청XML 파일을 생성한다.
        	trnsmitXmlData(mailVO);
    	}
    	return true;
    }
    
    /**
	 * 발송할 메일을 XML파일로 만들어 저장한다.
	 * 
	 * @param sndngMailVO
	 */
    public boolean trnsmitXmlData(SndngMailVO sndngMailVO) {
    	
    	// 1. 첨부파일 목록 (원파일명, 저장파일명)
    	String orignlFileList = "";
    	String streFileList = "";
    	List<AtchmnFileVO> atchmnFileList = sndngMailMapper.selectAtchmnFileList(sndngMailVO);
    	for (int i = 0; i < atchmnFileList.size(); i++) {
			AtchmnFileVO fileVO = (AtchmnFileVO)atchmnFileList.get(i);
			String orignlFile = fileVO.getOrignlFileNm();
			String streFile = fileVO.getFileStreCours() + fileVO.getStreFileNm();
			orignlFileList += orignlFile + ";";
			streFileList += streFile + ";";
		}    	
    	
    	// 2. XML데이터를 만든다.
    	SndngMailDocument mailDoc;
    	SndngMailDocument.SndngMail mailElement;
    	mailDoc = SndngMailDocument.Factory.newInstance();
    	mailElement = mailDoc.addNewSndngMail();
    	mailElement.setMssageId(sndngMailVO.getMssageId());
    	mailElement.setDsptchPerson(sndngMailVO.getDsptchPerson());
    	mailElement.setRecptnPerson(sndngMailVO.getRecptnPerson());
    	mailElement.setSj(sndngMailVO.getSj());
    	mailElement.setEmailCn(sndngMailVO.getEmailCn());
    	mailElement.setSndngResultCode(sndngMailVO.getSndngResultCode());
    	mailElement.setOrignlFileList(orignlFileList);
    	mailElement.setStreFileList(streFileList);

    	// 3. XML파일로 저장한다.
//    	String xmlFile = Globals.MAIL_REQUEST_PATH + sndngMailVO.getMssageId() + ".xml";
    	String xmlFile = AramProperties.getProperty("Globals.MailRequestPath") + sndngMailVO.getMssageId() + ".xml";
        boolean result = false;
		try {
			result = XMLDoc.getClassToXML(mailDoc, xmlFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        if (result == true) {
        	recptnXmlData(xmlFile);
        }
    	return result;
    }

    /**
	 * 발송메일 발송결과 XML파일을 읽어 발송결과코드에 수정한다.
	 * 
	 * @param xmlFile
	 */
    public boolean recptnXmlData(String xmlFile) {
    	
    	// 1. XML파일에서 발송결과코드를 가져온다.
    	SndngMailDocument mailDoc = null;
		try {
			mailDoc = XMLDoc.getXMLToClass(xmlFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    	SndngMailDocument.SndngMail mailElement = mailDoc.getSndngMail();
    	SndngMailVO sndngMailVO = new SndngMailVO();
    	sndngMailVO.setMssageId(mailElement.getMssageId());
    	sndngMailVO.setSndngResultCode("C");	// 발송결과 완료
    	
    	// 2. DB에 업데이트 한다.
    	sndngMailMapper.updateSndngMail(sndngMailVO);
    	
    	return true;
    }
	
	/**
	 * 메일을 발송한다
	 * 
	 * @param sndngMailVO
	 */
	public boolean sndngMail(SndngMailVO sndngMailVO) {
		
		String recptnPerson = (sndngMailVO.getRecptnPerson() == null) ? "" : sndngMailVO.getRecptnPerson();		// 수신자
		String subject = (sndngMailVO.getSj() == null) ? "" : sndngMailVO.getSj();								// 메일제목
		String emailCn = (sndngMailVO.getEmailCn() == null) ? "" : sndngMailVO.getEmailCn();					// 메일내용
    	
    	List<AtchmnFileVO> atchmnFileList = sndngMailMapper.selectAtchmnFileList(sndngMailVO);
    	try{
          	
        	// 기본 메일 정보를 생성합니다
    		email.addTo(recptnPerson); 
    		email.setSubject(subject);
    		email.setMsg(emailCn);
   
	  		  // 첨부할 attachment 정보를 생성합니다
        	for (int i = 0; i < atchmnFileList.size(); i++) {
    			AtchmnFileVO fileVO = (AtchmnFileVO)atchmnFileList.get(i);
    			String orignlFile = fileVO.getOrignlFileNm();
    			String streFile = fileVO.getFileStreCours() + fileVO.getStreFileNm();

    			EmailAttachment attachment = new EmailAttachment();
  		  		attachment.setPath(streFile);	
  		  		attachment.setDisposition(EmailAttachment.ATTACHMENT);
  		  		attachment.setDescription("첨부파일입니다");
//		  		attachment.setName(orignlFile); 
//  		  	attachment.setName(new String(orignlFile.getBytes(), "EUC-KR")); 
		  		attachment.setName(MimeUtility.encodeText(orignlFile, "EUC-KR", "B")); 

  		  		// 생성한 attachment를 추가합니다
  		  		email.attach(attachment);
        	}	
   
  		  	// 메일을 전송합니다
        	email.send();
        	
        }catch(MailParseException ex){
        	LOG.error("Sending Mail Exception : " +  ex.getCause() + " [failure when parsing the message]");
        	return false;
        }catch(MailAuthenticationException ex){
        	LOG.error("Sending Mail Exception : " +  ex.getCause() + " [authentication failure]");
        	return false;
        }catch(MailSendException ex){
        	LOG.error("Sending Mail Exception : " +  ex.getCause() + " [failure when sending the message]");
        	return false;
        }catch(Exception ex){
        	LOG.error("Sending Mail Exception : " +  ex.getCause() + " [unknown Exception]");
        	return false;
        }
		
		return true;
	}

}
