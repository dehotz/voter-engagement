package org.launchcode.voterengagement.models.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Address {

    private int id;
    private static int nextId = 1;

    @NotNull
    @Size(min=3, max=50)
    private String address1;

    @Size(max=50)
    private String address2;

    @NotNull
    @Size(min=1, max=20)
    private String city;

    @NotNull
    @Size(min=1, max=20)
    private String state;

    @NotNull
    @Size(min=5, max=5)
    private String zipCode;

    public Address () {
        id = nextId;
        nextId++;
    }

    public Address (String address1, String address2, String city, String state, String zipCode) {

        this();

        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
