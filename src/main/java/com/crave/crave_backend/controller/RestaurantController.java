package com.crave.crave_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.crave.crave_backend.constant.ApiPathConstants;
import com.crave.crave_backend.constant.SuccessMessageConstants;
import com.crave.crave_backend.dto.in.RegisterRestaurantIndto;
import com.crave.crave_backend.dto.out.MessageOutDto;
import com.crave.crave_backend.service.RestaurantService;
import com.crave.crave_backend.validation.RestaurantValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPathConstants.Restaurant.BASE)
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantValidation restaurantValidation;

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public MessageOutDto registerRestaurant(@Valid @ModelAttribute RegisterRestaurantIndto registerRestaurantIndto) {
		log.info("event=Restaurant registration request received");
		byte[] validatedImage = restaurantValidation.validateRestaurantRegistrationDetails(registerRestaurantIndto);
		Long restaurantId = restaurantService.registerRestaurant(registerRestaurantIndto, validatedImage);
		log.info("event=Restaurant registration successful restaurantId={}", restaurantId);
		return new MessageOutDto(SuccessMessageConstants.REGISTRATION_SUCCESSFUL);
	}
}
