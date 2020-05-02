package aramframework.com.cmm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.domain.AramKomoranResult;
import aramframework.com.cmm.service.KomoranService;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Controller
public class KomoranController {

	@Autowired 
	private KomoranService komoranService; 

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/testKomoran.do")
	public String unitContent(
			@RequestParam(value="inputText", required=false) String inputText, 
			ModelMap model) {

		if( inputText == null || "".equals(inputText) ) {
			inputText = "산림청에서 근무해 왔으며, 슬하에 아들 한 명을 두고 있다.";
		}
		
		KomoranResult komoranResultList = komoranService.analyze(inputText);

		AramKomoranResult aramResultList = new AramKomoranResult(komoranResultList);
		
		//print each tokens by getTokenList()  
//		List<Token> tokenList = analyzeResultList.getTokenList();
//		KORMORLOG.debug("==========print 'getTokenList()'==========");
//		for (Token token : tokenList) {
//			KORMORLOG.debug(token.toString()); 
//			KORMORLOG.debug(token.getMorph()+"/"+token.getPos()+"("+token.getBeginIndex()+","+token.getEndIndex()+")");
//			KORMORLOG.debug("");
//		}

		//print nouns
//		KORMORLOG.debug("==========print 'getNouns()'==========");
//		KORMORLOG.debug(analyzeResultList.getNouns().toString());
//		KORMORLOG.debug("");

//		KORMORLOG.debug("==========print 'getPlainText()'==========");
//		KORMORLOG.debug(analyzeResultList.getPlainText());
//		KORMORLOG.debug("");

//		KORMORLOG.debug("==========print 'getList()'==========");
//		KORMORLOG.debug(analyzeResultList.getList().toString());
		model.addAttribute("inputText", inputText);
		model.addAttribute("resultText", aramResultList.getPlainText());
			
		return "com/cmm/TestKomoran";
	}
	
}
