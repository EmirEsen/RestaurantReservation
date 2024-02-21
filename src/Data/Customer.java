package Data;

import Util.Util;

public class Customer extends Person {

    private final String id;
    private String phoneNumber;
    private String email;

    public Customer(String name, String surname, String phoneNumber, String email) {
        super(name, surname);
        this.id = Util.UUIDGenerator();
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id= " + id + ", " +
                super.toString() +
                ", gsm= " + phoneNumber +
                ", email= " + email +
                "} ";
    }
}
