package com.andydelgado.vinapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andydelgado.vinapi.dao.IVehicleDAO;
import com.andydelgado.vinapi.model.Vehicle;

@RestController
public class VinapiController {
	
	private static final Logger logger = LoggerFactory.getLogger(VinapiController.class);
	
	@Autowired
	private IVehicleDAO vehicleDAO;
	
	@GetMapping("/vinDetails/{id}")
	public ResponseEntity<Vehicle> getVehicleByVin(@PathVariable String id) {
		logger.info("Requesting /vin/" + id);
		return vehicleDAO.getByVin(id);
	}
	
	@PostMapping("/vinDetails/{id}")
	public ResponseEntity<Vehicle> addVehicleByVin(@PathVariable String id, @RequestBody Vehicle vehicle) {
		return vehicleDAO.addVehicle(id, vehicle);
	}
}
