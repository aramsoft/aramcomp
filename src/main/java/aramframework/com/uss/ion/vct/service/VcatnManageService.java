package aramframework.com.uss.ion.vct.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.ism.domain.InfrmlSanctnVO;
import aramframework.com.uss.ion.ism.service.InfrmlSanctnService;
import aramframework.com.uss.ion.vct.dao.VcatnManageMapper;
import aramframework.com.uss.ion.vct.domain.IndvdlYrycManageVO;
import aramframework.com.uss.ion.vct.domain.VcatnManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 휴가관리에 대한 ServiceImpl 클래스를 정의한다.
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
public class VcatnManageService extends EgovAbstractServiceImpl {

	@Autowired
	private VcatnManageMapper vcatnManageMapper;	

	@Autowired
	protected InfrmlSanctnService infrmlSanctnService;

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public List<EgovMap> selectVcatnManageList(VcatnManageVO vcatnManageVO) {
		return  vcatnManageMapper.selectVcatnManageList(vcatnManageVO);
	}

	/**
	 * 휴가관리목록 총 갯수를 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public int selectVcatnManageListCnt(VcatnManageVO vcatnManageVO) {
		return vcatnManageMapper.selectVcatnManageListCnt(vcatnManageVO);
	}

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO) {
		VcatnManageVO resultVo = vcatnManageMapper.selectVcatnManage(vcatnManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, vcatnManageVO); 
		return resultVo;
	}

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 * 
	 * @param vcatnManageVO
	 * @result 01 : 입력성공, 02 : 연차휴가 등록실패(잔여연차 부족), 03: 반차휴가 등록실패(잔여연차 부족)
	 */
	public String insertVcatnManage(VcatnManageVO vcatnManageVO) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String sYear = Integer.toString(cal.get(java.util.Calendar.YEAR));
		String sMonth = Integer.toString(cal.get(java.util.Calendar.MONTH) + 1);
		if (sMonth.length() == 1)
			sMonth = "0" + sMonth;
		String sDay = Integer.toString(cal.get(java.util.Calendar.DATE));

		vcatnManageVO.setOccrrncYear(sYear);
		vcatnManageVO.setReqstDe(sYear + sMonth + sDay);
		/*
		 * 휴가 승인처리 신청 infrmlSanctnService.insertInfrmlSanctn("000", vcatnManage);
		 */
		InfrmlSanctnVO infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(vcatnManageVO));

		// InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn("003", vcatnManage);
		vcatnManageVO.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		IndvdlYrycManageVO indvdlYrycManageVO1 = selectIndvdlYrycManage(loginVO.getUniqId());
		double iUseYrycCo = indvdlYrycManageVO1.getUseYrycCo(); // 연차테이블의 사용 연차갯수
		double iRemndrYrycCo = indvdlYrycManageVO1.getRemndrYrycCo(); // 연차테이블의 잔여 연차갯수
		double iCountYryc = 0.0;
		/*
		 * 시작일자 와 종료일자 사이의 일자 갯수 - 공휴일 or 주말 제외
		 */
		// 휴가구분이 연차인 경우
		if (vcatnManageVO.getVcatnSe().equals("01")) {
			// 연차 휴가 연도 체크
			if (!getVcatnYearSE(vcatnManageVO)) {
				return "09";
			}
			iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getEndde());
			if (iCountYryc == 0)
				return "99"; // 연차설정오류
			else if ((iRemndrYrycCo - iCountYryc) < 0)
				return "02";
			else {
				vcatnManageMapper.insertVcatnManage(vcatnManageVO);
				
				IndvdlYrycManageVO indvdlYrycManageVO = new IndvdlYrycManageVO();
				indvdlYrycManageVO.setUseYrycCo(iUseYrycCo + iCountYryc);
				indvdlYrycManageVO.setRemndrYrycCo(iRemndrYrycCo - iCountYryc);
				indvdlYrycManageVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycManageVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycManageVO.setUsid(vcatnManageVO.getApplcntId());
				updateIndvdlYrycManage(indvdlYrycManageVO);
				return "01";
			}
		}
		// 휴가구분이 반차인 경우
		else if (vcatnManageVO.getVcatnSe().equals("02")) {
			// 연차 휴가 연도 체크
			if (!getVcatnYearSE(vcatnManageVO)) {
				return "09";
			}
			iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getBgnde()); 
			// 반차는 시작일자 종료일자 동일함. 시작일자로만 체크
			if (iCountYryc == 0)
				return "99"; // 연차설정오류
			else if ((iRemndrYrycCo - 0.5) < 0)
				return "03";
			else {
				vcatnManageMapper.insertVcatnManage(vcatnManageVO);

				IndvdlYrycManageVO indvdlYrycManageVO = new IndvdlYrycManageVO();
				indvdlYrycManageVO.setUseYrycCo(iUseYrycCo + 0.5);
				indvdlYrycManageVO.setRemndrYrycCo(iRemndrYrycCo - 0.5);
				indvdlYrycManageVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycManageVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycManageVO.setUsid(vcatnManageVO.getApplcntId());
				updateIndvdlYrycManage(indvdlYrycManageVO);
				return "01";
			}
		} else {
			vcatnManageMapper.insertVcatnManage(vcatnManageVO);
			return "01";
		}
	}

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 * 
	 * @param vcatnManageVO
	 */
	public String updateVcatnManage(VcatnManageVO vcatnManageVO) {
		vcatnManageMapper.updateVcatnManage(vcatnManageVO);
		return "01";
	}

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 * 
	 * @param vcatnManageVO
	 */
	public void deleteVcatnManage(VcatnManageVO vcatnManageVO) {
		/*
		 * 휴가 승인처리 삭제 
		 */
		infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(vcatnManageVO));

		// 개인연차조회
		IndvdlYrycManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManageVO.getApplcntId());
		double iUseYrycCo = vcatnManageVO1.getUseYrycCo(); // 연차테이블의 사용 연차갯수
		double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); // 연차테이블의 잔여 연차갯수
		double iCountYryc = 0.0;
		/*
		 * 시작일자 와 종료일자 사이의 일자 갯수 - 공휴일 or 주말 제외
		 */
		// 휴가구분이 연차인 경우
		if (vcatnManageVO.getVcatnSe().equals("01")) {

			IndvdlYrycManageVO indvdlYrycManageVO = new IndvdlYrycManageVO();
			iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getEndde());
			indvdlYrycManageVO.setUseYrycCo(iUseYrycCo - iCountYryc);
			indvdlYrycManageVO.setRemndrYrycCo(iRemndrYrycCo + iCountYryc);
			indvdlYrycManageVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
			indvdlYrycManageVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
			indvdlYrycManageVO.setUsid(vcatnManageVO.getApplcntId());
			updateIndvdlYrycManage(indvdlYrycManageVO);

			vcatnManageMapper.deleteVcatnManage(vcatnManageVO);
		}
		// 휴가구분이 반차인 경우
		else if (vcatnManageVO.getVcatnSe().equals("02")) {

			IndvdlYrycManageVO indvdlYrycManageVO = new IndvdlYrycManageVO();
			indvdlYrycManageVO.setUseYrycCo(iUseYrycCo - 0.5);
			indvdlYrycManageVO.setRemndrYrycCo(iRemndrYrycCo + 0.5);
			indvdlYrycManageVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
			indvdlYrycManageVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
			indvdlYrycManageVO.setUsid(vcatnManageVO.getApplcntId());
			updateIndvdlYrycManage(indvdlYrycManageVO);

			vcatnManageMapper.deleteVcatnManage(vcatnManageVO);
		} else {
			vcatnManageMapper.deleteVcatnManage(vcatnManageVO);
		}
	}

	/**
	 * 휴가일자 중복여부 체크
	 * 
	 * @param vcatnManageVO
	 */
	public int selectVcatnManageDplctAt(VcatnManageVO vcatnManageVO) {
		return vcatnManageMapper.selectVcatnManageDplctAt(vcatnManageVO);
	}

	/*** 승인처리관련 ***/
	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public List<EgovMap> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO) {
		return  vcatnManageMapper.selectVcatnManageConfmList(vcatnManageVO);
	}

	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록 총 갯수를 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	public int selectVcatnManageConfmListCnt(VcatnManageVO vcatnManageVO) {
		return vcatnManageMapper.selectVcatnManageConfmListCnt(vcatnManageVO);
	}

	/**
	 * 신청된 휴가를 승인처리한다.
	 * 
	 * @param vcatnManageVO
	 */
	public void updateVcatnManageConfm(VcatnManageVO vcatnManageVO) {

		if (vcatnManageVO.getConfmAt().equals("C")) {
			/*
			 * 승인처리
			 */
			InfrmlSanctnVO infrmlSanctnVO = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(vcatnManageVO));

			vcatnManageVO.setSanctnDt(infrmlSanctnVO.getSanctnDt());
			vcatnManageVO.setConfmAt(infrmlSanctnVO.getConfmAt());

			vcatnManageMapper.updateVcatnManageConfm(vcatnManageVO);

		} else if (vcatnManageVO.getConfmAt().equals("R")) {
			/*
			 * 반려처리
			 */
			InfrmlSanctnVO infrmlSanctnVO = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(vcatnManageVO));

			vcatnManageVO.setSanctnDt(infrmlSanctnVO.getSanctnDt());
			vcatnManageVO.setConfmAt(infrmlSanctnVO.getConfmAt());

			// 연차 반환처리
			// 개인연차조회
			IndvdlYrycManageVO vcatnManageVO1 = selectIndvdlYrycManage(vcatnManageVO.getApplcntId());
			double iUseYrycCo = vcatnManageVO1.getUseYrycCo(); // 연차테이블의 사용 연차갯수
			double iRemndrYrycCo = vcatnManageVO1.getRemndrYrycCo(); // 연차테이블의 잔여 연차갯수
			double iCountYryc = 0.0;

			/*
			 * 시작일자 와 종료일자 사이의 일자 갯수 - 공휴일 or 주말 제외
			 */
			// 휴가구분이 연차인 경우
			if (vcatnManageVO.getVcatnSe().equals("01")) {

				IndvdlYrycManageVO indvdlYrycManageVO = new IndvdlYrycManageVO();
				iCountYryc = getDateCalc(vcatnManageVO.getBgnde(), vcatnManageVO.getEndde());
				indvdlYrycManageVO.setUseYrycCo(iUseYrycCo - iCountYryc);
				indvdlYrycManageVO.setRemndrYrycCo(iRemndrYrycCo + iCountYryc);
				indvdlYrycManageVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycManageVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycManageVO.setUsid(vcatnManageVO.getApplcntId());
				updateIndvdlYrycManage(indvdlYrycManageVO);
			}
			// 휴가구분이 반차인 경우
			else if (vcatnManageVO.getVcatnSe().equals("02")) {

				IndvdlYrycManageVO indvdlYrycManageVO = new IndvdlYrycManageVO();
				indvdlYrycManageVO.setUseYrycCo(iUseYrycCo - 0.5);
				indvdlYrycManageVO.setRemndrYrycCo(iRemndrYrycCo + 0.5);
				indvdlYrycManageVO.setLastUpdusrId(vcatnManageVO.getApplcntId());
				indvdlYrycManageVO.setOccrrncYear(vcatnManageVO.getOccrrncYear());
				indvdlYrycManageVO.setUsid(vcatnManageVO.getApplcntId());
				updateIndvdlYrycManage(indvdlYrycManageVO);
			}

			vcatnManageMapper.updateVcatnManageConfm(vcatnManageVO);
		}
	}

	/*** 연차관련 ***/
	/**
	 * 개인별 연차관리의 상세정보를 조회한다.
	 * 
	 * @param sUsid
	 */
	public IndvdlYrycManageVO selectIndvdlYrycManage(String sUsid) {

		IndvdlYrycManageVO indvdlYrycManageVO = new IndvdlYrycManageVO();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String sYear = Integer.toString(cal.get(java.util.Calendar.YEAR));

		indvdlYrycManageVO.setOccrrncYear(sYear);
		indvdlYrycManageVO.setUsid(sUsid);

		return vcatnManageMapper.selectIndvdlYrycManage(indvdlYrycManageVO);
	}

	/**
	 * 개인별 연차를 수정 처리한다.
	 * 
	 * @param indvdlYrycManageVO
	 */
	public void updateIndvdlYrycManage(IndvdlYrycManageVO indvdlYrycManageVO) {
		vcatnManageMapper.updateIndvdlYrycManage(indvdlYrycManageVO);
	}

	/****** 일수 계산 ******/
	/**
	 * 해당일자의 날짜사이 일수를 구한다
	 * 
	 * @param fromDay
	 * @param toDay
	 */
	private double getDateCalc(String fromDay, String toDay) {

		// 시작일자
		int fromYear = Integer.parseInt(fromDay.substring(0, 4));
		int fromMonth = Integer.parseInt(fromDay.substring(4, 6)) - 1;
		int fromDate = Integer.parseInt(fromDay.substring(6, 8));
		// 종료일자
		int toYear = Integer.parseInt(toDay.substring(0, 4));
		int toMonth = Integer.parseInt(toDay.substring(4, 6)) - 1;
		int toDate = Integer.parseInt(toDay.substring(6, 8));

		Calendar startDay = Calendar.getInstance();
		startDay.set(fromYear, fromMonth, fromDate);

		Calendar endDay = Calendar.getInstance();
		endDay.set(toYear, toMonth, toDate);

		double Count = 0.0;

		// 시작일자 부터 종료일자까지 while
		while (!startDay.after(endDay)) {
			// 토요일, 일요일은 일수 count에서 제외
			if (startDay.get(Calendar.DAY_OF_WEEK) != 1 && startDay.get(Calendar.DAY_OF_WEEK) != 7)
				Count++;
			startDay.add(Calendar.DATE, 1);
		}

		return Count;
	}

	/**
	 * 휴가일자 해당연차발생연도에 속하는지 여부 체크
	 * 
	 * @param vcatnManageVO
	 */
	private boolean getVcatnYearSE(VcatnManageVO vcatnManageVO) {

		boolean bRetrunValue = false;
		java.util.Calendar cal = java.util.Calendar.getInstance();

		int iYear = cal.get(java.util.Calendar.YEAR);
		// 시작일자
		int iYearBgnVcatn = Integer.parseInt(vcatnManageVO.getBgnde().substring(0, 4));
		// 종료일자
		int iYearEndVcatn = Integer.parseInt(vcatnManageVO.getEndde().substring(0, 4));
		if (iYear == iYearBgnVcatn && iYear == iYearEndVcatn)
			bRetrunValue = true;
		return bRetrunValue;
	}

	/**
	 * VcatnManage model을 InfrmlSanctn model로 변환한다.
	 * 
	 * @param vcatnManageVO
	 */
	private InfrmlSanctnVO converToInfrmlSanctnObject(VcatnManageVO vcatnManageVO) {
		InfrmlSanctnVO infrmlSanctn = new InfrmlSanctnVO();
		infrmlSanctn.setJobSeCode("003"); // 업무구분코드 (공통코드 COM75)
		infrmlSanctn.setApplcntId(vcatnManageVO.getApplcntId()); // 신청자ID
		infrmlSanctn.setReqstDe(vcatnManageVO.getReqstDe()); // 신청일자
		infrmlSanctn.setSanctnerId(vcatnManageVO.getSanctnerId()); // 결재자ID
		infrmlSanctn.setConfmAt(vcatnManageVO.getConfmAt()); // 승인구분
		infrmlSanctn.setSanctnDt(vcatnManageVO.getSanctnDt()); // 결재일시
		infrmlSanctn.setReturnResn(vcatnManageVO.getReturnResn()); // 반려사유
		infrmlSanctn.setFrstRegisterId(vcatnManageVO.getFrstRegisterId());
		infrmlSanctn.setFrstRegisterPnttm(vcatnManageVO.getFrstRegisterPnttm());
		infrmlSanctn.setLastUpdusrId(vcatnManageVO.getLastUpdusrId());
		infrmlSanctn.setLastUpdusrPnttm(vcatnManageVO.getLastUpdusrPnttm());
		infrmlSanctn.setInfrmlSanctnId(vcatnManageVO.getInfrmlSanctnId());// 약식결재ID
		return infrmlSanctn;
	}

}
