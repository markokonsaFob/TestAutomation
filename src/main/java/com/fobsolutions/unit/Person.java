package com.fobsolutions.unit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Person {

    String name;
    long idCode;
    List<String> hobbies = new ArrayList<>();

    Person(String name, long idCode, List<String> hobbies) {
        this.name = name;
        this.idCode = idCode;
        this.hobbies = hobbies;
    }

    public void addHobby(String hobby) {
        this.hobbies.add(hobby);
    }

    public String getName() {
        return name;
    }

    public long getIdCode() {
        return idCode;
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}
