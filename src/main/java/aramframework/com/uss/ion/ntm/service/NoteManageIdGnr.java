package aramframework.com.uss.ion.ntm.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

/**
 * 쪽지 관리(보내기) Id generation 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Configuration
public class NoteManageIdGnr {

	@Autowired
	DataSource dataSource;
/*	
	@Bean
	public EgovIdGnrStrategy noteManageIdStrategy() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("NOTEID_");
		strategy.setCipers(13);
		strategy.setFillChar('0');
		
		return strategy;
	}
*/
	@Bean(destroyMethod="destroy")
	public EgovIdGnrService noteManageIdGnrService() {
		
		EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
		strategy.setPrefix("NOTEID_");
		strategy.setCipers(13);
		strategy.setFillChar('0');
		
		EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
		idGnrService.setDataSource(dataSource);
//		idGnrService.setStrategy(noteManageIdStrategy());
		idGnrService.setStrategy(strategy);
		idGnrService.setBlockSize(1);
		idGnrService.setBlockSize(1);
		idGnrService.setTable("COMTE_COPSEQ");
		idGnrService.setTableName("NOTE_ID");
		
		return idGnrService;
	}
	
}
