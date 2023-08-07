package com.hero.dto.entity;

import javax.validation.constraints.NotBlank;

public class Location {
	
	/*
	 * CREATE TABLE Location(
locationId INT PRIMARY KEY AUTO_INCREMENT,
locationName VARCHAR(30),
locationDescription VARCHAR(100),
locationAddress VARCHAR(30),
locationCoordinates VARCHAR(30));
	 */
	
	private int locationId;
	private String locationName;
	@NotBlank(message = "Name cannot be left blank")
	private String locationDescription;
	private String locationAddress;
	private String locationCoordinates;
	
	public Location() {
	}
	
	public Location(int locationId, String locationName, String locationDescription, String locationAddress,
			String locationCoordinates) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationDescription = locationDescription;
		this.locationAddress = locationAddress;
		this.locationCoordinates = locationCoordinates;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationDescription() {
		return locationDescription;
	}
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public String getLocationCoordinates() {
		return locationCoordinates;
	}
	public void setLocationCoordinates(String locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
	}
}
