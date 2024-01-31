package zombieapocalypse;

public class Street {

    private String name;
    private boolean hasSewer;

    public Street(String name, boolean hasSewer) {
        this.name = name;
        this.hasSewer = hasSewer;
    }

    public boolean getHasSewer() {
        return this.hasSewer;
    }

    public String getName() {
        return this.name;
    }
}
