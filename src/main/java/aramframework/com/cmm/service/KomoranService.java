package aramframework.com.cmm.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.constant.FILENAME;
import kr.co.shineware.nlp.komoran.constant.SCORE;
import kr.co.shineware.nlp.komoran.constant.SEJONGTAGS;
import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.core.model.ContinuousSymbolBuffer;
import kr.co.shineware.nlp.komoran.core.model.Lattice;
import kr.co.shineware.nlp.komoran.core.model.LatticeNode;
import kr.co.shineware.nlp.komoran.core.model.MorphUtil;
import kr.co.shineware.nlp.komoran.core.model.Resources;
import kr.co.shineware.nlp.komoran.core.model.TagUtil;
import kr.co.shineware.nlp.komoran.core.model.combinationrules.CombinationRuleChecker;
import kr.co.shineware.nlp.komoran.core.model.combinationrules.MergedCombinationRuleChecker;
import kr.co.shineware.nlp.komoran.corpus.parser.CorpusParser;
import kr.co.shineware.nlp.komoran.corpus.parser.model.ProblemAnswerPair;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.MorphTag;
import kr.co.shineware.nlp.komoran.model.ScoredTag;
import kr.co.shineware.nlp.komoran.modeler.model.IrregularNode;
import kr.co.shineware.nlp.komoran.modeler.model.Observation;
import kr.co.shineware.nlp.komoran.parser.KoreanUnitParser;
import kr.co.shineware.util.common.model.Pair;
import kr.co.shineware.util.common.string.StringUtil;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class KomoranService extends EgovAbstractServiceImpl {

    private CombinationRuleChecker combinationRuleChecker;
    private Resources resources;
    private Observation userDic;
    private KoreanUnitParser unitParser;

    private HashMap<String, List<Pair<String, String>>> fwd;

    /**
     * Komoran에서 기본으로 제공되는 모델을 로딩하여 객체를 생성합니다. </p>
     * 별도의 경로를 지정할 필요가 없습니다.
     *
     */
    @PostConstruct
    public void init() {
    	
    	DEFAULT_MODEL modelType = DEFAULT_MODEL.STABLE;
    	
        String kormanPath = Komoran.class.getResource("").getPath();
		int lastIndex = kormanPath.lastIndexOf("kr/co/shineware");
		if( lastIndex == -1 ) return;
    	String basePath = kormanPath.substring(0, lastIndex); 
   	
        String modelPath;
        if (modelType == DEFAULT_MODEL.EXPERIMENT) {
            modelPath = FILENAME.EXPERIMENT_MODEL;
        } else if (modelType == DEFAULT_MODEL.STABLE) {
            modelPath = FILENAME.STABLE_MODEL;
        } else {
            modelPath = FILENAME.STABLE_MODEL;
        }

    	this.resources = new Resources();
        this.resources.load(basePath + modelPath);
        this.unitParser = new KoreanUnitParser();

        MorphUtil morphUtil = new MorphUtil();
        TagUtil tagUtil = new TagUtil(this.resources.getPosTable());
        this.combinationRuleChecker = new MergedCombinationRuleChecker(morphUtil, tagUtil);
    }

    /**
     * 입력된 문장에 대해서 형태소 분석을 진행합니다.
     *
     * @param sentence 분석 대상 문장
     * @return 형태소 분석 결과
     */
    public KomoranResult analyze(String sentence) {
        return this.analyze(sentence, 1).get(0);
    }

    /**
     * 입력된 문장에 대해서 형태소 분석을 진행 후 n-best 결과를 반환합니다.
     *
     * @param sentence 분석 대상 문장
     * @param nbest    분석 결과 중 추출할 상위 n개의 수
     * @return 형태소 분석 결과 중 nbest 수 만큼의 결과
     */
    public List<KomoranResult> analyze(String sentence, int nbest) {

        Lattice lattice = new Lattice(this.resources, this.userDic, nbest, combinationRuleChecker);

        //연속된 숫자, 외래어, 기호 등을 파싱 하기 위한 버퍼
        ContinuousSymbolBuffer continuousSymbolBuffer = new ContinuousSymbolBuffer();

        //자소 단위로 분할
        String jasoUnits = unitParser.parse(sentence);
        List<Pair<Character, KoreanUnitParser.UnitType>> jasoUnitsWithType = unitParser.parseWithType(sentence);

        int length = jasoUnits.length();
        //start 노드 또는 end 노드의 바로 다음 인덱스
        //어절의 시작을 알리는 idx
        int whitespaceIndex = 0;

        for (int curJasoIndex = 0; curJasoIndex < length; curJasoIndex++) {

            //기분석 사전
            int skipIdx = this.lookupFwd(lattice, jasoUnits, curJasoIndex);
            if (skipIdx != -1) {
                curJasoIndex = skipIdx - 1;
                continue;
            }

            //띄어쓰기인 경우
            if (jasoUnits.charAt(curJasoIndex) == ' ') {
                this.consumeContiniousSymbolParserBuffer(lattice, curJasoIndex, continuousSymbolBuffer);
                this.bridgeToken(lattice, curJasoIndex, jasoUnits, whitespaceIndex, jasoUnitsWithType);
                whitespaceIndex = curJasoIndex + 1;
            }

            //이 부분도 조금 더 깔끔한 방법으로 처리 할 수 없을지 고민해보자
            this.continuousSymbolParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex, continuousSymbolBuffer); //숫자, 영어, 외래어 파싱

            //기타 기호인 경우
            this.symbolParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex); // 기타 심볼 파싱
            this.userDicParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex); //사용자 사전 적용
            this.regularParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex); //일반규칙 파싱
            this.irregularParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex); //불규칙 파싱
            this.irregularExtends(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex); //불규칙 확장
        }

        this.consumeContiniousSymbolParserBuffer(lattice, jasoUnits, continuousSymbolBuffer);

        lattice.setLastIdx(jasoUnits.length());
        boolean inserted = lattice.appendEndNode();
        //입력 문장의 끝에 END 품사가 올 수 없는 경우
        if (!inserted) {
            double NAPenaltyScore = SCORE.NA;
            if (whitespaceIndex != 0) {
                NAPenaltyScore += lattice.getNodeList(whitespaceIndex).get(0).getScore();
            }
            String combinedWord = unitParser.combineWithType(jasoUnitsWithType.subList(whitespaceIndex, jasoUnits.length()));
            LatticeNode latticeNode = new LatticeNode(whitespaceIndex, jasoUnits.length(), new MorphTag(combinedWord, SYMBOL.NA, SEJONGTAGS.NA_ID), NAPenaltyScore);
            latticeNode.setPrevNodeIdx(0);
            lattice.appendNode(latticeNode);
            lattice.appendEndNode();
        }

        List<List<LatticeNode>> nBestPath = lattice.findNBestPath();

        List<KomoranResult> nbestResultList = new ArrayList<>();

        //입력 문장 전체가 미분석인 경우
        if (nBestPath == null) {
            List<LatticeNode> resultList = new ArrayList<>();
            resultList.add(new LatticeNode(0, jasoUnits.length(), new MorphTag(sentence, "NA", -1), SCORE.NA));
            nbestResultList.add(new KomoranResult(resultList, jasoUnits));
        } else {
            for (List<LatticeNode> shortestPath : nBestPath) {
                Collections.reverse(shortestPath);
                List<LatticeNode> resultList = new ArrayList<>(shortestPath);
                nbestResultList.add(new KomoranResult(resultList, jasoUnits));
            }
        }

        return nbestResultList;
    }

    //////////////////////////////////////////////////////
    private int lookupFwd(Lattice lattice, String token, int curJasoIndex) {

        if (this.fwd == null) {
            return -1;
        }

        //현재 인덱스가 시작이거나 이전 인덱스가 공백인 경우 (word 단어인 경우)
        //즉, 현재 인덱스가 온전한 단어의 시작 부분인 경우
        if (curJasoIndex == 0 || token.charAt(curJasoIndex - 1) == ' ') {
            //다음 공백을 찾아 단어(word)의 마지막 인덱스를 가져옴
            //다음에 공백이 없다면 입력 문자열의 마지막 인덱스를 가져옴 = 마지막 단어인 경우
            int wordEndIdx = token.indexOf(' ', curJasoIndex);
            wordEndIdx = wordEndIdx == -1 ? token.length() : wordEndIdx;
            String targetWord = token.substring(curJasoIndex, wordEndIdx);
            List<Pair<String, String>> fwdResultList = this.fwd.get(targetWord);

            if (fwdResultList != null) {
                this.insertLatticeForFwd(lattice, curJasoIndex, wordEndIdx, fwdResultList, targetWord);
                return wordEndIdx;
            }
        }
        return -1;
    }

    private void insertLatticeForFwd(Lattice lattice, int beginIdx, int endIdx,
            List<Pair<String, String>> fwdResultList, String targetWord) {

		//기분석 사전과 targetWord의 문자열이 일치하는 경우
		//if (hasRegularFWDValues(fwdResultList, targetWord)) {
		//for (Pair<String, String> morphPosPair : fwdResultList) {
		//lattice.put(beginIdx, beginIdx + this.unitParser.parse(morphPosPair.getFirst()).length(), morphPosPair.getFirst(), morphPosPair.getSecond(), this.resources.getTable().getId(morphPosPair.getSecond()), 0.0);
		//beginIdx += beginIdx + this.unitParser.parse(morphPosPair.getFirst()).length();
		//}
		//} else {
		lattice.put(beginIdx, endIdx, fwdResultList);
		//}
    }
    
    ////////////////////////////////////////
    private void bridgeToken(Lattice lattice, int curIdx, String jasoUnits, int prevBeginSymbolIdx, List<Pair<Character, KoreanUnitParser.UnitType>> jasoUnitsWithType) {

        if (lattice.put(curIdx, curIdx + 1, SYMBOL.EOE, SYMBOL.EOE, SEJONGTAGS.EOE_ID, 0.0)) {
            return;
        }
        //공백이라면 END 기호를 삽입
        LatticeNode naLatticeNode = lattice.makeNode(prevBeginSymbolIdx, curIdx, unitParser.combineWithType(jasoUnitsWithType.subList(prevBeginSymbolIdx, curIdx)), SYMBOL.NA, this.resources.getPosTable().getId(SYMBOL.NA), SCORE.NA, 0);

        int naNodeIndex = lattice.appendNode(naLatticeNode);
        LatticeNode endLatticeNode = lattice.makeNode(curIdx, curIdx + 1, SYMBOL.EOE, SYMBOL.EOE, SEJONGTAGS.EOE_ID, 0.0, naNodeIndex);
        lattice.appendNode(endLatticeNode);
    }

    private void continuousSymbolParsing(Lattice lattice, char charAt, int i, ContinuousSymbolBuffer continuousSymbolBuffer) {
        String curPos = "";
        if (StringUtil.isEnglish(charAt)) {
            curPos = "SL";
        } else if (StringUtil.isNumeric(charAt)) {
            curPos = "SN";
        } else if (StringUtil.isChinese(charAt)) {
            curPos = "SH";
        } else if (StringUtil.isForeign(charAt)) {
            curPos = "SL";
        }

        //현재 기호의 품사가 이전 기호의 품사와 같은 경우에는 갱신하여 추가
        if (curPos.equals(continuousSymbolBuffer.getPrevPos())) {
            continuousSymbolBuffer.setPrevMorph(continuousSymbolBuffer.getPrevMorph() + charAt);
        } else {
            switch (continuousSymbolBuffer.getPrevPos()) {
                case "SL":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(), i,
                            continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(),
                            this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
                            SCORE.SL
                    );
                    break;
                case "SN":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(), i,
                            continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(),
                            this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
                            SCORE.SN
                    );
                    break;
                case "SH":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(), i,
                            continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(),
                            this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
                            SCORE.SH
                    );
                    break;
            }
            continuousSymbolBuffer.setPrevBeginIdx(i);
            continuousSymbolBuffer.setPrevMorph("" + charAt);
            continuousSymbolBuffer.setPrevPos(curPos);
        }
    }

    private void symbolParsing(Lattice lattice, char jaso, int idx) {

        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(jaso);
        //숫자
        if (StringUtil.isNumeric(jaso)) {
        } else if (unicodeBlock == Character.UnicodeBlock.BASIC_LATIN) {
            if (!isEnglishCharacter(jaso) && !isWhitespaceCharacter(jaso) && !isDictionaryEntryCharacter(jaso)) {
                lattice.put(idx, idx + 1, "" + jaso, SYMBOL.SW, SEJONGTAGS.SW_ID, SCORE.SW);
            }
        } else if (!StringUtil.isKorean(jaso) && !StringUtil.isJapanese(jaso) && !StringUtil.isChinese(jaso)) {
            lattice.put(idx, idx + 1, "" + jaso, SYMBOL.SW, SEJONGTAGS.SW_ID, SCORE.SW);
        }
    }

    private boolean isDictionaryEntryCharacter(char jaso) {
        return this.resources.getObservation().getTrieDictionary().getValue("" + jaso) != null;
    }

    private boolean isWhitespaceCharacter(char jaso) {
        return jaso == ' ';
    }

    private boolean isEnglishCharacter(char jaso) {
        return ((jaso >= 'A') && (jaso <= 'Z')) || ((jaso >= 'a') && (jaso <= 'z'));
    }

    /////////////////////////////////////////
    private void userDicParsing(Lattice lattice, char jaso, int curIndex) {
        //Aho-corasick TRIE 기반의 사전 검색하여 형태소와 품사 및 품사 점수(observation)를 얻어옴
        Map<String, List<ScoredTag>> morphScoredTagsMap = lattice.retrievalUserDicObservation(jaso);

        if (morphScoredTagsMap == null || morphScoredTagsMap.size() == 0) {
            return;
        }

        //형태소 정보만 얻어옴
        Set<String> morphes = morphScoredTagsMap.keySet();

        //각 형태소와 품사 정보를 lattice에 삽입
        for (String morph : morphes) {
            int beginIdx = curIndex - morph.length() + 1;
            int endIdx = curIndex + 1;

            //형태소에 대한 품사 및 점수(observation) 정보를 List 형태로 가져옴
            List<ScoredTag> scoredTags = morphScoredTagsMap.get(morph);
            for (ScoredTag scoredTag : scoredTags) {
                lattice.put(beginIdx, endIdx, morph, scoredTag.getTag(), scoredTag.getTagId(), scoredTag.getScore());
            }
        }
    }

    private void regularParsing(Lattice lattice, char jaso, int curIndex) {
        //TRIE 기반의 사전 검색하여 형태소와 품사 및 품사 점수(observation)를 얻어옴
        Map<String, List<ScoredTag>> morphScoredTagsMap = lattice.retrievalObservation(jaso);

        if (morphScoredTagsMap == null || morphScoredTagsMap.size() == 0) {
            return;
        }

        //형태소 정보만 얻어옴
        Set<String> morphes = morphScoredTagsMap.keySet();

        //각 형태소와 품사 정보를 lattice에 삽입
        for (String morph : morphes) {
            int beginIdx = curIndex - morph.length() + 1;
            int endIdx = curIndex + 1;

            //형태소에 대한 품사 및 점수(observation) 정보를 List 형태로 가져옴
            List<ScoredTag> scoredTags = morphScoredTagsMap.get(morph);
            for (ScoredTag scoredTag : scoredTags) {
                lattice.put(beginIdx, endIdx, morph, scoredTag.getTag(), scoredTag.getTagId(), scoredTag.getScore());
                //품사가 EC인 경우에 품사를 EF로 변환하여 lattice에 추가
                if (scoredTag.getTag().equals(SYMBOL.EC)) {
                    lattice.put(beginIdx, endIdx, morph, SYMBOL.EF, SEJONGTAGS.EF_ID, scoredTag.getScore());
                }
            }
        }
    }

    private void irregularParsing(Lattice lattice, char jaso, int curIndex) {
        //불규칙 노드들을 얻어옴
        Map<String, List<IrregularNode>> morphIrrNodesMap = lattice.retrievalIrregularNodes(jaso);
        if (morphIrrNodesMap == null || morphIrrNodesMap.size() == 0) {
            return;
        }

        //형태소 정보만 얻어옴
        Set<String> morphs = morphIrrNodesMap.keySet();
        for (String morph : morphs) {

            int beginIdx = curIndex - morph.length() + 1;
            int endIdx = curIndex + 1;

            List<IrregularNode> irrNodes = morphIrrNodesMap.get(morph);
            for (IrregularNode irregularNode : irrNodes) {
                lattice.put(beginIdx, endIdx, irregularNode);
            }
        }
    }

    private void irregularExtends(Lattice lattice, char jaso, int curIndex) {
        List<LatticeNode> prevLatticeNodes = lattice.getNodeList(curIndex);
        if (prevLatticeNodes != null) {
            Set<LatticeNode> extendedIrrNodeList = new HashSet<>();

            for (LatticeNode prevLatticeNode : prevLatticeNodes) {
                //불규칙 태그인 경우에 대해서만
                if (prevLatticeNode.getMorphTag().getTagId() == SYMBOL.IRREGULAR_ID) {
                    //마지막 형태소 정보를 얻어옴
                    String lastMorph = prevLatticeNode.getMorphTag().getMorph();

                    //불규칙의 마지막 형태소에 현재 자소 단위를 합쳤을 때 자식 노드가 있다면 계속 탐색 가능 후보로 처리 해야함
                    if (this.resources.getObservation().getTrieDictionary().hasChild((lastMorph + jaso).toCharArray())) {
                        LatticeNode extendedIrregularNode = new LatticeNode();
                        extendedIrregularNode.setBeginIdx(prevLatticeNode.getBeginIdx());
                        extendedIrregularNode.setEndIdx(curIndex + 1);
                        extendedIrregularNode.setMorphTag(new MorphTag(prevLatticeNode.getMorphTag().getMorph() + jaso, SYMBOL.IRREGULAR, SYMBOL.IRREGULAR_ID));
                        extendedIrregularNode.setPrevNodeIdx(prevLatticeNode.getPrevNodeIdx());
                        extendedIrregularNode.setScore(prevLatticeNode.getScore());
                        extendedIrrNodeList.add(extendedIrregularNode);
                    }
                    //불규칙의 마지막 형태소에 현재 자소 단위를 합쳐 점수를 얻어옴
                    List<ScoredTag> lastScoredTags = this.resources.getObservation().getTrieDictionary().getValue(lastMorph + jaso);
                    if (lastScoredTags == null) {
                        continue;
                    }

                    //얻어온 점수를 토대로 lattice에 넣음
                    for (ScoredTag scoredTag : lastScoredTags) {
                        lattice.put(prevLatticeNode.getBeginIdx(), curIndex + 1, prevLatticeNode.getMorphTag().getMorph() + jaso,
                                scoredTag.getTag(), scoredTag.getTagId(), scoredTag.getScore());
                    }
                }
            }
            for (LatticeNode extendedIrrNode : extendedIrrNodeList) {
                lattice.appendNode(extendedIrrNode);
            }

        }
    }

    /////////////////////////////////////////////////
    
    private void consumeContiniousSymbolParserBuffer(Lattice lattice, String in, ContinuousSymbolBuffer continuousSymbolBuffer) {
        if (continuousSymbolBuffer.getPrevPos().trim().length() != 0) {
            switch (continuousSymbolBuffer.getPrevPos()) {
                case "SL":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(),
                            in.length(),
                            continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(),
                            this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
                            SCORE.SL
                    );
                    break;
                case "SH":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(),
                            in.length(),
                            continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(),
                            this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
                            SCORE.SH
                    );
                    break;
                case "SN":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(),
                            in.length(),
                            continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(),
                            this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
                            SCORE.SN
                    );
                    break;
            }
        }
    }

    private void consumeContiniousSymbolParserBuffer(Lattice lattice, int endIdx, ContinuousSymbolBuffer continuousSymbolBuffer) {
        if (continuousSymbolBuffer.getPrevPos().trim().length() != 0) {
            switch (continuousSymbolBuffer.getPrevPos()) {
                case "SL":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(), endIdx, continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(), this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()), SCORE.SL);
                    break;
                case "SH":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(), endIdx, continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(), this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()), SCORE.SH);
                    break;
                case "SN":
                    lattice.put(continuousSymbolBuffer.getPrevBeginIdx(), endIdx, continuousSymbolBuffer.getPrevMorph(),
                            continuousSymbolBuffer.getPrevPos(), this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()), SCORE.SN);
                    break;
            }
        }
    }

/*
    private boolean hasRegularFWDValues(List<Pair<String, String>> fwdResultList, String targetWord) {
        StringBuilder fwdMorphs = new StringBuilder();

        for (Pair<String, String> morphPosPair : fwdResultList) {
            fwdMorphs.append(this.unitParser.parse(morphPosPair.getFirst()));
        }

        return fwdMorphs.toString().equals(targetWord);
    }
*/
    /**
     * 형태소 분석 시 사용될 기분석 사전을 로드합니다. </p>
     * 형태소 분석 진행 전에 로드되어야 합니다. </p>
     * <pre>
     *     Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
     *     komoran.setFWDic("user_data/fwd.user");
     *     KomoranResult komoranResult = komoran.analyze("감기는 자주 걸리는 병이다");
     * </pre>
     *
     * @param filename 기분석 사전 파일 경로
     */
    public void setFWDic(String filename) {
        try {
            CorpusParser corpusParser = new CorpusParser();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
//            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            this.fwd = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split("\t");
                //주석이거나 format에 안 맞는 경우는 skip
                if (tmp.length != 2 || tmp[0].charAt(0) == '#') {
                    continue;
                }
                ProblemAnswerPair problemAnswerPair = corpusParser.parse(line);
                List<Pair<String, String>> convertAnswerList = new ArrayList<>();
                for (Pair<String, String> pair : problemAnswerPair.getAnswerList()) {
                    convertAnswerList.add(
                            new Pair<>(pair.getFirst(), pair.getSecond()));
                }

                this.fwd.put(this.unitParser.parse(problemAnswerPair.getProblem()),
                        convertAnswerList);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 형태소 분석 시 사용될 사용자 사전을 로드합니다. </p>
     * 형태소 분석 진행 전에 로드되어야 합니다.
     * <pre>
     *     Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
     *     komoran.setUserDic("user_date/dic.user");
     *     KomoranResult komoranResult = komoran.analyze("바람과 함께 사라지다를 봤어");
     * </pre>
     *
     * @param userDic 사용자 사전 파일 경로
     */
    public void setUserDic(String userDic) {
        try {

            this.userDic = new Observation();
            BufferedReader br = new BufferedReader(new FileReader(userDic));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0 || line.charAt(0) == '#') continue;
                int lastIdx = line.lastIndexOf("\t");

                String morph;
                String pos;
                //사용자 사전에 태그가 없는 경우에는 고유 명사로 태깅
                if (lastIdx == -1) {
                    morph = line.trim();
                    pos = "NNP";
                } else {
                    morph = line.substring(0, lastIdx);
                    pos = line.substring(lastIdx + 1);
                }
                this.userDic.put(morph, pos, this.resources.getPosTable().getId(pos), 0.0);

            }
            br.close();

            //init
            this.userDic.getTrieDictionary().buildFailLink();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
