package dao;

import models.Game;
import models.Player;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
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
        ArrayList<Player> allPlayers = new ArrayList<>();
        String getIdsSQL = "SELECT playerId FROM games_players WHERE gameId= :gameId";
        String sql = "SELECT * FROM players WHERE id = :id";
        try (Connection con = sql2o.open()){
            List<Integer> playerIds = con.createQuery(getIdsSQL)
                    .addParameter("gameId", gameId)
                    .executeAndFetch(Integer.class);
            for(Integer playerId : playerIds){
                allPlayers.add(
                        con.createQuery(sql)
                                .addParameter("id", playerId)
                                .executeAndFetchFirst(Player.class)
                );
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return allPlayers;
    }

    public Game findById(int gameId){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM games WHERE id= :id")
                    .addParameter("id", gameId)
                    .executeAndFetchFirst(Game.class);
        }
    }

    public void update(int id, String gameDate, String opposingTeam, String location, Boolean played, Integer leafsScore, Integer opponentScore){
        String sql = "UPDATE games SET (gameDate, opposingTeam, location, played, leafSscore, opponentScore) = (:gameDate, :opposingTeam, :location, :played, :leafsScore, :opponentScore) WHERE id= :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("gameDate", gameDate)
                    .addParameter("opposingTeam", opposingTeam)
                    .addParameter("location", location)
                    .addParameter("played", played)
                    .addParameter("leafsScore", leafsScore)
                    .addParameter("opponentScore", opponentScore)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteById(int gameId){
        String sql = "DELETE FROM games WHERE id = :id";
        String deleteJoin = "DELETE from games_players WHERE gameId = :gameId";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", gameId)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("gameId", gameId)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteAll(){
        String sql = "DELETE FROM games";
        String deleteJoin = "DELETE from games_players";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
