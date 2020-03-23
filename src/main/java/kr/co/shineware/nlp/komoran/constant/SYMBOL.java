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
package kr.co.shineware.nlp.komoran.constant;

/**
 * komoran에서 사용되는 SYMBOL에 대한 정의<br>
 *
 * @author Junsoo Shin
 * @version 2.1
 * @since 2.1
 */
public class SYMBOL {

    public static final String CONSYMBOL = "CON";
    public static final String MRKSYMBOL = "MRK";
    public static final String FORWARD   = "FWD";
    public static final String USERDIC   = "UDC";
    public static final String REGULAR   = "REG";
    public static final String IRREGULAR = "IRR";
    public static final String IRREXTEND = "IRX";
    
    public static final String START = "BOE";
    public static final String END = "EOE";
    public static final String SPACE = "<sp>";
    public static final String NA = "NA";

    public static final String NUMBER = "<number>";
    public static final int    IRREGULAR_ID = -1;
 
    public static final String NNG = "NNG";			// 일반명사NNG
    public static final String NNP = "NNP";			// 고유명사NNP
    public static final String NNB = "NNB";			// 의존명사NNB
    public static final String NP = "NP";			// 대명사NP
    public static final String NR = "NR";			// 수사NR

    public static final String VV = "VV";			// 동사VV
    public static final String VA = "VA";			// 형용사VA
    public static final String VX = "VX";			// 보조용언VX
    
    public static final String VCP = "VCP";			// 긍정지정사VCP
    public static final String VCN = "VCN";			// 부정지정사VCN
    
    public static final String JKS = "JKS";			// 주격조사JKS
    public static final String JKC = "JKC";			// 보격조사JKC
    public static final String JKG = "JKG";			// 관형격조사JKG
    public static final String JKO = "JKO";			// 목적격조사JKO
    public static final String JKB = "JKB";			// 부사격조사JKB
    public static final String JKV = "JKV";			// 호격조사JKV	
    
    public static final String JX = "JX";			// 보조사JX
    public static final String JC = "JC";			// 접속조사JC
    
    public static final String EP = "EP";			// 선어말어미EP
    public static final String EF = "EF";			// 종결어미EF
    public static final String EC = "EC";			// 연결어미EC
    public static final String ETN = "ETN";			// 명사형전성어미ETN
    public static final String ETM = "ETM";			// 관형형전성어미ETM

    public static final String SF = "SF";			// 마침표,물음표,느낌표SF
    public static final String SS = "SS";			// 따옴표,괄호표,줄표SS
    public static final String SW = "SW";			// 기타기호(논리수학기호,화폐기호)SW
    
    public static final String[] NOUN = new String[]{SYMBOL.NNG, SYMBOL.NNP, SYMBOL.NNB, SYMBOL.NP, SYMBOL.NR};
    public static final String[] EOMI = new String[]{SYMBOL.EP, SYMBOL.EC, SYMBOL.EF, SYMBOL.ETN, SYMBOL.ETM};
    public static final String[] JOSA = new String[]{SYMBOL.JC, SYMBOL.JKB, SYMBOL.JKC, SYMBOL.JKG, SYMBOL.JKO, SYMBOL.JKS, SYMBOL.JKV, SYMBOL.JX};

}
