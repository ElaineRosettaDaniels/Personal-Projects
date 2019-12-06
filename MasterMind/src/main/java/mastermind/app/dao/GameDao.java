/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dao;

import java.util.List;
import mastermind.app.dto.Game;

/**
 *
 * @author Asus1
 */
public interface GameDao {
    Game addGame(Game game);
    Game getGame(int gameId);
    List<Game> getAllGames(); 
    void deleteGame(int gameId, Game game);
    void setGameWon(int gameId, Game game);
}
