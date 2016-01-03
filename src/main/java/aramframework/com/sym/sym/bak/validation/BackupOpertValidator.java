package aramframework.com.sym.sym.bak.validation;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import aramframework.com.sym.sym.bak.service.BackupOpertVO;

/**
 * BackupOpert클래스에대한 validator 클래스. common validator가 처리하지 못하는 부분 검사.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

@Component("backupOpertValidator")
public class BackupOpertValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		return BackupOpertVO.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		// 배치프로그램으로 지정된 값이 파일로 존재하는지 검사한다.
		BackupOpertVO backupOpert = (BackupOpertVO) obj;
		File dir = null;
		String srcDir = backupOpert.getBackupOrginlDrctry();
		dir = new File(srcDir);
		try {
			if (!dir.exists()) {
				errors.rejectValue("backupOrginlDrctry", "errors.backupOrginlDrctry", new Object[] { srcDir }, "디렉토리 {0}이  존재하지 않습니다.");
				return;
			}
			if (!dir.isDirectory()) {
				errors.rejectValue("backupOrginlDrctry", "errors.backupOrginlDrctry", new Object[] { srcDir }, "본디렉토리 {0}이 디렉토리가 아닙니다.");
				return;
			}
		} catch (SecurityException se) {
			errors.rejectValue("backupOrginlDrctry", "errors.backupOrginlDrctry", new Object[] { srcDir }, " 디렉토리 {0}에 접근할 수 없습니다. 파일접근권한을 확인하세요.");
			return;
		}

		String targetDir = backupOpert.getBackupStreDrctry();
		dir = new File(backupOpert.getBackupStreDrctry());
		try {
			if (!dir.exists()) {
				errors.rejectValue("backupStreDrctry", "errors.backupStreDrctry", new Object[] { targetDir }, "디렉토리 {0}이  존재하지 않습니다.");
				return;
			}
			if (!dir.isDirectory()) {
				errors.rejectValue("backupStreDrctry", "errors.backupStreDrctry", new Object[] { targetDir }, "디렉토리 {0}이 디렉토리가 아닙니다.");
				return;
			}
		} catch (SecurityException se) {
			errors.rejectValue("backupStreDrctry", "errors.backupStreDrctry", new Object[] { targetDir }, " 디렉토리 {0}에 접근할 수 없습니다. 파일접근권한을 확인하세요.");
			return;
		}

		if (targetDir.equals(srcDir)) {
			errors.rejectValue("backupStreDrctry", "errors.backupStreDrctry", new Object[] { srcDir, targetDir }, "백업원본디렉토리{0}과 백업저장디렉토리 {1}이 같은 값을 가질수 없습니다.");
			return;
		}

	}

}
