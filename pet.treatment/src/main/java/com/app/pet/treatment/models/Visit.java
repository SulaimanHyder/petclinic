package com.app.pet.treatment.models;

import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "visits")
public class Visit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false, unique = true)
	private String visitId;
	@Column(nullable = false)
	private String vetId;
	@Column(nullable = false)
	private String petId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime appointment;
	
	private String issueDescription;
	private String treatment;
	private String doctorNotes;
	
	public Visit(String vetId, String petId, LocalDateTime appointment) {
		super();
		this.visitId = String.valueOf(new Random().nextLong());
		this.vetId = vetId;
		this.petId = petId;
		this.appointment = appointment;
		this.appointment = appointment;
	}
	
	public Visit() {
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
	public void setAppointment(LocalDateTime appointment) {
		this.appointment = appointment;
	}
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getDoctorNotes() {
		return doctorNotes;
	}
	public void setDoctorNotes(String doctorNotes) {
		this.doctorNotes = doctorNotes;
	}
}
