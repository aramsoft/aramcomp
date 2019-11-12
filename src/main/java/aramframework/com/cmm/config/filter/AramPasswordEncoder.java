package aramframework.com.cmm.config.filter;

import org.springframework.security.crypto.password.PasswordEncoder;

import aramframework.com.utl.sim.service.FileScrty;

/**
 * This {@link PasswordEncoder} is provided for legacy and testing purposes only and is
 * not considered secure.
 *
 * A password encoder that does nothing. Useful for testing where working with plain text
 * passwords may be preferred.
 *
 * @author Keith Donald
 * This PasswordEncoder is not secure. Instead use an
 * adaptive one way function like BCryptPasswordEncoder, Pbkdf2PasswordEncoder, or
 * SCryptPasswordEncoder. Even better use {@link DelegatingPasswordEncoder} which supports
 * password upgrades.
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
