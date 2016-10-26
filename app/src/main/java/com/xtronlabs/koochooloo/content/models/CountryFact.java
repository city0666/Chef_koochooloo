package com.xtronlabs.koochooloo.content.models;


public final class CountryFact extends ModelBase{

    //Table name
    public static final String COUNTRY_FACT = "ZCOUNTRYFACT";

    //fields/columns
    public static final String FACT = "ZFACT";

    private String fact;

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
