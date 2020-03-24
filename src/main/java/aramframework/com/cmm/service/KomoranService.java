package aramframework.com.cmm.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class KomoranService extends EgovAbstractServiceImpl {

    private static Komoran komoran;

    /**
     * Komoran에서 기본으로 제공되는 모델을 로딩하여 객체를 생성합니다. </p>
     * 별도의 경로를 지정할 필요가 없습니다.
     *
     */
    @PostConstruct
    public void init() {
        komoran = new Komoran(DEFAULT_MODEL.STABLE);
    }

    /**
     * 입력된 문장에 대해서 형태소 분석을 진행합니다.
     *
     * @param sentence 분석 대상 문장
     * @return 형태소 분석 결과
     */
    public KomoranResult analyze(String sentence) {
        return komoran.analyze(sentence, 1).get(0);
    }

}
