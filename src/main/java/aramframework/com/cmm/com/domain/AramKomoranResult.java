package aramframework.com.cmm.com.domain;

import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.model.LatticeNode;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.parser.KoreanUnitParser;

/**
 * 조회 base VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class AramKomoranResult {

	private KomoranResult komoranResult;
	private KoreanUnitParser parser = new KoreanUnitParser();
	 
    /**
     * KomoranResult 생성자 입니다. </p>
     * Komoran 내부에서 사용되며 대부분의 경우에 외부에서 사용되지 않습니다.
     *
     * @param latticeNode
     * @param jasoUnits
     */
    public AramKomoranResult(KomoranResult komoranResult) {
    	this.komoranResult = komoranResult;
    }
    /**
     * 형태소 분석 결과를 plainText 형태로 반환합니다. </p>
     * plainText 결과는 아래와 같습니다. </p>
     * <pre>
     *     감기/NNG 는/JX 자주/MAG
     * </pre>
     * @return 형태소 분석 결과의 plainText String
     */
    public String getPlainText() {
        StringBuilder result = new StringBuilder();
        for (LatticeNode latticeNode : komoranResult.getResultNodeList()) {
            if (latticeNode.getMorphTag().getTag().equals(SYMBOL.END)) {
            	result.append("<br/>");
            	continue;
            }
            if (latticeNode.getTag().equals(SYMBOL.NA)) {
                result.append(latticeNode.getMorphTag().getMorph()).append("/").append(latticeNode.getMorphTag().getTag()).append(" ");
            } else {
                result.append(parser.combine(latticeNode.getMorphTag().getMorph())).append("/").append(latticeNode.getTag()).append(" ");
            }
        }
        return result.toString().trim();
    }
	
}
