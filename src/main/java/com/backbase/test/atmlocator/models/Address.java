package com.backbase.test.atmlocator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by luizsantana on 10/6/15.
 */
public class Address {
    private String street;
    @JsonProperty("housenumber")
    private String houseNumber;
    @JsonProperty("postalcode")
    private String postalCode;
    private String city;
    private GeoLocation geoLocation;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }
}
