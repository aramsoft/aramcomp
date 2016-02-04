package aramframework.com.sym.ccm.zip.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.ccm.zip.domain.ZipVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 우편번호에 대한 서비스 구현클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

@Service
public class ZipManageService extends EgovAbstractServiceImpl {

	@Autowired
	private ZipManageMapper zipManageMapper;	

	@Autowired
	private EgovExcelService excelZipService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

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
		ZipVO resultVo = zipManageMapper.selectZipDetail(zipVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, zipVO); 
		return resultVo;
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
	 */
	public void insertExcelZip(InputStream file) {
//		zipManageDAO.deleteAllZip();
		try {
			String sqlId = "aramframework.com.sym.ccm.zip.service.ZipManageMapper.insertExcelZip";
			excelZipService.uploadExcel(sqlId, file, 1, (long) 5000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

	/**
	 * 우편번호 엑셀파일을 등록한다.(아람버전)
	 * 
	 * @param file
	 */
	public void insertExcelZipAram(InputStream file) {
//		zipManageDAO.deleteAllZipAram();
		try {
			excelZipService.uploadExcel("ZipManageDAO.insertExcelZipAram", file, 1, (long) 5000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
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
	 * 우편번호 전체를 삭제한다.
	 */
	public void deleteAllZip() {
		zipManageMapper.deleteAllZip();
	}

	/**
	 * 도로명에 대한 목록을 조회 한다.
	 */
	public Map<String, Object> selectRdNmList(ZipVO zipVO) {

		List<EgovMap> result = new ArrayList<EgovMap>();
		int cnt = 0;

		if( zipVO.getSearchKeyword() != null && !zipVO.getSearchKeyword().equals("") ) {
	        String indexName = AramProperties.getProperty("Globals.zipIndexPath");       
	        IndexSearcher searcher = null;         
	        TopDocs hits = null;                   

	        try {
	            IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexName)), true); // only searching, so read-only=true
	            searcher = new IndexSearcher(reader); 
	        } catch (Exception e) {
	        	LOG.error(e.getMessage());
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
	        	LOG.error(e.getMessage());
				throw new RuntimeException(e);
            }
            try {
				hits = searcher.search(query, zipVO.getRecordPerPage() + zipVO.getFirstIndex());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}   // run the query 
            LOG.debug("totalRecord = " + hits.totalHits);

/*
        	PhraseQuery pquery = new PhraseQuery();
        	pquery.setSlop(0);
            try {
            	String[] pharse = queryString.split(" ");
            	for(int i=0; i < pharse.length; i++) {
            		pquery.add(new Term("keywards", pharse[i]));
            	}
            } catch (Exception e) {                           
	        	LOG.error(e.getMessage());
	        	throw e;
            }
            hits = searcher.search(pquery, zipVO.getRecordPerPage() + zipVO.getFirstIndex());   // run the query 
            LOG.debug("totalRecord = " + hits.totalHits);
*/            
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
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

}
