package dao;

import models.Player;
import models.Game;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oPlayerDao implements PlayerDao{

    private final Sql2o sql2o;

    public Sql2oPlayerDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Player player){
        String sql = "INSERT INTO players (firstName, lastName, playerNumber, handed, position) VALUES (:firstName, :lastName, :playerNumber, :handed, :position)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(player)
                    .executeUpdate()
                    .getKey();
            player.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addPlayerToGame(Player player, Game game){
        String sql = "INSERT INTO games_players (gameId, playerId) VALUES (:gameId, :playerId)";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("gameId", game.getId())
                    .addParameter("playerId", player.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<Player> getAll(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM players")
                    .executeAndFetch(Player.class);
        }
    }

    public List<Game> getAllGamesForAPlayer(int playerId){
        ArrayList<Game> allGames = new ArrayList<>();
        String getIdsSQL = "SELECT gameId FROM games_players WHERE playerId= :playerId";
        String sql = "SELECT * FROM games WHERE id = :id";
        try (Connection con = sql2o.open()){
            List<Integer> gameIds = con.createQuery(getIdsSQL)
                    .addParameter("playerId", playerId)
                    .executeAndFetch(Integer.class);
            for(Integer gameId : gameIds){
                allGames.add(
                        con.createQuery(sql)
                                .addParameter("id", gameId)
                                .executeAndFetchFirst(Game.class)
                );
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return allGames;
    }

    public Player findById(int playerId){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM players WHERE id= :id")
                    .addParameter("id", playerId)
                    .executeAndFetchFirst(Player.class);
        }
    }

    public void update(int id, String firstName, String lastName, Integer playerNumber, String handed, String position){
        String sql = "UPDATE players SET (firstName, lastName, playerNumber, handed, position) = (:firstName, :lastName, :playerNumber, :handed, :position) WHERE id= :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("firstName", firstName)
                    .addParameter("lastName", lastName)
                    .addParameter("playerNumber", playerNumber)
                    .addParameter("handed", handed)
                    .addParameter("position", position)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteById(int playerId){
        String sql = "DELETE FROM players WHERE id = :id";
        String deleteJoin = "DELETE from games_players WHERE playerId = :playerId";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", playerId)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("playerId", playerId)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteAll(){
        String sql = "DELETE FROM players";
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
