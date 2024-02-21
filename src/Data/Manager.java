package Data;

import Util.Util;

public class Manager extends Person{

    private final String id;

    public Manager(String name, String surname) {
        super(name, surname);
        this.id = Util.UUIDGenerator();
    }

    @Override
    public String getId() {
        return id;
    }

}
