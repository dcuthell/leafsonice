package models;

public class Center extends Player {
    public Center(String firstName, String lastName, Integer playerNumber, String handed){
        super(firstName, lastName, playerNumber, handed);
        this.setPosition("Center");
    }
}
