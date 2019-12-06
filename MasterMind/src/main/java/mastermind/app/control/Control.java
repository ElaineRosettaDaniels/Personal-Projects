/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.control;


import java.util.ArrayList;
import java.util.List;
import mastermind.app.dao.GameDao;
import mastermind.app.dao.GuessDao;
import mastermind.app.dto.Game;
import mastermind.app.dto.Guess;
import mastermind.app.service.ServiceLayer;
import mastermind.app.service.ServiceLayerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus1
 */
@RestController
@RequestMapping("/api")
public class Control {
    
    @Autowired
    ServiceLayerImpl serv;
    
    @GetMapping("/game")
    List<Game> getAllGames(){
        return serv.getAllGames();
    }
    
    @GetMapping("/game/{gameId}")
    Game getGame(@PathVariable("gameId") int gameId) {
        Game gottenGame = serv.getGame(gameId);
        if (gottenGame.getFinished()==false) {
            gottenGame.setSolution("____");
        }
        return gottenGame;
    }
    
    @GetMapping("/guesses/{gameId}")
    List<Guess> getGuessesForGame(@PathVariable("gameId") int gameId) {
        List<Guess> gameGuesses = new ArrayList<>();
        for (Guess g : serv.getAllGuesses()){
            if(g.getGameId() == gameId){
                gameGuesses.add(g);
            }
        }
        return gameGuesses;
    }
    
    @PostMapping("/begin")
    String createGame() {
        Game newGame = serv.startNewGame();
        String gameStartMsg = "New game begun. New game Id:" + newGame.getGameId();
        return gameStartMsg;
    }
    
    @PostMapping("/guess")
    Guess makeGuess(@RequestBody Guess guess) {
        return serv.doTheWholeGuessThing(guess);
    }
    
}
