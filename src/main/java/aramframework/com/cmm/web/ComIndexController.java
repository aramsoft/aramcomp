package aramframework.com.cmm.web;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfoVO;
import aramframework.com.cmm.annotation.IncludedInfo;

/**
 * 컴포넌트 설치 후 설치된 컴포넌트들을 IncludedInfo annotation을 통해 찾아낸 후
 * 화면에 표시할 정보를 처리하는 Controller 클래스
 * <Notice>
 * 		개발시 메뉴 구조가 잡히기 전에 배포파일들에 포함된 공통 컴포넌트들의 목록성 화면에
 * 		URL을 제공하여 개발자가 편하게 활용하도록 하기 위해 작성된 것으로,
 * 		실제 운영되는 시스템에서는 적용해서는 안 됨
 *      실 운영 시에는 삭제해서 배포해도 좋음
 * <Disclaimer>
 * 		운영시에 본 컨트롤을 사용하여 메뉴를 구성하는 경우 성능 문제를 일으키거나
 * 		사용자별 메뉴 구성에 오류를 발생할 수 있음
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ComIndexController {

	@Autowired ApplicationContext applicationContext;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private Map<Integer, Object> map;

	@IncludedInfo(name = "초기화면", order = 1000, gid = 10)
	@RequestMapping("/UnitContent.do")
	public String unitContent() {
		return "com/cmm/UnitContent";
	}
	
	@RequestMapping("/UnitMain.do")
	public String index() {
		return "com/cmm/UnitMain";
	}

	@RequestMapping("/UnitLeft.do")
	public String setLeftMenu(ModelMap model) {

		/* 최초 한 번만 실행하여 map에 저장해 놓는다. */
		if (map == null) {
			map = new TreeMap<Integer, Object>();
			RequestMapping rmAnnotation;
			IncludedInfo annotation;
			IncludedInfoVO zooVO;

			/* @Controller Annotation 처리된 클래스를 모두 찾는다. */
			Map<String, Object> myZoos = applicationContext.getBeansWithAnnotation(Controller.class);
			LOG.debug("How many Controllers : " + myZoos.size());
			for (final Object myZoo : myZoos.values()) {
				Class<? extends Object> zooClass = myZoo.getClass();
//				LOG.debug("Controller Detected " + zooClass);

				String className = zooClass.getName();
//				int endIndex = className.indexOf("$$EnhancerByCGLIB$$", 0);
				int endIndex = className.indexOf("$$EnhancerBySpringCGLIB$$", 0);
				if( endIndex != -1 ) {
					className = className.substring(0, endIndex);
//					LOG.debug("Controller ClassName " + className);
					try {
						zooClass = Class.forName(className);
					}catch (Exception e) {
						LOG.error("Cannot find class !! -- " + className);
					}
				}
				
				Method[] methods = zooClass.getMethods();
				for (int i = 0; i < methods.length; i++) {
/*
					if( methods[i].getAnnotation(RequestMapping.class) != null ) {
						LOG.debug("Found @RequestMapping Method : " +  methods[i].getAnnotation(RequestMapping.class).toString() );
					}
					if( methods[i].getAnnotation(Secured.class) != null ) {
						LOG.debug("Found @Secured Method : " +  methods[i].getAnnotation(Secured.class).toString() );
					}
*/
					annotation = methods[i].getAnnotation(IncludedInfo.class);
					if (annotation != null) {
//						LOG.debug("Found @IncludedInfo Method : " + methods[i] );
						zooVO = new IncludedInfoVO();
						zooVO.setName(annotation.name());
						zooVO.setOrder(annotation.order());
						zooVO.setGid(annotation.gid());
						/*
						 * 목록형 조회를 위한 url 매핑은 @IncludedInfo나 @RequestMapping에서  가져온다
						 */
						rmAnnotation = methods[i].getAnnotation(RequestMapping.class);
						if ("".equals(annotation.listUrl())) {
							zooVO.setListUrl(rmAnnotation.value()[0]);
						} else {
							zooVO.setListUrl(annotation.listUrl());
						}

						map.put(zooVO.getOrder(), zooVO);
					}
				}
			}
		}

		model.addAttribute("resultList", map.values());
		return "com/cmm/UnitLeft";
	}
	
}
