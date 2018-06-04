package com.rbc.security.service;

import java.util.List;

import com.rbc.security.model.Pet;

public interface  PetService {

	public List getAllPets();
	public Pet  getPet(Long id);
	public Pet addPet(Pet pet);
	public Pet updatePet(Pet pet);
	public void deletePet(Pet pet);
	
}
