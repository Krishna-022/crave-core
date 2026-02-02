package com.crave.crave_backend.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crave.crave_backend.constant.ErrorMessageConstants;
import com.crave.crave_backend.dto.in.LoginInDto;
import com.crave.crave_backend.entity.User;
import com.crave.crave_backend.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Intentionally designed to mitigate timing attacks
	public Long authenticate(LoginInDto loginInDto) {
		Optional<User> userOptional = userRepository.findByContactNumber(loginInDto.getContactNumber());
		String givenPassword = loginInDto.getPassword();
		String userPassword = "dummyHash";
		User user = new User();

		if (!userOptional.isEmpty()) {
			user = userOptional.get();
			userPassword = user.getPasswordHash();
		}
		if (!passwordEncoder.matches(givenPassword, userPassword)) {
			throw new BadCredentialsException(ErrorMessageConstants.BAD_CREDENTIALS);
		}
		return user.getId();
	}
}
