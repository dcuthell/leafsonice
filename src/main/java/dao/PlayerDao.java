package dao;

import models.Game;
import models.Player;

import java.util.List;

public interface PlayerDao {

    //create
    void add (Player player);
    void addPlayertoGame(Player player, Game game);
    //read
    List<Player> getAll();
    List<Game> getAllGamesForAPlayer(int playerId);
    Player findById(int playerId);
    //update
    void update(int id, String firstName, String lastName, Integer number, String handed);
    //delete
    void deleteById(int playerId);
    void deleteAll();

}
