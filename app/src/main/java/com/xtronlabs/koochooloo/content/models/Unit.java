package com.xtronlabs.koochooloo.content.models;


public final class Unit extends ModelBase {

    //CREATE TABLE ZUNIT ( Z_PK INTEGER PRIMARY KEY, Z_ENT INTEGER, Z_OPT INTEGER, ZNAME VARCHAR )

    public static final String UNIT = "ZUNIT";
    public static final String NAME = "ZNAME";


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
