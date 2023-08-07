package com.hero.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organisation {

	private int organisationId;
	private String organisationName;
	private String organisationDescription;
	private String organisationAddress;
	private String organisationContactInfo;
  
}