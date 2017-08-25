package models;

public class LeftWing extends Player {
    public LeftWing(String firstName, String lastName, Integer playerNumber, String handed){
        super(firstName, lastName, playerNumber, handed);
        this.setPosition("Left Wing");
    }
}
