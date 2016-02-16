package aramframework.com.uss.ion.msi.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.uss.ion.msi.dao.MainImageMapper;
import aramframework.com.uss.ion.msi.domain.MainImageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 메인화면이미지에 대한 ServiceImpl 클래스를 정의한다.
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
public class MainImageService extends EgovAbstractServiceImpl {

	@Autowired
	private MainImageMapper mainImageMapper;	

	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService mainImageIdGnrService; 

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public List<EgovMap> selectMainImageList(MainImageVO mainImageVO) {
		return mainImageMapper.selectMainImageList(mainImageVO);
	}

	/**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public int selectLoginScrinImageListCnt(MainImageVO mainImageVO) {
		return mainImageMapper.selectMainImageListCnt(mainImageVO);
	}

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public MainImageVO selectMainImage(MainImageVO mainImageVO) {
		MainImageVO resultVo = mainImageMapper.selectMainImage(mainImageVO);
		// searchVO 이전 
		resultVo.setSearchVO(mainImageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * 
	 * @param mainImageVO
	 */
	public void insertMainImage(MainImageVO mainImageVO) {
		try {
			mainImageVO.setImageId(mainImageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		mainImageMapper.insertMainImage(mainImageVO);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * 
	 * @param mainImageVO
	 */
	public void updateMainImage(MainImageVO mainImageVO) {
		mainImageMapper.updateMainImage(mainImageVO);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * 
	 * @param mainImageVO
	 */
	public void deleteMainImage(MainImageVO mainImageVO) {
		deleteMainImageFile(mainImageVO);
		mainImageMapper.deleteMainImage(mainImageVO);
	}

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제한다.
	 * 
	 * @param mainImageVO
	 */
	public void deleteMainImageFile(MainImageVO mainImageVO) {
		FileVO fileVO = (FileVO) mainImageMapper.selectMainImageFile(mainImageVO);
		File file = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
		file.delete();
	}

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param mainImageVO
	 */
	public List<EgovMap> selectMainImageResult(MainImageVO mainImageVO) {
		return mainImageMapper.selectMainImageResult(mainImageVO);
	}
	
}
