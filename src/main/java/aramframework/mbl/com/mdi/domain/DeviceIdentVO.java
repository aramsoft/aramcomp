package aramframework.mbl.com.mdi.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요
 * - 모바일기기식별에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 모바일기기 정보 조회를 위해 필요한 정보를 관리한다.
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

public class DeviceIdentVO extends SearchVO {

	private static final long serialVersionUID = 1L;
	/**
     * 순번
     */
    private int sn;
    /**
     * 회원ID
     */
    private String mberId;
    /**
     * User-Agent 정보
     */
    private String uagentInfo;
    /**
     * 브라우저코드
     */
    private String browserCode;
    /**
     * 브라우저명
     */
    private String browserNm;
    /**
     * 운영체제코드
     */
    private String osCode;
    /**
     * 운영체제명
     */
    private String osNm;
    /**
     * 최신구분코드
     */
    private String recentCode;
    /**
     * 최신구분명
     */
    private String recentNm;
    /**
     * 생성 일시
     */
    private String creatDt;
    /**
     * 수정일시
     */
    private String updtDt;

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
     * User-Agent 정보를 가져온다.
     * @return String User-Agent 정보
     */
    public String getUagentInfo() {
        return uagentInfo;
    }
    /**
     * User-Agent 정보를 저장한다.
     * @param uagentInfo
     */
    public void setUagentInfo(String uagentInfo) {
        this.uagentInfo = uagentInfo;
    }

    /**
     * 브라우저코드를 가져온다.
     * @return String 브라우저코드
     */
    public String getBrowserCode() {
        return browserCode;
    }
    /**
     * 브라우저코드를 저장한다.
     * @param browserCode
     */
    public void setBrowserCode(String browserCode) {
        this.browserCode = browserCode;
    }

    /**
     * 브라우저명을 가져온다.
     * @return String 브라우저명
     */
    public String getBrowserNm() {
        return browserNm;
    }
    /**
     * 브라우저명을 저장한다.
     * @param browserNm
     */
    public void setBrowserNm(String browserNm) {
        this.browserNm = browserNm;
    }

    /**
     * 운영체제코드를 가져온다.
     * @return String 운영체제코드
     */
    public String getOsCode() {
        return osCode;
    }
    /**
     * 운영체제코드를 저장한다.
     * @param osCode
     */
    public void setOsCode(String osCode) {
        this.osCode = osCode;
    }

    /**
     * 운영체제명을 가져온다.
     * @return String 운영체제명
     */
    public String getOsNm() {
        return osNm;
    }
    /**
     * 운영체제명을 저장한다.
     * @param osNm
     */
    public void setOsNm(String osNm) {
        this.osNm = osNm;
    }

    /**
     * 최신구분코드를 가져온다.
     * @return String 최신구분코드
     */
    public String getRecentCode() {
        return recentCode;
    }
    /**
     * 최신구분코드를 저장한다.
     * @param recentCode
     */
    public void setRecentCode(String recentCode) {
        this.recentCode = recentCode;
    }

    /**
     * 최신구분명을 가져온다.
     * @return String 최신구분명
     */
    public String getRecentNm() {
        return recentNm;
    }
    /**
     * 최신구분명을 저장한다.
     * @param recentNm
     */
    public void setRecentNm(String recentNm) {
        this.recentNm = recentNm;
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
     * 수정일시를 저장한다.
     * @param updtDt
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }
    
}
