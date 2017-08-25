package models;

public class Player {
    private String firstName;
    private String lastName;
    private Integer playerNumber;
    private String handed;
    private String position;
    private Integer id;

    public Player(String firstName, String lastName, Integer playerNumber, String handed){
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerNumber = playerNumber;
        this.handed = handed;
        this.position = "Bench";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getHanded() {
        return handed;
    }

    public void setHanded(String handed) {
        this.handed = handed;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

        Player player = (Player) o;

        if (!firstName.equals(player.firstName)) return false;
        if (!lastName.equals(player.lastName)) return false;
        if (!playerNumber.equals(player.playerNumber)) return false;
        if (!handed.equals(player.handed)) return false;
        if (position != null ? !position.equals(player.position) : player.position != null) return false;
        return id != null ? id.equals(player.id) : player.id == null;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + playerNumber.hashCode();
        result = 31 * result + handed.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
