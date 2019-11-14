package aramframework.com.cmm.config.filter;

import org.springframework.security.crypto.password.PasswordEncoder;

import aramframework.com.utl.sim.service.FileScrty;

/**
 * 인증 작업에 필요한 password encode임.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2019.11.14
 * @version 1.0
 */
public final class AramPasswordEncoder implements PasswordEncoder {

	public String encode(CharSequence rawPassword) {
		String password;
		try {
			password = FileScrty.encryptPassword(rawPassword.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return password;
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String password;
		try {
			password = FileScrty.encryptPassword(rawPassword.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return password.equals(encodedPassword);
	}

	private AramPasswordEncoder() {
	}

}
