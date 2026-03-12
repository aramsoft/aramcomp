package aramframework.cmm.config.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Upload File Id generation 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Configuration
public class CacheConfig {
	@Bean
	public Map<String, Object> cacheDictionary() {
	    return new ConcurrentHashMap<>();
	}
}
