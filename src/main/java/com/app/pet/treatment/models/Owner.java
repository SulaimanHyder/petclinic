package com.app.pet.treatment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "owner")
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ownerId;
	
	@Column(nullable = false)
	@Size(min = 3, message = "Name can't be less then 3 characters.")
	@NotEmpty(message = "Owner name can't be empty. Please enter your name.")
	private String name;
	
	@Column(nullable = false)
	@Email(message = "Please enter email in the correct format.")
	private String email;
	
	@OneToMany(mappedBy = "owner")
	private String petId;
}
