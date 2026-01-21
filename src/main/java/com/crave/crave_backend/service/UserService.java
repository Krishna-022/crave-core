package com.crave.crave_backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crave.crave_backend.constant.DatabaseConstraintNames;
import com.crave.crave_backend.constant.ErrorMessageConstants;
import com.crave.crave_backend.constant.SuccessMessageConstants;
import com.crave.crave_backend.dto.in.RegisterUserInDto;
import com.crave.crave_backend.dto.out.MessageOutDto;
import com.crave.crave_backend.entity.User;
import com.crave.crave_backend.exception.EntityConflictException;
import com.crave.crave_backend.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public MessageOutDto registerUser(RegisterUserInDto registerUserInDto) {
		User user = new User();
		user.setContactNumber(registerUserInDto.getContactNumber());
		user.setEmail(registerUserInDto.getEmail());
		user.setFirstName(registerUserInDto.getFirstName());
		user.setLastName(registerUserInDto.getLastName());
		user.setMiddleName(registerUserInDto.getMiddleName());
		user.setPasswordHash(passwordEncoder.encode(registerUserInDto.getPassword()));
		
		try {
			userRepository.save(user);
		}
		catch (DataIntegrityViolationException ex){
			String info = ex.getMostSpecificCause().toString().toLowerCase();
			String entity = user.getClass().getSimpleName();
			List<String> messageList = new ArrayList<>();
			
			if (info.contains(DatabaseConstraintNames.UNIQUE_CONTACT_NUMBER)) {
				messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity, user.getContactNumber()));
			} else if (info.contains(DatabaseConstraintNames.UNIQUE_EMAIL)) {
				messageList.add(String.format(ErrorMessageConstants.ENTITY_CONFLICT, entity, user.getEmail()));
			} else {
				messageList.add(ErrorMessageConstants.DATA_INTEGRITY_VIOLATION);
			}
			throw new EntityConflictException(messageList);
		}
		return new MessageOutDto(SuccessMessageConstants.REGISTRATION_SUCCESSFUL);
	}
}
