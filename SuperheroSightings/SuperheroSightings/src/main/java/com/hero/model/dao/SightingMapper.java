package com.hero.model.dao;

import org.springframework.jdbc.core.RowMapper;

import com.hero.dto.entity.Sighting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SightingMapper implements RowMapper<Sighting> {

    @Override
    public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sighting sighting = new Sighting();
        sighting.setSightingId(rs.getInt("sightingId"));
        sighting.setDateSeen(rs.getDate("dateSeen"));
        sighting.setSupeId(rs.getInt("supeId"));
        sighting.setLocationId(rs.getInt("locationId"));
        return sighting;
    }
}

