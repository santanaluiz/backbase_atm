package com.backbase.test.atmlocator.models;

/**
 * Created by luizsantana on 10/6/15.
 */
public class ATM {
    private String type;
    private Address address;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
