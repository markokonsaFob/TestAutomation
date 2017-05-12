package com.fobsolutions.unit;

import com.fobsolutions.unit.Family;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class UnitTest {

    Family family;
    Person person;

    @Before
    public void setup() {
        family = new Family("Konsa");
        person = new Person("Marko", Long.valueOf("39204122268"), Arrays.asList("Basketball", "Skateboarding", "Jogging"));
    }

    @Test
    public void testFamilySize() {
        family.addToFamily(person);
        assert family.getFamilyMembers().size() == 1;
    }

    @Test
    public void testGetFamilyMemberByName() throws Exception {
        family.addToFamily(person);
        assert family.getMemberWithName("Marko") == person;
    }

    @Test(expected=Exception.class)
    public void testGetFamilyMemberWithInvalidName() throws Exception {
        family.addToFamily(person);
        family.getMemberWithName("Konsa");
    }

    @Test
    public void testGetFamilyMemberByIdCode() throws Exception {
        family.addToFamily(person);
        assert family.getMemberWithIdCode(Long.valueOf("39204122268")) == person;
    }

    @Test(expected=Exception.class)
    public void testGetFamilyNameWithInvalidIdCode() throws Exception {
        family.addToFamily(person);
        family.getMemberWithIdCode(Long.valueOf("392041"));
    }

}
