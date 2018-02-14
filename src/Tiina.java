import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tiina {


        static Room presentRoom;
        static int ilmansuunta; 
        static String esine;
        static Map<Integer, Room> building = new HashMap<>();

        static {

            Room huone = new Room("Cafeteria", 0, "The cafeteria, or so you at least think", new int[]{-1, 1, 5, -1});
            building.put(huone.getRoomNumber(), huone);
            huone = new Room("Lobby", 1, "The lobby, or so you at least think", new int[]{-1, 2, 4, 0});
            building.put(huone.getRoomNumber(), huone);
            huone = new Room("Office", 2, "The office, or so you at least think", new int[]{-1, -1, 3, 1});
            building.put(huone.getRoomNumber(), huone);
            huone = new Room("Meeting room", 3, "The meeting, or so you at least think", new int[]{2, -1, -1, -1});
            building.put(huone.getRoomNumber(), huone);
            huone = new Room("Classroom", 4, "The classroom, or so you at least think", new int[]{1, -1, -1, -1});
            building.put(huone.getRoomNumber(), huone);
            huone = new Room("Toilet", 5, "The toilet, or so you at least think", new int[]{0, -1, -1, -1});
            building.put(huone.getRoomNumber(), huone);
            presentRoom = building.get(4);
            System.out.println(presentRoom.getDescription());
        }

        public static void main(String[] args) {
            UserInterface ui = new UserInterface();

            Scanner scanner = new Scanner(System.in);
            Player player = new Player();
            System.out.println("Welcome to JavaQuest, may I know your name so we can start writing your tombstone?");

//    String name = Scanner.nextLine ();
//    player.setPlayerName (name);
            while (true) {
                System.out.println("What you wish to do");
                String komento = scanner.nextLine();

//        System.out.println(ui.action(komento));
                String[] komentoOsa = komento.split(" ");
                String kasky1 = komentoOsa[0].toUpperCase();
                String kasky2 = komentoOsa[1].toUpperCase();
                if (kasky1.equals("GO")) {
                    ilmansuunta = ui.go(kasky2);
                    System.out.println(ui.go(kasky2) + " Ilmansuunta");
                }
                if (kasky1.equals("TAKE")) {
                    esine = ui.take(kasky2);

                    System.out.println("You pick up the " + esine );
                }

                int adjacentRoom = presentRoom.getDirections()[ilmansuunta];

                if (adjacentRoom == -1 ) {
                    System.out.println("You cannot go to that direction.");
                } else {
                    presentRoom = building.get(presentRoom.getDirections()[ilmansuunta]);
                    System.out.println(presentRoom.getDescription());
                }
            }

        }
    }
    import java.util.Map;

public class UserInterface {
    static ArrayList <String> inventory = new ArrayList<>();

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
        inventory.add(Tiina.esine);
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




//    class action{}
//
//    class actionTarget{}











