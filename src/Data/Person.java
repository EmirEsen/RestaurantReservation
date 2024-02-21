package Data;
import Util.Util;



public class Person {
    //id fieldi bir kere olusturulduktan sonra degistirilmesini istemedigimden final olarak tanimladim.
    private final String id;
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.id = Util.UUIDGenerator();
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
