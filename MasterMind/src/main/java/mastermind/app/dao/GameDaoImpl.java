/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mastermind.app.dto.Game;
import mastermind.app.dto.Game;
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
public class GameDaoImpl implements GameDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game(Solution, Finished) VALUES(?,?)";
        jdbc.update(INSERT_GAME,
                game.getSolution(),
                game.getFinished());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        
        return game;
    }

    @Override
    public Game getGame(int gameId) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE GameId = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        List<Game> gamesAll = jdbc.query(SELECT_ALL_GAMES, new GameMapper());
        
        for (Game g : gamesAll) {
            if (g.getFinished()==false) {
                g.setSolution("____");
            }
        }
        return gamesAll;
    }

    @Override
    public void deleteGame(int gameId, Game game) {
        final String DELETE_GAME_BY_ID = "DELETE FROM game WHERE GameId = ?";
        jdbc.update(DELETE_GAME_BY_ID, gameId);
    }

    @Override
    public void setGameWon(int gameId, Game game) {
        final String SET_GAME_WON = "UPDATE game SET Finished = 1 WHERE GameId = ?";
        jdbc.update(SET_GAME_WON, gameId);
        game.setFinished(true);
    }

    public class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet row, int i) throws SQLException {
            Game result = new Game();
            result.setGameId(row.getInt("GameId"));
            result.setSolution(row.getString("Solution"));
            result.setFinished(row.getBoolean("Finished"));
            return result;
        }   
    }  
}
