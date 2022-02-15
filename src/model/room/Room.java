package model.room;

import java.util.Objects;

public class Room implements IRoom{

    private String roomNumber;
    private Double price;
    private RoomType enumeration;
    private boolean isFree;

    public Room(String roomNumber, Double price, RoomType enumeration, boolean isFree) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.isFree = isFree;
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

    public void setFree(boolean free) {isFree =free;}

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
        return isFree;
    }

    @Override
    public String toString(){
        return "Room Number :-> " + roomNumber +"\n" + "Room Type :-> " + enumeration + "\n" + "Room Price :->" + price +"\n"+ "Is it free :-> "+ isFree;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (!(o instanceof Room room)) return false;
        if(o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(price, room.price)
                && Objects.equals(enumeration, room.enumeration) && Objects.equals(isFree, room.isFree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, enumeration, isFree);
    }
}
