package kr.co.shineware.util.common.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * File과 관련된 Util 모음
 * @author jsshin
 *
 */
public class FileUtil {
	
	/**
	 * 입력된 파일의 내용을 라인별 List 형태로 반환
	 * @param filename 읽어들일 파일 이름
	 * @param encoding 읽어들일 파일의 인코딩
	 * @return 파일의 한라인씩 순차적으로 저장된 리스트
	 */
	public static List<String> load2List(String filename, String encoding){		  
		BufferedReader br;
		List<String> resultList = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
			String line;
			while ((line = br.readLine()) != null) {
//				if(line.trim().length() == 0)continue;
				resultList.add(line);
			}
			br.close();
			br = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * 입력된 파일의 내용을 라인별 List 형태로 반환
	 * @param filename 읽어들일 파일 이름
	 * @param encoding 읽어들일 파일의 인코딩
	 * @return 파일의 한라인씩 순차적으로 저장된 리스트
	 */
	public static List<String> load2List(String filename, Charset encoding){		  
		BufferedReader br;
		List<String> resultList = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
			String line;
			while ((line = br.readLine()) != null) {
//				if(line.trim().length() == 0)continue;
				resultList.add(line);
			}
			br.close();
			br = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * 입력된 파일의 내용을 라인별 List 형태로 반환
	 * @param filename 읽어들일 파일 이름 (기본 인코딩 형식은 UTF-8)
	 * @return 파일의 한라인씩 순차적으로 저장된 리스트
	 */
	public static List<String> load2List(String filename){		  
		return load2List(filename,"UTF-8");
	}
	
	/**
	 * 입력된 filename에 list의 내용을 String 형태로 write
	 * @param <T>
	 * @param list
	 * @param filename
	 */
	public static <T> void writeList(List<T> list, String filename){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			int count=0;
			for (T line : list) {
				bw.write(line.toString());
				if(count != list.size()-1){
					bw.newLine();;
				}
				count++;
			}
			bw.close();
			bw = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 입력된 파일의 내용을 라인별 Set 형태로 반환
	 * @param filename 읽어들일 파일 이름
	 * @param encoding 읽어들일 파일의 인코딩
	 * @return 파일의 한라인씩 순차적으로 저장된 리스트
	 */
	public static Set<String> load2Set(String filename, String encoding){
		
		BufferedReader br;
		Set<String> resultSet = new HashSet<String>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
			String line;
			while ((line = br.readLine()) != null) {
				if(line.trim().length() == 0)continue;
				resultSet.add(line);
			}
			br.close();
			br =null;
			line = null;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 
		return resultSet;
	}
	
	/**
	 * 입력된 파일의 내용을 라인별 Set 형태로 반환
	 * @param filename 읽어들일 파일 이름 (기본 인코딩 형식은 UTF-8)
	 * @return 파일의 한라인씩 순차적으로 저장된 리스트
	 */
	public static Set<String> load2Set(String filename){
		return load2Set(filename,"UTF-8");
	}
	
	/**
	 * 입력된 filename에 Set의 내용을 String 형태로 write.
	 * @param set
	 * @param filename
	 */
	public static <T> void writeSet(Set<T> set, String filename){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			int count = 0;
			for (T line : set) {
				bw.write(line.toString());
				if(count != set.size() -1){
					bw.newLine();
				}
				count++;
			}
			bw.close();
			bw = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 입력된 파일의 내용을 Map형태로 반환. key, value 구분자는 \t.
	 * @param filename
	 * @return
	 */
	public static Map<String,String> load2Map(String filename){
		Map<String,String> resultMap = new HashMap<String, String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				if(line.trim().length() == 0)continue;
				String[] tmp = line.split("\t");
				if(tmp.length != 2){
					System.err.println("FileUtil.load2Map error!");
					System.err.println("This line can not split by '\t' : "+line);
				}
				resultMap.put(tmp[0], tmp[1]);
				tmp = null;
			}
			br.close();
			br = null;
			line = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * map의 내용을 filename에 write
	 * key와 value의 구분자는 \t.
	 * @param map
	 * @param filename
	 */
	public static <K, V> void writeMap(Map<K,V> map, String filename){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			
			Set<Entry<K,V>> mapEntrySet = map.entrySet();
			int count = 0;
			for (Entry<K, V> entry : mapEntrySet) {
				bw.write(entry.getKey().toString());
				bw.write("\t");
				bw.write(entry.getValue().toString());
				if(count != mapEntrySet.size()-1){
					bw.newLine();
				}
				count++;
			}
			bw.close();
			mapEntrySet = null;
			bw = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 입력된 rootPath의 하위에 있는 모든 파일을 List 형태로 반환(path 포함)
	 * @param rootPath
	 * @return
	 */
	public static List<String> getFileNames(String rootPath){
		List<String> filenames = new ArrayList<String>();
		File f = new File(rootPath);
		File[] files = f.listFiles();
		for (File file : files) {
			if(file.isDirectory()){
				filenames.addAll(getFileNames(file.getAbsolutePath()));
			}
			else{
				filenames.add(file.getAbsolutePath());
			}
		}
		files = null;
		f = null;
		return filenames;
	}
	
	/**
	 * 입력된 rootPath의 하위에 있는 모든 파일을 List 형태로 반환(path 포함)
	 * @param rootPath
	 * @return
	 */
	public static List<String> getFileNames(String rootPath,String suffix){
		List<String> filenames = new ArrayList<String>();
		File f = new File(rootPath);
		File[] files = f.listFiles();
		for (File file : files) {
			if(file.isDirectory()){
				filenames.addAll(getFileNames(file.getAbsolutePath(),suffix));
			}
			else{
				if(file.getName().endsWith(suffix)){
					filenames.add(file.getAbsolutePath());
				}
			}
		}
		files = null;
		f = null;
		return filenames;
	}
	
	/**
	 * 입력된 파일 이름을 삭제
	 * @param filename
	 * @return success
	 */
	public static boolean removeFile(String filename){
		File f = new File(filename);
		if(f.exists()){
			if(f.delete()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 입력된 이름의 파일을 생성
	 * @param filename
	 * @return
	 */
	public static boolean makeFile(String filename){
		File f = new File(filename);
		try {
			return f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 입력된 이름의 파일을 생성
	 * @param filename
	 * @param makePath filename에 해당하는 path가 없을 시 path 생성 여부
	 * @return
	 */
	public static boolean makeFile(String filename, boolean makePath){
		String path = FileUtil.getPath(filename);
		
		//makePath를 설정하면 path 먼저 생성
		if(makePath){
			boolean successMakePath = FileUtil.makePath(path);
			
			//path 생성 실패면 false 리턴
			if(!successMakePath)return false;
		}
		path = null;
		//makeFile
		return FileUtil.makeFile(filename);
	}
	
	/**
	 * 입력된 path 생성. 하위 path 포함하여 생성함.
	 * @param filename
	 * @return
	 */
	public static boolean makePath(String path){
		File f = new File(path);
		boolean successMakePath = f.mkdirs();
		if(successMakePath){
			return true;
		}
		f = null;
		return false;
	}
	
	/**
	 * 입력된 filename 중 path를 반환
	 * 실질적인 의미의 path가 아닌 입력된 filename에서 나타나는 path의 가장 마지막
	 * token을 제외한 상위 path를 리턴
	 * 예) path/to/where/ -> return path/to/
	 * @param filename
	 * @return
	 */
	public static String getPath(String filename){
		String pathSeparator;
		String appendSeparator;
		StringBuffer path = new StringBuffer();
		if(filename.contains("/")){
			pathSeparator = "/";
			appendSeparator = "/";
		}else{
			pathSeparator = "\\\\";
			appendSeparator = "\\";
		}
		
		String[] tmp = filename.split(pathSeparator);
		
		for(int i=0;i<tmp.length-1;i++){
			path.append(tmp[i]);
			path.append(appendSeparator);
		}
		tmp = null;
		pathSeparator = null;
		appendSeparator = null;
		if(path.toString().trim().length() == 0){
			return null;
		}
		return path.toString();
	}
	
	public static void splitFile(String filename,int splitCount,String suffix){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			
			int count = 0;
			int suffixNumber = 0;
			List<String> lines = new ArrayList<String>();
			while((line = br.readLine()) != null){
				if(count == splitCount){
					FileUtil.writeList(lines, filename+suffix+suffixNumber);
					count = 0;
					lines = null;
					lines = new ArrayList<String>();
					suffixNumber++;
					continue;
				}
				lines.add(line);
				count++;
			}
			br.close();
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	
}
