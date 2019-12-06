/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.service;

import java.util.List;
import mastermind.app.dao.GameDao;
import mastermind.app.dao.GameDaoImpl;
import mastermind.app.dao.GuessDao;
import mastermind.app.dao.GuessDaoImpl;
import mastermind.app.dto.Game;
import mastermind.app.dto.Guess;

/**
 *
 * @author Asus1
 */
public interface ServiceLayer {
    
    public Game getGame(int gameId);
    public List<Game> getAllGames();
    public List<Guess> getAllGuesses();
    public String generateSolution();
    public Game startNewGame();
    public Guess makeGuess(int gameId, String guessCombo, String result);
    public String giveGuessResult(String guessCombo, String solution);
    public void setGameWon(int gameId, Game game);
    public Guess addGuess(Guess guess);
    public Guess doTheWholeGuessThing(Guess guess);
}
