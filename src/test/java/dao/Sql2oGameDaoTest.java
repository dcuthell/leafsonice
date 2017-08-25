package dao;

import models.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oGameDaoTest {

    private Connection conn;
    private Sql2oGameDao gameDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        gameDao = new Sql2oGameDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_addsId() throws Exception {
        Game testGame = createGame();
        Integer initialGameId = testGame.getId();
        gameDao.add(testGame);
        assertNotEquals(initialGameId, testGame.getId());
    }

    @Test
    public void getAll_returnsAllGames() throws Exception {
        Game testGame = createGame();
        Game testGame1 = createAnotherGame();
        gameDao.add(testGame);
        gameDao.add(testGame1);
        assertEquals(2, gameDao.getAll().size());
    }

    public Game createGame(){
        return new Game("PastDate", "Stupid Sens", "AC Center", 100, 0 );
    }

    public Game createAnotherGame(){
        return new Game("FutureDate", "Horrible Habs", "Centre Bell");
    }

}