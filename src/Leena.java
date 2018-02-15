import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

public class Leena extends JFrame {

    JFrame window;
    Container con;
    JTextField input;

    public Leena() {

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(new FlowLayout());
        window.setVisible(true);
        con = window.getContentPane();
        input = new JTextField(25);
        //input.addActionListener(this);

//        public void actionPerformed(ActionEvent event) {
//            String text = input.getText();
//            textArea.append(text + newline);
//            input.selectAll();
//        }



        /*Room presentRoom;
        int compassPoint;
        Map<Integer, Room> building = new HashMap<>();



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



        UserInterface ui = new UserInterface();

        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        System.out.println("Welcome to JavaQuest, may I know your name so we can start writing your tombstone?");

//    String name = Scanner.nextLine ();
//    player.setPlayerName (name);
        while (true) {
            try {
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

                } else if (verb.equals("SEARCH") && target.equals("ROOM")) {
                    System.out.println(presentRoom.getDescription());

                } else {
                    System.out.println("Your command does not make any sense. Try again.");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("invalid command");
                continue;
            }


        }*/
    }

    public static void main(String[] args) {

        new Leena().setVisible(true);

    }
}



//    class action{}
//
//    class actionTarget{}

