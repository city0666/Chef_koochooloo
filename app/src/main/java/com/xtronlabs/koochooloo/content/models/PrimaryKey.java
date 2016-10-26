package com.xtronlabs.koochooloo.content.models;


public final class PrimaryKey extends ModelBase {

    //CREATE TABLE Z_PRIMARYKEY (Z_ENT INTEGER PRIMARY KEY, Z_NAME VARCHAR, Z_SUPER INTEGER, Z_MAX INTEGER)

    public static final String PRIMARYKEY = "Z_PRIMARYKEY";
    public static final String NAME = "ZNAME";
    public static final String SUPER = "Z_SUPER";
    public static final String MAX = "Z_MAX";


    private String name;
    private int suprer, max;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSuprer() {
        return suprer;
    }

    public void setSuprer(int suprer) {
        this.suprer = suprer;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
