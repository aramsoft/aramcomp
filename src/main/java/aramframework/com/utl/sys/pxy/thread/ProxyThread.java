package aramframework.com.utl.sys.pxy.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 개요 - 
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class ProxyThread implements Runnable {

//	private Socket client = null;

	private InputStream streamFromClient = null;
//	private OutputStream streamToClient = null;
//	private InputStream streamFromServer = null;
	private OutputStream streamToServer = null;

	private boolean isStop = false;

	byte[] request = new byte[1024];
	byte[] reply = new byte[4096];

	public ProxyThread(Socket client) {
//		this.client = client;
	}

	public ProxyThread(Socket client, InputStream streamFromClient, OutputStream streamToClient, InputStream streamFromServer, OutputStream streamToServer)
			throws IOException {
//		this.client = client;
		this.streamFromClient = streamFromClient;
//		this.streamToClient = streamToClient;
//		this.streamFromServer = streamFromServer;
		this.streamToServer = streamToServer;
	}

	public void setIsStop(boolean isStop) {
		this.isStop = isStop;
	}

	public boolean getIsStop() {
		return this.isStop;
	}

	public void run() {

		int bytesRead;
		String strReceive = "";

		try {

			if (streamFromClient != null) {

				while ((bytesRead = streamFromClient.read(request)) != -1) {

					strReceive = new String(request, 0, bytesRead);

					if (strReceive.indexOf("stop") > -1) {
						setIsStop(true);
						break;
					}

					// streamToServer.write(strReceive.getBytes(), 0, strReceive.getBytes().length);
					streamToServer.write(request, 0, bytesRead);
					streamToServer.flush();
				}
			}
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println(e); // 2011.10.10 보안점검 후속조치
		} finally {
			try {
				streamToServer.close();
			} catch (IOException ex) {
				// ex.printStackTrace();
				System.out.println("IGNORE: " + ex); // 2011.10.10 보안점검 후속조치
			}
		}
	}
}
