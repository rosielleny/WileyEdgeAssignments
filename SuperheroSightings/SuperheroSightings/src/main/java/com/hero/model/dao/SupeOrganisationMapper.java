package com.hero.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hero.dto.entity.SupeOrganisation;

public class SupeOrganisationMapper implements RowMapper<SupeOrganisation> {

    @Override
    public SupeOrganisation mapRow(ResultSet rs, int rowNum) throws SQLException {
        SupeOrganisation supeOrganisation = new SupeOrganisation();
        supeOrganisation.setSupeId(rs.getInt("supeId"));
        supeOrganisation.setOrganisationId(rs.getInt("organisationId"));
        return supeOrganisation;
    }
}

