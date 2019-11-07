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
 * 자원관리에 관한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class ResourceService extends EgovAbstractServiceImpl {

	@Autowired
	public ResourceMapper resourceMapper;
	
	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService resourceIdGnrService; 
	
	/**
	 * 등록된 모든 자원 정보 목록 조회
	 * 
	 * @param resourceVO
	 */
	public List<EgovMap> selectResourceAllList(ResourceVO resourceVO) {
		return resourceMapper.selectResourceAllList(resourceVO);
	}

	/**
	 * 등록된 자원 정보 목록 조회
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
	 * 등록된 자원 정보 조회
	 * 
	 * @param resourceVO
	 */
	public ResourceVO selectResource(ResourceVO resourceVO) {
		return resourceMapper.selectResource(resourceVO);
	}

	/**
	 * 자원을 등록
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
	 * 자원을 수정
	 * 
	 * @param resourceVO
	 */
	public void updateResource(ResourceVO resourceVO) {
		resourceMapper.updateResource(resourceVO);
	}

	/**
	 * 자원 삭제
	 * 
	 * @param resourceVO
	 */
	public void deleteResource(ResourceVO resourceVO) {
		resourceMapper.deleteResource(resourceVO);
	}

	/**
	 * 자원 삭제
	 * 
	 * @param resourceVO
	 */
	public void deleteResources(String[] resourceCodes) {
		ResourceVO rmVO = new ResourceVO(); 
		for (int i = 0; i < resourceCodes.length; i++) {
			rmVO.setResourceCode(resourceCodes[i]);
			resourceMapper.deleteResource(rmVO);
		}
	}

}