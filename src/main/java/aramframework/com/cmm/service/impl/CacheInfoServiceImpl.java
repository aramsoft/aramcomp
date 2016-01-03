package aramframework.com.cmm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.service.CacheInfoService;
import aramframework.com.cmm.service.CacheVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
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

@Service("cacheInfoService")
public class CacheInfoServiceImpl extends EgovAbstractServiceImpl implements CacheInfoService {

	@Resource(name = "cacheInfoMapper")
	private CacheInfoMapper cacheInfoMapper;	

	/**
	 * 캐쉬를 조회한다.
	 * 
	 * @param cacheVO
	 */
	public CacheVO selectCacheInfo(CacheVO cacheVO) {
		return cacheInfoMapper.selectCacheInfo(cacheVO);
	}
	
	/**
	 * 캐쉬를 등록한다.
	 * 
	 * @param cacheVO
	 */
	public void insertCacheInfo(CacheVO cacheVO) {
		cacheInfoMapper.insertCacheInfo(cacheVO);
	}
	
	/**
	 * 캐쉬를 수정한다.
	 * 
	 * @param cacheVO
	 */
	public void updateCacheInfo(CacheVO cacheVO) {
		cacheInfoMapper.updateCacheInfo(cacheVO);
	}

}
