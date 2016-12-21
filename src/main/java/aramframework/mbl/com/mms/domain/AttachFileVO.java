package aramframework.mbl.com.mms.domain;

/**
 * 개요
 * - MMS 첨부파일에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - MMS 첨부파일 조회를 위해 필요한 정보를 관리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class AttachFileVO {

	/** 순번 */
    private int sn ;

    /** 회원ID */
    private String mberId ;

    /** 첨부파일 제목 */
    private String mmsAtchFileSj ;

    /** MMS 첨부파일ID */
    private String atchFileId ;
    
    /** MMS 첨부파일명 */
    private String atchFileNm ;

    /** 첨부파일타입 */
    private String atchFileType;
    
    /** 첨부파일 경로 */
    private String atchFilePath;
    
    /** 첨부파일 저장 파일명 */
    private String streFileNm;

    /** 생성일시 */
    private String creatDt ;

    /** 수정일시 */
    private String updtDt ;
    
    /**
     * 순번을 가져온다.
     * @return int 순번
     */
    public int getSn() {
        return sn;
    }

    /**
     * 순번을 저장한다.
     * 
     * @param sn
     */
    public void setSn(int sn) {
        this.sn = sn;
    }
 
    /**
     * 회원ID를 가져온다.
     * @return String 회원ID
     */
    public String getMberId() {
        return mberId;
    }
    
    /**
     * 회원ID를 저장한다.
     * 
     * @param mberId
     */
    public void setMberId(String mberId) {
        this.mberId = mberId;
    }

    /**
     * MMS 첨부파일제목을 가져온다.
     * @return String MMS 첨부파일제목
     */
    public String getMmsAtchFileSj() {
        return mmsAtchFileSj;
    }

    /**
     * MMS 첨부파일제목을 저장한다.
     * 
     * @param mmsAtchFileSj
     */
    public void setMmsAtchFileSj(String mmsAtchFileSj) {
        this.mmsAtchFileSj = mmsAtchFileSj;
    }

    /**
     * MMS 첨부파일ID를 가져온다.
     * @return String MMS 첨부파일ID
     */
    public String getAtchFileId() {
        return atchFileId;
    }

    /**
     * MMS 첨부파일ID를 저장한다.
     * 
     * @param atchFileId
     */
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }
    
    /**
     * MMS 첨부파일명을 가져온다.
     * @return String MMS 첨부파일명
     */
    public String getAtchFileNm() {
        return atchFileNm;
    }

    /**
     * MMS 첨부파일명을 저장한다.
     * 
     * @param atchFileNm
     */
    public void setAtchFileNm(String atchFileNm) {
        this.atchFileNm = atchFileNm;
    }

    /**
     * 첨부파일타입을 가져온다.
     * @return String 첨부파일타입
     */
    public String getAtchFileType() {
        return atchFileType;
    }

    /**
     * 첨부파일타입을 저장한다.
     * 
     * @param atchFileType
     */
    public void setAtchFileType(String atchFileType) {
        this.atchFileType = atchFileType;
    }

    /**
     * 첨부파일 경로를 가져온다.
     * @return String 첨부파일 경로
     */
    public String getAtchFilePath() {
        return atchFilePath;
    }

    /**
     * 첨부파일 경로를 저장한다.
     * 
     * @param atchFilePath
     */
    public void setAtchFilePath(String atchFilePath) {
        this.atchFilePath = atchFilePath;
    }
    
    /**
     * 첨부파일 저장 파일명을 가져온다.
     * @return String 첨부파일 저장 파일명
     */
    public String getStreFileNm() {
        return streFileNm;
    }

    /**
     * 첨부파일 저장 파일명을 저장한다.
     * 
     * @param streFileNm
     */
    public void setStreFileNm(String streFileNm) {
        this.streFileNm = streFileNm;
    }

    /**
     * 생성일시를 가져온다.
     * @return String 생성일시
     */
    public String getCreatDt() {
        return creatDt;
    }

    /**
     * 생성일시를 저장한다.
     * 
     * @param createDt
     */
    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
    }

    /**
     * 수정일시를 가져온다.
     * @return String 수정일시
     */
    public String getUpdtDt() {
        return updtDt;
    }

    /**
     * 수정일시를 저장한다.
     * 
     * @param updtDt
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }

}