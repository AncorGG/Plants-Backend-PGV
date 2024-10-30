package com.ancor.plants.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ancor.exceptions.PlantNotFoundException;
import com.ancor.plants.entity.models.Plant;
import com.ancor.plants.entity.services.IPlantService;

@RestController
@CrossOrigin(origins = "*")
public class PlantController {

	@Autowired
	IPlantService plantService;
	
	@GetMapping("/plants")
    public ResponseEntity<?> getAllPlants(
    		@RequestParam(required = false) String plantName,
    		@RequestParam(required = false) Integer plantAge,
    		Pageable pageable) {
		
		Page<Plant> plantPage;
		
		if(plantName != null && plantAge != null) {
			 plantPage = plantService.getByNameAndAge(plantName, plantAge, pageable);
		} else if (plantName != null) {
			 plantPage = plantService.getByNameContaining(plantName, pageable);
		} else if (plantAge != null) {
			 plantPage = plantService.getByAge(plantAge, pageable);
		}else {			
			 plantPage = plantService.getAll(pageable);
		}
		
		if(plantPage.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No plants found with the specified criteria.");
		}
		
		return ResponseEntity.ok(plantPage);
		
    }	
	
	@GetMapping("/plants/{id}")
	public ResponseEntity<?> getOne(@PathVariable(value = "id") long id)	{
		Plant plant = plantService.get(id);
	    if (plant == null) {
	        throw new PlantNotFoundException("Plant not found with id: " + id);
	    }
	    return ResponseEntity.ok(plant);
	}
	
	@PostMapping("/plants")
	public ResponseEntity<Void> post(@RequestBody(required = false) Plant plant) {
	    if (plant == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant must not be empty.");
	    }

	    if (plant.getPlantName() == null || plant.getPlantName().trim().isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant name must not be empty.");
	    }

	    // Check for null age and validate it
	    if (Integer.toString(plant.getPlantAge()) == null || plant.getPlantAge() <= 0) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant age must be a positive integer.");
	    }

	    plantService.post(plant);
	    
	    // Return a 201 Created status if successful
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	}


	
	@PutMapping("/plants/{id}")
	public void put(@RequestBody Plant plant, @PathVariable(value = "id") long id) {

		if (plantService.get(id) == null) {
	        throw new PlantNotFoundException("Plant not found with id: " + id);
	    }

	    if (plant == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant must not be empty.");
	    }

	    if (plant.getPlantName() == null || plant.getPlantName().trim().isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant name must not be empty.");
	    }

	    if (plant.getPlantAge() <= 0) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plant age must be a positive integer.");
	    }

	    plantService.put(plant, id);
	}

	
	@DeleteMapping("/plants/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable(value = "id") long id) {
	    if (plantService.get(id) == null) {
	        throw new PlantNotFoundException("Plant not found with id: " + id);
	    }

	    plantService.delete(id);
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Plant deleted successfully.");
	    return ResponseEntity.ok(response);
	}

}
