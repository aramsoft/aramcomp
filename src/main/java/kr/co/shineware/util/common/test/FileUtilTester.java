package kr.co.shineware.util.common.test;

import kr.co.shineware.util.common.file.FileUtil;

public class FileUtilTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FileUtil.splitFile("D:\\NLP\\데이터\\Corpus\\test\\news.for.20051124040503.cleaned", 10000, "splite");
		FileUtil.removeFile("D:\\NLP\\데이터\\Corpus\\test\\news.for.20051124040503.cleaned");
	}
}
