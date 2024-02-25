package Util;

import Data.Customer;

//ileride farkli tipte siniflara farkli feildlar ve fonksiyonlar eklenebileceginden, her sinif icin bir interface tanimladim.
public interface ICustomerManagementActivities {
    void addNewCustomer();
    Customer addNewCustomer(String name, String surname);
    void deleteCustomer();
    Customer searchCustomerByName();
    void listCustomers();
}
