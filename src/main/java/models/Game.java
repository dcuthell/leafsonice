package models;

public class Game {
    private String gameDate;
    private String opposingTeam;
    private String location;
    private Boolean played;
    private Integer leafsScore;
    private Integer opponentScore;
    private Integer id;

    public Game(String gameDate, String opposingTeam, String location, Integer leafsScore, Integer opponentScore){
        this.gameDate = gameDate;
        this.opposingTeam = opposingTeam;
        this.location = location;
        this.played = true;
        this.leafsScore = leafsScore;
        this.opponentScore = opponentScore;
    }

    public Game(String gameDate, String opposingTeam, String location){
        this.gameDate = gameDate;
        this.opposingTeam = opposingTeam;
        this.location = location;
        this.played = false;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getOpposingTeam() {
        return opposingTeam;
    }

    public void setOpposingTeam(String opposingTeam) {
        this.opposingTeam = opposingTeam;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public Integer getLeafsScore() {
        return leafsScore;
    }

    public void setLeafsScore(Integer leafsScore) {
        this.leafsScore = leafsScore;
    }

    public Integer getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(Integer opponentScore) {
        this.opponentScore = opponentScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
