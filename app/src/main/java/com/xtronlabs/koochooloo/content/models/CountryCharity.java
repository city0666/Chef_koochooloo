package com.xtronlabs.koochooloo.content.models;


public final class CountryCharity extends CountryBase {


    //Table name
    public static final String COUNTRY_CHARITY = "ZCOUNTRYCHARITY";

    //fields/columns
    public static final String DONATE = "ZDONATE";
    public static final String URL = "ZURL";

    private String donate,url;

    public String getDonate() {
        return donate;
    }

    public void setDonate(String donate) {
        this.donate = donate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
