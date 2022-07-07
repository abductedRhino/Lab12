import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
/*
 * nextRoom = currentRoom.eastExit -> nextRoom = currentRoom.getExit("east") s.295
 *
 * goRoom methode: Room nextRoom = currentRoom.getExit(direction)
 *
 * methode, printLocationInfo s.292
 * methoden printWelcome und goRoom sollen darauf zugreifen
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> previousRooms;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
        previousRooms = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room start, enclosure, den, cave, pit;

        // create the rooms
        start = new Room("outside the jungle");
        enclosure = new Room("in an empty rhino enclosure");
        den = new Room("in a poachers den");
        cave = new Room("in a cave");
        pit = new Room("in a pit of snakes");

        // initialise room exits
        start.setExit("east", enclosure);
        start.setExit("south", cave);
        start.setExit("west", den);
        enclosure.setExit("west", start);
        den.setExit("east", start);
        cave.setExit("north", start);
        cave.setExit("east", pit);
        pit.setExit("west", cave);

        //initialize items
        Item knife = new Item("knife", 3,true);
        Item leash = new Item("leash", 5,true);
        Item water = new Item("water", 1,true);
        Item food = new Item("food",2,true);
        Item rock = new Item("rock", 1,true);
        Item slingshot = new Item ("slingshot", 4,true);
        Item boots = new Item ("old worn out boots", 2,true);
        Item light = new Item ("rusty flashlight", 2,true);
        Item lighter = new Item ("a zippo lighter", 2,true);
        Item map = new Item ("a map of the cave", 1,true);


        //add items to specific rooms

        cave.addItem(food);
        cave.addItem(water);

        enclosure.addItem(lighter);
        enclosure.addItem(knife);

        den.addItem(slingshot);

        start.addItem(light);
        start.addItem(map);
        start.addItem(boots);
        start.addItem(rock);

        pit.addItem(leash);




        currentRoom = start;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            String output = processCommand(command);
            finished = (null == output);
            if (!finished)
            {
                System.out.println(output);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * This is a further method added by BK to
     * provide a clearer interface that can be tested:
     * Game processes a commandLine and returns output.
     * @param commandLine - the line entered as String
     * @return output of the command
     */
    public String processCommand(String commandLine){
        Command command = parser.getCommand(commandLine);
        return processCommand(command);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());

    }
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private String processCommand(Command command) {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            return "I don't know what you mean...";
        }
        String result = null;
        Enum anEnum = parser.getEnum(command.getFirstWord());
        switch(anEnum) {
            case HELP :
                result = printHelp();
                break;
            case GO :
                result = goRoom(command);
                break;
            case EAT :
                result = eat();
                break;
            case LOOK:
                result = look();
                break;
            case QUIT:
                result = quit(command);
                break;
            case JUMP:
                result = jump();
            case BACK:
                result = back();
                break;
        }

        /*
        if (commandWord.equals("help")) {
            result = printHelp();
        }
        else if (commandWord.equals("go")) {
            result = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            result = quit(command);
        }
        else if (commandWord.equals("look")){
            result = look();
        }
        else if (commandWord.equals("eat")){
            result = eat();
        }
        else if (commandWord == CommandWord.JUMP){
            result = jump();
        }
        */
        return result ;
    }


    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private String printHelp()
    {
        return "You are lost. You are alone. You wander"
                +"\n"
                + "around at the university."
                +"\n"
                +"\n"
                +"Your command words are:"
                +"\n"
                +"   "+parser.showCommands()
                +"\n";
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private String goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        String result = "";
        if (nextRoom == null) {
            result += "There is no door!";
        }
        else {
            previousRooms.push(currentRoom);
            currentRoom = nextRoom;
            result = currentRoom.getLongDescription();
        }
        return result + "\n";
    }

    private String back() {
        if(previousRooms.empty()){
            return "Nothing to go back to.";
        } else {
            currentRoom = previousRooms.pop();
            return currentRoom.getLongDescription()+"\n";
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private String quit(Command command)
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }
    private String look()
    {
        return currentRoom.getLongDescription();
    }
    private String eat(){
        return new String ("You have eaten now and are not hungry any more");
    }
    private String jump(){
        return new String ("You jumped and almost broke your ankle...");
    }
}

