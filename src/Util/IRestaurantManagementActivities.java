package Util;

import Data.Restaurant;

//ileride farkli tipte siniflara farkli feildlar ve fonksiyonlar eklenebileceginden, her sinif icin bir interface tanimladim.
public interface IRestaurantManagementActivities {
    void addNewRestaurant();
    void deleteRestaurant();
    Restaurant searchRestaurantByName();
    void listRestaurants();
    int availabilityOnDate(Restaurant restaurant, int month, int day);

}
