package Data;

public class Adress {
    //Ileride baska siniflarda da adres kullanilmasi istenirse diye adres sinifini olusturdum ve restoran sinifinda adress tipi ile adresi tuttum;

    private String country;
    private String city;
    private String street;

    public Adress(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Adress[" +
                "country='" + country +
                ", city='" + city +
                ", street='" + street +
                ']';
    }
}
