package dao;

import models.Game;
import models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oPlayerDaoTest {

    private Connection conn;
    private Sql2oPlayerDao playerDao;
    private Sql2oGameDao gameDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        playerDao = new Sql2oPlayerDao(sql2o);
        gameDao = new Sql2oGameDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_addsId() throws Exception {
        Player testPlayer = createPlayer();
        Integer initialPlayerId = testPlayer.getId();
        playerDao.add(testPlayer);
        assertNotEquals(initialPlayerId, testPlayer.getId());
    }

    @Test
    public void getAll_returnsAllPlayers() throws Exception {
        Player testPlayer = createPlayer();
        Player testPlayer1 = createAnotherPlayer();
        playerDao.add(testPlayer);
        playerDao.add(testPlayer1);
        assertEquals(2, playerDao.getAll().size());
    }

    @Test
    public void getAllGamesForAPlayer_returnsNoGamesWhenEmpty() throws Exception {
        Player testPlayer = createPlayer();
        playerDao.add(testPlayer);
        assertEquals(0, playerDao.getAllGamesForAPlayer(testPlayer.getId()).size());
    }

    @Test
    public void getAllGamesForAPlayer_returnsAllGames() throws Exception {
        Player testPlayer = createPlayer();
        Game testGame = createGame();
        playerDao.add(testPlayer);
        gameDao.add(testGame);
        playerDao.addPlayerToGame(testPlayer, testGame);
        assertEquals(1, playerDao.getAllGamesForAPlayer(1).size());
    }

    @Test
    public void findById_returnsCorrectly() throws Exception {
        Player testPlayer = createPlayer();
        playerDao.add(testPlayer);
        assertEquals(testPlayer, playerDao.findById(testPlayer.getId()));
    }

    @Test
    public void update_changesCorrectly() throws Exception {
        Player testPlayer = createPlayer();
        playerDao.add(testPlayer);
        String oldFirstName = testPlayer.getFirstName();
        playerDao.update(testPlayer.getId(), "Sammy", "Snipes", 69, "Right", testPlayer.getPosition());
        assertNotEquals(oldFirstName, playerDao.findById(testPlayer.getId()).getFirstName());
    }

    @Test
    public void deleteById_removesPlayer() throws Exception {
        Player testPlayer = createPlayer();
        playerDao.add(testPlayer);
        playerDao.deleteById(testPlayer.getId());
        assertEquals(0, playerDao.getAll().size());
    }

    @Test
    public void deleteAll_removesAllPlayers() throws Exception {
        Player testPlayer = createPlayer();
        Player testPlayer1 = createAnotherPlayer();
        playerDao.add(testPlayer);
        playerDao.add(testPlayer1);
        playerDao.deleteAll();
        assertEquals(0, playerDao.getAll().size());
    }

    public Player createPlayer(){
        return new Player("Duster", "Dan", 0, "Right");
    }

    public Player createAnotherPlayer(){
        return new Player("Duster", "Darrel", 99, "Left");
    }

    public Game createGame(){
        return new Game("PastDate", "Stupid Sens", "AC Center", 100, 0 );
    }

}