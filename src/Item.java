import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
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


    {
        Item knife = new Item("veitsi", Tiina.building.get(0) , "Zombie");
        Item doorKey = new Item ("Avain oveen", Tiina.building.get(2), "lukko");
        Item coffee = new Item("kahvi", Tiina.building.get(0), "kahvinkeitin");
        Item coffeeKey = new Item("Avain keittimeen", Tiina.building.get(3), "kahvinkeitin");
        Item letter = new Item ("kirje", Tiina.building.get(1), null );
    }

}
