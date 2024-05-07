package zombieapocalypse.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import zombieapocalypse.actor.survivor.Fighter;
import zombieapocalypse.actor.survivor.Healer;
import zombieapocalypse.actor.survivor.Lucky;
import zombieapocalypse.actor.survivor.Searcher;
import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Abomination;
import zombieapocalypse.actor.zombie.Bigboy;
import zombieapocalypse.actor.zombie.Runner;
import zombieapocalypse.actor.zombie.Walker;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.mapcreation.Map;
import zombieapocalypse.structure.Door;

import zombieapocalypse.cell.Cell;
import zombieapocalypse.cell.EmptyCell;
import zombieapocalypse.cell.HotelRoomCell;
import zombieapocalypse.cell.RoomCell;
import zombieapocalypse.cell.StreetCell;

import zombieapocalypse.item.Item;
import zombieapocalypse.item.tool.HandheldMap;
import zombieapocalypse.item.tool.HealthPotion;
import zombieapocalypse.item.tool.InfraredGlasses;
import zombieapocalypse.item.tool.MedKit;
import zombieapocalypse.item.tool.SkeletonKey;
import zombieapocalypse.item.weapon.Gun;
import zombieapocalypse.item.weapon.Weapon;

/**
 * Represents the game engine.
 */
public class Game {
    /**
     * the map
     */
    protected Map map;

    /**
     * List of survivors in the Game.
     */
    protected List<Survivor> survivors;

    /**
     * List of zombies in the Game.
     */
    protected List<Zombie> zombies;

    protected int zombieID;

    /**
     * Constructs a Game with the specified map.
     * 
     * @param map The map of the game
     */
    public Game(Map map, int NbOfSurvivors) {
        this.map = map;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
        spawnSurvivors(NbOfSurvivors);
        spawnInitialZombies();
    }

    /**
     * Spawns a survivor at the specified coordinates.
     * 
     * @param survivor The survivor to spawn
     * @param i        The x-coordinate
     * @param j        The y-coordinate
     */
    public void spawnSurvivor(Survivor survivor, int i, int j) {
        this.survivors.add(survivor);
        this.map.getCell(i, j).addSurvivor(survivor);
        survivor.setCoordinates(i, j);
    }

    /**
     * Spawns a zombie at the specified coordinates.
     * 
     * @param zombie The zombie to spawn
     * @param i      The x-coordinate
     * @param j      The y-coordinate
     */
    public void spawnZombie(Zombie zombie, int i, int j) {
        this.zombies.add(zombie);
        this.map.getCell(i, j).addZombie(zombie);
        zombie.setCoordinates(i, j);
    }

    public void moveZombie(Zombie zombie, int[] coordinates, int direction) {
        int[] newCoordinates = this.map.getCoordinatesFromDirection(coordinates[0], coordinates[1], direction);
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);

        cell.removeZombie(zombie);
        cell = this.map.getCell(newCoordinates[0], newCoordinates[1]);
        cell.addZombie(zombie);
        zombie.setCoordinates(newCoordinates[0], newCoordinates[1]);
    }

    public void moveSurvivor(Survivor survivor, int[] coordinates, int direction) {
        int[] newCoordinates = this.map.getCoordinatesFromDirection(coordinates[0], coordinates[1], direction);
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);

        cell.removeSurvivor(survivor);
        cell = this.map.getCell(newCoordinates[0], newCoordinates[1]);
        cell.addSurvivor(survivor);
        survivor.setCoordinates(newCoordinates[0], newCoordinates[1]);
    }

    public boolean canMove(int i, int j, int direction, boolean lookAround) {
        Cell cell = this.map.getCell(i, j);
        Cell targetCell = new EmptyCell();
        String doorDirection = getDirectionString(direction);
        int[] coordinates = this.map.getCoordinatesFromDirection(i, j, direction);

        if (this.map.cellExists(coordinates[0], coordinates[1])) {
            targetCell = this.map.getCell(coordinates[0], coordinates[1]);
        }

        if (targetCell instanceof EmptyCell) {
            return false;
        } else if (this.map.hasClosedDoorBetween(cell, targetCell, direction)) {
            if (lookAround) {
                System.out.println("There is a closed door " + doorDirection + ".");
            }
            return false;
        } else {
            return true;
        }
    }

    public void zombieTurn(Zombie zombie) {
        int[] coordinates = zombie.getCoordinates();
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        Random rand = new Random();
        if (cell.getSurvivors().isEmpty()) {
            int direction = rand.nextInt(4);
            while (true) {
                if (canMove(coordinates[0], coordinates[1], direction, false)) {
                    moveZombie(zombie, coordinates, direction);
                    System.out.println(zombie.getName() + " moves.");
                    break;
                }
                direction = rand.nextInt(4);
            }
        } else {
            List<Survivor> survivors = cell.getSurvivors();
            Survivor survivor = survivors.get(rand.nextInt(survivors.size()));
            zombie.attackSurvivor(survivor);
        }
    }

    public void survivorTurn(Survivor survivor) {
        int[] coordinates = survivor.getCoordinates();
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        int actionPoints = survivor.getActionPoints();

        System.out.println(survivor.getName() + " looks around himself.");
        lookAround(cell, coordinates[0], coordinates[1]);

        List<Survivor> survivorsInCell = cell.getSurvivors();
        List<Zombie> zombiesInCell = cell.getZombies();
        List<Survivor> survivorsInCellCopy = new ArrayList<>(survivorsInCell);
        survivorsInCellCopy.remove(survivor);

        if (!zombiesInCell.isEmpty()) {
            // attack zombie
            System.out.println(survivor.getName() + " attacks zombie.");
            actionPoints--;
        } else {
            survivor.makeNoise(cell);
            actionPoints--;
        }

        if (survivor.getHealthPoints() < 3) {
            actionPoints = survivor.healSelf(actionPoints);
        }

        if (!survivorsInCell.isEmpty()) {
            Iterator<Survivor> iterator = survivorsInCellCopy.iterator();

            while (iterator.hasNext()) {
                Survivor survivorInCell = iterator.next();
                if (survivorInCell.getHealthPoints() < 3) {
                    actionPoints = survivor.healSurvivor(survivorInCell, actionPoints);
                    break;
                }
            }
        }

        if (zombiesInCell.isEmpty() && actionPoints > 0) {
            Random rand = new Random();
            int decision = rand.nextInt(2);

            if (cell instanceof RoomCell && decision == 0) {
                RoomCell room = (RoomCell) cell;
                System.out.println(survivor.getName() + " searches the room.");
                survivor.searchRoom(room);
                actionPoints--;
            } else {
                while (true) {
                    int direction = rand.nextInt(4);
                    int[] coordinatesTarget = this.map.getCoordinatesFromDirection(coordinates[0], coordinates[1],
                            direction);

                    if (this.map.cellExists(coordinatesTarget[0], coordinatesTarget[1])) {
                        Cell targetCell = this.map.getCell(coordinatesTarget[0], coordinatesTarget[1]);
                        if (this.map.hasClosedDoorBetween(cell, targetCell, direction)) {
                            if (survivor.hasDoorItemInHand()) {
                                Item inHand = survivor.getItemInHand();
                                if (inHand instanceof SkeletonKey) {
                                    survivor.putItemInHand(null);
                                } else {
                                    cell.increaseNoiseLevel();
                                }
                                System.out.println(survivor.getName() + " opens door with " + inHand.getName() + ".");
                                actionPoints--;
                                if (actionPoints > 0) {
                                    moveSurvivor(survivor, coordinates, direction);
                                    actionPoints--;
                                }
                                break;
                            }
                        } else {
                            moveSurvivor(survivor, coordinates, direction);
                            actionPoints--;
                        }
                    }
                }
                System.out.println(survivor.getName() + " moves.");
            }
        }
    }

    public void lookAround(Cell cell, int i, int j) {
        if (cell instanceof StreetCell) {
            System.out.println("You are in the streets.");
        } else if (cell instanceof RoomCell) {
            System.out.println("You are in a room.");
        } else if (cell instanceof HotelRoomCell) {
            System.out.println("You are at the Continental”.");
            System.out.println("You can't see who is in this cell”.");
        } else {
            System.out.println("You are at the Pharmacy.");
        }

        canMove(i, j, 0, true);
        canMove(i, j, 1, true);
        canMove(i, j, 2, true);
        canMove(i, j, 3, true);

        if (!(cell instanceof HotelRoomCell)) {
            System.out.println("There are " + cell.getSurvivors().size() + " survivors in this cell including you.");
            System.out.println("There are " + cell.getZombies().size() + " zombies in this cell.");
        }
    }

    public void useToolInHand(Survivor survivor, Cell cell) {
        Item itemInHand = survivor.getItemInHand();

        if (itemInHand instanceof HandheldMap) {
            System.out.println(survivor.getName() + " uses handheld map.");
            this.map.showMap();
            cell.increaseNoiseLevel();
            survivor.putItemInHand(null);
        } else if (itemInHand instanceof InfraredGlasses) {
            int[] coordinates = survivor.getCoordinates();
            System.out.println(survivor.getName() + " uses infrared glasses.");
            infraredGlasses(coordinates[0], coordinates[1]);
        }
    }

    public void infraredGlasses(int i, int j) {
        for (int direction = 0; i < 4; i++) {
            int[] coordinates = this.map.getCoordinatesFromDirection(i, j, direction);
            if (this.map.cellExists(coordinates[0], coordinates[1])) {
                Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
                System.out.println("The cell " + getDirectionString(direction) + " has " + cell.getSurvivors().size()
                        + " survivors and " + cell.getZombies().size() + " zombies.");
            } else {
                System.out.println("There is no cell " + getDirectionString(direction) + ".");
            }
        }
    }

    public int getGlobalExperiencePoints() {
        int globalExperiencePoints = 0;
        Iterator<Survivor> iterator = this.survivors.iterator();

        while (iterator.hasNext()) {
            Survivor survivor = iterator.next();
            globalExperiencePoints += survivor.getExperiencePoints();
        }
        return globalExperiencePoints;
    }

    public int getInitialZombieSpawnRate() {
        return this.map.getHeight() + this.map.getWidth();
    }

    public int getZombieSpawnRate() {
        return (int) Math.ceil((double) (getGlobalExperiencePoints() / this.survivors.size()) / 3);
    }

    public String getDirectionString(int direction) {
        if (direction == 0) {
            return "on top of you";
        } else if (direction == 1) {
            return "to your right";
        } else if (direction == 2) {
            return "under you";
        } else {
            return "to your left";
        }
    }

    public Zombie createZombie(int zombieID) {
        int id = zombieID % 4;

        if (id == 0) {
            return new Abomination(zombieID);
        } else if (id == 1) {
            return new Bigboy(zombieID);
        } else if (id == 2) {
            return new Runner(zombieID);
        } else {
            return new Walker(zombieID);
        }
    }

    public Survivor createSurvivor(int survivorID) {
        int id = zombieID % 4;
        String name = "Player#" + survivorID;
        Gun gun = new Gun();

        if (id == 0) {
            return new Fighter(name, gun);
        } else if (id == 1) {
            return new Healer(name, gun);
        } else if (id == 2) {
            return new Lucky(name, gun);
        } else {
            return new Searcher(name, gun);
        }
    }

    public void spawnSurvivors(int numberOfSurvivors) {
        int[] coordinates = this.map.getMainroads();

        for (int i = 0; i < numberOfSurvivors; i++) {
            Survivor survivor = createSurvivor(i);
            spawnSurvivor(survivor, coordinates[0], coordinates[1]);
        }
    }

    public void spawnInitialZombies() {
        int spawnRate = getInitialZombieSpawnRate();

        while (spawnRate != 0) {
            int[] coordinates = this.map.getRandomCellCoordinates();
            Cell cell = this.map.getCell(coordinates[0], coordinates[1]);

            if (cell instanceof StreetCell) {
                Zombie zombie = createZombie(this.zombieID);
                spawnZombie(zombie, coordinates[0], coordinates[1]);
                this.zombieID += 1;
                spawnRate -= 1;
            }
        }
    }

    public void checkZombieDeath(Survivor survivor) {
        Iterator<Zombie> iterator = this.zombies.iterator();

        while (iterator.hasNext()) {
            Zombie zombie = iterator.next();
            if (zombie.getHealthPoints() <= 0) {
                System.out.println(survivor.getName() + " killed " + zombie.getName() + ".");
                int[] coordinates = zombie.getCoordinates();
                Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
                cell.removeZombie(zombie);
                this.zombies.remove(zombie);
                survivor.increaseExperiencePoints();
            }
        }
    }

    public void checkSurvivorDeath(Zombie zombie) {
        Iterator<Survivor> iterator = this.survivors.iterator();

        while (iterator.hasNext()) {
            Survivor survivor = iterator.next();
            if (survivor.getHealthPoints() <= 0) {
                System.out.println(zombie.getName() + " killed " + survivor.getName() + ".");
                int[] coordinates = survivor.getCoordinates();
                Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
                cell.removeSurvivor(survivor);
                this.survivors.remove(survivor);
                if (cell instanceof RoomCell) {
                    RoomCell room = (RoomCell) cell;
                    Item item = survivor.getItemInHand();
                    if (item != null) {
                        room.addItem(item);
                    }
                    Iterator<Item> itemIterator = survivor.getBackpack().iterator();

                    while (itemIterator.hasNext()) {
                        item = itemIterator.next();
                        room.addItem(item);
                    }
                }
            }
        }
    }

    public void gameLoop(int iteration) {
        for (int i = 0; i < iteration; i++) {
            System.out.println("********** Round " + i + " **********");
            System.out.println("");
            Iterator<Survivor> iterator = this.survivors.iterator();

            while (iterator.hasNext()) {
                Survivor survivor = iterator.next();
                System.out.println("Turn of " + survivor.getName() + ":");
                survivorTurn(survivor);
                checkZombieDeath(survivor);
                System.out.println("");
            }
            System.out.println("");

            Iterator<Zombie> iterator2 = this.zombies.iterator();

            while (iterator2.hasNext()) {
                Zombie zombie = iterator2.next();
                zombieTurn(zombie);
                checkSurvivorDeath(zombie);
            }
            System.out.println("");

            this.map.showMap();
            this.map.resetMapNoiseLevel();
        }
    }

    /**
     * Returns the map of the game.
     * 
     * @return The map of the game
     */
    public Map getMap() {
        return this.map;
    }
}