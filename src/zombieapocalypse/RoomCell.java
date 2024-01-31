import zombieapocalypse.Door;

public class RoomCell implements Cell {
    protected String Name;
    protected Door[] doors;
    protected int nbOfDoors;
    protected int doorCapacity;

    // we need to have a cardinality checker dk where
    public RoomCell(String Name, int doorCapacity) throws doorCapacityException{
        this.Name = Name;
        this.doorCapacity = doorCapacity;
        if(doorCapacity <= 4 && doorCapacity >= 0){
            this.doors = new Door[doorCapacity];
        }
        else{
            throw new doorCapacityException("Door Capacity should be between 0 and 4");
        }
        

    }
    // should really make a cardinality checker
    public boolean doorConstructor(Door door){
        boolean done = false;
        if (nbOfDoors < 4){
            for(int i = 0; i < nbOfDoors; i++){
                if (this.doors[i] == null){
                    this.doors[i] = door;
                    done = true;
                }
                    
                
            }
        } 
        return done;
    }
    @Override
    public String getName() {
        return this.Name;
    }

    //public boolean fullCapacity()
    // getDoor


}
