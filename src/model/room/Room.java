package model.room;

public class Room implements IRoom{

    private String roomNumber;
    private Double price;
    private RoomType enumeration;
    private Boolean booked;

    public Room(String roomNumber, Double price, RoomType enumeration, boolean booked) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.booked = booked;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public void setEnumeration(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return booked;
    }

    @Override
    public String toString(){
        return "Room Number :-> " + roomNumber +"\n" + "Room Type :-> " + enumeration + "\n" + "Room Price :->" + price +"\n"+ "Availability :-> "+ booked;

    }
}
