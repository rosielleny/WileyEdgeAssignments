package com.hero.model.dao;

import java.util.List;

import com.hero.dto.entity.Organisation;
import com.hero.dto.entity.SupeOrganisation;

public interface OrganisationDao {
	
	List<SupeOrganisation> getOrganisationMembers(int organisationId);
    List<SupeOrganisation> getOrganisationBySupeId(int supeId);
    Organisation getOrganisationById(int organisationId);
	Organisation addOrganisation(Organisation organisation);
	int updateOrganisation(int organisationId, String organisationName, String organisationDescription, String organisationAddress, String organisationContactInfo);
    int deleteOrganisation(int organisationId);
    List<Organisation> getAllOrganisations();

}
