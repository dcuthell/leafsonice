package dao;

import models.Game;
import models.Player;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oGameDao implements GameDao {

    private final Sql2o sql2o;

    public Sql2oGameDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Game game){
        String sql = "INSERT INTO games (gameDate, opposingTeam, location, played, leafSscore, opponentScore) VALUES (:gameDate, :opposingTeam, :location, :played, :leafsScore, :opponentScore)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(game)
                    .executeUpdate()
                    .getKey();
            game.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    public List<Game> getAll(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM games")
                    .executeAndFetch(Game.class);
        }
    }

    public List<Player> getAllPlayersForAGame(int gameId){
        return null;
    }


}
