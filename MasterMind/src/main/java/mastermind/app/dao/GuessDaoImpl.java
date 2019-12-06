/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dao;


import mastermind.app.dto.Guess;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Asus1
 */
@Repository
public class GuessDaoImpl implements GuessDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    GameDao games;

    @Override
    @Transactional
    public Guess addGuess(Guess guess) {
        final String INSERT_GUESS = "INSERT INTO guess(GameId, GuessCombo, Result, GuessTime) VALUES(?,?,?,?)";
        jdbc.update(INSERT_GUESS,
                guess.getGameId(),
                guess.getGuessCombo(),
                guess.getResult(),
                guess.getGuessTime());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        guess.setGuessId(newId);
        return guess;
    }

    @Override
    public Guess getGuess(int guessId) {
        String sql = "SELECT * FROM guess WHERE GuessId = ?";
        return jdbc.queryForObject(sql, new GuessMapper(), guessId);
    }

    @Override
    public List<Guess> getAllGuesses() {
        String sql = "SELECT * FROM guess";
        return jdbc.query(sql, new GuessMapper());
    }

    @Override
    public void deleteGuess(int guessId) {
        final String DELETE_GUESS_BY_ID = "DELETE FROM guess WHERE GuessId = ?";
        jdbc.update(DELETE_GUESS_BY_ID, guessId);
    }

    public class GuessMapper implements RowMapper<Guess> {

        @Override
        public Guess mapRow(ResultSet row, int i) throws SQLException {
            Guess result = new Guess();
            result.setGuessId(row.getInt("GuessId"));
            result.setGameId(row.getInt("GameId"));
            result.setGuessCombo(row.getString("GuessCombo"));
            result.setResult(row.getString("Result"));
            result.setGuessTime(row.getTimestamp("GuessTime"));
            
            return result;
        }
        
    }
}
