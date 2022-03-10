package textadventure.Game;
import java.util.Stack;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private CLS cls_var;
    public Game() {
        parser = new Parser();
        player = new Player();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.setupGame();
        game.play();
    }
    public void printInformation() {
        
        System.out.println(currentRoom.getShortDescription());
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.getExitString());
        System.out.println(currentRoom.getInventoryString());
        System.out.println(player.getInventoryString());
    }
    
    public void setupGame() {
        //Environment of the Game.
    	
        Room firstRoom = new Room("first name","Front","Ahh the Front of the Room. This is where your journey begins."); 
        Room secondRoom = new Room("second name","Living Room","You have entered the Living Room. I guess you need to add some funiture. That Bookshelf looks nice though."); 
        Room thirdRoom = new Room("third name","Kitchen","You have entered the kitchen. Want to cook?(Can't cook sorry :/ )"); 
        Room fourthRoom = new Room("fourth name","Hidden Basement","third row long");
        
        Item itemExample = new Item("key", "It is a...normal key?");
        Item itemExample2 = new Item("book", "This belongs in the book shelf apperently.");
        Item itemExample3 = new Item("box", "It is a....box...? It is sealed...");
        Item itemExample4 = new Item("knife", "It is a....knife?!?! It looks really rusty.");
        
        firstRoom.setExit("second", secondRoom); 
        secondRoom.setExit("third", thirdRoom); 
        thirdRoom.setExit("fourth", fourthRoom);
        thirdRoom.setExit("second", secondRoom);
        secondRoom.setExit("first", firstRoom);
        fourthRoom.setExit("third", thirdRoom);
        
        firstRoom.setItem("key", itemExample);
        secondRoom.setItem("book", itemExample2);
        thirdRoom.setItem("box", itemExample3);
        fourthRoom.setItem("knife", itemExample4);
        
        try {
            //cls_var.main(); 
       }catch(Exception e) {
            System.out.println(e); 
       }
        
        currentRoom = firstRoom;
        printInformation();
        
    }
    
    public void play() {
    while(true) {            
        Command command = parser.getCommand(); 
        try {
            cls_var.main(); 
        }catch(Exception e) {
            System.out.println(e); 
        }
        processCommand(command);
        printInformation();   
    }
    }
        
    public void processCommand(Command command) {
        String commandWord = command.getCommandWord().toLowerCase();
        
        switch(commandWord) {
            case  "speak":
                System.out.println("you wanted me to speak this word," + "\"" + command.getSecondWord() + "\"");
                break;
            case "go":
                goRoom(command);
                break;
            case "grab":
                grab(command);
                break;
            case "drop":
                drop(command);
                break;
            case "look":
                look(command);
                break;
            case "use":
                use(command);
                break;
            case "back":
                back(command);
                break;
            case "help":
            	help(command);
            	break;
                
            
        }
        }
    
    public void help(Command command) {
		String assist = " ";
		Room nextRoom;
		
		
		
	}

	public void use(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("use what?");
        }
        String use = command.getSecondWord();
        
    }
        
    public void grab(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("grab what?");
        }
        String item = command.getSecondWord();
        Item itemToGrab = currentRoom.removeItem(item);
        if (!command.hasLine()) {
            item = command.getSecondWord();
        }
        else if (command.hasLine()){
            item = command.getSecondWord()+command.getLine();
        }
        if(itemToGrab == null) {
            System.out.println ("You can not grab that");
        }
        else {
            player.setItem(item, itemToGrab);
        }
    }
    
    public void look(Command command) {
        String printString = "Looking at ";
        String thingToLook = null;
        if(!command.hasSecondWord()) {
            System.out.println("look at what?");
            return;
        }
        thingToLook = null;
        
        if (!command.hasLine()) {
            thingToLook = command.getSecondWord();
        }
        else if (command.hasLine()){
            thingToLook = command.getSecondWord()+command.getLine();
        }
        //it could be an item in the room or player inventory to see if it is an item.
        
        if(thingToLook.equals(currentRoom.getName())) {
            printString += "the room" + currentRoom.getName() + "\n" + currentRoom.getLongDescription();
        }
        else if (currentRoom.getItem(thingToLook) != null) {
            printString += "the item: " + currentRoom.getItem(thingToLook).getName() + "\n" + currentRoom.getItem(thingToLook).getDescription();
        }
        else if (player.getItem(thingToLook) != null) {
            printString += "the item: " + player.getItem(thingToLook).getName() + "\n" + player.getItem(thingToLook).getDescription();
        }
        
        else {
            printString += "\nYou cannot look at that";
        }
        System.out.println(printString);
    }
    
    public void drop(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("drop what?");
            return;
        }
        String item = command.getSecondWord();
        Item itemToDrop = player.removeItem(item);
        if (!command.hasLine()) {
            item = command.getSecondWord();
        }
        else if (command.hasLine()){
            item = command.getSecondWord()+command.getLine();
        }
        if(itemToDrop == null) {
            System.out.println ("You can not drop that");
            return;
        }
        else {
            currentRoom.setItem(item, itemToDrop);
            
        }
    }
    
    
    
    public void goRoom(Command command) {
        String direction = " ";
        if(!command.hasSecondWord()) {
            System.out.println("go where?");
        }
    
        
        if (!command.hasLine()) {
            direction = command.getSecondWord();
        }
        else if (command.hasLine()){
            direction = command.getSecondWord()+command.getLine();
        }
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null) {
            System.out.println ("You can not go there");
        }
        else {
            currentRoom = nextRoom;
        } 
        
    }
    
    public void back(Command command) {
        String direction = " ";
        if (!command.hasLine()) {
            direction = command.getSecondWord();
        }
        else if (command.hasLine()){
            direction = command.getLine();
        }
        Room nextRoom = currentRoom.getExit(direction);
        if(currentRoom == null) {
            System.out.println ("You can not go back there");
        }
        else {
            currentRoom = nextRoom;
        }
        
    }
    
    
    }
