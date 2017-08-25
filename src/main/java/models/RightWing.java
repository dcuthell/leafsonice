package models;

public class RightWing extends Player {
    public RightWing(String firstName, String lastName, Integer playerNumber, String handed){
        super(firstName, lastName, playerNumber, handed);
        this.setPosition("Right Wing");
    }
}
