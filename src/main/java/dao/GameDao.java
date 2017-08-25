package dao;

import models.Player;
import models.Game;

import java.util.List;

public interface GameDao {

    //create
    void add (Game game);
    //read
    List<Game> getAll();
    List<Player> getAllPlayersForAGame(int gameId);
    Game findById(int gameId);
    //update
    void update(int id, String gameDate, String opposingTeam, String location, Boolean played, Integer leafsScore, Integer opponentScore);
    //delete
    void deleteById(int gameId);
    void deleteAll();

}
