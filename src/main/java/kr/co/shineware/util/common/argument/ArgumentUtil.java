package kr.co.shineware.util.common.argument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArgumentUtil {
	
	public static Map<String,String> parse(String[] args){
	
		String key=null;
		String value=null;
		Map<String,String> argMap = new HashMap<String, String>();
		
		for(int i=0;i<args.length;i++){
			//key
			if(args[i].charAt(0) == '-' && key == null){
				key = args[i];
			}
			//value
			else if(key != null){
				value = args[i];
			}
			//key & value
			if(key != null && value != null){
				argMap.put(key, value);
				key = null;
				value = null;
			}
		}
		return argMap;
	}
	
	public static boolean parameterCheck(Set<String> parameterSet, Map<String,String> argMap){
		
		for (String parameter : parameterSet) {
			if(!argMap.containsKey(parameter)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean parameterCheck(String[] parameters, Map<String,String> argMap){
	
		for (String parameter : parameters) {
			if(!argMap.containsKey(parameter)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean parameterCheck(List<String> parameterList, Map<String,String> argMap){
		
		for (String parameter : parameterList) {
			if(!argMap.containsKey(parameter)){
				return false;
			}
		}
		return true;
	}
	
}
