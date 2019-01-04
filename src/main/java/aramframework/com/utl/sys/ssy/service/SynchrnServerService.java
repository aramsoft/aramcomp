package aramframework.com.utl.sys.ssy.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import aramframework.com.cmm.util.WebUtil;
import aramframework.com.utl.sys.ssy.dao.SynchrnServerMapper;
import aramframework.com.utl.sys.ssy.domain.SynchrnServerVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요 - 동기화대상 서버에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용 - 동기화대상 서버에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 동기화대상 서버의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class SynchrnServerService extends EgovAbstractServiceImpl {

	@Autowired
	private SynchrnServerMapper synchrnServerMapper;
	
	/** ID Generation */
	@Autowired
	private EgovIdGnrService synchrnServerIdGnrService; 

	/**
	 * 동기화대상 서버를 관리하기 위해 등록된 동기화대상 서버목록을 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public List<SynchrnServerVO> selectSynchrnServerList(SynchrnServerVO synchrnServerVO) {
		return synchrnServerMapper.selectSynchrnServerList(synchrnServerVO);
	}

	/**
	 * 동기화대상 서버목록 총 갯수를 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public int selectSynchrnServerListCnt(SynchrnServerVO synchrnServerVO) {
		return synchrnServerMapper.selectSynchrnServerListCnt(synchrnServerVO);
	}

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public SynchrnServerVO selectSynchrnServer(SynchrnServerVO synchrnServerVO) {
		return synchrnServerMapper.selectSynchrnServer(synchrnServerVO);
	}

	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void insertSynchrnServer(SynchrnServerVO synchrnServerVO) {
		try {
			synchrnServerVO.setServerId(synchrnServerIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		synchrnServerMapper.insertSynchrnServer(synchrnServerVO);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 수정한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void updateSynchrnServer(SynchrnServerVO synchrnServerVO) {
		synchrnServerMapper.updateSynchrnServer(synchrnServerVO);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void deleteSynchrnServer(SynchrnServerVO synchrnServerVO) {
		synchrnServerMapper.deleteSynchrnServer(synchrnServerVO);
	}

	/**
	 * 등록된 동기화대상 서버의 파일 목록을 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	public List<String> selectSynchrnServerFiles(SynchrnServerVO synchrnServerVO) {

		List<String> list = new ArrayList<String>();

		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.setControlEncoding("euc-kr");

			if (!WebUtil.isIPAddress(synchrnServerVO.getServerIp())) { // 2011.10.25 보안점검 후속조치
				throw new RuntimeException("IP is needed. (" + synchrnServerVO.getServerIp() + ")");
			}

			InetAddress host = InetAddress.getByName(synchrnServerVO.getServerIp());

			try {
				ftpClient.connect(host, Integer.parseInt(synchrnServerVO.getServerPort()));
				boolean isLogin = ftpClient.login(synchrnServerVO.getFtpId(), synchrnServerVO.getFtpPassword());
				if (!isLogin)
					throw new Exception("FTP Client Login Error : \n");

			} catch (SocketException se) {
				System.out.println(se);
				throw new Exception(se);
			} catch (Exception e) {
				System.out.println(e);
				throw new Exception(e);
			}

			FTPFile[] fTPFile = null;

			try {
				ftpClient.changeWorkingDirectory(synchrnServerVO.getSynchrnLc());
				fTPFile = ftpClient.listFiles(synchrnServerVO.getSynchrnLc());

				for (int i = 0; i < fTPFile.length; i++) {
					if (fTPFile[i].isFile())
						list.add(fTPFile[i].getName());
				}
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e); // 2011.10.10 보안점검 후속조치
			} finally {
				ftpClient.logout();
			}

		} catch (Exception e) {
			list.add("noList");
		}

		return list;
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 삭제한다.
	 * 
	 * @param synchrnServerVO
	 */
	public void deleteSynchrnServerFile(SynchrnServerVO synchrnServerVO) {

		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!WebUtil.isIPAddress(synchrnServerVO.getServerIp())) { // 2011.10.25 보안점검 후속조치
			throw new RuntimeException("IP is needed. (" + synchrnServerVO.getServerIp() + ")");
		}

		InetAddress host;
		try {
			host = InetAddress.getByName(synchrnServerVO.getServerIp());
		} catch (UnknownHostException e1) {
			throw new RuntimeException(e1);
		}

		try {
			ftpClient.connect(host, Integer.parseInt(synchrnServerVO.getServerPort()));
			ftpClient.login(synchrnServerVO.getFtpId(), synchrnServerVO.getFtpPassword());
		} catch (NumberFormatException e1) {
			throw new RuntimeException(e1);
		} catch (SocketException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}

		FTPFile[] fTPFile = null;

		try {
			ftpClient.changeWorkingDirectory(synchrnServerVO.getSynchrnLc());
			fTPFile = ftpClient.listFiles(synchrnServerVO.getSynchrnLc());

			for (int i = 0; i < fTPFile.length; i++) {
				if (synchrnServerVO.getDeleteFileNm().equals(fTPFile[i].getName()))
					ftpClient.deleteFile(fTPFile[i].getName());
			}

			SynchrnServerVO synchrnServer = new SynchrnServerVO();
			synchrnServer.setServerId(synchrnServerVO.getServerId());
			synchrnServer.setReflctAt("N");
			synchrnServerMapper.processSynchrn(synchrnServer);

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치
		} finally {
			try {
				ftpClient.logout();
			} catch (IOException e) {}
		}
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 다운로드 한다.
	 * 
	 * @param synchrnServerVO
	 * @param fileNm
	 */
	public void downloadFtpFile(SynchrnServerVO synchrnServerVO, String fileNm) {

		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!WebUtil.isIPAddress(synchrnServerVO.getServerIp())) { // 2011.10.25 보안점검 후속조치
			throw new RuntimeException("IP is needed. (" + synchrnServerVO.getServerIp() + ")");
		}

		InetAddress host;
		try {
			host = InetAddress.getByName(synchrnServerVO.getServerIp());
		} catch (UnknownHostException e1) {
			throw new RuntimeException(e1);
		}

		try {
			ftpClient.connect(host, Integer.parseInt(synchrnServerVO.getServerPort()));
			ftpClient.login(synchrnServerVO.getFtpId(), synchrnServerVO.getFtpPassword());
			ftpClient.changeWorkingDirectory(synchrnServerVO.getSynchrnLc());
		} catch (NumberFormatException e1) {
			throw new RuntimeException(e1);
		} catch (SocketException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}

		File downFile = new File(WebUtil.filePathBlackList(synchrnServerVO.getFilePath() + fileNm));
		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(downFile);
			ftpClient.retrieveFile(fileNm, outputStream);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치
		} finally {
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {}
			
			try {
				ftpClient.logout();
			} catch (IOException e) {}
		}

	}

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * 
	 * @param synchrnServerVO
	 * @param uploadFile
	 */
	public boolean processSynchrn(SynchrnServerVO synchrnServerVO, File[] uploadFile) {

		List<SynchrnServerVO> synchrnServerList = synchrnServerMapper.processSynchrnServerList(synchrnServerVO);
		Iterator<SynchrnServerVO> iterator = synchrnServerList.iterator();
		SynchrnServerVO synchrnServer = new SynchrnServerVO();
		boolean reflctAt = false;

		while (iterator.hasNext()) {
			SynchrnServerVO SynchrnServerVo = iterator.next();

			try {
				reflctAt = processFtp(SynchrnServerVo.getServerIp(), Integer.parseInt(SynchrnServerVo.getServerPort()), SynchrnServerVo.getFtpId(),
						SynchrnServerVo.getFtpPassword(), SynchrnServerVo.getSynchrnLc(), synchrnServerVO.getFilePath(), uploadFile);
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			synchrnServer.setServerId(SynchrnServerVo.getServerId());
			if (reflctAt)
				synchrnServer.setReflctAt("Y");
			else
				synchrnServer.setReflctAt("N");

			synchrnServerMapper.processSynchrn(synchrnServer);
		}

		return true;
	}

	/**
	 * FTP 서버에 있는 화일 목록을 조회한다.
	 * 
	 * @param serverIp
	 * @param port
	 * @param user
	 * @param password
	 * @param synchrnPath
	 */
	public List<String> getFtpFileList(String serverIp, int port, String user, String password, String synchrnPath) throws Exception {

		List<String> list = new ArrayList<String>();
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!WebUtil.isIPAddress(serverIp)) { // 2011.10.25 보안점검 후속조치
			throw new RuntimeException("IP is needed. (" + serverIp + ")");
		}

		InetAddress host = InetAddress.getByName(serverIp);

		ftpClient.connect(host, port);
		ftpClient.login(user, password);

		ftpClient.changeWorkingDirectory(synchrnPath);
		FTPFile[] fTPFile = ftpClient.listFiles(synchrnPath);
		for (int i = 0; i < fTPFile.length; i++) {
			list.add(fTPFile[i].getName());
		}
		return list;
	}

	/**
	 * 동기화 서버들을 대상으로 FTP Upload 처리를 한다.
	 * 
	 * @param serverIp
	 * @param port
	 * @param user
	 * @param password
	 * @param synchrnPath
	 * @param filePath
	 * @param uploadFile
	 */
	public boolean processFtp(String serverIp, int port, String user, String password, String synchrnPath, String filePath, File[] uploadFile) throws Exception {

		boolean upload = false;

		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.setControlEncoding("euc-kr");

			if (!WebUtil.isIPAddress(serverIp)) { // 2011.10.25 보안점검 후속조치
				throw new RuntimeException("IP is needed. (" + serverIp + ")");
			}

			InetAddress host = InetAddress.getByName(serverIp);

			ftpClient.connect(host, port);
			if (!ftpClient.login(user, password))
				throw new Exception("FTP Client Login Error : \n");

			if (synchrnPath.length() != 0)
				ftpClient.changeWorkingDirectory(synchrnPath);

			FTPFile[] fTPFile = ftpClient.listFiles(synchrnPath);

			FileInputStream fis = null;
			try {
				for (int i = 0; i < uploadFile.length; i++) {
					if (uploadFile[i].isFile()) {
						if (!isExist(fTPFile, uploadFile[i])) {
							fis = new FileInputStream(uploadFile[i]);
							ftpClient.storeFile(synchrnPath + uploadFile[i].getName(), fis);
						}
					}
				}

				// 업로드 파일 목록에 없는 FTP 서버에 있는 파일을 삭제한다.
				fTPFile = ftpClient.listFiles(synchrnPath);
				deleteFtpFile(ftpClient, fTPFile, uploadFile);

				upload = true;

			} catch (IOException ex) {
				// ex.printStackTrace();
				System.out.println(ex); // 2011.10.10 보안점검 후속조치
			} finally {
				if (fis != null)
					try {
						fis.close();
					} catch (IOException ignore) {
						System.out.println("IGNORE: " + ignore);
					}
			}
			ftpClient.logout();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치
			upload = false;
		}

		return upload;
	}

	/**
	 * 동기화 서버에 upload 할 파일이 존재하는지 확인한다.
	 * 
	 * @param fTPFiles
	 * @param targetFile
	 */
	public boolean isExist(FTPFile[] fTPFiles, File targetFile) throws Exception {

		boolean isExist = false;

		for (int i = 0; i < fTPFiles.length; i++) {
			if (fTPFiles[i].isFile()) {
				if (fTPFiles[i].getName().equals(targetFile.getName()))
					isExist = true;
			}
		}

		return isExist;
	}

	/**
	 * 동기화 서버의 파일 목록 중 upload 파일 목록에 없는 파일은 삭제한다.
	 * 
	 * @param ftpClient
	 * @param fTPFiles
	 * @param uploadFile
	 */
	public void deleteFtpFile(FTPClient ftpClient, FTPFile[] fTPFiles, File[] uploadFile) throws Exception {

		boolean isExist = false;

		for (int i = 0; i < fTPFiles.length; i++) {
			isExist = false;
			for (int j = 0; j < uploadFile.length; j++) {
				if (fTPFiles[i].isFile()) {
					if (fTPFiles[i].getName().equals(uploadFile[j].getName())) {
						isExist = true;
					}
				}
			}

			if (!isExist) {
				if (fTPFiles[i].isFile()) {
					ftpClient.deleteFile(fTPFiles[i].getName());
				}
			}
		}
	}

	/**
	 * 업로드 파일의 목록을 조회한다.
	 * 
	 * @param filePath
	 */
	public List<String> getFileName(String filePath)  {

		File uploadFile = new File(WebUtil.filePathBlackList(filePath));
		File[] fileList = uploadFile.listFiles();
		List<String> fileArray = new ArrayList<String>();

		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				fileArray.add(fileList[i].getName());
			}
		}
		return fileArray;
	}

	/**
	 * 동기화 대상 파일을 업로드 한다.
	 * 
	 * @param multipartFile
	 * @param newName
	 * @param stordFilePath
	 * @param synchrnServerVO
	 */
	public void writeFile(MultipartFile multipartFile, String newName, String stordFilePath, SynchrnServerVO synchrnServerVO)  {

		List<SynchrnServerVO> synchrnServerList = synchrnServerMapper.processSynchrnServerList(synchrnServerVO);
		Iterator<SynchrnServerVO> iterator = synchrnServerList.iterator();
		SynchrnServerVO synchrnServer = new SynchrnServerVO();

		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = multipartFile.getInputStream();
			File cFile = new File(WebUtil.filePathBlackList(stordFilePath));

			if (!cFile.isDirectory())
				cFile.mkdir();

			bos = new FileOutputStream(WebUtil.filePathBlackList(stordFilePath + File.separator + newName));

			int bytesRead = 0;
			byte[] buffer = new byte[2048];

			while ((bytesRead = stream.read(buffer, 0, 2048)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			while (iterator.hasNext()) {
				SynchrnServerVO SynchrnServerVo = iterator.next();
				synchrnServer.setServerId(SynchrnServerVo.getServerId());
				synchrnServer.setReflctAt("N");
				synchrnServerMapper.processSynchrn(synchrnServer);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치 finally
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception ignore) {
					System.out.println("IGNORE: " + ignore);
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception ignore) {
					System.out.println("IGNORE: " + ignore);
				}
			}
		}
	}

	/**
	 * 업로드 파일을 삭제한다.
	 * 
	 * @param deleteFiles
	 * @param stordFilePath
	 * @param synchrnServerVO
	 */
	public void deleteFile(String deleteFiles, String stordFilePath, SynchrnServerVO synchrnServerVO) {

		List<SynchrnServerVO> synchrnServerList = synchrnServerMapper.processSynchrnServerList(synchrnServerVO);
		Iterator<SynchrnServerVO> iterator = synchrnServerList.iterator();
		SynchrnServerVO synchrnServer = new SynchrnServerVO();

		String[] strDeleteFiles = deleteFiles.split(";");

		for (int i = 0; i < strDeleteFiles.length; i++) {
			File uploadFile = new File(WebUtil.filePathBlackList(stordFilePath + strDeleteFiles[i]));
			uploadFile.delete();
		}

		while (iterator.hasNext()) {
			SynchrnServerVO SynchrnServerVo = iterator.next();
			synchrnServer.setServerId(SynchrnServerVo.getServerId());
			synchrnServer.setReflctAt("N");
			synchrnServerMapper.processSynchrn(synchrnServer);
		}
	}
	
}