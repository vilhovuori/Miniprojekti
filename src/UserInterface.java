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

    public String use() {
        return "USE";
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
