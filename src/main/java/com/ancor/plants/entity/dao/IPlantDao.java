package com.ancor.plants.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.ancor.plants.entity.models.Plant;

public interface IPlantDao extends CrudRepository<Plant, Long>{

}
