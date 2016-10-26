package com.xtronlabs.koochooloo.content.models;


public final class Tool extends ModelBase {

    //CREATE TABLE ZTOOL ( Z_PK INTEGER PRIMARY KEY, Z_ENT INTEGER, Z_OPT INTEGER, ZNAME VARCHAR, ZURL VARCHAR )

    public static final String TOOL = "ZTOOL";
    public static final String NAME = "ZNAME";
    public static final String URL ="ZURL";


    private String name, url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
