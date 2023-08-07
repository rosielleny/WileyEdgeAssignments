package com.hero.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hero.dto.entity.Supe;

@Repository
public class SupeDaoImpl implements SupeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Supe createSupe(Supe supe) {
        String query = "INSERT INTO Supe (supeName, supePower, supeDescription) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, supe.getSupeName(), supe.getSupePower(), supe.getSupeDescription());
            
            String selectQuery = "SELECT LAST_INSERT_ID()";
            int supeId = jdbcTemplate.queryForObject(selectQuery, Integer.class);

            return new Supe(supeId, supe.getSupeName(), supe.getSupePower(), supe.getSupeDescription());
        } catch (Exception ex) {
            return null;
        }
    }

    
    @Override
    public Supe getSupeById(int supeId) {
        String query = "SELECT * FROM Supe WHERE supeId = ?";
        List<Supe> supes = jdbcTemplate.query(query, new SupeMapper(), supeId);
        
        if (!supes.isEmpty()) {
            return supes.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int updateSupe(Supe supe) {
        String query = "UPDATE Supe SET supeName = ?, supePower = ?, supeDescription = ? WHERE supeId = ?";
        try {
            return jdbcTemplate.update(query, supe.getSupeName(), supe.getSupePower(), supe.getSupeDescription(), supe.getSupeId());
        } catch (Exception ex) {
            return 0;
        }
    }
    
    @Override
    public boolean deleteSupe(int supeId) {
        String query = "DELETE FROM Supe WHERE supeId = ?";
        try {
            int affectedRows = jdbcTemplate.update(query, supeId);
            return affectedRows > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Supe> getAllSupes() {
        String query = "SELECT * FROM Supe";
        return jdbcTemplate.query(query, new SupeMapper());
    }

    @Override
    public List<String> getSuperheroesAtLocation(String locationName) {
        String query = "SELECT supe.supeName " +
                       "FROM Sighting sighting " +
                       "INNER JOIN Location location ON sighting.locationId = location.locationId " +
                       "INNER JOIN Supe supe ON sighting.supeId = supe.supeId " +
                       "WHERE location.locationName = ?";

        return jdbcTemplate.queryForList(query, String.class, locationName);
    }


}
