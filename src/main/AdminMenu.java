package main;

import api.AdminResources;
import model.customer.Customer;
import model.room.IRoom;
import model.room.Room;
import model.room.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    static AdminResources adminResources = AdminResources.getInstance();
    static Scanner ip = new Scanner(System.in);

    static void printAdminMenu(){
        System.out.println("""
                       ------------Admin Menu----------
                    -------------------------------------------
                    1. See all Customers
                    2. See all Rooms
                    3. see all Reservations
                    4. Add a Room
                    5. Back to Main Menu
                    
                    "Select One Of The Option Above"
                """);

    }

    static void seeAllCustomers(){
        Collection<Customer> allCustomers = adminResources.getAllCustomers();

        if(allCustomers.isEmpty()){
            System.out.println("No Customer");
        }
        else{
            for (Customer allCustomer : allCustomers) {
                System.out.println(allCustomer);
            }

        }
    }

    static void seeAllRooms(){
        Collection<IRoom> rooms = adminResources.getAllRooms();

        if (rooms.isEmpty()){
            System.out.println(" Add some rooms pls! ");
        }
        else{
            for (IRoom room : rooms) {
                System.out.println(room);
            }

        }
    }

    static void addARoom(){
        System.out.println("Enter Room Number: ");
        String roomNo = ip.nextLine();

        System.out.println("Enter A Price: ");
        Double price = Double.valueOf(ip.nextLine());

        System.out.println("Enter A Room Type: SINGLE/DOUBLE");
        RoomType roomType = RoomType.valueOf(ip.nextLine());

        List<IRoom> addRoom = new ArrayList<>();

        IRoom newRoom = new Room(roomNo,price,roomType,true);
        addRoom.add(newRoom);

        adminResources.addRoom(addRoom);
    }
    public static void adminMenu(){
        boolean onAdminMenu = true;

        while(onAdminMenu){
            try {
                printAdminMenu();

                int selectedOption = Integer.parseInt(ip.nextLine());

                switch (selectedOption){
                    case 1:
                        seeAllCustomers();
                        break;
                    case 2:
                        seeAllRooms();
                        break;
                    case 3:
                        adminResources.displayAllReservations();
                        break;
                    case 4:
                        addARoom();
                        break;
                    case 5:
                        onAdminMenu = false;
                        MainMenu.rootmenu();
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Please Enter A Valid Option :");
                adminMenu();
            }
        }



    }
}
