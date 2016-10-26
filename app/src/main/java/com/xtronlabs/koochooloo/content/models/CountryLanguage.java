package com.xtronlabs.koochooloo.content.models;

public final class CountryLanguage extends CountryBase {

    //table name
    public static final String COUNTRY_LANGUAGE = "ZCOUNTRYLANGUAGE";

    //fields/columns
    public static final String LANGUAGE = "ZLANGUAGE";

    private int language;

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
}
