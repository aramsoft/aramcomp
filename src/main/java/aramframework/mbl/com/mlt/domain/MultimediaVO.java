package aramframework.mbl.com.mlt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 멀티미디어에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 멀티미디어 정보를 조회하기 위해 필요한 정보를 관리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class MultimediaVO extends BaseVO {

	// domain
	/** 순번     */
    private int sn;
    
    /** 회원ID     */
    private String mberId;
    
    /** 멀티미디어파일(첨부파일)ID     */
    private String atchFileId;
    
   /** 멀티미디어파일(첨부파일) 구분 코드     */
    private String mltmdCode;
    
    /** 멀티미디어파일(첨부파일) 구분명 - 동영상파일, 음악파일..     */
    private String mltmdNm;
    
    /** 멀티미디어 제목     */
    private String mltmdSj;
    
    /** 멀티미디어 지원 브라우저명     */
    private String browserNm;
    
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
     * 회원ID를 가져온다.
     * @return String 회원ID
     */
    public String getMberId() {
        return mberId;
    }
    /**
     * 회원ID를 저장한다.
     * @param mberId
     */
    public void setMberId(String mberId) {
        this.mberId = mberId;
    }

    /**
     * 멀티미디어파일(첨부파일)ID를 획득한다. * @return String 첨부파일 ID
     */
    public String getAtchFileId() {
        return atchFileId;
    }
    /**
     * 멀티미디어파일(첨부파일)ID를 할당한다.
     * @param atchFileId
     */
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

   /**
     * 멀티미디어 첨부파일 코드를 획득한다.
     */
    public String getMltmdCode() {
        return mltmdCode;
    }
    /**
     * 멀티미디어 첨부파일 코드를 할당한다.
     * @param mltmdCode
     */
    public void setMltmdCode(String mltmdCode) {
        this.mltmdCode = mltmdCode;
    }

    /**
     * 멀티미디어 첨부파일 구분 명을 획득한다. - 동영상파일, 음악파일..
     */
    public String getMltmdNm() {
        return mltmdNm;
    }
    /**
     * 멀티미디어 첨부파일 구분명을 할당한다. - 동영상파일, 음악파일..
     * @param mltmdNm
     */
    public void setMltmdNm(String mltmdNm) {
        this.mltmdNm = mltmdNm;
    }

    /**
     * 멀티미디어 제목을 가져온다.
     * @return String 멀티미디어 제목
     */
    public String getMltmdSj() {
        return mltmdSj;
    }
    /**
     * 멀티미디어 제목을 저장한다.
     * @param mltmdSj
     */
    public void setMltmdSj(String mltmdSj) {
        this.mltmdSj = mltmdSj;
    }

    /**
     * 멀티미디어 지원 브라우저명을 가져온다.
     * @return String 멀티미디어 지원 브라우저명
     */
    public String getBrowserNm() {
        return browserNm;
    }
    /**
     * 멀티미디어 지원 브라우저명을 저장한다.
     * @param browserNm
     */
    public void setBrowserNm(String browserNm) {
        this.browserNm = browserNm;
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
     * @param creatDt
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
     * @param updtDt
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }
    
}
