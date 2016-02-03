package aramframework.mbl.com.mpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.mbl.com.mpa.domain.PhotoVO;
import aramframework.mbl.com.mpa.service.PhotoService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 사진 앨범에 대한 Service Interface를 구현한다.
 * 
 * 상세내용
 * - 사진에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 사진에 대한 조회기능은 목록, 상세조회로 구분된다.
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

@Service("photoService")
public class PhotoServiceImpl extends EgovAbstractServiceImpl implements PhotoService {

    /** PhotoMapper  */
	@Autowired
    private PhotoMapper photoMapper;    

    /** ID Generation */
	@Autowired
    private EgovIdGnrService photoIdGnrService; 

	@Autowired
	private FileMngUtil fileUtil;

    /**
     * 사진 목록을 조회 관련 비즈니스 구현 메서드
     * 
     * @param photoVO
     */
    public List<EgovMap> selectPhotoList(PhotoVO photoVO) {
        return photoMapper.selectPhotoList(photoVO);
    }

    /**
     * 사진 정보의 총 갯수를 조회한다.
     * 
     * @param photoVO
     */
    public int selectPhotoListCnt(PhotoVO photoVO) {
        return photoMapper.selectPhotoListCnt(photoVO);
    }

    /**
     * 사진 정보 상세 조회 관련 비즈니스 구현 메서드
     * 
     * @param photoVO
     */
    public PhotoVO selectPhoto(PhotoVO photoVO) {
    	PhotoVO resultVo = photoMapper.selectPhoto(photoVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, photoVO); 
		return resultVo;
    }

    /**
     * 사진 정보 등록 관련 비즈니스 구현 메서드
     * 
     * @param photoVO
     */
    public void insertPhoto(PhotoVO photoVO) {
    	try {
			photoVO.setSn(photoIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
        photoMapper.insertPhoto(photoVO);
    }

    /**
     * 사진 정보 수정 관련 비즈니스 구현 메서드
     * 
     * @param photoVO
     */
    public void updatePhoto(PhotoVO photoVO) {
        photoMapper.updatePhoto(photoVO);
    }

    /**
     * 사진 정보 삭제 관련 비즈니스 구현 메서드
     * 
     * @param photoVO
     */
    public void deletePhoto(PhotoVO photoVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(photoVO.getAtchFileId());

        photoMapper.deletePhoto(photoVO);
    }

}
