package Data;

import Util.Util;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    //Restoran sinifi disindaki bir sinifin status'u olmayacagindan, Status Enum sinifini Restaurant sinifi icinde tanimladim.
    //Boylelikle restoran objesi yaratildiktan sonra stastuse ulasilacak.
    public enum Status {
        OPEN, CLOSE
    }
    private final int[][] monthDay = new int[12][31];
    private final String id;
    private String name;
    private Adress adress;
    private String phoneNumber;
    private int capacity;
    private Status status;
    private List<Reservation> reservations;

    //initializing daily reservation capacity for only one year.
    public Restaurant(String name, Adress adress, String phoneNumber, int capacity) {
        for (int i = 0; i < monthDay.length; i++) {
            for (int j = 0; j < monthDay[i].length; j++) {
                monthDay[i][j] = capacity;
            }
        }
        this.id = Util.UUIDGenerator();
        this.name = name;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.reservations = new ArrayList<>();
        this.status = Status.OPEN;
    }

    public int getCapacityOnMonthDay(int month, int day) {
        return monthDay[month][day];
    }

    public void setCapacityOnMonthDay(int month, int day, int newCapacity) {
        monthDay[month][day] = newCapacity;
    }

    //print message for reservation
    public void reservationSuccessMessage(Customer customer, int placesFor, String time) {
        System.out.println("A rezervation for " +
                placesFor + " people in " + customer.getName() + " " +
                customer.getSurname() +
                "'s name has been made for " + time);
    }

    public Reservation getReservationWithId() {
        String resId = Util.stringScanner("Enter Reservation Id: ");
        for (Reservation res : this.getReservations()) {
            if (res.getId().equals(resId)) {
                return res;
            }
        }
        System.out.println(resId + " can not found.");
        return null;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Adress getAdress() {
        return adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public Status getStatus() {
        return status;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id= " + id +
                ", name= " + name.toUpperCase() +
                ", " + adress +
                ", phoneNumber= " + phoneNumber +
                ", capacity= " + capacity +
                ", status= " + status +
                '}';
    }
}
