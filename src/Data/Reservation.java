package Data;

import Util.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private final String id;
    private String customerId;
    private String restaurantId;
    private LocalDateTime time;
    private int customerNumber;

    public Reservation(String customerId, String restaurantId, LocalDateTime time, int customerNumber) {
        this.id = Util.UUIDGenerator();
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.time = time;
        this.customerNumber = customerNumber;
    }

    //Ilk rezervasyondan sonra sadece zaman ve kisi sayisi revizyonuna izin vermek icin sadece bu iki field icin setter olusturdum.
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "res-Id= " + id +
                ", customerId= " + customerId +
                "[" + Database.printCustomerNameById(customerId) +
                "], time= " + time.format(formatter) +
                ", Place for = " + customerNumber +
                '}';
    }
}
