package models;

public class Goalie extends Player {
    public Goalie(String firstName, String lastName, Integer playerNumber, String handed){
        super(firstName, lastName, playerNumber, handed);
        this.setPosition("Goalie");
    }
}
