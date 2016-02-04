package aramframework.com.utl.sys.trm.service;


/**
 * 송수신모니터링을 위한 Check interface
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

public interface TrsmrcvMntrngChecker {

	/**
	 * 송수신모니터링을 수행한다.
	 * 
	 * 연계ID를 이용하여 연계기관과 통신에 필요한 정보를 얻은 다음 연계기관과 통신을 수행한다. 통신결과를
	 * TrsmrcvMntrngResult 클래스 객체에 담아서 리턴한다.
	 * 
	 * 통신결과가 true일때 : TrsmrcvMntrngResult의 nrmltAt에 true, cause에 null을 저장. 통신결과가
	 * false일때: TrsmrcvMntrngResult의 nrmltAt에 false, cause에 에러원인 Exception을
	 * 저장한다.
	 * 
	 * @return 모니터링결과
	 * 
	 * @param cntcId
	 *            모니터링 대상 연계ID
	 * 
	 */
	public TrsmrcvMntrngResult check(String cntcId);
}
