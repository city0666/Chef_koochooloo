package com.xtronlabs.koochooloo.content.models;


public final class User extends ModelBase {

    //CREATE TABLE ZUSER ( Z_PK INTEGER PRIMARY KEY, Z_ENT INTEGER, Z_OPT INTEGER, ZNAME VARCHAR )

    public static final String USER = "ZUSER";
    public static final String NAME = "ZNAME";


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
