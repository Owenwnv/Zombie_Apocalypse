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
    public Game(Map map) {
        this.map = map;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
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

    public boolean canMove(int i, int j, int direction, boolean lookAround) {
        Cell cell = new EmptyCell();
        int[] coordinates = this.map.getCoordinatesFromDirection(i, j, direction);

        if (this.map.cellExists(coordinates[0], coordinates[1])) {
            cell = this.map.getCell(coordinates[0], coordinates[1]);
        }

        String doorDirection = getDirectionString(direction);
        int doorIndex = (direction + 2) % 4;

        if (cell instanceof EmptyCell) {
            return false;
        } else if (cell instanceof StreetCell) {
            return true;
        } else {
            RoomCell room = (RoomCell) cell;
            Door door = room.getDoors().get(doorIndex);
            if (door.getIsOpen()) {
                if (lookAround) {
                    System.out.println("There is an open door " + doorDirection + ".");
                }
                return true;
            } else {
                if (lookAround) {
                    System.out.println("There is a closed door " + doorDirection + ".");
                }
                return false;
            }
        }
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

    public void zombieTurn(Zombie zombie) {
        int[] coordinates = zombie.getCoordinates();
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        Random rand = new Random();
        if (cell.getSurvivors().isEmpty()) {
            int direction = rand.nextInt(4);
            while (true) {
                if (canMove(coordinates[0], coordinates[1], direction, false)) {
                    moveZombie(zombie, coordinates, direction);
                    break;
                }
                direction = rand.nextInt(4);
            }
        } else {
            List<Survivor> survivors = cell.getSurvivors();
            Survivor survivor = survivors.get(rand.nextInt(survivors.size()));
            survivor.decreaseHealthPoints(zombie.getDamage());
            System.out.println(
                    zombie.getName() + " deals " + zombie.getDamage() + " damages to " + survivor.getName() + ".");
        }
    }

    public void survivorTurn(Survivor survivor) {
        int[] coordinates = survivor.getCoordinates();
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        int actionPoints = survivor.getActionPoints();

        // lookAround
        System.out.println(survivor.getName() + " looks around himself.");
        lookAround(cell, coordinates[0], coordinates[1]);

        List<Survivor> survivorsInCell = cell.getSurvivors();
        List<Zombie> zombiesInCell = cell.getZombies();

        survivorsInCell.remove(survivor);

        // if zombie in cell
        if (!zombiesInCell.isEmpty()) {
            // attack zombie
            System.out.println(survivor.getName() + " attacks zombie.");
            actionPoints--;
        }

        // if low hp
        if (survivor.getHealthPoints() < 3) {
            if (survivor instanceof Healer) {
                Healer healer = (Healer) survivor;

                System.out.println(survivor.getName() + " heals himself.");
                healer.heal(healer);
                actionPoints--;
            } else {
                Item item = survivor.getItemInHand();
                List<Item> backpack = survivor.getBackpack();

                if (item != null && item instanceof HealthPotion) {
                    System.out.println(survivor.getName() + " heals himself with health potion.");
                    useToolInHand(survivor, cell);
                } else if (!backpack.isEmpty() && actionPoints > 1) {
                    Iterator<Item> iterator = backpack.iterator();

                    while (iterator.hasNext()) {
                        Item itemInBackpack = iterator.next();
                        if (itemInBackpack instanceof HealthPotion) {
                            putItemInHand(survivor, itemInBackpack);
                            System.out.println(survivor.getName() + " heals himself with health potion.");
                            useToolInHand(survivor, cell);
                            actionPoints -= 2;
                            break;
                        }
                    }
                }
            }
        }

        // if survivor in cell and survivor low hp
        if (!survivorsInCell.isEmpty()) {
            Iterator<Survivor> iterator = survivorsInCell.iterator();

            while (iterator.hasNext()) {
                Survivor survivorInCell = iterator.next();
                if (survivorInCell.getHealthPoints() < 3) {
                    // heal survivor
                    System.out.println(
                            survivor.getName() + " heals " + survivorInCell.getName() + " with health potion.");
                    actionPoints--;
                    break;
                }
            }
        }

        // if no zombie, if room
        if (zombiesInCell.isEmpty() && actionPoints > 0) {
            // move/open door or search room
            if (cell instanceof RoomCell) {
                // search
                System.out.println(survivor.getName() + " searches the room");
            } else {
                // move
                System.out.println(survivor.getName() + " moves.");
            }
            actionPoints--;
        }

        // if ap left
        if (actionPoints > 0) {
            System.out.println(survivor.getName() + " makes noise");
            makeNoise(cell);
            actionPoints--;
        }
    }

    public void lookAround(Cell cell, int i, int j) {
        if (cell instanceof StreetCell) {
            System.out.println("You are in the streets.");
        } else if (cell instanceof RoomCell) {
            System.out.println("You are in a room.");
        } else if (cell instanceof HotelRoomCell) {
            System.out.println("You are at the Hotel.");
        } else {
            System.out.println("You are at the Pharmacy.");
        }

        canMove(i, j, 0, true);
        canMove(i, j, 1, true);
        canMove(i, j, 2, true);
        canMove(i, j, 3, true);

        System.out.println("There are " + cell.getSurvivors().size() + " survivors in this cell including you.");
        System.out.println("There are " + cell.getZombies().size() + " zombies in this cell.");
    }

    public void searchRoom(Survivor survivor, RoomCell room) {
        List<Item> roomItems = room.getItems();

        if (roomItems.isEmpty()) {
            System.out.println("There is no item in this room.");
        } else {
            List<Item> survivorBackpack = survivor.getBackpack();

            if (survivorBackpack.size() < 5) {
                Item item = roomItems.get(0);
                room.removeItem(item);
                survivor.addItemToBackpack(item);
                System.out.println(item.getName() + " has been added to your backpack.");
            } else {
                Item itemToThrow = survivorBackpack.get(0);
                Item itemToPickUp = roomItems.get(0);

                survivor.removeItemFromBackpack(itemToThrow);
                room.addItem(itemToThrow);
                room.removeItem(itemToPickUp);
                survivor.addItemToBackpack(itemToPickUp);
                System.out.println(itemToPickUp.getName() + " has been added to your backpack.");
            }
        }
    }

    public void putItemInHand(Survivor survivor, Item itemToPutInHand) {
        Item itemInHand = survivor.getItemInHand();

        if (itemInHand == null) {
            survivor.removeItemFromBackpack(itemToPutInHand);
            survivor.putItemInHand(itemToPutInHand);
            System.out.println(survivor.getName() + " puts " + itemToPutInHand.getName() + " in his hand.");
        } else {
            survivor.putItemInHand(itemToPutInHand);
            survivor.removeItemFromBackpack(itemToPutInHand);
            survivor.addItemToBackpack(itemInHand);
            System.out.println(survivor.getName() + " puts " + itemToPutInHand.getName() + " in his hand and puts "
                    + itemInHand.getName() + " in his backpack.");
        }
    }

    public void useToolInHand(Survivor survivor, Cell cell) {
        Item itemInHand = survivor.getItemInHand();

        if (itemInHand instanceof HealthPotion) {
            survivor.increaseHealthPoints(1);
            survivor.putItemInHand(null);
        } else if (itemInHand instanceof MedKit) {
            List<Survivor> survivors = cell.getSurvivors();
            survivors.remove(survivor);

            Survivor survivorToHeal = survivors.get(0);
            survivorToHeal.increaseHealthPoints(1);
            System.out.println("You have healed 1 hp to " + survivorToHeal.getName() + ".");
            survivor.putItemInHand(null);
        } else if (itemInHand instanceof HandheldMap) {
            this.map.showMap();
            survivor.putItemInHand(null);
        } else if (itemInHand instanceof InfraredGlasses) {
            int[] coordinates = survivor.getCoordinates();
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

    public void makeNoise(Cell cell) {
        cell.increaseNoiseLevel();
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

        if (id == 0) {
            return new Fighter(name);
        } else if (id == 1) {
            return new Healer(name);
        } else if (id == 2) {
            return new Lucky(name);
        } else {
            return new Searcher(name);
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

    /**
     * Returns the map of the game.
     * 
     * @return The map of the game
     */
    public Map getMap() {
        return this.map;
    }
}