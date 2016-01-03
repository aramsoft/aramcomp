package aramframework.com.cop.cmy.service;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.WebUtil;

/**
 * 커뮤니티 관리를 위한 VO 클래스
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

public class CommunityVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 커뮤니티 아이디 */
	private String cmmntyId = "";

	/** 커뮤니티 명 */
	private String cmmntyNm = "";

	/** 커뮤니티 소개 */
	private String cmmntyIntrcn = "";

	/** 등록구분코드 */
	private String registSeCode = "";

	/** 등록구분 코드명 */
	private String registSeCodeNm = "";

	/** 템플릿 아이디 */
	private String tmplatId = "";

	/** 템플릿 이름 */
	private String tmplatNm = "";

	/** 사용 여부 */
	private String useAt = "";

	/** 회원인증 여부 */
	private String memberAt = "";

	/** Home URL */
	private String homeUrl = "";

	/** 제공 URL */
	private String provdUrl = "";

	/** 제공 URL2 */
	private String provdUrl2 = "";

	/** 커뮤니티 로고 이미지 */
	private byte[] cmmntyLogoImage;

	/**  */
	private String additionalInfo;
	
	/* temp */
	private String emplyrId = "";
	
	/* for jsp only */
	private List<EgovMap> clubList = null;	
	private List<EgovMap> topMenuList = null;
	private List<EgovMap> mgrMenuList = null;
	private List<EgovMap> subMenuList = null;
	
	/** cmmntyId 	 */ 
	public String getCmmntyId() {
		return cmmntyId;
	}
	public void setCmmntyId(String cmmntyId) {
		this.cmmntyId = cmmntyId;
		if(cmmntyId.length() != 0)
			this.pathId = WebUtil.getPathId(cmmntyId);
	}

	/** cmmntyIntrcn 	 */ 
	public String getCmmntyIntrcn() {
		return cmmntyIntrcn;
	}
	public void setCmmntyIntrcn(String cmmntyIntrcn) {
		this.cmmntyIntrcn = cmmntyIntrcn;
	}

	/** cmmntyNm 	 */ 
	public String getCmmntyNm() {
		return cmmntyNm;
	}
	public void setCmmntyNm(String cmmntyNm) {
		this.cmmntyNm = cmmntyNm;
	}

	/** registSeCode 	 */ 
	public String getRegistSeCode() {
		return registSeCode;
	}
	public void setRegistSeCode(String registSeCode) {
		this.registSeCode = registSeCode;
	}

	/** registSeCodeNm 	 */ 
	public String getRegistSeCodeNm() {
		return registSeCodeNm;
	}
	public void setRegistSeCodeNm(String registSeCodeNm) {
		this.registSeCodeNm = registSeCodeNm;
	}

	/** tmplatId 	 */ 
	public String getTmplatId() {
		return tmplatId;
	}
	public void setTmplatId(String tmplatId) {
		this.tmplatId = tmplatId;
	}

	/** tmplatNm 	 */ 
	public String getTmplatNm() {
		return tmplatNm;
	}
	public void setTmplatNm(String tmplatNm) {
		this.tmplatNm = tmplatNm;
	}

	/** useAt 	 */ 
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/** memberAt 	 */ 
	public String getMemberAt() {
		return memberAt;
	}
	public void setMemberAt(String memberAt) {
		this.memberAt = memberAt;
	}

	/** homeUrl 	 */ 
	public String getHomeUrl() {
		return homeUrl;
	}
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	/** provdUrl 	 */ 
	public String getProvdUrl() {
		return provdUrl;
	}
	public void setProvdUrl(String provdUrl) {
		this.provdUrl = provdUrl;
	}

	/** provdUrl2 	 */ 
	public String getProvdUrl2() {
		return provdUrl2;
	}
	public void setProvdUrl2(String provdUrl2) {
		this.provdUrl2 = provdUrl2;
	}

	/** cmmntyLogoImage 	 */ 
	public byte[] getCmmntyLogoImage() {
		return cmmntyLogoImage;
	}
	public void setCmmntyLogoImage(byte[] cmmntyLogoImage) {
		this.cmmntyLogoImage = cmmntyLogoImage;
	}

	/** additionalInfo 	 */ 
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	/** EmplyrId 	 */ 
	public String getEmplyrId() {
		return emplyrId;
	}
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	// for jsp only
	/** clubList 	 */ 
	public List<EgovMap> getClubList() {
		return clubList;
	}
	public void setClubList(List<EgovMap> clubList) {
		this.clubList = clubList;
	}

	/** topMenuList 	 */ 
	public List<EgovMap> getTopMenuList() {
		return topMenuList;
	}
	public void setTopMenuList(List<EgovMap> topMenuList) {
		this.topMenuList = topMenuList;
	}

	/** mgrMenuList 	 */ 
	public List<EgovMap> getMgrMenuList() {
		return mgrMenuList;
	}
	public void setMgrMenuList(List<EgovMap> mgrMenuList) {
		this.mgrMenuList = mgrMenuList;
	}

	/** subMenuList 	 */ 
	public List<EgovMap> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<EgovMap> subMenuList) {
		this.subMenuList = subMenuList;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
