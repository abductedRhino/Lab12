
/**
 * Write a description of class item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private int weight;
    private String name;

    /**
     * Constructor for objects of class item
     */
    public Item(String name, int weight)
    {
        this.weight = weight;
        this.name = name;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     *
     * @return    the sum of x and y
     */
    @Override
    public String toString()
    {   //"Coffee mug, 1kg"
        return name + "(" + weight + " kg)";

    }
}

