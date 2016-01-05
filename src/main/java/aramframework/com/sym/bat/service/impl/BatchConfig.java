package aramframework.com.sym.bat.service.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

@Configuration
public class BatchConfig {

	@Resource(name = "dataSource")
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
