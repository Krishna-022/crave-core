package com.crave.crave_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.crave.crave_backend.constant.ApiPathConstants;
import com.crave.crave_backend.dto.in.RegisterUserInDto;
import com.crave.crave_backend.dto.out.MessageOutDto;
import com.crave.crave_backend.service.UserService;
import com.crave.crave_backend.validation.UserValidation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPathConstants.User.BASE)
public class UserController {
	
	@Autowired
	private UserValidation userValidation;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageOutDto registerUser(@Valid @RequestBody RegisterUserInDto registerUserInDto) {
		userValidation.validateUserContactNumber(registerUserInDto.getContactNumber());
		userValidation.validateUserEmail(registerUserInDto.getEmail());
		return userService.registerUser(registerUserInDto);
	}
}
