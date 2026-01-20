package com.crave.crave_backend.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crave.crave_backend.configuration.security.JwtUtil;
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
	private JwtUtil jwtUtil; 
	
	public UserLogInOutDto authenticate(UserLoginInDto userLoginInDto) {
		Optional<User> userOptional = userRepository.findByContactNumber(userLoginInDto.getContactNumber());
		
		if (userOptional.isEmpty()) {
			throw new BadCredentialsException(ErrorMessageConstants.BAD_CREDENTIALS);
		}
		User user = userOptional.get();
		String givenPassword = userLoginInDto.getPassword();
		String userPassword = user.getPasswordHash();
		if (!passwordEncoder.matches(givenPassword, userPassword)) {
			throw new BadCredentialsException(ErrorMessageConstants.BAD_CREDENTIALS);
		}
		String accessToken = jwtUtil.getAccessToken(user.getId());
		
		return new UserLogInOutDto(accessToken);
	}
}
