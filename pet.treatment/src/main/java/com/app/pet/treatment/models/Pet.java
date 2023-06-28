package com.app.pet.treatment.models;


import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pet")
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String petId;
	
	@Column(nullable = false)
	@Size(min = 3, message = "Name can't be less then 3 characters.")
	@NotEmpty(message = "Pet name can't be empty. Please enter pet's name.")
	private String name;
	
	@Column(nullable = false)
	@NotEmpty(message = "Pet age can't be empty. Please enter pet's age.")
	private String age;
	
	@Column(nullable = false)
	@NotEmpty(message = "Pet type can't be empty. Please enter the type of pet.")
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ownerid", nullable = false)
	private String ownerId;
	
	public Pet(String petId, String name, String age, String type) {
		super();
		this.petId = "pet" + String.valueOf(new Random().nextLong());
		this.name = name;
		this.age = age;
		this.type = type;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
