public class Room {

    private String name;
    private int roomNumber;
    private String description;
    private int [] directions;



    public Room (String name, int roomNumber, String description, int [] directions  ) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.description = description;
        this.directions = directions;
        

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
