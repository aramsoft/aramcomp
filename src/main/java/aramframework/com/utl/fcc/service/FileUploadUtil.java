package aramframework.com.utl.fcc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import aramframework.cmm.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Spring 기반 File Upload 유틸리티
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class FileUploadUtil extends FormBasedFileUtil {
	/**
	 * 파일을 Upload 처리한다.
	 * 
	 * @param request
	 * @param where
	 * @param maxFileSize
	 * @return
	 * @throws Exception
	 */
	public static List<FormBasedFileVo> uploadFiles(
			HttpServletRequest request, 
			String where, 
			long maxFileSize) throws Exception {
		
		List<FormBasedFileVo> list = new ArrayList<FormBasedFileVo>();

		MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest) request;

		for(MultipartFile mFile: mptRequest.getFileMap().values()) {

			FormBasedFileVo vo = new FormBasedFileVo();

			String tmp = mFile.getOriginalFilename();
			if (tmp.lastIndexOf("\\") >= 0) {
				tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
			}

			vo.setFileName(tmp);
			vo.setContentType(mFile.getContentType());
			vo.setServerSubPath(getTodayString());
			vo.setPhysicalName(getPhysicalFileName());
			vo.setSize(mFile.getSize());

			if (tmp.lastIndexOf(".") >= 0) {
				vo.setPhysicalName(vo.getPhysicalName() + tmp.substring(tmp.lastIndexOf(".")));
			}

			if (mFile.getSize() > 0) {
				saveFile(mFile.getInputStream(),
						new File(WebUtil.filePathBlackList(where + SEPERATOR + vo.getServerSubPath() 
																 + SEPERATOR + vo.getPhysicalName())));
				list.add(vo);
			}
		}

		return list;
	}
}
