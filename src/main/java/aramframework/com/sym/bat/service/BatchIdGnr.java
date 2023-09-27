package aramframework.com.sym.bat.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import org.egovframe.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

/**
 * 배치작업관리 Id generation 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Configuration
public class BatchIdGnr {

	@Autowired
	DataSource dataSource;
	
/*	
	@Bean
	public EgovIdGnrStrategy batchOpertIdStrategy() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("BAT");
		strategy.setCipers(17);
		strategy.setFillChar('0');
		
		return strategy;
	}
*/
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService batchOpertIdGnrService() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("BAT");
		strategy.setCipers(17);
		strategy.setFillChar('0');

		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
//		idGnrService.setStrategy(batchOpertIdStrategy());
		idGnrService.setStrategy(strategy);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("BATCH_OPERT_ID");
		
		return idGnrService;
	}

/*	
	@Bean
	public EgovIdGnrStrategy batchSchdulIdStrategy() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("BSC");
		strategy.setCipers(17);
		strategy.setFillChar('0');
		
		return strategy;
	}
*/
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService batchSchdulIdGnrService() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("BSC");
		strategy.setCipers(17);
		strategy.setFillChar('0');
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
//		idGnrService.setStrategy(batchSchdulIdStrategy());
		idGnrService.setStrategy(strategy);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("BATCH_SCHDUL_ID");
		
		return idGnrService;
	}

/*	
	@Bean
	public EgovIdGnrStrategy batchResultIdStrategy() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("BRT");
		strategy.setCipers(17);
		strategy.setFillChar('0');
		
		return strategy;
	}
*/
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService batchResultIdGnrService() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("BRT");
		strategy.setCipers(17);
		strategy.setFillChar('0');
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
//		idGnrService.setStrategy(batchResultIdStrategy());
		idGnrService.setStrategy(strategy);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("BATCH_RESULT_ID");
		
		return idGnrService;
	}

}
