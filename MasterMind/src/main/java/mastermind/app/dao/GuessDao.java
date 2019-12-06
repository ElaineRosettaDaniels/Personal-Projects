/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dao;

import java.util.List;
import mastermind.app.dto.Game;
import mastermind.app.dto.Guess;

/**
 *
 * @author Asus1
 */
public interface GuessDao {
    Guess addGuess(Guess guess);
    Guess getGuess(int guessId);
    List<Guess> getAllGuesses();
    void deleteGuess(int guessId);

}
