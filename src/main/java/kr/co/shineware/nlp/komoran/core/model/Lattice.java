package kr.co.shineware.nlp.komoran.core.model;

import kr.co.shineware.ds.aho_corasick.FindContext;
import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.model.combinationrules.CombinationRuleChecker;
import kr.co.shineware.nlp.komoran.core.model.combinationrules.NounEomiCombinationRuleChecker;
import kr.co.shineware.nlp.komoran.core.model.combinationrules.NounJosaCombinationRuleChecker;
import kr.co.shineware.nlp.komoran.core.model.combinationrules.VerbEomiCombinationRuleChecker;
import kr.co.shineware.nlp.komoran.model.MorphTag;
import kr.co.shineware.nlp.komoran.model.ScoredTag;
import kr.co.shineware.nlp.komoran.modeler.model.*;
import kr.co.shineware.util.common.collection.MapUtil;
import kr.co.shineware.util.common.model.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lattice {

	protected final Logger KORMORLOG = LoggerFactory.getLogger("KORMORAN");
	
    private static final int IRREGULAR_TAG_ID = -1;
    
    private ArrayList<Integer> latticeArray;
    private Map<Integer, List<LatticeNode>> lattice;
    private PosTable posTable;
    private Transition transition;
    private Observation observation;
    private Observation userDicObservation;
    private IrregularTrie irregularTrie;
    
    private int lastIdx = -1;
    private int irrIdx = -10;

    private FindContext<List<ScoredTag>> observationFindContext;
    private FindContext<List<IrregularNode>> irregularFindContext;
    private FindContext<List<ScoredTag>> userDicFindContext;

    private List<CombinationRuleChecker> combinationRuleCheckerList;

    private int tmpPrevMaxSeq;
    private LatticeNode tmpPrevMaxNode;
    private double tmpPrevMaxScore;
    
    private int nbest;

    public Lattice(Resources resource, Observation userDic) {
        this(resource, userDic, 1);
    }

    public Lattice(Resources resource, Observation userDic, int nbest) {
        this.setPosTable(resource.getPosTable());
        this.setTransition(resource.getTransition());
        this.setObservation(resource.getObservation());
        this.setIrregularTrie(resource.getIrrTrie());
        this.setUserDicObservation(userDic);

        this.init();
        this.makeNewContexts();
        this.nbest = nbest;
        this.registCombinationRuleChecker();
    }

    public PosTable getPosTable() {
        return posTable;
    }
    public void setPosTable(PosTable posTable) {
        this.posTable = posTable;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    private void setIrregularTrie(IrregularTrie irrTrie) {
        this.irregularTrie = irrTrie;
    }

    private void setUserDicObservation(Observation userDic) {
        this.userDicObservation = userDic;
    }

    public int getLastIdx() {
        return lastIdx;
    }
    public void setLastIdx(int lastIdx) {
        this.lastIdx = lastIdx;
    }

    private void init() {

    	this.lattice = new HashMap<>();
    	this.latticeArray = new ArrayList<>();
        irrIdx = -10;

        List<LatticeNode> latticeNodes = new ArrayList<>();
        latticeNodes.add(this.makeStartNode());

        this.lattice.put(0, latticeNodes);
    	if( !this.latticeArray.contains(0)) {
    		this.latticeArray.add(0);
    	}
    }

    private LatticeNode makeStartNode() {
        return new LatticeNode(-1, 0, new MorphTag(SYMBOL.START, SYMBOL.START, this.getPosTable().getId(SYMBOL.START)), 0, "");
    }

    private void makeNewContexts() {
        this.observationFindContext = this.observation.getTrieDictionary().newFindContext();
        this.irregularFindContext = this.irregularTrie.getTrieDictionary().newFindContext();
        if (this.userDicObservation != null) {
            this.userDicFindContext = this.userDicObservation.getTrieDictionary().newFindContext();
        }
    }

    private void registCombinationRuleChecker() {
        MorphUtil morphUtil = new MorphUtil();
        TagUtil tagUtil = new TagUtil(this.getPosTable());
        this.combinationRuleCheckerList = new ArrayList<>();
        this.combinationRuleCheckerList.add(new NounJosaCombinationRuleChecker(morphUtil, tagUtil));
        this.combinationRuleCheckerList.add(new VerbEomiCombinationRuleChecker(morphUtil, tagUtil));
        this.combinationRuleCheckerList.add(new NounEomiCombinationRuleChecker(tagUtil));
    }

    public Map<String, List<ScoredTag>> retrievalObservation(char jaso) {
        return this.observation.getTrieDictionary().get(this.observationFindContext, jaso);
    }

    public Map<String, List<IrregularNode>> retrievalIrregularNodes(char jaso) {
        return this.irregularTrie.getTrieDictionary().get(this.irregularFindContext, jaso);
    }

    public Map<String, List<ScoredTag>> retrievalUserDicObservation(char jaso) {
        if (this.userDicObservation == null) return null;
        return this.userDicObservation.getTrieDictionary().get(this.userDicFindContext, jaso);
    }

    //기분석 사전을 위한 lattice put
    public void putFwdLattice(int beginIdx, int endIdx, List<Pair<String, String>> fwdResultList, String source) {

        if (fwdResultList.size() == 1) {
            Pair<String, String> morphPosPair = fwdResultList.get(0);
            this.appendMorphTag(beginIdx, endIdx, morphPosPair.getFirst(), morphPosPair.getSecond(), this.posTable.getId(morphPosPair.getSecond()), 0.0, source);
        }

        //TODO : find solution for better code to simplify calculation of FWD transition score
        //이 로직은 뭐지? 왜 이렇게 만들어 놨을까..
        //아..기분석 결과가 여러 형태소로 이뤄진 경우에는 그 형태소 간의 전이확률을 구해야하는데
        //이거 때문에 irrIdx라는 가상의 index를 주고 그걸로 잇는구나.. 이게 최선인가?
        //이건 코드 짠 사람이 아니면 이해하기 어려울 것 같다. 개선이 필요해 보임. 일단 irrIdx를 전역 변수로 쓰고 있는 것 자체가 별로임
        //어떻게 할까..생각해보면 lattice 자체는 thread safe 하지 않은데.. lattice를 생성하는 로직이 thread safe하기 때문에 이 로직이 가능한 구조인데.. 어떻게 가져가야할까..고민을 좀 해보자..
        else {
            for (int i = 0; i < fwdResultList.size(); i++) {
                Pair<String, String> morphPosPair = fwdResultList.get(i);
                if (i == 0) {
                    this.appendMorphTag(beginIdx, irrIdx - 1, morphPosPair.getFirst(), morphPosPair.getSecond(), this.posTable.getId(morphPosPair.getSecond()), 0.0, source);
                } else if (i == fwdResultList.size() - 1) {
                    this.appendMorphTag(irrIdx, endIdx, morphPosPair.getFirst(), morphPosPair.getSecond(), this.posTable.getId(morphPosPair.getSecond()), 0.0, source);
                } else {
                    this.appendMorphTag(irrIdx, irrIdx - 1, morphPosPair.getFirst(), morphPosPair.getSecond(), this.posTable.getId(morphPosPair.getSecond()), 0.0, source);
                }
                irrIdx--;
            }
        }
    }

    public void putIrrNode(int beginIdx, int endIdx, IrregularNode irregularNode, String source) {
        //현재 node를 연결 시킬 이전 node list들을 가져옴
        List<LatticeNode> prevLatticeNodes = this.lattice.get(beginIdx);

        //아 이거 아래 코드는 심오한데....
        if (prevLatticeNodes != null) {
            this.tmpPrevMaxSeq = -1;
            this.tmpPrevMaxNode = null;
            this.tmpPrevMaxScore = Double.NEGATIVE_INFINITY;
            this.getMaxTransitionInfoFromPrevNodes(prevLatticeNodes, irregularNode.getFirstPosId());

            if (this.tmpPrevMaxNode != null) {
                List<Pair<String, Integer>> irregularTokens = irregularNode.getTokens();
                int prevMaxSeq = this.tmpPrevMaxSeq;
                double prevMaxScore = this.tmpPrevMaxScore;

                //불규칙확장을 위한 노드 추가
                this.putIrregularExtendTokens(beginIdx, endIdx, irregularTokens, prevMaxScore, prevMaxSeq, source);
            }
        } 
    }

    private void putIrregularExtendTokens(int beginIdx, int endIdx,
                                          List<Pair<String, Integer>> irregularTokens, 
                                          double prevMaxScore, int prevMaxSeq, String source) {

        if (irregularTokens == null || irregularTokens.size() == 0) {
            return;
        }
        
        Pair<String, Integer> morphPosPair = null;
        List<ScoredTag> scoredTags = null;
        if (irregularTokens.size() == 1) {
            morphPosPair = irregularTokens.get(0);
            scoredTags = this.observation.getTrieDictionary().getValue(morphPosPair.getFirst());
            for (ScoredTag scoredTag : scoredTags) {
                if (scoredTag.getTagId() == morphPosPair.getSecond()) {
                    LatticeNode firstIrregularNode = this.makeNode(beginIdx, endIdx, morphPosPair.getFirst(), scoredTag.getTag(), scoredTag.getTagId(), prevMaxScore + scoredTag.getScore(), prevMaxSeq, source);
                    this.appendNode(firstIrregularNode);
                    //마지막 노드가 EC인 경우에는 EF를 변환하여 노드를 추가한다
                    if (scoredTag.getTagId() == this.posTable.getId(SYMBOL.EC)) {
                        LatticeNode extendIrregularNode = this.makeNode(beginIdx, endIdx, morphPosPair.getFirst(), SYMBOL.EF, this.posTable.getId(SYMBOL.EF), prevMaxScore + scoredTag.getScore(), prevMaxSeq, source);
                        this.appendNode(extendIrregularNode);
                    }
                }
            }
            return;
    	} 
    	
        //첫번쨰 토큰에 대한 처리
        morphPosPair = irregularTokens.get(0);
        scoredTags = this.observation.getTrieDictionary().getValue(morphPosPair.getFirst());
        for (ScoredTag scoredTag : scoredTags) {
            if (scoredTag.getTagId() == morphPosPair.getSecond()) {
                LatticeNode firstIrregularNode = this.makeNode(beginIdx, irrIdx - 1, morphPosPair.getFirst(), scoredTag.getTag(), scoredTag.getTagId(), prevMaxScore + scoredTag.getScore(), prevMaxSeq, source);
                irrIdx--;
                this.appendNode(firstIrregularNode);
            }
        }

        for (int i = 1; i < irregularTokens.size(); i++) {
        	morphPosPair = irregularTokens.get(i);
            scoredTags = this.observation.getTrieDictionary().getValue(morphPosPair.getFirst());
            if (i == irregularTokens.size() - 1) {
            	for (ScoredTag scoredTag : scoredTags) {
                    if (scoredTag.getTagId() == morphPosPair.getSecond()) {
                        this.appendMorphTag(irrIdx, endIdx, morphPosPair.getFirst(), this.posTable.getPos(morphPosPair.getSecond()), morphPosPair.getSecond(), scoredTag.getScore(), source);
                        if (morphPosPair.getSecond() == this.posTable.getId(SYMBOL.EC)) {
                            this.appendMorphTag(irrIdx, endIdx, morphPosPair.getFirst(), SYMBOL.EF, this.posTable.getId(SYMBOL.EF), scoredTag.getScore(), source);
                        }
                    }
                }
                //마지막 토큰에 대해서는 IRR 태그를 넣어줌 이때 score는 0.0을 줌
                LatticeNode latticeNode = this.makeNode(irrIdx, endIdx, morphPosPair.getFirst(), SYMBOL.IRREGULAR, IRREGULAR_TAG_ID, 0.0, 0, source);
                this.appendNode(latticeNode);
            } else {
                for (ScoredTag scoredTag : scoredTags) {
                    if (scoredTag.getTagId() == morphPosPair.getSecond()) {
                        this.appendMorphTag(irrIdx, irrIdx - 1, morphPosPair.getFirst(), this.posTable.getPos(morphPosPair.getSecond()), morphPosPair.getSecond(), scoredTag.getScore(), source);
                    }
                }
            }
            irrIdx--;
        }
    }

    public boolean appendMorphTag(int beginIdx, int endIdx, String morph, String tag, int tagId, double score, String source) {

        List<LatticeNode> prevLatticeNodes = this.getNodeList(beginIdx);

        if (prevLatticeNodes != null) {
            if (nbest != 1) {
                List<LatticeNode> nbestLatticeNodeList = this.getNbestMaxTransitionNodeFromPrevNodes(prevLatticeNodes, beginIdx, endIdx, morph, tag, tagId, score, this.nbest, source);

                if (nbestLatticeNodeList != null) {
                    for (LatticeNode latticeNode : nbestLatticeNodeList) {
                        this.appendNode(latticeNode);
                    }
                    return true;
                }
            } else {
                LatticeNode maxLatticeNode = this.getMaxTransitionNodeFromPrevNodes(prevLatticeNodes, beginIdx, endIdx, morph, tag, tagId, score, source);
                if (maxLatticeNode != null) {
                    this.appendNode(maxLatticeNode);
                    return true;
                }
            }

        }
        return false;
    }

    public int appendNode(LatticeNode latticeNode) {
    	List<LatticeNode> latticeNodeList = this.getNodeList(latticeNode.getEndIdx());
    	if (latticeNodeList == null) {
    		latticeNodeList = new ArrayList<>();
    	}
    	latticeNodeList.add(latticeNode);
    	this.lattice.put(latticeNode.getEndIdx(), latticeNodeList);
    	if( !this.latticeArray.contains(latticeNode.getEndIdx())) {
    		this.latticeArray.add(latticeNode.getEndIdx());
    	}
    	return latticeNodeList.size() - 1;
    }

    public LatticeNode makeNode(int beginIdx, int endIdx, String morph, String tag, int tagId, double score, int prevNodeSeq, String source) {
    	LatticeNode latticeNode = new LatticeNode(beginIdx, endIdx, new MorphTag(morph, tag, tagId), score, source);
    	latticeNode.setPrevNodeSeq(prevNodeSeq);
    	return latticeNode;
    }

    public boolean appendEndNode() {
    	return this.appendMorphTag(this.lastIdx, this.lastIdx + 1, SYMBOL.END, SYMBOL.END, this.getPosTable().getId(SYMBOL.END), 0, "");
    }

    public List<LatticeNode> getNodeList(int index) {
    	return this.lattice.get(index);
    }

    private List<LatticeNode> getNbestMaxTransitionNodeFromPrevNodes(
            List<LatticeNode> prevLatticeNodes, int beginIdx, int endIdx,
            String morph, String tag, int tagId, double score, int nbest, String source) {

        List<LatticeNode> nbestPrevNodeList = new ArrayList<>();
        int latticeNodeSeq = -1;
        for (LatticeNode prevLatticeNode : prevLatticeNodes) {
        	latticeNodeSeq++;
            //불규칙인경우
            if (prevLatticeNode.getMorphTag().getTagId() == IRREGULAR_TAG_ID) {
                continue;
            }
            int prevTagId;
            String prevMorph;
            if (prevLatticeNode.getMorphTag().getTag().equals(SYMBOL.END)) {
                prevTagId = this.getPosTable().getId(SYMBOL.START);
                prevMorph = SYMBOL.START;
            } else {
                prevTagId = prevLatticeNode.getMorphTag().getTagId();
                prevMorph = prevLatticeNode.getMorphTag().getMorph();
            }
            //전이 확률 값 가져옴
            Double transitionScore = this.transition.get(prevTagId, tagId);
            if (transitionScore == null) {
                continue;
            }

            //결합규칙 체크
            if (!isValidCombination(prevMorph, prevTagId, morph, tagId)) {
                continue;
            }

            double prevObservationScore = prevLatticeNode.getScore();

            if (nbestPrevNodeList.size() < nbest) {
                nbestPrevNodeList.add(
                		this.makeNode(beginIdx, endIdx, morph, tag, tagId, transitionScore + prevObservationScore + score, latticeNodeSeq, source)
                );
                continue;
            }

            int nbestMinIndex = 0;
            double nbestMinScore = nbestPrevNodeList.get(0).getScore();

            for (int i = 1; i < nbestPrevNodeList.size(); i++) {
                if (nbestMinScore > nbestPrevNodeList.get(i).getScore()) {
                    nbestMinIndex = i;
                    nbestMinScore = nbestPrevNodeList.get(i).getScore();
                }
            }

            if (nbestMinScore < transitionScore + prevObservationScore + score) {
                nbestPrevNodeList.set(
                        nbestMinIndex,
                        this.makeNode(beginIdx, endIdx, morph, tag, tagId, transitionScore + prevObservationScore + score, latticeNodeSeq, source)
                );
            }
        }
        if (nbestPrevNodeList.size() != 0) {
            return nbestPrevNodeList;
        }
        return null;
    }

    private LatticeNode getMaxTransitionNodeFromPrevNodes(
            List<LatticeNode> prevLatticeNodes, int beginIdx, int endIdx,
            String morph, String tag, int tagId, double score, String source) {

        int prevLatticeNodeSeq = -1;
        LatticeNode prevMaxNode = null;
        double prevMaxScore = Double.NEGATIVE_INFINITY;
       
        int latticeNodeSeq = -1;
        for (LatticeNode prevLatticeNode : prevLatticeNodes) {
        	latticeNodeSeq++;
            //불규칙인경우
            if (prevLatticeNode.getMorphTag().getTagId() == IRREGULAR_TAG_ID) {
                continue;
            }
            int prevTagId;
            String prevMorph;
            if (prevLatticeNode.getMorphTag().getTag().equals(SYMBOL.END)) {
                prevMorph = SYMBOL.START;
                prevTagId = this.getPosTable().getId(SYMBOL.START);
            } else {
                prevMorph = prevLatticeNode.getMorphTag().getMorph();
                prevTagId = prevLatticeNode.getMorphTag().getTagId();
            }
            //전이 확률 값 가져옴
            Double transitionScore = this.transition.get(prevTagId, tagId);
            if (transitionScore == null) {
                continue;
            }

            //결합규칙 체크
            if (!isValidCombination(prevMorph, prevTagId, morph, tagId)) {
                continue;
            }

            double prevObservationScore = prevLatticeNode.getScore();

            if (prevMaxScore < transitionScore + prevObservationScore) {
                prevMaxScore = transitionScore + prevObservationScore;
                prevMaxNode = prevLatticeNode;
                prevLatticeNodeSeq = latticeNodeSeq;
            }
        }
        if (prevMaxNode != null) {
            return this.makeNode(beginIdx, endIdx, morph, tag, tagId, prevMaxScore + score, prevLatticeNodeSeq, source);
        }
        return null;
    }

    private boolean isValidCombination(String prevMorph, int prevTagId, String morph, int tagId) {
        for (CombinationRuleChecker combinationRuleChecker : this.combinationRuleCheckerList) {
            if (!combinationRuleChecker.isValidRule(prevMorph, prevTagId, morph, tagId)) {
                return false;
            }
        }
        return true;
    }

    private void getMaxTransitionInfoFromPrevNodes(List<LatticeNode> prevLatticeNodes, int tagId) {

        int prevMaxNodeSeq = -1;
        for (LatticeNode prevLatticeNode : prevLatticeNodes) {
            prevMaxNodeSeq++;
            //불규칙인경우
            if (prevLatticeNode.getMorphTag().getTagId() == IRREGULAR_TAG_ID) {
                continue;
            }
            int prevTagId;
            if (prevLatticeNode.getMorphTag().getTag().equals(SYMBOL.END)) {
                prevTagId = this.getPosTable().getId(SYMBOL.START);
            } else {
                prevTagId = prevLatticeNode.getMorphTag().getTagId();
            }
            //전이 확률 값 가져옴
            Double transitionScore = this.transition.get(prevTagId, tagId);
            if (transitionScore == null) {
                continue;
            }

            double prevObservationScore = prevLatticeNode.getScore();

            if (this.tmpPrevMaxScore < transitionScore + prevObservationScore) {
                this.tmpPrevMaxScore = transitionScore + prevObservationScore;
                this.tmpPrevMaxNode = prevLatticeNode;
                this.tmpPrevMaxSeq = prevMaxNodeSeq;
            }
        }
    }

    public void printLattice(int triggerIdx) {
        int totalLatticeNodeSize = 0;
        boolean trigger = false;
        for (Integer idx : this.latticeArray) {
        	
            List<LatticeNode> nodeList = this.lattice.get(idx);
            if (nodeList == null) {
                continue;
            }
            totalLatticeNodeSize += nodeList.size();

            if ( idx == triggerIdx ) trigger = true;
            
            if ( trigger ) {
            	KORMORLOG.debug("[" + idx + "]");
            	for (LatticeNode latticeNode : nodeList) {
            		KORMORLOG.debug(latticeNode.toString());
            	}
            	KORMORLOG.debug("");
            }	
        }
        KORMORLOG.debug("Total lattice node size : " + totalLatticeNodeSize);
    }

    /*    
    private boolean isNoun(int prevTagId) {
        return prevTagId == this.posTable.getId(SYMBOL.NNG)
                || prevTagId == this.posTable.getId(SYMBOL.NNP)
                || prevTagId == this.posTable.getId(SYMBOL.NP);
    }

    private boolean isPredicate(int prevTagId) {
        return prevTagId == this.posTable.getId(SYMBOL.VV)
                || prevTagId == this.posTable.getId(SYMBOL.VA)
                || prevTagId == this.posTable.getId(SYMBOL.VX)
                || prevTagId == this.posTable.getId(SYMBOL.VCP)
                || prevTagId == this.posTable.getId(SYMBOL.VCN);
    }

    private boolean hasJongsung(String str) {
        char prevLastJaso = str.charAt(str.length() - 1);
        if (0x3131 <= prevLastJaso && prevLastJaso <= 0x314e) {
            return prevLastJaso != 0x3138 && prevLastJaso != 0x3143 && prevLastJaso != 0x3149;
        }
        return false;
    }
*/
    public List<LatticeNode> findPath() {
        List<LatticeNode> shortestPathList = new ArrayList<>();
        int idx = this.getLastIdx() + 1;
        //마지막 연결 노드가 없는 경우에는 null 반환
        if (!this.lattice.containsKey(idx)) {
            return null;
        }

        LatticeNode latticeNode = this.lattice.get(idx).get(0);
        int prevLatticeEndIndex = latticeNode.getEndIdx();
        while (true) {
            latticeNode = this.lattice.get(latticeNode.getBeginIdx()).get(latticeNode.getPrevNodeSeq());
            if (latticeNode.getEndIdx() < 0) {
                latticeNode.setEndIdx(prevLatticeEndIndex);
            }
            shortestPathList.add(latticeNode);
            prevLatticeEndIndex = latticeNode.getEndIdx();
            if (latticeNode.getBeginIdx() == 0) break;
        }

        return shortestPathList;
    }

    public List<List<LatticeNode>> findNBestPath() {

    	List<List<LatticeNode>> nBestShortestPathList = new ArrayList<>();
        int idx = this.getLastIdx() + 1;
        //마지막 연결 노드가 없는 경우에는 null 반환
        if (!this.lattice.containsKey(idx)) {
            return null;
        }

        for (LatticeNode endNode : this.lattice.get(idx)) {
            List<LatticeNode> shortestPathList = new ArrayList<>();
            int prevLatticeEndIndex = endNode.getEndIdx();
            LatticeNode latticeNode = endNode;
            shortestPathList.add(latticeNode);
            while (true) {
                latticeNode = this.lattice.get(latticeNode.getBeginIdx()).get(latticeNode.getPrevNodeSeq());
                //불규칙이거나 multi token 기분석 사전인 경우
                if (latticeNode.getEndIdx() < 0) {
                    latticeNode.setEndIdx(prevLatticeEndIndex);
                }
                shortestPathList.add(latticeNode);
                prevLatticeEndIndex = latticeNode.getEndIdx();
                if (latticeNode.getBeginIdx() == 0) break;	// 맨처음 node까지 진행
            }

            nBestShortestPathList.add(shortestPathList);
        }

        if (nBestShortestPathList.size() > 1) {
            nBestShortestPathList = sortNBestByScore(nBestShortestPathList);
        }

        return nBestShortestPathList;
    }

    private List<List<LatticeNode>> sortNBestByScore(List<List<LatticeNode>> nBestShortestPathList) {
        Map<List<LatticeNode>, Double> sortedLatticeNodeList = new HashMap<>();
        for (List<LatticeNode> latticeNodes : nBestShortestPathList) {
            double score = latticeNodes.get(0).getScore();
            sortedLatticeNodeList.put(latticeNodes, score);
        }

        return new ArrayList<>(MapUtil.sortByValue(sortedLatticeNodeList, MapUtil.DESCENDING_ORDER).keySet());
    }
    
}
