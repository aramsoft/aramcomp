package aramframework.mbl.com.geo.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 위치정보연계에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 위치정보연계 조회를 위해 필요한 정보를 관리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class GeoLocationVO extends BaseVO {

	// domain	
	/** 순번 */
    private int sn ;

    /** 회원ID */
    private String mberId ;
    
    /** 건물명 */
    private String buldNm ;

    /** 위도 */
    private String la ;

    /** 경도 */
    private String lo ;

    /** 전화번호 */
    private String telno ;

    /** 주소 */
    private String adres ;

    // domain
    /**
     * 순번을 가져온다.
     * 
     * @return  	순번
     */
    public int getSn() {
        return sn;
    }
    /**
     * 순번을 저장한다.
     * 
     * @param 	sn	순번
     */
    public void setSn(int sn) {
        this.sn = sn;
    }

    /**
     * 회원ID를 가져온다.
     * 
     * @return 		회원ID
     */
    public String getMberId() {
        return mberId;
    }
    /**
     * 회원ID를 저장한다.
     * 
     * @param 	mberId	회원ID
     */
    public void setMberId(String mberId) {
        this.mberId = mberId;
    }
   
    /**
     * 건물명을 가져온다.
     * 
     * @return 		건물명
     */
    public String getBuldNm() {
        return buldNm;
    }
    /**
     * 건물명을 저장한다.
     * 
     * @param 	buldNm	건물명
     */
    public void setBuldNm(String buldNm) {
        this.buldNm = buldNm;
    }

    /**
     * 위도를 가져온다.
     * 
     * @return 		위도
     */
    public String getLa() {
        return la;
    }
    /**
     * 위도를 저장한다.
     * 
     * @param 	la	위도
     */
    public void setLa(String la) {
        this.la = la;
    }
    
    /**
     * 경도를 가져온다.
     * 
     * @return 		경도
     */
    public String getLo() {
        return lo;
    }
    /**
     * 경도를 저장한다.
     * 
     * @param 	lo	경도
     */
    public void setLo(String lo) {
        this.lo = lo;
    }

    /**
     * 전화번호를 가져온다.
     * 
     * @return		전화번호
     */
    public String getTelno() {
        return telno;
    }
    /**
     * 전화번호를 저장한다.
     * 
     * @param 	telno	전화번호
     */
    public void setTelno(String telno) {
        this.telno = telno;
    }

    /**
     * 주소를 가져온다.
     * @return 		주소
     */
    public String getAdres() {
        return adres;
    }
    /**
     * 주소를 저장한다.
     * 
     * @param 	adres	주소
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }
    
}