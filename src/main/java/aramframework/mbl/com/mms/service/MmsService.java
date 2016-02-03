package aramframework.mbl.com.mms.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import aramframework.mbl.com.mms.domain.AttachFileVO;
import aramframework.mbl.com.mms.domain.MmsTransInfoVO;

/**
 * 개요
 * - MMS서비스연계에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - MMS 전송, MMS 전송 결과 등록, 수정, 조회 기능과 MMS 첨부파일에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - MMS 첨부파일에 대한 조회기능은 목록, 상세조회로 구분된다.
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

public interface MmsService {

    /**
     * MMS 전송결과 목록을 조회하는 Service interface 메서드
     * @param mmsTransInfo
     * @return Map<String, Object> 전송결과 목록 리스트 
     */
    public Map<String, Object> selectMmsTransmissionResultList(MmsTransInfoVO mmsTransInfoVO);

    /**
     * MMS 전송정보를 등록하는 Service interface 메서드 
     * @param mmsTransInfo
    */
    public void insertMmsTransmissionResult(MmsTransInfoVO mmsTransInfoVO);
    
    /**
     * MMS 전송결과를 DB에 반영하는 Service interface 메서드
     * @param mmsTransInfo
    */
    public void updateMmsTransmissionResult(MmsTransInfoVO mmsTransInfoVO);

    /**
     * MMS를 전송하는 Service interface 메서드
     * @param mmsTransInfo
     * @return Map<String, Object> MMS 요청 결과, 메시지 ID
    */
    public Map<String, Object> sendMms(MmsTransInfoVO mmsTransInfoVO);
    
    /**
     * 전송한 MMS의 전송결과를 확인하는 Service interface 메서드 (Push 방식)
     *
     * @param request
     * @return Map<String, Object> MMS 전송결과
    */
    public Map<String, Object> receiveMmsTransmissionResult(HttpServletRequest request);
    
    /**
     * 전송한 MMS의 전송결과를 요청하는 Service interface 메서드
     *
     * @param messageId
     * @return Map<String, Object> MMS 전송결과
    */
    public Map<String, Object> getMmsTransmissionResult(String messageId);
    
    /**
     * 첨부파일 목록을 조회하는 Service interface 메서드
     * @param attachFileVO
     * @return Map<String, Object> 첨부파일 목록 리스트
    */
    public Map<String, Object> selectAttachFileList(AttachFileVO attachFileVO);

    /**
     * 첨부파일의 상세정보를 조회하는 Service interface 메서드
     * @param attachFileVO
     * @return AttachFile 첨부파일 정보
    */
    public AttachFileVO selectAttachFile(AttachFileVO attachFileVO);
    
    /**
     * 첨부파일의 정보를 등록하는 Service interface 메서드
     * @param attachFile
    */
    public void insertAttachFile(AttachFileVO attachFileVO);

    /**
     * 조회된 첨부파일의 정보를 수정하는 Service interface 메서드
     * @param attachFile
    */
    public void updateAttachFile(AttachFileVO attachFileVO);
    
    /**
     * 조회된 첨부파일의 정보를 삭제하는 Service interface 메서드
     * @param attachFile
    */
    public void deleteAttachFile(AttachFileVO attachFileVO); 
    
}