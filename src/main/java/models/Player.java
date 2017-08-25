package models;

public class Player {
    String firstName;
    String lastName;
    Integer number;
    String position;
    Integer id;

    public Player(){

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        if (!number.equals(player.number)) return false;
        if (!position.equals(player.position)) return false;
        return id != null ? id.equals(player.id) : player.id == null;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
