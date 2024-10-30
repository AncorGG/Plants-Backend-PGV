package com.ancor.plants.entity.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ancor.plants.entity.models.Plant;
import com.ancor.plants.repository.PlantRepository;
import com.ancor.plants.entity.dao.IPlantDao;

@Service
public class PlantServiceImpl implements IPlantService{

	@Autowired
	private IPlantDao plantDao;
	
	@Autowired
	private PlantRepository plantRepository;
	
	@Override
	public Plant get(long id) {
		return plantDao.findById(id).get();
	}

	@Override
	public void post(Plant plant) {
		plantDao.save(plant);
		
	}
	
	@Override
	public void put(Plant plant, long id) {
		plantDao.findById(id).ifPresent((x)->{
			plant.setIdplant(id);
			plantDao.save(plant);
		});
	}

	@Override
	public void delete(long id) {
		plantDao.deleteById(id);
	}
	
	public Page<Plant> findAll(Pageable pageable) {
        return plantRepository.findAll(pageable);
    }

	@Override
    public Page<Plant> getAll(Pageable pageable) {
        return plantRepository.findAll(pageable);
    }
	
    @Override
    public Page<Plant> getByNameContaining(String name, Pageable pageable) {
        return plantRepository.findByPlantNameStartingWith(name, pageable);
    }

    @Override
    public Page<Plant> getByAge(int age, Pageable pageable) {
        return plantRepository.findByPlantAge(age, pageable);
    }

    @Override
    public Page<Plant> getByNameAndAge(String name, int age, Pageable pageable) {
        return plantRepository.findByPlantNameContainingAndPlantAge(name, age, pageable);
    }

}
