package com.hero.model.dao;

import org.springframework.jdbc.core.RowMapper;

import com.hero.dto.entity.Supe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupeMapper implements RowMapper<Supe> {

    @Override
    public Supe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Supe supe = new Supe();
        supe.setSupeId(rs.getInt("supeId"));
        supe.setSupeName(rs.getString("supeName"));
        supe.setSupePower(rs.getString("supePower"));
        supe.setSupeDescription(rs.getString("supeDescription"));
        return supe;
    }
}

