package com.crave.crave_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.crave.crave_backend.constant.DatabaseConstraintNames;
import com.crave.crave_backend.constant.EntityConflictLogConstants;
import com.crave.crave_backend.constant.ErrorMessageConstants;
import com.crave.crave_backend.dto.in.RegisterRestaurantInDto;
import com.crave.crave_backend.entity.Restaurant;
import com.crave.crave_backend.exception.EntityConflictException;
import com.crave.crave_backend.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	public Long registerRestaurant(RegisterRestaurantInDto registerRestaurantInDto, byte[] validatedImage) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(registerRestaurantInDto.getName());
		restaurant.setContactNumber(registerRestaurantInDto.getContactNumber());
		restaurant.setEmail(registerRestaurantInDto.getEmail());
		restaurant.setBuildingNumber(registerRestaurantInDto.getBuildingNumber());
		restaurant.setCityName(registerRestaurantInDto.getCityName());
		restaurant.setPinCode(registerRestaurantInDto.getPinCode());
		restaurant.setStateName(registerRestaurantInDto.getStateName());
		Long userId = Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		restaurant.setUserId(userId);
		restaurant.setImage(validatedImage);
		Long restaurantId;
		try {
			restaurantId = restaurantRepository.save(restaurant).getId();
		} catch (DataIntegrityViolationException ex) {
			String info = ex.getMostSpecificCause().toString().toLowerCase();
			String entity = restaurant.getClass().getSimpleName();
			List<String> messageList = new ArrayList<>();
			List<String> conflictingFieldsList = new ArrayList<String>();

			if (info.contains(DatabaseConstraintNames.UNIQUE_CONTACT_NUMBER)) {
				messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity,
						EntityConflictLogConstants.CONTACT_NUMBER, restaurant.getContactNumber()));
				conflictingFieldsList.add(EntityConflictLogConstants.CONTACT_NUMBER);
			} else if (info.contains(DatabaseConstraintNames.UNIQUE_EMAIL)) {
				messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity,
						EntityConflictLogConstants.EMAIL, restaurant.getEmail()));
				conflictingFieldsList.add(EntityConflictLogConstants.EMAIL);
			} else if (info.contains(DatabaseConstraintNames.UNIQUE_NAME)) {
				messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity,
						EntityConflictLogConstants.NAME, restaurant.getName()));
				conflictingFieldsList.add(EntityConflictLogConstants.NAME);
			} else {
				messageList.add(ErrorMessageConstants.DATA_INTEGRITY_VIOLATION);
			}
			throw new EntityConflictException(messageList, conflictingFieldsList, EntityConflictLogConstants.RESTAURANT);
		}
		return restaurantId;
	}
}
