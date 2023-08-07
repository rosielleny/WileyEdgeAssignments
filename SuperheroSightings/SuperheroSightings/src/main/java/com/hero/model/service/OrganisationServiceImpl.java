package com.hero.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hero.dto.entity.Organisation;
import com.hero.dto.entity.SupeOrganisation;
import com.hero.model.dao.OrganisationDao;

@Service
public class OrganisationServiceImpl implements OrganisationService {

	@Autowired
	OrganisationDao organisationDao;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SupeOrganisation> getOrganisationMembers(int organisationId) {
		return organisationDao.getOrganisationMembers(organisationId);
	}

	@Override
	public List<SupeOrganisation> getOrganisationBySupeId(int supeId) {
		return organisationDao.getOrganisationBySupeId(supeId);
	}
	
	@Override
	public Organisation getOrganisationById(int organisationId) {
		return organisationDao.getOrganisationById(organisationId);
	}
	
	@Override
	public List<Organisation> getAllOrganisations() {
		return organisationDao.getAllOrganisations();
	}
	
	@Override
	public Organisation addOrganisation(Organisation organisation) {
		return organisationDao.addOrganisation(organisation);
	}

	@Override
	public boolean updateOrganisation(int organisationId, String organisationName, String organisationDescription, String organisationAddress, String organisationContactInfo) {
	    try {
	        int update = jdbcTemplate.update(
	            "UPDATE Organisation SET organisationName=?, organisationDescription=?, organisationAddress=?, organisationContactInfo=? WHERE organisationId=?",
	            organisationName, organisationDescription, organisationAddress, organisationContactInfo, organisationId
	        );
	        return update > 0;
	    } catch (Exception ex) {
	        return false;
	    }
	}

	@Override
	public Organisation deleteOrganisation(int organisationId) {
		Organisation org = getOrganisationById(organisationId);
		
		if(org != null)
			organisationDao.deleteOrganisation(organisationId);
		
		return org;
	}

}