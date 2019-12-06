/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.service;

import java.sql.Array;
import java.util.ArrayList;
import mastermind.app.dao.GameDao;
import mastermind.app.dao.GameDaoImpl;
import mastermind.app.dao.GuessDao;
import mastermind.app.dao.GuessDaoImpl;
import mastermind.app.dto.Game;
import mastermind.app.dto.Guess;
import java.lang.Math;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus1
 */
@Repository
public class ServiceLayerImpl implements ServiceLayer {
    
    @Autowired
    GameDao gaDao;
    
    @Autowired
    GuessDao guDao;

    public ServiceLayerImpl() {}
    
    @Override
    public String generateSolution() {
        String solution;
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 4; i++) {
            nums.add(list.get(i));
        }
        
        solution =  Integer.toString(nums.get(0)) +
                    Integer.toString(nums.get(1)) +
                    Integer.toString(nums.get(2)) +
                    Integer.toString(nums.get(3));
        
        return solution;
    }
    
    @Override
    public List<Game> getAllGames() {
        return gaDao.getAllGames();
    }
    
    @Override
    public Game getGame(int gameId) {
        return gaDao.getGame(gameId);
    }

    @Override
    public Game startNewGame() {
        Game newGame = new Game();
        newGame.setFinished(false);
        newGame.setSolution(generateSolution());
        newGame = gaDao.addGame(newGame);
        return newGame;
    }

    @Override
    public Guess makeGuess(int gameId, String guessCombo, String result) {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Guess newGuess = new Guess();
        newGuess.setGameId(gameId);
        newGuess.setGuessCombo(guessCombo);
        newGuess.setResult(result);
        newGuess.setGuessTime(ts);
        return newGuess;
    }
    
    @Override
    public List<Guess> getAllGuesses() {
        return guDao.getAllGuesses();
    }


    @Override
    public String giveGuessResult(String solution, String guessCombo) {
   
        String guessArray[] = guessCombo.split("");
        String solutionArray[] = solution.split("");
        
        int exacts = 0;
        for (int i = 0; i < solutionArray.length; i++) {
            if (solutionArray[i].equals(guessArray[i])) {
                exacts++;
                solutionArray[i]="-1";
                guessArray[i]="-1";
            }
        }
        
        int partials = 0;
        for (int i = 0; i < solutionArray.length; i++) {
            if (solutionArray[i].equals("-1")) {
                continue;
            }
            for (int j = 0; j < solutionArray.length; j++) {
                if (guessArray[j].equals("-1")) {
                    continue;
                }
                if (solutionArray[i].equals(guessArray[j])) {
                    partials++;
                    solutionArray[i]="-1";
                    guessArray[j]="-1";
                }
            }
        }
        String result = "e:" + exacts + ":p:" + partials;
        return result;
    }

    @Override
    public void setGameWon(int gameId, Game game) {
        gaDao.setGameWon(gameId, game);
    }

    @Override
    @Transactional
    public Guess addGuess(Guess guess) {
        return guDao.addGuess(guess);
    }

//    Game chosenGame = serv.getGame(guess.getGameId());
//        String solution = chosenGame.getSolution();
//        String result = serv.giveGuessResult(solution, guess.getGuessCombo());
//        Guess madeGuess = serv.makeGuess(chosenGame.getGameId(), guess.getGuessCombo(), result);
//        serv.addGuess(madeGuess);
//        return madeGuess;
    
    @Override
    public Guess doTheWholeGuessThing(Guess guess) {
        Game chosenGame = getGame(guess.getGameId());
        String solution = chosenGame.getSolution();
        String result = giveGuessResult(solution, guess.getGuessCombo());
        Guess madeGuess = makeGuess(chosenGame.getGameId(), guess.getGuessCombo(), result);
        addGuess(madeGuess);
        return madeGuess;
    }

    
    


}
