package com.app.pet.treatment.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pet.treatment.models.Vet;

@Repository
public interface VetRepo extends JpaRepository<Vet, Long>{
	
	@Query(value = "select * from Vet", nativeQuery = true)
	public List<Vet> getAvailalbeVets();
	
	@Query(value = "select * from Vet where id =:id", nativeQuery = true)
	public Vet getVetById(String id);
	
	@Modifying
	@Query(value = "update vet set appointments =:time where vetid =:id", nativeQuery = true)
	public void addAppointmentToVet(Long id, LocalDateTime time);
		
}
