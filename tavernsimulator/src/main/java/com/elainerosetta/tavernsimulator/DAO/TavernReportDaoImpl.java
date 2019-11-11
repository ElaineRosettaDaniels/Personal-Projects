/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elainerosetta.tavernsimulator.DAO;

import com.elainerosetta.tavernsimulator.DTO.Tavern;
import com.elainerosetta.tavernsimulator.DTO.TavernReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class TavernReportDaoImpl implements TavernReportDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public TavernReport getReportById(int tavernReportId) {
        try {
            final String REQ = "SELECT * FROM tavernReport WHERE tavernReportId = ?";
            return jdbc.queryForObject(REQ, new TavernReportMapper(), tavernReportId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<TavernReport> getReportsForTavern(Tavern tavern) {
        final String REQ = "SELECT * FROM tavernReport WHERE tavernId = ?";
        return jdbc.query(REQ, new TavernReportMapper(), tavern.getTavernId());
    }

    @Override
    @Transactional
    public TavernReport addTavernReport(TavernReport report) {
        final String REQ = "INSERT INTO tavernReport(tavernReportWeek, tavernReportRoll, "
                + "tavernReportEarnings, tavernReportFunds, tavernId) VALUES(?,?,?,?,?)";
        jdbc.update(REQ, 
                report.getTavernReportWeek(), 
                report.getTavernReportRoll(), 
                report.getTavernReportEarnings(), 
                report.getTavernReportFunds(), 
                report.getTavernId());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        report.setTavernReportId(newId);
        return report;
    }

    @Override
    @Transactional
    public void deleteTavernReport(int tavernReportId) {
        final String REQ = "DELETE FROM tavernReport WHERE tavernReportId = ?";
        jdbc.update(REQ, tavernReportId);
    }

    public static final class TavernReportMapper implements RowMapper<TavernReport> {
        
        @Override
        public TavernReport mapRow(ResultSet rs, int index) throws SQLException {
            TavernReport report = new TavernReport();
            report.setTavernReportId(rs.getInt("tavernReportId"));
            report.setTavernReportWeek(rs.getInt("tavernReportWeek"));
            report.setTavernReportRoll(rs.getInt("tavernReportRoll"));
            report.setTavernReportEarnings(rs.getInt("tavernReportEarnings"));
            report.setTavernReportFunds(rs.getInt("tavernReportFunds"));
            report.setTavernId(rs.getInt("tavernId"));
            
            return report;
        }
    }
}
