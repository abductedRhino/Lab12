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
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go",CommandWord.GO);
        validCommands.put("look",CommandWord.LOOK);
        validCommands.put("eat",CommandWord.EAT);
        validCommands.put("help",CommandWord.HELP);
        //validCommands.put("?",CommandWord.UNKNOWN);
        validCommands.put("quit",CommandWord.QUIT);
        validCommands.put("jump",CommandWord.JUMP);
        validCommands.put("back",CommandWord.BACK);
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
    public CommandWord getCommand(String word){
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
