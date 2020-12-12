package battleship;

public enum ShipType {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUIZER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private String name;
    private int size;

    ShipType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int size() {
        return size;
    }

}
