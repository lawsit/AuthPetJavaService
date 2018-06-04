package com.rbc.security.repository;

import com.rbc.security.model.Pet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	public List<Pet> findByCategory(String category_name);
	
	
}