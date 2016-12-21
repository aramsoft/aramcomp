package aramframework.com.uss.ion.lsi.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.uss.ion.lsi.dao.LoginScrinImageMapper;
import aramframework.com.uss.ion.lsi.domain.LoginScrinImageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 로그인화면이미지에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class LoginScrinImageService extends EgovAbstractServiceImpl {

	@Autowired
	private LoginScrinImageMapper loginScrinImageMapper;	

	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService loginScrinImageIdGnrService; 

	/**
	 * 로그인화면이미지정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public List<EgovMap> selectLoginScrinImageList(LoginScrinImageVO loginScrinImageVO) {
		return loginScrinImageMapper.selectLoginScrinImageList(loginScrinImageVO);
	}

	/**
	 * 로그인화면이미지목록 총 갯수를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public int selectLoginScrinImageListCnt(LoginScrinImageVO loginScrinImageVO) {
		return loginScrinImageMapper.selectLoginScrinImageListCnt(loginScrinImageVO);
	}

	/**
	 * 등록된 로그인화면이미지의 상세정보를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public LoginScrinImageVO selectLoginScrinImage(LoginScrinImageVO loginScrinImageVO) {
		LoginScrinImageVO resultVo = loginScrinImageMapper.selectLoginScrinImage(loginScrinImageVO);
		// searchVO 이전 
		resultVo.setSearchVO(loginScrinImageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 로그인화면이미지정보를 신규로 등록한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void insertLoginScrinImage(LoginScrinImageVO loginScrinImageVO) {
		try {
			loginScrinImageVO.setImageId(loginScrinImageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		loginScrinImageMapper.insertLoginScrinImage(loginScrinImageVO);
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void updateLoginScrinImage(LoginScrinImageVO loginScrinImageVO) {
		loginScrinImageMapper.updateLoginScrinImage(loginScrinImageVO);
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void deleteLoginScrinImage(LoginScrinImageVO loginScrinImageVO) {
		deleteLoginScrinImageFile(loginScrinImageVO);
		loginScrinImageMapper.deleteLoginScrinImage(loginScrinImageVO);
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void deleteLoginScrinImages(String imageIds) {
		String[] strImageIds = imageIds.split(";");
		LoginScrinImageVO lsiVO = new LoginScrinImageVO();
		for (int i = 0; i < strImageIds.length; i++) {
			lsiVO.setImageId(strImageIds[i]);
			deleteLoginScrinImage(lsiVO);
		}
	}

	/**
	 * 기 등록된 로그인화면이미지정보의 파일을 삭제한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void deleteLoginScrinImageFile(LoginScrinImageVO loginScrinImageVO) {
		FileVO fileVO = (FileVO) loginScrinImageMapper.selectLoginScrinImageFile(loginScrinImageVO);
		File file = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
		file.delete();
	}

	/**
	 * 로그인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public List<EgovMap> selectLoginScrinImageResult(LoginScrinImageVO loginScrinImageVO) {
		return loginScrinImageMapper.selectLoginScrinImageResult(loginScrinImageVO);
	}
	
}
