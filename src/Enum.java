
/**
 * Enumeration class CommandWord - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Enum
{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    EAT("eat"),
    LOOK("look"),
    JUMP("jump"),
    BACK("back"),
    INSPECT("inspect"),
    TAKE("take"),
    DROP("drop"),

    ITEM("item"),
    UNKNOWN("?");
    private String enumString;

    Enum(String enumString) {
        this.enumString = enumString;
    }
    @Override
    public String toString() {
        return enumString;
    }

    public Enum getEnum(String enumString) {
        return Enum.valueOf(enumString);
    }

}
