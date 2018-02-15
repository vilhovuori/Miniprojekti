import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Item {

    private String itemName;
    private Room itemLocation;
    private Room targetRoom;

    public Item (String itemName, Room itemLocation, Room targetRoom){
        this.itemName = itemName;
        this.itemLocation = itemLocation;
        this.targetRoom = targetRoom;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Room getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(Room itemLocation) {
        this.itemLocation = itemLocation;
    }

    static ArrayList <String> inventory = new ArrayList<>();


    static public boolean löytyyköTargetHuoneesta (String target, HashMap<String, Item> itemit) {       //metodi käy läpi taulukon itemit. Jos item löytyy listalta sekä
        String[] taulukko = {"KNIFE", "DOORKEY","COFFEE", "COFFEEKEY", "LETTER"};                       //item on samassa paikassa kun pelaaja, palautuu true. Jos ei ole, peli jatkuu
        for (String s : taulukko) {
            if (itemit.containsKey(s) && itemit.get(target).itemLocation == Main.presentRoom) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    static public boolean käyköHuoneessa (String target, HashMap<String, Item> itemit) {       //metodi käy läpi taulukon itemit. Jos item löytyy listalta sekä
        String[] taulukko = {"KNIFE", "DOORKEY","COFFEE", "LETTER","WATER","WIRE"};                       //item on samassa paikassa kun pelaaja, palautuu true. Jos ei ole, peli jatkuu
        for (String s : taulukko) {
            if (itemit.containsKey(s) && itemit.get(target).targetRoom == Main.presentRoom) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }


}
