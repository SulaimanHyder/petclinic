package com.app.pet.treatment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pet.treatment.models.VetAppointments;

public interface VetAppointmentRepo extends JpaRepository<VetAppointments, Long>{
	
	@Modifying
	@Query(value = "delete from appointments where visitId =:visitId", nativeQuery = true)
	public void deleteAppointmentForVet(String visitId);
}
