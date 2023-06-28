package com.app.pet.treatment.service;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pet.treatment.exception.ServiceException;
import com.app.pet.treatment.models.Owner;
import com.app.pet.treatment.repository.OwnerRepo;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerRepo ownerRepo;
	
	Function<String, Boolean> checkEmailValid = (email) -> {
		Pattern p = Pattern.compile("^[A-Z a-z]*[1-9]*[.\\-\\]*$.\\petclinin.\\com");
		Matcher m = p.matcher(email);
		
		return m.find() ? true : false;
	};
	
	
	public String storeOwnerDetails(Owner owner) {
		
		if(!checkEmailValid.apply(owner.getEmail())) {
			throw new ServiceException("Invalid Email. Please choose an email as per policy.");
		}
		
		ownerRepo.save(owner);
		
		return "Success";
	}
	
	

}
