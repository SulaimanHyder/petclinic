package com.app.pet.treatment.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pet.treatment.models.Visit;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {
	
	public Visit findVisitById(String id);
	
	@Query(value = "from Visit where petId =:petId")
	public List<Visit> getAllVisitsForPetId(String petId);
	
	@Modifying
	@Query(value = "update Visit set appointment =:appointment where visitId =:visitId")
	public void updateVisitTimeById(LocalDateTime appointment, String visitId);
	
	@Modifying
	@Query(value = "delete from Visit where visitId =:visitId")
	public void deleleteVisitById(String visitId);
}
