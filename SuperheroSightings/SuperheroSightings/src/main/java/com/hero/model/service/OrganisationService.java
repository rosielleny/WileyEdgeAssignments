package com.hero.model.service;

import java.util.List;

import com.hero.dto.entity.Organisation;
import com.hero.dto.entity.SupeOrganisation;

public interface OrganisationService {

	List<SupeOrganisation> getOrganisationMembers(int organisationId);
    List<SupeOrganisation> getOrganisationBySupeId(int supeId);
    Organisation getOrganisationById(int organisationId);
    List<Organisation> getAllOrganisations();
    public Organisation addOrganisation(Organisation organisationId);
	public boolean updateOrganisation(int organisationId, String organisationName, String orgDescription, String orgAddress, String orgContactInfo);
    public Organisation deleteOrganisation(int organisationId);

}

