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
    private Player player1;
    private Room spawn;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
        player1 = new Player(spawn, 20);
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
        Item knife = new Item("knife"," a rusty knive", 3,true);
        Item leash = new Item("leash","a leash", 5,true);
        Item water = new Item("water","greenish dropplets of smelly", 1,true);
        Item food = new Item("food",2,true);
        Item rock = new Item("rock", 1,true);
        Item slingshot = new Item ("slingshot", 4,true);
        Item boots = new Item ("boots","old worn out boots", 2,true);
        Item light = new Item ("flashlight","rusty flashlight", 2,true);
        Item lighter = new Item ("lighter","a zippo lighter", 2,true);
        Item map = new Item ("map","a map of the cave", 1,true);


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

        this.spawn = start;
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
        System.out.println(player1.getCurrentRoom().getLongDescription());

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
            case INSPECT:
                result = inspect(command);
                break;
            case TAKE:
                result = take(command);
                break;
            case DROP:
                result = drop(command);
                break;
        }
        return result ;
    }

    private String take(Command command) {
        if (!command.hasSecondWord()) {
            return "take what?";
        } else {
            Item item = player1.getCurrentRoom().removeItem(command.getSecondWord());
            if (item == null) {
                return "no such thing in this room.";
            } else if (!player1.canCarry(item)) {
                return "it is too heavy. drop something.";
            } else if (!item.canBePickedUp()) {
                return "don't be ridiculous.";
            } else {
                player1.addItem(item);
                return "you picked up " + command.getSecondWord();
            }
        }
    }
    private String drop(Command command) {
        if (!command.hasSecondWord()) {
            return "drop what?";
        } else {
            Item item = player1.getItem(command.getSecondWord());
            if (item == null) {
                return "you can't drop what you don't have.";
            } else {
                player1.getCurrentRoom().addItem(item);
                return "You have thrown out " + item;
            }
        }
    }

    private String inspect(Command command) {
        if (!command.hasSecondWord()) {
            return "inspect what?";
        } else if (!player1.getCurrentRoom().hasItem(command.getSecondWord())) {
            return "no such thing in this room.";
        } else {
            return player1.getCurrentRoom().getItemDescription(command.getSecondWord());
        }
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
        Room nextRoom = player1.getCurrentRoom().getExit(direction);
        String result = "";
        if (nextRoom == null) {
            result += "There is no door!";
        }
        else {
            player1.moveTo(nextRoom);
            result = player1.getCurrentRoom().getLongDescription();
        }
        return result + "\n";
    }

    private String back() {
        if(player1.canGoBack()){
            player1.goBack();
            return player1.getCurrentRoom().getLongDescription()+"\n";
        } else {
            return "Nothing to go back to.";
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
        return player1.getCurrentRoom().getLongDescription();
    }
    private String eat(){
        return new String ("You have eaten now and are not hungry any more");
    }

    private String jump(){
        return new String ("You jumped and almost broke your ankle...");
    }
}

