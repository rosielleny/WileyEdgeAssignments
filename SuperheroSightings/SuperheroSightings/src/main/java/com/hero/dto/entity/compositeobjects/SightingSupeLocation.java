package com.hero.dto.entity.compositeobjects;
// An object to bring together data from the Sighting, Location, and Supe tables
public class SightingSupeLocation {
	
	private int sightingId;
	private String supeName;
	private String locationName;
	private String locationCoordinates;
	private String dateSeen;
	
	public SightingSupeLocation(int sightingId, String supeName, String locationName, String locationCoordinates, String dateSeen) {
		super();
		this.sightingId = sightingId;
		this.supeName = supeName;
		this.locationName = locationName;
		this.locationCoordinates = locationCoordinates;
		this.dateSeen = dateSeen;
	}
	
	public SightingSupeLocation() {
		
	}
	
	public int getSightingId() {
		return sightingId;
	}
	public void setSightingId(int sightingId) {
		this.sightingId = sightingId;
	}

	public String getSupeName() {
		return supeName;
	}

	public void setSupeName(String supeName) {
		this.supeName = supeName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCoordinates() {
		return locationCoordinates;
	}

	public void setLocationCoordinates(String locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
	}

	public String getDateSeen() {
		return dateSeen;
	}

	public void setDateSeen(String dateSeen) {
		this.dateSeen = dateSeen;
	}

	
	
}
