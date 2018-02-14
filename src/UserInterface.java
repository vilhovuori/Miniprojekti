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

    public int take(String esine) {
        int e;
        switch (esine) {
            case "KNIFE":
                e = 5;
                break;
            case "DOORKEY":
                e = 6;
                break;
            case "COFFEE":
                e = 7;
                break;
            case "COFFEEKEY":
                e = 8;
                break;
            case "LETTER":
                e = 9;
            default:
                e = -1;
        }
        return e;

    }

    public String open() {
        return "OPEN";
    }

    public String help() {
        return "Usable commands:\nGO\nUSE\nTAKE\nSEARCH\nOPEN\nINVENTORY\nHELP";
    }

    public String actionTarget() {
        return null;
    }

}
