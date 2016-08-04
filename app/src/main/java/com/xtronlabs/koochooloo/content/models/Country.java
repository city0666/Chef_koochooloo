package com.xtronlabs.koochooloo.content.models;


public final class Country extends ModelBase {

    //Table name
    public static final String COUNTRY = "ZCOUNTRY";

    //Fields/columns
    public static final String POPULATION = "ZPOPULATION";
    public static final String CAPITAL = "ZCAPITAL";
    public static final String CODE = "ZCODE";
    public static final String COVER = "ZCOVER";
    public static final String FLAG = "ZFLAG";
    public static final String LANGUAGES = "ZLANGUAGES";
    public static final String NAME = "ZNAME";
    public static final String WISH = "ZWISH";

    private int population;
    private String capital, code, cover, flag, languages, name, wish;

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
