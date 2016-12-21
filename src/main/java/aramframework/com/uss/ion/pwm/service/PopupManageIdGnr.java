package aramframework.com.uss.ion.pwm.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

/**
 * 팝업창 Id generation 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Configuration
public class PopupManageIdGnr {

	@Autowired
	DataSource dataSource;
/*	
	@Bean
	public EgovIdGnrStrategy popupManageIdStrategy() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("POPUP_");
		strategy.setCipers(14);
		strategy.setFillChar('0');
		
		return strategy;
	}
*/
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService popupManageIdGnrService() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("POPUP_");
		strategy.setCipers(14);
		strategy.setFillChar('0');
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
//		idGnrService.setStrategy(popupManageIdStrategy());
		idGnrService.setStrategy(strategy);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("POPUP_ID");
		
		return idGnrService;
	}
	
}
