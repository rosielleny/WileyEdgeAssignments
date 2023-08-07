package com.hero.model.dao;

import org.springframework.jdbc.core.RowMapper;

import com.hero.dto.entity.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setLocationId(rs.getInt("locationId"));
        location.setLocationName(rs.getString("locationName"));
        location.setLocationDescription(rs.getString("locationDescription"));
        location.setLocationAddress(rs.getString("locationAddress"));
        location.setLocationCoordinates(rs.getString("locationCoordinates"));
        return location;
    }
}