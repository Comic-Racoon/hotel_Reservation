package service;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

import java.util.*;

public class ReservationService {

    //----------Singleton static reference ------------------
    // ref: https://www.programiz.com/java-programming/singleton
    private static ReservationService reservationService;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }
    //-------collection to store reservations and rooms--------
    /**
     * reservations --> Reservation obj .
     * rooms        --> key (room Number), value room obj.
     */
    static Collection<Reservation> reservations = new HashSet<>();
    static Map<String, IRoom> rooms = new HashMap<>();

    //------------add room------------------------------

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
        System.out.println("Room No: " + room.getRoomNumber() + "added");
    }

    //------------get a room ---------------------------

    /**
     * @param roomId
     * @return the room with roomId (room Number)
     */
    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    //-------------reserve a room --------------------------

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDAte, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDAte, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    //---------- find a room ---------------------------------



    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){

        // ref:- https://knowledge.udacity.com/questions/596090

        //The purpose of the method is to find
        // the available rooms between the given checkInDate and

        Collection<Reservation> alreadyBookedRooms =  reservations;
        Collection<IRoom> notAvailableRooms = new HashSet<>();

        for (Reservation alreadyBookedRoom : alreadyBookedRooms) {
            if( checkInDate.before(alreadyBookedRoom.getCheckOutDate()) && checkOutDate.after(alreadyBookedRoom.getCheckInDate())){
                notAvailableRooms.add(alreadyBookedRoom.getRoom());
            }
        }

        Collection<IRoom> allRooms = rooms.values();

        for(IRoom notAvailableRoom: notAvailableRooms){
            allRooms.removeIf(notAvailableRoom::equals);
        }

        return allRooms;
    }




    Date daysNext (Date checkInDate){
        ArrayList<Date> arr = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, 7);

        return calendar.getTime();
    }




//-----------               find available dates  -----------------
    public ArrayList futureAvailableDate(Date checkInDate){
        Date finalDays = daysNext(checkInDate);

        Collection<Reservation> alreadyBookedRooms =  reservations;
        ArrayList futureDates = new ArrayList();

        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        while (cal.getTime().before(finalDays)) {
            cal.add(Calendar.DATE, 1);
            for (Reservation alreadyBookedRoom : alreadyBookedRooms) {
                if(!cal.equals(alreadyBookedRoom.getCheckInDate())){
                    futureDates.add(cal.getTime());
                }
            }
        }
        return futureDates;
    }














    //----- get a customer reservation -------------------

    public Collection<Reservation> getCustomerReservation(Customer customer){
        Collection<Reservation> customerRes = new ArrayList<>();
        for(Reservation res : reservations){
            if(res.getCustomer().equals(customer)){
//                return (Collection<Reservation>) res;
                customerRes.add(res);
            }

        }
//        return (Collection<Reservation>) new IllegalArgumentException("Please check customer details ");
        System.out.println(customerRes);
        return customerRes;
   }

    //------------------------print all reservations-------------

    public void printAllReservation(){
        System.out.println(reservations);
    }

    //--------------------get all rooms --------------------------

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }
}
























