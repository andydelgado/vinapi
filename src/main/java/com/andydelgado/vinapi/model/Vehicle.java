package com.andydelgado.vinapi.model;

public class Vehicle {
	private String make;
	private String model;
	private String year;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String make, String model, String year) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Vehicle [make=" + make + ", model=" + model + ", year=" + year + "]";
	}
	
}
