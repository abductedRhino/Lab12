import java.util.HashMap;
import java.util.ArrayList;


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2016.02.29
 */

/*
 * HashMap für Ausgänge. s.294
 * fields private machen
 * methode getExit(String direction)
 *
 */
public class Room
{
    private HashMap<String,Room> exits;
    private String description;
    private HashMap<String,Item> itemList;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */

    public Room(String description, String item, int itemWeight)
    {
        /*
        this.description = description;
        exits = new HashMap<>();
        this.item = item;
        this.itemWeight = itemWeight;
        */
    }
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        itemList = new HashMap<>();
    }
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    public String getAllExits() {
        String ret = "";
        for (String room : exits.keySet()) {
            ret += room + " ";
        }
        return ret;
    }
    private String getItemString(){
        String ret = "";
        for(String i : itemList.keySet()) {
            ret += i+", ";
        }
        return ret;
    }
    public String getLongDescription() {
        if(itemList.isEmpty()) {


            return "You are " + description + ".\n" + "Your exits are: \n   " + getAllExits();
        } else {
            return "You are " + description + ".\n" + "Your exits are: \n   " + getAllExits() + "\n" + "You see: " +
                    getItemString() + "\n";
        }

    }
    public void addItem(Item item) {
        itemList.put(item.toString(), item);
    }
    public String getItemDescription(String item) {
        return itemList.get(item).getDescription();
    }
}
