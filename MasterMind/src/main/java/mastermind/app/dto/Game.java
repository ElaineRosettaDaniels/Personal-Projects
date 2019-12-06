/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dto;

import java.util.Objects;

/**
 *
 * @author Asus1
 */
public class Game {
    
    private int gameId;
    private String solution;
    private boolean finished;

    public Game(int gameId, String solution, boolean finished) {
        this.gameId = gameId;
        this.solution = solution;
        this.finished = finished;
    }
    
    

    public Game() {}
    
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Game{" + 
                "gameId=" + gameId + 
                ", solution=" + solution + 
                ", finished=" + finished + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.gameId;
        hash = 53 * hash + Objects.hashCode(this.solution);
        hash = 53 * hash + (this.finished ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finished != other.finished) {
            return false;
        }
        if (!Objects.equals(this.solution, other.solution)) {
            return false;
        }
        return true;
    }
    
    
    
}
