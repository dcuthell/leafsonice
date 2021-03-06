import com.google.gson.Gson;
import dao.Sql2oGameDao;
import dao.Sql2oPlayerDao;
import exceptions.ApiException;
import models.Game;
import models.Player;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oGameDao gameDao;
        Sql2oPlayerDao playerDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/leafsonice.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        gameDao = new Sql2oGameDao(sql2o);
        playerDao = new Sql2oPlayerDao(sql2o);
        conn = sql2o.open();

        //STATIC
        //CREATE
        post("/players/new", "application/json", (req, res) -> {
            Player player = gson.fromJson(req.body(), Player.class);
            playerDao.add(player);
            res.status(201);
            return gson.toJson(player);
        });

        post("/games/new", "application/json", (req, res) -> {
            Game game = gson.fromJson(req.body(), Game.class);
            gameDao.add(game);
            res.status(201);
            return gson.toJson(game);
        });

        //READ
        get("/players", "application/json",(request, response) -> {
            return gson.toJson(playerDao.getAll());
        });

        get("/games", "application/json",(request, response) -> {
            return gson.toJson(gameDao.getAll());
        });



        //UPDATE
        //DELETE
        get("/players/remove-all", "application/json",(request, response) -> {
            playerDao.deleteAll();
            return gson.toJson(playerDao.getAll());
        });

        get("/games/remove-all", "application/json",(request, response) -> {
            gameDao.deleteAll();
            return gson.toJson(gameDao.getAll());
        });


        //DYNAMIC
        //CREATE
        //READ
        get("/players/:id", "application/json",(request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));

            Player returnPlayer = playerDao.findById(playerId);

            if(returnPlayer == null){
                throw new ApiException(404, String.format("No player with the id: \"%s\" exists", request.params("id")));
            }

            return gson.toJson(returnPlayer);
        });

        get("/games/:id", "application/json",(request, response) -> {
            int gameId = Integer.parseInt(request.params("id"));

            Game returnGame = gameDao.findById(gameId);

            if(returnGame == null){
                throw new ApiException(404, String.format("No game with the id: \"%s\" exists", request.params("id")));
            }

            return gson.toJson(returnGame);
        });

        get("/players/:id/games", "application/json",(request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));

            List<Game> allGames = playerDao.getAllGamesForAPlayer(playerId);

            return gson.toJson(allGames);
        });

        get("/games/:id/players", "application/json",(request, response) -> {
            int gameId = Integer.parseInt(request.params("id"));

            List<Player> allPlayers = gameDao.getAllPlayersForAGame(gameId);

            return gson.toJson(allPlayers);
        });
        //UPDATE
        post("/players/:playerid/game/:gameid", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("playerid"));
            int gameId = Integer.parseInt(request.params("gameid"));
            playerDao.addPlayerToGame(playerDao.findById(playerId), gameDao.findById(gameId));
            response.status(201);
            return gson.toJson(playerDao.findById(playerId));
        });

        post("/games/:gameid/player/:playerid", "application/json", (request, response) -> {
            int playerId = Integer.parseInt(request.params("playerid"));
            int gameId = Integer.parseInt(request.params("gameid"));
            playerDao.addPlayerToGame(playerDao.findById(playerId), gameDao.findById(gameId));
            response.status(201);
            return gson.toJson(gameDao.findById(gameId));
        });

        //DELETE
        get("/players/:id/remove", "application/json",(request, response) -> {
            int playerId = Integer.parseInt(request.params("id"));

            playerDao.deleteById(playerId);

            return gson.toJson(playerDao.getAll());
        });

        get("/games/:id/remove", "application/json",(request, response) -> {
            int gameId = Integer.parseInt(request.params("id"));

            gameDao.deleteById(gameId);

            return gson.toJson(gameDao.getAll());
        });

        //Filters
        after((req, res) ->{
            res.type("application/json");
        });

        //Exception Handling
        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });
    }
}
