import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */


public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String, Enum> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", Enum.GO);
        validCommands.put("look", Enum.LOOK);
        validCommands.put("eat", Enum.EAT);
        validCommands.put("help", Enum.HELP);
        //validCommands.put("?",CommandWord.UNKNOWN);
        validCommands.put("quit", Enum.QUIT);
        validCommands.put("jump", Enum.JUMP);
        validCommands.put("back", Enum.BACK);
        validCommands.put("inspect", Enum.INSPECT);
    }

    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }
    public Enum getCommand(String word){
        return validCommands.get(word);
    }
    @Override
    public String toString() {
        String ret = "";
        for (String command : validCommands.keySet()) {
            ret+= command + " ";
        }
        return ret;
    }
}
