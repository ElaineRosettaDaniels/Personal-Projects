/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dto;

import java.sql.Timestamp;

/**
 *
 * @author Asus1
 */
public class Guess {
    
    private int guessId;
    private int gameId;
    private String guessCombo;
    private String result;
    private Timestamp guessTime;

    public int getGuessId() {
        return guessId;
    }

    public void setGuessId(int guessId) {
        this.guessId = guessId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuessCombo() {
        return guessCombo;
    }

    public void setGuessCombo(String guessCombo) {
        this.guessCombo = guessCombo;
    }

    public Timestamp getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(Timestamp guessTime) {
        this.guessTime = guessTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Guess{" + 
               "guessId=" + guessId + 
                ", gameId=" + gameId + 
                ", guessCombo=" + guessCombo + 
                ", result=" + result + 
                ", guessTime=" + guessTime + '}';
    }
    
}
