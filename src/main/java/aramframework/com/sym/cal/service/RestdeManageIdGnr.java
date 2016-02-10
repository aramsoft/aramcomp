package aramframework.com.sym.cal.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;

@Configuration
public class RestdeManageIdGnr {

	@Autowired
	DataSource dataSource;
	
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService restDeIdGnrService() {
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("RESTDE_NO");
		
		return idGnrService;
	}

}
