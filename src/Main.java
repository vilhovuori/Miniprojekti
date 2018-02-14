import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

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

    String name = scanner.nextLine ();
    player.setPlayerName (name);
        while (true) {
            try {
                System.out.println("What you wish to do");
                String command = scanner.nextLine();

//        System.out.println(ui.action(command));
                String[] commandPart = command.split(" ");
                String verb = commandPart[0].toUpperCase(); //verb part
                String target = null;
                if (commandPart.length > 1) {
                    target = commandPart[1].toUpperCase(); //target part
                }
                if (commandPart[0].equals("SEARCH")) {
                    presentRoom = building.get(presentRoom.getDirections()[compassPoint]);
                    System.out.println(presentRoom.getDescription());
                }
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
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("invalid command");
                continue;
            }

            Random randomGenerator = new Random();
            int playerRandomDamage = randomGenerator.nextInt(20);
            int zombieRandomDamage = randomGenerator.nextInt(20);
            int playerHealth = 20;
            int playerDamage = playerRandomDamage;
            int zombieHealth = 15;
            int zombieDamage = zombieRandomDamage;



            while (presentRoom ==building.get (2)&& zombieHealth ==15 ) {
                System.out.println("By the holy coffee beans, you have encountered a zombie, Coffee Zombie! " +
                        "Time to take out your weapons, hopefully you brought more then your lefty and right.");

                while (true) {

                    System.out.println("Fight, what do you want to use?");
                    System.out.println("Your HP:" + playerHealth + " " + "Coffee zombie's HP:" +zombieHealth);
                    String taisteluToiminto = scanner.nextLine().toUpperCase();
                    if (taisteluToiminto.equals("USE KNIFE")){
                        zombieHealth -= playerDamage;
                        playerHealth -= zombieDamage;
//                        int damageReceived= (playerHealth - zombieDamage);

                        if (zombieHealth > 0 && playerHealth > 0) {
                            System.out.println("It lives so...");
                        }

                        else if (zombieHealth > 0 && playerHealth <= 0) {
                            System.out.println("Coffee zombie lives, you do not");
                            break;

                        } else {
                            System.out.println("You are victorious");
                            break;
                        }} else {
                        System.out.println("You don't need to use your head literally, but it is indeed needed to type something that is asked of you!");
                    }


                }
            }


        }

    }
}


//    class action{}
//
//    class actionTarget{}









