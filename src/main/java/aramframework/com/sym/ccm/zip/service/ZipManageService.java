package aramframework.com.sym.ccm.zip.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.ccm.zip.dao.ZipManageMapper;
import aramframework.com.sym.ccm.zip.domain.ZipAramVO;
import aramframework.com.sym.ccm.zip.domain.ZipVO;
import aramframework.com.sym.ccm.zip.excel.ExcelZipAramMapping;
import aramframework.com.sym.ccm.zip.excel.ExcelZipMapping;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 우편번호에 대한 서비스 구현클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class ZipManageService extends EgovAbstractServiceImpl {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ZipManageMapper zipManageMapper;	

	/**
	 * 우편번호 목록을 조회한다.
	 * 
	 * @param zipVO
	 */
	public List<EgovMap> selectZipList(ZipVO zipVO) {
		return zipManageMapper.selectZipList(zipVO);
	}

	/**
	 * 우편번호 총 갯수를 조회한다.
	 * 
	 * @param zipVO
	 */
	public int selectZipListCnt(ZipVO zipVO) {
		return zipManageMapper.selectZipListCnt(zipVO);
	}

	/**
	 * 우편번호 상세항목을 조회한다.
	 * 
	 * @param zipVO
	 */
	public ZipVO selectZipDetail(ZipVO zipVO) {
		return zipManageMapper.selectZipDetail(zipVO);
	}

	/**
	 * 우편번호를 등록한다.
	 * 
	 * @param zipVO
	 */
	public void insertZip(ZipVO zipVO) {
		zipManageMapper.insertZip(zipVO);
	}

	/**
	 * 우편번호 엑셀파일을 등록한다.
	 * 
	 * @param file
	 * @param cmmntyId
	 */
	public void syncExcelZip(InputStream fis) throws Exception {

		ExcelZipMapping mapping = new ExcelZipMapping();
			
		Workbook workbook = new XSSFWorkbook(fis);
		int sheetNum = workbook.getNumberOfSheets();
		for (int k = 0; k < sheetNum; k++) {
			Sheet sheet = workbook.getSheetAt(k);
			int rows = sheet.getPhysicalNumberOfRows();
			if( rows == 0 ) continue;
			
			mapping.setCells(sheet.getRow(0));	// cells 수 설정
			for (int r = 1; r < rows; r++) {
    			Row row = sheet.getRow(r);
    			if (row != null) {
    				ZipVO vo = (ZipVO)mapping.mappingColumn(row);
 
    				ZipVO resultVO = zipManageMapper.selectZipDetail(vo);
    		    	if ( resultVO == null ) {
    		    		zipManageMapper.insertZip(vo);
    		    	} else {
    		    		zipManageMapper.updateZip(vo);
    		    	}	
    			}
			}
		}
		workbook.close();
	}


	/**
	 * 우편번호 엑셀파일을 등록한다.
	 * 
	 * @param file
	 * @param cmmntyId
	 */
	public void syncExcelZipAram(InputStream fis) throws Exception {

		ExcelZipAramMapping mapping = new ExcelZipAramMapping();
			
		Workbook workbook = new XSSFWorkbook(fis);
		int sheetNum = workbook.getNumberOfSheets();
		for (int k = 0; k < sheetNum; k++) {
			Sheet sheet = workbook.getSheetAt(k);
			int rows = sheet.getPhysicalNumberOfRows();
			if( rows == 0 ) continue;
			
			mapping.setCells(sheet.getRow(0));	// cells 수 설정
			for (int r = 1; r < rows; r++) {
    			Row row = sheet.getRow(r);
    			if (row != null) {
    				ZipAramVO vo = (ZipAramVO)mapping.mappingColumn(row);
 
    				ZipAramVO resultVO = zipManageMapper.selectZipDetailAram(vo);
    		    	if ( resultVO == null ) {
    		    		zipManageMapper.insertZipAram(vo);
    		    	} else {
    		    		zipManageMapper.updateZipAram(vo);
    		    	}	
    			}
			}
		}
		workbook.close();
	}

	/**
	 * 우편번호를 수정한다.
	 */
	public void updateZip(ZipVO zipVO) {
		zipManageMapper.updateZip(zipVO);
	}

	/**
	 * 우편번호를 삭제한다.
	 */
	public void deleteZip(ZipVO zipVO) {
		zipManageMapper.deleteZip(zipVO);
	}

	/**
	 * 도로명에 대한 목록을 조회 한다.
	 */
	public Map<String, Object> selectRdNmList(ZipVO zipVO) {

		List<EgovMap> result = new ArrayList<EgovMap>();
		int cnt = 0;
/*
		if( zipVO.getSearchKeyword() != null && !zipVO.getSearchKeyword().equals("") ) {
	        String indexName = AramProperties.getProperty("Globals.zipIndexPath");       
	        IndexSearcher searcher = null;         
	        TopDocs hits = null;                   

	        try {
	            IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexName)), true); // only searching, so read-only=true
	            searcher = new IndexSearcher(reader); 
	        } catch (Exception e) {
	        	logger.error(e.getMessage());
				throw new RuntimeException(e);
	        }	  

	        String queryString = zipVO.getSearchKeyword();           
	        Query query = null;                     
	        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_29);           
            try {
            	QueryParser qp = new QueryParser(Version.LUCENE_29, "keywords", analyzer);
            	qp.setDefaultOperator(QueryParser.AND_OPERATOR);
				query = qp.parse(queryString); 				  
            } catch (ParseException e) {                           
	        	logger.error(e.getMessage());
				throw new RuntimeException(e);
            }
            try {
				hits = searcher.search(query, zipVO.getRecordPerPage() + zipVO.getFirstIndex());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}   // run the query 
            logger.debug("totalRecord = " + hits.totalHits);

//        	PhraseQuery pquery = new PhraseQuery();
//        	pquery.setSlop(0);
//          try {
//            	String[] pharse = queryString.split(" ");
//            	for(int i=0; i < pharse.length; i++) {
//            		pquery.add(new Term("keywards", pharse[i]));
//            	}
//          } catch (Exception e) {                           
//	        	logger.error(e.getMessage());
//	        	throw e;
//          }
//          hits = searcher.search(pquery, zipVO.getRecordPerPage() + zipVO.getFirstIndex());   // run the query 
//          logger.debug("totalRecord = " + hits.totalHits);
            
    		EgovMap target = null;
    		int lastIndex = zipVO.getLastIndex();
    		if( lastIndex > hits.totalHits ) lastIndex = hits.totalHits;
            try {
	            for (int i = zipVO.getFirstIndex(); i < lastIndex; i++) {  // for each element
	                Document doc = searcher.doc(hits.scoreDocs[i].doc);                    //get the next document 
	          		target = new EgovMap();
	           		target.put("zip", doc.get("zipcode"));
	           		target.put("addr1", doc.get("address1")); 
	           		target.put("addr2", doc.get("address2")); 
	           		result.add(target);
	            }
			} catch (IOException e) {
				throw new RuntimeException(e);
			}   
	        cnt = hits.totalHits;
		} 
*/		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

}
