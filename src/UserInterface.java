import java.util.Map;

public class UserInterface {

//    public String action(String command) {
//        if (command == "GO") {
//            return go();
//        }
//        return "virhetilanne";
//    }

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

    public String take() {
        return "TAKE";
    }

    public String search() {
        return "SEARCH";
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
