package aramframework.com.sym.ccm.acr.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.sym.ccm.acr.dao.AdministCodeRecptnMapper;
import aramframework.com.sym.ccm.acr.domain.AdministCodeRecptnVO;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 법정동코드에 대한 서비스 구현클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class AdministCodeRecptnService extends EgovAbstractServiceImpl {

	@Autowired
	private AdministCodeRecptnMapper administCodeRecptnMapper;	

	/** EgovIdGnrService */
	@Autowired
	private EgovIdGnrService administCodeRecptnIdGnrService; 

	/**
	 * 법정동코드수신 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public List<EgovMap> selectAdministCodeRecptnList(AdministCodeRecptnVO administCodeRecptnVO) {
		return administCodeRecptnMapper.selectAdministCodeRecptnList(administCodeRecptnVO);
	}

	/**
	 * 법정동코드수신 총 갯수를 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public int selectAdministCodeRecptnListCnt(AdministCodeRecptnVO administCodeRecptnVO) {
		return administCodeRecptnMapper.selectAdministCodeRecptnListCnt(administCodeRecptnVO);
	}

	/**
	 * 법정동코드 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public List<EgovMap> selectAdministCodeList(AdministCodeRecptnVO administCodeRecptnVO) {
		return administCodeRecptnMapper.selectAdministCodeList(administCodeRecptnVO);
	}

	/**
	 * 법정동코드 총 갯수를 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public int selectAdministCodeListCnt(AdministCodeRecptnVO administCodeRecptnVO) {
		return administCodeRecptnMapper.selectAdministCodeListCnt(administCodeRecptnVO);
	}

	/**
	 * 법정동코드 상세내역을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public AdministCodeRecptnVO selectAdministCodeDetail(AdministCodeRecptnVO administCodeRecptnVO) {
		AdministCodeRecptnVO resultVo = administCodeRecptnMapper.selectAdministCodeDetail(administCodeRecptnVO);
		// searchVO 이전 
		resultVo.setSearchVO(administCodeRecptnVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 법정동코드수신을 처리한다.
	 */
	public void insertAdministCodeRecptn() {
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
		String CODULD = "CODULD"; // DocCode선언

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

		InputStream is = null;
		BufferedReader br = null;

		// 연계목록을 가져온다.
		try {
			systemCmdFull = rcvListFullCmd + " " + userId + " " + userPw + " *UNSEEN *ALL*ALL " + rcvListFullName;
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(systemCmdFull);

			is = process.getInputStream();
			br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
			String tmp;
//			String temp = "";
			tmp = br.readLine();
			while (tmp != null) {
//				temp += tmp;
				tmp = br.readLine();
			}
		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception ignore) {
					egovLogger.error("Exception:  " + ignore.getClass().getName());
					egovLogger.error("Exception  Message:  " + ignore.getMessage());
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
					egovLogger.error("Exception:  " + ignore.getClass().getName());
					egovLogger.error("Exception  Message:  " + ignore.getMessage());
				}
			}
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

					if (listCount < 5) {
						continue;
					}

					String messageID = null; // messageID
					String docCode = null; // DocCode
					String fileID = null; // fileID

					messageID = readList.substring(0, 20);
					fileID = readList.substring(20, readList.indexOf(" "));
					docCode = readList.substring(50, 56);

					if (CODULD.equals(docCode)) {
						// 연계파일을 가져온다.
						try {
							systemCmdFull = rcvMesgFullCmd + " " + userId + " " + userPw + " " + messageID + " *ALL*ALL " + rcvDir + fileID;
							Runtime runtime = Runtime.getRuntime();
							Process process = runtime.exec(systemCmdFull);

							is = process.getInputStream();
							br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
							String tmp;
//							String temp = "";
							tmp = br.readLine();
							while (tmp != null) {
//								temp += tmp;
								tmp = br.readLine();
							}
							br.close();
							is.close();
						} catch (Exception e) {
							egovLogger.error("Exception:  " + e.getClass().getName());
							egovLogger.error("Exception  Message:  " + e.getMessage());
						} finally {
							if (br != null) {
								try {
									br.close();
								} catch (Exception ignore) {
									egovLogger.error("Exception:  " + ignore.getClass().getName());
									egovLogger.error("Exception  Message:  " + ignore.getMessage());
								}
							}
							if (is != null) {
								try {
									is.close();
								} catch (Exception ignore) {
									egovLogger.error("Exception:  " + ignore.getClass().getName());
									egovLogger.error("Exception  Message:  " + ignore.getMessage());
								}
							}
						}
					}
				}
				if (in != null) { // 보안점검 후속조치
					in.close();
					in = null;
				}

				// 연계파일 수신이 완료되면 listFile:=rcvListFullName 파일을 recvOldFileDir 로
				// 이동한다.
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
		}
		// 2011.10.21 보안점검 후속조치
		finally {
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
		}

		// 수신디렉토리의 모든 연계파일을 확인하여 연계정보를 처리한다.
		String buf = "";
		// String buf2[] = null;

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

						AdministCodeRecptnVO administCodeRecptnVO = new AdministCodeRecptnVO();

						String tokenData[] = readData.split("	", 12);
						int tokenLength = tokenData.length;

						String strTmp = null;
						for (int i = 0; i < tokenLength; i++) {
							strTmp = tokenData[i].trim();
							tokenData[i] = strTmp;
						}

						buf += "\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
						buf += "tokenLength" + Integer.toString(tokenLength);

						if (tokenLength >= 12) {
							// 마지막 문자 ';' 제거
							tokenData[tokenLength - 1] = tokenData[tokenLength - 1].replace(";", "").trim();

							buf += "\n" + "[ F]" + dataFile.getName() // 파일명
									+ "\n";

							// 명령 변경구분코드로 변환
							strTmp = "INS".equals(tokenData[0]) ? "01" : ("UPD".equals(tokenData[0]) ? "02" : ("DEL".equals(tokenData[0]) ? "03" : ""));

							// 기본셋팅
							administCodeRecptnVO.setAdministZoneSe("1"); 
							// 행정구역구분 1.법정동; 2.행정동

							// 실제 연계 항목 Mapping 작업
							administCodeRecptnVO.setChangeSeCode(strTmp); 
							// 명령 :: 변경구분코드
							administCodeRecptnVO.setOccrrDe(tokenData[1]); 
							// 날짜 :: 발생일자
							administCodeRecptnVO.setAdministZoneCode(tokenData[2]); 
							// 행정구역코드 :: 행정구역코드
							administCodeRecptnVO.setAdministZoneNm(tokenData[7]); 
							// 행정구역명 :: 행정구역명
							administCodeRecptnVO.setLowestAdministZoneNm(tokenData[8]); 
							// 최하위행정구역명 :: 최하위행정구역명
							administCodeRecptnVO.setCtprvnCode(tokenData[3]); 
							// 시도코드 :: 시도코드
							administCodeRecptnVO.setSignguCode(tokenData[4]); 
							// 시군구코드 :: 시군구코드
							administCodeRecptnVO.setEmdCode(tokenData[5]); 
							// 읍면동코드 :: 읍면동코드
							administCodeRecptnVO.setLiCode(tokenData[6]); 
							// 리코드 :: 리코드
							administCodeRecptnVO.setCreatDe(tokenData[9]); 
							// 생성일자 :: 생성일자
							administCodeRecptnVO.setAblDe(tokenData[10]); 
							// 폐지일자 :: 폐지일자
							administCodeRecptnVO.setAblEnnc(tokenData[11]); 
							// 폐지유무 :: 폐지유무
							administCodeRecptnVO.setUseAt(tokenData[11]); 
							// 폐지유무 :: 폐지유무

							// 작업일자
							if (administCodeRecptnVO.getOccrrDe().equals("") || administCodeRecptnVO.getOccrrDe() == null) {
								administCodeRecptnVO.setOccrrDe(strdate.substring(0, 8));
							}

							// 상위기관코드 계산
							String upperCode = administCodeRecptnVO.getSignguCode().equals("000") ? ""
									: (administCodeRecptnVO.getEmdCode().equals("000") ? administCodeRecptnVO.getCtprvnCode() + "000" + "000" + "00"
									: (administCodeRecptnVO.getLiCode().equals("00") ? administCodeRecptnVO.getCtprvnCode()
										+ administCodeRecptnVO.getSignguCode() + "000" + "00" : administCodeRecptnVO.getCtprvnCode()
										+ administCodeRecptnVO.getSignguCode() + administCodeRecptnVO.getEmdCode() + "00"));
							administCodeRecptnVO.setUpperAdministZoneCode(upperCode);

							// 작업일련번호 확인 Generation
							int iOpertSn = administCodeRecptnIdGnrService.getNextIntegerId();
							administCodeRecptnVO.setOpertSn(iOpertSn);

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
						administCodeRecptnVO.setFrstRegisterId(loginVO.getUniqId());

						// changeSeCode 변경구분코드
						// '01' 코드생성
						// '02' 코드변경
						// '03' 코드말소

						// processSe 처리구분
						// '00' 수신처리
						// '01' 처리완료
						// '11' 생성오류
						// '12' 변경오류
						// '13' 말소오류

						// 작업구분 - 수신
						administCodeRecptnVO.setProcessSe("00");
						administCodeRecptnMapper.insertAdministCodeRecptn(administCodeRecptnVO);

						// 작업구분 - 처리
						administCodeRecptnVO.setProcessSe("01");
						if ("01".equals(administCodeRecptnVO.getChangeSeCode())) {
							// 코드생성
							administCodeRecptnMapper.insertAdministCode(administCodeRecptnVO);
						} else if ("02".equals(administCodeRecptnVO.getChangeSeCode())) {
							// 코드변경
							administCodeRecptnMapper.updateAdministCode(administCodeRecptnVO);
						} else if ("03".equals(administCodeRecptnVO.getChangeSeCode())) {
							// 코드말소
							administCodeRecptnMapper.deleteAdministCode(administCodeRecptnVO);
						}
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
			} catch (Exception e) { // 2011.10.21 보안점검 후속조치
				egovLogger.error("Exception:  " + e.getClass().getName());
				egovLogger.error("Exception  Message:  " + e.getMessage());
			} finally{ // 2011.11.01 보안점검 후속조치
				if(in !=null) {
					try {
						in.close();
					} catch (IOException ex) {
						egovLogger.error("IGNORED: " + ex.getMessage());
					}
				}
			}
			fileCount++;

		} while (fileCount < recvFileList.length);

	}

}
