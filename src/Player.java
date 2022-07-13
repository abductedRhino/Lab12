import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Player {
    private Room currentRoom;
    private Stack<Room> previousRooms;
    private HashMap<String,Item> rucksack;
    private int weightLimit;

    public Player(Room spawnPoint,int weightLimit) {
        currentRoom = spawnPoint;
        this.weightLimit = weightLimit;
        previousRooms = new Stack<>();
        rucksack = new HashMap<>();


    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getWeightLimit() {
        return weightLimit;
    }
/*
    public Item drop(Item stuff) {
        return
    }

    public Item dropAll() {
        return
    }
*/
    public void moveTo(Room nextRoom) {
        previousRooms.push(currentRoom);
        currentRoom = nextRoom;
    }

    public Room goBack() {
        currentRoom = previousRooms.pop();
        return currentRoom;
    }

    public boolean canGoBack() {
        return !previousRooms.empty();
    }

    public void addItem(Item thing) {
        rucksack.put(thing.toString(), thing);
    }

    private int totalWeight() {
        int total = 0;
        for (String key :
                rucksack.keySet()) {
            total += rucksack.get(key).getWeight();
        }
        return total;
    }
    public boolean canCarry(Item item) {
        return item.getWeight() <= (weightLimit - totalWeight());
    }

    public Item getItem(String name) {
        return rucksack.remove(name);
    }
    public String itemString() {
        if (rucksack.isEmpty()) {
            return "Your rucksack is empty.";
        } else {
            String ret = "You have: \n";
            for (String key :
                    rucksack.keySet()) {
                ret += "   - " + rucksack.get(key)+ "\n";
            }
            return ret;
        }
    }
    public String dropItems() {
        if (rucksack.isEmpty()) {
            return "You don't have anything.";
        } else {
            String ret = "You have thrown away:\n";
            for (String key :
                    rucksack.keySet()) {
                Item thing = rucksack.get(key);
                ret += thing + "\n";
                currentRoom.addItem(thing);
            }
            rucksack.clear();
            return ret;
        }
    }
}
