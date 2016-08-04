package com.xtronlabs.koochooloo.content.models;


public abstract class CountryBase extends ModelBase {

    public static final String COUNTRY = "ZCOUNTRY";


    private int country;

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }


}
