package com.spring.tp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("jdbc")
public class OuvrageRepository {

    @Autowired
    private JdbcTemplate template;

}
