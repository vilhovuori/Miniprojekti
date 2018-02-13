import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Room presentRoom;
    static int ilmansuunta;
    static Map<Integer, Room> map = new HashMap<>();

    static {

        Room huone = new Room("Cafeteria", 0, "The cafeteria, or so you at least think", new int[]{-1, 1, 5, -1});
        map.put(huone.getRoomNumber(), huone);
        huone = new Room("Lobby", 1, "The lobby, or so you at least think", new int[]{-1, 2, 4, 0});
        map.put(huone.getRoomNumber(), huone);
        huone = new Room("Office", 2, "The office, or so you at least think", new int[]{-1, -1, 3, 1});
        map.put(huone.getRoomNumber(), huone);
        huone = new Room("Meeting room", 3, "The meeting, or so you at least think", new int[]{2, -1, -1, -1});
        map.put(huone.getRoomNumber(), huone);
        huone = new Room("Classroom", 4, "The classroom, or so you at least think", new int[]{1, -1, -1, -1});
        map.put(huone.getRoomNumber(), huone);
        huone = new Room("Toilet", 5, "The toilet, or so you at least think", new int[]{0, -1, -1, -1});
        map.put(huone.getRoomNumber(), huone);
        presentRoom = map.get(4);
        System.out.println(presentRoom);
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
            if (kasky1 == "GO") {
                ilmansuunta = ui.go(kasky2);
                System.out.println(ilmansuunta + " Ilmansuunta");
            }
//            if (map.get(presentRoom.getDirections()[ilmansuunta]).equals(-1)) {
//                System.out.println("You cannot go to that direction.");
//            } else {
                presentRoom = map.get(presentRoom.getDirections()[ilmansuunta]);
                System.out.println(presentRoom.getDescription());
            }
        }

    }




//    class action{}
//
//    class actionTarget{}









