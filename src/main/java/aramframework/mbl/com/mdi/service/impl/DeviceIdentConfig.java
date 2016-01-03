package aramframework.mbl.com.mdi.service.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;

@Configuration
public class DeviceIdentConfig {

	@Resource(name = "egov.dataSource")
	DataSource dataSource;
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService deviceIdentIdGnrService() {
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("MDI_ID");
		
		return idGnrService;
	}
	
}
