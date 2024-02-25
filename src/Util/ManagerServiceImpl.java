package Util;

import Data.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ManagerServiceImpl implements IRestaurantManagementActivities, ICustomerManagementActivities, IReservationActivities {
    @Override
    public void addNewCustomer() {
        String name = Util.stringScanner("Enter Customer name: ");
        String surname = Util.stringScanner("Enter Customer surname: ");
        String phoneNumber = Util.stringScanner("Enter Customer phoneNumber: ");
        String email = Util.stringScanner("Enter Customer email: ");
        Customer customer = new Customer(name.toUpperCase(), surname.toUpperCase(), phoneNumber, email);
        System.out.println(customer + " has been added to DB.");
        Database.getCustomerList().add(customer);
    }

    //Eger musteri kaydi bulunamazsa aranan musterinin isim ve soyisimi ile yeni kayit olusturulucak.
    //Bu metod sayesinde kulanicidan tekrar isim soyisim istenmeyecek.
    @Override
    public Customer addNewCustomer(String name, String surname) {
        System.out.println("Continue for new registration --> ");
        String phoneNumber = Util.stringScanner("Enter Customer phoneNumber: ");
        String email = Util.stringScanner("Enter Customer email: ");
        Customer customer = new Customer(name.toUpperCase(), surname.toUpperCase(), phoneNumber, email);
        System.out.println(customer + " has been added to DB.");
        Database.getCustomerList().add(customer);
        return customer;
    }

    @Override
    public void deleteCustomer() {
        Customer customer = searchCustomerByName();
        if (customer != null) {
            String id = Util.stringScanner("Enter Customer Id to Remove: ");
            for (Customer cst : Database.getCustomerList()) {
                if (cst.getId().equals(id)) {
                    Database.getCustomerList().remove(cst);
                    System.out.println(customer + " has been removed.");
                    return;
                }
            }
            System.out.println(id + " can not found.");
        }
    }

    @Override
    public Customer searchCustomerByName() {
        String name = Util.stringScanner("Enter Customer name: ").trim();
        String surname = Util.stringScanner("Enter Customer surname: ").trim();


        String header = "-".repeat(55) + "CUSTOMERS FOUND" + "-".repeat(55);
        System.out.println(header);
        Customer customer = null;
        for (Customer cst : Database.getCustomerList()) {
            if (cst.getName().equalsIgnoreCase(name) && cst.getSurname().equalsIgnoreCase(surname)) {
                System.out.println(cst);
                customer = cst;
            }
        }
        System.out.println("-".repeat(header.length()));
        if (customer == null) {
            System.out.println(name + " " + surname + " can not found.");
            return null;
        }
        return customer;

    }

    @Override
    public void listCustomers() {
        String header = "-".repeat(55) + "CUSTOMERS" + "-".repeat(55);
        System.out.println(header);
        Database.getCustomerList().forEach(System.out::println);
        System.out.println("-".repeat(header.length()));
    }

    @Override
    public void addNewRestaurant() {
        String name = Util.stringScanner("Restaurant Name: ");
        int capacity = Util.intScanner("Enter capacity: ");
        String phoneNumber = Util.stringScanner("Phone number: ");
        System.out.println("--Adress Section--");
        String country = Util.stringScanner("Country: ");
        String city = Util.stringScanner("City: ");
        String street = Util.stringScanner("Street: ");

        Restaurant restaurant = new Restaurant(name.toUpperCase(), new Adress(country, city, street), phoneNumber, capacity);
        Database.getRestaurantList().add(restaurant);
        System.out.println("Restaurant " + restaurant.getName() + " has been added.");
    }

    @Override
    public void deleteRestaurant() {
        listRestaurants();
        String id = Util.stringScanner("Enter Restaurant Id to Remove: ");
        for (Restaurant restaurant : Database.getRestaurantList()) {
            if (restaurant.getId().equals(id)) {
                Database.getRestaurantList().remove(restaurant);
                System.out.println(restaurant + " has been removed.");
                return;
            }
        }
        System.out.println(id + " can not found.");
    }

    @Override
    public Restaurant searchRestaurantByName() {
        String name = Util.stringScanner("Restaurant name: ").trim();
        for (Restaurant restaurant : Database.getRestaurantList()) {
            if (restaurant.getName().equalsIgnoreCase(name)) {
                return restaurant;
            }
        }

        System.out.println("Restaurant [" + name + "] can not found.");
        return null;
    }

    @Override
    public void listRestaurants() {
        String header = "-".repeat(55) + "RESTAURANTS" + "-".repeat(55);
        System.out.println(header);
        Database.getRestaurantList().forEach(System.out::println);
        System.out.println("-".repeat(header.length()));
    }

    //returns available seats on given month and day
    @Override
    public int availabilityOnDate(Restaurant restaurant, int month, int day) {
        return restaurant.getCapacityOnMonthDay(month, day);
    }

    @Override
    public void makeReservation(Restaurant restaurant) {
        if (restaurant.getStatus().equals(Restaurant.Status.CLOSE)) {
            System.out.println(restaurant.getName() + " restaurant is closed.");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //Kullaniciya girilmesi gereken tarih formatini gosterir.
        String str = Util.stringScanner("Date: [" + currentTime.format(dateTimeFormatter) + "]: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime resDate = LocalDateTime.parse(str, formatter);

        int resMonth = resDate.getMonthValue();
        int resDay = resDate.getDayOfMonth();
        int availablePlace = availabilityOnDate(restaurant, resMonth, resDay);

        int headCount = 0;
        if (availablePlace > 0) {
            System.out.println(restaurant.getName().toUpperCase() + " -> " + availablePlace
                    + " places are available on " + resDay + " " + resDate.getMonth().toString());
            do {
                headCount = Util.intScanner("Place for #: ");
                if (headCount < 1) {
                    System.out.println("input can not be ZERO, Re-Enter.");
                }
            } while (headCount < 1);

            if (headCount > restaurant.getCapacityOnMonthDay(resMonth, resDay)) {
                System.out.println("Sorry, only " + restaurant.getCapacityOnMonthDay(resMonth, resDay) + " places are available.");
                return;
            }
            List<Customer> customers;
            String customerName = Util.stringScanner("Customer F.Name: ").trim();
            String customerSurname = Util.stringScanner("Customer Surname: ").trim();
            customers = Database.findCustomersWithName(customerName, customerSurname);

            Customer customer;
            if (customers != null) {
                printCustomerList(customers);
                do {
                    String customerId = Util.stringScanner("Customer Id: ");
                    customer = Database.findCustomerById(customerId);
                } while (customer == null);
            } else {
                customer = addNewCustomer(customerName, customerSurname);
            }

            Reservation reservation = new Reservation(customer.getId(),
                    restaurant.getId(), resDate, headCount);

            restaurant.getReservations().add(reservation);
            restaurant.setCapacityOnMonthDay(resMonth, resDay, restaurant.getCapacityOnMonthDay(resMonth, resDay) - headCount);

            restaurant.reservationSuccessMessage(customer, headCount, resDate.format(dateTimeFormatter));
        } else {
            System.out.println("Not available on " + resDay + " " + resDate.getMonth());
        }
    }

    //helper method to lists found customers
    private void printCustomerList(List<Customer> customers) {
        String header = "-".repeat(55) + "CUSTOMERS FOUND" + "-".repeat(55);
        System.out.println(header);
        customers.forEach(System.out::println);
        System.out.println("-".repeat(header.length()));
    }

    @Override
    public void cancelReservation(Restaurant restaurant) {
        listReservations(restaurant);
        Reservation res = restaurant.getReservationWithId();
        int resMonth = res.getTime().getMonthValue();
        int resDay = res.getTime().getDayOfMonth();

        if (res != null) {
            restaurant.getReservations().remove(res);
            System.out.println("X " + res + " HAS BEEN CANCELLED.");
            restaurant.setCapacityOnMonthDay(resMonth, resDay, restaurant.getCapacityOnMonthDay(resMonth, resDay) + res.getCustomerNumber());
        }
    }

    @Override
    public void listReservations(Restaurant restaurant) {
        String header = "-".repeat(65) + restaurant.getName().toUpperCase() + " RESERVATIONS" + "-".repeat(65 - restaurant.getName().length());
        System.out.println(header);
        restaurant.getReservations().forEach(System.out::println);
        System.out.println("-".repeat(header.length()));
    }
}
