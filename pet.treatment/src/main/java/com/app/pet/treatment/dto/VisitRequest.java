package com.app.pet.treatment.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;


public class VisitRequest {
	
	@NotEmpty(message = "Visit Id can't be empty.")
	private String visitId;
	@NotEmpty(message = "Vet Id can't be empty.")
	private String vetId;
	@NotEmpty(message = "Pet Id can't be empty.")
	private String petId;
	@NotEmpty(message = "Appointment time can't be empty.")
	private LocalDateTime appointment;
	
	public VisitRequest(String visitId, String vetId, String petId, LocalDateTime appointment) {
		super();
		this.visitId = visitId;
		this.vetId = vetId;
		this.petId = petId;
		this.appointment = appointment;
	}
	
	public VisitRequest(String vetId, String petId, LocalDateTime appointment) {
		super();
		this.vetId = vetId;
		this.petId = petId;
		this.appointment = appointment;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getVetId() {
		return vetId;
	}

	public void setVetId(String vetId) {
		this.vetId = vetId;
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	public LocalDateTime getAppointment() {
		return appointment;
	}

	public void setAppointment(LocalDateTime appointmentDate) {
		this.appointment = appointmentDate;
	}
}
