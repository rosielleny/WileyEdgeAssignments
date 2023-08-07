package com.hero.dto.entity;

public class Supe {
	
	/*

			  CREATE TABLE Supe(
			  supeId INT PRIMARY KEY AUTO_INCREMENT,
			  supeName VARCHAR(100) NOT NULL,
			  supePower VARCHAR(100),
			  supeDescription VARCHAR(250));

	 */
	
	private int supeId;
	private String supeName;
	private String supePower;
	private String supeDescription;

	public Supe() {
	}

	public Supe(int supeId, String supeName, String supePower, String supeDescription) {
		super();
		this.supeId = supeId;
		this.supeName = supeName;
		this.supePower = supePower;
		this.supeDescription = supeDescription;
	}

	public int getSupeId() {
		return supeId;
	}

	public void setSupeId(int supeId) {
		this.supeId = supeId;
	}

	public String getSupeName() {
		return supeName;
	}

	public void setSupeName(String supeName) {
		this.supeName = supeName;
	}

	public String getSupePower() {
		return supePower;
	}

	public void setSupePower(String supePower) {
		this.supePower = supePower;
	}

	public String getSupeDescription() {
		return supeDescription;
	}

	public void setSupeDescription(String supeDescription) {
		this.supeDescription = supeDescription;
	}

}