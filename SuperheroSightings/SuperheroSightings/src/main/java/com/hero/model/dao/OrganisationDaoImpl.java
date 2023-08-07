package com.hero.model.dao;

import java.util.List;

import com.hero.dto.entity.Organisation;
import com.hero.dto.entity.SupeOrganisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrganisationDaoImpl implements OrganisationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SupeOrganisation> getOrganisationMembers(int organisationId) {
		try {
			return jdbcTemplate.query("SELECT * FROM SupeOrganisation WHERE organisationId=?", new SupeOrganisationMapper(), organisationId);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<SupeOrganisation> getOrganisationBySupeId (int supeId) {
		try {
			return jdbcTemplate.query("SELECT * FROM SupeOrganisation WHERE supeId=?", new SupeOrganisationMapper(), supeId);
		} catch (Exception ex) {
			return null;
		}
	}
	
	@Override
	public Organisation getOrganisationById(int organisationId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM Organisation WHERE organisationId=?", new OrganisationMapper(), organisationId);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public List<Organisation> getAllOrganisations() {
		return jdbcTemplate.query("SELECT * FROM Organisation", new OrganisationMapper());
	}
	
	@Override
	public Organisation addOrganisation(Organisation organisation) {
		try {
			jdbcTemplate.update ("INSERT INTO Organisation VALUES(?,?,?,?,?)",
					organisation.getOrganisationId(),
					organisation.getOrganisationName(),
					organisation.getOrganisationDescription(),
					organisation.getOrganisationAddress(),
					organisation.getOrganisationContactInfo());
			return organisation;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public int updateOrganisation(int organisationId, String organisationName, String organisationDescription, String organisationAddress, String organisationContactInfo) {
	    try {
	        return jdbcTemplate.update("UPDATE Organisation SET organisationName=?, organisatioonDescription=?, organisationAddress=?, organisationContactInfo=? WHERE organisationId=?", organisationName, organisationDescription, organisationAddress, organisationContactInfo, organisationId);
	    } catch (Exception ex) {
	        return 0;
	    }
	}
	
	@Override
	public int deleteOrganisation(int organisationId) {
		try {
			return jdbcTemplate.update("DELETE FROM Organisation WHERE organisationId=?", organisationId);
		} catch (Exception ex) {
			return 0;
		}
	}

}
