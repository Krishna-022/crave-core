package com.crave.crave_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import com.crave.crave_backend.constant.DatabaseConstraintNames;
import com.crave.crave_backend.constant.ErrorMessageConstants;
import com.crave.crave_backend.constant.SuccessMessageConstants;
import com.crave.crave_backend.dto.in.RegisterUserInDto;
import com.crave.crave_backend.dto.out.MessageOutDto;
import com.crave.crave_backend.entity.User;
import com.crave.crave_backend.exception.ContactNumberAlreadyExistsException;
import com.crave.crave_backend.exception.EmailAlreadyExistsException;
import com.crave.crave_backend.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TransactionTemplate transactionTemplate; 
	
	public MessageOutDto registerUser(RegisterUserInDto registerUserInDto) {
		User user = new User();
		user.setContactNumber(registerUserInDto.getContactNumber());
		user.setEmail(registerUserInDto.getEmail());
		user.setFirstName(registerUserInDto.getFirstName());
		user.setLastName(registerUserInDto.getLastName());
		user.setMiddleName(registerUserInDto.getMiddleName());
		user.setPasswordHash(passwordEncoder.encode(registerUserInDto.getPassword()));
		
		transactionTemplate.executeWithoutResult(status -> {
			try {
				userRepository.save(user);
			}
			catch (DataIntegrityViolationException ex){
				String info = ex.getMostSpecificCause().toString().toLowerCase();
				String entity = user.getClass().getSimpleName();
				
				if (info.contains(DatabaseConstraintNames.UNIQUE_CONTACT_NUMBER)) {
					String message = String.format(ErrorMessageConstants.CONTACT_NUMBER_ALREADY_EXISTS, entity, user.getContactNumber());
					throw new ContactNumberAlreadyExistsException(message);
				} else if (info.contains(DatabaseConstraintNames.UNIQUE_EMAIL)) {
					String message = String.format(ErrorMessageConstants.EMAIL_ALREADY_EXIST, entity, user.getEmail());
					throw new EmailAlreadyExistsException(message);
				} else {
					 throw new DataIntegrityViolationException(ErrorMessageConstants.DATA_INTEGRITY_VIOLATION);
				}
			}
		});
		
		return new MessageOutDto(SuccessMessageConstants.REGISTRATION_SUCCESSFUL);
	}
}
