import java.util.ArrayList;
import java.util.Stack;

public class Player {
    private Room currentRoom;
    private Stack<Room> previousRooms;
    private ArrayList<Item> rucksack;
    private int weightLimit;

    public Player(Room spawnPoint,int weightLimit) {
        currentRoom = spawnPoint;
        this.weightLimit = weightLimit;
        previousRooms = new Stack<>();
        rucksack = new ArrayList<>();


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
}
