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
                    String roomNumber = ip.nextLine();

                    for(IRoom room :availableRooms){
                        if (room.getRoomNumber().equals(roomNumber)){
                            IRoom selectedRoom = hotelResource.getRoom(roomNumber);

                            Reservation reservation = hotelResource.bookARoom(emailAssociated,selectedRoom,checkIn,checkOut);

                            System.out.println("Your Reservation is successful");
                        }
                        else {
                            System.out.println("Room Not Found, Start reservation again");
                            rootmenu();
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
    //-------------------------------------------------------------------------
    public static void main(String[] args) throws ParseException {

        rootmenu();

    }

    public static void rootmenu(){

        Scanner ip = new Scanner(System.in);

        boolean onMain = true;
        while(onMain){

            printMainMenu();

            int selectedOption = Integer.parseInt(ip.nextLine());

            if(selectedOption == 1){
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

            if(selectedOption == 2){
                System.out.println("Enter email to check your reservation");
                String email = ip.nextLine();

                hotelResource.getCustomerReservation(email);
            }

            if(selectedOption == 3){
                createCustomer();
            }

            if(selectedOption == 4){
                AdminMenu.adminMenu();
            }

            if(selectedOption == 5){
                break;
            }

            else{
                System.out.println("Invalid Input \n Please select valid Input");

            }

        }
    }
}

