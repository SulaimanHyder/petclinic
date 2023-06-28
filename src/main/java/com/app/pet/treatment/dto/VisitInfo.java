package com.app.pet.treatment.dto;

import java.util.List;

import com.app.pet.treatment.models.Visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitInfo {
	
	private String ownerId;
	
	private String ownerName;
	
	private List<Visit> allVisits;
	
	private String statusMessage;
}
