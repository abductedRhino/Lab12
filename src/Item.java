public class Item
{
    private String name;
    private String description;
    private int weight;
    private boolean portable;

    public Item(String name, String description, int weight, boolean portable) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.portable = portable;
    }
    public Item(String name, int weight, boolean portable) {
        this.name = name;
        this.weight = weight;
        this.description = "a "+ name;
        this.portable = portable;
    }

    public boolean canBePickedUp() {
        return portable;
    }
    @Override
    public String toString()
    {   //"Coffee mug, 1kg"
        return name;
    }
    public String getDescription() {
        return description + " (" + weight + " kg)";
    }
    public int getWeight() {
        return weight;
    }
}

