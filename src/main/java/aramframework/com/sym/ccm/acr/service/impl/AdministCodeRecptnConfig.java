package aramframework.com.sym.ccm.acr.service.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;

@Configuration
public class AdministCodeRecptnConfig {

	@Resource(name = "egov.dataSource")
	DataSource dataSource;
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService administCodeRecptnIdGnrService() {
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("ADMIN_CODE_OPERT_SN");
		
		return idGnrService;
	}

}
