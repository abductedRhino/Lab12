
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
    UNKNOWN("?"),
    JUMP("jump"),
    BACK("back"),
    INSPECT("inspect"),
    TAKE("take");
    private String enumString;

    Enum(String enumString) {
        this.enumString = enumString;
    }
    @Override
    public String toString() {
        return enumString;
    }

}
