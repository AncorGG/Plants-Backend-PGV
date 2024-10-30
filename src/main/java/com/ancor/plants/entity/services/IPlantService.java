package com.ancor.plants.entity.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ancor.plants.entity.models.Plant;

public interface IPlantService {
	public Plant get(long id);
	public Page<Plant> getAll(Pageable pageable);
	public void post(Plant plant);
	public void put(Plant plant, long id);
	public void delete(long id);
	public Page<Plant> getByNameContaining(String name, Pageable pageable);
    public Page<Plant> getByAge(int age,  Pageable pageable);
    public Page<Plant> getByNameAndAge(String name, int age, Pageable pageable);
}
