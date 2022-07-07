
/**
 * Write a description of class item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private int weight;
    private String description;
    private boolean portable;

    /**
     * Constructor for objects of class item
     */
    public Item(String description, int weight, boolean portable) {
        this.weight = weight;
        this.description = description;
    }

    public boolean canBePickedUp() {
        return portable;
    }
    @Override
    public String toString()
    {   //"Coffee mug, 1kg"
        return description + " (" + weight + " kg)";

    }
}

