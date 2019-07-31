package com.andydelgado.vinapi.dao;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.andydelgado.vinapi.model.Vehicle;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class VehicleDAO implements IVehicleDAO {

	private static final Logger logger = LoggerFactory.getLogger(VehicleDAO.class);
	
	@Override
	@Cacheable("vins")
	public ResponseEntity<Vehicle> getByVin(String vin) {
		Vehicle vehicle = new Vehicle();
		ResponseEntity<Vehicle> response = null;
		
		// REST api set up
		RestTemplate restTemplate = new RestTemplate();
		
		
		String vinResourceUrl = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVin/" + vin + "?format=json";
		
		ObjectMapper mapper = new ObjectMapper();
		
		// calling api
		logger.info("Calling NHTSA API URL: " + vinResourceUrl);
		ResponseEntity<String> apiResponse = restTemplate.getForEntity(vinResourceUrl, String.class);
		
		try {
			// parsing results
			JsonNode root = mapper.readTree(apiResponse.getBody());
			JsonNode results = root.path("Results");

			// check for error code
			String errorCode = "";
			for (JsonNode node: results) {
				if (node.path("Variable").asText().equals("Error Code")) {
					errorCode = node.path("Value").asText();
				}
			}
			
			if (errorCode.equals("0")) {
			// iterate through results array to get data
				for (JsonNode node : results) {
					if (node.path("Variable").asText().equals("Make")) {
						vehicle.setMake(node.path("Value").asText());
					}
					if (node.path("Variable").asText().equals("Model")) {
						vehicle.setModel(node.path("Value").asText());
					}
					if (node.path("Variable").asText().equals("Model Year")) {
						vehicle.setYear(node.path("Value").asText());
					}
						
					response = new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
				}
			} else {
				response = new ResponseEntity<Vehicle>(vehicle, HttpStatus.NOT_FOUND);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(response.getStatusCode().toString());
		logger.info(response.getBody().toString());
		return response;
	}

	@Override
	@CachePut(value="vins", key="#vin")
	public ResponseEntity<Vehicle> addVehicle(String vin, Vehicle vehicle) {
		logger.info("Adding vehicle. Vin: " + vin + " Vehicle: " + vehicle.toString());
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
	}

}
