package aramframework.mbl.com.mlt.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 개요
 * - 지원브라우저 정보를 담는 XML을 정의한다.
 * 
 * 상세내용
 * - 지원브라우저 정보를 관리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class MultimediaFileInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 파일 구분
     */
    private String classification;

    /**
     * 파일 확장자
     */
    private String extension;

    /**
     * 파일 타입
     */
    private String type;

    /**
     * 지원 브라우저
     */
    private List<String> browserList;

    /**
     * 파일 구분을 가져온다.
     * @return String
     */
    public String getClassification() {
        return classification;
    }

    /**
     * 파일 구분을 저장한다.
     * @param classification
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * 파일 확장자를 가져온다.
     * @return String
     */
    public String getExtension() {
        return extension;
    }

    /**
     * 파일 확장자를 저장한다.
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * 파일 타입을 가져온다.
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * 파일 타입을 저장한다.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 지원 브라우저를 가져온다.
     * @return String
     */
    public List<String> getBrowserList() {
        return browserList;
    }

    /**
     * 지원 브라우저를 저장한다.
     * @param browserList
     */
    public void setBrowserList(List<String> browserList) {
        this.browserList = browserList;
    }
}
