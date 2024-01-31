package zombieapocalypse;

public class StreetCell implements Cell {

    private String name;
    private Street street;

    public StreetCell(String name, Street street) {
        this.name = name;
        this.street = street;
    }

    public String getName() {
        return this.name;
    }

    public Street getStreet() {
        return this.street;
    }

    @Override
    public String toString() {
        return "This is a StreetCell";
    }

}
