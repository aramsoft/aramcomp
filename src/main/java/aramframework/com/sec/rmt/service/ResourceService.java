package aramframework.com.sec.rmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sec.rmt.dao.ResourceMapper;
import aramframework.com.sec.rmt.domain.ResourceVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 롤관리에 관한 ServiceImpl 클래스를 정의한다.
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
public class ResourceService extends EgovAbstractServiceImpl {

	@Autowired
	public ResourceMapper resourceMapper;
	
	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService resourceIdGnrService; 
	
	/**
	 * 등록된 모든 롤 정보 목록 조회
	 * 
	 * @param resourceVO
	 */
	public List<EgovMap> selectResourceAllList(ResourceVO resourceVO) {
		return resourceMapper.selectResourceAllList(resourceVO);
	}

	/**
	 * 등록된 롤 정보 목록 조회
	 * 
	 * @param resourceVO
	 */
	public List<EgovMap> selectResourceList(ResourceVO resourceVO) {
		return resourceMapper.selectResourceList(resourceVO);
	}

	/**
	 * 목록조회 카운트를 반환한다
	 * 
	 * @param resourceVO
	 */
	public int selectResourceListCnt(ResourceVO resourceVO) {
		return resourceMapper.selectResourceListCnt(resourceVO);
	}

	/**
	 * 등록된 롤 정보 조회
	 * 
	 * @param resourceVO
	 */
	public ResourceVO selectResource(ResourceVO resourceVO) {
		ResourceVO resultVo = resourceMapper.selectResource(resourceVO);
		// searchVO 이전 
		resultVo.setSearchVO(resourceVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
	 * 
	 * @param resourceVO
	 */
	public void insertResource(ResourceVO resourceVO) {
		String resourceTy = resourceVO.getResourceTy();
		if (resourceTy.equals("method"))
			resourceTy = "mtd";
		else if (resourceTy.equals("pointcut"))
			resourceTy = "pct";
		else
			resourceTy = "web";
		try {
			resourceVO.setResourceCode(resourceTy.concat("-").concat(resourceIdGnrService.getNextStringId()));
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}

		resourceMapper.insertResource(resourceVO);
	}

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
	 * 
	 * @param resourceVO
	 */
	public void updateResource(ResourceVO resourceVO) {
		resourceMapper.updateResource(resourceVO);
	}

	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param resourceVO
	 */
	public void deleteResource(ResourceVO resourceVO) {
		resourceMapper.deleteResource(resourceVO);
	}

	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param resourceVO
	 */
	public void deleteResources(String resourceCodes) {

		String[] strResourceCodes = resourceCodes.split(";");

		ResourceVO rmVO = new ResourceVO(); 
		for (int i = 0; i < strResourceCodes.length; i++) {
			rmVO.setResourceCode(strResourceCodes[i]);
			resourceMapper.deleteResource(rmVO);
		}
	}

}