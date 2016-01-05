package aramframework.com.sym.ccm.icr.service.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;

@Configuration
public class InsttCodeRecptnConfig {

	@Resource(name = "dataSource")
	DataSource dataSource;
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService insttCodeRecptnIdGnrService() {
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("INSTT_CODE_OPERT_SN");
		
		return idGnrService;
	}

}
