import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static Room presentRoom;
    static int compassPoint;
    static String otettuEsine;
    static int coffeeMachineStatus = 0;
    static Map<Integer, Room> building = new HashMap<>();

    static {

        Room huone = new Room("Cafeteria", 0, "You are in the Cafeteria.\nThere are doors to the south and east. A knife is lying on the floor.\n" +
                "A bag of freshly ground coffee is placed on one of the dining tables.", new int[]{-1, 1, 5, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Lobby", 1, "You are in the Lobby.\nThere are doors to the east, south and west." +
                "\nA reception desk stands in the middle of the room, but there's no one there. A letter is placed on the reception desk.", new int[]{-1, 2, 4, 0});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Office", 2, "You are in Office.\nThere are doors to the south and west.\nA sad, broken coffee machine stands in the corner. " +
                "\nBut wait, something seems to be lurking behind the machine...", new int[]{-1, -1, 3, 1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Meeting room", 3, "You are in the Meeting Room. There are doors to the north and west. The western door is locked. " +
                "\nThe wire to the coffee maker lies on the table the middle of the room.", new int[]{2, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Classroom", 4, "You are in the Classroom.\nThere's a door to the north and a locked door to the east. A coffee maker stands in the corner, but there's no electric wire. "
                , new int[]{1, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Toilet", 5, "You are in the Toilet. \nThere's a door to the north. There's a sink by the east wall and water is running from the faucet.", new int[]{0, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        presentRoom = building.get(4);
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        boolean gameStatus = true;
        int zombieKilledStatus = 0;
        int zombiecounter = 0;


        Item knife = new Item("knife", building.get(0), building.get(2));
        Item key = new Item("Key to the lock on the door", building.get(2), building.get(3));
        Item coffee = new Item("coffeebeans", building.get(0), building.get(4));
        Item wire = new Item("Electric wire for the coffee machine", building.get(3), building.get(4));
        Item letter = new Item("letter", building.get(1), building.get(1));
        Item water = new Item("water", building.get(5), building.get(4));

        Item.inventory.add("FIST");

        HashMap<String, Item> itemit = new HashMap<>();
        itemit.put("KNIFE", knife);
        itemit.put("KEY", key);
        itemit.put("COFFEE", coffee);
        itemit.put("WIRE", wire);
        itemit.put("LETTER", letter);
        itemit.put("WATER", water);

        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        String tulos = null;
        try (FileReader fr = new FileReader("title.txt");
             BufferedReader in = new BufferedReader(fr)) {
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
        System.out.println("Welcome to JavaQuest, may I know your name so we can start writing your tombstone?");

        String name = scanner.nextLine();
        player.setPlayerName(name);
        System.out.println("It's 5.30 pm. You are all alone in the Academy classroom, working hard on your programming project." +
                "\nYour brain is starting to overload from all the fore-loops, so you feel the urge to sip a cup of steaming hot coffee.\n" +
                "You notice the coffee maker in the corner, but some scoundrel has stolen the electric wire.\n" +
                "You need coffee to survive. Go explore your surroundings... ");

        while (coffeeMachineStatus < 3) {
            try {
                System.out.println("What do you wish to do, " + name + "?");
                String command = scanner.nextLine();

//        System.out.println(ui.action(command));
                String[] commandPart = command.split(" ");
                String verb = commandPart[0].toUpperCase(); //verb part
                String target = commandPart[1].toUpperCase();
                if (verb.equals("HELP") && target.equals("ME")) {
                    System.out.println(ui.help());
                } else if (verb.equals("GO") && (target.matches("NORTH|SOUTH|EAST|WEST"))) { //
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

                } else if (verb.equals("USE") && Item.inventory.contains(target.toUpperCase())) {
                    if (Item.useableInRoom(target, itemit)) {
                        //   kayttoEsine = ui.take(target);
                        System.out.println(ui.use(target));
                        Item.usedItems.add(target);
                    } else {
                        System.out.println("You cannot use this item in this room.");
                    }

                } else if (verb.equals("TAKE") && Item.itemInRoom(target, itemit)) {
                    otettuEsine = ui.take(target);
                    if (Item.inventory.contains(otettuEsine)) {
                        System.out.println("You already have this item");
                    } else if (Item.usedItems.contains(otettuEsine)) {
                        System.out.println("You have previously taken and used this item.");
                    } else {
                        System.out.println("You picked up the " + otettuEsine);
                        Item.inventory.add(otettuEsine.toUpperCase());
                        System.out.println(Item.inventory);
                    }

                } else if (verb.equals("CHECK") && target.equals("ITEMS")) {
                    System.out.println(Item.inventory);

                } else {
                    System.out.println("Your command does not make any sense. Try again.");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Are you stupid? You can't do that.");
                continue;
            } catch (NullPointerException ex) {
                System.out.println("What were you thinking?!? You can't do that..");
                continue;
            }
            String[] nimet = new String[]{
                    "Aino", "Aleksi H.", "Aleksi P", "Jani", "Johanna J", "Heidi K", "Joni",
                    "Tom", "Juuso", "Hanna-Leena", "Johanna L.", "Milla", "Vellu", "Heidi N",
                    "Sami", "Outi", "Elina", "Renne", "Olli", "Toni", "Paula", "Leena", "Nikita",
                    "Tiina K", "Tiina E", "Antti", "Ville", "Waltteri", "Satu", "Tommi", "Samu"
            };
            List<String> nimilista = new ArrayList<>(Arrays.asList(nimet));
            Collections.shuffle(nimilista);
            String randomnimi = nimilista.get(0);

            Random randomGenerator = new Random();
            int playerRandomDamage = randomGenerator.nextInt(15);
            int zombieRandomDamage = randomGenerator.nextInt(20);
            int playerHealth = 20;
            int playerDamage = playerRandomDamage;
            int zombieHealth = 15;
            int zombieDamage = zombieRandomDamage;
            String zombieName = randomnimi;


            while (presentRoom == building.get(2) && zombieHealth == 15) {
                zombiecounter ++;
                System.out.println("By the holy coffee beans, you have encountered a zombie, Coffee Zombie that creeps by the name of" + " " + randomnimi + "!" + " " +
                        "Time to take out your weapons, hopefully you brought more then your lefty and right.");

                while (true) {

                    System.out.println("Fight, what do you want to use, KNIFE or FIST to take up this channel?");
                    System.out.println("Your HP:" + playerHealth + " " + "Coffee zombie's HP:" + zombieHealth);
                    String taisteluToiminto = scanner.nextLine().toUpperCase();

                    if (taisteluToiminto.matches("KNIFE|FIST")) {
                        if (taisteluToiminto.matches("KNIFE") && (Item.inventory.contains("KNIFE"))) {
                            playerDamage += 5;
                        } else if (taisteluToiminto.matches("KNIFE") && (!Item.inventory.contains("KNIFE"))) {
                            System.out.println("You do not have knife, " +
                                    "but ended up looking for an imaginary one while" + " " + zombieName + " " + "almost dies in laughter, hahaa, he did already!");
                            continue;
                        }
                        zombieHealth -= playerDamage;
                        playerHealth -= zombieDamage;

                        if (zombieHealth > 0 && playerHealth > 0) {
                            System.out.println("The Zombie is still alive, so...");
                        } else if (zombieHealth > 0 && playerHealth <= 0) {
                            System.out.println("Coffee zombie lives, you do not");
                            gameStatus = false;
                            try (FileReader fr = new FileReader("gameover.txt");
                                 BufferedReader in = new BufferedReader(fr)) {
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

                            break;

                        } else {
                            System.out.println("You are victorious!! YOU KILLED THE ZOMBIE, but for how long does it stay dead!");
                            if (zombieKilledStatus == 0) {
                                System.out.println("You found a KEY. It has been added to your items.");
//                                ui.take("KEY");
//                                Item.inventory.contains("KEY");
                                Item.inventory.add("KEY");
                                zombieKilledStatus++;

                                //   knife.setItemLocation(null);


                            }


                            break;
                        }
                    } else {
                        System.out.println("You don't need to use your head literally, but it is indeed needed to type something that is asked of you!");
                    }


                }


            }

        }
        System.out.println("You were able to make fresh coffee and continue your Java project. Congratulations!");
        try (FileReader fr = new FileReader("end.txt");
             BufferedReader in = new BufferedReader(fr)) {
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
    }
}


//    class action{}
//
//    class actionTarget{}









