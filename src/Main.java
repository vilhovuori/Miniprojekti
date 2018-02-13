import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Room presentRoom;
    static Map<Integer,Room> map = new HashMap<>();
    static {
        Room huone = new Room("toimisto", 2, "The office, or so you at least think", new int[]{-1, -1, 3, 1});
        map.put(huone.getRoomNumber(), huone);
        presentRoom = huone;
        huone = new Room("meeting", 3, "The meeting, or so you at least think", new int[]{2, -1, -1, -1});
        map.put(huone.getRoomNumber(), huone);
    }


    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

    Scanner scanner = new Scanner (System.in);
    Player player = new Player ();
    System.out.println("Welcome to JavaQuest, may I know your name so we can start writing your tombstone?");

//    String name = Scanner.nextLine ();
//    player.setPlayerName (name);

        System.out.println("What you wish to do");
        String komento = scanner.nextLine();

        System.out.println(ui.action(komento));
        String [] komentoOsa = komento.split(" ");
        String kasky1 = komentoOsa [0].toUpperCase();
        String kasky2 = komentoOsa [1].toUpperCase();


















    }

}





//    class action{}
//
//    class actionTarget{}









