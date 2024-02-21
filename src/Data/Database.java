package Data;

import java.util.ArrayList;
import java.util.List;

public abstract class Database {

    private static List<Restaurant> restaurantList = new ArrayList<>();
    private static List<Customer> customerList = new ArrayList<>();

    public static List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    //Ayni isim-soyisimde kisilerin kayitli olmasi ihtimaline karsi id leri gormek icin ismi ve soyismi eslesen kullanicilari liste olarak dondurur.
    public static List<Customer> findCustomersWithName(String name, String surname) {
        List<Customer> tempCustomerList = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name) && customer.getSurname().equalsIgnoreCase(surname)) {
                tempCustomerList.add(customer);
            }
        }
        if (tempCustomerList.isEmpty()) {
            System.out.println("Customers with name [" + name + " " + surname + "] can not found");
            return null;
        }
        return tempCustomerList;
    }

    //rezervasyon class'inin toString() metodunda kullanabilmek icin bu metodu yaszdim. Her rezervasyonda new Customer yaratamayacagimizdan customerId'yi String veri tipinde tutuyoruz.
    public static String printCustomerNameById(String id) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                return customer.getName() + " " + customer.getSurname();
            }
        }
        return "";
    }

    public static Customer findCustomerById(String customerId) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        System.out.println(customerId + " does not match. Re-Enter.");
        return null;
    }
}
