package com.rbc.security.controller;


 
import com.rbc.security.exception.ResourceNotFoundException;

import com.rbc.security.model.Pet;
import com.rbc.security.service.PetService;
import com.rbc.security.service.PetServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List; 
 
  
@CrossOrigin 
@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    PetServiceImpl petService;
     

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @PostMapping("/pets")
    public Pet createPet(@Valid @RequestBody Pet pet) {
        return petService.addPet(pet);
    }

    @GetMapping("/pets/{id}")
    public  Pet getPetById(@PathVariable(value = "id") Long petId) {
        return petService.getPet(petId); 
    }

   
    @PutMapping("/pets/{id}")
    public Pet updatePet(@PathVariable(value = "id") Long petId,
                                           @Valid @RequestBody Pet petDetails) {

    	Pet pet = petService.getPet(petId);
 
    	pet.setName(petDetails.getName());
    	pet.setStatus(petDetails.getStatus());
    	pet.setPhotoUrl(petDetails.getPhotoUrl());
    	pet.setCategoryName(petDetails.getCategoryName());

        Pet updatedPet = petService.updatePet(pet);
        return updatedPet;
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<?> deletePet(@PathVariable(value = "id") Long petId) {
    	Pet pet = petService.getPet(petId);  
    	petService.deletePet(pet);

        return ResponseEntity.ok().build();
    }
}
 
