import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Juuso {

    static Room presentRoom;
    static int compassPoint;
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

        String tulos = null;
        try (FileReader fr = new FileReader("Miniprojekti/title.txt");
             BufferedReader in = new BufferedReader(fr)){
            StringBuilder teksti = new StringBuilder();
            String rivi;
            while ((rivi = in.readLine()) != null) {
                teksti.append(rivi).append("\n");
            }
            tulos = teksti.toString();
            System.out.println(tulos);
        } catch (FileNotFoundException ex) {
            System.out.println("Virhe: tiedostoa ei löytynyt");
        } catch (IOException ex) {
            System.out.println("Virhe: muu virhe lukiessa");
        }

//    String name = Scanner.nextLine ();
//    player.setPlayerName (name);
        while (true) {
            System.out.println("What you wish to do");
            String command = scanner.nextLine();

//        System.out.println(ui.action(command));
            String[] commandPart = command.split(" ");
            String verb = commandPart[0].toUpperCase(); //verb part
            String target = commandPart[1].toUpperCase(); //target part
            if (verb.equals("GO") && (target.matches("NORTH|SOUTH|EAST|WEST"))) { //
                compassPoint = ui.go(target);

                int adjacentRoom = presentRoom.getDirections()[compassPoint];
                if (adjacentRoom == -1) {
                    System.out.println("You cannot go to that direction.");
                } else {
                    presentRoom = building.get(presentRoom.getDirections()[compassPoint]);
                    System.out.println(presentRoom.getDescription());
                }
            } else {
                System.out.println("Your command does not make any sense. Try again.");
            }


        }

    }
}


//    class action{}
//
//    class actionTarget{}









