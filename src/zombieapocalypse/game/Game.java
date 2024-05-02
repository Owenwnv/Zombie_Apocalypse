package zombieapocalypse.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.mapcreation.Map;
import zombieapocalypse.structure.Door;

import zombieapocalypse.cell.Cell;
import zombieapocalypse.cell.EmptyCell;
import zombieapocalypse.cell.HotelRoomCell;
import zombieapocalypse.cell.RoomCell;
import zombieapocalypse.cell.StreetCell;

import zombieapocalypse.item.Item;

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

    protected Input input;

    /**
     * Constructs a Game with the specified map.
     * 
     * @param map The map of the game
     */
    public Game(Map map) {
        this.map = map;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
        this.input = new Input();
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

    public boolean canMove(int i, int j, int direction, boolean lookAround) {
        Cell cell = new EmptyCell();
        int doorIndex = -1;
        String doorDirection = "";

        if (direction == 0 && i > 0) {
            cell = this.map.getCell(i - 1, j);
            doorIndex = 2;
            doorDirection = "on top of you.";
        } else if (direction == 1 && (j + 1) < this.map.getHeight()) {
            cell = this.map.getCell(i, j + 1);
            doorIndex = 3;
            doorDirection = "to your right.";
        } else if (direction == 2 && (i + 1) < this.map.getWidth()) {
            cell = this.map.getCell(i + 1, j);
            doorIndex = 0;
            doorDirection = "under you.";
        } else if (direction == 3 && j > 0) {
            cell = this.map.getCell(i, j - 1);
            doorIndex = 1;
            doorDirection = "to your left.";
        }

        if (cell instanceof EmptyCell) {
            return false;
        } else if (cell instanceof StreetCell) {
            return true;
        } else {
            RoomCell room = (RoomCell) cell;
            Door door = room.getDoors().get(doorIndex);
            if (door.getIsOpen()) {
                if (lookAround) {
                    System.out.println("There is an open door " + doorDirection);
                }
                return true;
            } else {
                if (lookAround) {
                    System.out.println("There is a closed door " + doorDirection);
                }
                return false;
            }
        }
    }

    public void moveZombie(Zombie zombie, int[] coordinates, int direction) {
        int i = 0;
        int j = 0;

        if (direction == 0) {
            i = coordinates[0] - 1;
            j = coordinates[1];
        } else if (direction == 1) {
            i = coordinates[0];
            j = coordinates[1] + 1;
        } else if (direction == 2) {
            i = coordinates[0] + 1;
            j = coordinates[1];
        } else if (direction == 3) {
            i = coordinates[0];
            j = coordinates[1] - 1;
        }

        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        cell.removeZombie(zombie);
        cell = this.map.getCell(i, j);
        cell.addZombie(zombie);
        zombie.setCoordinates(i, j);
    }

    public void moveSurvivor(Survivor survivor, int[] coordinates, int i, int j) {
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        cell.removeSurvivor(survivor);
        cell = this.map.getCell(i, j);
        cell.addSurvivor(survivor);
        survivor.setCoordinates(i, j);
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

    private void printItemList(List<Item> items) {
        Iterator<Item> iterator = items.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            Item item = iterator.next();
            System.out.println(i + ". " + item.getName());
        }
        System.out.println("");
    }

    public void searchRoom(RoomCell room, Survivor survivor) {
        List<Item> roomItems = room.getItems();

        if (roomItems.isEmpty()) {
            System.out.println("There is no item in this room.");
        } else {
            System.out.println("You found:");
            printItemList(roomItems);

            int yesNo = this.input.readYesNo("Do you want to pick up an item ?\n");

            if (yesNo == 1) {
                List<Item> survivorBackpack = survivor.getBackpack();

                if (survivorBackpack.size() < 5) {
                    int itemIndex = this.input.readIntPrompt("What item do you want to pick up ?\n", 0,
                            roomItems.size() - 1);

                    if (itemIndex != -1) {
                        Item item = roomItems.get(itemIndex);
                        room.removeItem(item);
                        survivor.addItemToBackpack(item);
                        System.out.println(item.getName() + " has been added to your backpack.");
                    }
                } else {
                    int yesNoSwitch = this.input.readYesNo(
                            "Your backpack is full. Do you want to switch one of your items for another one ?\n");

                    if (yesNoSwitch == 1) {
                        System.out.println("Here are the items in your backpack:");
                        printItemList(survivorBackpack);

                        int itemToThrowIndex = this.input.readIntPrompt("What item do you want to throw away ?\n", 0,
                                survivorBackpack.size() - 1);
                        Item itemToThrow = survivorBackpack.get(itemToThrowIndex);

                        System.out.println("Here are the items in this room:");
                        printItemList(roomItems);

                        int itemToPickUpIndex = this.input.readIntPrompt("What item do you want to pick up ?\n", 0,
                                roomItems.size() - 1);
                        Item itemToPickUp = roomItems.get(itemToPickUpIndex);

                        survivor.removeItemFromBackpack(itemToThrow);
                        room.addItem(itemToThrow);
                        room.removeItem(itemToPickUp);
                        survivor.addItemToBackpack(itemToPickUp);
                        System.out.println(itemToPickUp.getName() + " has been added to your backpack.");
                    }
                }
            }
        }
    }

    public void putItemInHand(Survivor survivor) {
        List<Item> survivorBackpack = survivor.getBackpack();
        Item itemInHand = survivor.getItemInHand();

        if (itemInHand == null) {
            System.out.println("Here are the items in your backpack:");
            printItemList(survivorBackpack);

            int itemToPutInHandIndex = this.input.readIntPrompt("What item do you want to put in your hand ?\n", 0,
                    survivorBackpack.size() - 1);
            Item itemToPutInHand = survivorBackpack.get(itemToPutInHandIndex);

            survivor.removeItemFromBackpack(itemToPutInHand);
            survivor.putItemInHand(itemToPutInHand);
            System.out.println("You now have " + itemToPutInHand.getName() + " in your hand.");
        } else {
            int yesNoSwitch = this.input.readYesNo(
                    "You already have " + itemInHand.getName()
                            + " in your hand. Do you want to switch the item in your hand for another one ?\n");

            if (yesNoSwitch == 1) {
                System.out.println("Here are the items in your backpack:");
                printItemList(survivorBackpack);

                int itemToPutInHandIndex = this.input.readIntPrompt("What item do you want to put in your hand ?\n", 0,
                        survivorBackpack.size() - 1);
                Item itemToPutInHand = survivorBackpack.get(itemToPutInHandIndex);

                survivor.putItemInHand(itemToPutInHand);
                survivor.removeItemFromBackpack(itemToPutInHand);
                survivor.addItemToBackpack(itemInHand);
                System.out.println("You now have " + itemToPutInHand.getName() + " in your hand.");
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