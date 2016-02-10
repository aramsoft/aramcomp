package aramframework.com.utl.pao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.utl.pao.dao.PrntngOutptMapper;
import aramframework.com.utl.pao.domain.PrntngOutptVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관인이미지에 대한 서비스 구현클래스를 정의한다
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
public class PrntngOutptService extends EgovAbstractServiceImpl {

	@Autowired  
	private PrntngOutptMapper prntngOutptMapper;

	/**
	 * 관인이미지를 조회한다.
	 */
	public PrntngOutptVO selectErncsl(PrntngOutptVO prntngOutptVO){
		PrntngOutptVO resultVo = prntngOutptMapper.selectErncsl(prntngOutptVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, prntngOutptVO); 
		return resultVo;
	}

}
