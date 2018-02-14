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

        /*List<Item> esineLista = new ArrayList<>();
        esineLista.add(knife);
        esineLista.add(doorKey);*/
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







        }

    }
}


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

