package com.andydelgado.vinapi.dao;

import org.springframework.http.ResponseEntity;

import com.andydelgado.vinapi.model.Vehicle;

public interface IVehicleDAO {
	public ResponseEntity<Vehicle> getByVin(String vin);
	public ResponseEntity<Vehicle> addVehicle(String vin, Vehicle vehicle);
}
