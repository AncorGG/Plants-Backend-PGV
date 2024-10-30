package com.ancor.plants.entity.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="plant")
public class Plant implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idplant;
	
	@Column(name = "plant_name")
	private String plantName;
	
	@Column(name = "plant_age")
	private int plantAge;

	public long getIdplant() {
		return idplant;
	}

	public void setIdplant(long idplant) {
		this.idplant = idplant;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public int getPlantAge() {
		return plantAge;
	}

	public void setPlantAge(int plantAge) {
		this.plantAge = plantAge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Plant(long idplant, String plantName, int plantAge) {
		super();
		this.idplant = idplant;
		this.plantName = plantName;
		this.plantAge = plantAge;
	}

	public Plant() {

	}
	
	
}
