package aramframework.mbl.com.mpa.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 사진에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 사진 정보를 조회하기 위해 필요한 정보를 관리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class PhotoVO extends BaseVO {

	// domain
	/** 순번     */
    private int sn;
    
    /** 회원 아이디     */
    private String mberId;
    
    /** 사진 제목     */
    private String photoSj;
    
    /** 사진 파일 아이디     */
    private String atchFileId;
    
    /** 생성일시     */
    private String creatDt;
    
    /** 수정일시     */
    private String updtDt;

	// domain
    /**
     * 순번을 가져온다.
     * @return int 순번
     */
    public int getSn() {
        return sn;
    }
    /**
     * 순번을 저장한다.
     * @param sn
     */
    public void setSn(int sn) {
        this.sn = sn;
    }

    /**
     * 회원 ID를 가져온다.
     * @return String 회원 아이디
     */
    public String getMberId() {
        return mberId;
    }
    /**
     * 회원 ID를 저장한다.
     * @param mberId
     */
    public void setMberId(String mberId) {
        this.mberId = mberId;
    }

    /**
     * 사진 제목을 가져온다.
     * @return String 사진 제목
     */
    public String getPhotoSj() {
        return photoSj;
    }
    /**
     * 사진 제목을 저장한다.
     * @param photoSj
     */
    public void setPhotoSj(String photoSj) {
        this.photoSj = photoSj;
    }

    /**
     * 사진 파일 ID를 가져온다.
     * @return String 사진 파일 아이디
     */
    public String getAtchFileId() {
        return atchFileId;
    }
    /**
     * 사진 파일 ID를 저장한다.
     * @param atchFileId
     */
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

    /**
     * 생성일시를 가져온다.
     * @return String 생성일시
     */
    public String getCreatDt() {
        return creatDt;
    }
    /**
     * 생성 일시를 저장한다.
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
     * 수정 일시를 저장한다.
     * @param updtDt
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }
    
}
