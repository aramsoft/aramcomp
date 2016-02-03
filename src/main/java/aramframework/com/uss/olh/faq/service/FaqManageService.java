package aramframework.com.uss.olh.faq.service;

import java.util.List;

import aramframework.com.uss.olh.faq.domain.FaqManageVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * FAQ를 처리하는 서비스 클래스
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

public interface FaqManageService {

	/**
	 * FAQ 글 목록을 조회한다.
	 * 
	 * @param faqManageVO
	 */
	List<EgovMap> selectFaqList(FaqManageVO faqManageVO);

	/**
	 * FAQ 글 총 갯수를 조회한다.
	 * 
	 * @param faqManageVO
	 */
	int selectFaqListCnt(FaqManageVO faqManageVO);

	/**
	 * FAQ 글을 조회한다.
	 * 
	 * @param faqManageVO
	 */
	FaqManageVO selectFaqListDetail(FaqManageVO faqManageVO);

	/**
	 * FAQ글ㅇ르 등록한다.
	 * 
	 * @param faqManageVO
	 */
	void insertFaqCn(FaqManageVO faqManageVO);

	/**
	 * FAQ 글을 수정한다.
	 * 
	 * @param faqManageVO
	 */
	void updateFaqCn(FaqManageVO faqManageVO);

	/**
	 * 조회수를 수정한다.
	 * 
	 * @param faqManageVO
	 */
	void updateFaqInqireCo(FaqManageVO faqManageVO);

	/**
	 * FAQ 글을 삭제한다.
	 * 
	 * @param faqManageVO
	 */
	void deleteFaqCn(FaqManageVO faqManageVO);

}
