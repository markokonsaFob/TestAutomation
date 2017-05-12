package com.fobsolutions.unit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Family {

    String familyName;
    List<Person> familyMembers = new ArrayList<>();

    Family(String familyName) {
        this.familyName = familyName;
    }

    public void addToFamily(Person person) {
        familyMembers.add(person);
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<Person> getFamilyMembers() {
        return familyMembers;
    }

    public Person getMemberWithName(String name) throws Exception {
        Person person;

        for (Person member : familyMembers) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        throw new Exception("com.fobsolutions.unit.Family do not have a member with " + name +" name");
    }

    public Person getMemberWithIdCode(long idCode) throws Exception {
        Person person;

        for (Person member : familyMembers) {
            if (member.getIdCode() == idCode) {
                return member;
            }
        }
        throw new Exception("com.fobsolutions.unit.Family do not have a member with " + idCode +" id code");
    }
}
