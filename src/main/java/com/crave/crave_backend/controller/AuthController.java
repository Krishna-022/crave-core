package com.crave.crave_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crave.crave_backend.configuration.security.JwtUtils;
import com.crave.crave_backend.constant.ApiPathConstants;
import com.crave.crave_backend.dto.in.LoginInDto;
import com.crave.crave_backend.dto.out.LogInOutDto;
import com.crave.crave_backend.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPathConstants.Auth.BASE)
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtils jwtUtils;

	private Logger log = LoggerFactory.getLogger(AuthController.class);

	@PostMapping(ApiPathConstants.Auth.LOG_IN)
	public LogInOutDto userLogin(@Valid @RequestBody LoginInDto loginInDto) {
		log.info("event=Received user login request");
		Long userId = authService.authenticate(loginInDto);
		String token = jwtUtils.getAccessToken(userId);
		log.info("event=User login successful UserId={}", userId);
		return new LogInOutDto(token);
	}
}
