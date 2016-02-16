package aramframework.mbl.com.mlt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.domain.FileVO;
import aramframework.mbl.com.mlt.dao.MultimediaMapper;
import aramframework.mbl.com.mlt.domain.MultimediaFileInfoVO;
import aramframework.mbl.com.mlt.domain.MultimediaVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 멀티미디어 제어에 대한 Service Interface를 구현한다.
 * 
 * 상세내용
 * - 멀티미디어에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 멀티미디어에 대한 조회기능은 목록, 상세조회로 구분된다.
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
public class MultimediaService extends EgovAbstractServiceImpl {

	@Autowired
    private MultimediaMapper multimediaMapper;    

    /** ID Generation */
	@Autowired
    private EgovIdGnrService multimediaIdGnrService;
 
    /**
     * 멀티미디어 목록을 조회 관련 비즈니스 구현 메서드
     * 
     * @param multimediaVO
     */
    public List<EgovMap> selectMultimediaList(MultimediaVO multimediaVO) {
        return multimediaMapper.selectMultimediaList(multimediaVO);
    }

    /**
     * 멀티미디어 정보의 총 갯수를 조회한다.
     * 
     * @param multimediaVO
     */
    public int selectMultimediaListCnt(MultimediaVO multimediaVO) {
        return multimediaMapper.selectMultimediaListCnt(multimediaVO);
    }

    /**
     * 멀티미디어 정보 상세 조회 관련 비즈니스 구현 메서드
     * 
     * @param multimediaVO
     */
    public MultimediaVO selectMultimedia(MultimediaVO multimediaVO) {
    	MultimediaVO resultVo = multimediaMapper.selectMultimedia(multimediaVO);
		// searchVO 이전 
		resultVo.setSearchVO(multimediaVO.getSearchVO()); 
		return resultVo;
   }

    /**
     * 멀티미디어 정보 등록 관련 비즈니스 구현 메서드
     * 
     * @param multimediaVO
     */
    public void insertMultimedia(MultimediaVO multimediaVO) {
        try {
			multimediaVO.setSn(multimediaIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
        multimediaMapper.insertMultimedia(multimediaVO);
    }

    /**
     * 멀티미디어 정보 수정 관련 비즈니스 구현 메서드
     * 
     * @param multimediaVO
      */
    public void updateMultimedia(MultimediaVO multimediaVO) {
        multimediaMapper.updateMultimedia(multimediaVO);
    }

    /**
     * 멀티미디어 정보 삭제 관련 비즈니스 구현 메서드
     * 
     * @param multimediaVO
     */
    public void deleteMultimedia(MultimediaVO multimediaVO) {
        multimediaMapper.deleteMultimedia(multimediaVO);
    }

    /**
     * 멀티미디어 파일을 상대 경로에 저장한다.
     * 
     * @param fileList
     */
    public void copyFileToRelativePath(List<FileVO> fileList) {

        String currPath = this.getClass().getResource("").getPath();
        String absolutePath = currPath.substring(0, currPath.lastIndexOf("WEB-INF")) + "multimedia";

        // 디렉토리 생성
        if (!(new File(absolutePath)).isDirectory()) {
            (new File(absolutePath)).mkdirs();
        }

        for (int i = 0; i < fileList.size(); i++) {
            File sFile =
                new File(fileList.get(i).getFileStreCours(), fileList.get(i)
                    .getStreFileNm());
            File dFile =
                new File(absolutePath, fileList.get(i).getStreFileNm() + "."
                    + fileList.get(i).getFileExtsn());

            int fSize = (int) sFile.length();

            // 파일 복사
            if (fSize > 0) {

                FileInputStream fis = null;
                FileOutputStream fos = null;

                try {
                    fis = new FileInputStream(sFile);
                    fos = new FileOutputStream(dFile);

                    FileCopyUtils.copy(fis, fos);
                } catch (Exception ex) {
                	egovLogger.debug("IGNORED: " + ex.getMessage());
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (Exception ignore) {
                            // no-op
                        	egovLogger.debug("IGNORED: " + ignore.getMessage());
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (Exception ignore) {
                            // no-op
                        	egovLogger.debug("IGNORED: " + ignore.getMessage());
                        }
                    }
                }
            }
        }
    }

    /**
     * 파일별 지원 브라우저 정보를 조회한다.
     * 
     */
    public List<MultimediaFileInfoVO> getMultimediaFileInfoFromXML() {

    	String XMLPath = AramProperties.SYSCONFIG_PATH_PREFIX 
    				   + "multimedia" + System.getProperty("file.separator") 
    				   + "MultimediaFileInfo.xml";
 
        List<MultimediaFileInfoVO> multimediaFileInfoList = new ArrayList<MultimediaFileInfoVO>();

        SAXBuilder builder = new SAXBuilder();
        Document document;
		try {
			document = builder.build(new File(XMLPath));
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        Element rootElement = document.getRootElement();
        List<Element> multimediaFileInfoElements = rootElement.getChildren();

        for (Element multimediaFileInfoElement : multimediaFileInfoElements) {

            MultimediaFileInfoVO multimediaFileInfo = new MultimediaFileInfoVO();

            // 파일 구분
            multimediaFileInfo.setClassification(multimediaFileInfoElement.getName());

            // 파일 확장자
            multimediaFileInfo.setExtension(multimediaFileInfoElement.getAttributeValue("extension"));

            // 파일 타입
            multimediaFileInfo.setType(multimediaFileInfoElement.getAttributeValue("type"));

            // 지원 브라우저
            List<Element> elements = multimediaFileInfoElement.getChildren();
            List<String> browserList = new ArrayList<String>();

            for (int j = 0; j < elements.size(); j++) {
                if (elements.get(j).getName().equalsIgnoreCase("browser")) {
                    browserList.add(elements.get(j).getText());
                }
            }
            multimediaFileInfo.setBrowserList(browserList);

            multimediaFileInfoList.add(multimediaFileInfo);
        }

        return multimediaFileInfoList;
    }

    /**
     * 지원 브라우저 정보를 조회한다.
     * @param mltmdNm
     * @param extList
     */
    public String getBrowserInfoFromXML(String mltmdNm, List<String> extList) {
        String browserInfo = "";
        String mltmdNmType = "";
        
        // 멀티미디어 파일 구분 명 변경 (XML 태그명과 일치)
        if (mltmdNm.equalsIgnoreCase("동영상")) {
        	mltmdNmType = "Video";
        } else if (mltmdNm.equalsIgnoreCase("음악")) {
        	mltmdNmType = "Audio";
        }

    	String XMLPath = AramProperties.SYSCONFIG_PATH_PREFIX 
				       + "multimedia" + System.getProperty("file.separator") 
				       + "MultimediaFileInfo.xml";

        SAXBuilder builder = new SAXBuilder();
        Document document;
		try {
			document = builder.build(new File(XMLPath));
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        Element rootElement = document.getRootElement();

        List<Element> multimediaFileInfoElements = rootElement.getChildren();

        for (Element multimediaFileInfoElement : multimediaFileInfoElements) {

            for (int i = 0; i < extList.size(); i++) {

                // 파일 구분 비교
                if (multimediaFileInfoElement.getName().equalsIgnoreCase(mltmdNmType)) {
                    // 파일 확장자 비교
                    if (multimediaFileInfoElement.getAttributeValue("extension").equalsIgnoreCase(extList.get(i))) {
                        // 지원 브라우저 입력
                        List<Element> elements = multimediaFileInfoElement.getChildren();

                        for (int j = 0; j < elements.size(); j++) {
                            if (elements.get(j).getName().equalsIgnoreCase("browser")) {
                                String elementsText = elements.get(j).getText();
                                if (browserInfo.indexOf(elementsText) < 0) {
                                    browserInfo += elementsText + " ";
                                }
                            }
                        }
                    }
                }
            }
        }

        return browserInfo;
    }
	
}
