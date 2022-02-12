package main;

import api.HotelResource;
import model.reservation.Reservation;
import model.room.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class MainMenu {
    static final HotelResource hotelResource = HotelResource.getInstance();
    static Scanner ip = new Scanner(System.in);
    public static void printMainMenu() {
        System.out.println("""
                    Welcome to the Hotel Reservation Application
                    -------------------------------------------
                    1. Find and reserve a room
                    2. See my reservations
                    3. Create an Account
                    4. Admin
                    5. Exit
                    
                    "Select One Of The Option Above"
                """);
    }

    public static void reserveARoom(Scanner ip, Date checkIn, Date checkOut, Collection<IRoom> availableRooms) {

        System.out.println("Would you like to book? y/n");
        String yOrN = ip.nextLine();

        if (yOrN.toLowerCase().equals("y")) {

            System.out.println("Do you have account with us?");

            String haveAccount = ip.nextLine();

            if (haveAccount.equals("y")) {

                System.out.println("Enter The Email Associated With The Account");
                String emailAssociated  = ip.nextLine();

                if(hotelResource.getCustomer(emailAssociated) == null){
                    System.out.println("Customer Not Found.\n You may need to create an Account");
                }
                else{

                    System.out.println("What room would you like to reserve");
                    System.out.println(availableRooms);

                    if(availableRooms.isEmpty()){

                        System.out.println(hotelResource.getFutureAvailableDatesForReservation(checkIn));
                    }
                    else {
                        String roomNumber = ip.nextLine();

                        for(IRoom room :availableRooms){
                            if (room.getRoomNumber().equals(roomNumber)){
                                IRoom selectedRoom = hotelResource.getRoom(roomNumber);

                                Reservation reservation = hotelResource.bookARoom(emailAssociated,selectedRoom,checkIn,checkOut);

                                System.out.println("Your Reservation is successful");
                            }
                            else {
                                System.out.println("get_Dates_when_room_available");
                                System.out.println("Room Not Found, Start reservation again");
                                rootmenu();
                            }
                        }
                    }


                }

            } else {
                System.out.println("please create an account main menu");
                rootmenu();
            }

        } else {
            rootmenu();
        }
    }

    public static void createCustomer(){
        Scanner ip = new Scanner(System.in);
        System.out.println("Enter your Email: name@domain.com");
        String email = ip.nextLine();

        System.out.println("First Name");
        String firstName = ip.nextLine();

        System.out.println("Last Name");
        String lastName = ip.nextLine();

        try {
            hotelResource.createACustomer(email,firstName,lastName);
            System.out.println("Account Has Been Successfully Created");
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getLocalizedMessage());
            createCustomer();
        }
    }

    public static void findAndRserveARoom(){
        System.out.println("Enter check in date: mm-dd-yyyy");
        String inDate = ip.nextLine();

        System.out.println("Enter check out date: mm-dd-yyyy");
        String outDate = ip.nextLine();

        Date checkIn = null;
        Date checkOut = null;

        if(inDate!= null && outDate != null){
            try {
                checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(inDate);
                checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(outDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

            reserveARoom(ip, checkIn,  checkOut,availableRooms );
        }
    }
    //-------------------------------------------------------------------------
    public static void main(String[] args) throws ParseException {

        rootmenu();

    }

    public static void rootmenu(){



        boolean onMain = true;
        while(onMain){

            try{
                printMainMenu();

                int selectedOption = Integer.parseInt(ip.nextLine());

                switch (selectedOption){
                    case 1:
                        findAndRserveARoom();
                        break;
                    case 2:
                        System.out.println("Enter email to check your reservation");
                        String email = ip.nextLine();

                        hotelResource.getCustomerReservation(email);

                    case 3:
                        createCustomer();
                        break;

                    case 4:
                        AdminMenu.adminMenu();
                        break;
                    case 5:
                        break;
                }

            }catch (Exception e){
                System.out.println("Please enter a valid option");
            }

        }
    }
}

