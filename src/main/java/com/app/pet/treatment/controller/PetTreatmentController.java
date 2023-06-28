package com.app.pet.treatment.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.pet.treatment.dto.Response;
import com.app.pet.treatment.dto.VisitRequest;
import com.app.pet.treatment.exception.ControllerException;
import com.app.pet.treatment.exception.ServiceException;
import com.app.pet.treatment.models.Vet;
import com.app.pet.treatment.models.Visit;
import com.app.pet.treatment.service.PetTreatmentService;

@Controller
@RequestMapping("/app/treatment")
public class PetTreatmentController {
	
	@Autowired
	private PetTreatmentService petTrtmntSer;
	
	@GetMapping("/{petID}")
	public ResponseEntity<?> getAllVisitsForPets(@PathVariable String petId) {
		
		try
		{
			return new ResponseEntity<>(petTrtmntSer.getAllVisitsForPet(petId), HttpStatus.OK);
			
		} catch(ServiceException s) {
			return new ResponseEntity<>(s, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			ControllerException ce = new ControllerException("Error in controller", e.getMessage());
			return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> getAvailableVetDetails() {
		try
		{
			Response<List<Vet>> response = petTrtmntSer.getAvailableVets(); 
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch(ServiceException s) {
			return new ResponseEntity<>(s, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			ControllerException ce = new ControllerException("Error in controller", e.getMessage());
			return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> createVisitDetails(@Valid @RequestBody VisitRequest request) {
		try
		{
			Response<Visit> response = petTrtmntSer.generateVisitDetails(request);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
			
		} catch(ServiceException s) {
			return new ResponseEntity<>(s, HttpStatus.NOT_IMPLEMENTED);
		} catch(Exception e) {
			ControllerException ce = new ControllerException("Error in controller", e.getMessage());
			return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping()
	public ResponseEntity<?> updateVisitInfo(@RequestBody VisitRequest request) {
		try
		{
			Response<Visit> response = petTrtmntSer.updateAppointmentTime(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch(ServiceException s) {
			return new ResponseEntity<>(s, HttpStatus.NOT_MODIFIED);
		} catch(Exception e) {
			ControllerException ce = new ControllerException("Error in controller", e.getMessage());
			return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{visitId}")
	public ResponseEntity<?> cancelVisit(@PathVariable String visitId) {
		try
		{
			String status = petTrtmntSer.deleteVisit(visitId);
			return new ResponseEntity<>(status, HttpStatus.OK);
			
		} catch(ServiceException s) {
			return new ResponseEntity<>(s, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			ControllerException ce = new ControllerException("Error in controller", e.getMessage());
			return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
