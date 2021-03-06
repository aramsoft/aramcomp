package aramframework.com.cmm.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 컴포넌트 설치 유무 확인 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Component("componentChecker")
public class ComponentChecker implements ApplicationContextAware {

	public static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context) throws BeansException {

		ComponentChecker.context = context;
	}

	/**
	 * Spring MVC에서 설정한 빈이 아닌 서비스 빈(컴포넌트)만을 검색할 수 있음
	 * 
	 * @param 	componentName		String
	 * @return 						true/false
	 */
	public static boolean hasComponent(String componentName) {

		try {
			Object component = context.getBean(componentName);

			if (component == null) {
				return false;
			} else {
				return true;
			}

		} catch (NoSuchBeanDefinitionException ex) {// 해당 컴포넌트를 찾을 수없을 경우
													// false반환
			return false;
		}
	}
	
	/**
	 * Spring MVC에서 설정한 빈이 아닌 서비스 빈(컴포넌트)만을 검색할 수 있음
	 * 
	 * @param 	componentName		String
	 * @return 						true/false
	 */
	public static Object getComponent(String componentName) {

		try {
			return context.getBean(componentName);

		} catch (NoSuchBeanDefinitionException ex) {// 해당 컴포넌트를 찾을 수없을 경우
													// false반환
			return null;
		}
	}
	
}
