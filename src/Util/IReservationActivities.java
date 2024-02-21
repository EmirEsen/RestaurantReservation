package Util;

import Data.Restaurant;

//ileride farkli tipte siniflara farkli feildlar ve fonksiyonlar eklenebileceginden, her sinif icin bir interface tanimladim.
public interface IReservationActivities {

    void makeReservation(Restaurant restaurant);
    void cancelReservation(Restaurant restaurant);
    void listReservations(Restaurant restaurant);
}
