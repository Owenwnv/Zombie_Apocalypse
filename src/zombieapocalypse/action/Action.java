package zombieapocalypse.action;

public abstract class Action {
    protected int cost;

    public Action(int cost) {
        this.cost = cost;
    }

    public abstract void effect();
}
