package com.app.pet.treatment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.pet.treatment.exception.ControllerException;
import com.app.pet.treatment.exception.ServiceException;
import com.app.pet.treatment.models.Owner;
import com.app.pet.treatment.service.OwnerService;

@Controller
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@PostMapping
	public ResponseEntity<?> storeOwnerDetails(Owner owner) {
		try
		{
			String status = ownerService.storeOwnerDetails(owner);
			return new ResponseEntity<>(status, HttpStatus.CREATED);
			
		} catch(ServiceException s) {
			return new ResponseEntity<>(s, HttpStatus.NOT_IMPLEMENTED);
		} catch(Exception e) {
			ControllerException ce = new ControllerException("Error in controller", e.getMessage());
			return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
