package aramframework.mbl.com.geo.domain;

import aramframework.com.cmm.SearchVO;

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

public class GeoLocationVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 순번 */
    private int sn ;

    /** 회원ID */
    private String mberId ;
    
    /** 위도 */
    private String la ;

    /** 경도 */
    private String lo ;

    /** 건물명 */
    private String buldNm ;

    /** 전화번호 */
    private String telno ;

    /** 주소 */
    private String adres ;

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
     * 위도를 가져온다.
     * @return String 위도
     */
    public String getLa() {
        return la;
    }
    /**
     * 위도를 저장한다.
     * 
     * @param la
     */
    public void setLa(String la) {
        this.la = la;
    }
    
    /**
     * 경도를 가져온다.
     * @return String 경도
     */
    public String getLo() {
        return lo;
    }
    /**
     * 경도를 저장한다.
     * 
     * @param lo
     */
    public void setLo(String lo) {
        this.lo = lo;
    }

    /**
     * 건물명을 가져온다.
     * @return String 건물명
     */
    public String getBuldNm() {
        return buldNm;
    }
    /**
     * 건물명을 저장한다.
     * 
     * @param buldNm
     */
    public void setBuldNm(String buldNm) {
        this.buldNm = buldNm;
    }

    /**
     * 전화번호를 가져온다.
     * @return String 전화번호
     */
    public String getTelno() {
        return telno;
    }
    /**
     * 전화번호를 저장한다.
     * 
     * @param telno
     */
    public void setTelno(String telno) {
        this.telno = telno;
    }

    /**
     * 주소를 가져온다.
     * @return String 주소
     */
    public String getAdres() {
        return adres;
    }
    /**
     * 주소를 저장한다.
     * 
     * @param adres
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }
    
}