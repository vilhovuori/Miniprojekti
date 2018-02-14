import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

public class Item {

    private String itemName;
    private int itemLocation;
    private String itemTarget;

    public Item (String itemName, int itemLocation, String itemTarget){
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

    public int getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(int itemLocation) {
        this.itemLocation = itemLocation;
    }

    public Object[] getItemTarget() {
        return itemTarget;
    }

    public void setItemTarget(String[] itemTarget) {
        this.itemTarget = itemTarget;
    }


    static {
        Item veitsi = new Item("veitsi", Main.map. , "Zombie");
    }
}
