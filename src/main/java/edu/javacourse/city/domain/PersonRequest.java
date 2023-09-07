package edu.javacourse.city.domain;

import java.time.LocalDate;

public class PersonRequest {

    private String surName;
    private String givenName;
    private String patronymic;
    private LocalDate datOfBirth;
    private Integer streetCode;
    private String building;
    private String extension;
    private String apartment;

    public Integer getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Integer streetCode) {
        this.streetCode = streetCode;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String gvenName) {
        this.givenName = gvenName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDatOfBirth() {
        return datOfBirth;
    }

    public void setDatOfBirth(LocalDate datOfBirth) {
        this.datOfBirth = datOfBirth;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
