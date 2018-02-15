import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* Tässä luokassa luodaan Item-olioiden konstruktori sekä Arraylistit. Inventory-listaan kerätään pelaajan keräämät itemit ja usedItems-listaan
  * kerätään itemit, jotka on käytetty.
   * ItemInRoom-metodi käy läpi taulukon, johon on kerätty pelin esineet ja etsii pelaajan syöttämää esinettä niistä.
   * Jos syötetty esine löytyy taulukosta sekä pelaaja on sillä hetkellä samassa huoneessa kuin pelaajan syöttämä esine, palautuu true.
   * Kutsutaan main-luokasta silloin kun halutaan tarkistaa, onko pelaajalla jo olemassa jokin esine, ja jos on sitä ei voi ottaa uudestaan.
   *
   * Pitkälti samat toiminnot esiintyy metodissa usableInRoom. Eli kun pelaaja haluaa käyttää jotain esinettä, metodi tarkistaa onko esinettä mahdollista
   * käyttää kyseisessä huoneessa. Kunkin esineen käyttö on sidottu vain yhteen tiettyyn huoneeseen. Ainoastaan letterin voi lukea kaikissa huoneissa*/

public class Item {


    private Room itemLocation;
    private Room targetRoom;
    static ArrayList <String> inventory = new ArrayList<>();
    static ArrayList <String> usedItems = new ArrayList<>();


    public Item (Room itemLocation, Room targetRoom){
        this.itemLocation = itemLocation;
        this.targetRoom = targetRoom;
    }

    static public boolean itemInRoom (String target, HashMap<String, Item> itemit) {       //metodi käy läpi taulukon itemit. Jos item löytyy listalta sekä
        String[] taulukko = {"KNIFE", "KEY","COFFEE", "WIRE", "LETTER", "WATER"};                       //item on samassa paikassa kun pelaaja, palautuu true. Jos ei ole, peli jatkuu
        for (String s : taulukko) {
            if (itemit.containsKey(s) && itemit.get(target).itemLocation == Main.presentRoom) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    static public boolean useableInRoom(String target, HashMap<String, Item> itemit) {       //metodi käy läpi taulukon itemit. Jos item löytyy listalta sekä
        String[] taulukko = {"KNIFE", "KEY","COFFEE", "LETTER","WATER","WIRE"};                       //item on samassa paikassa kun pelaaja, palautuu true. Jos ei ole, peli jatkuu
        for (String s : taulukko) {
            if (itemit.containsKey(s) && (itemit.get(target).targetRoom == Main.presentRoom || itemit.get(target).targetRoom.getRoomNumber() == 10000)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }


}
