package models;

public class Defense extends Player {
    public Defense(String firstName, String lastName, Integer playerNumber, String handed){
        super(firstName, lastName, playerNumber, handed);
        this.setPosition("Defense");
    }
}
