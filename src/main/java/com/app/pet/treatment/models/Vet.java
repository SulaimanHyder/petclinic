package com.app.pet.treatment.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.app.pet.treatment.enums.Specialty;

@Entity
@Table(name = "vet")
public class Vet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String vetId;
	@Column(nullable = false)
	private List<LocalDateTime> availableOnDates;
	@Column(nullable = false)
	private Set<Specialty> specialties;
	
	@Temporal(TemporalType.TIMESTAMP)
	@OneToMany(mappedBy = "vet")
	private List<LocalDateTime> appointments;
	
	public Vet(Long id, String name, List<LocalDateTime> availableOnDates,
			Set<Specialty> specialties) {
		super();
		this.id = id;
		this.name = name;
		this.availableOnDates = availableOnDates;
		this.specialties = specialties;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LocalDateTime> getAvailableOnDates() {
		return availableOnDates;
	}
	public void setAvailableOnDates(List<LocalDateTime> availableOnDates) {
		this.availableOnDates = availableOnDates;
	}
	public Set<Specialty> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(Set<Specialty> specialties) {
		this.specialties = specialties;
	}

	public List<LocalDateTime> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<LocalDateTime> appointments) {
		this.appointments = appointments;
	}
	
}
