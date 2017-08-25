package models;

public class Game {
    String date;
    String opposingTeam;
    String location;
    Boolean played;
    Integer leafsScore;
    Integer opponentScore;
    Integer id;

    public Game(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!date.equals(game.date)) return false;
        if (!opposingTeam.equals(game.opposingTeam)) return false;
        if (!location.equals(game.location)) return false;
        if (!played.equals(game.played)) return false;
        if (!leafsScore.equals(game.leafsScore)) return false;
        if (!opponentScore.equals(game.opponentScore)) return false;
        return id != null ? id.equals(game.id) : game.id == null;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + opposingTeam.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + played.hashCode();
        result = 31 * result + leafsScore.hashCode();
        result = 31 * result + opponentScore.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
