package zombieapocalypse;
public class NormalCell implements Cell{

    private String name;

    public NormalCell() {
        this.name = "Normal";
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "This is a NormalCell ";
    }
}