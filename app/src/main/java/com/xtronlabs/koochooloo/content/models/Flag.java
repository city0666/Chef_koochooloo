package com.xtronlabs.koochooloo.content.models;


public final class Flag extends ModelBase{

    //table name
    public static final String FLAG = "ZFLAG";

    //Fields/Columns
    public static final String NAME = "ZNAME";

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
