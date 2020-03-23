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
package kr.co.shineware.nlp.komoran.corpus.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import kr.co.shineware.nlp.komoran.interfaces.FileAccessible;

/**
 * This class is model of dictionary. It can be used like DB instead of save, load.
 *
 * @author Junsoo Shin <jsshin@shineware.co.kr>
 * @version 2.1
 * @since 2.1
 */
public class CorpusData implements FileAccessible {

    //String = line
    private List<String> corpusData;

    /**
     * 사전 생성자로써 init() method 실행.
     */
    public CorpusData() {
        this.init();
    }

    /**
     * 사전 생성자로써 filename에 저장된 사전 데이터를 로딩
     * 데이터 로딩 시 init method 실행
     *
     * @param filename
     */
    public CorpusData(String filename) {
        this.load(filename);
    }

    /**
     * 사전이 사용되는 메모리 초기화.
     * 내부적으로 Double Map collection 사용됨.
     */
    protected void init() {
        this.corpusData = new ArrayList<String>();
    }

    /**
     * 현재 메모리에 올라가 있는 사전 데이터를 반환.
     *
     * @return
     */
    public List<String> getCopursData() {
        return this.corpusData;
    }

    @Override
    public void save(String filename) {
        try {
            BufferedWriter bw = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8));
            for (String line : corpusData) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(String filename) {
        this.init();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
//            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = null;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                lineCount += 1;
                line = this.refineFormat(line);
                if(lineCount < 10){
                    System.out.println(line);
                }
                this.corpusData.add(line);
            }
            br.close();
            br = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 신규 corpus 추가 <br>
     *
     * @param corpus
     * @param answers
     */
    public void append(String line) {
        this.corpusData.add(line);
    }

    /**
     * 입력된 line의 형식을 정규화 <br>
     * 1. 2개 이상의 공백은 하나로 대체<br>
     * 2. line의 양 끝 trim
     *
     * @param line
     * @return
     */
    private String refineFormat(String line) {
        return line.replaceAll("[ ]+", " ").trim();
    }

}
