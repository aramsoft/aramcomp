package aramframework.mbl.com.mdi.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.mbl.com.mdi.dao.DeviceIdentMapper;
import aramframework.mbl.com.mdi.domain.DeviceIdentVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 모바일기기식별에 대한 Service Interface를 구현한다.
 * 
 * 상세내용
 * - 모바일기기 식별정보에 대한 등록, 수정, 삭제, 조회 기능과 User-Agent값 조회, 모바일기기 정보 추출 기능을 제공한다.
 * - 모바일기기 식별정보에 대한 조회기능은 목록, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class DeviceIdentService extends EgovAbstractServiceImpl {

	@Autowired
    private DeviceIdentMapper deviceIdentMapper;    

    /** ID Generation */
	@Autowired
    private EgovIdGnrService deviceIdentIdGnrService; 

    /**
     * 모바일 기기 정보 목록을 조회한다.
     * 
     * @param deviceIdentVO
     */
    public List<EgovMap> selectDeviceIdentList(DeviceIdentVO deviceIdentVO) {
        return deviceIdentMapper.selectDeviceIdentList(deviceIdentVO);
    }

    /**
     * 모바일 기기 정보의 총 갯수를 조회한다.
     * 
     * @param deviceIdentVO
     */
    public int selectDeviceIdentListCnt(DeviceIdentVO deviceIdentVO) {
        return deviceIdentMapper.selectDeviceIdentListCnt(deviceIdentVO);
    }

    /**
     * 모바일 기기의 상세정보를 조회한다.
     * 
     * @param deviceIdentVO
     */
    public DeviceIdentVO selectDeviceIdent(DeviceIdentVO deviceIdentVO) {
    	DeviceIdentVO resultVo = deviceIdentMapper.selectDeviceIdent(deviceIdentVO);
		// searchVO 이전 
		resultVo.setSearchVO(deviceIdentVO.getSearchVO()); 
		return resultVo;
    }

    /**
     * 모바일 기기 정보를 등록한다.
     * 
     * @param deviceIdentVO
     */
    public void insertDeviceIdent(DeviceIdentVO deviceIdentVO) {
    	try {
			deviceIdentVO.setSn(deviceIdentIdGnrService.getNextIntegerId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
        deviceIdentMapper.insertDeviceIdent(deviceIdentVO);
    }

    /**
     * 조회된 모바일 기기 정보를 수정한다.
     * 
     * @param deviceIdentVO
     */
    public void updateDeviceIdent(DeviceIdentVO deviceIdentVO) {
        deviceIdentMapper.updateDeviceIdent(deviceIdentVO);
    }

    /**
     * 조회된 모바일 기기 정보를 삭제한다.
     * 
     * @param deviceIdentVO
     */
    public void deleteDeviceIdent(DeviceIdentVO deviceIdentVO) {
        deviceIdentMapper.deleteDeviceIdent(deviceIdentVO);
    }

    /**
     * 모바일 기기 정보를 XML 파일로 생성한다.
     */
    public void createDeviceIndentListToXML() {

        // DOM 파서 생성
        Element rootElement = new Element("DeviceIdents");
        Document document = new Document(rootElement);

        // 모바일 기기 정보 목록 읽기
        DeviceIdentVO deviceIdentVO = new DeviceIdentVO();
        deviceIdentVO.getSearchVO().setFirstIndex(0);
        deviceIdentVO.getSearchVO().setRecordPerPage(1000);
        List<EgovMap> deviceIdentList = selectDeviceIdentList(deviceIdentVO);

        // 모바일 기기 정보를 Document 변환
        for (int i = 0; i < deviceIdentList.size(); i++) {
            Element deviceIdentElement = new Element("DeviceIdent");

            EgovMap egovMap = (EgovMap) deviceIdentList.get(i);

            Element uagentInfoElement = new Element("UserAgent");
            uagentInfoElement.setText((String) egovMap.get("uagentInfo"));
            deviceIdentElement.addContent(uagentInfoElement);

            Element browserElement = new Element("Browser");
            browserElement.setText((String) egovMap.get("browserNm"));
            deviceIdentElement.addContent(browserElement);

            Element osElement = new Element("OS");
            osElement.setText((String) egovMap.get("osNm"));
            deviceIdentElement.addContent(osElement);

            rootElement.addContent(deviceIdentElement);
        }

        // 파일로 저장하기
        XMLOutputter output = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        output.setFormat(format);
        
        String xmlPath = AramProperties.getProperty(AramProperties.getPathProperty("Globals.MDIConfPath"), "deviceIdentXMLPath");
        
    	//KISA 보안 점검 결과 반영
    	FileOutputStream out = null;
    	OutputStreamWriter writer = null;
    	
		try {
			out = new FileOutputStream(xmlPath);
			writer = new OutputStreamWriter(out, "utf-8");
			output.output(document, writer);
			
		} catch (FileNotFoundException e) {			
			egovLogger.debug("Fail to createDeviceIndentListToXML : FileNotFoundException");
		} catch (UnsupportedEncodingException e) {
			
			egovLogger.debug("Fail to createDeviceIndentListToXML : UnsupportedEncodingException");
		}catch (IOException e) {
			
			egovLogger.debug("Fail to createDeviceIndentListToXML : IOException");
		}finally{//2011.11.23  보안점검 결과 반영
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {}
			}
		}
    }

    /**
     * user-Agent 값에서 모바일기기 정보를 추출한다.
     * @param userAgent
     */
    public DeviceIdentVO getDeviceIdentFromXML(String userAgent) {

    	DeviceIdentVO deviceIdentVO = null;

        SAXBuilder builder = new SAXBuilder();
        
        String xmlPath = AramProperties.getProperty(AramProperties.getPathProperty("Globals.MDIConfPath"), "deviceIdentXMLPath");
        
        if (!((new File(xmlPath)).isFile())) {
            return null;
        }
               
        Document document;
		try {
			document = builder.build(new File(xmlPath));
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
        Element rootElement = document.getRootElement();
        List<Element> deviceIdentElements = rootElement.getChildren();

        for (Element deviceIdentElement : deviceIdentElements) {

            List<Element> elements = deviceIdentElement.getChildren();
            Element uagentInfoElement = elements.get(0);
            Element browserElement = elements.get(1);
            Element osElement = elements.get(2);
            if (uagentInfoElement.getText().equalsIgnoreCase(userAgent)
                && uagentInfoElement.getName().equalsIgnoreCase("UserAgent")
                && browserElement.getName().equalsIgnoreCase("Browser")
                && osElement.getName().equalsIgnoreCase("OS")) {
            	deviceIdentVO = new DeviceIdentVO();
            	deviceIdentVO.setUagentInfo(uagentInfoElement.getText());
            	deviceIdentVO.setBrowserNm(browserElement.getText());
            	deviceIdentVO.setOsNm(osElement.getText());

                break;
            }
        }

        return deviceIdentVO;
    }
	
}
