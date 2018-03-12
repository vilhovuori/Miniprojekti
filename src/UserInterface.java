import java.util.Map;

public class UserInterface {

//    public int action(String command) {
//        if (command == "GO") {
//            return go(command);
//        }               return 0;
//

/* metodi public int go muuttaa String-tyyppiset directionit inteiksi*/
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
/* USE-command for different items. Particular item changes room description. Coffee, water and wire adds one point to the goal.*/
    public String use(String esine) {
        String u;
        switch (esine) {
            case "KEY":
                u = "You opened the door.";
                Main.building.get(3).setDirections(new int[]{2, -1, -1, 4});
                Main.building.get(4).setDirections(new int[]{1, 3, -1, -1});
                String s = Main.building.get(3).getDescription();
                String replaceString = s.replace("The western door is locked. ", "");
                Main.building.get(3).setDescription(replaceString);
                String s2 = Main.building.get(4).getDescription();
                String replaceString2 = s2.replace("locked", "unlocked");
                Main.building.get(4).setDescription(replaceString2);
                break;
            case "COFFEE":
                u = "You put the coffee in the coffee machine.";
                Main.coffeeMachineStatus = Main.coffeeMachineStatus + 1;
                Item.inventory.remove("COFFEE");

                break;
            case "WIRE":
                u = "The coffee machine is turned on.";
                Main.coffeeMachineStatus = Main.coffeeMachineStatus + 1;
                String s3 = Main.building.get(4).getDescription();
                String replaceString3 = s3.replace("A coffee maker stands in the corner, but there's no electric wire.", "A powered coffee maker stands in the corner.");
                Main.building.get(4).setDescription(replaceString3);
                Item.inventory.remove("WIRE");
                break;
            case "LETTER":
                u = "You opened the letter and it says: 'Instructions for good coffee: take some fresh water and aromatic coffee beans.'";
                break;
            case "WATER":
                u = "You put water in the coffee machine";
                Main.coffeeMachineStatus = Main.coffeeMachineStatus + 1;
                Item.inventory.remove("WATER");
                break;
            default:
                u = "You cannot use this item here.";
                break;
        }
        return u;

    }
/* metodi public String take muuttaa huoneen kuvauksen, kun huoneesta on otettu esine.*/


    public String take(String esine) {
        String e;
        switch (esine) {
            case "KNIFE":
                e = "KNIFE";
                String s1 = Main.building.get(0).getDescription();
                String replaceString1 = s1.replace("A knife is lying on the floor and it is bloody, but who cares.", "");
                Main.building.get(0).setDescription(replaceString1);
                break;
            case "KEY":
                e = "KEY";
                break;
            case "COFFEE":
                e = "COFFEE";
                String s2 = Main.building.get(0).getDescription();
                String replaceString2 = s2.replace("A bag of freshly ground coffee is placed on one of the dining tables.", "");
                Main.building.get(0).setDescription(replaceString2);

                break;
            case "WIRE":
                e = "WIRE";
                String s4 = Main.building.get(3).getDescription();
                String replaceString4 = s4.replace("The wire to the coffee maker lies on the table the middle of the room, may the scoundrel rest in peace.", "");
                Main.building.get(3).setDescription(replaceString4);
                break;
            case "LETTER":
                e = "LETTER";
                String s3 = Main.building.get(1).getDescription();
                String replaceString3 = s3.replace("A letter is placed on the reception desk.", "");
                Main.building.get(1).setDescription(replaceString3);
                break;
            case "WATER":
                e = "WATER";
                break;
            default:
                e = "there is no such item";
                break;
        }
        return e;
    }


/*metodi public String help	palauttaa komennot, joita pelissä voi käyttää*/
    public String help() {
        return "Usable commands:\nGO + DIRECTION (north, east, south, west)\nUSE + ITEMNAME\nTAKE + ITEMNAME\nSEARCH ROOM\nCHECK ITEMS\nHELP ME\nDuring fight:\nFIST\nKNIFE (if you have one)";
    }



}
