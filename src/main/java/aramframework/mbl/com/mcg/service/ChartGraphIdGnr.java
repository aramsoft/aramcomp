package aramframework.mbl.com.mcg.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;

@Configuration
public class ChartGraphIdGnr {

	@Autowired
	DataSource dataSource;
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService chartGraphIdGnrService() {
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("MCG_ID");
		
		return idGnrService;
	}
	
}
