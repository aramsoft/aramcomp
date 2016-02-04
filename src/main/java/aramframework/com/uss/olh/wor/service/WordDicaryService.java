package aramframework.com.uss.olh.wor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olh.wor.domain.WordDicaryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 용어사전을 처리하는 비즈니스 구현 클래스
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
public class WordDicaryService extends EgovAbstractServiceImpl {

	@Autowired
	private WordDicaryMapper wordDicaryMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService wordDicaryIdGnrService; 

	/**
	 * 글 목록을 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	public List<EgovMap> selectWordDicaryList(WordDicaryVO wordDicaryVO) {
		return wordDicaryMapper.selectWordDicaryList(wordDicaryVO);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	public int selectWordDicaryListCnt(WordDicaryVO wordDicaryVO) {
		return wordDicaryMapper.selectWordDicaryListCnt(wordDicaryVO);
	}

	/**
	 * 글을 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	public WordDicaryVO selectWordDicaryDetail(WordDicaryVO wordDicaryVO) {
		WordDicaryVO resultVo = wordDicaryMapper.selectWordDicaryDetail(wordDicaryVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, wordDicaryVO); 
		return resultVo;
	}

	/**
	 * 글을 등록한다.
	 * 
	 * @param wordDicaryVO
	 */
	public void insertWordDicary(WordDicaryVO wordDicaryVO) {
		try {
			wordDicaryVO.setWordId(wordDicaryIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		wordDicaryMapper.insertWordDicary(wordDicaryVO);
	}

	/**
	 * 글을 수정한다.
	 * 
	 * @param wordDicaryVO
	 */
	public void updateWordDicary(WordDicaryVO wordDicaryVO) {
		wordDicaryMapper.updateWordDicary(wordDicaryVO);
	}

	/**
	 * 글을 삭제한다.
	 * 
	 * @param wordDicaryVO
	 */
	public void deleteWordDicary(WordDicaryVO wordDicaryVO) {
		wordDicaryMapper.deleteWordDicary(wordDicaryVO);
	}

}
