/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import mastermind.app.dto.Guess;
import mastermind.app.dto.Guess;
import mastermind.app.TestApplicationConfiguration;
import mastermind.app.dto.Game;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Asus1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GuessDaoImplTest {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    GameDao gaDao;
    
    @Autowired
    GuessDao guDao;

    public GuessDaoImplTest() {
    }
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    //Delete everything in DB so that when we add something, it is the only thing
    // considered for the test.
    @Before
    public void setUp() throws Exception {
        jdbc.execute("SET FOREIGN_KEY_CHECKS = 0;");
        jdbc.execute("TRUNCATE TABLE game;");
        jdbc.execute("TRUNCATE TABLE guess;");
        jdbc.execute("SET FOREIGN_KEY_CHECKS = 1;");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testAddGuess() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Guess guess = new Guess();
        guess.setGameId(1);
        guess.setGuessCombo("1234");
        guess.setResult("e:2:p:2");
        guess.setGuessTime(ts);
        guess = guDao.addGuess(guess);
        
        Guess fromDao = guDao.getGuess(guess.getGuessId());
        
        assertEquals(guess.getGuessId(), fromDao.getGuessId());
        assertEquals(guess.getGuessCombo(), fromDao.getGuessCombo());
        assertEquals(guess.getResult(), fromDao.getResult());
        assertEquals(guess.getGuessTime(), fromDao.getGuessTime());
    }

    /**
     * Test of getGuess method, of class GuessDaoImpl.
     */
    @Test
    public void testGetGuess() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Guess guess = new Guess();
        guess.setGameId(1);
        guess.setGuessCombo("1234");
        guess.setResult("e:2:p:2");
        guess.setGuessTime(ts);
        guess = guDao.addGuess(guess);
        
        Guess fromDao = guDao.getGuess(guess.getGuessId());
        
        assertEquals(guess.getGuessId(), fromDao.getGuessId());
        assertEquals(guess.getGuessCombo(), fromDao.getGuessCombo());
        assertEquals(guess.getResult(), fromDao.getResult());
        assertEquals(guess.getGuessTime(), fromDao.getGuessTime());
    }

    /**
     * Test of getAllGuesss method, of class GuessDaoImpl.
     */
    @Test
    public void testGetAllGuesses() {
        Game game = new Game();
        game.setSolution("1234");
        game.setFinished(false);
        game = gaDao.addGame(game);
        
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Guess guess1 = new Guess();
        guess1.setGameId(1);
        guess1.setGuessCombo("1234");
        guess1.setResult("e:2:p:2");
        guess1.setGuessTime(ts);
        guess1 = guDao.addGuess(guess1);
        
        Guess guess2 = new Guess();
        guess2.setGameId(1);
        guess2.setGuessCombo("1234");
        guess2.setResult("e:2:p:2");
        guess2.setGuessTime(ts);
        guess2 = guDao.addGuess(guess2);
        
        List<Guess> allGuesses = guDao.getAllGuesses();
        
         assertNotNull(allGuesses);
         assertEquals(2, allGuesses.size());
//         assertTrue(allGuesses.contains(guess1));
//         assertTrue(allGuesses.contains(guess2));
    }
    
    @Test
    public void testDeleteGuess() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Guess guess = new Guess();
        guess.setGameId(1);
        guess.setGuessCombo("1234");
        guess.setResult("e:2:p:2");
        guess.setGuessTime(ts);
        guess = guDao.addGuess(guess);
        
        guDao.deleteGuess(guess.getGuessId());
        Guess noGuess = guDao.getGuess(guess.getGuessId());
        
        assertNull(noGuess);
    }
}
