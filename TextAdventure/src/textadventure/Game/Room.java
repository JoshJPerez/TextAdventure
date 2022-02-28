package textadventure.Game;
import java.util.HashMap;
import java.util.Set;

public class Room {
    private String name;
    private String shortDescription;
    private String longDescription;
    private String description;
    
    private HashMap<String, Room> exits;
    private HashMap<String, Item> inventory;
    
    
    
    public Room(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        
        exits = new HashMap<>();
        inventory = new HashMap<>();
    }
    
    public void setItem(String name, Item item) {
        inventory.put(name, item);
    }
    
    public Item removeItem(String key) {
        return inventory.remove(key);
    }
    
    public Item getItem(String key){
        return inventory.get(key);
    }
    
    public String getName() {
        return name;
    }
    
    
    public String getShortDescription() {
        return shortDescription;
        
    }
    
    public String getLongDescription() {
        return longDescription;
    }
    
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    public String getExitString() {
        String returnString = "\nExits ";
        
        Set<String>keys = exits.keySet();
        for (String exit: keys) {
            returnString += "\"" + exit + "\" " ;
            
        }
        return returnString;
    }
    
    public String getInventoryString() {
        String returnString = "Room Inventory";
        Set<String> keys = inventory.keySet();
        for(String name: keys) {
            returnString += "\"" + keys + "\" ";
        }
        return returnString;
    }
    
    public String getDescription() {
        return description;
        
    }
    
    
}