package aramframework.com.sym.ccm.icr.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.sym.ccm.icr.domain.InsttCodeRecptnVO;
import aramframework.com.sym.ccm.icr.service.InsttCodeRecptnService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 기관코드에 대한 서비스 구현클래스를 정의한다.
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

@Service("insttCodeRecptnService")
public class InsttCodeRecptnServiceImpl extends EgovAbstractServiceImpl implements InsttCodeRecptnService {

	@Autowired
	private InsttCodeRecptnMapper insttCodeRecptnMapper;

	/** EgovIdGnrService */
	@Autowired
	private EgovIdGnrService insttCodeRecptnIdGnrService; 

	/**
	 * 기관코드수신 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public List<EgovMap> selectInsttCodeRecptnList(InsttCodeRecptnVO insttCodeRecptnVO) {
		return insttCodeRecptnMapper.selectInsttCodeRecptnList(insttCodeRecptnVO);
	}

	/**
	 * 기관코드수신 총 갯수를 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public int selectInsttCodeRecptnListCnt(InsttCodeRecptnVO insttCodeRecptnVO) {
		return insttCodeRecptnMapper.selectInsttCodeRecptnListCnt(insttCodeRecptnVO);
	}

	/**
	 * 기관코드 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public List<EgovMap> selectInsttCodeList(InsttCodeRecptnVO insttCodeRecptnVO) {
		return insttCodeRecptnMapper.selectInsttCodeList(insttCodeRecptnVO);
	}

	/**
	 * 기관코드 총 갯수를 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public int selectInsttCodeListCnt(InsttCodeRecptnVO insttCodeRecptnVO) {
		return insttCodeRecptnMapper.selectInsttCodeListCnt(insttCodeRecptnVO);
	}

	/**
	 * 기관코드 상세내역을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public InsttCodeRecptnVO selectInsttCodeDetail(InsttCodeRecptnVO insttCodeRecptnVO) {
		InsttCodeRecptnVO resultVo = insttCodeRecptnMapper.selectInsttCodeDetail(insttCodeRecptnVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, insttCodeRecptnVO); 
		return resultVo;
	}

	/**
	 * 기관코드수신을 처리한다.
	 * 
	 */
	public void insertInsttCodeRecptn() {
		SimpleDateFormat sDate = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
		String strdate = sDate.format(new java.util.Date());

		String rcvDir = AramProperties.getProperty("CNTC.INSTTCODE.DIR.rcv");
		String rcvOldDir = AramProperties.getProperty("CNTC.INSTTCODE.DIR.rcvold");
		String binDir = AramProperties.getProperty("CNTC.INSTTCODE.DIR.bin");

		String rcvListCmd = AramProperties.getProperty("CNTC.INSTTCODE.CMD.edircv");
		String rcvMesgCmd = AramProperties.getProperty("CNTC.INSTTCODE.CMD.edircvmsg");

		String userId = AramProperties.getProperty("CNTC.INSTTCODE.INFO.userid");
		String userPw = AramProperties.getProperty("CNTC.INSTTCODE.INFO.userpw");

		String rcvListName = "unSEENlst";
		String CODUCH = "CODUCH"; // DocCode선언

		String rcvListFullCmd = binDir + rcvListCmd;
		String rcvMesgFullCmd = binDir + rcvMesgCmd;
		String rcvListFullName = rcvDir + rcvListName + "." + strdate;

		String systemCmdFull = null;

		FileInputStream fin = null;
		InputStreamReader sin = null;
		BufferedReader in = null;

		File listFile = null;
		File dataFile = null;

		File recvOldFile = null;

		// 연계목록을 가져온다.
		try {
			systemCmdFull = rcvListFullCmd + " " + userId + " " + userPw + " *UNSEEN *ALL*ALL " + rcvListFullName;
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(systemCmdFull);

			java.io.InputStream is = process.getInputStream();
			java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
			String tmp;
//			String temp = "";
			tmp = br.readLine();
			while (tmp != null) {
//				temp += tmp;
				tmp = br.readLine();
			}
			br.close();
			is.close();
		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		}

		// 연계목록을 확인하여 연계파일을 가져온다.
		try {
			listFile = new File(rcvListFullName);

			if (listFile.exists()) {
				fin = new FileInputStream(listFile);
				sin = new InputStreamReader(fin);
				in = new BufferedReader(sin);

				String readList = null;
				int listCount = 0;

				// 연계목록을 확인
				readList = in.readLine();
				while (readList != null) {
					listCount++;
					readList = in.readLine();// 2011.09.05
					if (listCount < 4 || readList == null) {

						continue;
					}

					String messageID = null; // messageID
					String docCode = null; // DocCode
					String fileID = null; // fileID

					if (readList.length() > 56) { // 기관코드 리스트 수신 row가 56자보다 긴지 점검
						messageID = readList.substring(0, 20);
						fileID = readList.substring(20, readList.indexOf(" "));
						docCode = readList.substring(50, 56);

						if (CODUCH.equals(docCode)) {
							// 연계파일을 가져온다.
							try {
								systemCmdFull = rcvMesgFullCmd + " " + userId + " " + userPw + " " + messageID + " *ALL*ALL " + rcvDir + fileID;
								Runtime runtime = Runtime.getRuntime();
								Process process = runtime.exec(systemCmdFull);

								java.io.InputStream is = process.getInputStream();
								java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
								String tmp;
//								String temp = "";
								tmp = br.readLine();
								while (tmp != null) {
//									temp += tmp;
									tmp = br.readLine();
								}
								br.close();
								is.close();
							} catch (Exception e) {
								egovLogger.error("Exception:  " + e.getClass().getName());
								egovLogger.error("Exception  Message:  " + e.getMessage());
							}
						}
					}

				}
				if (in != null) {
					in.close();
				}
				in = null;
				// 연계파일 수신이 완료되면 listFile:=rcvListFullName 파일을 recvOldFileDir 로 이동한다.
				recvOldFile = new File(rcvOldDir + listFile.getName());
				if (listFile.isFile()) {
					if (recvOldFile.getParentFile().isDirectory()) {
						listFile.renameTo(recvOldFile);
					}
				} else {
					// 진행종료
					processException("recvList filename or rcvold path is not valid!!!");
					// throw new
					// Exception("recvList filename or rcvold path is not valid!!!");
				}
			}
		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		} finally { // 2011.10.07 에러시 자원반환할 수 있도록 추가
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					egovLogger.error("IGNORED: " + ex.getMessage());
				}
			}
			in = null;
		}

		// 수신디렉토리의 모든 연계파일을 확인하여 연계정보를 처리한다.
		String buf = "";

		File recvFileDir = new File(rcvDir);
		File recvFileList[] = recvFileDir.listFiles();

		int fileCount = 0;

		do {
			if (recvFileList[fileCount].getName().indexOf(".rec") > -1) {
				dataFile = new File(recvFileList[fileCount].getPath());

			} else {
				fileCount++;
				continue;
			}

			buf += "\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n";

			String readData = null;

			try {
				if (dataFile.exists()) {

					fin = new FileInputStream(dataFile);
					sin = new InputStreamReader(fin);
					in = new BufferedReader(sin);
					readData = in.readLine();
					while (readData != null) {

						InsttCodeRecptnVO insttCodeRecptnVO = new InsttCodeRecptnVO();

						String tokenData[] = readData.split("	", 25);
						int tokenLength = tokenData.length;

						String strTmp = null;
						for (int i = 0; i < tokenLength; i++) {
							strTmp = tokenData[i].trim();
							tokenData[i] = strTmp;
						}

						if (tokenLength >= 25) {
							// 마지막 문자 ';' 제거
							tokenData[tokenLength - 1] = tokenData[tokenLength - 1].replace(";", "").trim();

							// 서열설정 3자리 숫자로 맞춤
							if (tokenData[8].length() == 1) {
								tokenData[8] = "00" + tokenData[8];
							} else if (tokenData[8].length() == 2) {
								tokenData[8] = "0" + tokenData[8];
							}

							// 숫자형 변환 전 처리
							tokenData[24] = (tokenData[24] == null || "".equals(tokenData[24])) ? "0" : tokenData[24];

							buf += "\n" + "[ F]" + dataFile.getName() // 파일명
									+ "\n";

							// 명령 변경구분코드로 변환
							strTmp = "INS".equals(tokenData[0]) ? "01" : ("UPD".equals(tokenData[0]) ? "02" : ("DEL".equals(tokenData[0]) ? "03" : ""));

							// 실제 연계 항목 Mapping 작업
							insttCodeRecptnVO.setChangeSeCode(strTmp); 
							// 명령 :: 변경구분코드
							insttCodeRecptnVO.setOccrrDe(tokenData[1]); 
							// 날짜 :: 발생일자
							insttCodeRecptnVO.setEtcCode(tokenData[2]); 
							// 2자리코드 <적용:기타코드> :: 기타코드
							insttCodeRecptnVO.setInsttCode(tokenData[3]); 
							// 기관코드 :: 기관코드
							insttCodeRecptnVO.setAllInsttNm(tokenData[4]); 
							// 기관명(전체) :: 전체기관명
							insttCodeRecptnVO.setLowestInsttNm(tokenData[5]); 
							// 기관명(최하위) :: 최하위기관명
							insttCodeRecptnVO.setInsttAbrvNm(tokenData[6]); 
							// 기관명(약어) :: 기관약칭명
							insttCodeRecptnVO.setOdr(tokenData[7]); // 차수 :: 차수
							insttCodeRecptnVO.setOrd(tokenData[8]); // 서열 :: 서열
							insttCodeRecptnVO.setInsttOdr(tokenData[9]); 
							// 소속기관차수 :: 기관차수
							insttCodeRecptnVO.setUpperInsttCode(tokenData[10]); 
							// 차상위기관코드 :: 상위기관코드
							insttCodeRecptnVO.setBestInsttCode(tokenData[11]); 
							// 최상위기관코드 :: 최상위기관코드
							insttCodeRecptnVO.setReprsntInsttCode(tokenData[12]); 
							// 대표기관코드 :: 대표기관코드
							insttCodeRecptnVO.setInsttTyLclas(tokenData[13]); 
							// 기관유형(대) :: 기관유형대분류
							insttCodeRecptnVO.setInsttTyMclas(tokenData[14]); 
							// 기관유형(중) :: 기관유형중분류
							insttCodeRecptnVO.setInsttTySclas(tokenData[15]); 
							// 기관유형(소) :: 기관유형소분류
							insttCodeRecptnVO.setTelno(tokenData[16]); 
							// 전화번호 :: 전화번호
							insttCodeRecptnVO.setFxnum(tokenData[17]); 
							// 팩스번호 :: 팩스번호
							insttCodeRecptnVO.setCreatDe(tokenData[18]); 
							// 생성일자 :: 생성일자
							insttCodeRecptnVO.setAblDe(tokenData[19]); 
							// 폐지일자 :: 폐지일자
							insttCodeRecptnVO.setAblEnnc(tokenData[20]); 
							// 폐지구분 :: 폐지유무
							insttCodeRecptnVO.setChangede(tokenData[21]); 
							// 변경일자 :: 변경일자
							insttCodeRecptnVO.setChangeTime(tokenData[22]); 
							// 변경시간 :: 변경시간
							insttCodeRecptnVO.setBsisDe(tokenData[23]); 
							// 기초날짜 :: 기초일자
							insttCodeRecptnVO.setSortOrdr(Integer.parseInt(tokenData[24])); 
							// 트리순서(트리서열)<적용:정렬순서>:: 정렬순서

							// 작업일자
							if (insttCodeRecptnVO.getOccrrDe().equals("") || insttCodeRecptnVO.getOccrrDe() == null) {
								insttCodeRecptnVO.setOccrrDe(strdate.substring(0, 8));
							}

							// 작업일련번호 확인 Generation
							int iOpertSn = insttCodeRecptnIdGnrService.getNextIntegerId();
							insttCodeRecptnVO.setOpertSn(iOpertSn);

							buf += "\n-all--------------\n";

							for (int i = 0; i < tokenLength; i++) {
								buf += "SPLIT [" + Integer.toString(tokenData[i].length()) + "]>>>>>> " + Integer.toString(i) + "	: [" + tokenData[i] + "]\n";
							}

						} else {

							egovLogger.debug("\n\n*****************************************************************");
							egovLogger.debug(buf);
							egovLogger.debug("\n\n*****************************************************************");
							egovLogger.debug(readData);

							continue;
						}
						egovLogger.debug("\n\n*****************************************************************");
						egovLogger.debug("\n\n*****************************************************************");
						egovLogger.debug(buf);

						buf += "\n---------------\n";

						// 로그인VO에서 사용자 정보 가져오기
						LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
						insttCodeRecptnVO.setFrstRegisterId(loginVO.getUniqId());

						// changeSeCode 변경구분코드
						// '01' 기관생성
						// '02' 기관변경
						// '03' 기관말소

						// processSe 처리구분
						// '00' 수신처리
						// '01' 처리완료
						// '11' 생성오류
						// '12' 변경오류
						// '13' 말소오류

						// 작업구분 - 수신
						insttCodeRecptnVO.setProcessSe("00");
						insttCodeRecptnMapper.insertInsttCodeRecptn(insttCodeRecptnVO);

						// 작업구분 - 처리
						insttCodeRecptnVO.setProcessSe("01");
						if ("01".equals(insttCodeRecptnVO.getChangeSeCode())) {
							// 기관생성
							insttCodeRecptnMapper.insertInsttCode(insttCodeRecptnVO);
						} else if ("02".equals(insttCodeRecptnVO.getChangeSeCode())) {
							// 기관변경
							insttCodeRecptnMapper.updateInsttCode(insttCodeRecptnVO);
						} else if ("03".equals(insttCodeRecptnVO.getChangeSeCode())) {
							// 기관말소
							insttCodeRecptnMapper.deleteInsttCode(insttCodeRecptnVO);
						}
						readData = in.readLine();// 2011.09.05
					}
					in.close();
					in = null;

					// 연계파일 수신이 완료되면 dataFile 파일을 recvOldFileDir 로 이동한다.
					recvOldFile = new File(rcvOldDir + dataFile.getName());
					if (dataFile.isFile()) {
						if (recvOldFile.getParentFile().isDirectory()) {
							dataFile.renameTo(recvOldFile);
						}
					} else {
						// 진행종료
						processException("dataFile filename or rcvold path is not valid!!!");
						// throw new
						// Exception("dataFile filename or rcvold path is not valid!!!");
					}
				}
			} catch (Exception e) {
				egovLogger.error("Exception:  " + e.getClass().getName());
				egovLogger.error("Exception  Message:  " + e.getMessage());
			} finally { // 2011.10.21 보안점검 후속조치
				if (fin != null) {
					try {
						fin.close();
					} catch (IOException ex) {
						egovLogger.error("IGNORED: " + ex.getMessage());
					}
				}
				if (sin != null) {
					try {
						sin.close();
					} catch (IOException ex) {
						egovLogger.error("IGNORED: " + ex.getMessage());
					}
				}
				if (in != null) {
					try {
						in.close();
					} catch (IOException ex) {
						egovLogger.error("IGNORED: " + ex.getMessage());
					}
				}
				fileCount++;
			}

		} while (fileCount < recvFileList.length);

	}

}
