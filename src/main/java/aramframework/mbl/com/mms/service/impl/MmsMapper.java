package aramframework.mbl.com.mms.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.mbl.com.mms.service.AttachFileVO;
import aramframework.mbl.com.mms.service.MmsTransInfoVO;

/**
 * 개요
 * - MMS서비스연계에 대한 DB상의 접근, 변경을 처리한다.
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

@Mapper("mmsMapper")
public interface MmsMapper {

    /**
     * MMS 전송 결과 목록을 조회한다.
     * @param attachFileVO
     * @return List<AttachFileVO> 첨부파일 리스트
     * @throws Exception
    */
	public List<AttachFileVO> selectMmsTransmissionResultList(MmsTransInfoVO mmsTransInfoVO);
    
   /**
     * MMS 전송 정보를 DB에 등록한다.
     * 
     * @param mmsTransInfo
     * @throws Exception
     */
    public void insertMmsTransmissionResult(MmsTransInfoVO mmsTransInfoVO);
    
    /**
     * MMS 전송 결과를 수정한다.
     * 
     * @param mmsTransInfo
     * @throws Exception
     */
    public void updateMmsTransmissionResult(MmsTransInfoVO mmsTransInfoVO);
    
    /**
     * MMS 전송 결과에 대한 목록 건수를 조회 한다.
     * @param attachFileVO
     * @return int 첨부파일 목록 건수
     * @throws Exception
    */
    public int selectMmsTransmissionResultListCnt(MmsTransInfoVO mmsTransInfoVO);
    
    /**
     * 첨부파일 목록을 조회한다.
     * @param attachFileVO
     * @return List<AttachFileVO> 첨부파일 리스트
     * @throws Exception
    */
	public List<AttachFileVO> selectAttachFileList(AttachFileVO attachFileVO);
    
    /**
     * 첨부파일에 대한 목록 건수를 조회 한다.
     * @param attachFileVO
     * @return int 첨부파일 목록 건수
     * @throws Exception
    */
    public int selectAttachFileListCnt(AttachFileVO attachFileVO);
    
    /**
     * 첨부파일의 정보를 상세조회한다.
     * @param attachFileVO
     * @return AttachFile 첨부파일 정보
     * @throws Exception
    */
    public AttachFileVO selectAttachFile(AttachFileVO attachFileVO);
    
    /**
     * 첨부파일의 정보를 DB에 등록한다.
     * 
     * @param attachFile
     * @throws Exception
     */
    public void insertAttachFile(AttachFileVO attachFileVO);
    
    /**
     * 조회된 첨부파일의 정보를 DB에서 수정한다.
     * 
     * @param attachFile
     * @throws Exception
     */
    public void updateAttachFile(AttachFileVO attachFileVO);
    
    /**
     * 조회된 첨부파일의 정보를 DB에서 삭제한다.
     * 
     * @param attachFile
     * @throws Exception
     */
    public void deleteAttachFile(AttachFileVO attachFileVO);
    
}