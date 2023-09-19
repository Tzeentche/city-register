package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCheckDaoTest {

    @Test
    public void checkPerson() {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Vampirov");
        pr.setGivenName("Pablo");
        pr.setPatronymic("Escobarovich");
        pr.setDatOfBirth(LocalDate.of(1990, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");

        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse ps = new PersonResponse();
//        Assert.assertTrue(ps.isRegistered());
//        Assert.assertFalse(ps.isTemporal());
    }
}