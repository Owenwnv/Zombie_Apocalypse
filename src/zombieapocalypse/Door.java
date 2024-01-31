package zombieapocalypse;

public class Door {
    private boolean isOpen;
    private Cardinal cardinal;

    public Door(boolean isOpen, Cardinal cardinal) {
        this.isOpen = isOpen;
        this.cardinal = cardinal;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public Cardinal getCardinal() {
        return this.cardinal;
    }

}
