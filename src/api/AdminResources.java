package api;

import model.customer.Customer;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResources {
    //----------Singleton static reference ------------------
    // ref: https://www.programiz.com/java-programming/singleton
    private static AdminResources adminResources;

    private AdminResources() {
    }

    public static AdminResources getInstance() {
        if (adminResources == null) {
            adminResources = new AdminResources();
        }
        return adminResources;
    }

    private static final CustomerService customerService =  CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
