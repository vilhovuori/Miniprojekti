import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Item {


    private Room itemLocation;
    private Room targetRoom;
    static ArrayList <String> inventory = new ArrayList<>();
    static ArrayList <String> usedItems = new ArrayList<>();


    public Item (Room itemLocation, Room targetRoom){
        this.itemLocation = itemLocation;
        this.targetRoom = targetRoom;
    }

    static public boolean itemInRoom (String target, HashMap<String, Item> itemit) {       //metodi käy läpi taulukon esineet.
        String[] taulukko = {"KNIFE", "KEY","COFFEE", "WIRE", "LETTER", "WATER"};          //Jos etsitty esine löytyy listalta sekä
        for (String s : taulukko) {
            if (itemit.containsKey(s) && itemit.get(target).itemLocation == Main.presentRoom) {     //esine on samassa paikassa kun pelaaja, palautuu true.
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    static public boolean useableInRoom(String target, HashMap<String, Item> itemit) {       //metodissa pääosin sama idea kuin yllä.
        String[] taulukko = {"KNIFE", "KEY","COFFEE", "LETTER","WATER","WIRE"};              //tarkistaa onko esine taulukossa ja sen lisäksi katsoo onko esine samassa
        for (String s : taulukko) {                                                     //siinä huoneessa, jossa sitä voi käyttää.
            if (itemit.containsKey(s) && (itemit.get(target).targetRoom == Main.presentRoom || itemit.get(target).targetRoom.getRoomNumber() == 10000)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }


}
