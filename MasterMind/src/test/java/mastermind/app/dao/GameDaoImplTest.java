/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.app.dao;

import java.util.List;
import mastermind.app.dto.Game;
import mastermind.app.dto.Guess;
import mastermind.app.TestApplicationConfiguration;
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
public class GameDaoImplTest {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    GameDao gaDao;
    
    @Autowired
    GuessDao guDao;
    
    public GameDaoImplTest() {
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

    /**
     * Test of addGame method, of class GameDaoImpl.
     */
    @Test
    public void testAddGame() {
        Game game = new Game();
        game.setSolution("1234");
        game.setFinished(false);
        game = gaDao.addGame(game);
        
        Game fromDao = gaDao.getGame(game.getGameId());
        
        assertEquals(game.getGameId(), fromDao.getGameId());
        assertEquals(game.getSolution(), fromDao.getSolution());
        assertEquals(game.getFinished(), fromDao.getFinished());
    }

    /**
     * Test of getGame method, of class GameDaoImpl.
     */
    @Test
    public void testGetGame() {
        Game game = new Game();
        game.setSolution("1234");
        game.setFinished(false);
        game = gaDao.addGame(game);
        
        Game fromDao = gaDao.getGame(game.getGameId());
        
        assertEquals(game.getGameId(), fromDao.getGameId());
        assertEquals(game.getSolution(), fromDao.getSolution());
        assertEquals(game.getFinished(), fromDao.getFinished());
    }

    /**
     * Test of getAllGames method, of class GameDaoImpl.
     */
    @Test
    public void testGetAllGames() {
        Game game1 = new Game();
        game1.setSolution("1234");
        game1.setFinished(false);
        game1 = gaDao.addGame(game1);
        
        Game game2 = new Game();
        game2.setSolution("5678");
        game2.setFinished(false);
        game2 = gaDao.addGame(game2);
        
        List<Game> allGames = gaDao.getAllGames();
        
         assertNotNull(allGames);
         assertEquals(2, allGames.size());
         assertTrue(allGames.contains(game1));
         assertTrue(allGames.contains(game2));
    }
    
    @Test
    public void testDeleteGame() {
        Game game1 = new Game();
        game1.setSolution("1234");
        game1.setFinished(false);
        game1 = gaDao.addGame(game1);
        
        gaDao.deleteGame(game1.getGameId(), game1);
        Game noGame = gaDao.getGame(game1.getGameId());
        
        assertNull(noGame);
    }
    
    @Test
    public void testSetGameWon() {
        Game game1 = new Game();
        game1.setSolution("1234");
        game1.setFinished(false);
        game1 = gaDao.addGame(game1);
        
        gaDao.setGameWon(game1.getGameId(), game1);
        
        assertTrue(game1.getFinished());
    }
}
