package aramframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.constant.Globals;

/**
 * 시스템정보를 조회하는 Business Interface class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class SysInfo {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	// Log protected static final Log log = LogFactory.getLog(EgovSysInfo.class);

	/**
	 * 시스템에 존재하는 서버목록을 조회하는 기능
	 * 
	 * @return Vector server_list 서버목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public static Vector getPrductList() throws Exception {

		String strlist = AramProperties.getProperty(Globals.SERVER_CONF_PATH, "SERVER_LIST");
		String[] list = strlist.split("\\$");

		Vector server_list = new Vector();

		String name = "";
		String version = "";
		String port = "";
		String status = "";

		for (int i = 0; i < list.length; i++) {
			Map map = new HashMap();
			name = list[i];
			version = getPrductVersion(list[i]);
			port = getPrductPort(list[i]);
			status = getPrductStatus(port);
			map.put("name", name);
			map.put("version", version);
			map.put("port", port);
			map.put("status", status);
			server_list.add(map);
		}

		return server_list;
	}

	/**
	 * 시스템에 존재하는 서버의 제품명, 버전정보를 조회하는 기능
	 * 
	 * @param String
	 *            server 서버명
	 * @return String version 버전
	 * @exception Exception
	 */
	public static String getPrductVersion(String server) throws Exception {

		String version = AramProperties.getProperty(Globals.SERVER_CONF_PATH, server.toUpperCase() + ".VERSION");
		return version;
	}

	/**
	 * 시스템에 존재하는 서버의 포트 정보를 조회하는 기능
	 * 
	 * @param String
	 *            server 서버명
	 * @return String port 포트
	 * @exception Exception
	 */
	public static String getPrductPort(String server) throws Exception {

		String port = AramProperties.getProperty(Globals.SERVER_CONF_PATH, server.toUpperCase() + ".PORT");
		return port;
	}

	/**
	 * 시스템에 존재하는 서버의 실행상태 정보를 조회하는 기능
	 * 
	 * @param String
	 *            port 사용포트
	 * @return String status 실행상태
	 * @exception Exception
	 */
	public static String getPrductStatus(String port) throws Exception {

		String status = "CLOSE";
		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getPrductStatus");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), port };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && Integer.parseInt(str) > 0 && str.length() <= MAX_STR_LEN) {
						status = "LISTENING";
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf(port) != -1 && str.indexOf("LISTENING") != -1 && str.length() <= MAX_STR_LEN) {
						status = "LISTENING";
					}
				}
			}

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return status;
	}

	/**
	 * 시스템의 호스트명을 조회하는 기능
	 * 
	 * @return String hostName 호스트명
	 * @exception Exception
	 */
	public static String getHostName() throws Exception {

		// 호스트명 결과
		String hostName = "";
		Process p = null;
		try {
			String[] command = { "hostname" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while (true) {
				String str = b_out.readLine();
				if (str == null)
					break;
				if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
					hostName = str;
				}
			}
			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return hostName;
	}

	/**
	 * 시스템의 OS 이름을 조회하는 기능
	 * 
	 * @return String osname OS이름
	 * @exception Exception
	 */
	public static String getOSName() throws Exception {

		// OS 이름
		String osname = "";
		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "NAME" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						osname = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("OS 이름:") != -1 && str.length() <= MAX_STR_LEN) {
						osname = str.replaceAll("OS 이름:", "");
					}
				}
			}

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return osname;
	}

	/**
	 * 시스템의 OS 버전을 조회하는 기능
	 * 
	 * @return String osversion OS버전
	 * @exception Exception
	 */
	public static String getOSVersion() throws Exception {

		// OS 버전
		String osversion = "";
		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "VERSION" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						osversion = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("OS 버전:") != -1 && str.length() <= MAX_STR_LEN) {
						osversion = str.replaceAll("OS 버전:", "");
					}
				}
			}

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return osversion;
	}

	/**
	 * 시스템의 OS 제조사를 조회하는 기능
	 * 
	 * @return String osprductor OS제조사
	 * @exception Exception
	 */
	public static String getOSPrductor() throws Exception {

		// OS 제조사
		String osprductor = "";
		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "PRDUCTOR" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						osprductor = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("OS 제조업체:") != -1 && str.length() <= MAX_STR_LEN) {
						osprductor = str.replaceAll("OS 제조업체:", "");
					}
				}
			}

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return osprductor;
	}

	/**
	 * 시스템의 Processor ID를 조회하는 기능
	 * 
	 * @return String processor 프로세서ID
	 * @exception Exception
	 */
	public static String getProcessorID() throws Exception {

		// 프로세서ID
		String processor = "";
		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getOSInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "PROCESSOR" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						processor = str;
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("프로세서:") != -1 && str.length() <= MAX_STR_LEN) {
						processor = str.replaceAll("\\[01\\]:", "");
					}
				}
			}

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return processor;
	}

	/**
	 * 시스템의 디스크명을 확인
	 * 
	 * @return ArrayList list 디스크명이 담긴 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList getDiskName() throws Exception {

		// 디스크명
		ArrayList list = new ArrayList();

		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "NAME" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						list.add(str);
					}
				}
			} // else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				// 윈도우 제공안함
				// log.debug("Windows System Doesn't Support This Function..");
			// }

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return list;
	}

	/**
	 * 시스템의 디스크 전체용량을 확인
	 * 
	 * @param String
	 *            disk 디스크명
	 * @return long cpcty 디스크 전체용량(MB)
	 * @exception Exception
	 */
	public static float getDiskFullCpcty(String disk) throws Exception {

		// 디스크 전체용량
		float cpcty = 0;

		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "FULL", disk };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			}// else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				// 윈도우 제공안함
				// log.debug("Windows System Doesn't Support This Function..");
			// }

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return cpcty;
	}

	/**
	 * 시스템의 디스크 사용중 용량을 확인
	 * 
	 * @param String
	 *            disk 디스크명
	 * @return long cpcty 디스크 사용중 용량(MB)
	 * @exception Exception
	 */
	public static float getDiskUsedCpcty(String disk) throws Exception {

		// 디스크 사용중 용량
		float cpcty = 0;

		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "USED", disk };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			}// else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				// 윈도우 제공안함
				// log.debug("Windows System Doesn't Support This Function..");
			// }

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return cpcty;
	}

	/**
	 * 시스템의 디스크 유효 용량을 확인
	 * 
	 * @param String
	 *            disk 디스크명
	 * @return long cpcty 디스크 유효 용량(MB)
	 * @exception Exception
	 */
	public static float getDiskFreeCpcty(String disk) throws Exception {

		// 디스크 유효 용량
		float cpcty = 0;

		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getDiskInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "FREE", disk };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			} // else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				// 윈도우 제공안함
				// log.debug("Windows System Doesn't Support This Function..");
			// }

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return cpcty;
	}

	/**
	 * 시스템의 메모리 전체용량을 확인
	 * 
	 * @return long cpcty 메모리 전체용량(MB)
	 * @exception Exception
	 */
	public static float getMoryFullCpcty() throws Exception {

		// 메모리 전체 용량
		float cpcty = 0;
		cpcty = getMoryUsedCpcty() + getMoryFreeCpcty();
		return cpcty;

		/*
		 * // 메모리 전체 용량 
		 * float cpcty = 0;
		 * 
		 * Process p = null; 
		 * try { 
		 * 		String cmdStr = AramProperties.getPathProperty(Globals.SERVER_CONF_PATH,
		 * 						"SHELL."+Globals.OS_TYPE+".getMoryInfo"); 
		 * 		String[] command ={cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "FULL"}; 
		 * 		p = Runtime.getRuntime().exec(command); 
		 * 		//p.waitFor();
		 * 
		 * 		boolean result = false; 
		 * 		BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
		 * 
		 * 		if ("UNIX".equals(Globals.OS_TYPE)) { 
		 * 			while (true){ 
		 * 				String str = b_out.readLine(); 
		 * 				if (str == null) break; 
		 * 				if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) { 
		 * 					str = str.replaceAll("mem=", "").replaceAll(" ", "")
		 * 							.replaceAll("MB", "").replaceAll(",", ""); 
		 * 					cpcty = Float.parseFloat(str); 
		 * 					result = true; 
		 * 				} 
		 * 			} 
		 * 		} else if ("WINDOWS".equals(Globals.OS_TYPE)) { 
		 * 			// 메모리 전체용량 = 사용중 용량 + 유효 용량 
		 * 			cpcty = getMoryUsedCpcty() + getMoryFreeCpcty(); 
		 * 		}
		 * 
		 * 		b_out.close();
		 * 
		 * }catch(Exception ex){ 
		 * 		ex.printStackTrace(); 
		 * }finally{ 
		 * 		if (p != null)
		 * 			p.destroy(); 
		 * }
		 * 
		 * return cpcty;
		 */
	}

	/**
	 * 시스템의 메모리 사용중 용량을 확인
	 * 
	 * @return long cpcty 메모리 사용중 용량(MB)
	 * @exception Exception
	 */
	public static float getMoryUsedCpcty() throws Exception {

		// 메모리 사용중 용량
		float cpcty = 0;

		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getMoryInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "USED" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				long fullcpcty = 0;
				long usedcpcty = 0;
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("총 실제 메모리:") != -1 && str.length() <= MAX_STR_LEN) {
						str = str.replaceAll("총 실제 메모리:", "").replaceAll(" ", "").replaceAll("MB", "").replaceAll(",", "");
						fullcpcty = Long.parseLong(str);
					}
					if (str != null && !"".equals(str) && str.indexOf("사용 가능한 실제 메모리:") != -1) {
						str = str.replaceAll("사용 가능한 실제 메모리:", "").replaceAll(" ", "").replaceAll("MB", "").replaceAll(",", "");
						usedcpcty = Long.parseLong(str);
					}
				}
				cpcty = fullcpcty - usedcpcty;
			}

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return cpcty;
	}

	/**
	 * 시스템의 메모리 유효 용량을 확인
	 * 
	 * @return long cpcty 메모리 유효 용량
	 * @exception Exception
	 */
	public static float getMoryFreeCpcty() throws Exception {

		// 메모리 유효 용량
		float cpcty = 0;

		Process p = null;
		try {
			String cmdStr = AramProperties.getSysPathProperty(Globals.SERVER_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getMoryInfo");
			String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), "FREE" };
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();

			BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ("UNIX".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.length() <= MAX_STR_LEN) {
						cpcty = Float.parseFloat(str);
					}
				}
			} else if ("WINDOWS".equals(Globals.OS_TYPE)) {
				while (true) {
					String str = b_out.readLine();
					if (str == null)
						break;
					if (str != null && !"".equals(str) && str.indexOf("사용 가능한 실제 메모리:") != -1 && str.length() <= MAX_STR_LEN) {
						str = str.replaceAll("사용 가능한 실제 메모리:", "").replaceAll(" ", "").replaceAll("MB", "").replaceAll(",", "");
						cpcty = Long.parseLong(str);
					}
				}
			}

			b_out.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (p != null)
				p.destroy();
		}

		return cpcty;
	}

	/**
	 * 특정 프로그램을 실행시키기 위해 메모리용량이 충분한지 확인
	 * 
	 * @param String
	 *            memory 메모리용량(MB)
	 * @return boolean 가용여부 True/False
	 * @exception Exception
	 */
	public static boolean checkMoryCpcty(long memory) throws Exception {

		// 가용여부
		boolean result = false;

		if (memory <= 0) {
			// log.debug("+++ Memory Capacity is Not Valid..");
			return false;
		}

		if (memory < getMoryFreeCpcty()) {
			result = true;
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디스크속성정보를 확인한다.
	 * </pre>
	 * 
	 * @return ArrayList result 디스크속성정보를 라인단위로 담은 ArrayList를 리턴한다.
	 */
	public static ArrayList getDiskAttribute() {

		ArrayList result = new ArrayList();
		String[] command = { "" }; // 인자 없음
		try {
			result = executeProgram("getDiskAttrb", command);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e); // 2011.10.10 보안점검 후속조치
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디스크용량정보를 확인한다.
	 * </pre>
	 * 
	 * @return ArrayList result 디스크용량정보를 라인단위로 담은 ArrayList를 리턴한다.
	 */
	public static ArrayList getDiskCapacity() {

		ArrayList result = new ArrayList();
		String[] command = { "" }; // 인자 없음
		try {
			result = executeProgram("getDiskCpcty", command);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e); // 2011.10.10 보안점검 후속조치
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디스크존재여부를 확인한다.
	 * </pre>
	 * 
	 * @return ArrayList result 디스크존재여부를 라인단위로 담은 ArrayList를 리턴한다.
	 */
	public static ArrayList getExistDisk(String diskName) {

		ArrayList result = new ArrayList();
		String[] command = { "", diskName }; // 인자 없음
		try {
			result = executeProgram("getDiskExst", command);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e); // 2011.10.10 보안점검 후속조치
		}

		return result;
	}

	/**
	 * 시스템에서 특정 쉘프로그램을 동작하고 콘솔에 출력된 결과를 라인단위로 ArrayList에 담아서 전달한다.
	 * 
	 * @param String
	 *            propertyKeyword 프로퍼티키워드 -프로퍼티에서 해당키에 대한 프로그램정보를 확인)
	 * @param String
	 *            [] command 실행할 프로그램의 argument
	 * @return ArrayList resultTxtList 콘솔상에 출력된 결과를 라인단위 문자열로 보관
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList executeProgram(String propertyKeyword, String[] command) throws Exception {

		ArrayList resultTxtList = new ArrayList();
		BufferedReader b_err = null;
		BufferedReader b_out = null;
		try {
			Process p = null;
			String cmdStr = AramProperties.getSysPathProperty(Globals.SHELL_CONF_PATH, "SHELL." + Globals.OS_TYPE + "." + propertyKeyword);
			command[0] = cmdStr;
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			// 프로세스 에러시 종료
			if (p.exitValue() != 0) {
				b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while (b_err.ready()) {
					String line = b_err.readLine();
					if (line.length() <= MAX_STR_LEN) ;
//						log.debug("ERR\n" + line);
				}
				b_err.close();
			}
			// 프로세스 실행 성공시 결과 확인
			else {
				String tmpLine = "";
				b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while (b_out.ready()) {
					// 결과문자가 있으면 생성자가 있다는 의미
					tmpLine = b_out.readLine();

					if (tmpLine.length() <= MAX_STR_LEN) {
						resultTxtList.add(tmpLine);
					}
				}
				b_out.close();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} finally {
			if (b_err != null)
				b_err.close();
			if (b_out != null)
				b_out.close();
		}

		return resultTxtList;
	}

	/**
	 * <pre>
	 * Comment : 프로세스 정보를 확인한다. (
	 * </pre>
	 * 
	 * @param
	 * @return List<String[]> 프로세스 정보를 리턴한다.
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList getProcessId() {

		ArrayList processes = new ArrayList();
		String[] command = { "", "-all" }; // 인자 없음
		try {
			String line;
			String separator = null;

			Process p = null;

			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
				separator = "\",\"";
			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				/*
				 * String tmp = "ps -eo \"%p %G %U %c %a\"|awk -F\" \" '{print $1,$2,$3,$4,$5}'" ; 
				 * log.debug(tmp); 
				 * command [0] = tmp; 
				 * p = Runtime.getRuntime().exec(tmp); 
				 * separator = " ";
				 */
				separator = " ";
				processes = executeProgram("getProcInfo", command);

			}

			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if (input != null) {
				while ((line = input.readLine()) != null) {
					if (!line.trim().equals("") && line.length() <= MAX_STR_LEN) {

						line = line.substring(1, line.length() - 1);
						String[] operator = line.split(separator);

						processes.add(operator);
					}
				}

				input.close();
			}
		} catch (Exception err) {
			// err.printStackTrace();
			throw new RuntimeException(err); // 2011.10.10 보안점검 후속조치
		}

		return processes;
	}

	/**
	 * <pre>
	 * Comment : 프로세스 정보를 확인한다. (
	 * </pre>
	 * 
	 * @param String
	 *            processName
	 * @return List<String[]> 프로세스 정보를 리턴한다.
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList getProcessId(String processName) {
		// log.debug("getProcessId_start");
		ArrayList processes = new ArrayList();
		String[] command = { "", "" }; // 인자 없음
//		String tmp = "";
		BufferedReader input = null;
		try {
			String line;
			String separator = null;

			Process p = null;

			// 2011.10.10 보안점검 후속조치
			if (Globals.OS_TYPE == null) {
				throw new RuntimeException("Globals.OS_TYPE property value is needed!!!");
			}
			// 2011.10.10 보안점검 후속조치 끝

			// log.debug("Globals.OS_TYPE:"+Globals.OS_TYPE);
			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				p = Runtime.getRuntime().exec("tasklist /fo csv /nh /fi \"imagename eq " + processName + "*\"");
				separator = "\",\"";
			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				// tmp = "ps -eo \"%p %G %U %c %a\"|awk -F\" \" '{print $1,$2,$3,$4,$5}'|grep "+processName;
				// command [0] = tmp;
				// p = Runtime.getRuntime().exec(tmp);
				command[1] = processName;
				separator = " ";
				// log.debug("command0"+command[0]);
				// log.debug("command1"+command[1]);
				processes = executeProgram("getProcInfo", command);
				// log.debug("command2 ???");

			}

			if ("WINDOWS".equals(Globals.OS_TYPE)) {

				input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				if (input != null) {
					while ((line = input.readLine()) != null) {
						// log.debug("LINE__"+line);
						if (line.length() <= MAX_STR_LEN) {

							line = line.substring(1, line.length() - 1);
							String[] operator = line.split(separator);

							processes.add(operator);
						}
					}

					input.close();
				}

			} // else{
				// log.debug("UNIX....procId...");
			// }
		} catch (Exception err) {
			// err.printStackTrace();
			throw new RuntimeException(err); // 2011.10.10 보안점검 후속조치
		}
		// log.debug("size -->"+processes.size());
		return processes;
	}
}
