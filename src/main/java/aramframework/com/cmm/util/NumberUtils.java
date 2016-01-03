/*    
 * Classname : StrUtil.java
 * Remark    : 
 * Version   : V1.0
 * Date      : 2005.2.23
 * Author    : 조헌철
 */
package aramframework.com.cmm.util; 
  
/**
 * Number Utility : Number 처리 관련 Utility.<br>
 * String 처리를 위한 Utility입니다.<br>
 * 
 * @author		c.h.c. 		
 */
public class NumberUtils {
	
	/**
	 * Integer Array내에 해당 정수가 있는가를 확인한다.
	 * 
	 * @param	intArray	검사할 정수 배열
	 * @param	number		검사할 정수 
	 * @return 	boolean	:   존재하면 <code>true</code>, 
	 * 						없으면 <code>false</false>
	 */
	public static boolean existNumber(int[] nArray, int number) 
	{
		if (nArray.length == 0 || number == 0) {
			return false;
		}
		for(int i=0; i < nArray.length ; i++ ) {
			if( nArray[i] == number ) return true;
		}
		return false;
	}

}