package com.rbc.security.controller;

 
	import com.rbc.security.exception.ResourceNotFoundException;

	import com.rbc.security.model.Pet;
	import com.rbc.security.repository.PetRepository;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import javax.validation.Valid;
	import java.util.List;
	 
	  
	@CrossOrigin 
	@RestController
	@RequestMapping("/search")
	public class PetSearchController {
	    @Autowired
	    PetRepository petRepository;


	    
	    @GetMapping("/pets/{category_name}")
	    public List<Pet> getPetByCategory(@PathVariable(value = "category_name") String cat) {
	        return petRepository.findByCategory(cat);
	         
	    }
	    
	    
	      
	}
	 

