public class Item
{
    private int weight;
    private String description;
    private boolean portable;
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

