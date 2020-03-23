package kr.co.shineware.util.common.string;

import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.co.shineware.util.common.model.Jaso;
import kr.co.shineware.util.common.model.Syllable;

/**
 * String과 관련된 Util 모음
 * @author jsshin
 *
 */
public class StringUtil {
	
	public static char[] ChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138,
		0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
		0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	public static char[] JungSung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153,
		0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b,
		0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };
	public static char[] JongSung = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134,
		0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d,
		0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146,
		0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

	/**
	 * 입력된 source를 spliter 기준으로 분할
	 * @param source
	 * @param spliter
	 * @return
	 */
	public static List<String> split(String source, String spliter){
	
		List<String> splitedList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer(source);
		int prevIndex = -1;
		int index = 0;

		while(true){			
			index = sb.indexOf(spliter, prevIndex+1);			
			if(index == -1){
				splitedList.add(sb.substring(prevIndex+1));
				break;
			}
			splitedList.add(sb.substring(prevIndex+1,index));			
			prevIndex = index + spliter.length()-1;
		}
		sb = null;

		return splitedList;
	}

	/**
	 * 입력된 문장을 ngram List 형태로 반환
	 * 띄어쓰기를 포함하여 ngram을 생성함
	 * @param str 입력 문장
	 * @param n ngram에서 n의 개수
	 * @param begin 시작 기호 설정 (Can be null)
	 * @param end 끝나는 기호 설정 (Can be null)
	 * @return
	 */
	public static List<String> ngram(String str,int n,String begin,String end){

		List<String> ngramList = new ArrayList<String>();
		for(int i=0;i<n-1;i++){
			if(begin != null){
				str = begin+str;
			}
			if(end != null){
				str = str + end;
			}			
		}
		for(int i=0;i<str.length();i++){
			if(i+n > str.length()){
				break;
			}			
			StringBuilder sb = new StringBuilder();
			for(int j=i;j<i+n;j++){
				sb.append(str.charAt(j));
			}
			ngramList.add(sb.toString());
			sb = null;
		}
		return ngramList;
	}

	/**
	 * 입력된 문장을 Jaso 리스트로 변환
	 * 
	 * 종성이 없으면 x로 변환
	 * ex : 가 -> ㄱ,ㅏ,x
	 * 
	 * 입력 문장 중 음절 정보가 부정확한 경우 자소 타입을 ETC로 반환
	 * ex : ㅋ,ㅏ,a,b 등
	 * @param str
	 * @return
	 */
	public static List<Jaso> korean2JasoList(String str) {
		
		int length = str.length();		
		List<Jaso> jasoList = new ArrayList<Jaso>();

		for(int i=0;i<length;i++){

			//한글 str을 음절 정보를 담고 있는 Syllable 형태로 반환
			Syllable syllable =StringUtil.hangul2Syllable(str.charAt(i));

			//입력 str의 음절 정보가 부정확한 경우(ㅋ,ㅏ,a, b 등등) 자소 타입을 ETC로 정함
			if(syllable == null){
				jasoList.add(new Jaso(str.charAt(i), Jaso.TYPE.ETC, -1));
				continue;
			}

			//초성 정보를 Jaso로 변환
			Jaso chosung = new Jaso(syllable.getChosung(),Jaso.TYPE.CHOSUNG,syllable.getChosungIndex());
			jasoList.add(chosung);

			//중성 정보를 Jaso로 변환
			Jaso jungsung = new Jaso(syllable.getJungsung(),Jaso.TYPE.JUNGSUNG,syllable.getJungsungIndex());
			jasoList.add(jungsung);

			//종성이 있는 경우 종성 정보를 Jaso로 변환
			//			if(syllable.getJongsungIndex() != 0){
			Jaso jongsung = new Jaso(syllable.getJongsung(),Jaso.TYPE.JONGSUNG,syllable.getJongsungIndex());
			jasoList.add(jongsung);
			syllable =null;
			chosung = null;
			jungsung = null;
			jongsung = null;
			//			}
		}
		return jasoList;
	}

	/**
	 * 자소리스트를 string으로 변환
	 * @param jasoList
	 * @return
	 */
	public static String restoreJasoList2Korean(List<Jaso> jasoList){
		return restoreJasoList2Korean(0, jasoList.size()-1, jasoList);
	}

	public static String restoreJasoList2Korean(int begin, int end, List<Jaso> jasoList) {

		int cho=-1,jung=-1,jong=-1;

		StringBuffer koreanStr = new StringBuffer();
		for(int i=begin;i<=end;i++){

			Jaso jaso = jasoList.get(i);
			if(jaso.getType() == Jaso.TYPE.CHOSUNG){
				cho = jaso.getIndex();
			}
			else if(jaso.getType() == Jaso.TYPE.JUNGSUNG){
				jung = jaso.getIndex();
			}
			else if(jaso.getType() == Jaso.TYPE.JONGSUNG){
				jong = jaso.getIndex();
			}
			else{				
				jong = Arrays.binarySearch(StringUtil.JongSung, jaso.getJaso());
			}

			if(i+1 > end 
					|| jasoList.get(i+1).getType() == Jaso.TYPE.CHOSUNG 
					|| jasoList.get(i+1).getType() == Jaso.TYPE.ETC 
					|| jasoList.get(i+1).getType() == Jaso.TYPE.ETC){
				//종성이 있는 경우
				if(jong != -1){
					//초성이 있는 경우
					if(cho != -1){
						koreanStr.append((char)(0xAC00+cho*588+jung*28+jong));
					}
					//초성이 없는 경우
					else{						
						koreanStr.append(jaso.getJaso());
					}
				}else if(jung != -1){
					koreanStr.append((char)(0xAC00+cho*588+jung*28));
				}else{
					koreanStr.append(jaso.getJaso());
				}
				cho = jung = jong = -1;
			}
			jaso = null;
		}	

		return koreanStr.toString();
	}

	/**
	 * 입력된 ch가 한글인지 아닌지 판단
	 * @param ch
	 * @return
	 */
	public static boolean isKorean(char ch){
		Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
		if(UnicodeBlock.HANGUL_SYLLABLES == unicodeBlock ||
				UnicodeBlock.HANGUL_COMPATIBILITY_JAMO == unicodeBlock ||
				UnicodeBlock.HANGUL_JAMO == unicodeBlock)
			return true;
		else return false;
	}
	
	public static String getKorean(String in){
		StringBuffer sb = new StringBuffer();
		char[] charArray = in.toCharArray();
		for (char ch : charArray) {
			Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
			if(UnicodeBlock.HANGUL_SYLLABLES == unicodeBlock 
					|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO == unicodeBlock 
					|| UnicodeBlock.HANGUL_JAMO == unicodeBlock){
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * 입력된 ch가 영어인지 판단
	 * @param ch
	 * @return
	 */
	public static boolean isEnglish(char ch){
		Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
		if(unicodeBlock == Character.UnicodeBlock.BASIC_LATIN){
			if (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'))) {
				return true;
			}
		}
		return false;		
	}

	/**
	 * 입력된 ch가 일본어(카타카나, 카타카나 포네틱 확장, 히라가나)인지 판단
	 * @param ch
	 * @return
	 */
	public static boolean isJapanese(char ch){
		Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
		if(UnicodeBlock.KATAKANA.equals(unicodeBlock) 
				|| UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS.equals(unicodeBlock)
				|| UnicodeBlock.HIRAGANA.equals(unicodeBlock)){
			return true;
		}
		return false;
	}

	/**
	 * 입력된 ch가 영어 외의 외국어인지 판단(히라가나, 카타카나, 한자)
	 * @param ch
	 * @return
	 */
	public static boolean isForeign(char ch){
		Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);	
		if(unicodeBlock == Character.UnicodeBlock.HIRAGANA ||
				unicodeBlock == Character.UnicodeBlock.KATAKANA ||
				unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS){
			return true;
		}
		return false;
	}

	/**
	 * 입력된 ch가 한자인지 판단
	 * @param ch
	 * @return
	 */
	public static boolean isChinese(char ch){
		Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
		if(UnicodeBlock.CJK_COMPATIBILITY.equals(unicodeBlock)				
				|| UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(unicodeBlock)
				|| UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(unicodeBlock)
				|| UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B.equals(unicodeBlock)
				|| UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(unicodeBlock)){
			return true;
		}
		return false;
	}

	/**
	 * 입력된 ch의 유니코드 블록 범위를 반환
	 * @param ch
	 * @return
	 */
	public static UnicodeBlock getUnicodeBlock(char ch){
		return Character.UnicodeBlock.of(ch);
	}

	/**
	 * 입력된 ch가 숫자인지 아닌지 판단
	 * @param ch
	 * @return
	 */
	public static boolean isNumeric(char ch){				
		if(Character.isDigit(ch)){
			return true;
		}
		return false;
	}


	public static String korean2JasoString(String word){
		return korean2JasoString(word, false);
	}

	/**
	 * 입력된 word를 자소 스트링 형태로 반환
	 * ex : "감기!" -> "ㄱㅏㅁㄱㅣx!"
	 * @param word 변환할 스트링
	 * @param fixJongsung 종성 고정 여부 선택. 종성이 없는 경우에는 x로 문자열 대치.
	 * @return
	 */
	public static String korean2JasoString(String word,boolean fixJongsung){
		String key = "";
		for(int j=0;j<word.length();j++){
			Syllable s = StringUtil.hangul2Syllable(word.charAt(j));
			if(s == null){
				key += word.charAt(j);
				continue;
			}
			key += s.getChosung();
			key += s.getJungsung();
			if(fixJongsung){
				key += s.getJongsung();
			}else{
				if(s.getJongsung() != 'x'){
					key += s.getJongsung();
				}
			}
			s = null;
		}		
		return key;
	}

	/**
	 * 입력된 한글 음절 ch를 음절 정보를 포함하는 Syllable로 변환
	 * @param ch
	 * @return
	 */
	public static Syllable hangul2Syllable(char ch){
		int cho,jung,jong,tmp;
		if(ch >= 0xAC00 && ch <=0xD7A3){
			tmp = ch - 0xAC00;
			cho = tmp / (21*28);
			tmp = tmp % (21*28);			
			jung = tmp / 28;
			jong = tmp % 28;			

			if(jong != 0){
				return new Syllable(ChoSung[cho], cho,JungSung[jung], jung,JongSung[jong],jong);
			}else{
				return new Syllable(ChoSung[cho], cho,JungSung[jung],jung, 'x',jong);
			}
		}		
		return null;
	}

	/**
	 * 입력된 jaso word를 스트링 형태로 반환
	 * @param jasoWord
	 * @return
	 */
	public static String restoreJasoword2Korean(String jasoWord){

		StringBuilder sb = new StringBuilder();
		int length = jasoWord.length();

		char cho, jung, jong;
		cho = jung = jong = ' ';

		for(int i=0; i<length; i++) {

			//자소가 자음인 경우
			if(jasoWord.charAt(i) >= 0x3131 && jasoWord.charAt(i) <= 0x314e) {

				//초성이 비어있으면 초성으로 셋팅
				if(cho == ' ') {
					cho = jasoWord.charAt(i);
				} else {
					//초성이 이미 있고 중성이 없으면 이미 있는 초성을 음절로 출력하고 현재 자음를 초성으로 셋팅
					if(jung == ' ') {
						sb.append(cho);
						cho = jasoWord.charAt(i);
					} else {
						//마지막 자소가 아니고
						//초성이 있고 중성도 있지만 종성이 없으면 다음 자소를 확인해 
						//자음이면 종성으로 모음이면 현재까지의 초성 중성을 음절로 출력하고 현재 자음을 초성으로 셋팅
						if(i+1 < length) {
							if(jasoWord.charAt(i+1) >= 0x3131 && jasoWord.charAt(i+1) <= 0x314e) {
								jong = jasoWord.charAt(i);
								sb.append(combineJaso2Emjeol(cho, jung, jong));
								cho = jung = jong = ' ';
							} else {
								sb.append(combineJaso2Emjeol(cho, jung, jong));
								cho = jasoWord.charAt(i);
								jung = jong = ' ';
							}
						} else {
							//마지막 자소이면 현재 자음을 종성으로 넣어 음절로 만듬
							jong = jasoWord.charAt(i);
							sb.append(combineJaso2Emjeol(cho, jung, jong));
							cho = jung = jong = ' ';
						}

					}
				}
			}
			//자소가 모음인 경우
			else {
				//초성이 비어있으면 자소만 바로 출력
				if(cho == ' ') {
					sb.append(jasoWord.charAt(i));
				} 
				//초성이 있으면
				else {
					jung = jasoWord.charAt(i);
				}
			}
		}

		if(cho != ' ' || jung != ' ' || jong != ' ') {
			sb.append(combineJaso2Emjeol(cho, jung, jong));
		}

		return sb.toString();
	}

	private static char combineJaso2Emjeol(char cho, char jung, char jong) {

		char emjeol = 0;

		if(cho != ' ') {
			if(jung != ' ') {
				int choIndex = Arrays.binarySearch(ChoSung, cho);
				int jungIndex = jung - 0x314f;

				emjeol = (char) (0xAC00 + (choIndex * 588) + (jungIndex * 28));

				if(jong != ' ') {
					int jongIndex = Arrays.binarySearch(JongSung, jong);
					emjeol += jongIndex;
				}
			} else {
				emjeol = cho;
			}
		} else {
			if(jong != ' ') {
				emjeol = jung;
			} else {
				emjeol = jong;
			}
		}

		return emjeol;
	}
	
}
