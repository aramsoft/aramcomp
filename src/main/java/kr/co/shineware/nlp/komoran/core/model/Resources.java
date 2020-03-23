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
package kr.co.shineware.nlp.komoran.core.model;

import java.io.File;

import kr.co.shineware.nlp.komoran.constant.FILENAME;
import kr.co.shineware.nlp.komoran.modeler.model.IrregularTrie;
import kr.co.shineware.nlp.komoran.modeler.model.Observation;
import kr.co.shineware.nlp.komoran.modeler.model.PosTable;
import kr.co.shineware.nlp.komoran.modeler.model.Transition;

public class Resources {
	
	private PosTable posTable;
	private Transition transition;
	private Observation observation;
	private IrregularTrie irrTrie;

	public PosTable getPosTable() {
		return posTable;
	}
	public void setPosTable(PosTable posTable) {
		this.posTable = posTable;
	}
	
	public Transition getTransition() {
		return transition;
	}
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
	public Observation getObservation() {
		return observation;
	}
	public void setObservation(Observation observation) {
		this.observation = observation;
	}
	
	public IrregularTrie getIrrTrie() {
		return irrTrie;
	}
	public void setIrrTrie(IrregularTrie irrTrie) {
		this.irrTrie = irrTrie;
	}
	
	public void init(){
		this.posTable = new PosTable();
		this.observation = new Observation();
		this.transition = new Transition();
		this.irrTrie = new IrregularTrie();
	}
	
	public void load(String filePath) {
		this.init();
		this.loadPosTable(filePath+File.separator+FILENAME.POS_TABLE);
		this.loadTransition(filePath+File.separator+FILENAME.TRANSITION);
		this.loadObservation(filePath+File.separator+FILENAME.OBSERVATION);
		this.loadIrregular(filePath+File.separator+FILENAME.IRREGULAR_MODEL);
	}

	public void loadPosTable(String fileName){
		this.posTable.load(fileName);
	}

	public void loadTransition(String fileName){
		this.transition.load(fileName);
	}

	public void loadObservation(String fileName){
		this.observation.load(fileName);
		this.observation.getTrieDictionary().buildFailLink();
	}

	public void loadIrregular(String fileName){
		this.irrTrie.load(fileName);
		this.irrTrie.getTrieDictionary().buildFailLink();
	}

}
