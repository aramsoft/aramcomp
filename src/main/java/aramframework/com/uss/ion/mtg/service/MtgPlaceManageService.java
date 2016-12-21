package aramframework.com.uss.ion.mtg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.uss.ion.mtg.dao.MtgPlaceManageMapper;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceFxtrsVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceManageVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceResveVO;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 회의실관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class MtgPlaceManageService extends EgovAbstractServiceImpl {

	@Autowired
	private MtgPlaceManageMapper mtgPlaceManageMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService mtgPlaceManageIdGnrService; 

	/** ID Generation */
	@Autowired
	private EgovIdGnrService mtgPlaceResveIdGnrService; 

	@Autowired
	private FileMngUtil fileUtil;

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public List<EgovMap> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO) {
		return mtgPlaceManageMapper.selectMtgPlaceManageList(mtgPlaceManageVO);
	}

	/**
	 * 회의실관리목록 총 갯수를 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public int selectMtgPlaceManageListCnt(MtgPlaceManageVO mtgPlaceManageVO) {
		return mtgPlaceManageMapper.selectMtgPlaceManageListCnt(mtgPlaceManageVO);
	}

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public MtgPlaceManageVO selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) {
		MtgPlaceManageVO resultVo = mtgPlaceManageMapper.selectMtgPlaceManage(mtgPlaceManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(mtgPlaceManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceManageVO
	 * @param checkedMtgPlacesForInsert
	 */
	public void insertMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO, String checkedMtgPlacesForInsert) {

		String mtgPlaceId;
		try {
			mtgPlaceId = mtgPlaceManageIdGnrService.getNextStringId();
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		mtgPlaceManageVO.setMtgPlaceId(mtgPlaceId);

		mtgPlaceManageMapper.insertMtgPlaceManage(mtgPlaceManageVO);

		MtgPlaceFxtrsVO mtgPlaceFxtrsVO;
		String[] mtgPlacesValues = checkedMtgPlacesForInsert.split("[$]");
		String[] sTempMtgPlaces;
		String sTemp = null;

		if (checkedMtgPlacesForInsert != null && !checkedMtgPlacesForInsert.equals("")) {
			for (int i = 0; i < mtgPlacesValues.length; i++) {
				mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
				sTemp = mtgPlacesValues[i];
				sTempMtgPlaces = sTemp.split(",");
				mtgPlaceFxtrsVO.setMtgPlaceId(mtgPlaceId);
				mtgPlaceFxtrsVO.setFxtrsCd(sTempMtgPlaces[0]);
				mtgPlaceFxtrsVO.setQuantity(Integer.parseInt(sTempMtgPlaces[1]));
				mtgPlaceManageMapper.insertMtgPlaceFxtrs(mtgPlaceFxtrsVO);
			}
		}
	}

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 * 
	 * @param mtgPlaceManageVO
	 * @param checkedMtgPlacesForInsert
	 */
	public void updateMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO, String checkedMtgPlacesForInsert) {

		mtgPlaceManageMapper.updateMtgPlaceManage(mtgPlaceManageVO);
		String sMtgPlaceId = mtgPlaceManageVO.getMtgPlaceId();

		MtgPlaceFxtrsVO mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
		mtgPlaceFxtrsVO.setMtgPlaceId(sMtgPlaceId);
		mtgPlaceManageMapper.deleteMtgPlaceFxtrs(mtgPlaceFxtrsVO);

		String[] mtgPlacesValues = checkedMtgPlacesForInsert.split("[$]");
		String[] sTempMtgPlaces;
		String sTemp = null;
		if (checkedMtgPlacesForInsert != null && !checkedMtgPlacesForInsert.equals("")) {
			for (int i = 0; i < mtgPlacesValues.length; i++) {
				mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
				sTemp = mtgPlacesValues[i];
				sTempMtgPlaces = sTemp.split(",");
				mtgPlaceFxtrsVO.setMtgPlaceId(sMtgPlaceId);
				mtgPlaceFxtrsVO.setFxtrsCd(sTempMtgPlaces[0]);
				mtgPlaceFxtrsVO.setQuantity(Integer.parseInt(sTempMtgPlaces[1]));
				mtgPlaceManageMapper.insertMtgPlaceFxtrs(mtgPlaceFxtrsVO);
			}
		}
	}

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	public void deleteMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) {

		String sMtgPlaceId = mtgPlaceManageVO.getMtgPlaceId();
		MtgPlaceFxtrsVO mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
		mtgPlaceFxtrsVO.setMtgPlaceId(sMtgPlaceId);
		mtgPlaceManageMapper.deleteMtgPlaceFxtrs(mtgPlaceFxtrsVO);

		mtgPlaceManageMapper.deleteMtgPlaceManage(mtgPlaceManageVO);

		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(mtgPlaceManageVO.getAtchFileId());
	}

	/******** 회의실 예약 관리 *************/

	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실 예약 목록을 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public List<EgovMap> selectMtgPlaceResveManageList(MtgPlaceResveVO mtgPlaceResveVO) {

		MtgPlaceManageVO mtgPlaceManageVO = new MtgPlaceManageVO();
		if( mtgPlaceResveVO.getTrgetId() != null ) {
			mtgPlaceManageVO.setTrgetId(mtgPlaceResveVO.getTrgetId());
		}
		List<MtgPlaceManageVO> result = mtgPlaceManageMapper.selectMtgPlaceIDList(mtgPlaceManageVO);
		int num = result.size();

		List<EgovMap> list = new ArrayList<EgovMap>();
		mtgPlaceResveVO.setResveDe(StringUtil.removeMinusChar(mtgPlaceResveVO.getResveDe()));
		for (int i = 0; i < num; i++) {
			mtgPlaceManageVO = result.get(i);
			mtgPlaceResveVO.setMtgPlaceId(mtgPlaceManageVO.getMtgPlaceId());
			list.add(mtgPlaceManageMapper.selectMtgPlaceResveManageList(mtgPlaceResveVO));
		}

		return list;
	}

	/**
	 * 등록된 회의실 예약 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public MtgPlaceResveVO selectMtgPlaceResveDetail(MtgPlaceResveVO mtgPlaceResveVO) {
		MtgPlaceResveVO resultVo = mtgPlaceManageMapper.selectMtgPlaceResveDetail(mtgPlaceResveVO);
		// searchVO 이전 
		resultVo.setSearchVO(mtgPlaceResveVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 회의실 예약정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void insertMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) {
		try {
			mtgPlaceResveVO.setResveId(mtgPlaceResveIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		mtgPlaceManageMapper.insertMtgPlaceResve(mtgPlaceResveVO);
	}

	/**
	 * 기 등록된 회의실 예약정보를 수정한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void updateMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) {
		mtgPlaceManageMapper.updateMtgPlaceResve(mtgPlaceResveVO);
	}

	/**
	 * 기 등록된 회의실 예약정보를 삭제한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public void deleteMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) {
		mtgPlaceManageMapper.deleteMtgPlaceResve(mtgPlaceResveVO);
	}

	/**
	 * 회의실 중복여부 체크.
	 * 
	 * @param mtgPlaceResveVO
	 */
	public int mtgPlaceResveDplactCeck(MtgPlaceResveVO mtgPlaceResveVO) {
		return mtgPlaceManageMapper.mtgPlaceResveDplactCeck(mtgPlaceResveVO);
	}

	/******** 회의실 비품 관리 *************/
	/**
	 * 비품정보 목록을 조회한다
	 * 
	 * @param mtgPlaceFxtrsVO
	 */
	public List<MtgPlaceFxtrsVO> selectFxtrsManageList(MtgPlaceFxtrsVO mtgPlaceFxtrsVO) {
		return mtgPlaceManageMapper.selectFxtrsManageList(mtgPlaceFxtrsVO);
	}
	
}
