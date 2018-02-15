import java.util.Map;

public class UserInterface {

//    public int action(String command) {
//        if (command == "GO") {
//            return go(command);
//        }               return 0;
//


    public int go(String direction) {
        int d;
        switch (direction) {
            case "NORTH":
                d = 0;
                break;
            case "EAST":
                d = 1;
                break;
            case "SOUTH":
                d = 2;
                break;
            case "WEST":
                d = 3;
                break;
            default:
                d = -1;
        }
        return d;
    }

    public String use(String esine) {
        String u;
        switch (esine) {
            case "KNIFE":
                u = "You hit the zombie with your knife.";
                break;
            case "KEY":
                u = "You opened the door.";
               Main.building.get(3).setDirections(new int[]{2, -1, -1, 4});
               Main.building.get(4).setDirections(new int[]{1, 3, -1, -1});
                break;
            case "COFFEE":
                u = "You put the coffee in the coffee machine.";
                String s1=Main.building.get(5).getDescription();
                String replaceString=s1.replace(" A letter is placed on the reception desk.","");
                Main.building.get(5).setDescription(replaceString);
                break;
            case "ELECTRICWIRE":
                u = "The coffee machine is turned on.";
                Main.building.get(4).setDescription("You are in the Classroom. There's a door to the north and a locked door to the east. A coffee maker stands in the corner, and it is powered. \" +\n" + "In the other corner there's a thing that resembles a coffee bag with the label: \\\"KORVIKE-COFFEE\\\".");
                break;
            case "LETTER":
                u = "You openen the letter and it says: 'Instructions for good coffee: take some fresh water and aromatic coffee beans.'";
                break;
            case "WATER":
                u = "You put water in the coffee machine";
                break;
            default:
                u = "You cannot use this item here.";
                break;
        }
        return u;

    }

    public String take(String esine) {
        String e;
        switch (esine) {
            case "KNIFE":
                e = "knife";
                break;
            case "KEY":
                e = "key";
                break;
            case "COFFEE":
                e = "coffee";
                break;
            case "ELECTRICWIRE":
                e = "electricwire";

                break;
            case "LETTER":
                e = "letter";

                break;
            case "WATER":
                e = "water";
                break;
            default:
                e = "there is no such item";
                break;
        }
        return e;

    }

    public String open() {
        return "OPEN";
    }

    public String help() {
        return "Usable commands:\nGO DIRECTION\nUSE ITEMNAME\nTAKE ITEMNAME\nSEARCH ROOM\nCHECK ITEMS\nHELP ME";
    }

    public String actionTarget() {
        return null;
    }

}
