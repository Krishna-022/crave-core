package com.crave.crave_backend.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.crave.crave_backend.constant.EntityConflictLogConstants;
import com.crave.crave_backend.constant.ErrorMessageConstants;
import com.crave.crave_backend.dto.in.RegisterRestaurantIndto;
import com.crave.crave_backend.entity.Restaurant;
import com.crave.crave_backend.exception.EntityConflictException;
import com.crave.crave_backend.exception.InvalidImageException;
import com.crave.crave_backend.repository.RestaurantRepository;

@Component
public class RestaurantValidation {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserValidation userValidation;
	
	public byte[] validateRestaurantRegistrationDetails(RegisterRestaurantIndto registerRestaurantIndto) {
		Long userId = Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		userValidation.validateUser(userId);
		byte[] validatedImage = validateImage(registerRestaurantIndto.getImage());
		validateRegistrationDetails(registerRestaurantIndto.getContactNumber(), registerRestaurantIndto.getEmail(), registerRestaurantIndto.getName());
		return validatedImage;
	}
	
	public void validateRegistrationDetails(String contactNumber, String email, String name) {
		List<Restaurant> restaurantList = restaurantRepository.findByContactNumberOrEmailOrName(contactNumber, email, name);
		
		if (restaurantList.size() > 0) {
			List<String> messageList = new ArrayList<>();
			List<String> conflictingFieldsList = new ArrayList<>();
			String entity = Restaurant.class.getSimpleName();
			
			for (int i = 0; i < restaurantList.size(); i++) {
				Restaurant restaurant = restaurantList.get(i);
				
				if (contactNumber.equals(restaurant.getContactNumber())) {
		            messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity, EntityConflictLogConstants.CONTACT_NUMBER, contactNumber));
		            conflictingFieldsList.add(EntityConflictLogConstants.CONTACT_NUMBER);
		        }
				if (email.equals(restaurant.getEmail())) {
		            messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity, EntityConflictLogConstants.EMAIL, email));
		            conflictingFieldsList.add(EntityConflictLogConstants.EMAIL);
		        }
				if (name.equals(restaurant.getName())) {
					messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity, EntityConflictLogConstants.NAME, name));
		            conflictingFieldsList.add(EntityConflictLogConstants.NAME);
				}
			}
			throw new EntityConflictException(messageList, conflictingFieldsList, EntityConflictLogConstants.RESTAURANT);
		}
	}
	
	public byte[] validateImage(MultipartFile image) {
		if (image == null || image.isEmpty()) {
			throw new InvalidImageException(ErrorMessageConstants.RESTAURANT_IMAGE_REQUIRED);
		}
		if (!image.getContentType().startsWith("image/")) {
            throw new InvalidImageException(ErrorMessageConstants.INVALID_IMAGE_TYPE);
        }
        if (image.getSize() > 2 * 1024 * 1024) {
            throw new InvalidImageException(ErrorMessageConstants.LARGE_IMAGE);
        }
        byte[] validatedImage;
        try {
        	validatedImage = image.getBytes();
        }
        catch (IOException ex) {
        	throw new InvalidImageException(ErrorMessageConstants.IMAGE_READ_FAILED);
        }
        return validatedImage;
	}
}
