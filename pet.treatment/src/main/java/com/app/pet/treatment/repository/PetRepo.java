package com.app.pet.treatment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pet.treatment.models.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long>{
	
	@Query(value = "from Pet")
	public List<Pet> getAllPets();

}
