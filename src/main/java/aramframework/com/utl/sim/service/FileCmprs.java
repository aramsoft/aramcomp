package aramframework.com.utl.sim.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

/**
 * 파일(디렉토리)의 압축 및 압축해제 하는 Business Interface class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class FileCmprs {

	static final int COMPRESSION_LEVEL = 8;
	static final int BUFFER_SIZE = 64 * 1024;
	static final char FILE_SEPARATOR = File.separatorChar;

	/**
	 * 이미지(디렉토리)을 축소하는 기능
	 * 
	 * @param String
	 *            source 원본파일명(디렉토리명)
	 * @param String
	 *            target 압축파일명
	 * @return boolean result 압축성공여부 True / False
	 * @exception Exception
	 */
	public static boolean resizeJpeg(String source, String target, int width) throws Exception {

		// 압축성공여부
		boolean result = false;

		String source1 = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String target1 = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(source1);

		if (srcFile.exists()) {

			String target2 = FileTool.createNewFile(target1);
			File tarFile = new File(target2);

			BufferedImage bi = ImageIO.read(srcFile);
			int type = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();
	 
			int height = (int)(((float)bi.getHeight()/(float)bi.getWidth())*width);
			BufferedImage resizedImage = new BufferedImage(width, height, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(bi, 0, 0, width, height, null);
			g.dispose();

			ImageIO.write(resizedImage, "jpg", tarFile); 

			result= true;
		}
		return result;
	}

	/**
	 * 이미지(디렉토리)을 압축하는 기능
	 * 
	 * @param String
	 *            source 원본파일명(디렉토리명)
	 * @param String
	 *            target 압축파일명
	 * @return boolean result 압축성공여부 True / False
	 * @exception Exception
	 */
	public static boolean cmprsJpeg(String source, String target) throws Exception {

		// 압축성공여부
		boolean result = false;

		String source1 = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String target1 = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(source1);

		if (srcFile.exists()) {

			String target2 = FileTool.createNewFile(target1);
			File tarFile = new File(target2);

			BufferedImage bi = ImageIO.read(srcFile);

			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
			ImageWriter writer = iter.next();

			ImageWriteParam iwp = writer.getDefaultWriteParam();
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

			// reduced quality.
			iwp.setCompressionQuality(0.1f);

			FileImageOutputStream output = new FileImageOutputStream(tarFile);

			writer.setOutput(output);

			IIOImage image = new IIOImage(bi, null, null);
			writer.write(null, image, iwp);

			writer.dispose();
			result= true;
		}
		return result;
	}

	/**
	 * 파일(디렉토리)을 압축하는 기능
	 * 
	 * @param String
	 *            source 원본파일명(디렉토리명)
	 * @param String
	 *            target 압축파일명
	 * @return boolean result 압축성공여부 True / False
	 * @exception Exception
	 */
	public static boolean cmprsFile(String source, String target) throws Exception {

		// 압축성공여부
		boolean result = false;
		int cnt = 0;
		// 읽어들일 byte 버퍼
		byte[] buffer = new byte[BUFFER_SIZE];

		FileInputStream finput = null;
		FileOutputStream foutput = null;
		ZipOutputStream zoutput = null;

		String source1 = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String target1 = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(source1);

		if (srcFile.exists()) {

			// 1. 파일인 경우
			if (srcFile.isFile()) {

				String target2 = FileTool.createNewFile(target1);
				File tarFile = new File(target2);

				foutput = null;
				zoutput = null;
				ZipEntry zentry = null;

				try {
					foutput = new FileOutputStream(tarFile);
					zoutput = new ZipOutputStream((OutputStream) foutput);
					finput = new FileInputStream(srcFile);
					zentry = new ZipEntry(srcFile.getName());
					zoutput.putNextEntry(zentry);
					zoutput.setLevel(COMPRESSION_LEVEL);
					cnt = 0;
					while ((cnt = finput.read(buffer)) != -1) {
						zoutput.write(buffer, 0, cnt);
					}
					zoutput.closeEntry();
					result = true;
				} catch (Exception e) {
					tarFile.delete();
					// e.printStackTrace();
					throw e; // 2011.10.10 보안점검 후속조치
				} finally {
					if (finput != null)
						finput.close();
					if (zoutput != null)
						zoutput.close();
					if (foutput != null)
						foutput.close();
				}

				// 2. 디렉토리인 경우
			} else if (srcFile.isDirectory()) {

				String target2 = FileTool.createNewFile(target1);
				File tarFile = new File(target2);

				ZipEntry zentry = null;

				try {
					foutput = new FileOutputStream(tarFile);
					zoutput = new ZipOutputStream((OutputStream) foutput);
					File[] fileArr = srcFile.listFiles();
					ArrayList<String> list = FileTool.getSubFilesByAll(fileArr);

					for (int i = 0; i < list.size(); i++) {
						File sfile = new File(list.get(i));
						finput = new FileInputStream(sfile);
						zentry = new ZipEntry(sfile.getAbsolutePath().replace('\\', '/').replaceAll(srcFile.getAbsolutePath().replace('\\', '/'), ""));
						zoutput.putNextEntry(zentry);
						zoutput.setLevel(COMPRESSION_LEVEL);
						cnt = 0;
						while ((cnt = finput.read(buffer)) != -1) {
							zoutput.write(buffer, 0, cnt);
						}
						finput.close();
						result = true;
					}
					zoutput.closeEntry();
				} catch (Exception e) {
					tarFile.delete();
					// e.printStackTrace();
					throw e; // 2011.10.10 보안점검 후속조치
				} finally {
					if (finput != null)
						finput.close();
					if (zoutput != null)
						zoutput.close();
					if (foutput != null)
						foutput.close();
				}
			}
		}
		return result;
	}

	/**
	 * 파일(디렉토리)을 압축해제하는 기능
	 * 
	 * @param String
	 *            source 압축파일명
	 * @param String
	 *            target 압출이 풀릴 디렉토리
	 * @return boolean result 압축해제성공여부 True / False
	 * @exception Exception
	 */
	public static boolean decmprsFile(String source, String target) throws Exception {

		// 압축해제성공여부
		boolean result = false;
		int cnt = 0;
		// 읽어들일 byte 버퍼
		byte[] buffer = new byte[BUFFER_SIZE];

		FileInputStream finput = null;
		FileOutputStream foutput = null;
		ZipInputStream zinput = null;

		String source1 = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String target1 = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(source1);

		if (srcFile.exists() && srcFile.isFile()) {

			String target2 = FileTool.createNewDirectory(target1);
			File tarFile = new File(target2);
			finput = new FileInputStream(srcFile);
			zinput = new ZipInputStream((InputStream) finput);

			foutput = null;
			ZipEntry entry;

			try {

				while ((entry = zinput.getNextEntry()) != null) {

					String filename = entry.getName();
					String tempFile = FileTool.createNewFile(tarFile.getAbsolutePath() + FILE_SEPARATOR + filename);
					File efile = new File(tempFile);
					foutput = new FileOutputStream(efile);
					while ((cnt = zinput.read(buffer)) != -1) {
						if (foutput != null)
							foutput.write(buffer, 0, cnt);
					}
				}

				result = true;

			} catch (Exception e) {
				// e.printStackTrace();
				throw e; // 2011.10.10 보안점검 후속조치
			} finally {
				if (finput != null)
					finput.close();
				if (zinput != null)
					zinput.close();
				if (foutput != null)
					foutput.close();
			}
		}
		return result;
	}
}
