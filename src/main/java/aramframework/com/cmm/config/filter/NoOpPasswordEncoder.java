package aramframework.com.cmm.config.filter;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This {@link PasswordEncoder} is provided for legacy and testing purposes only and is
 * not considered secure.
 *
 * A password encoder that does nothing. Useful for testing where working with plain text
 * passwords may be preferred.
 *
 * @author Keith Donald
 * @deprecated This PasswordEncoder is not secure. Instead use an
 * adaptive one way function like BCryptPasswordEncoder, Pbkdf2PasswordEncoder, or
 * SCryptPasswordEncoder. Even better use {@link DelegatingPasswordEncoder} which supports
 * password upgrades.
 */
public final class NoOpPasswordEncoder implements PasswordEncoder {

	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

	private NoOpPasswordEncoder() {
	}

}
