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
 * Parsing에서 사용할 품사별 score 점수 <br>
 * 단, SL, SN, SH는 rule parsing 에서만 사용함
 *
 * @author Junsoo Shin
 * @version 2.1.1
 * @since 2.1.1
 *
 */
public class SCORE {
	public static final double NA = -10000.0;
	public static final double SL = -1.00;		// 외국어SL
	public static final double SN = -1.00;		// 숫자SN
	public static final double SH = -1.00;		// 한자SH
	
	public static final double SF = -1.00;		// 마침표,물음표,느낌표SF
	public static final double SW = -10000.0;	// 기타기호(논리수학기호,화폐기호)SW
	public static final double SP = -1.00;		// 쉼표,가운뎃점,콜론,빗금SP
	public static final double SS = -1.00;		// 따옴표,괄호표,줄표SS
	public static final double SO = -1.00;		// 붙임표(물결,숨김,빠짐)SO
}
