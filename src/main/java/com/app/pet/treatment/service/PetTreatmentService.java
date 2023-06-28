package com.app.pet.treatment.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pet.treatment.dto.Response;
import com.app.pet.treatment.dto.VisitInfo;
import com.app.pet.treatment.dto.VisitRequest;
import com.app.pet.treatment.exception.ServiceException;
import com.app.pet.treatment.models.Owner;
import com.app.pet.treatment.models.Pet;
import com.app.pet.treatment.models.Vet;
import com.app.pet.treatment.models.VetAppointments;
import com.app.pet.treatment.models.Visit;
import com.app.pet.treatment.repository.OwnerRepo;
import com.app.pet.treatment.repository.PetRepo;
import com.app.pet.treatment.repository.VetAppointmentRepo;
import com.app.pet.treatment.repository.VetRepo;
import com.app.pet.treatment.repository.VisitRepo;

@Service
public class PetTreatmentService {
	
	@Autowired
	private VetRepo vetRepo;
	@Autowired
	private VisitRepo visitRepo;
	@Autowired
	private VetAppointmentRepo vetAppntmntRepo;
	@Autowired
	private OwnerRepo ownerRepo;
	@Autowired
	private PetRepo petRepo;
	
	
	
	public VisitInfo getAllVisitsForPet(String petId) {
		
		CompletableFuture<List<Visit>> visit = CompletableFuture.supplyAsync(() -> {
			return visitRepo.getAllVisitsForPetId(petId);
		});
		
		CompletableFuture<Owner> owner = CompletableFuture.supplyAsync(() -> {
			return ownerRepo.getOwnerByPetId(petId);
		});
		
		return visit.thenCombine(owner, (v, o) -> {
			if(v.isEmpty()) {
				return new VisitInfo(o.getOwnerId(),o.getName(), v, "No Visits till date.");
			} else {
				return new VisitInfo(o.getOwnerId(),o.getName(), v, "Success.");
			}
		})
		.join();	
	}
	
	public Response<List<Vet>> getAvailableVets() {
		try
		{
			List<Vet> vets = vetRepo.getAvailalbeVets();
			
			if(vets.isEmpty()) {
				throw new ServiceException("No vets are available.");
			} 
			
			return new Response<List<Vet>>(vets, "Successful");
		} catch (Exception e) {
			throw new ServiceException("Error occured in the PetTreatmentService", e.getMessage());
		}
	}
	
	public Response<Visit> generateVisitDetails(VisitRequest request) {
		try
		{
			Vet vet = vetRepo.getVetById(request.getVetId());
			
			if(vet.getAppointments().contains(request.getAppointment())) {
				throw new ServiceException("Appointment can't be set. Please select another time.");
			}
			
			Visit visit = new Visit(request.getVetId(), request.getPetId(), request.getAppointment());
			visitRepo.save(visit);
			
			VetAppointments appntmnt = new VetAppointments(visit.getVetId(), visit.getVisitId(), visit.getAppointment());
			vetAppntmntRepo.save(appntmnt);
			
			return new Response<Visit> (visit, "Appointment scheduled.");
		} catch (Exception e) {
			throw new ServiceException("An error occured while creating appointment.", e.getMessage());
		}
	}
	
	@Transactional
	public Response<Visit> updateAppointmentTime(VisitRequest request) {
		try
		{
			Vet vet = vetRepo.getVetById(request.getVetId());
			
			if(vet.getAppointments().contains(request.getAppointment())) {
				throw new ServiceException("Appointment can't be set. Please select another time.");
			}
			
			VetAppointments appntment = new VetAppointments(request.getVetId(),
					request.getVisitId(), request.getAppointment());
			vetAppntmntRepo.save(appntment);
			
			Visit visit = visitRepo.findVisitById(request.getVisitId());
			visit.setAppointment(request.getAppointment());
			visitRepo.save(visit);
			
			return new Response<Visit> (visit, "Appointment has been re-scheduled.");
		} catch(Exception e) {
			throw new ServiceException("An error occured while updating appointment.", e.getMessage());
		}
	}
	
	@Transactional
	public String deleteVisit(String visitId) {
		try
		{	
			vetAppntmntRepo.deleteAppointmentForVet(visitId);
			
			visitRepo.deleleteVisitById(visitId);
			
			return "Appointment canceled successfully.";
		} catch (Exception e) {
			throw new ServiceException("An error occured while deleting appointment.", e.getMessage());
		}
	}

}
