package com.rbc.security.service;

import java.util.ArrayList;
import java.util.List; 

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.security.repository.PetRepository;
 
import com.rbc.security.model.Pet;

@Service
@Transactional
public class PetServiceImpl implements  PetService {

     
    private PetRepository petRepository;
    
	@Autowired
	public PetServiceImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	// Retrieve all rows from table and populate list with objects
	public List getAllPets() {
		
		List pets = new ArrayList<>();
		petRepository.findAll().forEach(pets::add);
		
		return pets;
	}
	
	// Retrieves one row from table based on given id
	public  Pet  getPet(Long id) {
		return petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
				 "The requested  Id [" + id +
				 "] does not exist."));
	
	}
	
	// Inserts row into table 
	public Pet addPet(Pet pet) {
		return petRepository.save(pet);
	}
	
	// Updates row in table
	public Pet updatePet(Pet pet) {
		return petRepository.save(pet);
	}
	
	// Removes row from table
	public void deletePet(Pet pet) {
		petRepository.delete(pet);
	}
}