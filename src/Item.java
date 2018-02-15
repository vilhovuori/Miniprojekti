import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Item {

    private String itemName;
    private Room itemLocation;
    private String itemTarget;

    public Item (String itemName, Room itemLocation, String itemTarget){
        this.itemName = itemName;
        this.itemLocation = itemLocation;
        this.itemTarget = itemTarget;
    }

    public String useItem(){
        List<String> lista = Arrays.asList(itemTarget);
        //if (lista.contains(Target)
        return null;
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

      public String getItemTarget() {
        return itemTarget;
    }

    public void setItemTarget(String itemTarget) {
        this.itemTarget = itemTarget;
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



}
