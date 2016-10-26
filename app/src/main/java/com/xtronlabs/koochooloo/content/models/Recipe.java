package com.xtronlabs.koochooloo.content.models;


public final class Recipe extends CountryBase {

    //Table name
    public static final String RECIPE = "ZRECIPE";

    //Fields/columns
    public static final String TYPE = "ZTYPE";
    public static final String USER = "ZUSER";
    public static final String NAME = "ZNAME";
    public static final String PRESENTATION = "ZPRESENTATION";
    public static final String TIME = "ZTIME";

    private int type, user;
    private String name, presentation, time;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
