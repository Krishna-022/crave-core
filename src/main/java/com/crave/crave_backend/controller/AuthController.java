package com.crave.crave_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.crave.crave_backend.constant.ApiPathConstants;
import com.crave.crave_backend.dto.in.UserLoginInDto;
import com.crave.crave_backend.dto.out.UserLogInOutDto;
import com.crave.crave_backend.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPathConstants.Auth.BASE)
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(ApiPathConstants.Auth.LOG_IN)
	@ResponseStatus(HttpStatus.OK)
	public UserLogInOutDto userLogin(@Valid @RequestBody UserLoginInDto userLoginInDto) {
		return authService.authenticate(userLoginInDto);
	}
}
