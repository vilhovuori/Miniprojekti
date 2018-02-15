import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tiina {

    static Room presentRoom;
    static int compassPoint;
    static String otettuEsine;
    static String kayttoEsine;
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

        Item knife = new Item("knife", building.get(0), building.get(2));
        Item key = new Item("Key to the lock on the door", building.get(2), building.get(3));
        Item coffee = new Item("coffeebeans", building.get(0), building.get(4));
        Item wire = new Item("Electric wire for the coffee machine", building.get(3), building.get(4));
        Item letter = new Item("letter", building.get(1), null);
        Item water = new Item("water", building.get(5), building.get(4));

        Item.inventory.add("fist");

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

        while (gameStatus == true) {
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

                } else if (verb.equals("USE") && Item.inventory.contains(target.toUpperCase()) && Item.useableInRoom(target,itemit)) {
                    kayttoEsine = ui.take(target);
                    System.out.println(ui.use(target));

                } else if (verb.equals("TAKE") && Item.löytyyköTargetHuoneesta(target, itemit)) {
                    otettuEsine = ui.take(target);
                    if (Item.inventory.contains(otettuEsine)) {
                        System.out.println("You already have this item");
                    } else {
                        System.out.println("You picked up the " + otettuEsine);
                        Item.inventory.add(otettuEsine.toUpperCase());
                        System.out.println(Item.inventory);
                    }
                    if (otettuEsine.equals("letter")) {
                        System.out.println("There is a note in the letter which says: 'Instructions for good coffee: take some fresh water and aromatic coffee beans.'");
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
                System.out.println("By the holy coffee beans, you have encountered a zombie, Coffee Zombie that creeps by the name of" + " " + randomnimi + "!" + " " +
                        "Time to take out your weapons, hopefully you brought more then your lefty and right.");

                while (true) {

                    System.out.println("Fight, what do you want to use, knife or fist to take up this channel?");
                    System.out.println("Your HP:" + playerHealth + " " + "Coffee zombie's HP:" + zombieHealth);
                    String taisteluToiminto = scanner.nextLine().toUpperCase();

                    if (taisteluToiminto.matches("KNIFE|FIST")) {
                        if (taisteluToiminto.matches("KNIFE") && (Item.inventory.contains(knife))) {
                            playerDamage += 5;
                        } else if (taisteluToiminto.matches("KNIFE") && (!Item.inventory.contains(knife))) {
//                            playerHealth -= zombieDamage/2;
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
                            System.out.println("You are victorious! YOU KILLED THE ZOMBIE, but for how long does it stay dead!");
                            break;
                        }
                    } else {
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















/*



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tiina {

    static Room presentRoom;
    static int compassPoint;
    static String otettuEsine;
    static Map<Integer, Room> building = new HashMap<>();

    static {

        Room huone = new Room("Cafeteria", 0, "You are in the Cafeteria. There are doors to the south and east. A knife is lying on the floor. " +
                "A bag of freshly ground coffee is placed on one of the dining tables.", new int[]{-1, 1, 5, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Lobby", 1, "You are in the Lobby. There are doors to the east, south and west. " +
                "A reception desk stands in the middle of the room, but there's no one there. A letter is placed on the reception desk.", new int[]{-1, 2, 4, 0});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Office", 2, "You are in Office. There are doors to the south and west. A sad, broken coffee machine stands in the corner. " +
                "But wait, something seems to be lurking behind the machine...", new int[]{-1, -1, 3, 1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Meeting room", 3, "You are in the Meeting Room. There are doors to the north and west. The western door is locked. " +
                "A key to the coffee maker lies on the table the middle of the room.", new int[]{2, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Classroom", 4, "You are in the Classroom. There's a door to the north and a locked door to the east. A coffee maker stands in the corner, but it's locked. " +
                "In the other corner there's a thing that resembles a coffee bag with the label: \"KORVIKE-COFFEE\".", new int[]{1, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Toilet", 5, "You are in the Toilet. There's a to the north. There's a sink by the east wall and water is running from the faucet.", new int[]{0, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        presentRoom = building.get(4);
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        boolean gameStatus = true;

        Item knife = new Item("knife", building.get(0) , "Zombie");
        Item key = new Item ("Key to the lock on the door", building.get(2), "lukko");
        Item coffee = new Item("coffeebeans", building.get(0), "kahvinkeitin");
        Item electricwire = new Item("Electric wire for the coffee machine", building.get(3), "kahvinkeitin");
        Item letter = new Item ("letter", building.get(1), null );
        Item water = new Item ("water", building.get(5), "kahvinkeitin");

        Item.inventory.add("fist");

        HashMap<String, Item> itemit = new HashMap<>();
        itemit.put("KNIFE", knife);
        itemit.put("KEY", key);
        itemit.put("COFFEE", coffee);
        itemit.put("ELECTRICWIRE", electricwire);
        itemit.put("LETTER", letter);
        itemit.put("WATER", water);

        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        String tulos = null;
        try (FileReader fr = new FileReader("title.txt");
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
        System.out.println("Welcome to JavaQuest, may I know your name so we can start writing your tombstone?");

        String name = scanner.nextLine ();
        player.setPlayerName (name);
        System.out.println(presentRoom.getDescription());
        while (gameStatus==true) {
            try {
                System.out.println("What do you wish to do, " + name + "?");
                String command = scanner.nextLine();

//        System.out.println(ui.action(command));
                String[] commandPart = command.split(" ");
                String verb = commandPart[0].toUpperCase(); //verb part
                String target = commandPart[1].toUpperCase();


                if (verb.equals("GO") && (target.matches("NORTH|SOUTH|EAST|WEST"))) { //
                    compassPoint = ui.go(target);

                    int adjacentRoom = presentRoom.getDirections()[compassPoint];
                    if (adjacentRoom == -1) {
                        System.out.println("You cannot go to that direction.");
                    } else {
                        presentRoom = building.get(presentRoom.getDirections()[compassPoint]);
                        System.out.println(presentRoom.getDescription());
                    }
                } else if (verb.equals("SEARCH")&& target.equals("ROOM")) {
                    System.out.println(presentRoom.getDescription());

                } else if (verb.equals("TAKE") && Item.löytyyköTargetHuoneesta(target, itemit)){
                    otettuEsine = ui.take(target);
                    if (Item.inventory.contains(otettuEsine)) {
                        System.out.println("You already have this item");
                    } else {
                        System.out.println("You picked up the " + otettuEsine);
                        Item.inventory.add(otettuEsine);
                        System.out.println(Item.inventory);
                        if (otettuEsine.equals("letter")) {
                            System.out.println("Instructions for good coffee: take some fresh water and aromatic coffee beans.");
                        }
                    }
                } else if (verb.equals("CHECK") && target.equals("ITEMS")) {
                    System.out.println(Item.inventory);
                }

                else {
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
                    "Aino", "Aleksi H.", "Aleksi P.", "Jani", "Johanna J.", "Heidi K", "Joni",
                    "Tom", "Juuso", "Hanna-Leena", "Johanna L.", "Milla", "Vellu", "Heidi N",
                    "Sami", "Outi", "Elina", "Renne", "Olli", "Toni", "Paula", "Leena", "Nikita",
                    "Tiina K.", "Tiina E.", "Antti", "Ville", "Waltteri", "Satu", "Tommi", "Samu"
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



            while (presentRoom ==building.get (2)&& zombieHealth ==15 ) {
                System.out.println("By the holy coffee beans, you have encountered a zombie, Coffee Zombie that creeps by the name of" + " "+ randomnimi+"!" +" " +
                        "Time to take out your weapons, hopefully you brought more then your lefty and right.");

                while (true) {

                    System.out.println("Fight, what do you want to use, knife or fist to take up this channel?");
                    System.out.println("Your HP:" + playerHealth + " " + "Coffee zombie's HP:" +zombieHealth);
                    String taisteluToiminto = scanner.nextLine().toUpperCase();
                    if (Item.inventory.contains(knife)) {
                        playerDamage += 5;}
                    if (taisteluToiminto.matches("KNIFE|FIST")){
                        zombieHealth -= playerDamage;
                        playerHealth -= zombieDamage;

                        if (zombieHealth > 0 && playerHealth > 0) {
                            System.out.println("The Zombie is still alive, so...");
                        }

                        else if (zombieHealth > 0 && playerHealth <= 0) {
                            System.out.println("Coffee zombie lives, you do not");
                            gameStatus=false;
                            try (FileReader fr = new FileReader("gameover.txt");
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

                            break;

                        } else {
                            System.out.println("You are victorious! YOU KILLED THE ZOMBIE, but for how long does it stay dead!");
                            break;
                        }} else {
                        System.out.println("You don't need to use your head literally, but it is indeed needed to type something that is asked of you!");
                    }


                }
            }


        }

    }

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

    public String take(String esine) {
        String e;
        switch (esine) {
            case "KNIFE":
                e = "knife";
                String s1=Main.building.get(0).getDescription();
                String replaceString1=s1.replace(" A knife is lying on the floor.","");
                Main.building.get(0).setDescription(replaceString1);
                break;
            case "KEY":
                e = "key";
                Main.building.get(0).setDescription("");
                break;
            case "COFFEE":
                e = "coffee";
                String s2=Main.building.get(0).getDescription();
                String replaceString2=s2.replace("A bag of freshly ground coffee is placed on one of the dining tables.","");
                Main.building.get(0).setDescription(replaceString2);
                break;
            case "ELECTRICWIRE":
                e = "electricwire";
                String s4=Main.building.get(3).getDescription();
                String replaceString4=s4.replace("The electric wire to the coffee maker lies on the table the middle of the room.","");
                Main.building.get(3).setDescription(replaceString4);
                break;
            case "LETTER":
                e = "letter";
                String s3=Main.building.get(1).getDescription();
                String replaceString3=s3.replace("A letter is placed on the reception desk.","");
                Main.building.get(1).setDescription(replaceString3);
                break;
            case "WATER":
                e = "water";
                break;
            default:
                e = "there is no such item";
                break;
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

*/


//    class action{}
//
//    class actionTarget{}


































/*
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tiina {

    static Room presentRoom;
    static int compassPoint;
    static String otettuEsine;
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

        Item knife = new Item("veitsi", Tiina.building.get(0) , "Zombie");
        Item doorKey = new Item ("Avain oveen", Tiina.building.get(2), "lukko");
        Item coffee = new Item("kahvi", Tiina.building.get(0), "kahvinkeitin");
        Item coffeeKey = new Item("Avain keittimeen", Tiina.building.get(3), "kahvinkeitin");
        Item letter = new Item ("kirje", Tiina.building.get(1), null );

List<Item> esineLista = new ArrayList<>();
        esineLista.add(knife);
        esineLista.add(doorKey);

        Item.inventory.add("FISTS");

        HashMap<String, Item> itemit = new HashMap<>();
        itemit.put("KNIFE", knife);
        itemit.put("DOORKEY", doorKey);
        itemit.put("COFFEE", coffee);
        itemit.put("COFFEEKEY", coffeeKey);
        itemit.put("LETTER", letter);

        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        System.out.println("Welcome to JavaQuest, may I know your name so we can start writing your tombstone?");

//    String name = Scanner.nextLine ();
//    player.setPlayerName (name);
        while (true) {
            System.out.println("What you wish to do");
            String command = scanner.nextLine();

//        System.out.println(ui.action(command));
            String[] commandPart = command.split(" ");
            String verb = commandPart[0].toUpperCase(); //verb part
            String target = commandPart[1].toUpperCase(); //target part
            if (verb.equals("GO") &&(target.matches("NORTH|SOUTH|EAST|WEST"))) { //
                compassPoint = ui.go(target);

                int adjacentRoom = presentRoom.getDirections()[compassPoint];
                if (adjacentRoom == -1) {
                    System.out.println("You cannot go to that direction.");
                } else {
                    presentRoom = building.get(presentRoom.getDirections()[compassPoint]);
                    System.out.println(presentRoom.getDescription());
                }
            } else if (verb.equals("TAKE") && Item.löytyyköTargetHuoneesta(target, itemit)){
                System.out.println("You picked up the " + target);
                otettuEsine = ui.take(target);

                Item.inventory.add(otettuEsine);
                System.out.println(Item.inventory);

            }

            else {
                System.out.println("Your command does not make any sense. Try again.");
            }

*/

   /*     } else {
            System.out.println("You picked up the " + otettuEsine);
            Item.inventory.add(otettuEsine);
            System.out.println(Item.inventory);
            if (otettuEsine.equals("letter")) {
                System.out.println("Instructions for good coffee: take some fresh water and aromatic coffee beans.");
            }
        }*/

/*



        }

    }
}

*/

//    class action{}
//
//    class actionTarget{}


 //   static ArrayList <String> inventory = new ArrayList<>();



  //  public String take() {
      //  if (Tiina.presentRoom.equals(Item)) {
        //    System.out.println("You pick up the " + Tiina.esine );
         //   inventory.add(Tiina.esine);

     //   }
      //  System.out.println("You can't do that");

     //   return "TAKE";
 //   }


/*
import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.Random;
        import java.util.Scanner;

public class Main {

    static Room presentRoom;
    static int compassPoint;
    static String otettuEsine;
    static Map<Integer, Room> building = new HashMap<>();

    static {

        Room huone = new Room("Cafeteria", 0, "You are in the Cafeteria. There are doors to the south and east. A knife is lying on the floor. " +
                "A bag of freshly ground coffee is placed on one of the dining tables.", new int[]{-1, 1, 5, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Lobby", 1, "You are in the Lobby. There are doors to the east, south and west. " +
                "A reception desk stands in the middle of the room, but there's no one there. A letter is placed on the reception desk.", new int[]{-1, 2, 4, 0});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Office", 2, "You are in Office. There are doors to the south and west. A sad, broken coffee machine stands in the corner. " +
                "But wait, something seems to be lurking behind the machine...", new int[]{-1, -1, 3, 1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Meeting room", 3, "You are in the Meeting Room. There are doors to the north and west. The western door is locked. " +
                "A key to the coffee maker lies on the table the middle of the room.", new int[]{2, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Classroom", 4, "You are in the Classroom. There's a door to the north and a locked door to the east. A coffee maker stands in the corner, but it's locked. " +
                "In the other corner there's a thing that resembles a coffee bag with the label: \"KORVIKE-COFFEE\".", new int[]{1, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        huone = new Room("Toilet", 5, "You are in the Toilet. There's a to the north. There's a sink by the east wall and water is running from the faucet.", new int[]{0, -1, -1, -1});
        building.put(huone.getRoomNumber(), huone);
        presentRoom = building.get(4);
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        boolean gameStatus = true;

        Item knife = new Item("veitsi", building.get(0), "Zombie");
        Item doorKey = new Item("Avain oveen", building.get(2), "lukko");
        Item coffee = new Item("kahvi", building.get(0), "kahvinkeitin");
        Item coffeeKey = new Item("Avain keittimeen", building.get(3), "kahvinkeitin");
        Item letter = new Item("kirje", building.get(1), null);

        Item.inventory.add("fist");

        HashMap<String, Item> itemit = new HashMap<>();
        itemit.put("KNIFE", knife);
        itemit.put("DOORKEY", doorKey);
        itemit.put("COFFEE", coffee);
        itemit.put("COFFEEKEY", coffeeKey);
        itemit.put("LETTER", letter);

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
        System.out.println(presentRoom.getDescription());
        while (gameStatus == true) {
            try {
                System.out.println("What do you wish to do, " + name + "?");
                String command = scanner.nextLine();

//        System.out.println(ui.action(command));
                String[] commandPart = command.split(" ");
                String verb = commandPart[0].toUpperCase(); //verb part
                String target = commandPart[1].toUpperCase();


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

                } else if (verb.equals("TAKE") && Item.löytyyköTargetHuoneesta(target, itemit)) {
                    otettuEsine = ui.take(target);
                    if (Item.inventory.contains(otettuEsine)) {
                        System.out.println("You already have this item");
                    } else {
                        System.out.println("You picked up the " + otettuEsine);
                        Item.inventory.add(otettuEsine);
                        System.out.println(Item.inventory);
                        if (otettuEsine.equals("letter")) {
                            System.out.println("Instructions for good coffee: take some fresh water and aromatic coffee beans.");
                        }
                    }
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

            Random randomGenerator = new Random();
            int playerRandomDamage = randomGenerator.nextInt(15);
            int zombieRandomDamage = randomGenerator.nextInt(20);
            int playerHealth = 20;
            int playerDamage = playerRandomDamage;
            int zombieHealth = 15;
            int zombieDamage = zombieRandomDamage;


            while (presentRoom == building.get(2) && zombieHealth == 15) {
                System.out.println("By the holy coffee beans, you have encountered a zombie, Coffee Zombie! " +
                        "Time to take out your weapons, hopefully you brought more then your lefty and right.");

                while (true) {

                    System.out.println("Fight, what do you want to use, knife or fist to take up this channel?");
                    System.out.println("Your HP:" + playerHealth + " " + "Coffee zombie's HP:" + zombieHealth);
                    String taisteluToiminto = scanner.nextLine().toUpperCase();
                    //    if (Item.inventory.contains(knife)) {
                    //       playerDamage += 5;}

                    if (taisteluToiminto.matches("KNIFE|FIST")) {
                        if (taisteluToiminto.matches("KNIFE") && (!Item.inventory.contains(knife))) {
                            playerDamage -= 5;
                            System.out.println("What knife? You don't have one. Sorry, but you can only use your fist. Good luck..");
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
                                System.out.println("You are victorious! YOU KILLED THE ZOMBIE, but for how long does it stay dead!");
                                break;
                            }
                        } else {
                            System.out.println("You don't need to use your head literally, but it is indeed needed to type something that is asked of you!");
                        }


                    }
                }


            }

        }
    }
}

//    class action{}
//
//    class actionTarget{}









*/
