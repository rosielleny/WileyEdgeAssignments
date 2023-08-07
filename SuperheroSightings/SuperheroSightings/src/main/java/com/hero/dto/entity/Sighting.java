package com.hero.dto.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Sighting {
	
	/*
		CREATE TABLE Sighting(
		sightingId INT PRIMARY KEY AUTO_INCREMENT,
		dateSeen VARCHAR(30),
		supeId INT,
		locationId INT,
		CONSTRAINT FOREIGN KEY (supeId) REFERENCES Supe(supe),
		CONSTRAINT FOREIGN KEY (locationId) REFERENCES Location(locationId));

	 */
	
	private int sightingId;
	@NotNull(message = "Cannot be blank.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateSeen;
	@NotNull(message = "Cannot be blank.")
	private int supeId;
	@NotNull(message = "Cannot be blank.")
	private int locationId;
	
	public Sighting() {
	}

	public Sighting(int sightingId, Date dateSeen, int supeId, int locationId) {
		super();
		this.sightingId = sightingId;
		this.dateSeen = dateSeen;
		this.supeId = supeId;
		this.locationId = locationId;
	}

	public int getSightingId() {
		return sightingId;
	}

	public void setSightingId(int sightingId) {
		this.sightingId = sightingId;
	}

	public Date getDateSeen() {
		return dateSeen;
	}

	public void setDateSeen(Date dateSeen) {
		this.dateSeen = dateSeen;
	}

	public int getSupeId() {
		return supeId;
	}

	public void setSupeId(int supeId) {
		this.supeId = supeId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
}
