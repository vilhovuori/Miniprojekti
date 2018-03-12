public class Room {


    private int roomNumber;
    private String description;
    private int [] directions;


/* Room-luokka sisältää eri huoneiden ominaisuudet; konstruktorissa asetetaan huoneen indeksinumero, huoneen kuvaus ja suunnat, joihin huoneesta pääsee.
*  Kaikki pelin Room-oliot on luotu Main-luokassa.
*  setDescriptionin avulla Room-olioiden kuvaukset muuttuvat, kun pelaaja ottaa esineitä haltuun. setDirections-metodia kutsutaan, jos huoneessa oleva
* lukittu ovi avataan*/

    public Room (int roomNumber, String description, int [] directions  ) {
        this.roomNumber = roomNumber;
        this.description = description;
        this.directions = directions;

    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getDirections() {
        return directions;
    }

    public void setDirections(int[] directions) {
        this.directions = directions;
    }
}
