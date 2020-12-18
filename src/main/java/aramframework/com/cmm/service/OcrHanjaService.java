package aramframework.com.cmm.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.domain.ImageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * 커뮤니티 정보를 관리하기 위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class OcrHanjaService extends EgovAbstractServiceImpl {

	@Resource(name = "cacheDictionary")
	private Map<String, Object> cacheDictionary;

	/**
	 * 캐쉬로부터 커뮤니티 정보 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	@SuppressWarnings("unchecked")
	public ImageVO getImageInfo(String imageId) {
		HashMap<String, Object> cacheMap = null;
		
		cacheMap = (HashMap<String, Object>) cacheDictionary.get(CacheKey.IMG_CACHE);
        if( cacheMap == null ) {
        	cacheMap = new HashMap<String, Object>();
        	cacheDictionary.put(CacheKey.IMG_CACHE, cacheMap);
        }
        
        ImageVO imageVO = (ImageVO) cacheMap.get(imageId);

		return imageVO;
	}

	@SuppressWarnings("unchecked")
	public ImageVO setImageInfo(String imageId, ImageVO imageVO) {
		HashMap<String, Object> cacheMap = null;
		
		cacheMap = (HashMap<String, Object>) cacheDictionary.get(CacheKey.IMG_CACHE);
        if( cacheMap == null ) {
        	cacheMap = new HashMap<String, Object>();
        	cacheDictionary.put(CacheKey.IMG_CACHE, cacheMap);
        }
        
        // --------------------------------
		// 커뮤니티 메인
		// --------------------------------
       	cacheMap.put(imageId, imageVO);

       	return imageVO;
	}


}
