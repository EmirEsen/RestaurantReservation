import Data.*;
import Util.*;

import java.time.LocalDateTime;

public class Main {
    static ManagerServiceImpl managerService = new ManagerServiceImpl();

    public static void main(String[] args) {
        //Sample data for testing.
        Restaurant restaurant = new Restaurant("Dun Moda",
                new Adress("Turkiye", "Istanbul", "Moda"), "0216-23", 50);
        Database.getRestaurantList().add(restaurant);

        Customer customer = new Customer("Emir", "Esen", "530-076-07-96", "emiresen246@gmail.com");
        Database.getCustomerList().add(customer);
        Customer customer1 = new Customer("Mina", "Esen", "530-076-07-96", "emiresen246@gmail.com");
        Database.getCustomerList().add(customer1);
        Customer customer2 = new Customer("Mina", "Esen", "530-", "asdasd@gmail.com");
        Database.getCustomerList().add(customer2);

        Reservation reservation = new Reservation(customer.getId(), restaurant.getId(), LocalDateTime.now(), 4);
        Reservation reservation2 = new Reservation(customer1.getId(), restaurant.getId(), LocalDateTime.now(), 10);
        restaurant.getReservations().add(reservation);
        restaurant.getReservations().add(reservation2);

        systemMenu();

    }

    public static void systemMenu() {
        System.out.println("""
                -----SYSTEM MENU-----
                 1- Reservation ops.
                 2- Restaurant ops.
                 3- Customer ops.
                 0- Quit.""");

        while (true) {
            int input = Util.intScanner("input: ");

            switch (input) {
                case 1:
                    System.out.println("Choose Restaurant");
                    Restaurant restaurant = managerService.searchRestaurantByName();
                    if (restaurant != null) {
                        reservationMenu(restaurant);
                    }
                    break;
                case 2:
                    restaurantMenu();
                    break;
                case 3:
                    customerMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void restaurantMenu() {
        System.out.println("""
                -----RESTAURANT MENU-----
                 1- Add new Restaurant
                 2- Delete restaurant
                 3- Search restaurant by name
                 4- List all restaurants
                 5- Show Options
                 6- Back
                 0- Quit
                """);

        while (true) {
            int input = Util.intScanner("Input (5- for options): ");

            switch (input) {
                case 1:
                    managerService.addNewRestaurant();
                    break;
                case 2:
                    managerService.deleteRestaurant();
                    break;
                case 3:
                    Restaurant restaurant = managerService.searchRestaurantByName();
                    if (restaurant != null) {
                        System.out.println(restaurant);
                    }
                    break;
                case 4:
                    managerService.listRestaurants();
                    break;
                case 5:
                    restaurantMenu();
                    break;
                case 6:
                    systemMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

        }
    }

    //todo list reservations on a specific date
    public static void reservationMenu(Restaurant restaurant) {
        System.out.println("--------- " + restaurant.getName().toUpperCase() + " ---------");
        System.out.println(""" 
                 1- Take new Reservation
                 2- Cancel Reservation
                 3- List reservations
                 4- Back
                """);

        while (true) {
            int input = Util.intScanner("Input (5- for options): ");

            switch (input) {
                case 1:
                    managerService.makeReservation(restaurant);
                    break;
                case 2:
                    managerService.cancelReservation(restaurant);
                    break;
                case 3:
                    managerService.listReservations(restaurant);
                    break;
                case 4:
                    systemMenu();
                    break;
                case 5:
                    reservationMenu(restaurant);
                    break;
            }
        }
    }

    public static void customerMenu() {
        System.out.println("""
                -----CUSTOMER MENU-----
                 1- Add New Customer
                 2- Delete Customer
                 3- Search Customer by Name
                 4- List All Customers
                 5- Back""");

        while (true) {
            int input = Util.intScanner("Input (6- for options): ");

            switch (input) {
                case 1:
                    managerService.addNewCustomer();
                    break;
                case 2:
                    managerService.deleteCustomer();
                    break;
                case 3:
                    managerService.searchCustomerByName();
                    break;
                case 4:
                    managerService.listCustomers();
                    break;
                case 5:
                    systemMenu();
                    break;
                case 6:
                    customerMenu();
                    break;
            }
        }
    }
}