package aramframework.com.cmm.annotation;

/**
 * 컴포넌트의 포함 정보 표현을 위한 annotation 클래스
 * 기본적으로 Controller 클래스에 annotation을 부여하되, 
 * 하나의 Controller에 여러 개의 목록성 url mapping이 제공되는 경우에는
 * 메소드에 annotation을 부여한다. 
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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IncludedInfo {
	String name() default ""; 		// 컴포넌트의 한글 이름
	String listUrl() default ""; 	// 컴포넌트의 목록정보조회를 위한 URL
	int order() default 0; 			// 자동 생성되는 메뉴 목록에 표시되는 순서
	int gid() default 0; 			// 컴포넌트의 Group ID(대분류 구분)
}
