package aramframework.com.uss.ion.rsn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.ion.rsn.domain.RssInfoVO;
import aramframework.com.uss.ion.rsn.service.RssService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * RSS서비스를 처리하는 ServiceImpl Class 구현
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

@Service("rssService")
public class RssServiceImpl extends EgovAbstractServiceImpl implements RssService {

	@Autowired
	private RssMapper rssMapper;	

	/**
	 * RSS서비스를(을) 목록을 조회 한다.
	 * 
	 * @param rssInfoVO
	 */
	public List<EgovMap> selectRssServiceList(RssInfoVO rssInfoVO) {
		return rssMapper.selectRssServiceList(rssInfoVO);
	}

	/**
	 * RSS서비스를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param rssInfoVO
	 */
	public int selectRssServiceListCnt(RssInfoVO rssInfoVO) {
		return rssMapper.selectRssServiceListCnt(rssInfoVO);
	}

	/**
	 * RSS서비스를(을) 상세조회 한다.
	 * 
	 * @param rssInfoVO
	 */
	public RssInfoVO selectRssServiceDetail(RssInfoVO rssInfoVO) {
		RssInfoVO resultVo = rssMapper.selectRssServiceDetail(rssInfoVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, rssInfoVO); 
		return resultVo;
	}

	/**
	 * RSS서비스 테이블을 조회 한다.
	 * 
	 * @param rssInfoVO
	 */
	public List<EgovMap> selectRssServiceTable(RssInfoVO rssInfoVO) {

		List<HashMap<String, Object>> listResult = rssMapper.selectRssServiceTable(rssInfoVO);
		List<EgovMap> listReturn = new ArrayList<EgovMap>();

		String sBDT_TITLE = rssInfoVO.getBdtTitle();
		String sBDT_LINK = rssInfoVO.getBdtLink();
		String sBDT_DESCRIPTION = rssInfoVO.getBdtDescription();
		String sBDT_TAG = rssInfoVO.getBdtTag();
		String sBDT_ETC = rssInfoVO.getBdtEtc();

		HashMap<String, Object> mapRow;
		EgovMap mapResult;

		for (int i = 0; i < listResult.size(); i++) {

			mapRow = listResult.get(i);
			
			// null 처리
			String sM_BDT_TITLE = sBDT_TITLE == null ? "" : sBDT_TITLE;
			String sM_BDT_LINK = sBDT_LINK == null ? "" : sBDT_LINK;
			String sM_BDT_DESCRIPTION = sBDT_DESCRIPTION == null ? "" : sBDT_DESCRIPTION;
			String sM_BDT_TAG = sBDT_TAG == null ? "" : sBDT_TAG;
			String sM_BDT_ETC = sBDT_ETC == null ? "" : sBDT_ETC;

			Object[] Keys = mapRow.keySet().toArray();

			for (Object key : Keys) {
				if (mapRow.get(key) instanceof String) {
					// null 처리
					if (mapRow.get(key) != null && key != null) {
						sM_BDT_TITLE = sM_BDT_TITLE.replaceAll("#" + key + "#", (String) mapRow.get(key));
						sM_BDT_LINK = sM_BDT_LINK.replaceAll("#" + key + "#", (String) mapRow.get(key));
						sM_BDT_DESCRIPTION = sM_BDT_DESCRIPTION.replaceAll("#" + key + "#", (String) mapRow.get(key));
						sM_BDT_TAG = sM_BDT_TAG.replaceAll("#" + key + "#", (String) mapRow.get(key));
						sM_BDT_ETC = sM_BDT_ETC.replaceAll("#" + key + "#", (String) mapRow.get(key));
					}
				}
			}
			mapResult = new EgovMap();
			mapResult.put("bdtTitle", sM_BDT_TITLE);
			mapResult.put("bdtLink", sM_BDT_LINK);
			mapResult.put("bdtDescription", sM_BDT_DESCRIPTION);
			mapResult.put("bdtTag", sM_BDT_TAG);
			mapResult.put("bdtEtc", sM_BDT_ETC);
			mapResult.put("frstRegisterPnttm", (Date) mapRow.get("FRST_REGIST_PNTTM"));

			listReturn.add(mapResult);
		}

		return listReturn;
	}

}
