package model.room;

public class FreeRoom extends Room{


    public FreeRoom(String roomNumber, Double price, RoomType enumeration, boolean booked) {
        super(roomNumber, price, enumeration, booked);
        setPrice(0.0);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
