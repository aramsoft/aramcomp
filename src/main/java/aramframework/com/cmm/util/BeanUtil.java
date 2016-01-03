package aramframework.com.cmm.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtil {

	protected static final Logger LOG = LoggerFactory.getLogger(BeanUtil.class);
	
	private static List<String> arrDefault 
			= Arrays.asList(
					"pathId", 
					"searchCondition", 
					"searchKeyword", 
					"searchUseYn",
					"pageIndex", 
					"pageSize", 
					"firstIndex", 
					"lastIndex", 
					"recordPerPage",
					"totalRecordCount", 
					"searchKeywordFrom", 
					"searchKeywordTo"
			);
	
	public static void copyProperties(Object source, Object target, String[] properties) {

		if( source == null ) {
			LOG.error("source can not be null !!!, target = " + target.toString());
			throw new RuntimeException("source can not be null !!!,");
		}
		final BeanWrapper src = new BeanWrapperImpl(source);
		final BeanWrapper trg = new BeanWrapperImpl(target);

		for(final String propName : properties){
		    trg.setPropertyValue(propName, src.getPropertyValue(propName) );
		}
	}

	public static void copyPropertiesCore(Object source, Object target) {

		if( source == null ) {
			LOG.error("source can not be null !!!, target = " + target.toString());
			throw new RuntimeException("source can not be null !!!,");
		}
		final BeanWrapper src = new BeanWrapperImpl(source);
		final BeanWrapper trg = new BeanWrapperImpl(target);

		String propName = null;
		PropertyDescriptor[] descriptors = src.getPropertyDescriptors();
			
	    for (int i = 0; i < descriptors.length; i++) {		    
	    	propName = descriptors[i].getName();
	    	if( propName.equals("class") ) continue;
	    	if( arrDefault.contains(propName)) continue;
	    	if( src.getPropertyValue(propName) == null ) continue;
	    	trg.setPropertyValue(propName, src.getPropertyValue(propName));
		}
	}
	
	public static void copyPropertiesCore(Object source, Object target, List<String> arrExclude) {

		if( source == null ) {
			LOG.error("source can not be null !!!, target = " + target.toString());
			throw new RuntimeException("source can not be null !!!,");
		}
		final BeanWrapper src = new BeanWrapperImpl(source);
		final BeanWrapper trg = new BeanWrapperImpl(target);

		String propName = null;
		PropertyDescriptor[] descriptors = src.getPropertyDescriptors();
		
	    for (int i = 0; i < descriptors.length; i++) {		    
	    	propName = descriptors[i].getName();
	    	if( propName.equals("class") ) continue;
	    	if( arrDefault.contains(propName)) continue;
	    	if( arrExclude.contains(propName)) continue;
	    	
	    	trg.setPropertyValue(propName, src.getPropertyValue(propName));
		}
	}

}