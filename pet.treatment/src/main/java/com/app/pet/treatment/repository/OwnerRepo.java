package com.app.pet.treatment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pet.treatment.models.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long>{
	
	@Query(value = "from Owner where petId =:petId")
	public Owner getOwnerByPetId(String petId);
	
	@Query(value = "select email from Owner where petId =:petId")
	public String getOwnerEmailByPetId(String petId);

}
