package aramframework.com.utl.pao.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.utl.pao.domain.PrntngOutptVO;

/**
 * 전자관인에서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface PrntngOutptMapper  {

	/**
	 * 주어진 조건에 따른 공통코드를 불러온다.
	 * 
	 * @param prntngOutptVO
	 * @return
	 */
	public PrntngOutptVO selectErncsl(PrntngOutptVO prntngOutptVO);

}
