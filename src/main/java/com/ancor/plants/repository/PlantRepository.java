package com.ancor.plants.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ancor.plants.entity.models.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long>{
	
    Page<Plant> findByPlantNameStartingWith(String plantName, Pageable pageable);

    Page<Plant> findByPlantAge(int plantAge, Pageable pageable);

    Page<Plant> findByPlantNameContainingAndPlantAge(String plantName, int plantAge, Pageable pageable);
}
