package api;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    //----------Singleton static reference ------------------
    // ref: https://www.programiz.com/java-programming/singleton

    private static HotelResource hotelResource;
    private HotelResource(){}

    public static HotelResource getInstance(){
        if(hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    private static final CustomerService customerService =  CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    //----------get Customer ------------------------------------


    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservation(String customerEmail){
        return reservationService.getCustomerReservation(customerService.getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn , Date checkOut){
        return reservationService.findARoom(checkIn, checkOut);
    }

    public void getFutureAvailableDatesForReservation(Date CheckIn){
        reservationService.futureAvailableDate(CheckIn);
    }
}
