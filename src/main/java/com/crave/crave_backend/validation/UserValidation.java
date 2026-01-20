package com.crave.crave_backend.validation;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crave.crave_backend.constant.ErrorMessageConstants;
import com.crave.crave_backend.entity.User;
import com.crave.crave_backend.exception.ContactNumberAlreadyExistsException;
import com.crave.crave_backend.exception.EmailAlreadyExistsException;
import com.crave.crave_backend.repository.UserRepository;

@Component
public class UserValidation {
	
	@Autowired
	private UserRepository userRepository;
	
	public void validateUserContactNumber(String contactNumber) {
		Optional<User> userOptional = userRepository.findByContactNumber(contactNumber);
		
		if (!userOptional.isEmpty()) {
			String entity = userOptional.get().getClass().getSimpleName();
			String message = String.format(ErrorMessageConstants.CONTACT_NUMBER_ALREADY_EXISTS, entity, contactNumber);
			throw new ContactNumberAlreadyExistsException(message);
		}
	}
	
	public void validateUserEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if (!userOptional.isEmpty()) {
			String entity = userOptional.get().getClass().getSimpleName();
			String message = String.format(ErrorMessageConstants.EMAIL_ALREADY_EXIST, entity, email);
			throw new EmailAlreadyExistsException(message);
		}
	}
}
