package com.crave.crave_backend.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crave.crave_backend.configuration.security.JwtUtils;
import com.crave.crave_backend.constant.ErrorMessageConstants;
import com.crave.crave_backend.dto.in.UserLoginInDto;
import com.crave.crave_backend.dto.out.UserLogInOutDto;
import com.crave.crave_backend.entity.User;
import com.crave.crave_backend.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwtUtils; 
	
	private Logger log = LoggerFactory.getLogger(AuthService.class);
	
	
	//Intentionally designed to mitigate timing attacks 
	public UserLogInOutDto authenticate(UserLoginInDto userLoginInDto) {
		Optional<User> userOptional = userRepository.findByContactNumber(userLoginInDto.getContactNumber());
		String givenPassword = userLoginInDto.getPassword();
		String userPassword = "dummyHash";
		User user = new User();
		
		if (!userOptional.isEmpty()) {
			user = userOptional.get();
			userPassword = user.getPasswordHash();
		}
		
		if (!passwordEncoder.matches(givenPassword, userPassword)) {
			throw new BadCredentialsException(ErrorMessageConstants.BAD_CREDENTIALS);
		}
		String accessToken = jwtUtils.getAccessToken(user.getId());
		
		log.info("event=User login successful UserId={}", user.getId());
		return new UserLogInOutDto(accessToken);
	}
}
