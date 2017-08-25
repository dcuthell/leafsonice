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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!gameDate.equals(game.gameDate)) return false;
        if (!opposingTeam.equals(game.opposingTeam)) return false;
        if (!location.equals(game.location)) return false;
        if (played != null ? !played.equals(game.played) : game.played != null) return false;
        if (leafsScore != null ? !leafsScore.equals(game.leafsScore) : game.leafsScore != null) return false;
        if (opponentScore != null ? !opponentScore.equals(game.opponentScore) : game.opponentScore != null)
            return false;
        return id != null ? id.equals(game.id) : game.id == null;
    }

    @Override
    public int hashCode() {
        int result = gameDate.hashCode();
        result = 31 * result + opposingTeam.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + (played != null ? played.hashCode() : 0);
        result = 31 * result + (leafsScore != null ? leafsScore.hashCode() : 0);
        result = 31 * result + (opponentScore != null ? opponentScore.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
