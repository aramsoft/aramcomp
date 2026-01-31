package aramframework.com.sym.bat.validation;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import aramframework.com.sym.bat.domain.BatchOpertVO;

/**
 * BatchOpert클래스에대한 validator 클래스. 
 * common validator가 처리하지 못하는 부분 검사.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Component("batchOpertValidator")
public class BatchOpertValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		return BatchOpertVO.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		// 배치프로그램으로 지정된 값이 파일로 존재하는지 검사한다.
		BatchOpertVO batchOpert = (BatchOpertVO) obj;
		File file = new File(batchOpert.getBatchProgrm());
		try {
			if (!file.exists()) {
				errors.rejectValue("batchProgrm", "errors.batchProgrm", new Object[] { batchOpert.getBatchProgrm() }, "배치프로그램 {0}이  존재하지 않습니다.");
				return;
			}
			if (!file.isFile()) {
				errors.rejectValue("batchProgrm", "errors.batchProgrm", new Object[] { batchOpert.getBatchProgrm() }, "배치프로그램 {0}이 파일이 아닙니다.");
				return;
			}
		} catch (SecurityException se) {
			errors.rejectValue("batchProgrm", "errors.batchProgrm", new Object[] { batchOpert.getBatchProgrm() }, " 배치프로그램 {0}에 접근할 수 없습니다. 파일접근권한을 확인하세요.");
		}

	}

}
