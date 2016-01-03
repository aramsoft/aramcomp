package aramframework.com.cmm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import aramframework.com.cmm.service.CacheInfoService;
import aramframework.com.cmm.service.CacheVO;

@Component
public class CacheUtil implements ApplicationContextAware {

	public static ApplicationContext context;
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CacheUtil.context = context;
	}
	
	protected static final Logger LOG = LoggerFactory.getLogger(CacheUtil.class);

	private static CacheInfoService cacheInfoService;
//	private static Ehcache ehcache ;

	public static String checkCache(String cacheGroup, String cacheKey, String cacheValue) {

		try {
			if( cacheInfoService == null ) {
				cacheInfoService = (CacheInfoService) context.getBean("cacheInfoService");
			}
		} catch (NoSuchBeanDefinitionException ex) {// 해당 컴포넌트를 찾을 수없을 경우
			LOG.error("not found bean !! ");
		}
		
		String preValue = null;
		
		CacheVO cacheVO = new CacheVO();
		cacheVO.setCacheGroup(cacheGroup);
		cacheVO.setCacheKey(cacheKey);
		cacheVO.setCacheValue(cacheValue);
		
		CacheVO returnVO = cacheInfoService.selectCacheInfo(cacheVO);
		if( returnVO == null ) {
			cacheInfoService.insertCacheInfo(cacheVO);
		} else {
			preValue = returnVO.getCacheValue();
			if (!preValue.equals(cacheValue)){
				cacheInfoService.updateCacheInfo(cacheVO);
			} 	
		}
		
		return preValue;
	}

}