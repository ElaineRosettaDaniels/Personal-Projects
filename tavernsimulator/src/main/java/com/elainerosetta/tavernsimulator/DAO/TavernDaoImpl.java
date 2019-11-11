/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.DAO;

import com.elainerosetta.tavernsimulator.DTO.Tavern;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus1
 */
@Repository
public class TavernDaoImpl implements TavernDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Tavern getTavernById(int tavernId) {
        try {
            final String REQ = "SELECT * FROM tavern WHERE tavernId = ?";
            return jdbc.queryForObject(REQ, new TavernMapper(), tavernId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Tavern> getAllTaverns() {
        final String REQ = "SELECT * FROM tavern";
        return jdbc.query(REQ, new TavernMapper());
    }

    @Override
    @Transactional
    public Tavern addTavern(Tavern tav) {
        final String REQ = "INSERT INTO tavern(tavernName, tavernOwner, tavernAbout, "
                + "tavernFunds, tavernDrinks, tavernFood, tavernRepairs) "
                + "VALUES(?,?,?,?,?,?,?)";
        jdbc.update(REQ, 
                tav.getTavernName(),
                tav.getTavernOwner(),
                tav.getTavernAbout(),
                tav.getTavernFunds(),
                tav.getTavernDrinks(),
                tav.getTavernFood(),
                tav.getTavernRepairs());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        tav.setTavernId(newId);
        return tav;
    }

    @Override
    public void updateTavernBio(Tavern tav) {
        final String REQ = "UPDATE tavern SET tavernName = ?, tavernOwner = ?, tavernAbout = ?"
                + " WHERE tavernId = ?";
        jdbc.update(REQ, 
                tav.getTavernName(), 
                tav.getTavernOwner(), 
                tav.getTavernAbout(), 
                tav.getTavernId());
    }
    
    @Override
    public void updateTavernStats(Tavern tav) {
        final String REQ = "UPDATE tavern SET tavernFunds = ?, tavernDrinks = ?, "
                + "tavernFood = ?, tavernRepairs = ? WHERE tavernId = ?";
        jdbc.update(REQ, 
                tav.getTavernFunds(), 
                tav.getTavernDrinks(), 
                tav.getTavernFood(), 
                tav.getTavernRepairs(), 
                tav.getTavernId());
    }

    @Override
    @Transactional
    public void deleteTavern(int tavernId) {
        final String DELETE_TAVERNREPORT = "DELETE FROM tavernReport WHERE tavernId = ?";
        jdbc.update(DELETE_TAVERNREPORT, tavernId);
        
        final String DELETE_TAVERN = "DELETE FROM tavern WHERE tavernId = ?";
        jdbc.update(DELETE_TAVERN, tavernId);
    }
    
    public static final class TavernMapper implements RowMapper<Tavern> {
        
        @Override
        public Tavern mapRow(ResultSet rs, int index) throws SQLException {
            Tavern tavern = new Tavern();
            tavern.setTavernId(rs.getInt("tavernId"));
            tavern.setTavernName(rs.getString("tavernName"));
            tavern.setTavernOwner(rs.getString("tavernOwner"));
            tavern.setTavernAbout(rs.getString("tavernAbout"));
            tavern.setTavernFunds(rs.getInt("tavernFunds"));
            tavern.setTavernDrinks(rs.getInt("tavernDrinks"));
            tavern.setTavernFood(rs.getInt("tavernFood"));
            tavern.setTavernRepairs(rs.getInt("tavernRepairs"));
            
            return tavern;
        }
    }
    
}
