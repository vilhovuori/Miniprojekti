import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    /* static variables*/
    static Room presentRoom;
    static int compassPoint;
    static String otettuEsine;
    static int coffeeMachineStatus = 0;
    static Map<Integer, Room> building = new HashMap<>();

    static {
        /*  Different room objects for the game*/
        Room huone = new Room(0, "\nYou are in the Cafeteria.\nThere are doors to the south and east. A knife is lying on the floor and it is bloody, but who cares.\n" +
                "A bag of freshly ground coffee is placed on one of the dining tables.", new int[]{-1, 1, 5, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room(1, "\nYou are in the Lobby.\nThere are doors to the east, south and west." +
                "\nA reception desk stands in the middle of the room, but there's no one there, thank god. A letter is placed on the reception desk.", new int[]{-1, 2, 4, 0});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room(2, "\nYou are in Office.\nThere are doors to the south and west.\nA sad, broken coffee machine stands in the corner. " + "\n*****" +
                "\nBut wait, something seems to be lurking behind the machine...", new int[]{-1, -1, 3, 1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room(3, "\nYou are in the Meeting Room. There are doors to the north and west. The western door is locked. " +
                "\nThe wire to the coffee maker lies on the table the middle of the room, may the scoundrel rest in peace.", new int[]{2, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room(4, "\nYou are in the Classroom.\nThere's a door to the north and a locked door to the east. A coffee maker stands in the corner, but there's no electric wire. "
                , new int[]{1, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room(5, "\nYou are in the Toilet. \nThere's a door to the north. There's a sink by the east wall and water is running from the faucet, does not anybody care about the environment anymore?", new int[]{0, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        presentRoom = building.get(4);
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface(); //command prompt for the game
        boolean gameStatus = true; //checks if player is dead after zombie
        int zombieKilledStatus = 0; //checks if the zombie is dead so you cannot get key twice
        int zombiecounter = 0; // after the end, this tells how many times the player killed the zombie
        int errorCount = 0; // gives help me -suggestion if the player inputs to many invalid commands

        /*  All useable items for the game , all i*/
        Item knife = new Item(building.get(0), building.get(2));
        Item key = new Item(building.get(2), building.get(3));
        Item coffee = new Item(building.get(0), building.get(4));
        Item wire = new Item(building.get(3), building.get(4));
        Item letter = new Item(building.get(1), new Room(null, 10000, null, null));
        Item water = new Item(building.get(5), building.get(4));

        Item.inventory.add("FIST");

        /* used for inventory and checking if item is in the same room as the player */
        HashMap<String, Item> itemit = new HashMap<>();
        itemit.put("KNIFE", knife);
        itemit.put("KEY", key);
        itemit.put("COFFEE", coffee);
        itemit.put("WIRE", wire);
        itemit.put("LETTER", letter);
        itemit.put("WATER", water);

        Scanner scanner = new Scanner(System.in);
        Player player = new Player(); //player name
        String tulos = null; //needed when printing ASCII
        /* TITLE ASCII*/
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
        System.out.println("\nWelcome to JavaQuest, may I know your name so we can start writing your tombstone?");

        String name = scanner.nextLine();
        player.setPlayerName(name);
        System.out.println("\nIt's 5.30 pm. You are all alone in the Academy classroom, working hard on your programming project." +
                "\nYour brain is starting to overload from all the fore-loops, so you feel the urge to sip a cup of steaming hot coffee.\n" +
                "You notice the coffee maker in the corner, but some scoundrel has stolen the electric wire.\n" +
                "You need coffee to survive. Go explore your surroundings... (If you need help to get started, type command 'help me')");
        /* Game run for this while loop. Most of the code is inside of this loop. If the player uses water, coffee and wire in the class room game ends. If the zombie kills the player it is game over*/
        while (coffeeMachineStatus < 3 && gameStatus == true) {
            try {
                System.out.println("What do you wish to do, " + name + "?");
                String command = scanner.nextLine();

                /* Player input, check every valid command in different if-statement*/
                String[] commandPart = command.split(" ");
                String verb = commandPart[0].toUpperCase(); //verb part
                String target = commandPart[1].toUpperCase();
                /* HELP-command*/
                if (verb.equals("HELP") && target.equals("ME")) {
                    System.out.println(ui.help());
                    /* GO-command*/
                } else if (verb.equals("GO") && (target.matches("NORTH|SOUTH|EAST|WEST"))) { //
                    compassPoint = ui.go(target);

                    int adjacentRoom = presentRoom.getDirections()[compassPoint];
                    if (adjacentRoom == -1) {
                        System.out.println("You cannot go to that direction.");
                    } else {
                        presentRoom = building.get(presentRoom.getDirections()[compassPoint]);
                        System.out.println(presentRoom.getDescription());
                    }
                    /* SEARCH-command*/
                } else if (verb.equals("SEARCH") && target.equals("ROOM")) {
                    System.out.println(presentRoom.getDescription());
                    /* USE-command*/
                } else if (verb.equals("USE") && Item.inventory.contains(target.toUpperCase())) {
                    if (Item.useableInRoom(target, itemit)) {
                        //   kayttoEsine = ui.take(target);
                        System.out.println(ui.use(target));
                        Item.usedItems.add(target);
                    } else {
                        System.out.println("You cannot use this item in this room.");
                    }
                    /* TAKE-command*/
                } else if (verb.equals("TAKE") && Item.itemInRoom(target, itemit)) {
                    otettuEsine = ui.take(target);
                    if (Item.inventory.contains(otettuEsine)) {
                        System.out.println("You already have this item");
                    } else if (Item.usedItems.contains(otettuEsine)) {
                        System.out.println("You have previously taken and used this item.");
                    } else {
                        System.out.println("You picked up the " + otettuEsine.toLowerCase());
                        Item.inventory.add(otettuEsine.toUpperCase());
                        System.out.println("Your items: " + Item.inventory);
                    }
                    /* CHECK-command*/
                } else if (verb.equals("CHECK") && target.equals("ITEMS")) {
                    System.out.println(Item.inventory);
                    /* error messages below */
                } else {
                    System.out.println("Your command does not make any sense. Try again.");
                    errorCount++;
                    if (errorCount == 3) {
                        System.out.println("A little voice in your head says: Help me");
                        errorCount = 0;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Are you stupid? You can't do that.");
                errorCount++;
                if (errorCount == 3) {
                    System.out.println("A little voice in your head says: Help me");
                    errorCount = 0;
                }
                continue;
            } catch (NullPointerException ex) {
                System.out.println("What were you thinking?!? You can't do that..");
                errorCount++;
                if (errorCount == 3) {
                    System.out.println("A little voice in your head says: Help me");
                    errorCount = 0;
                }
                continue;
            }
            /*names for zombies */
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

/* Fight with the zombie. Zombie never dies and resurrects if a new command is inputted after fight */
            while (presentRoom == building.get(2) && zombieHealth == 15) {
                zombiecounter++;
                System.out.println("By the holy coffee beans, you have encountered a zombie, Coffee Zombie that creeps by the name of" + " " + randomnimi + "!" + " " +
                        "\nTime to take out your weapons, hopefully you brought more then your lefty and right. ");
                /* Zombie picture */
                try (FileReader fr = new FileReader("zombie.txt");
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


                while (gameStatus == true) {

                    System.out.println("Fight, what do you want to use, KNIFE or FIST to take up this channel?");
                    System.out.println("Your HP:" + playerHealth + " " + "Coffee zombie's HP:" + zombieHealth);
                    String taisteluToiminto = scanner.nextLine().toUpperCase();

                    if (taisteluToiminto.matches("KNIFE|FIST")) {
                        if (taisteluToiminto.matches("KNIFE") && (Item.inventory.contains("KNIFE"))) {
                            playerDamage += 5;
                        } else if (taisteluToiminto.matches("KNIFE") && (!Item.inventory.contains("KNIFE"))) {
                            System.out.println("You do not have knife, " +
                                    "but ended up looking for an imaginary one while" + " " + zombieName + " " + "almost dies in laughter. \nHahaa, it did already! (Die I mean... It is a zombie after all..)");
                            continue;
                        }
                        zombieHealth -= playerDamage;
                        playerHealth -= zombieDamage;

                        if (zombieHealth > 0 && playerHealth > 0) {
                            System.out.println("The Zombie is still alive, so...");
                        } else if (zombieHealth > 0 && playerHealth <= 0) {
                            System.out.println("Coffee zombie lives, you do not! Good thing that your tombstone was started carving beforehand, \nso it is ready for you to use, may " + name + " rest in peace.");
                            gameStatus = false;
                            scanner.close();
                            /* Game over ASCII if the player dies */
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
                                System.out.println("Hey! Look what appeared from the zombies broken coffee cup! A KEY!!! It has been added to your items.");
                                Item.inventory.add("KEY");
                                zombieKilledStatus++;
                            }
                            break;
                        }
                    } else {
                        System.out.println("You don't need to use your head literally, but it is indeed needed to type something that is asked of you!");
                    }
                }
            }
        }
        /* THE END*/
        if (gameStatus == true) {
            System.out.println("You were able to make fresh coffee and continue your Java project. Hopefully, you will remember that today, \n your bodycount on this road has risen by " + zombiecounter + ", \nCongratulations and best of luck in the future!");
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
}








