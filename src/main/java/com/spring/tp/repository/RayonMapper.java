package com.spring.tp.repository;

import com.spring.tp.entity.Rayon;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RayonMapper implements RowMapper<Rayon> {
    @Override
    public Rayon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rayon rayon = new Rayon();
        rayon.setTheme(rs.getString("theme"));
        return rayon;
    }
}
