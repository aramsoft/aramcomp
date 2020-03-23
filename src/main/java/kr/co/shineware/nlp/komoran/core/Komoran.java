/*******************************************************************************
 * KOMORAN 3.0 - Korean Morphology Analyzer
 *
 * Copyright 2015 Shineware http://www.shineware.co.kr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kr.co.shineware.nlp.komoran.core;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.constant.FILENAME;
import kr.co.shineware.nlp.komoran.constant.SCORE;
import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.model.ContinuousSymbolBuffer;
import kr.co.shineware.nlp.komoran.core.model.Lattice;
import kr.co.shineware.nlp.komoran.core.model.LatticeNode;
import kr.co.shineware.nlp.komoran.core.model.Resources;
import kr.co.shineware.nlp.komoran.corpus.parser.CorpusParser;
import kr.co.shineware.nlp.komoran.corpus.parser.model.ProblemAnswerPair;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.MorphTag;
import kr.co.shineware.nlp.komoran.model.ScoredTag;
import kr.co.shineware.nlp.komoran.modeler.model.IrregularNode;
import kr.co.shineware.nlp.komoran.modeler.model.Observation;
import kr.co.shineware.nlp.komoran.parser.KoreanUnitParser;
import kr.co.shineware.nlp.komoran.util.KomoranCallable;
import kr.co.shineware.util.common.file.FileUtil;
import kr.co.shineware.util.common.model.Pair;
import kr.co.shineware.util.common.string.StringUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * KOMORAN core 클래스입니다.
 */
public class Komoran implements Cloneable {

	protected final Logger KORMORLOG = LoggerFactory.getLogger("KORMORAN");
	
    private Resources resources;
    private Observation userDic;
    private KoreanUnitParser unitParser;

    private HashMap<String, List<Pair<String, String>>> fwd;

    /**
     * modelPath 디렉토리에 있는 모델 파일들을 로딩하여 객체를 생성합니다. </p>
     * modelPath 디렉토리에는 pos.table, observation.model, transition.model, irregular.model 파일이 포함되어 있어야 합니다. </p>
     * 각 파일은 ModelBuilder를 통해 생성됩니다.
     *
     * @param modelPath 모델 파일들이 포함되어 있는 디렉토리 경로
     */
    public Komoran(String modelPath) {
        this.resources = new Resources();
        this.resources.load(modelPath);
        this.unitParser = new KoreanUnitParser();
    }

    /**
     * Komoran에서 기본으로 제공되는 모델을 로딩하여 객체를 생성합니다. </p>
     * 별도의 경로를 지정할 필요가 없습니다.
     *
     * @param modelType 기본으로 제공되는 모델의 타입
     */
    public Komoran(DEFAULT_MODEL modelType) {

    	String kormanPath = Komoran.class.getResource("").getPath();
		int lastIndex = kormanPath.lastIndexOf("kr/co/shineware");
		if( lastIndex == -1 ) return;
    	String basePath = kormanPath.substring(0, lastIndex); 
   	
        String modelPath;
        if (modelType == DEFAULT_MODEL.FULL) {
            modelPath = FILENAME.FULL_MODEL;
        } else if (modelType == DEFAULT_MODEL.LIGHT) {
            modelPath = FILENAME.LIGHT_MODEL;
        } else {
            modelPath = FILENAME.FULL_MODEL;
        }

    	this.resources = new Resources();
        this.resources.load(basePath + modelPath);
        this.unitParser = new KoreanUnitParser();
    }

    /**
     * 파일 단위로 형태소 분석을 진행합니다.
     *
     * @param inputFilename  분석할 파일 경로
     * @param outputFilename 분석 결과가 저장될 파일 경로
     * @param thread         분석 시 사용할 thread 수
     */
    public void analyzeTextFile(String inputFilename, String outputFilename, int thread) {

        try {
            List<String> lines = FileUtil.load2List(inputFilename);

            BufferedWriter bw = new BufferedWriter(
                    (new OutputStreamWriter(new FileOutputStream(outputFilename), StandardCharsets.UTF_8)));
//            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename));
            List<Future<KomoranResult>> komoranResultList = new ArrayList<>();
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(thread);

            for (String line : lines) {
                KomoranCallable komoranCallable = new KomoranCallable(this, line);
                komoranResultList.add(executor.submit(komoranCallable));
            }

            for (Future<KomoranResult> komoranResultFuture : komoranResultList) {
                KomoranResult komoranResult = komoranResultFuture.get();
                bw.write(komoranResult.getPlainText());
                bw.newLine();
            }
            bw.close();
            executor.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 여러 문장을 입력 받아 형태소 분석을 진행합니다.
     *
     * @param sentences 분석할 문장들이 담긴 List. 각 원소는 하나의 문장이라고 간주합니다.
     * @param thread    분석 시 사용할 thread 수
     * @return 문장 별 형태소 분석 결과가 담긴 List
     */
    public List<KomoranResult> analyze(List<String> sentences, int thread) {

        List<KomoranResult> komoranResultList = new ArrayList<>();

        try {
            List<Future<KomoranResult>> komoranResultFutureList = new ArrayList<>();
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(thread);

            for (String line : sentences) {
                KomoranCallable komoranCallable = new KomoranCallable(this, line);
                komoranResultFutureList.add(executor.submit(komoranCallable));
            }

            for (Future<KomoranResult> komoranResultFuture : komoranResultFutureList) {
                komoranResultList.add(komoranResultFuture.get());
            }
            executor.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return komoranResultList;
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

//      sentence = sentence.replaceAll("[ ]+", " ").trim();
        Lattice lattice = new Lattice(this.resources, this.userDic, nbest);
        
        //연속된 숫자, 외래어, 기호 등을 파싱 하기 위한 버퍼
        ContinuousSymbolBuffer continuousSymbolBuffer = new ContinuousSymbolBuffer();

        //자소 단위로 분할
        String jasoUnits = unitParser.parse(sentence);
        List<Pair<Character, KoreanUnitParser.UnitType>> jasoUnitsWithType = unitParser.parseWithType(sentence);

        KORMORLOG.debug("===== [Sentence] =====");
        KORMORLOG.debug(sentence);
        KORMORLOG.debug(jasoUnits);
        
        int jasoLength = jasoUnits.length();
        //start 노드 또는 end 노드의 바로 다음 인덱스
        //어절의 시작을 알리는 idx 
        int whitespaceIndex = 0;

        for (int curJasoIndex = 0; curJasoIndex < jasoLength; curJasoIndex++) {

            //기분석 사전
            int skipIdx = this.lookupFwd(lattice, jasoUnits, curJasoIndex, SYMBOL.FORWARD);
            if (skipIdx != -1) {
                curJasoIndex = skipIdx - 1;

                KORMORLOG.debug("===== [curJasoIndex] " + curJasoIndex + " =====");
                lattice.printLattice(curJasoIndex+1);
                continue;
            } 

            //띄어쓰기인 경우
            if (jasoUnits.charAt(curJasoIndex) == ' ') {
                this.consumeContiniousSymbolParserBuffer(lattice, curJasoIndex, continuousSymbolBuffer);
                this.bridgeToken(lattice, curJasoIndex, jasoUnits, whitespaceIndex, jasoUnitsWithType);
                whitespaceIndex = curJasoIndex + 1;
            }

            //이 부분도 조금 더 깔끔한 방법으로 처리 할 수 없을지 고민해보자
            this.continuousSymbolParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex, continuousSymbolBuffer, SYMBOL.CONSYMBOL); //숫자, 영어, 외래어 파싱

            //기타 기호인 경우
            this.symbolParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex, SYMBOL.MRKSYMBOL); // 기타 심볼 파싱
            this.userDicParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex, SYMBOL.USERDIC); //사용자 사전 적용
            this.regularParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex, SYMBOL.REGULAR); //일반규칙 파싱
            this.irregularParsing(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex, SYMBOL.IRREGULAR); //불규칙 파싱
            this.irregularExtends(lattice, jasoUnits.charAt(curJasoIndex), curJasoIndex, SYMBOL.IRREXTEND); //불규칙 확장

            KORMORLOG.debug("===== [curJasoIndex] : " + curJasoIndex + " =====");
            lattice.printLattice(curJasoIndex+1);
        }

        this.consumeContiniousSymbolParserBuffer(lattice, jasoLength, continuousSymbolBuffer);

        lattice.setLastIdx(jasoLength);
        boolean inserted = lattice.appendEndNode();

        //입력 문장의 끝에 END 품사가 올 수 없는 경우
        if (!inserted) {
            double NAPenaltyScore = SCORE.NA;
            if (whitespaceIndex != 0) {
                NAPenaltyScore += lattice.getNodeList(whitespaceIndex).get(0).getScore();
            }
            String combinedWord = unitParser.combineWithType(jasoUnitsWithType.subList(whitespaceIndex, jasoUnits.length()));
            LatticeNode latticeNode = new LatticeNode(whitespaceIndex, jasoUnits.length(), new MorphTag(combinedWord, SYMBOL.NA, this.resources.getPosTable().getId(SYMBOL.NA)), NAPenaltyScore, "");
            latticeNode.setPrevNodeSeq(0);
            lattice.appendNode(latticeNode);
            lattice.appendEndNode();
        } 

        KORMORLOG.debug("===== [Last] =====");
        lattice.printLattice(0); 
        
        List<List<LatticeNode>> nBestPath = lattice.findNBestPath();

        List<KomoranResult> nbestResultList = new ArrayList<>();
        //입력 문장 전체가 미분석인 경우
        if (nBestPath == null) {
            List<LatticeNode> resultList = new ArrayList<>();
            resultList.add(new LatticeNode(0, jasoUnits.length(), new MorphTag(sentence, "NA", -1), SCORE.NA, ""));
            nbestResultList.add(new KomoranResult(resultList, jasoUnits));
        } else {
//            KORMORLOG.debug("nBestPath size = " + nBestPath.size());
            for (List<LatticeNode> shortestPath : nBestPath) {
                Collections.reverse(shortestPath);
                List<LatticeNode> resultList = new ArrayList<>(shortestPath);
                KORMORLOG.debug("===== [resultList] =====");
                for(LatticeNode node : resultList) {
                	KORMORLOG.debug(node.toString());
                } 
                nbestResultList.add(new KomoranResult(resultList, jasoUnits));
            }
        }

        return nbestResultList;
    }

    //////////////////////////////////////////////////////
    private int lookupFwd(Lattice lattice, String token, int curJasoIndex, String source) {

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
                this.insertLatticeForFwd(lattice, curJasoIndex, wordEndIdx, fwdResultList, targetWord, source);
                return wordEndIdx;
            }
        }
        return -1;
    }

    private void insertLatticeForFwd(Lattice lattice, int curJasoIndex, int wordEndIdx, 
    								 List<Pair<String, String>> fwdResultList, String targetWord, String source) {

        //기분석 사전과 targetWord의 문자열이 일치하는 경우
//        if (hasRegularFWDValues(fwdResultList, targetWord)) {
//            for (Pair<String, String> morphPosPair : fwdResultList) {
//                lattice.put(beginIdx, beginIdx + this.unitParser.parse(morphPosPair.getFirst()).length(), morphPosPair.getFirst(), morphPosPair.getSecond(), this.resources.getTable().getId(morphPosPair.getSecond()), 0.0);
//                beginIdx += beginIdx + this.unitParser.parse(morphPosPair.getFirst()).length();
//            }
//        } else {
        lattice.putFwdLattice(curJasoIndex, wordEndIdx, fwdResultList, source);
//        }
    }

    ////////////////////////////////////////////////////////////
    private void bridgeToken(Lattice lattice, int curJasoIndex, String jasoUnits, int prevBeginSymbolIdx, List<Pair<Character, KoreanUnitParser.UnitType>> jasoUnitsWithType) {

        if (lattice.appendMorphTag(curJasoIndex, curJasoIndex + 1, SYMBOL.END, SYMBOL.END, this.resources.getPosTable().getId(SYMBOL.END), 0.0, "") ) {
            return;
        }
        //공백이라면 END 기호를 삽입
        LatticeNode naLatticeNode = lattice.makeNode(prevBeginSymbolIdx, curJasoIndex, unitParser.combineWithType(jasoUnitsWithType.subList(prevBeginSymbolIdx, curJasoIndex)), SYMBOL.NA, this.resources.getPosTable().getId(SYMBOL.NA), SCORE.NA, 0, "");
        int naNodeIndex = lattice.appendNode(naLatticeNode);
        LatticeNode endLatticeNode = lattice.makeNode(curJasoIndex, curJasoIndex + 1, SYMBOL.END, SYMBOL.END, this.resources.getPosTable().getId(SYMBOL.END), 0.0, naNodeIndex, "");
        lattice.appendNode(endLatticeNode);
    }

    private void continuousSymbolParsing(Lattice lattice, char charAt, int curJasoIndex, ContinuousSymbolBuffer continuousSymbolBuffer, String source) {
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
                    lattice.appendMorphTag(continuousSymbolBuffer.getPrevBeginIdx(), 
           			        curJasoIndex,
           			        continuousSymbolBuffer.getPrevMorph(),
           			        continuousSymbolBuffer.getPrevPos(),
           			        this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
           			        SCORE.SL,
                			source
                    );
                    break;
                case "SN":
                    lattice.appendMorphTag(continuousSymbolBuffer.getPrevBeginIdx(), 
           			        curJasoIndex,
           			        continuousSymbolBuffer.getPrevMorph(),
           			        continuousSymbolBuffer.getPrevPos(),
           			        this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
           			        SCORE.SN,
                			source
                    );
                    break;
                case "SH":
                    lattice.appendMorphTag(continuousSymbolBuffer.getPrevBeginIdx(), 
           			        curJasoIndex,
           			        continuousSymbolBuffer.getPrevMorph(),
           			        continuousSymbolBuffer.getPrevPos(),
           			        this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()),
           			        SCORE.SH,
                			source
                    );
                    break;
            }
            continuousSymbolBuffer.setPrevBeginIdx(curJasoIndex);
            continuousSymbolBuffer.setPrevMorph("" + charAt);
            continuousSymbolBuffer.setPrevPos(curPos);
        }
    }

    private void symbolParsing(Lattice lattice, char jaso, int curJasoIndex, String source) {

        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(jaso);
        //숫자
        if (StringUtil.isNumeric(jaso)) {
        } 
        else if (unicodeBlock == Character.UnicodeBlock.BASIC_LATIN) {
            if (!isEnglishCharacter(jaso) && !isWhitespaceCharacter(jaso) && !isDictionaryEntryCharacter(jaso)) {
                lattice.appendMorphTag(curJasoIndex, curJasoIndex + 1, "" + jaso, SYMBOL.SW, this.resources.getPosTable().getId(SYMBOL.SW), SCORE.SW, source);
            }
        } 
        else if (!StringUtil.isKorean(jaso) && !StringUtil.isJapanese(jaso) && !StringUtil.isChinese(jaso)) {
            lattice.appendMorphTag(curJasoIndex, curJasoIndex + 1, "" + jaso, SYMBOL.SW, this.resources.getPosTable().getId(SYMBOL.SW), SCORE.SW, source);
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

    //////////////////////////////////////////////
    private void userDicParsing(Lattice lattice, char jaso, int curJasoIndex, String source) {
        //Aho-corasick TRIE 기반의 사전 검색하여 형태소와 품사 및 품사 점수(observation)를 얻어옴
        Map<String, List<ScoredTag>> morphScoredTagsMap = lattice.retrievalUserDicObservation(jaso);
        if (morphScoredTagsMap == null || morphScoredTagsMap.size() == 0) {
            return;
        }

        KORMORLOG.debug("===== [userDicParsing : (" + curJasoIndex + ") " + jaso + "] =====");

        //형태소 정보만 얻어옴
        Set<String> morphes = morphScoredTagsMap.keySet();
        //각 형태소와 품사 정보를 lattice에 삽입
        for (String morph : morphes) {
            int beginIdx = curJasoIndex - morph.length() + 1;
            int endIdx = curJasoIndex + 1;

            KORMORLOG.debug("morph = " + morph);
            //형태소에 대한 품사 및 점수(observation) 정보를 List 형태로 가져옴
            List<ScoredTag> scoredTags = morphScoredTagsMap.get(morph);
            for (ScoredTag scoredTag : scoredTags) {
                KORMORLOG.debug(scoredTag.toString());
                lattice.appendMorphTag(beginIdx, endIdx, morph, scoredTag.getTag(), scoredTag.getTagId(), scoredTag.getScore(), source);
            }
        }
    }

    private void regularParsing(Lattice lattice, char jaso, int curJasoIndex, String source) {
        //TRIE 기반의 사전 검색하여 형태소와 품사 및 품사 점수(observation)를 얻어옴
        Map<String, List<ScoredTag>> morphScoredTagsMap = lattice.retrievalObservation(jaso);
        if (morphScoredTagsMap == null || morphScoredTagsMap.size() == 0) {
            return;
        }

        KORMORLOG.debug("===== [regularParsing : (" + curJasoIndex + ") " + jaso + "] =====");

        //형태소 정보만 얻어옴
        Set<String> morphes = morphScoredTagsMap.keySet();
        //각 형태소와 품사 정보를 lattice에 삽입
        for (String morph : morphes) {
            int beginIdx = curJasoIndex - morph.length() + 1;
            int endIdx = curJasoIndex + 1;

            KORMORLOG.debug("morph = " + morph);
            //형태소에 대한 품사 및 점수(observation) 정보를 List 형태로 가져옴
            List<ScoredTag> scoredTags = morphScoredTagsMap.get(morph);
            for (ScoredTag scoredTag : scoredTags) {
                KORMORLOG.debug(scoredTag.toString());
                lattice.appendMorphTag(beginIdx, endIdx, morph, scoredTag.getTag(), scoredTag.getTagId(), scoredTag.getScore(), source);
                //품사가 EC인 경우에 품사를 EF로 변환하여 lattice에 추가
                if (scoredTag.getTag().equals(SYMBOL.EC)) {
                    lattice.appendMorphTag(beginIdx, endIdx, morph, SYMBOL.EF, this.resources.getPosTable().getId(SYMBOL.EF), scoredTag.getScore(), source);
                }
            }
        }
    }

    private void irregularParsing(Lattice lattice, char jaso, int curJasoIndex, String source) {
        //불규칙 노드들을 얻어옴
        Map<String, List<IrregularNode>> morphIrrNodesMap = lattice.retrievalIrregularNodes(jaso);
        if (morphIrrNodesMap == null || morphIrrNodesMap.size() == 0) {
            return;
        }

        KORMORLOG.debug("===== [irregularParsing : (" + curJasoIndex + ") " + jaso + "] =====");

        //형태소 정보만 얻어옴
        Set<String> morphs = morphIrrNodesMap.keySet();
        for (String morph : morphs) {
            int beginIdx = curJasoIndex - morph.length() + 1;
            int endIdx = curJasoIndex + 1;

            KORMORLOG.debug("morph = " + morph);
            List<IrregularNode> irrNodes = morphIrrNodesMap.get(morph);
            for (IrregularNode irregularNode : irrNodes) {
                KORMORLOG.debug(irregularNode.toString());
                lattice.putIrrNode(beginIdx, endIdx, irregularNode, source);
            }
        }
    }

    private void irregularExtends(Lattice lattice, char jaso, int curJasoIndex, String source) {
 
    	List<LatticeNode> prevLatticeNodes = lattice.getNodeList(curJasoIndex);
        if (prevLatticeNodes == null || prevLatticeNodes.size() == 0) { 
        	return;
        }	
        	
        KORMORLOG.debug("===== [irregularExtends : (" + curJasoIndex + ") " + jaso + "] =====");
            
        Set<LatticeNode> extendedIrrNodeList = new HashSet<>();
        for (LatticeNode prevLatticeNode : prevLatticeNodes) { 
        	//불규칙 태그인 경우에 대해서만
            if (prevLatticeNode.getMorphTag().getTagId() == SYMBOL.IRREGULAR_ID) {
            	KORMORLOG.debug(prevLatticeNode.toString());
                //마지막 형태소 정보를 얻어옴
                String lastMorph = prevLatticeNode.getMorphTag().getMorph();
                //불규칙의 마지막 형태소에 현재 자소 단위를 합쳤을 때 자식 노드가 있다면 계속 탐색 가능 후보로 처리 해야함
                if (this.resources.getObservation().getTrieDictionary().hasChild((lastMorph + jaso).toCharArray())) {
                	LatticeNode extendedIrregularNode = new LatticeNode();
                    extendedIrregularNode.setBeginIdx(prevLatticeNode.getBeginIdx());
                    extendedIrregularNode.setEndIdx(curJasoIndex + 1);
                    extendedIrregularNode.setMorphTag(new MorphTag(prevLatticeNode.getMorphTag().getMorph() + jaso, SYMBOL.IRREGULAR, SYMBOL.IRREGULAR_ID));
                    extendedIrregularNode.setPrevNodeSeq(prevLatticeNode.getPrevNodeSeq());
                    extendedIrregularNode.setScore(prevLatticeNode.getScore());
                    extendedIrregularNode.setSource(source);
                    extendedIrrNodeList.add(extendedIrregularNode);
                }
                //불규칙의 마지막 형태소에 현재 자소 단위를 합쳐 점수를 얻어옴
                List<ScoredTag> lastScoredTags = this.resources.getObservation().getTrieDictionary().getValue(lastMorph + jaso);
                if (lastScoredTags == null) {
                    continue;
                }
                
            	KORMORLOG.debug("morph=" + lastMorph + jaso);

                //얻어온 점수를 토대로 lattice에 넣음
                for (ScoredTag scoredTag : lastScoredTags) {
                	KORMORLOG.debug(scoredTag.toString());
                    lattice.appendMorphTag(prevLatticeNode.getBeginIdx(), 
                       				       curJasoIndex + 1, 
                       				       lastMorph + jaso,
                       					   scoredTag.getTag(), 
                       					   scoredTag.getTagId(), 
                       					   scoredTag.getScore(), 
                       					   source);
                }
            }
        }
            
        for (LatticeNode extendedIrrNode : extendedIrrNodeList) {
            lattice.appendNode(extendedIrrNode);
        }
        
    }

    //////////////////////////////////////////////////////////
    private void consumeContiniousSymbolParserBuffer(Lattice lattice, int curJasoIndex, ContinuousSymbolBuffer continuousSymbolBuffer) {
        if (continuousSymbolBuffer.getPrevPos().trim().length() != 0) {
        	double score = 0.0;
        	
            switch (continuousSymbolBuffer.getPrevPos()) {
            	case "SL":			// 외국어SL
            		score = SCORE.SL;
                	break;
            	case "SH":			// 한자SH
            		score = SCORE.SH;
            		break;
            	case "SN":			// 숫자SN
                	score =	SCORE.SN;
                	break;
            }
            lattice.appendMorphTag(continuousSymbolBuffer.getPrevBeginIdx(), 
            					   curJasoIndex, 
               					   continuousSymbolBuffer.getPrevMorph(),
               					   continuousSymbolBuffer.getPrevPos(), 
               					   this.resources.getPosTable().getId(continuousSymbolBuffer.getPrevPos()), 
                    			   score,
                    			   ""
            );
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
